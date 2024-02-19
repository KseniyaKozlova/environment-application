package by.itacademy.services.tare;

import by.itacademy.dto.request.CreateTareRequestDto;
import by.itacademy.dto.request.UpdateTareRequestDto;
import by.itacademy.dto.response.TareResponseDto;
import by.itacademy.entities.Tare;
import by.itacademy.exceptions.notFound.TareNotFoundException;
import by.itacademy.mappers.TareMapper;
import by.itacademy.repositories.tare.TareRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class TareServiceImpl implements TareService {

    private final TareRepository tareRepository;
    private final TareMapper tareMapper;

    @Override
    @Transactional
    public TareResponseDto saveTare(final CreateTareRequestDto tareRequestDto) {
        final Tare tare = tareMapper.mapToTare(tareRequestDto);
        final Tare savedTare = tareRepository.save(tare);
        return tareMapper.mapToTareResponse(savedTare);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TareResponseDto> readTares() {
        return tareRepository.findAll().stream()
                .map(tareMapper::mapToTareResponse)
                .collect(toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TareResponseDto> getTaresByUserId(final UUID id) {
        return tareRepository.findTaresByUsersId(id).stream()
                .map(tareMapper::mapToTareResponse)
                .collect(toList());
    }

    @Override
    @Transactional
    public TareResponseDto updateTare(final UUID id, final UpdateTareRequestDto tareRequestDto) {
        final Tare tareToUpdate = tareRepository.findById(id)
                .orElseThrow(() -> new TareNotFoundException(id.toString()));
        tareMapper.updateTare(tareRequestDto, tareToUpdate);
        final Tare updatedTare = tareRepository.save(tareToUpdate);
        return tareMapper.mapToTareResponse(updatedTare);
    }

    @Override
    @Transactional
    public void delete(final UUID id) {
        tareRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public TareResponseDto getTareResponseById(final UUID id) {
        final Tare tare = tareRepository.findById(id)
                .orElseThrow(() -> new TareNotFoundException(id.toString()));
        return tareMapper.mapToTareResponse(tare);
    }

    @Override
    public Tare getTareById(final UUID id) {
        return tareRepository.findById(id)
                .orElseThrow(() -> new TareNotFoundException(id.toString()));
    }
}
