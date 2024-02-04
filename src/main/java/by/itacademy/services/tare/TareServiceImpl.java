package by.itacademy.services.tare;

import by.itacademy.dto.request.CreateTareRequestDto;
import by.itacademy.dto.request.UpdateTareRequestDto;
import by.itacademy.dto.response.TareResponseDto;
import by.itacademy.entities.Tare;
import lombok.RequiredArgsConstructor;
import by.itacademy.mappers.TareMapper;
import org.springframework.stereotype.Service;
import by.itacademy.repositories.tare.TareRepository;
import by.itacademy.services.exceptions.TareServiceException;

import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class TareServiceImpl implements TareService {

    private final TareRepository tareRepository;
    private final TareMapper tareMapper;

    @Override
    public TareResponseDto saveTare(final CreateTareRequestDto tareRequestDto) {
        final Tare tare = tareMapper.mapToTare(tareRequestDto);
        final Tare savedTare = tareRepository.save(tare);
        return tareMapper.mapToTareResponse(savedTare);
    }

    @Override
    public List<TareResponseDto> readTares() {
        return tareRepository.findAll().stream()
                .map(tareMapper::mapToTareResponse)
                .collect(toList());
    }

//    @Override
//    public List<Tare> getTaresByUserId(final UUID id) {
//        return tareRepository.findTaresByUserId(id);
//    }

    @Override
    public TareResponseDto updateTare(final UUID id, final UpdateTareRequestDto tareRequestDto) {
        final Tare tareToUpdate = tareRepository.findById(id)
                .orElseThrow(() -> new TareServiceException("You can't update this coupon"));
        tareMapper.updateTare(tareRequestDto, tareToUpdate);
        final Tare updatedTare = tareRepository.save(tareToUpdate);
        return tareMapper.mapToTareResponse(updatedTare);
    }

    @Override
    public void delete(final UUID id) {
        tareRepository.deleteById(id);
    }

    @Override
    public TareResponseDto getById(final UUID id) {
        final Tare tare = tareRepository.findById(id)
                .orElseThrow(() -> new TareServiceException("This coupon doesn't exist"));
        return tareMapper.mapToTareResponse(tare);
    }

    @Override
    public Tare getTareById(final UUID id) {
        return tareRepository.findById(id)
                .orElseThrow(() -> new TareServiceException("This coupon doesn't exist"));
    }
}
