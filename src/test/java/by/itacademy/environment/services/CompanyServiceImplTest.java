package by.itacademy.environment.services;

import by.itacademy.environment.dto.request.UpdateCompanyRequestDto;
import by.itacademy.environment.dto.response.CompanyResponseDto;
import by.itacademy.environment.entities.Company;
import by.itacademy.environment.exceptions.notFound.CompanyNotFoundException;
import by.itacademy.environment.feignClients.CompanyAddressClient;
import by.itacademy.environment.mappers.CompanyMapper;
import by.itacademy.environment.repositories.company.CompanyRepository;
import by.itacademy.environment.services.company.CompanyService;
import by.itacademy.environment.services.company.CompanyServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceImplTest {

    private static final UUID COMPANY_ID = UUID.fromString("00010203-0405-0607-0809-0a0b0c0d0e0f");

    private static final Company COMPANY_1 = Company.builder()
            .companyName("Sosedi")
            .details("UN: 40003415")
            .coupons(new ArrayList<>())
            .build();

    private static final Company SAVED_COMPANY_1 = Company.builder()
            .id(COMPANY_ID)
            .companyName("Sosedi")
            .details("UN: 40003415")
            .build();

    private static final Company COMPANY_2 = Company.builder()
            .id(UUID.fromString("2a01a6ca-acb4-4399-ac59-63045722af4e"))
            .companyName("Green")
            .details("UN: 40007116")
            .build();

    private static final CompanyResponseDto COMPANY_RESPONSE_DTO_1 = CompanyResponseDto.builder()
            .id(COMPANY_ID)
            .companyName("Sosedi")
            .details("UN: 40003415")
            .build();

    private static final CompanyResponseDto COMPANY_RESPONSE_DTO_2 = CompanyResponseDto.builder()
            .id(UUID.fromString("2a01a6ca-acb4-4399-ac59-63045722af4e"))
            .companyName("Green")
            .details("UN: 40007116")
            .build();

    @Mock
    private CompanyRepository mockCompanyRepository;
    @Mock
    private CompanyMapper mockCompanyMapper;
    @Mock
    private CompanyAddressClient mockAddressClient;
    private CompanyService companyServiceImpl;

    @BeforeEach
    void setUp() {
        companyServiceImpl = new CompanyServiceImpl(mockCompanyRepository, mockCompanyMapper, mockAddressClient);
    }

//    @Test
//    void saveCompanyTest_successful() {
//        final CreateCompanyRequestDto companyRequestDto = CreateCompanyRequestDto.builder()
//                .companyName("Sosedi")
//                .details("UN: 40003415")
//                .build();
//
//        doReturn(COMPANY_1).when(mockCompanyMapper).mapToCompany(companyRequestDto);
//        doReturn(SAVED_COMPANY_1).when(mockCompanyRepository).save(COMPANY_1);
//        doReturn(COMPANY_RESPONSE_DTO_1).when(mockCompanyMapper).mapToCompanyResponse(SAVED_COMPANY_1);
//
//        final var expected = companyServiceImpl.saveCompany(companyRequestDto);
//
//        assertEquals(expected, COMPANY_RESPONSE_DTO_1);
//        verify(mockCompanyMapper).mapToCompany(companyRequestDto);
//        verify(mockCompanyRepository).save(COMPANY_1);
//        verify(mockCompanyMapper).mapToCompanyResponse(SAVED_COMPANY_1);
//
//        verifyNoMoreInteractions(mockCompanyMapper);
//        verifyNoMoreInteractions(mockCompanyRepository);
//    } // TODO

    @Test
    void readCouponsTest_successful() {
        final CompanyResponseDto companyResponseDto3 = CompanyResponseDto.builder()
                .id(UUID.fromString("7808fdd0-c51e-11ee-9b2b-f7474e46232d"))
                .companyName("Almi")
                .details("UN: 40001843")
                .build();

        final Company company3 = Company.builder()
                .id(UUID.fromString("7808fdd0-c51e-11ee-9b2b-f7474e46232d"))
                .companyName("Almi")
                .details("UN: 40001843")
                .build();

        List<Company> companies = List.of(COMPANY_1, COMPANY_2, company3);

        List<CompanyResponseDto> actual = List.of(COMPANY_RESPONSE_DTO_1, COMPANY_RESPONSE_DTO_2, companyResponseDto3);

        doReturn(companies).when(mockCompanyRepository).findAll();
        doReturn(COMPANY_RESPONSE_DTO_1).when(mockCompanyMapper).mapToCompanyResponse(COMPANY_1);
        doReturn(COMPANY_RESPONSE_DTO_2).when(mockCompanyMapper).mapToCompanyResponse(COMPANY_2);
        doReturn(companyResponseDto3).when(mockCompanyMapper).mapToCompanyResponse(company3);

        final var expected = companyServiceImpl.getCompanies();

        assertEquals(expected, actual);

        verify(mockCompanyRepository).findAll();
        verify(mockCompanyMapper).mapToCompanyResponse(COMPANY_1);
        verify(mockCompanyMapper).mapToCompanyResponse(COMPANY_2);
        verify(mockCompanyMapper).mapToCompanyResponse(company3);

        verifyNoMoreInteractions(mockCompanyRepository);
        verifyNoMoreInteractions(mockCompanyMapper);
    }

    @Test
    void updateCouponTest_successful() {
        final UpdateCompanyRequestDto companyRequestDto = UpdateCompanyRequestDto.builder()
                .companyName("Sosedi++")
                .details("UN: 40008989")
                .build();

        final Company updatedCompany1 = Company.builder()
                .id(COMPANY_ID)
                .companyName("Sosedi++")
                .details("UN: 40008989")
                .build();

        final CompanyResponseDto actual = CompanyResponseDto.builder()
                .id(COMPANY_ID)
                .companyName("Sosedi++")
                .details("UN: 40008989")
                .build();

        doReturn(Optional.ofNullable(SAVED_COMPANY_1)).when(mockCompanyRepository).findById(COMPANY_ID);
        doAnswer(invocationOnMock
                        -> invocationOnMock
                        .getArgument(1, Company.class)
                        .setCompanyName("Sosedi++")
                        .setDetails("UN: 40008989"))
                .when(mockCompanyMapper)
                .updateCompany(companyRequestDto, SAVED_COMPANY_1);
        doReturn(updatedCompany1).when(mockCompanyRepository).save(updatedCompany1);
        doReturn(actual).when(mockCompanyMapper).mapToCompanyResponse(updatedCompany1);

        final var expected = companyServiceImpl.updateCompany(COMPANY_ID, companyRequestDto);

        assertEquals(expected, actual);

        verify(mockCompanyRepository).findById(COMPANY_ID);
        verify(mockCompanyMapper).updateCompany(companyRequestDto, SAVED_COMPANY_1);
        verify(mockCompanyRepository).save(updatedCompany1);
        verify(mockCompanyMapper).mapToCompanyResponse(updatedCompany1);

        verifyNoMoreInteractions(mockCompanyRepository);
        verifyNoMoreInteractions(mockCompanyMapper);
    }

    @Test
    void updateCouponTest_throwCouponServiceException() {
        final UpdateCompanyRequestDto companyRequestDto = UpdateCompanyRequestDto.builder()
                .companyName("Sosedi++")
                .details("UN: 40008989")
                .build();

        doReturn(Optional.empty()).when(mockCompanyRepository).findById(COMPANY_ID);

        final var error = Assertions.assertThrowsExactly(CompanyNotFoundException.class,
                () -> companyServiceImpl.updateCompany(COMPANY_ID, companyRequestDto));

        assertEquals(error.getMessage(), COMPANY_ID.toString());

        verify(mockCompanyRepository).findById(COMPANY_ID);

        verifyNoMoreInteractions(mockCompanyRepository);
    }

    @Test
    void getByIdTest_successful() {
        doReturn(Optional.ofNullable(COMPANY_1)).when(mockCompanyRepository).findById(COMPANY_ID);
        doReturn(COMPANY_RESPONSE_DTO_1).when(mockCompanyMapper).mapToCompanyResponse(COMPANY_1);

        final var expected = companyServiceImpl.getCompanyResponseById(COMPANY_ID);

        assertEquals(expected, COMPANY_RESPONSE_DTO_1);

        verify(mockCompanyRepository).findById(COMPANY_ID);
        verify(mockCompanyMapper).mapToCompanyResponse(COMPANY_1);

        verifyNoMoreInteractions(mockCompanyRepository);
        verifyNoMoreInteractions(mockCompanyMapper);
    }
}
