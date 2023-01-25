package org.example.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity(name = "socks_table")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public int getCottonPart() {
        return cottonPart;
    }

    public void setCottonPart(int cottonPart) {
        this.cottonPart = cottonPart;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public org.example.model.typeOperation getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(org.example.model.typeOperation typeOperation) {
        this.typeOperation = typeOperation;
    }

    @Override
    public String toString() {
        return "Socks{" +
                "id=" + id +
                ", colour='" + colour + '\'' +
                ", cottonPart=" + cottonPart +
                ", quantity=" + quantity +
                ", date=" + date +
                ", typeOperation=" + typeOperation +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Socks socks = (Socks) o;
        return cottonPart == socks.cottonPart && quantity == socks.quantity && Objects.equals(id, socks.id) && Objects.equals(colour, socks.colour) && Objects.equals(date, socks.date) && typeOperation == socks.typeOperation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, colour, cottonPart, quantity, date, typeOperation);
    }
}
