package org.example.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "socks")
@Data
public class Socks {
    @Id
    @GeneratedValue
    private Long id;

    private String colour;
    private int cottonPart;
    @Column(name = "quantity")
    private int quantity;
    private Timestamp date;
    @Column(name = "type")
    private typeOperation typeOperation;

    public Socks() {
    }

    public Socks(String colour, int quantity, int cottonPart) {
        this.colour = colour;
        this.cottonPart = cottonPart;
        this.quantity = quantity;
    }
}
