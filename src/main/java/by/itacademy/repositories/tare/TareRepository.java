package by.itacademy.repositories.tare;

import by.itacademy.entities.Tare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TareRepository extends JpaRepository<Tare, UUID> {

    List<Tare> findTaresByUsersId(UUID id);
}
