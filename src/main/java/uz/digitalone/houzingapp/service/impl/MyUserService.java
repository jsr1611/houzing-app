package uz.digitalone.houzingapp.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import java.time.Instant;
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

    public void verification(String token) {
        Optional<VerificationToken> refreshToken = verificationTokenRepository.findByToken(token);
        if (refreshToken.isPresent()){
            VerificationToken refreshToken1 = refreshToken.get();
            if (refreshToken1.getExpirationData().isBefore(Instant.now())){
                log.error("Token expiration Data {}", refreshToken1.getExpirationData());
                throw new RuntimeException("Token expiration");
            }
            User user = refreshToken1.getUser();
            user.setEnabled(true);
            userRepository.save(user);
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

            mailService.send(new NotificationEmail("Accountingizni activatsia qiling",
                    user.getEmail(), "<h1> Ushbu link orqali </h1>" +
                    "http://loaclhost:9090/api/v1/auth/verification/" + token));
            return ResponseEntity.status(HttpStatus.CREATED).body(token);
        }

    public User getCurrentUser() {
        Jwt principal = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByEmail(principal.getSubject()).orElseThrow(()
                -> new UsernameNotFoundException("username Not found"));
    }

    public void verifyAccount(String token) {
        Optional<VerificationToken> optionalVerificationToken = verificationTokenRepository.findByToken(token);
        if(optionalVerificationToken.isPresent()){
            VerificationToken verificationToken = optionalVerificationToken.get();
            if(verificationToken.getExpirationData().isBefore(Instant.now())){
                throw new RuntimeException("Verification token expired");
            }
            User user = verificationToken.getUser();
            user.setEnabled(true);
            userRepository.save(user);
        }else {
            throw new VerifyTokenNotFound("Verify token not found");
        }
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
}
