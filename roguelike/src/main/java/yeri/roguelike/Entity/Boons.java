package yeri.roguelike.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;
import java.util.UUID;

@Entity
public class Boons {

    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(columnDefinition = "TEXT")
    private String name;

    @Column(columnDefinition = "TEXT")
    private String god;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "boon")
    private List<GuideBoons> guideBoons;
}
