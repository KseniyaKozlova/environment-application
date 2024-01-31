package services.tare;

import entities.Tare;
import services.Service;

import java.util.UUID;

/**
 * some additional methods will be here
 */
public interface TareService extends Service<Tare> {

    Tare getById(UUID id);
}
