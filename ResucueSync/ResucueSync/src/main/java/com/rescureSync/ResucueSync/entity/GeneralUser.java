package com.rescureSync.ResucueSync.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="GeneralUser")
public class GeneralUser {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name="GENERAL_USER_ID")
    private Long id;

    @OneToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;
}
