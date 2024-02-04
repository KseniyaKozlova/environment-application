package by.itacademy.dto.request;

import by.itacademy.enums.TareCategory;
import lombok.Data;

@Data
public class CreateTareRequestDto {

    private TareCategory tareCategory;
    private Double litresVolume;
    private Integer bonusesToAccounting;
}
