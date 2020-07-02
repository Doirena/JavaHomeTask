package com.dovile.springbootrest.springbootrest.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.List;


@Entity
@Table(name = "owner")
@NamedQueries({
        @NamedQuery(name = "Owner.findBYName", query = "SELECT o FROM Owner o WHERE o.name =: owner")})

public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private List<BuildingRecords> buildingRecords;

    public Owner() {
    }

    public Owner(Integer id) {
        this.id = id;
    }

    public Owner(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BuildingRecords> getBuildingRecords() {
        return buildingRecords;
    }

    public void setBuildingRecords(List<BuildingRecords> buildingRecords) {
        this.buildingRecords = buildingRecords;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", name='" + name +
                '}';
    }
}
