package com.jomo.eorganism.metamodel.entity.system;

import com.jomo.eorganism.metamodel.entity.domain.DomainEntity;
import com.jomo.eorganism.metamodel.entity.base.BaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "system")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
//@Setter
public class SystemEntity extends BaseEntity {

    private String correlationId;

    @ManyToOne
    @JoinColumn(name = "domain_id")
    private DomainEntity application;

    @Override
    public String toString() {
        return "SystemEntity" +
                ", correlationId='" + correlationId + '\'' +
                 super.toString();
    }
}
