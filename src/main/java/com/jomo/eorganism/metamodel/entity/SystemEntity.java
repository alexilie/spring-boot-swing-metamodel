package com.jomo.eorganism.metamodel.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "system")
//@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
//@Setter
public class SystemEntity extends BaseEntity {

    private String name;
    private String type;
    private String description;
    private String status;
    private String inventoryName;
    private String shortName;
    private String longName;
    private String code;
    private String classification;

      @Override
    public String toString() {
        return "SystemEntity{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", inventoryName='" + inventoryName + '\'' +
                ", shortName='" + shortName + '\'' +
                ", longName='" + longName + '\'' +
                ", code='" + code + '\'' +
                ", classification='" + classification + '\'' +
                '}';
    }
}
