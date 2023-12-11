package services;

import java.util.List;
import java.util.UUID;

public interface Service<O> {

    O create(O object);

    List<O> read();

    O update(UUID id, O object);

    boolean delete(UUID id);
}
