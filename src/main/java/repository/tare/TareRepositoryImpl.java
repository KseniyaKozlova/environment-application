package repository.tare;

import entity.Tare;
import enums.TareCategory;

import java.util.*;

public class TareRepositoryImpl implements TareRepository {

    private static TareRepository tareRepository;
    private final List<Tare> tares;

    {
        tares = new ArrayList<>();
        tares.add(new Tare(UUID.fromString("55b77255-0771-4578-8226-3de0d4da69ec"), TareCategory.GLASS_BOTTLE, 0.5, 30));
        tares.add(new Tare(UUID.randomUUID(), TareCategory.PLASTIC_BOTTLE, 1.0, 30));
    }

    private TareRepositoryImpl() {
    }

    public static TareRepository getInstance() {
        return Objects.requireNonNullElseGet(tareRepository, () -> tareRepository = new TareRepositoryImpl());
    }

    @Override
    public Tare create(final Tare tare) {
        tare.setId(UUID.randomUUID());
        tares.add(tare);
        return tare;
    }

    @Override
    public List<Tare> read() {
        return tares;
    }

    @Override
    public Optional<Tare> update(final UUID id, final Tare tare) {
        Optional<Tare> tareOptional = getById(id);
        tareOptional.ifPresent(tareToUpdate -> tareToUpdate
                .setTareCategory(tare.getTareCategory())
                .setLitresVolume(tare.getLitresVolume())
                .setBonusesToAccounting(tare.getBonusesToAccounting()));

        return tareOptional;
    }

    @Override
    public boolean delete(final UUID id) {
        Optional<Tare> userOptional = getById(id);
        return userOptional.map(tares::remove)
                .orElse(false);
    }

    private Optional<Tare> getById(UUID id) {
        return tares.stream()
                .filter(tare -> tare.getId().equals(id))
                .findFirst();
    }
}
