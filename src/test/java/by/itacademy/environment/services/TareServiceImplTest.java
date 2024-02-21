package by.itacademy.environment.services;

import by.itacademy.environment.dto.request.CreateTareRequestDto;
import by.itacademy.environment.dto.response.BonusesCountResponseDto;
import by.itacademy.environment.dto.response.TareResponseDto;
import by.itacademy.environment.entities.Tare;
import by.itacademy.environment.entities.User;
import by.itacademy.environment.enums.Role;
import by.itacademy.environment.enums.TareCategory;
import by.itacademy.environment.feignClients.TareBonusesCountClient;
import by.itacademy.environment.mappers.TareMapper;
import by.itacademy.environment.repositories.tare.TareRepository;
import by.itacademy.environment.services.tare.TareService;
import by.itacademy.environment.services.tare.TareServiceImpl;
import by.itacademy.environment.services.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TareServiceImplTest {

    private static final UUID TARE_ID = UUID.fromString("2b4bb68d-1f3e-4e4e-8d5c-8d56abe2043a");
    private static final UUID USER_ID = UUID.fromString("4b4bb68a-1f3e-4e4e-8d5c-8d56abe2043a");

    private static final TareResponseDto TARE_RESPONSE_DTO_1 = TareResponseDto.builder()
            .id(TARE_ID)
            .tareCategory(TareCategory.PLASTIC_BOTTLE)
            .litresVolume(BigDecimal.valueOf(0.5))
            .accountingBonusesCount(10)
            .build();

    private static final TareResponseDto TARE_RESPONSE_DTO_2 = TareResponseDto.builder()
            .id(UUID.fromString("6e9f3c65-86f8-4774-b9ed-36e3651121be"))
            .tareCategory(TareCategory.GLASS_BOTTLE)
            .litresVolume(BigDecimal.valueOf(0.5))
            .accountingBonusesCount(30)
            .build();

    private static final Tare SAVED_TARE_2 = Tare.builder()
            .id(UUID.fromString("6e9f3c65-86f8-4774-b9ed-36e3651121be"))
            .tareCategory(TareCategory.GLASS_BOTTLE)
            .litresVolume(BigDecimal.valueOf(0.2))
            .accountingBonusesCount(30)
            .build();

    private static final Tare SAVED_TARE_1 = Tare.builder()
            .id(TARE_ID)
            .tareCategory(TareCategory.PLASTIC_BOTTLE)
            .litresVolume(BigDecimal.valueOf(0.5))
            .accountingBonusesCount(10)
            .build();

    @Mock
    private TareBonusesCountClient mockBonusesCountClient;
    @Mock
    private UserService mockUserService;
    @Mock
    private TareRepository mockTareRepository;
    @Mock
    private TareMapper mockTareMapper;
    private TareService tareServiceImpl;

    @BeforeEach
    void setUp() {
        tareServiceImpl = new TareServiceImpl(mockBonusesCountClient, mockUserService, mockTareRepository, mockTareMapper);
    }

    @Test
    void saveTareTest_successful() {
        final CreateTareRequestDto tareRequestDto = CreateTareRequestDto.builder()
                .tareCategory(TareCategory.PLASTIC_BOTTLE)
                .litresVolume(BigDecimal.valueOf(0.5))
                .userId(USER_ID)
                .build();

        final User user = User.builder()
                .id(USER_ID)
                .login("armail@master.by")
                .password("Assdssa")
                .name("Armail")
                .bonuses(0)
                .role(Role.CONSUMER)
                .tares(new ArrayList<>())
                .build();

        final Tare tare = Tare.builder()
                .tareCategory(TareCategory.PLASTIC_BOTTLE)
                .litresVolume(BigDecimal.valueOf(0.5))
                .build();

        final BonusesCountResponseDto bonusesCountResponseDto = BonusesCountResponseDto.builder()
                .accountingBonusesCount(10)
                .build();

        final Tare tareWithoutId = Tare.builder()
                .tareCategory(TareCategory.PLASTIC_BOTTLE)
                .litresVolume(BigDecimal.valueOf(0.5))
                .accountingBonusesCount(10)
                .user(user)
                .build();

        final Tare savedTare = Tare.builder()
                .tareCategory(TareCategory.PLASTIC_BOTTLE)
                .litresVolume(BigDecimal.valueOf(0.5))
                .accountingBonusesCount(10)
                .user(user)
                .build();

        doReturn(tare).when(mockTareMapper).mapToTare(tareRequestDto);
        doReturn(bonusesCountResponseDto).when(mockBonusesCountClient).getBonusesCount(BigDecimal.valueOf(0.5),
                TareCategory.PLASTIC_BOTTLE);
        doReturn(user).when(mockUserService).getUserById(USER_ID);
        doReturn(savedTare).when(mockTareRepository).save(tareWithoutId);
        doReturn(TARE_RESPONSE_DTO_1).when(mockTareMapper).mapToTareResponse(tareWithoutId);

        final var expected = tareServiceImpl.saveTare(tareRequestDto);

        assertEquals(expected, TARE_RESPONSE_DTO_1);
        verify(mockTareMapper).mapToTare(tareRequestDto);
        verify(mockBonusesCountClient).getBonusesCount(BigDecimal.valueOf(0.5), TareCategory.PLASTIC_BOTTLE);
        verify(mockUserService).getUserById(USER_ID);
        verify(mockTareRepository).save(tareWithoutId);
        verify(mockTareMapper).mapToTareResponse(tareWithoutId);

        verifyNoMoreInteractions(mockTareMapper);
        verifyNoMoreInteractions(mockBonusesCountClient);
        verifyNoMoreInteractions(mockUserService);
        verifyNoMoreInteractions(mockTareRepository);
    }

    @Test
    void readTaresTest_successful() {
        final TareResponseDto tareResponseDto3 = TareResponseDto.builder()
                .id(UUID.fromString("7b3ab68d-1f3e-4e4e-8d5c-36e3651121be"))
                .tareCategory(TareCategory.PLASTIC_BOTTLE)
                .litresVolume(BigDecimal.valueOf(2.0))
                .accountingBonusesCount(20)
                .build();

        final Tare tare3 = Tare.builder()
                .id(UUID.fromString("7b3ab68d-1f3e-4e4e-8d5c-36e3651121be"))
                .tareCategory(TareCategory.PLASTIC_BOTTLE)
                .litresVolume(BigDecimal.valueOf(2.0))
                .accountingBonusesCount(20)
                .build();

        List<Tare> tares = List.of(SAVED_TARE_1, SAVED_TARE_2, tare3);

        List<TareResponseDto> actual = List.of(TARE_RESPONSE_DTO_1, TARE_RESPONSE_DTO_2, tareResponseDto3);

        doReturn(tares).when(mockTareRepository).findAll();
        doReturn(TARE_RESPONSE_DTO_1).when(mockTareMapper).mapToTareResponse(SAVED_TARE_1);
        doReturn(TARE_RESPONSE_DTO_2).when(mockTareMapper).mapToTareResponse(SAVED_TARE_2);
        doReturn(tareResponseDto3).when(mockTareMapper).mapToTareResponse(tare3);

        final var expected = tareServiceImpl.getTares();

        assertEquals(expected, actual);

        verify(mockTareRepository).findAll();
        verify(mockTareMapper).mapToTareResponse(SAVED_TARE_1);
        verify(mockTareMapper).mapToTareResponse(SAVED_TARE_2);
        verify(mockTareMapper).mapToTareResponse(tare3);

        verifyNoMoreInteractions(mockTareRepository);
        verifyNoMoreInteractions(mockTareMapper);
    }

    @Test
    void getTaresByUserIdTest_successful() {
        final var tares = List.of(SAVED_TARE_1, SAVED_TARE_2);

        final var actual = List.of(TARE_RESPONSE_DTO_1, TARE_RESPONSE_DTO_2);

        doReturn(tares).when(mockTareRepository).findTaresByUserId(USER_ID);
        doReturn(TARE_RESPONSE_DTO_1).when(mockTareMapper).mapToTareResponse(SAVED_TARE_1);
        doReturn(TARE_RESPONSE_DTO_2).when(mockTareMapper).mapToTareResponse(SAVED_TARE_2);

        final var expected = tareServiceImpl.getTaresByUserId(USER_ID);

        assertEquals(expected, actual);

        verify(mockTareRepository).findTaresByUserId(USER_ID);
        verify(mockTareMapper).mapToTareResponse(SAVED_TARE_1);
        verify(mockTareMapper).mapToTareResponse(SAVED_TARE_2);
        verifyNoMoreInteractions(mockTareRepository);
        verifyNoMoreInteractions(mockTareMapper);
    }

    @Test
    void getByIdTest_successful() {
        doReturn(Optional.ofNullable(SAVED_TARE_1)).when(mockTareRepository).findById(TARE_ID);
        doReturn(TARE_RESPONSE_DTO_1).when(mockTareMapper).mapToTareResponse(SAVED_TARE_1);

        final var expected = tareServiceImpl.getTareResponseById(TARE_ID);

        assertEquals(expected, TARE_RESPONSE_DTO_1);

        verify(mockTareRepository).findById(TARE_ID);
        verify(mockTareMapper).mapToTareResponse(SAVED_TARE_1);

        verifyNoMoreInteractions(mockTareRepository);
        verifyNoMoreInteractions(mockTareMapper);
    }
}
