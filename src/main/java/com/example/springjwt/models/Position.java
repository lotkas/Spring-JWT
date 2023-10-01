package com.example.springjwt.models;

import javax.persistence.*;

@Entity
@Table
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String positionName;

    public Position() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    @Override
    public String toString() {
        return "Position{" +
                "id=" + id +
                ", positionName='" + positionName + '\'' +
                '}';
    }
}

