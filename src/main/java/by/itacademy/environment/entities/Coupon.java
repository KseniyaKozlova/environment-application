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
import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.FetchType.LAZY;

@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = COUPON)
public class Coupon {

    @Id
    @Column(name = ID_COLUMN)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = DESCRIPTION, nullable = false)
    private String description;

    @Column(name = COST, nullable = false)
    private Integer cost;

    @ManyToOne(optional = false, cascade = {MERGE}, fetch = LAZY)
    @JoinColumn(name = FK_COMPANY_ID_COLUMN)
    private Company company;

    @ManyToMany(mappedBy = COUPONS_REFERENCE)
    private List<User> users;
}
