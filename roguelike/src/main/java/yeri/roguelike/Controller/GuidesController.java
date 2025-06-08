package yeri.roguelike.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yeri.roguelike.DTO.GuideDTO;

import yeri.roguelike.Entity.Guides;
import yeri.roguelike.Service.GuideBoonService;
import yeri.roguelike.Service.GuidesService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/guides")
public class GuidesController {

    private final GuidesService guidesService;
    private final GuideBoonService guideBoonService;

    public GuidesController(GuidesService guidesService, GuideBoonService guideBoonService){
        this.guidesService = guidesService;
        this.guideBoonService = guideBoonService;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createGuide(@RequestBody GuideDTO guideDTO){
        String title = guideDTO.getTitle();
        String content = guideDTO.getContent();
        UUID user = guideDTO.getAuthor();
        UUID weapon = guideDTO.getWeapon();
        List<UUID> boons = guideDTO.getBoonIds();

        Guides guide = guidesService.newGuide(title, user, weapon, content);
        guideBoonService.saveBoons(guide, boons);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/view")
    public ResponseEntity<Object> viewGuides(){
        return ResponseEntity.ok().build();
    }

}
