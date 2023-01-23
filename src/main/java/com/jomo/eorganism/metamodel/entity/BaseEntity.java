package com.jomo.eorganism.metamodel.entity;

import lombok.Getter;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@Getter
//@Setter
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "BaseEntity{" + '\'' +
                "id=" + id + '\'' +
                '}';
    }
}
