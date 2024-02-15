package by.itacademy.entities;

import by.itacademy.util.Constants;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.UUID;

import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.FetchType.LAZY;

@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = Constants.COUPON)
public class Coupon {

    @Id
    @Column(name = Constants.ID_COLUMN)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = Constants.DESCRIPTION, nullable = false)
    private String description;

    @Column(name = Constants.COST, nullable = false)
    private Integer cost;

    @ManyToOne(optional = false, cascade = {MERGE}, fetch = LAZY)
    @JoinColumn(name = Constants.FK_COMPANY_ID_COLUMN)
    private Company company;

    @ManyToMany(mappedBy = Constants.COUPONS_REFERENCE)
    private List<User> users;
}
