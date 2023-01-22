package com.jomo.eorganism.metamodel.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "database")
//@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
//@Setter
public class DatabaseEntity extends BaseEntity  {

    private Long domainId;
    private Long systemId;
    private Long environmentId;
    private Long releaseId;
    private Long applicationId;
    private Long ownerId;
    private Long supportGroupId;
    private Long eorganismId;
    private Long segmentId;
    private Long metadataId;
    private Long businessUnitId;
    private Long lastUpdatedUserId;
    private Long lastUpdatedApplicationId;

    private String environmentName;
    private String releaseName;
    private String domainName;
    private String systemName;
    private String applicationName;

    private String name;
    private String type;
    private String description;
    private String vendorName;
    private String databaseVersion;
    private String status;
    private String inventoryName;
    private String shortName;
    private String longName;
    private String code;
    private String classification;
    private String url;

    private String ownerName;
    private String supportGroupName;
    private String supportGroupEmail;

    private String applicationIdInput;
    private String applicationIdInputName;
    private String applicationIdOutput;
    private String applicationIdOutputName;

    private String databaseIdInput;
    private String databaseIdInputName;
    private String databaseIdOutput;
    private String databaseIdOutputName;

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

    @Override
    public String toString() {
        return "DatabaseEntity{" +
                "domainId=" + domainId +
                ", systemId=" + systemId +
                ", environmentId=" + environmentId +
                ", releaseId=" + releaseId +
                ", applicationId=" + applicationId +
                ", ownerId=" + ownerId +
                ", supportGroupId=" + supportGroupId +
                ", eorganismId=" + eorganismId +
                ", segmentId=" + segmentId +
                ", metadataId=" + metadataId +
                ", businessUnitId=" + businessUnitId +
                ", lastUpdatedUserId=" + lastUpdatedUserId +
                ", lastUpdatedApplicationId=" + lastUpdatedApplicationId +
                ", environmentName='" + environmentName + '\'' +
                ", releaseName='" + releaseName + '\'' +
                ", domainName='" + domainName + '\'' +
                ", systemName='" + systemName + '\'' +
                ", applicationName='" + applicationName + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", vendorName='" + vendorName + '\'' +
                ", databaseVersion='" + databaseVersion + '\'' +
                ", status='" + status + '\'' +
                ", inventoryName='" + inventoryName + '\'' +
                ", shortName='" + shortName + '\'' +
                ", longName='" + longName + '\'' +
                ", code='" + code + '\'' +
                ", classification='" + classification + '\'' +
                ", url='" + url + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", supportGroupName='" + supportGroupName + '\'' +
                ", supportGroupEmail='" + supportGroupEmail + '\'' +
                ", applicationIdInput='" + applicationIdInput + '\'' +
                ", applicationIdInputName='" + applicationIdInputName + '\'' +
                ", applicationIdOutput='" + applicationIdOutput + '\'' +
                ", applicationIdOutputName='" + applicationIdOutputName + '\'' +
                ", databaseIdInput='" + databaseIdInput + '\'' +
                ", databaseIdInputName='" + databaseIdInputName + '\'' +
                ", databaseIdOutput='" + databaseIdOutput + '\'' +
                ", databaseIdOutputName='" + databaseIdOutputName + '\'' +
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
                ", createdDate=" + createdDate +
                ", lastUpdatedDate=" + lastUpdatedDate +
                '}';
    }
}
