package entities;

import enums.TareCategory;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "TARE")
public class Tare {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "TARE_CATEGORY")
    private TareCategory tareCategory;

    @Column(name = "LITRES_VOLUME")
    private Double litresVolume;

    @Column(name = "BONUSES_TO_ACCOUNTING")
    private Integer bonusesToAccounting;

    /**
     * user can be null, if he was deleted
     */
//    @ManyToOne() //TODO : optional?
//    @JoinColumn(name = "PERSON_ID")
    @ManyToMany(mappedBy = "tares")
    @ToString.Exclude
    private List<User> users;

    public Tare(final TareCategory tareCategory, final Double litresVolume, final Integer bonusesToAccounting) {
        this.tareCategory = tareCategory;
        this.litresVolume = litresVolume;
        this.bonusesToAccounting = bonusesToAccounting;
    }
}
