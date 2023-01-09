package com.jomo.eorganism.metamodel.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

@Controller
@AllArgsConstructor
public class LookAndFeelController {
    public static void setWindowsLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null,
                    "WINDOWS_STYLE_LOADING_ERROR_MESSAGE" + e,
                    "ALERT_TILE",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
