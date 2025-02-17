package com.rescureSync.ResucueSync.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name="FIRST_NAME")
    private String firstName;

    @Column(name="LAST_NAME")
    private String lastName;

    @Column(name="EMAIL_ID")
    private String email;

    @Column(name="PASSWORD")
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> contact;

    @Column(name = "REGISTERED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registeredDate;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Admin admin;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private GeneralUser generalUser;


}
