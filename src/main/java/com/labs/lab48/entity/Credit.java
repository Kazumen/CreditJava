package com.labs.lab48.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "credits")
@SQLDelete(sql = "UPDATE credits SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Credit extends BaseEntityAudit implements Serializable {
    @NotBlank(message = "Can`t be blank")
    @Size(min = 3, max = 20)
    @Column(name = "name")
    private String name;
    @NotNull
    @Column(name = "max_sum")
    private Double maxSum;
    @NotNull
    @Column(name = "commission")
    private Double commission;
    @NotNull
    @Column(name = "contract_term")
    private Long contractTerm;
    @ManyToOne
    @NotNull
    @JoinColumn(name = "bank_id",nullable = false)
    private Bank bank;
    @OneToMany(mappedBy = "credit")
    private List<Contract> contracts;
public String getBankName(){
    return bank.getName();
}
}
