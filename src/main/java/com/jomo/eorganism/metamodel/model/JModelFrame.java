package com.jomo.eorganism.metamodel.model;
// Java core packages
import java.awt.*;
import java.awt.event.*;
import java.io.File;

// Java extension packages
import javax.swing.*;

//
import com.jomo.eorganism.metamodel.MetamodelApplication;
import com.jomo.eorganism.metamodel.config.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

// l & f

//Log4J 2


public class JModelFrame extends JFrame {

    private JMenu          fileMenu,
                           enterpriseMenu,
                           openDBMenu,
                           //openAppMenu,
                           webMenu,   
                           helpMenu,
                           designPattern,
                           lookAndFeelMenu,
                           designPatternSubMenu,
                           themeMenu,
                           projectPreferencesMenu,
                           openHistorySubMenu;

   private JMenuItem        LoadEorganismXMLModelMenuItem,
                            LoadDbEorganismMenuItem,
                            OpenEorganismMenuItem,
                            OpenEorganismEnvironmentMenuItem,
                            OpenEorganismXMLMenuItem,
                            OpenApplicationXMLMenuItem,
                            LoadCommandXMLFileMenuItem,
                            SaveEorganismMenuItem,
                            CloseEorganismMenuItem,
                            CloneEorganismMenuItem,
                            CompareEorganismMenuItem,
                            exitEnterpriseMenuItem,
                            newAppScheletonMenuItem,
                            newAppLookupMenuItem,
                            openAppDBMenuItem,
                            openFromXMLMenuItem,
                           saveMenuItem,
                           saveAllMenuItem,
                           saveAsMenuItem,
                           saveACopyAsMenuItem,
                           exitMenuItem,
                           aboutMenu,
                           newAppScheletonPopupMenuItem,
                           newAppLookupPopupMenuItem,
                           openFromXMLPopupMenuItem,
                           savePopupMenuItem,
                           saveAllPopupMenuItem,
                           exitPopupMenuItem,
                           aboutPopupMenu,
                           howToMenu,
                           buyMenu,
                           jomoSiteMenuItem,
                           browserOperationMenuItem,
                           webServicesMenuItem,
                           rmiMenuItem,
                           createTemplate,
                           openTemplate,
                           jFileChooserMenuItem,
                           designPatternSubMenuFactoryPattern,
                           designPatternSubMenuServiceToWorkerPattern,
                           designPatternSubMenuFacadePattern,
                           generatePreferencesMenuItem,
                           deploymentPreferencesMenuItem,
                           createMyLookAndFeelMenuItem,
                           openHistoryMenuItem,
                           saveHistoryMenuItem,
                           examplesMenuItem,
                           printMenuItem,
                           pageSetupMenuItem,
                           closeMenuItem,
                           closeAllMenuItem,
                           newMetalLookAndFeelMenuItem;

   private JMenuBar        menuBar = new JMenuBar();
   private JToolBar        toolBar = new JToolBar();
   private Container       container;
   private JDesktopPane    desktopPane;

   // JTree for displaying file system
   private JTree           fileTree;

   private JPopupMenu               popupMenu       = new JPopupMenu();
   private JFrame                   frame           = null;
   private JWindow                  splashScreen    = null;
   private JWindow                  loadingScreen   = null;

  // constructor
  public JModelFrame() {
             super("EORGANISM MetaModel :: Enterprise Architecture :: Main");
  }// end constructor JModelFrame

    public JFrame getFrame() {return frame;}

