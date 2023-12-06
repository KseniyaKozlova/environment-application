package mapper;

import entity.Tare;
import enums.TareCategory;

import javax.servlet.http.HttpServletRequest;

import static util.Constants.*;

public class TareMapper {

    public Tare build(HttpServletRequest request) {
        return Tare.builder()
                .tareCategory(TareCategory.valueOf(request.getParameter(TARE_CATEGORY)))
                .litresVolume(Double.valueOf(request.getParameter(TARE_VOLUME)))
                .bonusesToAccounting(Integer.valueOf(request.getParameter(BONUSES_TO_ACCOUNTING)))
                .build();
    }
}
