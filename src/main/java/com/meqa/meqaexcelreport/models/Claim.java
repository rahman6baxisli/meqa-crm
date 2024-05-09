package com.meqa.meqaexcelreport.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@Table(name = "claims")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Claim {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "claim_id_seq")
    @SequenceGenerator(name = "claim_id_seq", sequenceName = "claim_id_seq",  allocationSize=1)
    private Integer id;
    private String code;
    private String insuranceClass;
    private String insurancePolicy;
    private Date beginAt;
    private Date startAt;
    private Date endAt;
    private Float amount;
    private Float cost;
    private Float paid;
    private Float commission;
    private Float forceCost;
    private String terminateDate;
    private Float refundCost;
    private String name;
    private Integer maxHedd;
    private Integer firstGroupPremia;
    private Integer secondGroupPremia;
    private Integer thirdGroupPremia;
    private Integer otherGroupPremia;
    private Integer paidGroupPremia;
    private Integer firstGroupCommission;
    private Integer secondGroupCommission;
    private Integer thirdGroupCommission;
    private Integer otherGroupCommission;
    private String tsTerminateDate;
    private Integer parentPolicyId;
    private String tekrarSigortaSlipininNomresi;
    private String tekrarSigortaMuqavilesininBaglanmaTarixi;
    private String tekrarSigortaMuqavilesininBaslangicTarixi;
    private String tekrarSigortaMuqavilesininSonTarixi;
}
