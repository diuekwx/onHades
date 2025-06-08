package yeri.roguelike.Service;

import org.springframework.stereotype.Service;
import yeri.roguelike.Entity.Boons;
import yeri.roguelike.Entity.GuideBoons;
import yeri.roguelike.Entity.Guides;
import yeri.roguelike.Repository.BoonsRepo;
import yeri.roguelike.Repository.GuideBoonsRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class GuideBoonService {

    private final BoonsRepo boonsRepo;
    private final GuideBoonsRepo guideBoonsRepo;

    public GuideBoonService(BoonsRepo boonsRepo, GuideBoonsRepo guideBoonsRepo){
        this.boonsRepo = boonsRepo;
        this.guideBoonsRepo = guideBoonsRepo;
    }

    public void saveBoons(Guides guide, List<UUID> listOfBoons){
        List<GuideBoons> guideBoon = new ArrayList<>();

        for (UUID boonId: listOfBoons){
            Boons boon = boonsRepo.findByUUID(boonId);
            GuideBoons gb = new GuideBoons();

            gb.setGuide(guide);
            gb.setBoon(boon);

            guideBoon.add(gb);
        }

        guideBoonsRepo.saveAll(guideBoon);
    }
}
