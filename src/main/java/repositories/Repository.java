package repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface Repository<O> {

    O create(O object);

    List<O> read();

    Optional<O> update(UUID id, O object);

    boolean delete(UUID id);
}
