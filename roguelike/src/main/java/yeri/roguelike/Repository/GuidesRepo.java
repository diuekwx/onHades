package yeri.roguelike.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yeri.roguelike.Entity.Guides;
import yeri.roguelike.Entity.Users;

import java.util.List;

@Repository
public interface GuidesRepo extends JpaRepository<Guides, Long> {
    List<Guides> findAllByAuthor(Users author);
    Guides findByAuthor(Users author);

}
