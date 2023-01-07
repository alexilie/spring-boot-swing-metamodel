package com.jomo.eorganism.metamodel.controller;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

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
