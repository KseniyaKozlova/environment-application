package by.itacademy.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.UUID;

import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.FetchType.LAZY;

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
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "COST")
    private Integer cost;

    /**
     * company can't be null
     */
    @ManyToOne(optional = false, cascade = PERSIST, fetch = LAZY)
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
