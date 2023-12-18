package com.labs.lab48.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
@Table(name = "users")
@SQLDelete(sql = "UPDATE users SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class User extends BaseEntityAudit implements Serializable {
    @NotBlank(message = "Can`t be blank")
    @Size(min = 3, max = 20)
    @Column(name = "name")
    private String name;
    @NotBlank(message = "Can`t be blank")
    @Size(min = 3, max = 20)
    @Column(name = "surname")
    private String surname;
    @Column(name = "email")
    @Email
    @NotBlank(message = "Can`t be blank")
    private String email;
    @OneToMany(mappedBy = "user")
    private List<Contract> contracts;
    @OneToMany(mappedBy = "user")
    private List<Limit> limits;
}
