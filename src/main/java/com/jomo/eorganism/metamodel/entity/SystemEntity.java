package com.jomo.eorganism.metamodel.entity;

import com.jomo.eorganism.metamodel.config.ApplicationConfiguration;
import com.jomo.eorganism.metamodel.util.MetamodelUtil;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "system")
//@AllArgsConstructor
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
//@Setter
public class SystemEntity extends BaseEntity {
    private String uuid;
    private Long domainId;
    private Long metadataId;
    private Long lastUpdatedUserId;
    private Long lastUpdatedApplicationId;

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
    private String segmentName;
    private String metadataName;
    private String metadataType;

    private String lastUpdatedUserName;
    private String lastUpdatedApplicationName;
    private Date createdDate;
    private Date lastUpdatedDate;

    public Long getDomainId() {
        return domainId;
    }

    public void setDomainId(Long domainId) {
        this.domainId = domainId;
    }

    protected Date getCreatedDate() {
        return createdDate;
    }

    protected void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    protected Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    protected void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLastUpdatedUserName() {
        return lastUpdatedUserName;
    }

    public void setLastUpdatedUserName(String lastUpdatedUserName) {
        this.lastUpdatedUserName = lastUpdatedUserName;
    }

    public String getLastUpdatedApplicationName() {
        return lastUpdatedApplicationName;
    }

    public void setLastUpdatedApplicationName(String lastUpdatedApplicationName) {
        this.lastUpdatedApplicationName = lastUpdatedApplicationName;
    }

    public String getMetadataType() {
        return metadataType;
    }

    public void setMetadataType(String metadataType) {
        this.metadataType = metadataType;
    }

    public SystemEntity() {

    }

    public SystemEntity(String name, String type, String description) {
        this.uuid        = MetamodelUtil.getUuidRandomString();
        this.metadataType = ApplicationConfiguration.SYSTEM;
        this.lastUpdatedApplicationName = "Metamodel Spring Boot";
        this.lastUpdatedUserName = "eorganism";
        this.name        = name;
        this.type        = type;
        this.description = description;
        this.createdDate = new java.util.Date();
        this.lastUpdatedDate = new java.util.Date();
    }

    @Override
    public String toString() {
        return "SystemEntity{" +
                super.toString() + '\'' +
                "uuid=" + uuid +
                "domainId=" + domainId +
                ", metadataId=" + metadataId +
                ", lastUpdatedUserId=" + lastUpdatedUserId +
                ", lastUpdatedApplicationId=" + lastUpdatedApplicationId +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", inventoryName='" + inventoryName + '\'' +
                ", shortName='" + shortName + '\'' +
                ", longName='" + longName + '\'' +
                ", code='" + code + '\'' +
                ", classification='" + classification + '\'' +
                ", eorganismName='" + eorganismName + '\'' +
                ", segmentName='" + segmentName + '\'' +
                ", metadataName='" + metadataName + '\'' +
                ", metadataType='" + metadataType + '\'' +
                ", lastUpdatedUserName='" + lastUpdatedUserName + '\'' +
                ", lastUpdatedApplicationName='" + lastUpdatedApplicationName + '\'' +
                ", createdDate=" + createdDate +
                ", lastUpdatedDate=" + lastUpdatedDate +
                '}';
    }
}
