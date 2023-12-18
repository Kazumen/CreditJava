package com.labs.lab48.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contracts")
@SQLDelete(sql = "UPDATE contracts SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Contract extends BaseEntityAudit implements Serializable {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "credit_id", referencedColumnName = "id")
    private Credit credit;
    @Column(name = "repayment")
    private Double repayment;
    @Column(name="opened")
    private Boolean opened;

    public Integer getUserId(){
        return user.getId();
    }
    public Integer getCreditId(){
        return credit.getId();
    }


}
