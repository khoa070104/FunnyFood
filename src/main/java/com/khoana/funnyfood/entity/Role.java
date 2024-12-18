package com.khoana.funnyfood.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "rolename")
    private String roleName;

    @Column(name = "create_date")
    private LocalDateTime createDate;
}
