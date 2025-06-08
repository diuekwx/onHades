package yeri.roguelike.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yeri.roguelike.Entity.Users;
import yeri.roguelike.Entity.Weapons;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface WeaponsRepo extends JpaRepository<Weapons, Long> {
    Optional<Weapons> findByName(String name);
    Optional<Weapons> findById(UUID id);
}
