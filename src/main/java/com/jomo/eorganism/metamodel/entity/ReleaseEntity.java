package com.jomo.eorganism.metamodel.entity;

import com.jomo.eorganism.metamodel.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "release")
//@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
//@Setter
public class ReleaseEntity extends BaseEntity  {

    private String name;
    private String type;
    private String description;
    private String status;
    private Date releaseDate;

    protected Date getReleaseDate() {
        return releaseDate;
    }

    @Override
    public String toString() {
        return "ReleaseEntity{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", releaseDate=" + releaseDate.toString() +
                '}';
    }
}
