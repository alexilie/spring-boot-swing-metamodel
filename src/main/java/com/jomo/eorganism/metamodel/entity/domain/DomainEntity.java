package com.jomo.eorganism.metamodel.entity.domain;

import com.jomo.eorganism.metamodel.entity.base.BaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "domain")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
//@Setter
public class DomainEntity extends BaseEntity {

    private String correlationId;

    @Override
    public String toString() {
        return "DomainEntity" +
                ", correlationId='" + correlationId + '\'' +
                 super.toString();
    }
}
