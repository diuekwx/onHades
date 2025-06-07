package yeri.roguelike.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yeri.roguelike.Entity.Guides;

import java.util.List;

@Repository
public interface GuidesRepo extends JpaRepository<Guides, Long> {
    List<String> findAllByAuthor(String author);
    String findByAuthor(String author);

}
