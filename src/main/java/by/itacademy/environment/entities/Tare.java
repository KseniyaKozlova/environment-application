package by.itacademy.environment.entities;

import by.itacademy.environment.enums.TareCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.UUID;

import static by.itacademy.environment.util.Constants.*;
import static jakarta.persistence.EnumType.STRING;

@Data
@Accessors(chain = true)
@Builder
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
    private Double litresVolume;

    @Column(name = BONUSES_TO_ACCOUNTING, nullable = false)
    private Integer bonusesToAccounting;

    @ManyToMany(mappedBy = TARES_FIELD_NAME)
    private List<User> users;
}
