package by.itacademy.services.tare;

import by.itacademy.dto.request.CreateTareRequestDto;
import by.itacademy.dto.request.UpdateTareRequestDto;
import by.itacademy.entities.Tare;
import lombok.RequiredArgsConstructor;
import by.itacademy.mappers.TareMapper;
import org.springframework.stereotype.Service;
import by.itacademy.repositories.tare.TareRepository;
import by.itacademy.services.exceptions.TareServiceException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TareServiceImpl implements TareService {

    private final TareRepository tareRepository;
    private final TareMapper tareMapper;

    @Override
    public Tare saveTare(final CreateTareRequestDto tareRequestDto) {
        final Tare tare = tareMapper.mapToTare(tareRequestDto);
        return tareRepository.save(tare);
    }

    @Override
    public List<Tare> readTares() {
        return tareRepository.findAll();
    }

//    @Override
//    public List<Tare> getTaresByUserId(final UUID id) {
//        return tareRepository.findTaresByUserId(id);
//    }

    @Override
    public Tare updateTare(final UUID id, final UpdateTareRequestDto tareRequestDto) {
        final Tare tareToUpdate = getTareById(id);
        tareMapper.updateTare(tareRequestDto, tareToUpdate);
        return tareRepository.save(tareToUpdate);
    }

    @Override
    public void delete(final UUID id) {
        tareRepository.deleteById(id);
    }

    @Override
    public Tare getTareById(final UUID id) {
        return tareRepository.findById(id).orElseThrow(() -> new TareServiceException("This coupon doesn't exist"));
    }
}
