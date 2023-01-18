package com.jomo.eorganism.metamodel.entity.component;

import com.jomo.eorganism.metamodel.entity.base.BaseEntity;
import com.jomo.eorganism.metamodel.entity.application.ApplicationEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
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
//@Setter
public class ComponentEntity extends BaseEntity {

    private String correlationId;

    @ManyToOne
    @JoinColumn(name = "application_id")
    private ApplicationEntity application;

    @Override
    public String toString() {
         return "ComponentEntity" +
                ", correlationId='" + correlationId + '\'' +
                super.toString();
    }
}
