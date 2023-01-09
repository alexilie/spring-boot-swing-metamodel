package com.jomo.eorganism.metamodel;

import com.jomo.eorganism.metamodel.controller.ApplicationController;
import com.jomo.eorganism.metamodel.controller.LookAndFeelController;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.SwingUtilities;

@SpringBootApplication
public class MetamodelApplication {
    //private static final org.apache.logging.log4j.Logger logWorker = org.apache.logging.log4j.LogManager.getLogger(MetamodelApplication.class.getName());

    public static void main(String[] args) {
        //setup L&F
        LookAndFeelController.setWindowsLookAndFeel();
        //get configurable context
        ConfigurableApplicationContext context = createApplicationContext(args);
        //display Swing  GUI
        displayMainFrame(context);
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
} //end class