package services.tare;

import entities.Tare;
import repositories.tare.TareRepository;
import repositories.tare.TareRepositoryImpl;
import services.exceptions.TareServiceException;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class TareServiceImpl implements TareService {

    private static TareService tareService;
    private final TareRepository tareRepository = TareRepositoryImpl.getInstance();

    private TareServiceImpl() {
    }

    public static TareService getInstance() {
        return Objects.requireNonNullElseGet(tareService, () -> tareService = new TareServiceImpl());
    }

    @Override
    public Tare create(final Tare tare) {
        return tareRepository.create(tare);
    }

    @Override
    public List<Tare> read() {
        return tareRepository.read();
    }

    @Override
    public Tare update(final UUID id, final Tare tare) {
        return tareRepository.update(id, tare).orElseThrow(() -> new TareServiceException("This tare can't be updated"))
                .setTareCategory(tare.getTareCategory())
                .setLitresVolume(tare.getLitresVolume())
                .setBonusesToAccounting(tare.getBonusesToAccounting());
    }

    @Override
    public boolean delete(final UUID id) {
        return tareRepository.delete(id);
    }
}
