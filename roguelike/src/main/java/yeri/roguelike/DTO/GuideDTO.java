package yeri.roguelike.DTO;


import jakarta.validation.constraints.NotNull;


import java.util.List;
import java.util.UUID;

public class GuideDTO {

    private String title;

    private String content;

    // passing these from frontend, will be done by get endpoints
    @NotNull
    private UUID weapon;

    @NotNull
    private UUID author;

    private List<UUID> boonIds;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public @NotNull UUID getWeapon() {
        return weapon;
    }

    public void setWeapon(@NotNull UUID weapon) {
        this.weapon = weapon;
    }

    public @NotNull UUID getAuthor() {
        return author;
    }

    public void setAuthor(@NotNull UUID author) {
        this.author = author;
    }

    public List<UUID> getBoonIds() {
        return boonIds;
    }

    public void setBoonIds(List<UUID> boonIds) {
        this.boonIds = boonIds;
    }
}
