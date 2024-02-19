package by.itacademy.services;

import by.itacademy.dto.response.CouponResponseDto;
import by.itacademy.dto.request.CreateCouponRequestDto;
import by.itacademy.dto.request.UpdateCouponRequestDto;
import by.itacademy.entities.Company;
import by.itacademy.entities.Coupon;
import by.itacademy.exceptions.notFound.CouponNotFoundException;
import by.itacademy.mappers.CouponMapper;
import by.itacademy.repositories.coupon.CouponRepository;
import by.itacademy.repositories.user.UserRepository;
import by.itacademy.services.company.CompanyService;
import by.itacademy.services.coupon.CouponService;
import by.itacademy.services.coupon.CouponServiceImpl;
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
public class CouponServiceImplTest {

    private static final UUID COUPON_ID = UUID.fromString("3422b448-2460-4fd2-9183-8000de6f8343");
    private static final UUID USER_ID = UUID.fromString("e58ed763-928c-4155-bee9-fdbaaadc15f3");
    private static final UUID COMPANY_ID = UUID.fromString("123e4567-e89b-12d3-a456-556642440000");

    private static final Coupon COUPON_1 = Coupon.builder()
            .description("Discount 10%")
            .cost(30)
            .build();

    private static final Coupon SAVED_COUPON_1 = Coupon.builder()
            .id(COUPON_ID)
            .description("Discount 10%")
            .cost(30)
            .build();

    private static final Coupon COUPON_2 = Coupon.builder()
            .id(UUID.fromString("417ddc5d-e556-4d27-95dd-a34d84e46a50"))
            .description("Discount 5%")
            .cost(17)
            .build();

    private static final CouponResponseDto COUPON_RESPONSE_DTO_1 = CouponResponseDto.builder()
            .id(COUPON_ID)
            .description("Discount 10%")
            .cost(30)
            .build();

    private static final CouponResponseDto COUPON_RESPONSE_DTO_2 = CouponResponseDto.builder()
            .id(UUID.fromString("417ddc5d-e556-4d27-95dd-a34d84e46a50"))
            .description("Discount 5%")
            .cost(17)
            .build();

    @Mock
    private CouponRepository mockCouponRepository;
    @Mock
    private CompanyService mockCompanyService;
    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private CouponMapper mockCouponMapper;
    private CouponService couponServiceImpl;

    @BeforeEach
    void setUp() {
        couponServiceImpl = new CouponServiceImpl(mockCouponRepository, mockCompanyService,
                mockUserRepository, mockCouponMapper);
    }

    @Test
    void saveCouponTest_successful() {
        final CreateCouponRequestDto couponRequestDto = CreateCouponRequestDto.builder()
                .description("Discount 10%")
                .cost(30)
                .companyId(COMPANY_ID)
                .build();

        final Company company = Company.builder()
                .id(COMPANY_ID)
                .companyName("Sosedi")
                .details("UNN: 40001223")
                .coupons(new ArrayList<>())
                .build();

        Mockito.doReturn(COUPON_1).when(mockCouponMapper).mapToCoupon(couponRequestDto);
        Mockito.doReturn(company).when(mockCompanyService).getCompanyById(COMPANY_ID);
        Mockito.doReturn(SAVED_COUPON_1).when(mockCouponRepository).save(COUPON_1);
        Mockito.doReturn(COUPON_RESPONSE_DTO_1).when(mockCouponMapper).mapToCouponResponse(SAVED_COUPON_1);

        final var expected = couponServiceImpl.saveCoupon(couponRequestDto);

        Assertions.assertEquals(expected, COUPON_RESPONSE_DTO_1);
        Mockito.verify(mockCouponMapper).mapToCoupon(couponRequestDto);
        Mockito.verify(mockCompanyService).getCompanyById(COMPANY_ID);
        Mockito.verify(mockCouponRepository).save(COUPON_1);
        Mockito.verify(mockCouponMapper).mapToCouponResponse(SAVED_COUPON_1);

        Mockito.verifyNoMoreInteractions(mockCouponMapper);
        Mockito.verifyNoMoreInteractions(mockCouponRepository);
    }

