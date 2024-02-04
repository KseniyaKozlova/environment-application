package by.itacademy.dto.response;

import by.itacademy.enums.TareCategory;
import lombok.Data;

import java.util.UUID;

@Data
public class TareResponseDto {

    private UUID id;
    private TareCategory tareCategory;
    private Double litresVolume;
    private Integer bonusesToAccounting;
}
