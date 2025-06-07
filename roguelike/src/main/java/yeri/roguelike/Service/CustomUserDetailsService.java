package yeri.roguelike.Service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import yeri.roguelike.Details.CustomUserDetails;
import yeri.roguelike.Entity.Users;
import yeri.roguelike.Repository.UserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepo userRepo;

    public CustomUserDetailsService(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    @Override
    public CustomUserDetails loadUserByUsername (String name) throws UsernameNotFoundException{
        Users user = userRepo.findByUsername(name).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new CustomUserDetails(user);
    }
}
