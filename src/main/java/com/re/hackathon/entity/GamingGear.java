package com.re.hackathon.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "gaming_gears")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GamingGear {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String productName;
    private String serialCode;
    private Double price;
    @Enumerated(EnumType.STRING)
    private Type type;
    private boolean isDelete;
}
