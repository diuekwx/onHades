package yeri.roguelike.Service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import yeri.roguelike.Entity.Users;
import yeri.roguelike.Repository.UserRepo;

import java.util.Optional;

@Service
public class UserService {

    private UserRepo userRepo;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder){
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public void createUser(Users user){
        user.setRole("ROLE_USER");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }

    public boolean existingUsername(String username){
        return userRepo.findByUsername(username).isPresent();
    }

//    public String encryptPassword(String password){
//        String hashed = passwordEncoder.encode(password);
////        user.setPassword(password);
////        userRepo.save(user);
//    }

    public boolean verifyAccount(String username, String raw){
        Optional<Users> user = userRepo.findByUsername(username);
        if (user.isPresent()){
            Users actualUser = user.get();
            return passwordEncoder.matches(raw, actualUser.getPassword());
        }
        return false;
    }
}
