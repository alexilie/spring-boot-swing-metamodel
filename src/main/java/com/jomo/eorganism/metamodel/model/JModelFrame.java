package com.jomo.eorganism.metamodel.model;

import java.awt.Container;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JPopupMenu;
import javax.swing.JWindow;
import javax.swing.JDesktopPane;
import javax.swing.JToolBar;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.WindowConstants;
import com.jomo.eorganism.metamodel.config.ApplicationConfiguration;
import javafx.scene.layout.BorderStroke;
import lombok.Getter;

public class JModelFrame extends JFrame {
    private JMenu          fileMenu;
    private JMenu          enterpriseMenu;
    private JMenu          openDBMenu;
    private JMenu          webMenu;
    private JMenu          helpMenu;
    private JMenu          designPattern;
    private JMenu          lookAndFeelMenu;
    private JMenu          designPatternSubMenu;
    private JMenu          openHistorySubMenu;

    private JMenuItem      loadEorganismXMLModelMenuItem;
    private JMenuItem      loadDbEorganismMenuItem;
    private JMenuItem      openEorganismMenuItem;
    private JMenuItem      openEorganismEnvironmentMenuItem;
    private JMenuItem      openEorganismXMLMenuItem;
    private JMenuItem      openApplicationXMLMenuItem;
    private JMenuItem      loadCommandXMLFileMenuItem;
    private JMenuItem      saveEorganismMenuItem;
    private JMenuItem      closeEorganismMenuItem;
    private JMenuItem      cloneEorganismMenuItem;
    private JMenuItem      compareEorganismMenuItem;
    private JMenuItem      exitEnterpriseMenuItem;
    private JMenuItem      newAppScheletonMenuItem;
    private JMenuItem      newAppLookupMenuItem;
    private JMenuItem      openAppDBMenuItem;
    private JMenuItem      openFromXMLMenuItem;
    private JMenuItem      saveMenuItem;
    private JMenuItem      saveAllMenuItem;
    private JMenuItem      saveAsMenuItem;
    private JMenuItem      saveACopyAsMenuItem;
    private JMenuItem      exitMenuItem;
    private JMenuItem      aboutMenu;
    private JMenuItem      newAppScheletonPopupMenuItem;
    private JMenuItem      newAppLookupPopupMenuItem;
    private JMenuItem      openFromXMLPopupMenuItem;
    private JMenuItem      savePopupMenuItem;
    private JMenuItem      saveAllPopupMenuItem;
    private JMenuItem      exitPopupMenuItem;
    private JMenuItem      aboutPopupMenu;
    private JMenuItem      howToMenu;
    private JMenuItem      buyMenu;
    private JMenuItem      jomoSiteMenuItem;
    private JMenuItem      browserOperationMenuItem;
    private JMenuItem      webServicesMenuItem;
    private JMenuItem      rmiMenuItem;
    private JMenuItem      createTemplate;
    private JMenuItem      openTemplate;
    private JMenuItem      jFileChooserMenuItem;
    private JMenuItem      designPatternSubMenuFactoryPattern;

    private JMenuItem      openHistoryMenuItem;
    private JMenuItem      saveHistoryMenuItem;
    private JMenuItem      examplesMenuItem;
    private JMenuItem      printMenuItem;
    private JMenuItem      pageSetupMenuItem;
    private JMenuItem      closeMenuItem;
    private JMenuItem      closeAllMenuItem;
    private JMenuItem      newMetalLookAndFeelMenuItem;

   private JMenuBar        menuBar  = new JMenuBar();
   private JToolBar        toolBar  = new JToolBar();
   private JPanel          panel    = new JPanel();
   private Container       container;
   private JDesktopPane    desktopPane;

   // JTree for displaying file system
   private JTree                    fileTree;
   private JPopupMenu               popupMenu       = new JPopupMenu();
   private JFrame                   frame           = null;
   private JWindow                  splashScreen    = null;
   private JWindow                  loadingScreen   = null;

    // constructor
    public JModelFrame() {
               super();
    } // end constructor JModelFrame

    public JFrame getFrame() {
        return frame;
    }

