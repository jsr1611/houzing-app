package uz.digitalone.houzingapp.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import uz.digitalone.houzingapp.Exception.VerifyTokenNotFound;
import uz.digitalone.houzingapp.dto.NotificationEmail;
import uz.digitalone.houzingapp.dto.VerificationLink;
import uz.digitalone.houzingapp.dto.request.RefreshTokenRequest;
import uz.digitalone.houzingapp.dto.request.LoginRequest;
import uz.digitalone.houzingapp.dto.request.RegUserDto;
import uz.digitalone.houzingapp.dto.response.AuthenticationResponse;
import uz.digitalone.houzingapp.dto.response.Response;
import uz.digitalone.houzingapp.entity.Role;
import uz.digitalone.houzingapp.entity.User;
import uz.digitalone.houzingapp.entity.auth.VerificationToken;
import uz.digitalone.houzingapp.repository.RoleRepository;
import uz.digitalone.houzingapp.repository.UserRepository;
import uz.digitalone.houzingapp.repository.VerificationTokenRepository;
import uz.digitalone.houzingapp.security.JwtProvider;
import uz.digitalone.houzingapp.service.MailSerivce;
import uz.digitalone.houzingapp.service.RefreshTokenService;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MyUserService implements UserDetailsService {

    private final JavaMailSender javaMailSender;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final VerificationTokenRepository verificationTokenRepository;
    private final RefreshTokenService refreshTokenService;
    private final MailSerivce mailService;
    private final JwtProvider jwtProvider;
    public static User currentUser = new User();
    private NotificationEmail notificationEmail = null;

    private static final long EXPIRE_TOKEN_AFTER_MINUTES = 30;

    @Value("${server.port}")
    private Integer port;
    @Value("${server.host}")
    private String host; // = "158.51.99.245:"
    private String address;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(()
                -> new IllegalArgumentException("Email not found"));
//        return new org.springframework.security.core.userdetails.User(
//                user.getUsername(),
//                user.getPassword(),
//                user.getEnabled(),
//                true,
//                true,
//                true,
//                grantedAuthority("USER")
//        );
        return user;
    }

    private Collection<? extends GrantedAuthority> grantedAuthority(String user) {
        return Collections.singletonList(new SimpleGrantedAuthority(user));
    }

    public HttpEntity<?> verifyAccount(String token) {
        Optional<VerificationToken> optionalVerificationToken = verificationTokenRepository.findByToken(token);
        if(optionalVerificationToken.isPresent()){
            VerificationToken verificationToken = optionalVerificationToken.get();
            if(verificationToken.getExpirationData().isBefore(Instant.now())){
                log.error("Token expired: {}", verificationToken.getExpirationData());
                log.info("Sending the verification token again...");

//                throw new RuntimeException("Verification token expired");
                // TODO: 2022-04-19 Agar token expired bo`lsa qayta email jo'natish kerakmi?
                User user = verificationToken.getUser();
                token = generateTokenForVerification(user);
                address = host + ":" + port.toString();
                VerificationLink link = new VerificationLink(token, "link", address);
                notificationEmail = new NotificationEmail(
                        "Please, activate your account again!",
                        user.getEmail(),
                        link.getLink());
                    mailService.send(notificationEmail);
                    return ResponseEntity.status(401).body("Verification token expired! Please, check your email for new verification token, and try again.");

            }
            User user = verificationToken.getUser();
            user.setEnabled(true);
            userRepository.save(user);
            return ResponseEntity.ok("Verification Success");
        }else {
//            throw new VerifyTokenNotFound("Verification token not found");
            return ResponseEntity.status(404).body("Verification token not found");
        }
    }

    public HttpEntity<?> register(RegUserDto dto) {

        Boolean emailExists = null;
        if(dto != null && dto.getEmail() != null){
            emailExists = checkEmailExists(dto.getEmail());
        }
        if(emailExists!= null && emailExists)
            return ResponseEntity.status(422).body(new Response(false, "Email is invalid or already taken", dto.getEmail()));


        assert dto != null;
        User user = new User();
        user.setFirstname(dto.getFirstname());
        user.setLastname(dto.getLastname());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        Set<Role> roles = new HashSet<>();
            if (dto.getRoleIdSet() != null) {
                for (Long roleId : dto.getRoleIdSet()) {
                    Optional<Role> byId = roleRepository.findById(roleId);
                    byId.ifPresent(roles::add);
                }
                user.setRoles(roles);
            }
            user.setRoles(roles);

            user.setEnabled(false);
            userRepository.save(user);
            String token = generateTokenForVerification(user);
            address = host + ":" + port.toString();
            VerificationLink link = new VerificationLink(token, "link", address);
//            String link = "<a href=\"http://localhost:" +port+"/api/public/verification/" + token + "\", target=\"_blank\">Faollashtirish uchun havola</a>";
            notificationEmail = new NotificationEmail(
                    "Please, activate your account",
                    user.getEmail(),
                    link.getLink());

            mailService.send(notificationEmail);
            return ResponseEntity.status(HttpStatus.CREATED).body(token);
        }

    public User getCurrentUser() {
        Jwt principal = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByEmail(principal.getSubject()).orElseThrow(()
                -> new UsernameNotFoundException("Username Not Found"));
    }



    /**
     * Check if user exists by email
     * @param email user email
     * @return "true" if user exists, else return "false"
     */
    private Boolean checkEmailExists(String email) {
        User user = findByEmail(email);
        return user != null;
    }

    /**
     * Find user by email and return it
     * @param email user email address
     * @return user object
     */
    private User findByEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        return userOptional.orElse(null);
    }

    private String generateTokenForVerification(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationToken.setExpirationData(Instant.now().plus(1, ChronoUnit.HOURS));
        verificationTokenRepository.save(verificationToken);
        return token;
    }

    @Value("${jwt.expiration.time}")
    Long EXPIRATION_TIME;

    public HttpEntity<AuthenticationResponse> login(LoginRequest dto) {
        Authentication authenticate =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String generateToken = jwtProvider.generateToken((User) authenticate.getPrincipal());
        // TODO: 2/25/22 check if user login details match, if not handle it.

        AuthenticationResponse response = AuthenticationResponse.builder()
                .authenticationToken(generateToken)
                .refreshToken(refreshTokenService.generateRefreshToken().getRefreshToken())
                .username(dto.getEmail())
                .expirationData(Instant.now().plusMillis(EXPIRATION_TIME))
                .build();
        return ResponseEntity.ok(response);
    }

    /**
     * Check if user details match database records, if not return appropriate response
     * @param dto
     * @return
     */
    public Response findUser(LoginRequest dto) {
        User user = findByEmail(dto.getEmail());
        if(user != null){
            if (!passwordEncoder.matches(dto.getPassword(), user.getPassword()))
                return new Response(false, "Password didn't match with user's password. Please, check your input: " + dto.getPassword(), HttpStatus.valueOf(200));
            else
                return new Response(true, "OK");
        }
        else
            return new Response(false, "User not found with email: " + dto.getEmail(), HttpStatus.NOT_FOUND);
    }

    /**
     * Return error code and default message set for the validation annotation
     * @param errors errors
     * @return map of error code as key, default message as value, e.g. :['Email': 'invalid email address']
     */
    public Map<String, String> getErrors(Errors errors) {
        Map<String, String> errorList = new HashMap<>();
        for (ObjectError error : errors.getAllErrors()) {

            String code = error.getCode();
            if(error.getCodes()!= null && error.getCodes().length > 0){
                code = error.getCodes()[0];
            }
            errorList.put(code, error.getDefaultMessage());
        }
        return errorList;
    }

    public HttpEntity<AuthenticationResponse> refreshToken(RefreshTokenRequest dto) {
        refreshTokenService.validationToken(dto.getRefreshToken());
        String authenticationToken = jwtProvider.generateTokenWithUsername(dto.getUsername());
        AuthenticationResponse response = AuthenticationResponse.builder()
                .authenticationToken(authenticationToken)
                .refreshToken(dto.getRefreshToken())
                .username(dto.getUsername())
                .expirationData(Instant.now().plusMillis(EXPIRATION_TIME))
                .build();
        return ResponseEntity.ok(response);

    }

    public ResponseEntity<String> logout(RefreshTokenRequest request) {
        refreshTokenService.refreshTokenDelete(request);
        return ResponseEntity.status(200).body("Successfully logged out");
    }

    public HttpEntity<?> forgotPassword(String email) {
        Response response;
        User user = userRepository.findByEmail(email).orElseThrow(()
                -> new IllegalArgumentException("Email not found"));

        user.setToken(generateToken());
        user.setTokenCreationDate(LocalDateTime.now());

        user = userRepository.save(user);

        response = new Response(true, user.getToken());

        return ResponseEntity.ok(response);
    }

    public HttpEntity<?> resetPassword(String token, String password) {
        Response response;
        User user = userRepository.findByToken(token).orElseThrow(()
                -> new IllegalArgumentException("Email not found"));

        LocalDateTime tokenCreationDate = user.getTokenCreationDate();

        if (isTokenExpired(tokenCreationDate)) {
            return ResponseEntity.ok(new Response(false, "Token Expired"));
        }
        user.setPassword(passwordEncoder.encode(password));
        user.setToken(null);
        user.setTokenCreationDate(null);
        userRepository.save(user);

        response = new Response(true, "Your password successfully updated");

        return ResponseEntity.ok(response);
    }


    private String generateToken() {
        return String.valueOf(UUID.randomUUID()) +
                UUID.randomUUID();
    }

    private boolean isTokenExpired(final LocalDateTime tokenCreationDate) {

        LocalDateTime now = LocalDateTime.now();
        Duration diff = Duration.between(tokenCreationDate, now);

        return diff.toMinutes() >= EXPIRE_TOKEN_AFTER_MINUTES;
    }
}
