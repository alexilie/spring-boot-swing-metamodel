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
@Table(name = "release")
//@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
//@Setter
public class ReleaseEntity extends BaseEntity  {
    private String uuid;
    private Long metadataId;
    private Long lastUpdatedUserId;
    private Long lastUpdatedApplicationId;

    private String name;
    private String type;
    private String description;
    private String status;
    private Date releaseDate;

    private String eorganismName;
    private String metadataName;
    private String metadataType;

    private String lastUpdatedUserName;
    private String lastUpdatedApplicationName;
    private Date createdDate;
    private Date lastUpdatedDate;

    public Long getMetadataId() {
        return metadataId;
    }

    public void setMetadataId(Long metadataId) {
        this.metadataId = metadataId;
    }

    public Long getLastUpdatedUserId() {
        return lastUpdatedUserId;
    }

    public void setLastUpdatedUserId(Long lastUpdatedUserId) {
        this.lastUpdatedUserId = lastUpdatedUserId;
    }

    public Long getLastUpdatedApplicationId() {
        return lastUpdatedApplicationId;
    }

    public void setLastUpdatedApplicationId(Long lastUpdatedApplicationId) {
        this.lastUpdatedApplicationId = lastUpdatedApplicationId;
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

    public String getMetadataName() {
        return metadataName;
    }

    public void setMetadataName(String metadataName) {
        this.metadataName = metadataName;
    }

    public String getMetadataType() {
        return metadataType;
    }

    public void setMetadataType(String metadataType) {
        this.metadataType = metadataType;
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

    public String getStatus() {
        return status;
    }

    protected void setStatus(String status) {
        this.status = status;
    }

    protected Date getReleaseDate() {
        return releaseDate;
    }

    protected void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
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

    public String getEorganismName() {
        return eorganismName;
    }

    public void setEorganismName(String eorganismName) {
        this.eorganismName = eorganismName;
    }

    public ReleaseEntity(String name, String type, String description) {
        this.uuid        = MetamodelUtil.getUuidRandomString();
        this.metadataType = ApplicationConfiguration.RELEASE;
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
        return "ReleaseEntity{" +
                super.toString() + '\'' +
                "uuid=" + uuid +
                "metadataId=" + metadataId +
                ", lastUpdatedUserId=" + lastUpdatedUserId +
                ", lastUpdatedApplicationId=" + lastUpdatedApplicationId +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", releaseDate=" + releaseDate +
                ", eorganismName='" + eorganismName + '\'' +
                ", metadataName='" + metadataName + '\'' +
                ", metadataType='" + metadataType + '\'' +
                ", lastUpdatedUserName='" + lastUpdatedUserName + '\'' +
                ", lastUpdatedApplicationName='" + lastUpdatedApplicationName + '\'' +
                ", createdDate=" + createdDate +
                ", lastUpdatedDate=" + lastUpdatedDate +
                '}';
    }
}
