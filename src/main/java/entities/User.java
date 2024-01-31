package entities;

import enums.Role;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.EnumType.STRING;

@Data
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "PERSON")
public class User {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column(name = "LOGIN")
    private String login;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "NAME")
    private String name;

    @Column(name = "BONUSES")
    private Integer bonuses;

    @Enumerated(STRING)
    @Column(name = "ROLE")
    private Role role;

    @ManyToMany(cascade = ALL)
    @JoinTable(name = "PERSON_COUPON",
            joinColumns = @JoinColumn(name = "PERSON_ID"),
            inverseJoinColumns = @JoinColumn(name = "COUPON_ID"))
    @ToString.Exclude
    private List<Coupon> coupons;

    /**
     * if user is deleted,
     * tare is stay in phandomate
     */
    //TODO user is not deleted, user is detached
//    @OneToMany(mappedBy = "user")
    @ManyToMany(cascade = ALL)
    @JoinTable(name = "PERSON_TARE",
            joinColumns = @JoinColumn(name = "PERSON_ID"),
            inverseJoinColumns = @JoinColumn(name = "TARE_ID"))
    @ToString.Exclude
    private List<Tare> tares;


    public User(final String login, final String password, final String name, final int bonuses, final Role role) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.bonuses = bonuses;
        this.role = role;
    }

    public void addTare(final Tare tare) {
        this.tares.add(tare);
        tare.getUsers().add(this);
//        this.bonuses += tare.getBonusesToAccounting();
    }

//    public void removeTare(final UUID tareId) {
//        final Tare tare = this.tares.stream().filter(userTare -> userTare.getId() == tareId).findFirst().orElse(null);
//        if (tare != null) {
//            this.tares.remove(tare);
//            tare.getUsers().remove(this);
//        }
//    }

    public void addCoupon(final Coupon coupon) {
        this.coupons.add(coupon);
        coupon.getUsers().add(this);
//        this.bonuses -= coupon.getCost();
    }

    public void removeCoupon(final Coupon coupon) {
//        final Coupon coupon = this.coupons.stream().filter(userCoupon -> userCoupon.getId() == couponId).findFirst().orElse(null);
//        if (coupon != null) {
            this.coupons.remove(coupon);
            coupon.getUsers().remove(this);
//        }
    }
}
