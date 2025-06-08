package yeri.roguelike.Entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "guides")
public class Guides {

    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false)
    private Users author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "weapon_id", referencedColumnName = "id", nullable = false)
    private Weapons weapon;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "created_at", columnDefinition = "timestamptz")
    private Timestamp createdAt;

    @Column(name = "updated_at", columnDefinition = "timestamptz")
    private Timestamp updatedAt;

    @OneToMany(mappedBy = "guide", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GuideBoons> guideBoons = new ArrayList<>();

    public Guides() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Users getAuthor() {
        return author;
    }

    public void setAuthor(Users author) {
        this.author = author;
    }

    public Weapons getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapons weapon) {
        this.weapon = weapon;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<GuideBoons> getGuideBoons() {
        return guideBoons;
    }

    public void setGuideBoons(List<GuideBoons> guideBoons) {
        this.guideBoons = guideBoons;
    }

    // Getters and setters
}
