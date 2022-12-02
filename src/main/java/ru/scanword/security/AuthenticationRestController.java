package ru.scanword.security;

import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import net.bytebuddy.utility.RandomString;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.scanword.domain.User;
import ru.scanword.domain.enums.Role;
import ru.scanword.dto.UserDTO;
import ru.scanword.exceptions.InvalidAuthenticationInformationException;
import ru.scanword.exceptions.ResourceNotFoundException;
import ru.scanword.mapper.UserMapper;
import ru.scanword.repository.UserRepository;
import ru.scanword.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationRestController {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private JwtTokenProvider jwtTokenProvider;
    private final UserServiceImpl userService;

    private final List<UserDTO> guests = Collections.synchronizedList(new ArrayList<>());
    private static final Pattern GUEST_REGEX = Pattern.compile("(guest)\\w{0,}");

    public AuthenticationRestController(AuthenticationManager authenticationManager, UserRepository userRepository, JwtTokenProvider jwtTokenProvider, UserServiceImpl userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.passwordEncoder = new BCryptPasswordEncoder(12);
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        guests.addAll(UserMapper.USER_MAPPER.allToDTO(userRepository.findAll()).stream().filter(user -> GUEST_REGEX.matcher(user.getName()).matches()).collect(Collectors.toList()));
    }

    @PostMapping("/register")
    public void register(@RequestBody AuthenticationRequestDTO requestDTO) {
        if (GUEST_REGEX.matcher(requestDTO.getUsername()).matches()) {
            throw new ResourceNotFoundException("User with name = " + requestDTO.getUsername() + " already exist", "");
        }
        if (!userRepository.findByName(requestDTO.getUsername()).isPresent()) {
            UserDTO userDTO = new UserDTO();
            userDTO.setName(requestDTO.getUsername());
            userDTO.setRole(Role.USER);
            userDTO.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
            userService.createUser(userDTO);
        } else {
            throw new ResourceNotFoundException("User with name = " + requestDTO.getUsername() + " already exist", "");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequestDTO requestDTO) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestDTO.getUsername(), requestDTO.getPassword()));
            User user = userRepository.findByName(requestDTO.getUsername()).get();
            String token = jwtTokenProvider.createToken(requestDTO.getUsername(), user.getPassword());
            Map<Object, Object> response = new HashMap<>();
            response.put("username", requestDTO.getUsername());
            response.put("role", user.getRole());
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new InvalidAuthenticationInformationException("Invalid username/password combination", "");
        }
    }

    @PostMapping("/loginGuest")
    public ResponseEntity<?> authenticateAsGuest() {
        String username;

        do {
            String login = "guest" + RandomString.make(3);
            if (guests.stream().noneMatch(guest -> guest.getName().equals(login))) {
                username = login;
                break;
            }
        } while (true);


        UserDTO guestDTO = new UserDTO();
        guestDTO.setName(username);
        guestDTO.setRole(Role.GUEST);
        guestDTO.setPassword(passwordEncoder.encode("guest"));
        userService.createUser(guestDTO);

        guests.add(guestDTO);

        AuthenticationRequestDTO requestDTO = new AuthenticationRequestDTO();
        requestDTO.setUsername(guestDTO.getName());
        requestDTO.setPassword("guest");
        return authenticate(requestDTO);
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        UserDetails securityUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByName(securityUser.getUsername()).get();
        if (user.getRole() == Role.GUEST) {
            UserDTO userDTO = UserMapper.USER_MAPPER.toDTO(user);
            guests.remove(userDTO);
            userService.deleteGuest(userDTO);
        }
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }
}
