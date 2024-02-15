package by.itacademy.environment.feignClients;

import by.itacademy.environment.dto.response.AddressResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import static by.itacademy.environment.util.Constants.*;

@FeignClient(value = FEIGN_CLIENT_NAME, url = ADDRESSES_URL_PROPERTIES)
public interface CompanyAddressClient {

    @GetMapping(path = GET_ADDRESSES_URL)
    List<AddressResponseDto> getCompanyAddress(@PathVariable(value = NAME) String companyName);
}
