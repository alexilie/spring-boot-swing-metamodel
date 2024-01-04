package com.jomo.eorganism.metamodel;

import com.jomo.eorganism.metamodel.controller.ApplicationController;
import com.jomo.eorganism.metamodel.controller.LookAndFeelController;

import com.jomo.eorganism.metamodel.repository.ApplicationRepository;
import com.jomo.eorganism.metamodel.repository.ComponentRepository;
import com.jomo.eorganism.metamodel.repository.DatabaseRepository;
import com.jomo.eorganism.metamodel.repository.SystemRepository;
import com.jomo.eorganism.metamodel.repository.DomainRepository;
import com.jomo.eorganism.metamodel.repository.ReleaseRepository;
import com.jomo.eorganism.metamodel.repository.EnvironmentRepository;
import com.jomo.eorganism.metamodel.repository.MetadataRepository;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import com.jomo.eorganism.metamodel.entity.ApplicationEntity;
import com.jomo.eorganism.metamodel.entity.ComponentEntity;
import com.jomo.eorganism.metamodel.entity.DatabaseEntity;
import com.jomo.eorganism.metamodel.entity.SystemEntity;
import com.jomo.eorganism.metamodel.entity.DomainEntity;
import com.jomo.eorganism.metamodel.entity.ReleaseEntity;
import com.jomo.eorganism.metamodel.entity.EnvironmentEntity;
import com.jomo.eorganism.metamodel.entity.MetadataEntity;

import javax.swing.SwingUtilities;
import java.util.Date;

@SpringBootApplication
public class MetamodelApplication {
    private static final org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager.getLogger(MetamodelApplication.class.getName());
    //private static final org.slf4j.Logger logger2                = org.slf4j.LoggerFactory.getLogger(MetamodelWebApplication.class);

    public static void main(String[] args) {
        //setup L&F
        LookAndFeelController.setWindowsLookAndFeel();
        logger.info("##LookAndFeelController.setWindowsLookAndFeel()##");

        //get configurable context
        ConfigurableApplicationContext context = createApplicationContext(args);
        logger.info("##ConfigurableApplicationContext context = createApplicationContext(args)##");

        //display Swing  GUI
        displayMainFrame(context);
        logger.info("##displayMainFrame(context)##");
    } // main

    private static ConfigurableApplicationContext createApplicationContext(String[] args) {
        return new SpringApplicationBuilder(MetamodelApplication.class).headless(false).run(args);
    }

    private static void displayMainFrame(ConfigurableApplicationContext context) {
        SwingUtilities.invokeLater(() -> {
            ApplicationController applicationController = context.getBean(ApplicationController.class);
            applicationController.initialize();
        });
    }

