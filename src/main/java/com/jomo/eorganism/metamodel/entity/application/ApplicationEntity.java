package com.jomo.eorganism.metamodel.entity.application;

import com.jomo.eorganism.metamodel.entity.base.BaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "application")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ApplicationEntity extends BaseEntity {

    private String name;
    private String type;
    private String description;

    @Override
    public String toString() {
        return name + " " +
                type + "/" +
                description;
    }
}
