package com.jomo.eorganism.metamodel.controller;

import com.jomo.eorganism.metamodel.model.MetamodelFrame;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class ApplicationController {
    public static void initialize() {
        MetamodelFrame applicationGUIModelFrame = new MetamodelFrame();
        applicationGUIModelFrame.initialize();
    } //initialize
} // end class
