package com.rescureSync.ResucueSync.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="Admin")
@Data
public class Admin {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name="ADMIN_ID")
    private Long id;

    @OneToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;
}
