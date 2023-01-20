package com.jomo.eorganism.metamodel.entity;

import com.jomo.eorganism.metamodel.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "environment")
//@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
//@Setter
public class EnvironmentEntity extends BaseEntity  {

    private String name;
    private String type;
    private String description;
    private String status;
    private Date startDate;
    private Date endDate;

    protected Date getStartDate() {
        return startDate;
    }

    protected Date getEndDate() {
        return endDate;
    }

    @Override
    public String toString() {
        return "EnvironmentEntity{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", startDate=" + startDate.toString() +
                ", endDate=" + endDate.toString() +
                '}';
    }
}
