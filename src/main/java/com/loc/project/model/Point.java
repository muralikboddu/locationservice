package com.loc.project.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="Point")
@NamedQuery(name="Point.findAll" , query = "SELECT p from Point p")
public class Point implements Serializable {
    @Id
   /* @Column(name="id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )*/
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name="street_address")
    private String streetAddress;

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public double getxCor() {
        return xCor;
    }

    public void setxCor(double xCor) {
        this.xCor = xCor;
    }

    public double getyCor() {
        return yCor;
    }

    public void setyCor(double yCor) {
        this.yCor = yCor;
    }

    @Column(name="x_cor")
    private double xCor;

    @Column(name="y_cor")
    private double yCor;

    public void displayPoint()
    {
        System.out.println("(" + xCor + "," + yCor + ")");
    }

    public Point() {

    }
    public Point( double xCor, double yCor ,String streetAddress) {
        this.streetAddress = streetAddress;
        this.xCor = xCor;
        this.yCor = yCor;
    }

    public double getDistance(Point point) {
        final double dx = getxCor() - point.getxCor();
        final double dy = getyCor() - point.getyCor();

        return Math.sqrt(dx*dx + dy*dy);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof Point)) {
            return false;
        }
        return (xCor == ((Point) o).xCor && yCor == ((Point) o).yCor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(xCor, yCor, streetAddress);
    }
}
