package com.jomo.eorganism.metamodel.controller;

import com.jomo.eorganism.metamodel.MetamodelApplication;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@Controller
@AllArgsConstructor
public class ConfigurationController {
    public static ConfigurableApplicationContext createApplicationContext(String[] args) {
        return new SpringApplicationBuilder(MetamodelApplication.class)
                .headless(false)
                .run(args);
    } //createApplicationContext
}