    @Bean
    public CommandLineRunner loadApplications(ApplicationRepository applicationRepository,
                                              ComponentRepository componentRepository,
                                              DatabaseRepository databaseRepository,
                                              DomainRepository domainRepository,
                                              SystemRepository systemRepository,
                                              ReleaseRepository releaseRepository,
                                              EnvironmentRepository environmentRepository,
                                              MetadataRepository metadataRepository) {

        return (args) -> {
            ApplicationEntity applicationEntityWorker;
            ComponentEntity componentEntityWorker;
            DatabaseEntity databaseEntityWorker;
            SystemEntity systemEntityWorker;
            DomainEntity domainEntityWorker;
            ReleaseEntity releaseEntityWorker;
            EnvironmentEntity environmentEntityWorker;
            MetadataEntity metadataEntityWorker;

            applicationEntityWorker = new ApplicationEntity("Jomo Metamodel Spring Boot Admin", "Defect Tracking", "Jomo Trackzilla Defect tracking Application for tracking bugs");
            componentEntityWorker = new ComponentEntity("Jomo Spring Boot Admin", "Defect Tracking", "Jomo Trackzilla Web GUI Admin component");
            databaseEntityWorker = new DatabaseEntity("Jomo Spring Boot Admin", "Oracle Cloud", "Jomo Trackzilla Database");
            domainEntityWorker = new DomainEntity("Billing Domain Spring Boot Admin", "Billing", "Business Banking Billing Domain");
            systemEntityWorker = new SystemEntity("CRM ", "Critical", "CRM Customer Relationship System");
            releaseEntityWorker = new ReleaseEntity("PROD-HEROKU Metamodel Spring Boot Admin", "PROD", "CRM Customer Relationship System");
            environmentEntityWorker = new EnvironmentEntity("ENV-HEROKU Spring Boot Admin", "PROD", "HEROKU Environment");
            metadataEntityWorker = new MetadataEntity("METADATA", "METADATA", "METADATA DESC");

            componentEntityWorker.setApplicationId(1L);
            systemEntityWorker.setDomainId(1L);
            environmentEntityWorker.setReleaseId(1L);

            releaseRepository.save(releaseEntityWorker);
            domainRepository.save(domainEntityWorker);
            environmentRepository.save(environmentEntityWorker);
            applicationRepository.save(applicationEntityWorker);
            componentRepository.save(componentEntityWorker);
            databaseRepository.save(databaseEntityWorker);
            systemRepository.save(systemEntityWorker);

            metadataEntityWorker.setName(releaseEntityWorker.getName());
            metadataEntityWorker.setType(releaseEntityWorker.getMetadataType());
            metadataEntityWorker.setDescription(releaseEntityWorker.getDescription());
            metadataEntityWorker.setOriginalId(releaseEntityWorker.getId());
            metadataEntityWorker.setLastUpdatedApplicationName(releaseEntityWorker.getLastUpdatedApplicationName());
            metadataEntityWorker.setLastUpdatedUserName(releaseEntityWorker.getLastUpdatedUserName());

            metadataRepository.save(metadataEntityWorker);

            //reset
            metadataEntityWorker = new MetadataEntity("METADATA", "METADATA", "METADATA DESC");

            metadataEntityWorker.setName(domainEntityWorker.getName());
            metadataEntityWorker.setType(domainEntityWorker.getMetadataType());
            metadataEntityWorker.setDescription(domainEntityWorker.getDescription());
            metadataEntityWorker.setOriginalId(domainEntityWorker.getId());
            metadataEntityWorker.setLastUpdatedApplicationName(domainEntityWorker.getLastUpdatedApplicationName());
            metadataEntityWorker.setLastUpdatedUserName(domainEntityWorker.getLastUpdatedUserName());

            metadataRepository.save(metadataEntityWorker);

            //reset
            metadataEntityWorker = new MetadataEntity("METADATA", "METADATA", "METADATA DESC");

            metadataEntityWorker.setName(environmentEntityWorker.getName());
            metadataEntityWorker.setType(environmentEntityWorker.getMetadataType());
            metadataEntityWorker.setDescription(environmentEntityWorker.getDescription());
            metadataEntityWorker.setOriginalId(environmentEntityWorker.getId());
            metadataEntityWorker.setLastUpdatedApplicationName(environmentEntityWorker.getLastUpdatedApplicationName());
            metadataEntityWorker.setLastUpdatedUserName(environmentEntityWorker.getLastUpdatedUserName());

            metadataRepository.save(metadataEntityWorker);

            //reset
            metadataEntityWorker = new MetadataEntity("METADATA", "METADATA", "METADATA DESC");

            metadataEntityWorker.setName(applicationEntityWorker.getName());
            metadataEntityWorker.setType(applicationEntityWorker.getMetadataType());
            metadataEntityWorker.setDescription(applicationEntityWorker.getDescription());
            metadataEntityWorker.setOriginalId(applicationEntityWorker.getId());
            metadataEntityWorker.setLastUpdatedApplicationName(applicationEntityWorker.getLastUpdatedApplicationName());
            metadataEntityWorker.setLastUpdatedUserName(applicationEntityWorker.getLastUpdatedUserName());

            metadataRepository.save(metadataEntityWorker);

            //reset
            metadataEntityWorker = new MetadataEntity("METADATA", "METADATA", "METADATA DESC");

            metadataEntityWorker.setName(componentEntityWorker.getName());
            metadataEntityWorker.setType(componentEntityWorker.getMetadataType());
            metadataEntityWorker.setDescription(componentEntityWorker.getDescription());
            metadataEntityWorker.setOriginalId(componentEntityWorker.getId());
            metadataEntityWorker.setLastUpdatedApplicationName(componentEntityWorker.getLastUpdatedApplicationName());
            metadataEntityWorker.setLastUpdatedUserName(componentEntityWorker.getLastUpdatedUserName());

            metadataRepository.save(metadataEntityWorker);

            //reset
            metadataEntityWorker = new MetadataEntity("METADATA", "METADATA", "METADATA DESC");

            metadataEntityWorker.setName(databaseEntityWorker.getName());
            metadataEntityWorker.setType(databaseEntityWorker.getMetadataType());
            metadataEntityWorker.setDescription(databaseEntityWorker.getDescription());
            metadataEntityWorker.setOriginalId(databaseEntityWorker.getId());
            metadataEntityWorker.setLastUpdatedApplicationName(databaseEntityWorker.getLastUpdatedApplicationName());
            metadataEntityWorker.setLastUpdatedUserName(databaseEntityWorker.getLastUpdatedUserName());

            metadataRepository.save(metadataEntityWorker);

            //reset
            metadataEntityWorker = new MetadataEntity("METADATA", "METADATA", "METADATA DESC");

            metadataEntityWorker.setName(systemEntityWorker.getName());
            metadataEntityWorker.setType(systemEntityWorker.getMetadataType());
            metadataEntityWorker.setDescription(systemEntityWorker.getDescription());
            metadataEntityWorker.setOriginalId(systemEntityWorker.getId());
            metadataEntityWorker.setLastUpdatedApplicationName(systemEntityWorker.getLastUpdatedApplicationName());
            metadataEntityWorker.setLastUpdatedUserName(systemEntityWorker.getLastUpdatedUserName());

            metadataRepository.save(metadataEntityWorker);

            //2
            applicationEntityWorker = new ApplicationEntity("EOrganism Connector", "Code Generator", "EOrganism Connector Source Code Generator from Metamodel");

            componentEntityWorker = new ComponentEntity("EOrganism Connector Swing GUI", "Java Swing GUI App", "EOrganism Connector Swing GUI, Java Swing GUI App");
            databaseEntityWorker = new DatabaseEntity("EOrganism Connector Database", "My SQL", "Jomo Trackzilla Database");
            domainEntityWorker = new DomainEntity("Digital Domain", "Digital Channels", "Digital Domain Channels - Mobile, Web Channels");
            systemEntityWorker = new SystemEntity("SAP", "Critical", "SAP System");
            releaseEntityWorker = new ReleaseEntity("PROD-AWS Metamodel Web", "PROD", "PROD-AWS Infrastructure");
            environmentEntityWorker = new EnvironmentEntity("ENV-AWS", "PROD", "AWS Cloud Environment");
            metadataEntityWorker = new MetadataEntity("METADATA", "METADATA", "METADATA DESC");

            componentEntityWorker.setApplicationId(1L);
            systemEntityWorker.setDomainId(1L);
            environmentEntityWorker.setReleaseId(1L);

            releaseRepository.save(releaseEntityWorker);
            domainRepository.save(domainEntityWorker);
            environmentRepository.save(environmentEntityWorker);
            applicationRepository.save(applicationEntityWorker);
            componentRepository.save(componentEntityWorker);
            databaseRepository.save(databaseEntityWorker);
            systemRepository.save(systemEntityWorker);

            metadataEntityWorker.setName(releaseEntityWorker.getName());
            metadataEntityWorker.setType(releaseEntityWorker.getMetadataType());
            metadataEntityWorker.setDescription(releaseEntityWorker.getDescription());
            metadataEntityWorker.setOriginalId(releaseEntityWorker.getId());
            metadataEntityWorker.setLastUpdatedApplicationName(releaseEntityWorker.getLastUpdatedApplicationName());
            metadataEntityWorker.setLastUpdatedUserName(releaseEntityWorker.getLastUpdatedUserName());

            metadataRepository.save(metadataEntityWorker);
            //reset
            metadataEntityWorker = new MetadataEntity("METADATA", "METADATA", "METADATA DESC");

            metadataEntityWorker.setName(domainEntityWorker.getName());
            metadataEntityWorker.setType(domainEntityWorker.getMetadataType());
            metadataEntityWorker.setDescription(domainEntityWorker.getDescription());
            metadataEntityWorker.setOriginalId(domainEntityWorker.getId());
            metadataEntityWorker.setLastUpdatedApplicationName(domainEntityWorker.getLastUpdatedApplicationName());
            metadataEntityWorker.setLastUpdatedUserName(domainEntityWorker.getLastUpdatedUserName());

            metadataRepository.save(metadataEntityWorker);

            //reset
            metadataEntityWorker = new MetadataEntity("METADATA", "METADATA", "METADATA DESC");

            metadataEntityWorker.setName(environmentEntityWorker.getName());
            metadataEntityWorker.setType(environmentEntityWorker.getMetadataType());
            metadataEntityWorker.setDescription(environmentEntityWorker.getDescription());
            metadataEntityWorker.setOriginalId(environmentEntityWorker.getId());
            metadataEntityWorker.setLastUpdatedApplicationName(environmentEntityWorker.getLastUpdatedApplicationName());
            metadataEntityWorker.setLastUpdatedUserName(environmentEntityWorker.getLastUpdatedUserName());

            metadataRepository.save(metadataEntityWorker);

            //reset
            metadataEntityWorker = new MetadataEntity("METADATA", "METADATA", "METADATA DESC");

            metadataEntityWorker.setName(applicationEntityWorker.getName());
            metadataEntityWorker.setType(applicationEntityWorker.getMetadataType());
            metadataEntityWorker.setDescription(applicationEntityWorker.getDescription());
            metadataEntityWorker.setOriginalId(applicationEntityWorker.getId());
            metadataEntityWorker.setLastUpdatedApplicationName(applicationEntityWorker.getLastUpdatedApplicationName());
            metadataEntityWorker.setLastUpdatedUserName(applicationEntityWorker.getLastUpdatedUserName());

            metadataRepository.save(metadataEntityWorker);

            //reset
            metadataEntityWorker = new MetadataEntity("METADATA", "METADATA", "METADATA DESC");

            metadataEntityWorker.setName(componentEntityWorker.getName());
            metadataEntityWorker.setType(componentEntityWorker.getMetadataType());
            metadataEntityWorker.setDescription(componentEntityWorker.getDescription());
            metadataEntityWorker.setOriginalId(componentEntityWorker.getId());
            metadataEntityWorker.setLastUpdatedApplicationName(componentEntityWorker.getLastUpdatedApplicationName());
            metadataEntityWorker.setLastUpdatedUserName(componentEntityWorker.getLastUpdatedUserName());

            //reset
            metadataEntityWorker = new MetadataEntity("METADATA", "METADATA", "METADATA DESC");

            metadataEntityWorker.setName(databaseEntityWorker.getName());
            metadataEntityWorker.setType(databaseEntityWorker.getMetadataType());
            metadataEntityWorker.setDescription(databaseEntityWorker.getDescription());
            metadataEntityWorker.setOriginalId(databaseEntityWorker.getId());
            metadataEntityWorker.setLastUpdatedApplicationName(databaseEntityWorker.getLastUpdatedApplicationName());
            metadataEntityWorker.setLastUpdatedUserName(databaseEntityWorker.getLastUpdatedUserName());

            metadataRepository.save(metadataEntityWorker);

            //reset
            metadataEntityWorker = new MetadataEntity("METADATA", "METADATA", "METADATA DESC");

            metadataEntityWorker.setName(systemEntityWorker.getName());
            metadataEntityWorker.setType(systemEntityWorker.getMetadataType());
            metadataEntityWorker.setDescription(systemEntityWorker.getDescription());
            metadataEntityWorker.setOriginalId(systemEntityWorker.getId());
            metadataEntityWorker.setLastUpdatedApplicationName(systemEntityWorker.getLastUpdatedApplicationName());
            metadataEntityWorker.setLastUpdatedUserName(systemEntityWorker.getLastUpdatedUserName());

            metadataRepository.save(metadataEntityWorker);

            for (ReleaseEntity releaseEntity : releaseRepository.findAll()) {
                logger.info("The release is: " + releaseEntity.toString());
            }

            for (EnvironmentEntity environmentEntity : environmentRepository.findAll()) {
                logger.info("The environment is: " + environmentEntity.toString());
            }

            for (DomainEntity domainEntity : domainRepository.findAll()) {
                logger.info("The domain is: " + domainEntity.toString());
            }
            for (SystemEntity systemEntity : systemRepository.findAll()) {
                logger.info("The system is: " + systemEntity.toString());
            }

            for (ApplicationEntity applicationEntity : applicationRepository.findAll()) {
                logger.info("The application is: " + applicationEntity.toString());
            }

            for (ComponentEntity componentEntity : componentRepository.findAll()) {
                logger.info("The component is: " + componentEntity.toString());
            }

            for (DatabaseEntity databaseEntity : databaseRepository.findAll()) {
                logger.info("The database is: " + databaseEntity.toString());
            }

            for (MetadataEntity metadataEntity : metadataRepository.findAll()) {
                logger.info("The metadata is: " + metadataEntity.toString());
            }
        };
    } // end CommandLineRunner
} //end class