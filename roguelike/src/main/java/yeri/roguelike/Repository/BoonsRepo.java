package yeri.roguelike.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yeri.roguelike.Entity.Boons;

import java.util.UUID;

@Repository
public interface BoonsRepo extends JpaRepository<Boons, String> {

    Boons findByUUID(UUID id);
}
