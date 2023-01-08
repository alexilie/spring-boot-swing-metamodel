package com.jomo.eorganism.metamodel.entity.component;

import com.jomo.eorganism.metamodel.entity.base.BaseEntity;
import com.jomo.eorganism.metamodel.entity.application.ApplicationEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "component")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ComponentEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "application_id")
    private ApplicationEntity application;

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