    public void initialize() {
                    // new menus and actions
                    enterpriseMenu = new JMenu("Enterprise");

                    //LOAD_XML_MODEL_PHYSICAL_LOGICAL
                     
                    loadEorganismXMLModelMenuItem   = new JMenuItem("NEW ::  " + ApplicationConfiguration.LOAD_XML_MODEL_PHYSICAL_LOGICAL);
                    loadEorganismXMLModelMenuItem.setFont(ApplicationConfiguration.attributeFont);

                    loadDbEorganismMenuItem   = new JMenuItem(ApplicationConfiguration.LOAD_DB_MODEL_PHYSICAL_LOGICAL);
                    loadDbEorganismMenuItem.setFont(ApplicationConfiguration.attributeFont);

                  //OPEN_ENTERPRISE_EORGANISM_ALL_ENVIRONMENTS
                  //OPEN_ENTERPRISE_EORGANISM_FROM_ENVIRONMENT
                  openEorganismMenuItem   = new JMenuItem(ApplicationConfiguration.OPEN_ENTERPRISE_EORGANISM_ALL_ENVIRONMENTS);
                  openEorganismMenuItem.setFont(ApplicationConfiguration.attributeFont);

                  openEorganismEnvironmentMenuItem   = new JMenu("LOAD EORGANISM ENVIRONMENTS");
                  openEorganismEnvironmentMenuItem.setFont(ApplicationConfiguration.attributeFont);

                  openEorganismXMLMenuItem   = new JMenuItem(ApplicationConfiguration.OPEN_ENTERPRISE_EORGANISM_FROM_XML);
                  openEorganismXMLMenuItem.setFont(ApplicationConfiguration.attributeFont);

                  openApplicationXMLMenuItem   = new JMenuItem(ApplicationConfiguration.OPEN_APPLICATION_FROM_XML);
                  openApplicationXMLMenuItem.setFont(ApplicationConfiguration.attributeFont);

                  loadCommandXMLFileMenuItem   = new JMenuItem(ApplicationConfiguration.LOAD_COMMAND_XML_FILE);
                  loadCommandXMLFileMenuItem.setFont(ApplicationConfiguration.attributeFont);

                  saveEorganismMenuItem   = new JMenuItem(ApplicationConfiguration.SAVE_ENTERPRISE_EORGANISM);
                  saveEorganismMenuItem.setFont(ApplicationConfiguration.attributeFont);

                  closeEorganismMenuItem   = new JMenuItem(ApplicationConfiguration.CLOSE_ENTERPRISE_EORGANISM);
                  closeEorganismMenuItem.setFont(ApplicationConfiguration.attributeFont);

                  cloneEorganismMenuItem   = new JMenuItem(ApplicationConfiguration.CLONE_ENTERPRISE_EORGANISM);
                  cloneEorganismMenuItem.setFont(ApplicationConfiguration.attributeFont);

                  compareEorganismMenuItem   = new JMenuItem(ApplicationConfiguration.COMPARE_ENTERPRISE_EORGANISM);
                  compareEorganismMenuItem.setFont(ApplicationConfiguration.attributeFont);

                  exitEnterpriseMenuItem              = new JMenuItem(ApplicationConfiguration.EXIT_TEXT );
                  exitEnterpriseMenuItem.setFont(ApplicationConfiguration.attributeFont);

                  // File Menu
                  fileMenu               = new JMenu("File");
                  fileMenu.setFont(ApplicationConfiguration.attributeFont);
                                    
                  newAppScheletonMenuItem   = new JMenuItem(ApplicationConfiguration.NEWAPPSCHELETON_TEXT );
                  newAppScheletonMenuItem.setFont(ApplicationConfiguration.attributeFont);

                  newAppLookupMenuItem      = new JMenuItem(ApplicationConfiguration.NEWAPPLOOKUP_TEXT );
                  newAppLookupMenuItem.setFont(ApplicationConfiguration.attributeFont);

                  openAppDBMenuItem      = new JMenuItem(ApplicationConfiguration.OPENFROMDB_TEXT);
                  openAppDBMenuItem.setFont(ApplicationConfiguration.attributeFont);

                  newAppScheletonPopupMenuItem   = new JMenuItem(ApplicationConfiguration.NEWAPPSCHELETON_TEXT );
                  newAppScheletonPopupMenuItem.setFont(ApplicationConfiguration.attributeFont);

                  newAppLookupPopupMenuItem      = new JMenuItem(ApplicationConfiguration.NEWAPPLOOKUP_TEXT );
                  newAppLookupPopupMenuItem.setFont(ApplicationConfiguration.attributeFont);

                  openFromXMLMenuItem              = new JMenuItem(ApplicationConfiguration.OPENFROMXML_TEXT );
                  openFromXMLMenuItem.setFont(ApplicationConfiguration.attributeFont);

                  openFromXMLPopupMenuItem     = new JMenuItem(ApplicationConfiguration.OPENFROMXML_TEXT );
                  openFromXMLPopupMenuItem.setFont(ApplicationConfiguration.attributeFont);

                  saveMenuItem              = new JMenuItem(ApplicationConfiguration.SAVE_TEXT );
                  saveMenuItem.setFont(ApplicationConfiguration.attributeFont);

                  saveAsMenuItem              = new JMenuItem( "Save JConnector As..." );
                  saveAsMenuItem.setFont(ApplicationConfiguration.attributeFont);

                  saveACopyAsMenuItem              = new JMenuItem( "Save JConnector As and copy As" );
                  saveACopyAsMenuItem.setFont(ApplicationConfiguration.attributeFont);

                  saveAllMenuItem              = new JMenuItem( "Save All JConnector Projects" );
                  saveAllMenuItem.setFont(ApplicationConfiguration.attributeFont);

                  printMenuItem              = new JMenuItem( "Print JConnector" );
                  printMenuItem.setFont(ApplicationConfiguration.attributeFont);

                  closeMenuItem              = new JMenuItem( "Close JConnector" );
                  closeMenuItem.setFont(ApplicationConfiguration.attributeFont);

                  closeAllMenuItem           = new JMenuItem( "Close All JConnector projects" );
                  closeAllMenuItem.setFont(ApplicationConfiguration.attributeFont);

                  savePopupMenuItem         = new JMenuItem(ApplicationConfiguration.SAVE_TEXT );
                  savePopupMenuItem.setFont(ApplicationConfiguration.attributeFont);

                  saveAllMenuItem              = new JMenuItem( ApplicationConfiguration.SAVEALL_TEXT );
                  saveAllMenuItem.setFont(ApplicationConfiguration.attributeFont);

                  saveAllPopupMenuItem          = new JMenuItem( ApplicationConfiguration.SAVEALL_TEXT );
                  saveAllPopupMenuItem.setFont(ApplicationConfiguration.attributeFont);

                  exitMenuItem              = new JMenuItem( ApplicationConfiguration.EXIT_TEXT );
                  exitMenuItem.setFont(ApplicationConfiguration.attributeFont);

                  exitPopupMenuItem        = new JMenuItem( ApplicationConfiguration.EXIT_TEXT );
                  exitPopupMenuItem.setFont(ApplicationConfiguration.attributeFont);

                  openDBMenu            = new JMenu("Open");
                  openDBMenu.setFont(ApplicationConfiguration.attributeFont);

                  webMenu                 = new JMenu("Web");
                  webMenu.setFont(ApplicationConfiguration.attributeFont);

                  jomoSiteMenuItem              = new JMenuItem( "Launch EOrganism Web version" );
                  jomoSiteMenuItem.setFont(ApplicationConfiguration.attributeFont);
                  
                  browserOperationMenuItem      = new JMenuItem( "Launch EOrganism Web Doc" );
                  browserOperationMenuItem.setFont(ApplicationConfiguration.attributeFont);
                  
                  webServicesMenuItem           = new JMenuItem( "EOrganism Web Services" );
                  webServicesMenuItem.setFont(ApplicationConfiguration.attributeFont);
                  
                  rmiMenuItem                   = new JMenuItem( "EOrganism RMI" );
                  rmiMenuItem.setFont(ApplicationConfiguration.attributeFont);

                  helpMenu                      = new JMenu("Help");
                  helpMenu.setFont(ApplicationConfiguration.attributeFont);

                  designPattern                  = new JMenu("Design");
                  designPattern.setFont(ApplicationConfiguration.attributeFont);

                  createTemplate   = new JMenuItem( "Create Template" );
                  createTemplate.setFont(ApplicationConfiguration.attributeFont);

                  jFileChooserMenuItem   = new JMenuItem("JColorChooser");
                  jFileChooserMenuItem.setFont(ApplicationConfiguration.attributeFont);

                  openTemplate   = new JMenuItem("Open Template");
                  openTemplate.setFont(ApplicationConfiguration.attributeFont);

                  aboutMenu   = new JMenuItem("About");
                  aboutMenu.setFont(ApplicationConfiguration.attributeFont);

                  howToMenu   = new JMenuItem("How To Use");
                  howToMenu.setFont(ApplicationConfiguration.attributeFont);
                  
                  buyMenu   = new JMenuItem("Buy now");
                  buyMenu.setFont(ApplicationConfiguration.attributeFont);
                     
                  lookAndFeelMenu   = new JMenu("L&F");
                  lookAndFeelMenu.setFont(ApplicationConfiguration.attributeFont);

                  designPatternSubMenu  = new JMenu( "DesignPatternSubMenu" );
                  designPatternSubMenu.setFont(ApplicationConfiguration.attributeFont);
                  
                  openHistoryMenuItem  = new JMenu( "Open @ OPEN History" );
                  openHistoryMenuItem.setFont(ApplicationConfiguration.attributeFont);

                  saveHistoryMenuItem  = new JMenu( "Open @ SAVE History" );
                  saveHistoryMenuItem.setFont(ApplicationConfiguration.attributeFont);

                  examplesMenuItem  = new JMenu( "Open JConnector Samples Apps" );
                  examplesMenuItem.setFont(ApplicationConfiguration.attributeFont);

                  openHistorySubMenu  = new JMenu("History");
                  openHistorySubMenu.setFont(ApplicationConfiguration.attributeFont);

                  menuBar       = new JMenuBar();
                  menuBar.setFont(ApplicationConfiguration.attributeFont);

                  // enterprise
                  enterpriseMenu.add(loadEorganismXMLModelMenuItem);
                  enterpriseMenu.add(loadDbEorganismMenuItem);
                  enterpriseMenu.add(openEorganismMenuItem);
                  enterpriseMenu.add(openEorganismEnvironmentMenuItem);
                  enterpriseMenu.add(openEorganismXMLMenuItem);
                  enterpriseMenu.add(openApplicationXMLMenuItem);
                  enterpriseMenu.add(loadCommandXMLFileMenuItem);
                  enterpriseMenu.add(saveEorganismMenuItem);
                  enterpriseMenu.add(new JPopupMenu.Separator() );
                  enterpriseMenu.add(closeEorganismMenuItem);
                  enterpriseMenu.add(cloneEorganismMenuItem);
                  enterpriseMenu.add(compareEorganismMenuItem);
                  enterpriseMenu.add(new JPopupMenu.Separator() );
                  enterpriseMenu.add(exitEnterpriseMenuItem);

                  fileMenu.add(newAppScheletonMenuItem);
                  fileMenu.add(newAppLookupMenuItem);
                  fileMenu.add(openAppDBMenuItem);
                  fileMenu.add(new JPopupMenu.Separator() );
                  fileMenu.add(openFromXMLMenuItem);

                  fileMenu.add( new JPopupMenu.Separator() );
                  fileMenu.add(closeMenuItem);
                  fileMenu.add(closeAllMenuItem);
                  fileMenu.add( new JPopupMenu.Separator() );
                  fileMenu.add(saveMenuItem);
                  fileMenu.add(saveAsMenuItem);
                  fileMenu.add(saveACopyAsMenuItem);
                  fileMenu.add(saveAllMenuItem);
                  fileMenu.add( new JPopupMenu.Separator());
                  fileMenu.add(printMenuItem);
                  fileMenu.add(new JPopupMenu.Separator());
                  fileMenu.add(exitMenuItem);

                  webMenu.add( jomoSiteMenuItem);
                  webMenu.add( browserOperationMenuItem);
                  webMenu.add( webServicesMenuItem);
                  webMenu.add( rmiMenuItem);

                  helpMenu.add(aboutMenu);
                  helpMenu.add(howToMenu);
                  helpMenu.add(buyMenu);
                  
                  designPattern.add(designPatternSubMenu);
                  designPattern.add(openTemplate);

                  // set up menu bar
                  menuBar.add( fileMenu );
                  menuBar.add( enterpriseMenu );
                  menuBar.add( openDBMenu );
                  menuBar.add( webMenu );
                  menuBar.add( helpMenu );
                  menuBar.add( lookAndFeelMenu );
                  menuBar.add( designPattern );

                  menuBar.setFont(ApplicationConfiguration.attributeFont);

                  toolBar.putClientProperty("JToolBar.isRollover", Boolean.TRUE);
                 
                  // creates the JPopUpMenu
                  popupMenu.add( newAppScheletonPopupMenuItem);
                  popupMenu.add( newAppLookupPopupMenuItem);
                  popupMenu.add( openAppDBMenuItem);
        
                  popupMenu.add( new JPopupMenu.Separator() );
                  popupMenu.add( openFromXMLPopupMenuItem);
                  popupMenu.add( saveMenuItem);
                  popupMenu.add( saveAllPopupMenuItem);
                  popupMenu.add( new JPopupMenu.Separator() );
                  popupMenu.add( exitPopupMenuItem);

                  // set toolbar
                  toolBar.setSize(200, 25);
                  toolBar.setVisible(true);

                   // for internal frames
                   desktopPane = new JDesktopPane();
                   desktopPane.setSize(940,900);
                   desktopPane.setVisible(true);
                   desktopPane.setBackground(new Color(0,51,153));

                   // setup container with desktop pane- get the content pane to set up GUI
                   container = getContentPane();
                   container.add( desktopPane, BorderLayout.CENTER);
                   panel.add(toolBar);
                   panel.setVisible(true);
                   container.add( panel, BorderLayout.EAST);

                   // setup JFrame
                   setJMenuBar( menuBar );
                   setTitle(ApplicationConfiguration.APPLICATION_FRAME_NAME);
                   setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                   setBackground(new Color(0,51,204));
                   setSize( 940, 730 );
                   setVisible(true);

    } // end initialize()

} // end JModelFrame class
