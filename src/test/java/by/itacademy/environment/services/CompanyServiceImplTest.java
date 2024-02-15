package by.itacademy.environment.services;

import by.itacademy.environment.dto.request.CreateCompanyRequestDto;
import by.itacademy.environment.dto.request.UpdateCompanyRequestDto;
import by.itacademy.environment.dto.response.CompanyResponseDto;
import by.itacademy.environment.entities.Company;
import by.itacademy.environment.exceptions.CompanyNotFoundException;
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
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @Test
    void saveCompanyTest_successful() {
        final CreateCompanyRequestDto companyRequestDto = CreateCompanyRequestDto.builder()
                .companyName("Sosedi")
                .details("UN: 40003415")
                .build();

        Mockito.doReturn(COMPANY_1).when(mockCompanyMapper).mapToCompany(companyRequestDto);
        Mockito.doReturn(SAVED_COMPANY_1).when(mockCompanyRepository).save(COMPANY_1);
        Mockito.doReturn(COMPANY_RESPONSE_DTO_1).when(mockCompanyMapper).mapToCompanyResponse(SAVED_COMPANY_1);

        final var expected = companyServiceImpl.saveCompany(companyRequestDto);

        Assertions.assertEquals(expected, COMPANY_RESPONSE_DTO_1);
        Mockito.verify(mockCompanyMapper).mapToCompany(companyRequestDto);
        Mockito.verify(mockCompanyRepository).save(COMPANY_1);
        Mockito.verify(mockCompanyMapper).mapToCompanyResponse(SAVED_COMPANY_1);

        Mockito.verifyNoMoreInteractions(mockCompanyMapper);
        Mockito.verifyNoMoreInteractions(mockCompanyRepository);
    }

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

        Mockito.doReturn(companies).when(mockCompanyRepository).findAll();
        Mockito.doReturn(COMPANY_RESPONSE_DTO_1).when(mockCompanyMapper).mapToCompanyResponse(COMPANY_1);
        Mockito.doReturn(COMPANY_RESPONSE_DTO_2).when(mockCompanyMapper).mapToCompanyResponse(COMPANY_2);
        Mockito.doReturn(companyResponseDto3).when(mockCompanyMapper).mapToCompanyResponse(company3);

        final var expected = companyServiceImpl.readCompanies();

        Assertions.assertEquals(expected, actual);

        Mockito.verify(mockCompanyRepository).findAll();
        Mockito.verify(mockCompanyMapper).mapToCompanyResponse(COMPANY_1);
        Mockito.verify(mockCompanyMapper).mapToCompanyResponse(COMPANY_2);
        Mockito.verify(mockCompanyMapper).mapToCompanyResponse(company3);

        Mockito.verifyNoMoreInteractions(mockCompanyRepository);
        Mockito.verifyNoMoreInteractions(mockCompanyMapper);
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

        Mockito.doReturn(Optional.ofNullable(SAVED_COMPANY_1)).when(mockCompanyRepository).findById(COMPANY_ID);
        Mockito.doAnswer(invocationOnMock
                        -> invocationOnMock
                        .getArgument(1, Company.class)
                        .setCompanyName("Sosedi++")
                        .setDetails("UN: 40008989"))
                .when(mockCompanyMapper)
                .updateCompany(companyRequestDto, SAVED_COMPANY_1);
        Mockito.doReturn(updatedCompany1).when(mockCompanyRepository).save(updatedCompany1);
        Mockito.doReturn(actual).when(mockCompanyMapper).mapToCompanyResponse(updatedCompany1);

        final var expected = companyServiceImpl.updateCompany(COMPANY_ID, companyRequestDto);

        Assertions.assertEquals(expected, actual);

        Mockito.verify(mockCompanyRepository).findById(COMPANY_ID);
        Mockito.verify(mockCompanyMapper).updateCompany(companyRequestDto, SAVED_COMPANY_1);
        Mockito.verify(mockCompanyRepository).save(updatedCompany1);
        Mockito.verify(mockCompanyMapper).mapToCompanyResponse(updatedCompany1);

        Mockito.verifyNoMoreInteractions(mockCompanyRepository);
        Mockito.verifyNoMoreInteractions(mockCompanyMapper);
    }

    @Test
    void updateCouponTest_throwCouponServiceException() {
        final UpdateCompanyRequestDto companyRequestDto = UpdateCompanyRequestDto.builder()
                .companyName("Sosedi++")
                .details("UN: 40008989")
                .build();

        Mockito.doReturn(Optional.empty()).when(mockCompanyRepository).findById(COMPANY_ID);

        final var error = Assertions.assertThrowsExactly(CompanyNotFoundException.class,
                () -> companyServiceImpl.updateCompany(COMPANY_ID, companyRequestDto));

        Assertions.assertEquals(error.getMessage(), COMPANY_ID.toString());

        Mockito.verify(mockCompanyRepository).findById(COMPANY_ID);

        Mockito.verifyNoMoreInteractions(mockCompanyRepository);
    }

    @Test
    void getByIdTest_successful() {
        Mockito.doReturn(Optional.ofNullable(COMPANY_1)).when(mockCompanyRepository).findById(COMPANY_ID);
        Mockito.doReturn(COMPANY_RESPONSE_DTO_1).when(mockCompanyMapper).mapToCompanyResponse(COMPANY_1);

        final var expected = companyServiceImpl.getCompanyResponseById(COMPANY_ID);

        Assertions.assertEquals(expected, COMPANY_RESPONSE_DTO_1);

        Mockito.verify(mockCompanyRepository).findById(COMPANY_ID);
        Mockito.verify(mockCompanyMapper).mapToCompanyResponse(COMPANY_1);

        Mockito.verifyNoMoreInteractions(mockCompanyRepository);
        Mockito.verifyNoMoreInteractions(mockCompanyMapper);
    }
}
