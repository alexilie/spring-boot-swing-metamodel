package com.jomo.eorganism.metamodel.controller;

import com.jomo.eorganism.metamodel.model.JModelFrame;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class ApplicationController {
    public static void initialize() {
        JModelFrame applicationGUIModelFrame = new JModelFrame();
        applicationGUIModelFrame.initialize();
    } //initialize
} // end class
