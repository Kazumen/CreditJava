package com.labs.lab48.entity;

import jakarta.persistence.*;
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
@Table(name = "limits")
@SQLDelete(sql = "UPDATE limits SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Limit  extends BaseEntityAudit implements Serializable {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bank_id", referencedColumnName = "id")
    private Bank bank;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @Column(name = "max_limit")
    private Double maxLimit;
    @Column(name = "current_limit")
    private Double currentLimit;
    public Integer getUserId(){
        return user.getId();
    }
    public Integer getBankId(){
        return bank.getId();
    }
}