    @Test
    void readCouponsTest_successful() {
        final CouponResponseDto couponResponseDto3 = CouponResponseDto.builder()
                .id(UUID.fromString("7b3ab68d-1f3e-4e4e-8d5c-36e3651121be"))
                .description("5 rubles to discount card")
                .cost(50)
                .build();

        final Coupon coupon3 = Coupon.builder()
                .id(UUID.fromString("7b3ab68d-1f3e-4e4e-8d5c-36e3651121be"))
                .description("5 rubles to discount card")
                .cost(50)
                .build();

        List<Coupon> coupons = List.of(COUPON_1, COUPON_2, coupon3);

        List<CouponResponseDto> actual = List.of(COUPON_RESPONSE_DTO_1, COUPON_RESPONSE_DTO_2, couponResponseDto3);

        Mockito.doReturn(coupons).when(mockCouponRepository).findAll();
        Mockito.doReturn(COUPON_RESPONSE_DTO_1).when(mockCouponMapper).mapToCouponResponse(COUPON_1);
        Mockito.doReturn(COUPON_RESPONSE_DTO_2).when(mockCouponMapper).mapToCouponResponse(COUPON_2);
        Mockito.doReturn(couponResponseDto3).when(mockCouponMapper).mapToCouponResponse(coupon3);

        final var expected = couponServiceImpl.readCoupons();

        Assertions.assertEquals(expected, actual);

        Mockito.verify(mockCouponRepository).findAll();
        Mockito.verify(mockCouponMapper).mapToCouponResponse(COUPON_1);
        Mockito.verify(mockCouponMapper).mapToCouponResponse(COUPON_2);
        Mockito.verify(mockCouponMapper).mapToCouponResponse(coupon3);

        Mockito.verifyNoMoreInteractions(mockCouponRepository);
        Mockito.verifyNoMoreInteractions(mockCouponMapper);
    }

    @Test
    void updateCouponTest_successful() {
        final UpdateCouponRequestDto couponRequestDto = UpdateCouponRequestDto.builder()
                .cost(60)
                .build();

        final Coupon updatedCoupon1 = Coupon.builder()
                .id(COUPON_ID)
                .description("Discount 5 rubles")
                .cost(60)
                .build();

        final CouponResponseDto actual = CouponResponseDto.builder()
                .id(COUPON_ID)
                .description("Discount 5 rubles")
                .cost(60)
                .build();

        Mockito.doReturn(Optional.ofNullable(SAVED_COUPON_1)).when(mockCouponRepository).findById(COUPON_ID);
        Mockito.doAnswer(invocationOnMock
                        -> invocationOnMock
                        .getArgument(1, Coupon.class)
                        .setDescription("Discount 5 rubles")
                        .setCost(60))
                .when(mockCouponMapper)
                .updateCoupon(couponRequestDto, SAVED_COUPON_1);
        Mockito.doReturn(updatedCoupon1).when(mockCouponRepository).save(updatedCoupon1);
        Mockito.doReturn(actual).when(mockCouponMapper).mapToCouponResponse(updatedCoupon1);

        final var expected = couponServiceImpl.updateCoupon(COUPON_ID, couponRequestDto);

        Assertions.assertEquals(expected, actual);

        Mockito.verify(mockCouponRepository).findById(COUPON_ID);
        Mockito.verify(mockCouponMapper).updateCoupon(couponRequestDto, SAVED_COUPON_1);
        Mockito.verify(mockCouponRepository).save(updatedCoupon1);
        Mockito.verify(mockCouponMapper).mapToCouponResponse(updatedCoupon1);

        Mockito.verifyNoMoreInteractions(mockCouponRepository);
        Mockito.verifyNoMoreInteractions(mockCouponMapper);
    }

