package by.itacademy.environment.entities;

import by.itacademy.environment.enums.TareCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.UUID;

import static by.itacademy.environment.util.Constants.*;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;

@Data
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = TARE)
public class Tare {

    @Id
    @Column(name = ID_COLUMN)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(value = STRING)
    @Column(name = TARE_CATEGORY, nullable = false)
    private TareCategory tareCategory;

    @Column(name = LITRES_VOLUME, nullable = false)
    private BigDecimal litresVolume;

    @Column(name = ACCOUNTING_BONUSES_COUNT, nullable = false)
    private Integer accountingBonusesCount;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = FK_PERSON_ID_COLUMN)
    private User user;
}
