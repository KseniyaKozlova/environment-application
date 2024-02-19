package by.itacademy.feignClients;

import by.itacademy.dto.response.AddressResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import static by.itacademy.util.Constants.*;

/**
 * Receive Company addresses from localhost:8081
 */

@FeignClient(value = FEIGN_CLIENT_NAME, url = ADDRESSES_URL_PROPERTIES)
public interface CompanyAddressClient {

    /**
     * Get list with addresses
     *
     * @param companyName name of Company, which addresses are need
     * @return list with addresses in the response format
     */
    @GetMapping(path = GET_ADDRESSES_URL)
    List<AddressResponseDto> getCompanyAddress(@PathVariable(value = NAME) String companyName);
}