    public void initialize(){
                  // new menus and actions
                  enterpriseMenu 		 = new JMenu();

                  //LOAD_XML_MODEL_PHYSICAL_LOGICAL
                     
                    LoadEorganismXMLModelMenuItem   = new JMenuItem("NEW ::  " + ApplicationConfiguration.LOAD_XML_MODEL_PHYSICAL_LOGICAL);
                    LoadEorganismXMLModelMenuItem.setFont(ApplicationConfiguration.attributeFont);

                    LoadDbEorganismMenuItem   = new JMenuItem(ApplicationConfiguration.LOAD_DB_MODEL_PHYSICAL_LOGICAL);
                    LoadDbEorganismMenuItem.setFont(ApplicationConfiguration.attributeFont);

                 //OPEN_ENTERPRISE_EORGANISM_ALL_ENVIRONMENTS
                 //OPEN_ENTERPRISE_EORGANISM_FROM_ENVIRONMENT
                  OpenEorganismMenuItem   = new JMenuItem(ApplicationConfiguration.OPEN_ENTERPRISE_EORGANISM_ALL_ENVIRONMENTS);
                  OpenEorganismMenuItem.setFont(ApplicationConfiguration.attributeFont);

                  OpenEorganismEnvironmentMenuItem   = new JMenu("LOAD EORGANISM ENVIRONMENTS");
                  OpenEorganismEnvironmentMenuItem.setFont(ApplicationConfiguration.attributeFont);

                  OpenEorganismXMLMenuItem   = new JMenuItem(ApplicationConfiguration.OPEN_ENTERPRISE_EORGANISM_FROM_XML);
                  OpenEorganismXMLMenuItem.setFont(ApplicationConfiguration.attributeFont);

                OpenApplicationXMLMenuItem   = new JMenuItem(ApplicationConfiguration.OPEN_APPLICATION_FROM_XML);
                OpenApplicationXMLMenuItem.setFont(ApplicationConfiguration.attributeFont);

                LoadCommandXMLFileMenuItem   = new JMenuItem(ApplicationConfiguration.LOAD_COMMAND_XML_FILE);
                LoadCommandXMLFileMenuItem.setFont(ApplicationConfiguration.attributeFont);

                  SaveEorganismMenuItem   = new JMenuItem(ApplicationConfiguration.SAVE_ENTERPRISE_EORGANISM);
                  SaveEorganismMenuItem.setFont(ApplicationConfiguration.attributeFont);

                  CloseEorganismMenuItem   = new JMenuItem(ApplicationConfiguration.CLOSE_ENTERPRISE_EORGANISM);
                  CloseEorganismMenuItem.setFont(ApplicationConfiguration.attributeFont);

                  CloneEorganismMenuItem   = new JMenuItem(ApplicationConfiguration.CLONE_ENTERPRISE_EORGANISM);
                  CloneEorganismMenuItem.setFont(ApplicationConfiguration.attributeFont);

                  CompareEorganismMenuItem   = new JMenuItem(ApplicationConfiguration.COMPARE_ENTERPRISE_EORGANISM);
                  CompareEorganismMenuItem.setFont(ApplicationConfiguration.attributeFont);

                  exitEnterpriseMenuItem              = new JMenuItem(ApplicationConfiguration.EXIT_TEXT );
                  exitEnterpriseMenuItem.setFont(ApplicationConfiguration.attributeFont);

                  // File Menu
                  fileMenu               = new JMenu();
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

                  openDBMenu            = new JMenu();
                  openDBMenu.setFont(ApplicationConfiguration.attributeFont);

                  webMenu                 = new JMenu();
                  webMenu.setFont(ApplicationConfiguration.attributeFont);

                  jomoSiteMenuItem              = new JMenuItem( "Launch EOrganism Web version" );
                  jomoSiteMenuItem.setFont(ApplicationConfiguration.attributeFont);
                  
                  browserOperationMenuItem      = new JMenuItem( "Launch EOrganism Web Doc" );
                  browserOperationMenuItem.setFont(ApplicationConfiguration.attributeFont);
                  
                  webServicesMenuItem           = new JMenuItem( "EOrganism Web Services" );
                  webServicesMenuItem.setFont(ApplicationConfiguration.attributeFont);
                  
                  rmiMenuItem                   = new JMenuItem( "EOrganism RMI" );
                  rmiMenuItem.setFont(ApplicationConfiguration.attributeFont);

                  helpMenu                      = new JMenu();
                  helpMenu.setFont(ApplicationConfiguration.attributeFont);

                  designPattern                  = new JMenu(  );
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
                     
                  lookAndFeelMenu   = new JMenu();
                  lookAndFeelMenu.setFont(ApplicationConfiguration.attributeFont);

                  designPatternSubMenu  = new JMenu( "DesignPatternSubMenu" );
                  designPatternSubMenu.setFont(ApplicationConfiguration.attributeFont);
                  
                  openHistoryMenuItem  = new JMenu( "Open @ OPEN History" );
                  openHistoryMenuItem.setFont(ApplicationConfiguration.attributeFont);

                  saveHistoryMenuItem  = new JMenu( "Open @ SAVE History" );
                  saveHistoryMenuItem.setFont(ApplicationConfiguration.attributeFont);

                  examplesMenuItem  = new JMenu( "Open JConnector Samples Apps" );
                  examplesMenuItem.setFont(ApplicationConfiguration.attributeFont);

                  openHistorySubMenu  = new JMenu();
                  openHistorySubMenu.setFont(ApplicationConfiguration.attributeFont);

                  menuBar       = new JMenuBar();
                  menuBar.setFont(ApplicationConfiguration.attributeFont);

                  // enterprise
                  enterpriseMenu.add(LoadEorganismXMLModelMenuItem);   
                  enterpriseMenu.add(LoadDbEorganismMenuItem);
                  enterpriseMenu.add(OpenEorganismMenuItem);
                  enterpriseMenu.add(OpenEorganismEnvironmentMenuItem);
                  enterpriseMenu.add(OpenEorganismXMLMenuItem);
                  enterpriseMenu.add(OpenApplicationXMLMenuItem);
                  enterpriseMenu.add(LoadCommandXMLFileMenuItem);
                  enterpriseMenu.add(SaveEorganismMenuItem);
                  enterpriseMenu.add(new JPopupMenu.Separator() );
                  enterpriseMenu.add(CloseEorganismMenuItem);
                  enterpriseMenu.add(CloneEorganismMenuItem);
                  enterpriseMenu.add(CompareEorganismMenuItem);
                  enterpriseMenu.add(new JPopupMenu.Separator() );
                  enterpriseMenu.add(exitEnterpriseMenuItem);
                  
                  //end
                 // add actions to File menu
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
                  fileMenu.add( new JPopupMenu.Separator() );
                  fileMenu.add(printMenuItem);
                  fileMenu.add(new JPopupMenu.Separator() );
                  fileMenu.add(exitMenuItem);

                // add actions to web menu
                  webMenu.add( jomoSiteMenuItem );
                  webMenu.add( browserOperationMenuItem );
                  webMenu.add( webServicesMenuItem );
                  webMenu.add( rmiMenuItem );                

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
             
                  toolBar.setSize(200, 25);
                  setJMenuBar( menuBar );

                   // for internal frames
                   desktopPane = new JDesktopPane();
                   desktopPane.setSize(940,900);
                   desktopPane.setVisible(true);

                   desktopPane.setBackground(new Color(0,51,153));

                   // setup container with desktop pane- get the content pane to set up GUI
                   container = getContentPane();
                   container.add( desktopPane, BorderLayout.CENTER);
                   container.add( menuBar, BorderLayout.NORTH);
                   container.add( toolBar, BorderLayout.NORTH);

                   setDefaultCloseOperation( EXIT_ON_CLOSE );

                   setBackground(new Color(0,51,204));
                   setSize( 940, 730 );
                   setVisible(true);
 
    }// end initialize()

} // end JModelFrame class
