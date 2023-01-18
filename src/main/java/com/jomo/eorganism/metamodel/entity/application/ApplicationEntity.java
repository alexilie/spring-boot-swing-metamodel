package com.jomo.eorganism.metamodel.entity.application;

import com.jomo.eorganism.metamodel.entity.base.BaseEntity;
import com.jomo.eorganism.metamodel.entity.system.SystemEntity;
import com.jomo.eorganism.metamodel.entity.domain.DomainEntity;
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
@Table(name = "application")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
//@Setter
public class ApplicationEntity extends BaseEntity {

    private String correlationId;

    @ManyToOne
    @JoinColumn(name = "system_id")
    private SystemEntity system;

    @ManyToOne
    @JoinColumn(name = "domain_id")
    private DomainEntity domain;

    @Override
    public String toString() {
        return "ApplicationEntity" +
                ", correlationId='" + correlationId + '\'' +
                 super.toString();
    }
}
