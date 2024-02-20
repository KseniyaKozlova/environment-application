package by.itacademy.environment.services.tare;

import by.itacademy.environment.dto.request.CreateTareRequestDto;
import by.itacademy.environment.dto.response.BonusesCountResponseDto;
import by.itacademy.environment.dto.response.TareResponseDto;
import by.itacademy.environment.entities.Tare;
import by.itacademy.environment.entities.User;
import by.itacademy.environment.exceptions.notFound.TareNotFoundException;
import by.itacademy.environment.feignClients.TareBonusesCountClient;
import by.itacademy.environment.mappers.TareMapper;
import by.itacademy.environment.repositories.tare.TareRepository;
import by.itacademy.environment.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class TareServiceImpl implements TareService {

    private final TareBonusesCountClient bonusesCountClient;
    private final UserService userService;
    private final TareRepository tareRepository;
    private final TareMapper tareMapper;

    @Override
    @Transactional
    public TareResponseDto saveTare(final CreateTareRequestDto tareRequestDto) {
        final Tare tare = tareMapper.mapToTare(tareRequestDto);

        final BonusesCountResponseDto bonusesCountResponse = bonusesCountClient.getBonusesCount(tare.getLitresVolume(),
                tare.getTareCategory());
        final Integer bonusesCount = bonusesCountResponse.getAccountingBonusesCount();
        tare.setAccountingBonusesCount(bonusesCount);

        if (tareRequestDto.getUserId() != null) {
            final User user = userService.getUserById(tareRequestDto.getUserId());
            user.addTare(tare);
        }
        final Tare savedTare = tareRepository.save(tare);
        return tareMapper.mapToTareResponse(savedTare);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TareResponseDto> getTares() {
        return tareRepository.findAll().stream()
                .map(tareMapper::mapToTareResponse)
                .collect(toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TareResponseDto> getTaresByUserId(final UUID id) {
        return tareRepository.findTaresByUserId(id).stream()
                .map(tareMapper::mapToTareResponse)
                .collect(toList());
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
}
