package by.itacademy.services;

import by.itacademy.dto.request.UpdateTareRequestDto;
import by.itacademy.dto.response.TareResponseDto;
import by.itacademy.dto.request.CreateTareRequestDto;
import by.itacademy.entities.Tare;
import by.itacademy.enums.TareCategory;
import by.itacademy.exceptions.notFound.TareNotFoundException;
import by.itacademy.mappers.TareMapper;
import by.itacademy.repositories.tare.TareRepository;
import by.itacademy.services.tare.TareService;
import by.itacademy.services.tare.TareServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class TareServiceImplTest {

    private static final UUID TARE_ID = UUID.fromString("2b4bb68d-1f3e-4e4e-8d5c-8d56abe2043a");
    private static final UUID USER_ID = UUID.fromString("4b4bb68a-1f3e-4e4e-8d5c-8d56abe2043a");

    private static final TareResponseDto TARE_RESPONSE_DTO_1 = TareResponseDto.builder()
            .id(TARE_ID)
            .tareCategory(TareCategory.PLASTIC_BOTTLE)
            .litresVolume(0.5)
            .bonusesToAccounting(10)
            .build();

    private static final TareResponseDto TARE_RESPONSE_DTO_2 = TareResponseDto.builder()
            .id(UUID.fromString("6e9f3c65-86f8-4774-b9ed-36e3651121be"))
            .tareCategory(TareCategory.GLASS_BOTTLE)
            .litresVolume(0.2)
            .bonusesToAccounting(30)
            .build();

    private static final Tare TARE_1 = Tare.builder()
            .tareCategory(TareCategory.PLASTIC_BOTTLE)
            .litresVolume(0.5)
            .bonusesToAccounting(10)
            .build();

    private static final Tare SAVED_TARE_2 = Tare.builder()
            .id(UUID.fromString("6e9f3c65-86f8-4774-b9ed-36e3651121be"))
            .tareCategory(TareCategory.GLASS_BOTTLE)
            .litresVolume(0.2)
            .bonusesToAccounting(30)
            .build();

    private static final Tare SAVED_TARE_1 = Tare.builder()
            .id(TARE_ID)
            .tareCategory(TareCategory.PLASTIC_BOTTLE)
            .litresVolume(0.5)
            .bonusesToAccounting(10)
            .build();

    @Mock
    private TareRepository mockTareRepository;
    @Mock
    private TareMapper mockTareMapper;
    private TareService tareServiceImpl;

    @BeforeEach
    void setUp() {
        tareServiceImpl = new TareServiceImpl(mockTareRepository, mockTareMapper);
    }

    @Test
    void saveTareTest_successful() {
        final CreateTareRequestDto tareRequestDto = CreateTareRequestDto.builder()
                .tareCategory(TareCategory.PLASTIC_BOTTLE)
                .litresVolume(0.5)
                .bonusesToAccounting(10)
                .build();

        Mockito.doReturn(TARE_1).when(mockTareMapper).mapToTare(tareRequestDto);
        Mockito.doReturn(SAVED_TARE_1).when(mockTareRepository).save(TARE_1);
        Mockito.doReturn(TARE_RESPONSE_DTO_1).when(mockTareMapper).mapToTareResponse(SAVED_TARE_1);

        final var expected = tareServiceImpl.saveTare(tareRequestDto);

        Assertions.assertEquals(expected, TARE_RESPONSE_DTO_1);
        Mockito.verify(mockTareMapper).mapToTare(tareRequestDto);
        Mockito.verify(mockTareRepository).save(TARE_1);
        Mockito.verify(mockTareMapper).mapToTareResponse(SAVED_TARE_1);

        Mockito.verifyNoMoreInteractions(mockTareMapper);
        Mockito.verifyNoMoreInteractions(mockTareRepository);
    }

    @Test
    void readTaresTest_successful() {
        final TareResponseDto tareResponseDto3 = TareResponseDto.builder()
                .id(UUID.fromString("7b3ab68d-1f3e-4e4e-8d5c-36e3651121be"))
                .tareCategory(TareCategory.PLASTIC_BOTTLE)
                .litresVolume(2.0)
                .bonusesToAccounting(20)
                .build();

        final Tare tare3 = Tare.builder()
                .id(UUID.fromString("7b3ab68d-1f3e-4e4e-8d5c-36e3651121be"))
                .tareCategory(TareCategory.PLASTIC_BOTTLE)
                .litresVolume(2.0)
                .bonusesToAccounting(20)
                .build();

        List<Tare> tares = List.of(SAVED_TARE_1, SAVED_TARE_2, tare3);

        List<TareResponseDto> actual = List.of(TARE_RESPONSE_DTO_1, TARE_RESPONSE_DTO_2, tareResponseDto3);

        Mockito.doReturn(tares).when(mockTareRepository).findAll();
        Mockito.doReturn(TARE_RESPONSE_DTO_1).when(mockTareMapper).mapToTareResponse(SAVED_TARE_1);
        Mockito.doReturn(TARE_RESPONSE_DTO_2).when(mockTareMapper).mapToTareResponse(SAVED_TARE_2);
        Mockito.doReturn(tareResponseDto3).when(mockTareMapper).mapToTareResponse(tare3);

        final var expected = tareServiceImpl.readTares();

        Assertions.assertEquals(expected, actual);

        Mockito.verify(mockTareRepository).findAll();
        Mockito.verify(mockTareMapper).mapToTareResponse(SAVED_TARE_1);
        Mockito.verify(mockTareMapper).mapToTareResponse(SAVED_TARE_2);
        Mockito.verify(mockTareMapper).mapToTareResponse(tare3);

        Mockito.verifyNoMoreInteractions(mockTareRepository);
        Mockito.verifyNoMoreInteractions(mockTareMapper);
    }

    @Test
    void getTaresByUserIdTest_successful() {
        final var tares = List.of(SAVED_TARE_1, SAVED_TARE_2);

        final var actual = List.of(TARE_RESPONSE_DTO_1, TARE_RESPONSE_DTO_2);

        Mockito.doReturn(tares).when(mockTareRepository).findTaresByUsersId(USER_ID);
        Mockito.doReturn(TARE_RESPONSE_DTO_1).when(mockTareMapper).mapToTareResponse(SAVED_TARE_1);
        Mockito.doReturn(TARE_RESPONSE_DTO_2).when(mockTareMapper).mapToTareResponse(SAVED_TARE_2);

        final var expected = tareServiceImpl.getTaresByUserId(USER_ID);

        Assertions.assertEquals(expected, actual);

        Mockito.verify(mockTareRepository).findTaresByUsersId(USER_ID);
        Mockito.verify(mockTareMapper).mapToTareResponse(SAVED_TARE_1);
        Mockito.verify(mockTareMapper).mapToTareResponse(SAVED_TARE_2);
        Mockito.verifyNoMoreInteractions(mockTareRepository);
        Mockito.verifyNoMoreInteractions(mockTareMapper);
    }

    @Test
    void updateTareTest_successful() {
        final UpdateTareRequestDto tareRequestDto = UpdateTareRequestDto.builder()
                .bonusesToAccounting(17)
                .build();

        final Tare updatedTare1 = Tare.builder()
                .id(TARE_ID)
                .tareCategory(TareCategory.PLASTIC_BOTTLE)
                .litresVolume(0.5)
                .bonusesToAccounting(17)
                .build();

        final TareResponseDto updatedTareResponseDto = TareResponseDto.builder()
                .id(TARE_ID)
                .tareCategory(TareCategory.PLASTIC_BOTTLE)
                .litresVolume(0.5)
                .bonusesToAccounting(17)
                .build();

        Mockito.doReturn(Optional.ofNullable(SAVED_TARE_1)).when(mockTareRepository).findById(TARE_ID);
        Mockito.doAnswer(invocationOnMock
                        -> invocationOnMock
                        .getArgument(1, Tare.class)
                        .setBonusesToAccounting(17))
                .when(mockTareMapper)
                .updateTare(tareRequestDto, SAVED_TARE_1);
        Mockito.doReturn(updatedTare1).when(mockTareRepository).save(updatedTare1);
        Mockito.doReturn(updatedTareResponseDto).when(mockTareMapper).mapToTareResponse(updatedTare1);

        final var expected = tareServiceImpl.updateTare(TARE_ID, tareRequestDto);

        Assertions.assertEquals(expected, updatedTareResponseDto);

        Mockito.verify(mockTareRepository).findById(TARE_ID);
        Mockito.verify(mockTareMapper).updateTare(tareRequestDto, SAVED_TARE_1);
        Mockito.verify(mockTareRepository).save(updatedTare1);
        Mockito.verify(mockTareMapper).mapToTareResponse(updatedTare1);

        Mockito.verifyNoMoreInteractions(mockTareRepository);
        Mockito.verifyNoMoreInteractions(mockTareMapper);
    }

    @Test
    void updateTareTest_throwTareServiceException() {
        final UpdateTareRequestDto tareRequestDto = UpdateTareRequestDto.builder()
                .bonusesToAccounting(17)
                .build();

        Mockito.doReturn(Optional.empty()).when(mockTareRepository).findById(TARE_ID);

        final var error = Assertions.assertThrowsExactly(TareNotFoundException.class,
                () -> tareServiceImpl.updateTare(TARE_ID, tareRequestDto));

        Assertions.assertEquals(error.getMessage(), TARE_ID.toString());

        Mockito.verify(mockTareRepository).findById(TARE_ID);
        Mockito.verifyNoMoreInteractions(mockTareRepository);
    }

    @Test
    void getByIdTest_successful() {
        Mockito.doReturn(Optional.ofNullable(SAVED_TARE_1)).when(mockTareRepository).findById(TARE_ID);
        Mockito.doReturn(TARE_RESPONSE_DTO_1).when(mockTareMapper).mapToTareResponse(SAVED_TARE_1);

        final var expected = tareServiceImpl.getTareResponseById(TARE_ID);

        Assertions.assertEquals(expected, TARE_RESPONSE_DTO_1);

        Mockito.verify(mockTareRepository).findById(TARE_ID);
        Mockito.verify(mockTareMapper).mapToTareResponse(SAVED_TARE_1);

        Mockito.verifyNoMoreInteractions(mockTareRepository);
        Mockito.verifyNoMoreInteractions(mockTareMapper);
    }
}
