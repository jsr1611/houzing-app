package uz.digitalone.houzingapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import uz.digitalone.houzingapp.dto.request.LoginDto;
import uz.digitalone.houzingapp.dto.request.RegUserDto;
import uz.digitalone.houzingapp.dto.response.Response;
import uz.digitalone.houzingapp.entity.Role;
import uz.digitalone.houzingapp.entity.User;
import uz.digitalone.houzingapp.mapper.UserMapper;
import uz.digitalone.houzingapp.repository.RoleRepository;
import uz.digitalone.houzingapp.repository.UserRepository;
import uz.digitalone.houzingapp.security.JwtProvider;

import java.util.*;

@Service
@RequiredArgsConstructor
public class MyUserService implements UserDetailsService {

    private final JavaMailSender javaMailSender;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    public static User currentUser;
    private final UserMapper userMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found."));
        return user;
    }


    public void sendVerificationEmail(String sender, String receiver){
        Integer code = generateCode();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(receiver);
        message.setSubject("Confirmation Code");
        message.setText(code.toString());
        javaMailSender.send(message);

    }

    private Integer generateCode() {
        Random random = new Random();
        return random.nextInt(999999);
    }

    public HttpEntity<?> register(RegUserDto dto) {

        Boolean emailExists = null;
        if(dto != null && dto.getEmail() != null){
            emailExists = emailExists(dto.getEmail());
        }
        if(emailExists!= null && emailExists)
            return ResponseEntity.status(422).body(new Response(false, "Email is invalid or already taken", dto.getEmail()));

        User user = new User();
        user.setFirstname(dto.getFirstname());
        user.setLastname(dto.getLastname());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        Set<Role> roles = new HashSet<>();
        for(Long roleId : dto.getRoleIdSet()){
            Optional<Role> byId = roleRepository.findById(roleId);
            byId.ifPresent(roles::add);
        }
        user.setRoles(roles);
        User savedUser = userRepository.save(user);
        sendVerificationEmail("no-reply@uz-digitalone.com", user.getEmail());
        Response response = new Response(true, "Successfully registered. Email verification code has been sent to your email address.", userMapper.fromEntity(user));
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    /**
     * Check if user exists by email
     * @param email user email
     * @return "true" if user exists, else return "false"
     */
    private Boolean emailExists(String email) {
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
        if(userOptional.isPresent()){
            return userOptional.get();
        }
        return null;
    }

    public HttpEntity<?> login(LoginDto dto) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));
        User principal = (User) authenticate.getPrincipal();
        currentUser = principal;
        String generatedToken = jwtProvider.generateToken(principal.getEmail(), principal.getRoles());
        Response response = new Response(true, "Token", generatedToken);

        // TODO: 2/25/22 check if user login details match, if not handle it.
        return ResponseEntity.status(response.getStatus()).body(response);
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
}
