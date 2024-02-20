package by.itacademy.environment.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.UUID;

import static by.itacademy.environment.util.Constants.*;
import static jakarta.persistence.CascadeType.ALL;

@Data
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = COMPANY)
public class Company {

    @Id
    @Column(name = ID_COLUMN)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = COMPANY_NAME, nullable = false)
    private String companyName;

    @Column(name = DETAILS, nullable = false)
    private String details;

    @OneToMany(mappedBy = COMPANY_REFERENCE, orphanRemoval = true, cascade = ALL)
    private List<Coupon> coupons;

    public void addCoupon(final Coupon coupon) {
        this.coupons.add(coupon);
        coupon.setCompany(this);
    }
}
