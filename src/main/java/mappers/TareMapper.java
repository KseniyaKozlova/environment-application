package mappers;

import entities.Tare;
import enums.TareCategory;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

import static util.Constants.*;

public class TareMapper {

    private static TareMapper tareMapper;

    private TareMapper() {
    }

    public static TareMapper getInstance() {
        return Objects.requireNonNullElseGet(tareMapper, () -> tareMapper = new TareMapper());
    }

    public Tare build(HttpServletRequest request) {
        return Tare.builder()
                .tareCategory(TareCategory.valueOf(request.getParameter(TARE_CATEGORY)))
                .litresVolume(Double.valueOf(request.getParameter(TARE_VOLUME)))
                .bonusesToAccounting(Integer.valueOf(request.getParameter(BONUSES_TO_ACCOUNTING)))
                .build();
    }
}
