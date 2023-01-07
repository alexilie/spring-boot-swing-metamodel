package com.jomo.eorganism.metamodel.controller;

import com.jomo.eorganism.metamodel.MetamodelApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class ConfigurationController {

    public ConfigurationController(){
    }

    public static ConfigurableApplicationContext createApplicationContext(String[] args) {
        return new SpringApplicationBuilder(MetamodelApplication.class)
                .headless(false)
                .run(args);

    }//createApplicationContext
}
