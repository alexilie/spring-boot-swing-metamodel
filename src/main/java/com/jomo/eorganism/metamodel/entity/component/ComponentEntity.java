package com.jomo.eorganism.metamodel.entity.component;

import com.jomo.eorganism.metamodel.entity.base.BaseEntity;
import com.jomo.eorganism.metamodel.entity.application.ApplicationEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "component")
//@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
//@Setter
public class ComponentEntity extends BaseEntity {

    private Long applicationId;
    private Long eorganismId;
    private Long metadataId;
    private Long lastUpdatedUserId;
    private Long lastUpdatedApplicationId;

    private String applicationName;

    private String name;
    private String type;
    private String description;
    private String status;
    private String inventoryName;
    private String shortName;
    private String longName;
    private String code;
    private String classification;

    private String eorganismName;
    private String taxonomy;
    private String topology;
    private String graphPath;

    private String metadataName;
    private String metadataType;
    private String serviceCode;
    private String version;

    private String lastUpdatedUserName;
    private String lastUpdatedApplicationName;

    private Date createdDate;
    private Date lastUpdatedDate;

    protected Date getCreatedDate() {
        return createdDate;
    }

    protected Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    @Override
    public String toString() {
        return "ComponentEntity{" +
                "applicationId=" + applicationId +
                ", eorganismId=" + eorganismId +
                ", metadataId=" + metadataId +
                ", lastUpdatedUserId=" + lastUpdatedUserId +
                ", lastUpdatedApplicationId=" + lastUpdatedApplicationId +
                ", applicationName='" + applicationName + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", inventoryName='" + inventoryName + '\'' +
                ", shortName='" + shortName + '\'' +
                ", longName='" + longName + '\'' +
                ", code='" + code + '\'' +
                ", classification='" + classification + '\'' +
                ", eorganismName=" + eorganismName +
                ", taxonomy='" + taxonomy + '\'' +
                ", topology='" + topology + '\'' +
                ", graphPath='" + graphPath + '\'' +
                ", metadataName='" + metadataName + '\'' +
                ", metadataType='" + metadataType + '\'' +
                ", serviceCode='" + serviceCode + '\'' +
                ", version='" + version + '\'' +
                ", lastUpdatedUserName='" + lastUpdatedUserName + '\'' +
                ", lastUpdatedApplicationName='" + lastUpdatedApplicationName + '\'' +
                ", createdDate=" + createdDate +
                ", lastUpdatedDate=" + lastUpdatedDate +
                '}';
    }
}
