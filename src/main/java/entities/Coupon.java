package entities;

import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "COUPON")
public class Coupon {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "COST")
    private Integer cost;

    /**
     * company can't be null
     */
    @ManyToOne(optional = false, cascade = ALL, fetch = LAZY)
    @JoinColumn(name = "COMPANY_ID")
    @ToString.Exclude
    private Company company;

    @ManyToMany(mappedBy = "coupons")
    @ToString.Exclude
    private List<User> users;

    public Coupon(final String description, final Integer cost, final Company company) {
        this.description = description;
        this.cost = cost;
        this.company = company;
    }
}
