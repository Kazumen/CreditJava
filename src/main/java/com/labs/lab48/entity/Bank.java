package com.labs.lab48.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
@Table(name = "banks")
@SQLDelete(sql = "UPDATE banks SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Bank extends BaseEntityAudit implements Serializable {
    @NotBlank(message = "Can`t be blank")
    @Size(min = 3, max = 20)
    @Column(name = "name")
    private String name;
    @Column(name = "owner")
    @NotBlank(message = "Can`t be blank")
    private String owner;
    @Column(name = "address")
    @NotBlank(message = "Can`t be blank")
    private String address;
    @Column(name = "website")
    @NotBlank(message = "Can`t be blank")
    private String website;
    @OneToMany(mappedBy = "bank")
    private List<Credit> credits;
    @OneToMany(mappedBy = "bank")
    private List<Limit> limits;
    @Column(name = "max_limit")
    private Double maxLimit;
}
