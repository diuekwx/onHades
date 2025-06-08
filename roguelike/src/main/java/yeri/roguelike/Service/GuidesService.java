package yeri.roguelike.Service;

import org.springframework.stereotype.Service;
import yeri.roguelike.Entity.Guides;
import yeri.roguelike.Entity.Users;
import yeri.roguelike.Entity.Weapons;
import yeri.roguelike.Repository.GuidesRepo;
import yeri.roguelike.Repository.UserRepo;
import yeri.roguelike.Repository.WeaponsRepo;

import java.util.UUID;

@Service
public class GuidesService {

    private final GuidesRepo guidesRepo;
    private final UserRepo userRepo;
    private final WeaponsRepo weaponsRepo;

    public GuidesService(GuidesRepo guidesRepo, UserRepo userRepo, WeaponsRepo weaponsRepo){
        this.guidesRepo = guidesRepo;
        this.userRepo = userRepo;
        this.weaponsRepo = weaponsRepo;
    }

    public Guides newGuide(String title, UUID userId, UUID weaponId, String content){
        Guides guide = new Guides();

        Users user = userRepo.findById(userId).get();
        Weapons weapon = weaponsRepo.findById(weaponId).get();

        guide.setId(UUID.randomUUID());
        guide.setAuthor(user);
        guide.setTitle(title);
        guide.setWeapon(weapon);
        guide.setContent(content);
        guidesRepo.save(guide);
        return guide;
    }


}
