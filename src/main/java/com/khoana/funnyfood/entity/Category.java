package com.khoana.funnyfood.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "namecate")
    private String nameCate;

    @Column(name = "create_date")
    private LocalDateTime createDate;
}
