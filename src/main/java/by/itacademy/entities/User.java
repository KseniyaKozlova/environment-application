package by.itacademy.entities;

import by.itacademy.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.UUID;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.EnumType.STRING;

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
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "LOGIN", nullable = false)
    private String login;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "BONUSES")
    private Integer bonuses;

    @Enumerated(STRING)
    @Column(name = "ROLE", nullable = false)
    private Role role;

    @ManyToMany(cascade = ALL)
    @JoinTable(name = "PERSON_COUPON",
            joinColumns = @JoinColumn(name = "PERSON_ID"),
            inverseJoinColumns = @JoinColumn(name = "COUPON_ID"))
    @ToString.Exclude //TODO deleteUser
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
    @ToString.Exclude // TODO deleteUser
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
