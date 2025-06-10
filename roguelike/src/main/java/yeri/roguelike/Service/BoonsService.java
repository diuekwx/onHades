package yeri.roguelike.Service;

import org.springframework.stereotype.Service;
import yeri.roguelike.Entity.Boons;
import yeri.roguelike.Repository.BoonsRepo;

import java.util.List;
import java.util.UUID;

@Service
public class BoonsService {
    private final BoonsRepo boonsRepo;

    public BoonsService(BoonsRepo boonsRepo){
        this.boonsRepo = boonsRepo;
    }

    public List<Boons> getAllBoons(){
        return boonsRepo.findAll();
    }

    public Boons findById(UUID uuid){
        return  boonsRepo.findByUUID(uuid);
    }

}
