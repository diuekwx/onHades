package yeri.roguelike.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yeri.roguelike.Entity.GuideBoons;

@Repository
public interface GuideBoonsRepo extends JpaRepository<GuideBoons, String> {
}