    @Test
    void updateCouponTest_throwCouponServiceException() {
        final UpdateCouponRequestDto couponRequestDto = UpdateCouponRequestDto.builder()
                .cost(60)
                .build();

        Mockito.doReturn(Optional.empty()).when(mockCouponRepository).findById(COUPON_ID);

        final var error = Assertions.assertThrowsExactly(CouponNotFoundException.class,
                () -> couponServiceImpl.updateCoupon(COUPON_ID, couponRequestDto));

        Assertions.assertEquals(error.getMessage(), COUPON_ID.toString());

        Mockito.verify(mockCouponRepository).findById(COUPON_ID);

        Mockito.verifyNoMoreInteractions(mockCouponRepository);
    }

    @Test
    void getByIdTest_successful() {
        Mockito.doReturn(Optional.ofNullable(SAVED_COUPON_1)).when(mockCouponRepository).findById(COUPON_ID);
        Mockito.doReturn(COUPON_RESPONSE_DTO_1).when(mockCouponMapper).mapToCouponResponse(SAVED_COUPON_1);

        final var expected = couponServiceImpl.getCouponResponseById(COUPON_ID);

        Assertions.assertEquals(expected, COUPON_RESPONSE_DTO_1);

        Mockito.verify(mockCouponRepository).findById(COUPON_ID);
        Mockito.verify(mockCouponMapper).mapToCouponResponse(SAVED_COUPON_1);

        Mockito.verifyNoMoreInteractions(mockCouponRepository);
        Mockito.verifyNoMoreInteractions(mockCouponMapper);
    }

    @Test
    void getCouponsByUserIdTest_successful() {
        final var coupons = List.of(SAVED_COUPON_1);

        final var actual = List.of(COUPON_RESPONSE_DTO_1);

        Mockito.doReturn(coupons).when(mockCouponRepository).findCouponsByUsersId(USER_ID);
        Mockito.doReturn(COUPON_RESPONSE_DTO_1).when(mockCouponMapper).mapToCouponResponse(SAVED_COUPON_1);

        final var expected = couponServiceImpl.getCouponsByUserId(USER_ID);

        Assertions.assertEquals(expected, actual);

        Mockito.verify(mockCouponRepository).findCouponsByUsersId(USER_ID);
        Mockito.verify(mockCouponMapper).mapToCouponResponse(SAVED_COUPON_1);
        Mockito.verifyNoMoreInteractions(mockCouponRepository);
        Mockito.verifyNoMoreInteractions(mockCouponMapper);
    }

    @Test
    void getCouponsByCompanyIdTest_successful() {
        final var coupons = List.of(SAVED_COUPON_1, COUPON_2);

        final var actual = List.of(COUPON_RESPONSE_DTO_1, COUPON_RESPONSE_DTO_2);

        Mockito.doReturn(coupons).when(mockCouponRepository).findCouponsByCompanyId(COMPANY_ID);
        Mockito.doReturn(COUPON_RESPONSE_DTO_1).when(mockCouponMapper).mapToCouponResponse(SAVED_COUPON_1);
        Mockito.doReturn(COUPON_RESPONSE_DTO_2).when(mockCouponMapper).mapToCouponResponse(COUPON_2);

        final var expected = couponServiceImpl.getCouponsByCompanyId(COMPANY_ID);

        Assertions.assertEquals(expected, actual);

        Mockito.verify(mockCouponRepository).findCouponsByCompanyId(COMPANY_ID);
        Mockito.verify(mockCouponMapper).mapToCouponResponse(SAVED_COUPON_1);
        Mockito.verify(mockCouponMapper).mapToCouponResponse(COUPON_2);
        Mockito.verifyNoMoreInteractions(mockCouponRepository);
        Mockito.verifyNoMoreInteractions(mockCouponMapper);
    }
}
