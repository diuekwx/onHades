package yeri.roguelike.Entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name="guide_boons")
public class GuideBoons {

//    @Id
//    @GeneratedValue
//    @Column(name = "id", columnDefinition = "uuid")
//    private UUID id;

    @ManyToOne
    @JoinColumn(name = "guide_id", nullable = false)
    private Guides guide;

    @ManyToOne
    @JoinColumn(name = "boon_id", nullable = false)
    private Boons boon;


    public Guides getGuide() {
        return guide;
    }

    public void setGuide(Guides guide) {
        this.guide = guide;
    }

    public Boons getBoon() {
        return boon;
    }

    public void setBoon(Boons boon) {
        this.boon = boon;
    }
}
