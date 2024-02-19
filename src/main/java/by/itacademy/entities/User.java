package by.itacademy.entities;

import by.itacademy.enums.Role;
import by.itacademy.exceptions.InsufficientBonusesException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.UUID;

import static by.itacademy.util.Constants.*;
import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.EnumType.STRING;

@Data
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = PERSON)
public class User {

    @Id
    @Column(name = ID_COLUMN)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = LOGIN, nullable = false, unique = true)
    private String login;

    @Column(name = PASSWORD, nullable = false)
    private String password;

    @Column(name = NAME_COLUMN, nullable = false)
    private String name;

    @Column(name = BONUSES, nullable = false)
    private Integer bonuses;

    @Enumerated(STRING)
    @Column(name = ROLE, nullable = false)
    private Role role;

    @ManyToMany(cascade = MERGE)
    @JoinTable(name = PERSON_COUPON,
            joinColumns = @JoinColumn(name = FK_PERSON_ID_COLUMN),
            inverseJoinColumns = @JoinColumn(name = FK_COUPON_ID_COLUMN))
    private List<Coupon> coupons;

    @ManyToMany(cascade = MERGE)
    @JoinTable(name = PERSON_TARE,
            joinColumns = @JoinColumn(name = FK_PERSON_ID_COLUMN),
            inverseJoinColumns = @JoinColumn(name = FK_TARE_ID_COLUMN))
    private List<Tare> tares;

    public void addTare(final Tare tare) {
        this.tares.add(tare);
        tare.getUsers().add(this);
        this.bonuses += tare.getBonusesToAccounting();
    }

    public void addCoupon(final Coupon coupon) {
        final boolean isBonusesEnough = (this.bonuses - coupon.getCost()) >= 0;

        if (isBonusesEnough) {
            this.coupons.add(coupon);
            coupon.getUsers().add(this);
            this.bonuses -= coupon.getCost();
        } else {
            throw new InsufficientBonusesException(coupon.getId().toString());
        }
    }

    public void removeCoupon(final Coupon coupon) {
        this.bonuses += coupon.getCost();
        this.coupons.remove(coupon);
        coupon.getUsers().remove(this);
    }

    public void useCoupon(final Coupon coupon) {
        this.coupons.remove(coupon);
        coupon.getUsers().remove(this);
    }
}
