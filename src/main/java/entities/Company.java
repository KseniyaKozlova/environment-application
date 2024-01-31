package entities;

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
@Table(name = "COMPANY")
public class Company {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column(name = "COMPANY_NAME")
    private String companyName;

    @Column(name = "DETAILS")
    private String details;

    /**
     * if company is deleted,
     * all its coupons also will be deleted
     */
    @OneToMany(mappedBy = "company", orphanRemoval = true)
    @ToString.Exclude
    private List<Coupon> coupons;

    public Company(final String companyName, final String details) {
        this.companyName = companyName;
        this.details = details;
    }
}
