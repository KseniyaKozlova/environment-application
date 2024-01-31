package repositories.tare;

import entities.Tare;
import repositories.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * some additional methods will be here
 */
public interface TareRepository extends Repository<Tare> {

    List<Tare> getTaresByUserId(final UUID id);

    Optional<Tare> getById(UUID id);
}
