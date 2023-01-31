package ru.geekbrains.Market.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "roles")
@Data
public class Role extends BaseEntity{
    @Column(name = "name")
    private String name;
}
