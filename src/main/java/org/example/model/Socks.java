package org.example.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity(name = "socks_table")
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
