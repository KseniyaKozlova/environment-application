package by.itacademy.environment.repositories.tare;

import by.itacademy.environment.entities.Tare;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TareRepository extends JpaRepository<Tare, UUID> {

    /**
     * Get all tares from DB of determined user
     *
     * @param id user id, whose tares are need
     * @return list of tares
     */
    List<Tare> findTaresByUserId(UUID id);
}
