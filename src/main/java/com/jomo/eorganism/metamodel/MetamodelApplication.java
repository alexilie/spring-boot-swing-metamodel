package com.jomo.eorganism.metamodel;

import com.jomo.eorganism.metamodel.config.ApplicationConfiguration;
import com.jomo.eorganism.metamodel.controller.ApplicationController;
import com.jomo.eorganism.metamodel.controller.ConfigurationController;
import com.jomo.eorganism.metamodel.controller.LookAndFeelController;
import com.jomo.eorganism.metamodel.model.JModelFrame;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;
import java.io.File;

//@SpringBootApplication
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class MetamodelApplication {
	private static final org.apache.logging.log4j.Logger logWorker 		= org.apache.logging.log4j.LogManager.getLogger(MetamodelApplication.class.getName());

	public static void main(String[] args) {
		//setup L&F
		LookAndFeelController.setWindowsLookAndFeel();

		// ConfigurationController.createApplicationContext(args));

		//display Swing  GUI
		ApplicationController.initialize();

		logWorker.debug("Log4J - Metamodel is working fine!");

	}// main

}//end class