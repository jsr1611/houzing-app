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
import uz.digitalone.houzingapp.dto.request.LoginDto;
import uz.digitalone.houzingapp.dto.request.RegUserDto;
import uz.digitalone.houzingapp.dto.response.Response;
import uz.digitalone.houzingapp.entity.Role;
import uz.digitalone.houzingapp.entity.User;
import uz.digitalone.houzingapp.repository.RoleRepository;
import uz.digitalone.houzingapp.repository.UserRepository;
import uz.digitalone.houzingapp.security.JwtProvider;

import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class MyUserService implements UserDetailsService {

    private final JavaMailSender javaMailSender;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found."));
        return user;
    }


    public void sendVerificationEmail(){
        Integer code = generateCode();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("jimmy.sweetk@gmail.com");
        String toEmail = "gm.khamza@gmail.com";
        message.setTo(toEmail);
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
        Response response = new Response(true, "Successfully registered",savedUser.getEmail());
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
        String generatedToken = jwtProvider.generateToken(principal.getEmail(), principal.getRoles());
        Response response = new Response(true, "Token", generatedToken);

        // TODO: 2/25/22 check if user login details match, if not handle it.
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
