package yeri.roguelike.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yeri.roguelike.Entity.Users;
import yeri.roguelike.Repository.UserRepo;
import yeri.roguelike.Service.CustomUserDetailsService;
import yeri.roguelike.Service.JwtService;
import yeri.roguelike.Service.UserService;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("api/auth/")
public class UserController {

    private final UserService userService;
    private final CustomUserDetailsService userDetailsService;
    private final JwtService jwtService;

    public UserController(UserService userService, CustomUserDetailsService userDetailsService, JwtService jwtService){
        this.userService = userService;
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
    }


    @PostMapping("/create")
    public ResponseEntity<Object> createUser(@RequestBody Map<String, String> info){
        String userName = info.get("username");
        if (userService.existingUsername(userName)){
            Map<String, String> errorBody = new HashMap<>();
            errorBody.put("error", "UsernameAlreadyTaken");
            errorBody.put("message", "The username '" + userName + "' is already in use. Please choose a different one.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorBody);
        }
        String password = info.get("password");
        Instant now = Instant.now();
        Timestamp createdAt =  Timestamp.from(now);
        Users user = new Users(createdAt, userName, password);
        userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody Map<String, String> info){
        String username = info.get("username");
        String password = info.get("password");
        if (userService.verifyAccount(username, password)){
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            String jwtToken = jwtService.generateToken(userDetails);
            Map<String, String> response = new HashMap<>();
            response.put("token", jwtToken);
            return ResponseEntity.ok(response);
        }
        else{
            Map<String, String> errorBody = new HashMap<>();
            errorBody.put("error", "WrongCredientials");
            errorBody.put("message", " The username or password is incorrect");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorBody);
        }

    }
}
