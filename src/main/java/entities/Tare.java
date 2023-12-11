package entities;

import enums.TareCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tare {

    private UUID id;
    private TareCategory tareCategory;
    private Double litresVolume;
    private Integer bonusesToAccounting;
}
