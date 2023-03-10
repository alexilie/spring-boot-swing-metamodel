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
@Table(name = "application")
//@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
//@Setter
public class ApplicationEntity extends BaseEntity  {
    private String uuid;

    private Long domainId;
    private Long systemId;
    private Long environmentId;
    private Long releaseId;
    private Long applicationProfileId;
    private Long ownerId;
    private Long eorganismId;
    private Long supportGroupId;
    private Long segmentId;
    private Long metadataId;
    private Long businessUnitId;
    private Long eapplicationId;
    private Long lastUpdatedUserId;
    private Long lastUpdatedApplicationId;

    private String environmentName;
    private String releaseName;
    private String domainName;
    private String systemName;

    private String name;
    private String type;
    private String description;
    private String status;
    private String inventoryName;
    private String shortName;
    private String longName;
    private String code;
    private String classification;
    private String ownerName;
    private String supportGroupName;
    private String supportGroupEmail;
    private String eapplicationName;
    private String applicationProfileName;
    private String applicationIdInput;
    private String applicationIdInputName;
    private String applicationIdOutput;
    private String applicationIdOutputName;

    private String eorganismName;
    private String taxonomy;
    private String topology;
    private String graphPath;

    private String segmentName;

    private String metadataName;
    private String metadataType;
    private String serviceCode;
    private String businessUnitName;
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

    protected void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    protected void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public Long getMetadataId() {
        return metadataId;
    }

    public void setMetadataId(Long metadataId) {
        this.metadataId = metadataId;
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

    public ApplicationEntity(String name, String type, String description) {
        this.uuid        = MetamodelUtil.getUuidRandomString();
        this.metadataType = ApplicationConfiguration.APPLICATION;
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
        return "ApplicationEntity{" + '\'' +
                super.toString() + '\'' +
                "uuid=" + uuid +
                "domainId=" + domainId +
                ", systemId=" + systemId +
                ", environmentId=" + environmentId +
                ", releaseId=" + releaseId +
                ", applicationProfileId=" + applicationProfileId +
                ", ownerId=" + ownerId +
                ", eorganismId=" + eorganismId +
                ", supportGroupId=" + supportGroupId +
                ", segmentId=" + segmentId +
                ", metadataId=" + metadataId +
                ", businessUnitId=" + businessUnitId +
                ", eapplicationId=" + eapplicationId +
                ", lastUpdatedUserId=" + lastUpdatedUserId +
                ", environmentName='" + environmentName + '\'' +
                ", releaseName='" + releaseName + '\'' +
                ", domainName='" + domainName + '\'' +
                ", systemName='" + systemName + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", inventoryName='" + inventoryName + '\'' +
                ", shortName='" + shortName + '\'' +
                ", longName='" + longName + '\'' +
                ", code='" + code + '\'' +
                ", classification='" + classification + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", supportGroupName='" + supportGroupName + '\'' +
                ", supportGroupEmail='" + supportGroupEmail + '\'' +
                ", eapplicationName='" + eapplicationName + '\'' +
                ", applicationProfileName='" + applicationProfileName + '\'' +
                ", applicationIdInput='" + applicationIdInput + '\'' +
                ", applicationIdInputName='" + applicationIdInputName + '\'' +
                ", applicationIdOutput='" + applicationIdOutput + '\'' +
                ", applicationIdOutputName='" + applicationIdOutputName + '\'' +
                ", eorganismName='" + eorganismName + '\'' +
                ", taxonomy='" + taxonomy + '\'' +
                ", topology='" + topology + '\'' +
                ", graphPath='" + graphPath + '\'' +
                ", segmentName='" + segmentName + '\'' +
                ", metadataName='" + metadataName + '\'' +
                ", metadataType='" + metadataType + '\'' +
                ", serviceCode='" + serviceCode + '\'' +
                ", businessUnitName='" + businessUnitName + '\'' +
                ", version='" + version + '\'' +
                ", lastUpdatedUserName='" + lastUpdatedUserName + '\'' +
                ", lastUpdatedApplicationName='" + lastUpdatedApplicationName + '\'' +
                ", lastUpdatedApplicationId=" + lastUpdatedApplicationId +
                ", createdDate=" + createdDate +
                ", lastUpdatedDate=" + lastUpdatedDate +
                '}';
    }
}
