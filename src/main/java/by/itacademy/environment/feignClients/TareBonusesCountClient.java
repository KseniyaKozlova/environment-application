package by.itacademy.environment.feignClients;

import by.itacademy.environment.dto.response.BonusesCountResponseDto;
import by.itacademy.environment.enums.TareCategory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

import static by.itacademy.environment.util.Constants.*;

/**
 * Receive Company addresses from Tare API
 */

@FeignClient(name = BONUSES_FEIGN_CLIENT_NAME, url = BONUSES_URL_PROPERTIES)
public interface TareBonusesCountClient {

    /**
     * Get bonuses count for deposited tare
     *
     * @param category type of tare category
     * @param volume   of tare
     * @return bonuses count in the response format
     */
    @GetMapping(path = GET_BONUSES_COUNT_URL)
    BonusesCountResponseDto getBonusesCount(@PathVariable(value = VOLUME) BigDecimal volume,
                                            @PathVariable(value = CATEGORY) TareCategory category);

}
