package com.jomo.eorganism.metamodel.controller;

import java.awt.*;
import java.nio.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.channels.FileChannel;
import java.nio.charset.*;
import java.awt.event.*;

// Java extension packages
import javax.swing.*;
import javax.swing.event.*;

import java.awt.event.*;
import java.awt.*;

import javax.swing.tree.*;

import java.util.*;
import java.util.logging.Logger;
import java.io.*;
import java.nio.*;
import java.sql.*;
import java.net.*;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSourceFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.security.*;
import java.io.*;

import javax.net.ssl.HttpsURLConnection;

import java.security.cert.Certificate;
import java.security.*;

import javax.net.ssl.*;

//Java extension packages
import java.text.SimpleDateFormat;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;

//third-party libraries
import org.xml.sax.*;
import org.w3c.dom.*;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;

import java.security.*;

// Log4J 2
import org.apache.logging.log4j.*;
import org.apache.logging.log4j.core.LoggerContext;



import java.io.StringWriter;

public class JEOrganismController {
	
	private static final boolean 			DISPLAY_HARDWARE 				= false;
	private static final boolean 			DISPLAY_DATAMETADATA 			= false;
	private static final boolean 			COPY_WSDL 						= false;
	
	private static final File 			logfileconfig 				= new File("C:\\EOrganism_MySQL\\resource\\log4j2.xml");
	private static final LoggerContext 	context 					= (LoggerContext) LogManager.getContext(false);
	private static final org.apache.logging.log4j.Logger log4j 		= LogManager.getLogger(JEOrganismController.class.getName());

	private static Logger 			 logger 	= Logger.getLogger(JEOrganismController.class.getName());
	
	static{
			
			context.setConfigLocation(logfileconfig.toURI());
			
			log4j.debug("SYSTEM STATIC: logfileconfig.getName() + logfileconfig.getName() " + logfileconfig.getName() + logfileconfig.getName());
			log4j.debug("SYSTEM STATIC: logfileconfig.toURI() " + logfileconfig.toURI());
		
	}// end static

	 public static final String eOrganismDBDataCopyPath 	="C:\\ProgramData\\MySQL\\MySQL Server 5.6\\data\\COPY_WSDL";
	 public static final String eOrganismDBDataPath 		="C:\\ProgramData\\MySQL\\MySQL Server 5.6\\data";
	 public static final String eOrganismWebPath 			="C:\\apache\\Tomcat 8.0\\webapps\\eorganism";
	
	 public static final String eOrganismPath 				= "C:\\EOrganism_MySQL";
	 public static final String eOrganismWebsite 			= "http://localhost:9090/eorganism/COPY_WSDL";
	 public static final String fileName 					= "C:\\EOrganism_MySQL\\tempfile.xml";
	 public static final String fileNameAttribute 			= "C:\\EOrganism_MySQL\\tempfileAtt.xml";
	 public static final String fileNameURL 				= "C:\\EOrganism_MySQL\\tempfileURL.xml";
	 
	  // DB STATIC INSERT
	 public static final String INSERT_ERELEASE_TABLE="INSERT INTO ERELEASE_TABLE(ERELEASE_ID,ERELEASE_NAME,ERELEASE_DESCRIPTION,EENVIRONMENT_ID_FK,EENVIRONMENT_NAME_FK) VALUES (?,?,?,?,?)";
	 
	 public static final String INSERT_EENVIRONMENT_TABLE="INSERT INTO EENVIRONMENT_TABLE(EENVIRONMENT_ID,EENVIRONMENT_NAME,EENVIRONMENT_DESCRIPTION,ERELEASE_ID_FK,ERELEASE_NAME_FK) VALUES (?,?,?,?,?)";
	 public static final String INSERT_EPROJECT_TABLE="INSERT INTO EPROJECT_TABLE(EPROJECT_ID,EPROJECT_NAME,EPROJECT_DESCRIPTION,EPROJECT_STATUS,ERELEASE_ID_FK,ERELEASE_NAME_FK,EENVIRONMENT_ID_FK,EENVIRONMENT_NAME_FK) VALUES (?,?,?,?,?,?,?,?)";
    
   	 public static final String INSERT_EAPPLICATION="INSERT INTO EAPPLICATION(EAPPLICATION_ID,EAPPLICATION_NAME,EAPPLICATION_BUSINESS_KPI,EORGANISM_ID,EAPPLICATION_INFRA,EAPPLICATION_LAYER,EAPPLICATION_CHANNEL,EAPPLICATION_SEGMENT,EAPPLICATION_IN,EAPPLICATION_OUT,METADATA_TYPE,EAPPLICATION_CREATED_DATE,EAPPLICATION_LAST_UPDATED_USER,EAPPLICATION_LAST_UPDATED_APP,EENVIRONMENT_NAME_FK,EAPPLICATION_INVENTORY_NAME,BUSINESS_SERVICE,BUSINESS_UNIT,SERVICE_CODE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	 public static final String INSERT_ECOMPONENT="INSERT INTO ECOMPONENT(ECOMPONENT_ID,ECOMPONENT_NAME,ECOMPONENT_DESCRIPTION,EORGANISM_EID,EAPPLICATION_ID_FK,EAPPLICATION_NAME_FK,EDATABASE_ID_FK,EDATABASE_NAME_FK,METADATA_TYPE,EENVIRONMENT_NAME_FK) VALUES (?,?,?,?,?,?,?,?,?,?)";
	 public static final String INSERT_EDATABASE ="INSERT INTO EDATABASE (EDATABASE_ID,EDATABASE_NAME,EDATABASE_DESCRIPTION, EORGANISM_EID,EAPPLICATION_ID_FK, EAPPLICATION_NAME_FK,EDATABASE_TYPE,EDATABASE_DRIVER_CLASS,METADATA_TYPE,EENVIRONMENT_NAME_FK) VALUES (?,?,?,?,?,?,?,?,?,?)";//10
	 public static final String INSERT_EHARDWARE_PHYSICAL_TABLE="INSERT INTO EHARDWARE_PHYSICAL_TABLE (EHARDWARE_PHYSICAL_ID, EHARDWARE_PHYSICAL_NAME, EHARDWARE_PHYSICAL_DESCRIPTION, EORGANISM_EID, EAPPLICATION_MODEL_ID_FK, EAPPLICATION_MODEL_NAME_FK, EENVIRONMENT_NAME_FK) VALUES (?,?,?,?,?,?,?)";
	 public static final String INSERT_ESERVICE="INSERT INTO ESERVICE(ESERVICE_ID,ESERVICE_NAME,ESERVICE_DESCRIPTION,EORGANISM_EID,EAPPLICATION_ID_FK, EAPPLICATION_NAME_FK) VALUES (?,?,?,?,?,?)";
	 public static final String INSERT_EINTERFACE="INSERT INTO EINTERFACE(EINTERFACE_ID,EINTERFACE_NAME,EINTERFACE_DESCRIPTION,EAPPLICATION_ID_FK,EAPPLICATION_NAME_FK,ECOMPONENT_NAME_FK,ESERVICE_NAME_FK) VALUES (?,?,?,?,?,?,?)";
        
	 public static final String INSERT_ESEQUENCE_FLOW_TABLE="INSERT INTO ESEQUENCE_FLOW_TABLE(ESEQUENCE_FLOW_ID,ESEQUENCE_FLOW_NAME,ESEQUENCE_FLOW_DESCRIPTION,ESEQUENCE_FLOW_DATA,EAPPLICATION_NAME_FK,EAPPLICATION_ID_FK) VALUES (?,?,?,?,?,?)";
	 public static final String INSERT_ESEQUENCE_ITEM_TABLE="INSERT INTO ESEQUENCE_ITEM_TABLE(ESEQUENCE_ITEM_ID,ESEQUENCE_ITEM_NAME,ESEQUENCE_ITEM_DATA,ESEQUENCE_FLOW_ID_FK,EAPPLICATION_NAME_FK,EAPPLICATION_ID_FK,LAST_UPDATED_APPLICATION,CREATED_DATE,UPDATED_DATE) VALUES (?,?,?,?,?,?,?,?,?)";
    
    //EMETADATA_SERVICE, EMETADATA_INTERFACE, EMETADATA_ATTRIBUTE
	 public static final String INSERT_EMETADATA_SERVICE="INSERT INTO EMETADATA_SERVICE(EMETADATA_SERVICE_ID,EMETADATA_SERVICE_NAME,EMETADATA_SERVICE_TYPE,EMETADATA_SERVICE_DESCRIPTION,EMETADATA_APPLICATION_NAME,EMETADATA_LAST_UPDATED_DATE,EMETADATA_LAST_UPDATED_USER,EMETADATA_LAST_UPDATED_APP) VALUES (?,?,?,?,?,?,?,?)";
	 public static final String INSERT_EMETADATA_INTERFACE="INSERT INTO EMETADATA_INTERFACE(EMETADATA_INTERFACE_ID,EMETADATA_INTERFACE_NAME,EMETADATA_COMPONENT_NAME,EMETADATA_APPLICATION_NAME,EMETADATA_LAST_UPDATED_DATE,EMETADATA_LAST_UPDATED_USER,EMETADATA_LAST_UPDATED_APP) VALUES (?,?,?,?,?,?,?)"; 
	 public static final String INSERT_EMETADATA_ATTRIBUTE="INSERT INTO EMETADATA_ATTRIBUTE( EMETADATA_ATTRIBUTE_ID,EMETADATA_ATTRIBUTE_NAME,EMETADATA_ATTRIBUTE_TYPE,EMETADATA_INTERFACE_NAME,EMETADATA_SERVICE_NAME,EMETADATA_COMPONENT_NAME,EMETADATA_DATABASE_NAME,EMETADATA_APPLICATION_NAME,EMETADATA_LAST_UPDATED_DATE,EMETADATA_LAST_UPDATED_USER,EMETADATA_LAST_UPDATED_APP) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    
    //EAPPLICATION_LINK
	 public static final String INSERT_EAPPLICATION_LINK="INSERT INTO EAPPLICATION_LINK(EAPPLICATION_LINK_ID,EAPPLICATION_LINK_NAME,EAPPLICATION_LINK_DESCRIPTION,EAPPLICATION_ID_FROM,EAPPLICATION_NAME_FROM,EAPPLICATION_ID_TO,EAPPLICATION_NAME_TO,LAST_UPDATED_DATE,LAST_UPDATED_USER,LAST_UPDATED_APPLICATION) VALUES (?,?,?,?,?,?,?,?,?,?)"; 

	  //// DB STATIC INSERT
	 public static final String SELECT_EAPPLICATION="SELECT EAPPLICATION_ID,EAPPLICATION_APPLICATION_ID, EAPPLICATION_ESIS_NAME,EAPPLICATION_ESIS,EAPPLICATION_ESIS_SYSTEM_NAME,EAPPLICATION_CLASIFICATION, EAPPLICATION_CLASIF_SERV,EAPPLICATION_CLASIF_CRITIC,EAPPLICATION_CLASIFICATION_CIM,EAPPLICATION_CLASIFICATION_PII,EAPPLICATION_CLASIFICATION_PCI,EAPPLICATION_CLASIFICATION_SOX, EAPPLICATION_TAX_IMPACT,EAPPLICATION_ARCHITECT_CRITIC, EAPPLICATION_DESC_FUNCTIONAL,EAPPLICATION_VP,EAPPLICATION_SUP_DIRECTOR,EAPPLICATION_SUP_MANAGER,EAPPLICATION_DEV_DIRECTOR,EAPPLICATION_DEV_MANAGER,EAPPLICATION_DEV_PRIME,EAPPLICATION_ISM_GROUP,EAPPLICATION_STATUS,EAPPLICATION_BUSINESS_KPI,EAPPLICATION_IS_HPD,EAPPLICATION_IS_SIS, EAPPLICATION_IS_BSM,EAPPLICATION_IS_BPM,EAPPLICATION_IS_SHO,EAPPLICATION_IS_SHR,OWNER,METADATA_TYPE,EENVIRONMENT_NAME_FK,EAPPLICATION_NAME,EAPPLICATION_DESCRIPTION,EAPPLICATION_IN,EAPPLICATION_OUT,BUSINESS_UNIT,BUSINESS_SERVICE FROM EAPPLICATION WHERE METADATA_TYPE IN ('APPLICATION','COMPONENT','INVENTORY','PHYSICAL')  ORDER BY EAPPLICATION_NAME";
     //resultSetApplications                     =  selectStatementApplication.executeQuery("SELECT EAPPLICATION_ID,EAPPLICATION_APPLICATION_ID, EAPPLICATION_ESIS_NAME,EAPPLICATION_ESIS,EAPPLICATION_ESIS_SYSTEM_NAME,EAPPLICATION_CLASIFICATION, EAPPLICATION_CLASIF_SERV,EAPPLICATION_CLASIF_CRITIC,EAPPLICATION_CLASIFICATION_CIM,EAPPLICATION_CLASIFICATION_PII,EAPPLICATION_CLASIFICATION_PCI,EAPPLICATION_CLASIFICATION_SOX, EAPPLICATION_TAX_IMPACT,EAPPLICATION_ARCHITECT_CRITIC, EAPPLICATION_DESC_FUNCTIONAL,EAPPLICATION_VP,EAPPLICATION_SUP_DIRECTOR,EAPPLICATION_SUP_MANAGER,EAPPLICATION_DEV_DIRECTOR,EAPPLICATION_DEV_MANAGER,EAPPLICATION_DEV_PRIME,EAPPLICATION_ISM_GROUP,EAPPLICATION_STATUS,EAPPLICATION_BUSINESS_KPI,EAPPLICATION_IS_HPD,EAPPLICATION_IS_SIS, EAPPLICATION_IS_BSM,EAPPLICATION_IS_BPM,EAPPLICATION_IS_SHO,EAPPLICATION_IS_SHR,OWNER,METADATA_TYPE,EENVIRONMENT_NAME_FK,EAPPLICATION_NAME,EAPPLICATION_DESCRIPTION,EAPPLICATION_IN,EAPPLICATION_OUT FROM EAPPLICATION WHERE EAPPLICATION_NAME ='Maestro'");
      
     // FOR ALL THE COMP MODELS
	 public static final String SELECT_ECOMPONENT="SELECT * FROM ECOMPONENT  WHERE METADATA_TYPE IN ('MODEL','INVENTORY') AND EAPPLICATION_NAME_FK=?"; 
	 public static final String SELECT_EAPPLICATION_="SELECT EAPPLICATION_ID,EAPPLICATION_APPLICATION_ID, EAPPLICATION_ESIS_NAME,EAPPLICATION_ESIS,EAPPLICATION_ESIS_SYSTEM_NAME,EAPPLICATION_CLASIFICATION, EAPPLICATION_CLASIF_SERV,EAPPLICATION_CLASIF_CRITIC,EAPPLICATION_CLASIFICATION_CIM,EAPPLICATION_CLASIFICATION_PII,EAPPLICATION_CLASIFICATION_PCI,EAPPLICATION_CLASIFICATION_SOX, EAPPLICATION_TAX_IMPACT,EAPPLICATION_ARCHITECT_CRITIC, EAPPLICATION_DESC_FUNCTIONAL,EAPPLICATION_VP,EAPPLICATION_SUP_DIRECTOR,EAPPLICATION_SUP_MANAGER,EAPPLICATION_DEV_DIRECTOR,EAPPLICATION_DEV_MANAGER,EAPPLICATION_DEV_PRIME,EAPPLICATION_ISM_GROUP,EAPPLICATION_STATUS,EAPPLICATION_BUSINESS_KPI,EAPPLICATION_IS_HPD,EAPPLICATION_IS_SIS, EAPPLICATION_IS_BSM,EAPPLICATION_IS_BPM,EAPPLICATION_IS_SHO,EAPPLICATION_IS_SHR,OWNER,METADATA_TYPE,EENVIRONMENT_NAME_FK,EAPPLICATION_NAME,EAPPLICATION_DESCRIPTION,EAPPLICATION_IN,EAPPLICATION_OUT,BUSINESS_UNIT,BUSINESS_SERVICE FROM EAPPLICATION WHERE METADATA_TYPE IN ('APPLICATION', 'COMPONENT', 'INVENTORY','PHYSICAL')  ORDER BY EAPPLICATION_NAME";
     
     // FOR ALL Services
	 public static final String SELECT_ESERVICE="SELECT ESERVICE_NAME,ESERVICE_TYPE,EAPPLICATION_NAME_FK,ECOMPONENT_NAME_FK FROM ESERVICE WHERE EAPPLICATION_NAME_FK=?";      
	 public static final String SELECT_EINTERFACE="SELECT EINTERFACE_NAME,EINTERFACE_TYPE,EAPPLICATION_NAME_FK,ECOMPONENT_NAME_FK,ESERVICE_NAME_FK FROM EINTERFACE WHERE EAPPLICATION_NAME_FK=?";    
	 public static final String SELECT_EATTRIBUTE="SELECT EATTRIBUTE_NAME,EATTRIBUTE_TYPE,EAPPLICATION_NAME_FK,ECOMPONENT_NAME_FK,ESERVICE_NAME_FK,EINTERFACE_NAME_FK FROM EATTRIBUTE WHERE EAPPLICATION_NAME_FK=?";
 
     //DB metadata
	 public static final String SELECT_EMETADATA_TABLE="SELECT DISTINCT EMETADATA_TABLE_OWNER, EMETADATA_COMPONENT_NAME,EMETADATA_APPLICATION_NAME FROM EMETADATA_TABLE ORDER BY EMETADATA_TABLE_OWNER";    
	 public static final String SELECT_EMETADATA_TABLE_="SELECT EMETADATA_TABLE_NAME FROM EMETADATA_TABLE WHERE EMETADATA_TABLE_OWNER=? ORDER BY EMETADATA_TABLE_NAME";
	 public static final String SELECT_EMETADATA_COLUMN="SELECT EMETADATA_COLUMN_NAME,EMETADATA_COLUMN_TYPE FROM EMETADATA_COLUMN WHERE EMETADATA_TABLE_NAME=? ORDER BY EMETADATA_COLUMN_NAME";
     
    // FOR ALL THE DB MODELS
	 public static final String SELECT_EDATABASE="SELECT * FROM EDATABASE WHERE METADATA_TYPE IN ('MODEL','INVENTORY') AND EAPPLICATION_NAME_FK=?";
     // FOR ALL HW
	 public static final String SELECT_EHARDWARE_PHYSICAL_TABLE="SELECT EHARDWARE_PHYSICAL_ID, EHARDWARE_PHYSICAL_NAME,EHARDWARE_PHYSICAL_IP,EENVIRONMENT_NAME_FK,EAPPLICATION_MODEL_NAME_FK,SUPPORT_DIRECTOR FROM EHARDWARE_PHYSICAL_TABLE WHERE EAPPLICATION_MODEL_NAME_FK=? ORDER BY EHARDWARE_PHYSICAL_NAME";  
	 public static final String SELECT_ESEQUENCE_FLOW_TABLE="SELECT ESEQUENCE_FLOW_ID,ESEQUENCE_FLOW_NAME,ESEQUENCE_FLOW_DESCRIPTION,ESEQUENCE_FLOW_DATA FROM ESEQUENCE_FLOW_TABLE WHERE EAPPLICATION_NAME_FK=?";  
	 public static final String SELECT_EHARDWARE_PHYSICAL_TABLE_="SELECT DISTINCT EENVIRONMENT_NAME_FK FROM EHARDWARE_PHYSICAL_TABLE WHERE EENVIRONMENT_NAME_FK IN ('DEVELOPMENT','DISASTER RECOVERY','LAB', 'NONPRODUCTION', 'PERFORMANCE', 'SADNBOX', 'QA','PREPRODUCTION','PRODUCTION','TEST','TRAIN') ORDER BY EENVIRONMENT_NAME_FK ASC";
	 public static final String SELECT_EHARDWARE_PHYSICAL_TABLE__="SELECT DISTINCT SUPPORT_DIRECTOR FROM EHARDWARE_PHYSICAL_TABLE WHERE SUPPORT_DIRECTOR IN ('AARON.LAING','AARON.MINKOVICH','AL.TABONE','ALICE.REAVIE','ANDREW.MILJANOVSKI','ANNALISA.ABOUD','B.SUNTHARALINGAM','BARRINDER.GREWAL','BERNARD.MIN','BIKRAM.SINGH','BILL.AHRENS','BILL.MANOLAKOS','CAMILLE.KIFFER','CATHY.ROY','CHANTELLE.SLATERSHAW','CHRISTINE.WATERS','CRAIG.ADAMCZYK','Christine.Waters','DAN.DUFOUR','DANNY.SU','DISALVOP@CA.IBM.COM','DOUG.BELANGER','DOUG.ILTON','FAIZ.UDDIN',	'FIL.GAMBATESA','FRANK.CROMBEEN', 'GINO.MORETTI','GORDON.HUNTER','GREGORY.MOORS','HOLSTEAD@CA.IBM.COM','IRENE.ZAGUSKIN','IROBINSO@CA.IBM.COM','JBOWES@CA.IBM.COM','JEFF.IVERS','JIBRIL.ESAK','JIM.GRAINGER','JODY.WAYE','JOE.HOPKINS','JOHN.BASTABLE','JOHN.GIOMBANI','JONATHAN.RASIAH','JOSIE.NIGRO','KAY.FOONG','KEN.PEBESMA','KESHAWA.THIRUNAHARI','KUMAR.KANDASAMY','LORIANNE.FITZPATRICK','M.SRINATH@IN.IBM.COM','MAAKRISH@IN.IBM.COM','MARION.THIVY','MARK.PRYCE','MARK.SEGAL','MARK.SHORROCKS','MERAV.MUNZ','MINGLI@CA.IBM.COM', 'MOHAMMAD.MUSHTAQUE','NANCYMJ@CA.IBM.COM','PAUL.ALVES','PAUL.KORBER','PAUL.LANDAU','PAULK@CA.IBM.COM','PETER.CHAFE','RALPH.VONEPPINGHOVEN','RICK.WELLEN','ROBERT.DREWETT','ROBERT.VANDENBERG','STELLA.CORLAZZOLI','STEVE.JOSEPH', 'STEWART.ZANOLLA','TEJDEEP.SINGH@IN.IBM.COM','TONY.BASSON','TREVORROLSTON@CA.IBM.COM','VICTORY.CHAN','VIJAYS@CA.IBM.COM','VIKRAM.VIRK','VINCE.GISMONDI','VIRGINIA.GEROLIN','VLADIMIR.FERNANDEZ','VLADIMIR.RADULOVIC','WILL.GREER','YOLANDA.BARNA','ZUL.SIDI') ORDER BY SUPPORT_DIRECTOR ASC";
     
	  //String fileXML = "http://localhost:9090/hexplorer/eorganism-tree-view.xml";
	  //path - C:\apache\Tomcat 8.0\webapps\hexplorer
	  String fileXML = "C:/apache/Tomcat 8.0/webapps/hexplorer/eorganism-tree-view.xml";
      String dbXML = "C:/apache/Tomcat 8.0/webapps/hexplorer/eorganism-db-view.xml";
      
      String fileJSON = "C:/apache/Tomcat 8.0/webapps/hexplorer/eorganism.json";
      String dbJSON = "C:/apache/Tomcat 8.0/webapps/hexplorer/eorganismdb.json";
        
      String fileGraphML = "C:/apache/Tomcat 8.0/webapps/hexplorer/eorganism-graphml.xml";
    
     public static final int EORGANISM      = 0;// dataSource.getConnection("eorganism", "alex1900");
     public static final int ABACUS         = 1;// dataSource.getConnection("abacus", "alex1900");
     public static final int JCONNECTOR     = 2;// dataSource.getConnection("jconnector", "alex1900");
     public static final int JOMO           = 3;// dataSource.getConnection("jomo", "alex1900");
    // INTERNAL FRAME BUTTONS
    public static final int SAVE_DATABASE      =0;
    public static final int DELETE_DATABASE    =1;
    public static final int LOAD_DATABASE      =2;
    public static final int BACKUP_DATABASE    =3;
    public static final int EXPORT_DATABASE    =4;
    public static final int ADD_APPLICATIONS   =5;
    public static final int ADD_COMPONENTS     =6;
    public static final int DELETE_WSDL_METADATA        =7;
    public static final int DELETE_DB_METADATA          =8;
    public static final int LOAD_WSDL_METADATA          =9;
    public static final int LOAD_DB_METADATA            =10;
    public static final int LOAD_APPLICATION_INVENTORY  =11;
    
    public static final int INTERNAL_BUTTONS_NUMBER      =12;
     	
   // JTree for displaying file system
    private JTree                            fileTree                    = new JTree();
    private JEditorPane                      fileEditor                  = new JEditorPane();

    public  JTree                            enterpriseTree          		= new JTree();//JTree
    // FileSystemModel TreeModel implementation
    private FileSystemModel                  fileSystemModel;
    
    private Font                             fontClasses         = new Font("New Font", Font.TRUETYPE_FONT, 10);
    private Font                             fontEditor          = new Font("New Font", Font.TRUETYPE_FONT, 12);
   
    private JSplitPane                       verticalLeftListSplitPanel  = new JSplitPane();
    private JSplitPane                       verticalLeftTableSplitPanel = new JSplitPane();

    private JTabbedPane                      eorganismTabbedPane;
    private JTabbedPane                      leftTabbedPane;
    private JSplitPane                       horizontalSplitPanel        = new JSplitPane();
    private JSplitPane                       verticalLeftSplitPanel      = new JSplitPane();

    private JInternalFrame                   internalFrame;
    private Container                        containerInternal;
    // new Feb 25, 2014
    private JToolBar                         toolBar                     = new JToolBar();
    public JButton[]                         internalButtonsArray        = null;
    public String[]                          internalToolTixTextArray    = null;
    public ImageIcon[]                       internalIconImageArray      = null;
    private JPopupMenu                       popupMenu                   = new JPopupMenu();
    
    Action saveDatabaseAction                 = new SaveDatabaseAction();
    Action deleteDatabaseAction               = new DeleteDatabaseAction();
    Action addApplicationsAction              = new AddApplicationsAction();
    Action addComponentsAction                = new AddComponentsAction();
    
    Action deleteWsdlMetadataAction           = new DeleteWSDLMetadataAction();
    Action deleteDbMetadataAction             = new DeleteDBMetadataAction();
    Action loadWsdlMetadataAction             = new LoadWSDLMetadataAction();
    Action loadDbMetadataAction               = new LoadDBMetadataAction();
    
    Action loadApplicationInventoryAction     = new ApplicationInventoryAction();
    
    //
    private     DefaultMutableTreeNode attributesRootNode ;
    
    private     DefaultMutableTreeNode attributesCollectionRootNode;
    private     DefaultMutableTreeNode modelsRootNode;
    private     DefaultMutableTreeNode modelsCollectionRootNode;
    private     DefaultMutableTreeNode methodsRootNode;
    private     DefaultMutableTreeNode constantsRootNode;

    private     DefaultMutableTreeNode enterpriseRootNode;
    // new
    private     DefaultMutableTreeNode systemsModelRootNode;
    private     DefaultMutableTreeNode applicationsInventory;
    private     DefaultMutableTreeNode domainsModel;
    private     DefaultMutableTreeNode systemsModel;
    private     DefaultMutableTreeNode applications;
    private     DefaultMutableTreeNode applicationsMaster;
    private     DefaultMutableTreeNode applicationsModel;
    
    private     DefaultMutableTreeNode databasesMetamodel;
    private     DefaultMutableTreeNode applicationComponents;
    private     DefaultMutableTreeNode applicationOperations;
    private     DefaultMutableTreeNode applicationInterfaces;
    private     DefaultMutableTreeNode applicationEntities;
    private     DefaultMutableTreeNode applicationDatabases;
    private     DefaultMutableTreeNode applicationHardware;
    private     DefaultMutableTreeNode applicationProperties;
    private     DefaultMutableTreeNode applicationBusinessFunctions;
    private     DefaultMutableTreeNode applicationActivity;
    private     DefaultMutableTreeNode applicationSequenceFlow;
    private     DefaultMutableTreeNode applicationInfrastructure;
    private     DefaultMutableTreeNode applicationEnvironment;
    
    private     DefaultMutableTreeNode technologyModelRootNode;
    private     DefaultMutableTreeNode hardwares;
    private     DefaultMutableTreeNode software;
    private     DefaultMutableTreeNode network;
    private     DefaultMutableTreeNode databasesModelRootNode;
    private     DefaultMutableTreeNode databases;
    private     DefaultMutableTreeNode databasesEntities;
    
    private     DefaultMutableTreeNode flowsModelRootNode;
    private     DefaultMutableTreeNode businessFlowsRootNode;
    private     DefaultMutableTreeNode sequenceDiagramFlowsRootNode;
    private     DefaultMutableTreeNode projectsModelRootNode;
    private     DefaultMutableTreeNode projectsSpaceRootNode;
    private     DefaultMutableTreeNode projects;
    private     DefaultMutableTreeNode releases;
    private     DefaultMutableTreeNode pepleModelRootNode;
    private     DefaultMutableTreeNode people;
    private     DefaultMutableTreeNode environmentsModelRootNode;
    private     DefaultMutableTreeNode environments;
    
    private     DefaultMutableTreeNode presentationRootNode;
    private     DefaultMutableTreeNode clientRootNode;
    private     DefaultMutableTreeNode presentationJSPRootNode;
    private     DefaultMutableTreeNode presentationStrutsRootNode;
    private     DefaultMutableTreeNode modelRootNode;
    private     DefaultMutableTreeNode controllerRootNode;
    private     DefaultMutableTreeNode stateTranferRootNode ;
    private     DefaultMutableTreeNode deploymentRootNode;
    private     DefaultMutableTreeNode datasourceModelRootNode;  
    private     DefaultMutableTreeNode dbModelRootNode;
    private     DefaultMutableTreeNode triggersModelRootNode; 
    private     DefaultMutableTreeNode proceduresModelRootNode ; 
     //
     private     DefaultMutableTreeNode xmlViewRootNode;
   
    private     DefaultMutableTreeNode webServicesRootNode;
    private     DefaultMutableTreeNode clientWebServicesRootNode;
    private     DefaultMutableTreeNode webServiceAndClientWSRootNode;

    private    DefaultMutableTreeNode columnsRootNode;
    private    DefaultMutableTreeNode pkRootNode;
    private    DefaultMutableTreeNode fkRootNode;
    private    DefaultMutableTreeNode indexesRootNode;

    // used to set images here
    private    DefaultTreeCellRenderer   beanTreeCellRenderer;
    private    DefaultTreeCellRenderer   attributeTreeCellRenderer;
    private    DefaultTreeCellRenderer   modelTreeCellRenderer;
    private    DefaultTreeCellRenderer   modelCollectionTreeCellRenderer;
    
    private    DefaultTreeCellRenderer   eorganismTreeCellRenderer;
    private    DefaultTreeCellRenderer   tableTreeCellRenderer;
    private    DefaultTreeCellRenderer   enterpriseTreeCellRenderer;       
   
    private    DefaultTreeModel          enterpriseTreeModel;
    private    JTree                     enterpriseTreeTemp;
    private    DefaultMutableTreeNode    tableRootNode;
    
    private    DefaultMutableTreeNode    eorganismRootNode;    
    // dec 07
    public  JTree  companyTree             = new JTree();//JTree
    public  JTree  applicationTree         = new JTree();//JTree
    public  JTree  databaseTree            = new JTree();//JTree
    
    private     DefaultMutableTreeNode   profileRootNode;
    private     DefaultMutableTreeNode   companyRootNode;
    private     DefaultMutableTreeNode   applicationRootNode;
    private     DefaultMutableTreeNode   databaseRootNode;

    private    DefaultTreeCellRenderer   profileTreeCellRenderer;
    private    DefaultTreeModel          profileTreeModel;
    private    DefaultTreeModel          companyTreeModel;
    private    DefaultTreeModel          applicationTreeModel;
    private    DefaultTreeModel          databaseTreeModel;
    
    private    JTree                     profileTreeTemp;
    private    JTree                     companyTreeTemp;
    private    JTree                     applicationTreeTemp;
    private    JTree                     databaseTreeTemp;
    
    private    DefaultTreeCellRenderer   companyTreeCellRenderer;
    private    DefaultTreeCellRenderer   applicationTreeCellRenderer;
    private    DefaultTreeCellRenderer   databaseTreeCellRenderer;
    //
    public  	Vector                   modelOfEOrganism    			= new Vector();// EOrganism
    public  	Vector  				 modelMetadataDatabases   		 = new Vector();
    public  	Vector                   modelOfEDomain    			= new Vector();// EDomain
    public  	Vector                   modelOfESubdomain    		= new Vector();// ESubdomain
    public  	Vector                   modelOfESystem    				= new Vector();// ESystem
    public  	Vector                   modelOfESystemApplication    	= new Vector();// ESystem or EApplications
    public  	Vector                   modelOfJSystemDatabase    		= new Vector();// ESystem or EApplications
    public  	Vector                   modelOfEApplication    		= new Vector();// EApplication
    public      Vector                   modelOfEApplicationModel       = new Vector();// EApplication   
    public      Vector                   modelOfEApplicationPhysical    = new Vector();// EApplication
    public      Vector                   modelOfEApplicationLogical     = new Vector();// EApplication
    
    public  	Vector                   modelOfEEnvironment    		= new Vector();// EEnvironment
    public  	Vector                   modelOfEnvApplication    		= new Vector();// Env EApplication
    public  	Vector                   modelOfERelease    			= new Vector();// ERelease
    public      Vector                   modelOfEProcess                = new Vector();// EProcess
    public      Vector                   modelOfEGroup                  = new Vector();// EGroup
    public      Vector                   modelOfEPerson                 = new Vector();// EPerson
   
    public  	Vector                   modelOfEComponents    			= new Vector();// EComponent
    public  	Vector                   modelOfJDatabases    			= new Vector();// JDatabase
    public      Vector                   modelOfEComponentsPhysical     = new Vector();// EComponent
    public      Vector                   modelOfJDatabasesPhysical       = new Vector();// JDatabase
    public  	Vector                   modelOfEHardwares    			= new Vector();// EHardware
    public  	Vector                   modelOfEOwners    				= new Vector();// EPerson
    public  	Vector                   modelOfEOperations    			= new Vector();// EService
    //
    public  	ArrayList                personArrayList                = new ArrayList(); //EPerson
    public  	ArrayList                businessUnitArrayList          = new ArrayList(); //EBusinessUnit
    public  	ArrayList                businessServiceArrayList       = new ArrayList(); //EBusinessService
    //
    public      ArrayList                systemArrayList  				= new ArrayList(); //ESystem
    public      ArrayList                systemInventoryArrayList  		= new ArrayList(); //ESystem
    public  	ArrayList                serviceArrayList               = new ArrayList(); //EService
    public  	ArrayList 				 operationsArrayList 			= new ArrayList(); //EInterface
    public      ArrayList                tableArrayList               	= new ArrayList(); //ETable
    public      ArrayList                columnArrayList               	= new ArrayList(); //EColumn
    public      ArrayList                applicationInventoryArrayList  = new ArrayList(); //EApplication
    public      ArrayList                serverInventoryArrayList       = new ArrayList(); //EHardware
    public      ArrayList                hardwareInventoryArrayList      = new ArrayList(); //EHardware
    public      ArrayList                databaseInventoryArrayList      = new ArrayList(); //EDatabasePhysical
    public      ArrayList                middlewareInventoryArrayList    = new ArrayList(); //
    
    public      ArrayList                locationMappingArrayList 		= new ArrayList();   	
    
    public      ArrayList      			interfacesArrayListTemp 		= new ArrayList();		// EInterface
    public      ArrayList      			schemasArrayListTemp 			= new ArrayList();		// ESchema
    public      ArrayList      			schemasArrayListTemp2 			= new ArrayList();		// ESchema
    public      ArrayList     	 		complexTypeArrayListTemp 		= new ArrayList();		// EComplexType
    public      ArrayList      			elementsArrayListTemp 			= new ArrayList();		// EElements
    
    public      Vector                  tempECommanderVector            = new Vector();
    public      ArrayList               tempECommanderArrayList         = new ArrayList();
     
    public  	Vector                   modelOfESequenceFlow       = new Vector();// ESequenceFlow
    public      Vector                   eorganismPanelVector       = new Vector();// Vector of JSplitPane to display each of the modelOfBeans
    public		EOrganism 		 		 eorganism 					= new EOrganism();
    
    public  	Vector                  modelOfEFileVector      	= new Vector();// EFile
    public  	ArrayList 				securityCertificatesTemp 	= new ArrayList();
      
    public   	Vector  				tempApplicationsVector 	= new Vector();
    public   	Vector  				tempEnvironmentsVector 	= new Vector();
    public      Vector                  eDBConnectionVector = new Vector();// EDBConnection
    public      EDBConnection           eDBConnectionTemp;
    
    public      URL                     url;
    public      URLConnection           urlConnection;
    public      HttpsURLConnection      urlHttpsConnection;
    
    //https stuff
    public      URL 					urlHttps 				= null;
    public      HttpsURLConnection 		httpsURLConnection 		= null;
    public      String 					https_url				 = "";
    public      BufferedReader 			buferredReader 			= null;
    
    private LookupApplicationFrame      			addApplicationsFrame;
    
    private MainFormEOrganismComponentsFrame      	addComponentsFrame;
    
    private EApplication                 application             = new EApplication();
    
    Vector 		treeEorganisms   =null;
    
    private Document document; // org.w3c.dom.Document for XML manipulations
    
    private JEditorPane                      databaseSchemaEditor        = new JEditorPane();
    private JEditorPane                      columnsEditor               = new JEditorPane();
    private JScrollPane                      databaseSchemaEditorScroll  = new JScrollPane();
    
    //public  JTree                            enterpriseTree          = new JTree();//JTree
    public  JScrollPane                      enterpriseScrollPane    = new JScrollPane();

    public  JTree                            profileTree             = new JTree();//JTree
    public  JScrollPane                      profileScrollPane       = new JScrollPane();
    
    public  JScrollPane                      attributeScrollPane     = new JScrollPane();
    public  JScrollPane                      tablesScrollPane        = new JScrollPane();

   // private JTabbedPane                      eorganismTabbedPane;
    
    // database
    private  StringBuffer 			results;
    private  OracleDataSource 		oracleDataSource ;
    private  MysqlDataSource 		mySQLDataSource ;
    
    private  OracleDataSource 		oracleDataSourceTemp ;
    private  MysqlDataSource 		mySQLDataSourceTemp ;
    private  Connection 			connection;
    private  Connection 			connectionTemp;
    private  Connection 			connectionWorker;
   
    private  Connection             connections[];
    private  OracleDataSource       dataSources[] ;
    private  Vector  connectionsVector = new Vector();// EDBConnection
    
    private  Connection connectionSeq;
    private  Statement statement;
    private  ResultSet resultSet;
    private  int result;
    private  int nextValue;
    
    public String 						printEOrganismXMLString 	="";
    public String 						fileContentStringTemp		="";
    public File 						fileForPrint;
    public FileOutputStream 	fileOutputForPrint;
    
    ArrayList<String > types = new ArrayList(Arrays.asList("applicationcmdb", "applicationits","applicationmodel","database","component","hardware","sequence","person"));
    
   public InputStream 			inputStream;
   public InputStream 			inputStreamFileTemp;
   public BufferedReader 		bufferedReader;
   public BufferedReader 		br       		= null;  
   public String 				inputLine;
   public FileWriter 			fileWriter;
   public BufferedWriter 		bufferWritter;    
   public File 					file;
   public java.sql.Date 		today = new java.sql.Date(System.currentTimeMillis());
   
   // parsing methods
   public String[]  			operationsArrayString;
   public Set<String> 			set;
   public int 					end=0;
   public String[]  			operationsArrayStringNew;
   public Iterator 				iterator;
   public int 					i=0;
   public int 					size=0;
   
   public URL 					url1;
	
   public  BufferedReader 		in;
   
   public  String returnString	="";
   public  String inputLine1	="";
   
   public  int 	eid 		    =  0; // eorganism ID
    
   private EEnvironmentConfig envConfigEOrganismController = new EEnvironmentConfig();
   
   public EEnvironmentConfig getEnvConfigEOrganismController(){ return envConfigEOrganismController;}
   public void   setEnvConfigEOrganismController( EEnvironmentConfig _envConfigEOrganismController){ envConfigEOrganismController = _envConfigEOrganismController;};
   
    private Vector envConfigEOrganismControllerVector = new Vector();
    public Vector getEnvConfigEOrganismControllerVector(){ return envConfigEOrganismControllerVector;}
    public void   setEnvConfigEOrganismControllerVector( Vector _envConfigEOrganismControllerVector){ envConfigEOrganismControllerVector = _envConfigEOrganismControllerVector;};
	
    public void JEOrganismController(){
        setEnvConfigEOrganismControllerVector(ConfigFrame.environmentConfigVector); 
        initArrays();
        databaseInitializer();
       
    } // end constructor
    
    //database initializer
    public void databaseInitializer(){
         try{
                    
        	//System.out.println("###ConfigFrame.databaseSelected####"+"::databaseInitializer: is : " + ConfigFrame.databaseSelected);
              
        	 
            if(ConfigFrame.databaseSelected.equals(ConfigFrame.ORACLE)){
            	
            		oracleDataSource = new OracleDataSource();
              		oracleDataSource.setURL(EOrganismConfig.eorganismConfig.getProperty("eorganism.datasource.url"));
             	     
                    connection      = oracleDataSource.getConnection(EOrganismConfig.eorganismConfig.getProperty("eorganism.datasource.username"), EOrganismConfig.eorganismConfig.getProperty("eorganism.datasource.password"));
                    //System.out.println("###ORACLE####"+"::databaseInitializer:: database : " + ConfigFrame.databaseSelected);
            
            }else if(ConfigFrame.databaseSelected.equals(ConfigFrame.MYSQL)){
            	
            		mySQLDataSource = new MysqlDataSource();
	            	 	
	            	mySQLDataSource.setURL(EOrganismConfig.eorganismConfig.getProperty("eorganism.datasource.url"));
	            	mySQLDataSource.setUser(EOrganismConfig.eorganismConfig.getProperty("eorganism.datasource.username"));
	            	mySQLDataSource.setPassword(EOrganismConfig.eorganismConfig.getProperty("eorganism.datasource.password"));
	                    
            	 	connection      = mySQLDataSource.getConnection();
            	 	//System.out.println("###MYSQL####"+"::databaseInitializer:: database : " + ConfigFrame.databaseSelected);
                      
            }
            
            
            if(ConfigFrame.databaseSelected.equals(ConfigFrame.ORACLE)){
            	
        		oracleDataSource = new OracleDataSource();
          		oracleDataSource.setURL(EOrganismConfig.eorganismConfig.getProperty("eorganism.datasource.url"));
         	     
                connectionSeq      = oracleDataSource.getConnection(EOrganismConfig.eorganismConfig.getProperty("eorganism.datasource.username"), EOrganismConfig.eorganismConfig.getProperty("eorganism.datasource.password"));
                //System.out.println("###ORACLE####"+"::databaseInitializer SEQ:: database : " + ConfigFrame.databaseSelected);
        
            }else if(ConfigFrame.databaseSelected.equals(ConfigFrame.MYSQL)){
        	
        		mySQLDataSource = new MysqlDataSource();
            	 	
            	mySQLDataSource.setURL(EOrganismConfig.eorganismConfig.getProperty("eorganism.datasource.url"));
            	mySQLDataSource.setUser(EOrganismConfig.eorganismConfig.getProperty("eorganism.datasource.username"));
            	mySQLDataSource.setPassword(EOrganismConfig.eorganismConfig.getProperty("eorganism.datasource.password"));
                    
            	connectionSeq      = mySQLDataSource.getConnection();
        	 	//System.out.println("###MYSQL####"+"::databaseInitializer SEQ:: database : " + ConfigFrame.databaseSelected);
                  
            }
            
            
            //System.out.println("JEOrganismcontroller - databaseInitializer CONFIG :OK");
            
            
        }catch(SQLException sqle){
            JOptionPane.showMessageDialog( null,
                                           "databaseInitializer :: SQLException  "    + sqle.toString()+"\n");
            log4j.error("databaseInitializer:: SQLException: " + sqle.toString());
        }catch(Exception e){
            JOptionPane.showMessageDialog( null,
                                           "databaseInitializer:: Exception: " +e.toString()+"\n");
            log4j.error("databaseInitializer:: Exception: " + e.toString());
            
        }// end catch
        
        //JOptionPane.showMessageDialog( null, "databaseInitializer:: OK: ");
           
    }// databaseInitializer
    
    public Connection createDatabaseConnection(String _url, String _username, String _password){
        try	{
            	mySQLDataSourceTemp = new MysqlDataSource();
            	mySQLDataSourceTemp.setURL(_url);
            	mySQLDataSourceTemp.setUser(_username);
            	mySQLDataSourceTemp.setPassword(_password);
                 
         	 	connectionTemp      = mySQLDataSourceTemp.getConnection();

        }catch(SQLException sqle){
           JOptionPane.showMessageDialog( null,
                                          "createDatabaseConnection :: SQLException  "    + sqle.toString()+"\n");
           	log4j.debug("createDatabaseConnection:: SQLException: " + sqle.toString());
           	log4j.error("createDatabaseConnection:: SQLException: " + sqle.toString());
        }catch(Exception e){
           JOptionPane.showMessageDialog( null,
                                          "createDatabaseConnection:: SQLException: " +e.toString()+"\n");
           	log4j.debug("createDatabaseConnection:: SQLException : " + _url + ": " + _username +": "+  _password);
        	log4j.error("createDatabaseConnection:: SQLException : " + _url + ": " + _username +": "+  _password);
            
        }// end catch
        	log4j.debug("createDatabaseConnection:: retrun OK for : " + _url + ": " + _username +": "+  _password);
        	log4j.error("createDatabaseConnection:: retrun OK for : " + _url + ": " + _username +": "+  _password);
        
        return connectionTemp;
        
    }//createDatabaseConnection
    
    //checkDatabaseConnection
    public void checkConnection(){
    	
         try{
             //if connection is opened
             if((connection ==null) | (connection.isClosed())) {
            	        //System.out.println("checkDatabase:: _connection ==null or _connection.isClosed()" + connection);

		            	 mySQLDataSource = new MysqlDataSource();
		            	 mySQLDataSource.setURL(EOrganismConfig.eorganismConfig.getProperty("eorganism.datasource.url"));
		            	 mySQLDataSource.setUser(EOrganismConfig.eorganismConfig.getProperty("eorganism.datasource.username"));
		            	 mySQLDataSource.setPassword(EOrganismConfig.eorganismConfig.getProperty("eorganism.datasource.password"));
		                     
	             	 	connection      = mySQLDataSource.getConnection();
	             	 	//log4j.debug("checkDatabase:: _connection ==null or _connection.isClosed() and connection reopened MYSQL" + connection);
	             	 	 
	             	 	//if(!(connection ==null)){System.out.println("#### AFTER RESET checkDatabase:: connection NOT null" + connection);}
	                	//if(!(connection.isClosed())){System.out.println("#### AFTER RESET checkDatabase:: _connection NOT Closed()" + connection);}
	                  
             }
           
        }catch(SQLException sqle){
            JOptionPane.showMessageDialog( null,
                                           "checkDatabaseConnection :: SQLException  "    + sqle.toString()+"\n");
            log4j.error("checkDatabaseConnection:: SQLException: " + sqle.toString());
            log4j.debug("checkDatabaseConnection:: SQLException: " + sqle.toString());
        }catch(Exception e){
            JOptionPane.showMessageDialog( null,
                                           "checkDatabaseConnection:: Exception: " +e.toString()+"\n");
            log4j.error("checkDatabaseConnection:: Exception: " + e.toString());
            log4j.debug("checkDatabaseConnection:: Exception: " + e.toString());
        }// end catch
        
        //JOptionPane.showMessageDialog( null, "checkDatabase:: OK: ");
           
    }// checkDatabaseConnection

    public void JEOrganismController(String argFileName ){
       //set this xml file name per each JEOrganismController instance created
       // eorganism.setEOrganismXMLFile( argFileName);
    } // end constructor

    public void reset(){
    }

    public void setJEOrganismController(boolean booleanArg){
    
    	createEOrganismTreeVectors();
        initArrays();
        databaseInitializer();
    } // end setter

    public void setJEOrganismControllerNew(){
    } // end setter

    public void setJEOrganismControllerLookup(boolean booleanArg){
    } // end setter

    public void setJEOrganismController(Vector models){
    } // end setJEOrganismController
      
	
    public JInternalFrame createInternalFrame(){
        
            // create FileSystemModel for given directory
            fileSystemModel = new FileSystemModel( new File( ConfigFrame.sourceGeneratedPath + "/applications"));

            // create JTree for FileSystemModel
            fileTree = new JTree( fileSystemModel );

            fileTree.setFont(fontClasses);

           // source editor
            fileEditor.setContentType("text/html");

            fileEditor.setFont(fontEditor);
            fileEditor.setEditable(false);


            // make JTree editable for renaming Files
            fileTree.setEditable( true );


            verticalLeftSplitPanel =   new JSplitPane(
                                      JSplitPane.VERTICAL_SPLIT,
                                      true,
                                      new JScrollPane( fileTree ),
                                      new JScrollPane( fileEditor ) );

           verticalLeftSplitPanel.setDividerSize(7);
           verticalLeftSplitPanel.setDividerLocation(300);
           verticalLeftSplitPanel.setOneTouchExpandable( true );
           
           
             // register lisneners with local buttons
             internalButtonsArray[SAVE_DATABASE].addActionListener ( saveDatabaseAction);
             internalButtonsArray[DELETE_DATABASE].addActionListener( deleteDatabaseAction);
             internalButtonsArray[ADD_APPLICATIONS].addActionListener( addApplicationsAction);
             internalButtonsArray[ADD_COMPONENTS].addActionListener( addComponentsAction);
     
		     internalButtonsArray[LOAD_WSDL_METADATA].addActionListener( loadWsdlMetadataAction);
		     internalButtonsArray[LOAD_DB_METADATA].addActionListener( loadDbMetadataAction);
		     internalButtonsArray[LOAD_APPLICATION_INVENTORY].addActionListener( loadApplicationInventoryAction);
		     
		     
		     internalButtonsArray[DELETE_WSDL_METADATA].addActionListener( deleteWsdlMetadataAction);
		     internalButtonsArray[DELETE_DB_METADATA].addActionListener( deleteDbMetadataAction);
            
             // add the local buttons
             for( int i =0 ; i < INTERNAL_BUTTONS_NUMBER; i++){    
                    toolBar.add(internalButtonsArray[i]);
             }
        
             toolBar.putClientProperty("JToolBar.isRollover", Boolean.TRUE);
    
             // creates the JPopUpMenu
             popupMenu.add( saveDatabaseAction );
             popupMenu.add( deleteDatabaseAction );
             popupMenu.add( new JPopupMenu.Separator() );
             popupMenu.add( addApplicationsAction );
             popupMenu.add( addComponentsAction );
             
		     popupMenu.add( deleteWsdlMetadataAction );
		     popupMenu.add( deleteDbMetadataAction );
		     popupMenu.add( loadWsdlMetadataAction );
		     popupMenu.add( loadDbMetadataAction );
		     popupMenu.add( loadApplicationInventoryAction );
                      
              enterpriseTree = getEOrganismTree();
              enterpriseTree.setFont(fontClasses);
        
      //leftTabbedPane
      setDescriptorsLeftTabs();
      
      //set eorganismTabbedPane
      createEOrganismTabbedPane();
      
     internalFrame        = new JInternalFrame(   getEnvConfigEOrganismController().getFrameFullText()+ "EORGANISM ::  Enterprise MetaModel :: Enterprise MetaProcess:: Browse ##DB" + ConfigFrame.databaseSelected,
                                                   true,
                                                   true,
                                                   true,
                                                   true);

     horizontalSplitPanel = new JSplitPane( JSplitPane.HORIZONTAL_SPLIT,
                                            true,
                                            leftTabbedPane,
                                            eorganismTabbedPane);

     // add buttons for expanding/contracting divider
     horizontalSplitPanel.setOneTouchExpandable( true );
     horizontalSplitPanel.setDividerLocation(180);
     horizontalSplitPanel.setDividerSize(7);

     //
     internalFrame.setForeground(Color.CYAN);
    // internalFrame.setFrameIcon((Icon)iconApplication);
     //internalFrame.setIconImage( iconApplication.getImage());
     internalFrame.setLocation(10,5);
     internalFrame.setSize(920,620);

     internalFrame.setVisible(true);

     containerInternal =   internalFrame.getContentPane();

     containerInternal.add( toolBar, BorderLayout.NORTH);
     containerInternal.add( horizontalSplitPanel );
     
     toolBar.addMouseListener( new MouseAdapter(){

                  public void mousePressed(MouseEvent me){

                	  		log4j.debug("MOUSE EVENT POP UP TRIGGER");

                          verifyTriggerEvent(me);

                  }

                  public void mouseRelease(MouseEvent me){

                          verifyTriggerEvent(me);

                  }

                  public void mouseClicked(MouseEvent me){

                          verifyTriggerEvent(me);

                  }

                  private void verifyTriggerEvent(MouseEvent mouseEvent){

                	  log4j.debug("MOUSE EVENT POP UP TRIGGER :: VERIFY TRIGGER");

                      if(mouseEvent.getClickCount()==2){

                    	  log4j.debug("MOUSE EVENT POP UP TRIGGER :: IS POPUP TRIGGER");
                          popupMenu.show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());

                      }else{

                    	  log4j.debug("MOUSE EVENT POP UP TRIGGER :: IS NOT POPUP TRIGGER !!!");

                      }
                  }


          }
          );

  
     	return internalFrame;

     }// end createInternalFrame
	 
	 
	 public void setInternalFrameTitle(){
                 
         internalFrame.setTitle( "EORGANISM :: OPEN ENTERPRISE METAMODEL" );
     
	 }// end setInternalFrameTitle
	 

         public void initArrays(){

                internalButtonsArray             = new JButton[INTERNAL_BUTTONS_NUMBER];
                internalToolTixTextArray         = new String[INTERNAL_BUTTONS_NUMBER];
                internalIconImageArray           = new ImageIcon[INTERNAL_BUTTONS_NUMBER];

                 for(int i = 0; i < INTERNAL_BUTTONS_NUMBER; i++){

                      internalButtonsArray[i]                = new JButton();
                      internalToolTixTextArray[i]            = "";
                      internalIconImageArray[i]              = new ImageIcon();

                }// for all the internal frame buttons
                      
                 internalIconImageArray[SAVE_DATABASE]              = new ImageIcon( getClass().getResource( "../../../../images/save16.gif" ));
                 internalIconImageArray[DELETE_DATABASE]            = new ImageIcon( getClass().getResource( "../../../../images/jndi_remove.gif" ));
                 internalIconImageArray[LOAD_DATABASE]              = new ImageIcon( getClass().getResource( "../../../../images/SaveAll16.gif" ));
                 internalIconImageArray[BACKUP_DATABASE]            = new ImageIcon( getClass().getResource( "../../../../images/deploy_application_16.gif" ));
                 internalIconImageArray[EXPORT_DATABASE]            = new ImageIcon( getClass().getResource( "../../../../images/j2ee_server_16.gif" ));
                 internalIconImageArray[ADD_APPLICATIONS]           = new ImageIcon( getClass().getResource( "../../../../images/j2ee_server_16.gif" ));
                 internalIconImageArray[ADD_COMPONENTS]              = new ImageIcon( getClass().getResource( "../../../../images/j2ee_server_16.gif" ));
            
	             internalIconImageArray[DELETE_WSDL_METADATA]           = new ImageIcon( getClass().getResource( "../../../../images/jndi_remove.gif" ));
	             internalIconImageArray[DELETE_DB_METADATA]             = new ImageIcon( getClass().getResource( "../../../../images/jndi_remove.gif" ));
	             internalIconImageArray[LOAD_WSDL_METADATA]             = new ImageIcon( getClass().getResource( "../../../../images/SaveAll16.gif" ));
	             internalIconImageArray[LOAD_DB_METADATA]                = new ImageIcon( getClass().getResource( "../../../../images/SaveAll16.gif" ));
	             internalIconImageArray[LOAD_APPLICATION_INVENTORY]      = new ImageIcon( getClass().getResource( "../../../../images/deploy_application_16.gif" ));
	           
                 internalToolTixTextArray[SAVE_DATABASE]                = "SAVE_DATABASE";
                 internalToolTixTextArray[DELETE_DATABASE]              = "DELETE_DATABASE";
                 internalToolTixTextArray[LOAD_DATABASE]                = "LOAD_DATABASE";
                 internalToolTixTextArray[BACKUP_DATABASE]              = "BACKUP_DATABASE";
                 internalToolTixTextArray[EXPORT_DATABASE]             = "EXPORT_DATABASE";
                 internalToolTixTextArray[ADD_APPLICATIONS]            = "ADD_APPLICATIONS";
                 internalToolTixTextArray[ADD_COMPONENTS]              = "ADD_COMPONENTS";

	             internalToolTixTextArray[DELETE_WSDL_METADATA]             = "DELETE_WSDL_METADATA";
	             internalToolTixTextArray[DELETE_DB_METADATA]               = "DELETE_DB_METADATA";
	             internalToolTixTextArray[LOAD_WSDL_METADATA]               = "LOAD_WSDL_METADATA";
	             internalToolTixTextArray[LOAD_DB_METADATA]                 = "LOAD_DB_METADATA";
	             internalToolTixTextArray[LOAD_APPLICATION_INVENTORY]       = "LOAD_APPLICATION_INVENTORY";

                 for(int i = 0; i < INTERNAL_BUTTONS_NUMBER; i++){

                      internalButtonsArray[i].setToolTipText(internalToolTixTextArray[i]);
                      internalButtonsArray[i].setIcon(internalIconImageArray[i]);

                }// for all buttons


         } // end initArrays
          

     public void loadEOrganismFromDB(){
        	
        	String tempStringDatabase	="";
        	String tempStringTable		="";
        	String tempStringColumn	="";
        
            EOrganism          tempJEOrganism              = new EOrganism();
            EDomain            tempDomain              	   = new EDomain();
            ESubdomain         tempSubdomain               = new ESubdomain();
            
            ESystem            tempSystem              	   = new ESystem();
            
            EApplication       tempApplication             = new EApplication();
            EApplication       tempApplication1             = new EApplication();
            
            EApplication       tempEnvApplication          = new EApplication();
            EParameter         tempEnvParameter            = new EParameter();
            
            EComponent         tempComponent;
            JDatabase          tempDatabase;
            EHardware          tempHardware;
            EBusinessUnit  				tempBusinessFunction;
            ESequenceFlow      			tempSequenceFlow;
            EOperation             		tempOperation;
            EEnvironment           		tempEnvironment;
            ERelease               		tempRelease;
            EProject               		tempProject;
            EProcess               		tempProcess;
            EGroup                 		tempGroup;
            EPerson                tempPerson;
            EInterface             tempInterface;
            EAttribute            tempAttribute;
            ETable                tempTable;
            EColumn               tempColumn;
            
            Vector     tempComponentsVector;
            ArrayList  tempSubdomainsArrayList					   			= new ArrayList();
            ArrayList  tempSystemApplicationArrayList					   = new ArrayList();
            ArrayList  tempApplicationArrayList                            = null;
            ArrayList  tempComponentsArrayList                             = null;
            ArrayList  tempDatabasesArrayList                              = new ArrayList();
            ArrayList  tempHardwaresArrayList                              = new ArrayList();
            ArrayList  tempBusinesFunctionArrayList                        = new ArrayList();
            ArrayList  tempSequenceFlowArrayList                           = new ArrayList();
            ArrayList  tempOperationArrayList                              = new ArrayList();
            ArrayList  tempServiceArrayList                                = new ArrayList();
            ArrayList  tempInterfaceArrayList                              = new ArrayList();
            ArrayList  tempAttributeArrayList                              = new ArrayList();
            ArrayList  tempOperationArrayListWorker                        = new ArrayList();
            ArrayList  tempInterfaceArrayListWorker                        = new ArrayList();
            ArrayList  tempAttributeArrayListWorker                        = new ArrayList();
            
            ArrayList  tempEnvironmentArrayList                            = new ArrayList();
            ArrayList  tempEnvApplicationArrayList                         = new ArrayList();
            Vector     tempEnvApplicationVector                            = new Vector();
            Vector     tempEnvParameterVector                              = new Vector();
            
            Vector     tempProjectVector                                   = new Vector();
            ArrayList  tempReleaseArrayList                                = new ArrayList();
            ArrayList  tempProjectArrayList                                = new ArrayList();
     
            ArrayList  tempMetadataTablesArrayList                         = new ArrayList();
            ArrayList  tempMetadataColumnsArrayList                        = new ArrayList();
              
            Statement         selectStatementDomain                    		;
            Statement         selectStatementSubdomain                    	;
            
            Statement         selectStatementSystem                    		;
            Statement         selectStatementApplication                    ;
            Statement         selectStatementMetadataDatabases              ;
            Statement         selectStatementEnvironments					;
            Statement         selectStatementOwners							;
            Statement         selectStatementPersons						;
             
            
            PreparedStatement selectPreparedStatementSubdomain				;
            PreparedStatement selectPreparedStatementApplicationPhysical    ;
            PreparedStatement selectPreparedStatementComponent              ;
            PreparedStatement selectPreparedStatementDatabase               ;
            PreparedStatement selectPreparedStatementDatabasePhysical       ;
            PreparedStatement selectPreparedStatementHardware               ;
            PreparedStatement selectPreparedStatementService                ;
            PreparedStatement selectPreparedStatementInterface              ;
            PreparedStatement selectPreparedStatementAttribute              ;
            PreparedStatement selectPreparedStatementMetadataTable			;
            PreparedStatement selectPreparedStatementMetadataColumn			;
            PreparedStatement selectPreparedStatementSequenceFlow			;
             
            
            ResultSet resultSetDomains;
            ResultSet resultSetSubdomains;
            ResultSet resultSetSystems;
            ResultSet resultSetApplications;
            ResultSet resultSetComponents;
            ResultSet resultSetEnvironments;
             
            ResultSet resultSetDatabases;
            ResultSet resultSetMetadataDatabases;
            ResultSet resultSetMetadataTable;
            ResultSet resultSetMetadataColumn;
            
            ResultSet resultSetHardwares;
            ResultSet resultSetSequenceFlow;
            ResultSet resultSetServices;
            ResultSet resultSetInterfaces;
            ResultSet resultSetAttributes;
            ResultSet resultSetOwners;
            ResultSet resultSetPersons;
            
            
            int applicationsCount    = 1;
            int componentsCount      = 1;
            int databasesCount       = 1;
            int hardwaresCount       = 1;
            int servicesCount        = 1;
            
            databaseInitializer();
            checkConnection();
             
            //reset all
            modelOfEDomain 			= new Vector();
            modelOfESubdomain 		= new Vector();
             
            modelOfESystem 			= new Vector();
            modelOfEApplication 	= new Vector();
            modelOfEEnvironment 	= new Vector();
            modelOfEHardwares 		= new Vector();
            modelOfESequenceFlow	= new Vector();
            modelMetadataDatabases	= new Vector();
            modelOfEOwners			= new Vector();
            
            try{
            		
            		selectStatementDomain                  		= connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);      
              		selectStatementSubdomain                  	= connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);      
            	
            		selectStatementApplication                  = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);      
            		selectStatementSystem                  		= connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);  
                	selectStatementMetadataDatabases            = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);                
                    selectStatementEnvironments					= connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);               
                    selectStatementOwners						= connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    selectStatementPersons						= connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    
                   
                    //TEST
                    //resultSetApplications                     =  selectStatementApplication.executeQuery("SELECT EORGANISM_ID, EAPPLICATION_NAME, EAPPLICATION_DESCRIPTION  FROM EAPPLICATION ORDER BY EAPPLICATION_NAME");
                    //resultSetApplications                     =  selectStatementApplication.executeQuery("SELECT EAPPLICATION_ID,EAPPLICATION_APPLICATION_ID, EAPPLICATION_ESIS_NAME,EAPPLICATION_ESIS,EAPPLICATION_ESIS_SYSTEM_NAME,EAPPLICATION_CLASIFICATION, EAPPLICATION_CLASIF_SERV,EAPPLICATION_CLASIF_CRITIC,EAPPLICATION_CLASIFICATION_CIM,EAPPLICATION_CLASIFICATION_PII,EAPPLICATION_CLASIFICATION_PCI,EAPPLICATION_CLASIFICATION_SOX, EAPPLICATION_TAX_IMPACT,EAPPLICATION_ARCHITECT_CRITIC, EAPPLICATION_DESC_FUNCTIONAL,EAPPLICATION_VP,EAPPLICATION_SUP_DIRECTOR,EAPPLICATION_SUP_MANAGER,EAPPLICATION_DEV_DIRECTOR,EAPPLICATION_DEV_MANAGER,EAPPLICATION_DEV_PRIME,EAPPLICATION_ISM_GROUP,EAPPLICATION_STATUS,EAPPLICATION_BUSINESS_KPI,EAPPLICATION_IS_HPD,EAPPLICATION_IS_SIS, EAPPLICATION_IS_BSM,EAPPLICATION_IS_BPM,EAPPLICATION_IS_SHO,EAPPLICATION_IS_SHR,OWNER,METADATA_TYPE,EENVIRONMENT_NAME_FK,EAPPLICATION_NAME,EAPPLICATION_DESCRIPTION,EAPPLICATION_IN,EAPPLICATION_OUT,BUSINESS_UNIT,BUSINESS_SERVICE FROM EAPPLICATION WHERE METADATA_TYPE IN ('APPLICATION','INVENTORY','PHYSICAL') ORDER BY EAPPLICATION_NAME");
                    //resultSetApplications                     =  selectStatementApplication.executeQuery("SELECT EAPPLICATION_ID,EAPPLICATION_APPLICATION_ID, EAPPLICATION_ESIS_NAME,EAPPLICATION_ESIS,EAPPLICATION_ESIS_SYSTEM_NAME,EAPPLICATION_CLASIFICATION, EAPPLICATION_CLASIF_SERV,EAPPLICATION_CLASIF_CRITIC,EAPPLICATION_CLASIFICATION_CIM,EAPPLICATION_CLASIFICATION_PII,EAPPLICATION_CLASIFICATION_PCI,EAPPLICATION_CLASIFICATION_SOX, EAPPLICATION_TAX_IMPACT,EAPPLICATION_ARCHITECT_CRITIC, EAPPLICATION_DESC_FUNCTIONAL,EAPPLICATION_VP,EAPPLICATION_SUP_DIRECTOR,EAPPLICATION_SUP_MANAGER,EAPPLICATION_DEV_DIRECTOR,EAPPLICATION_DEV_MANAGER,EAPPLICATION_DEV_PRIME,EAPPLICATION_ISM_GROUP,EAPPLICATION_STATUS,EAPPLICATION_BUSINESS_KPI,EAPPLICATION_IS_HPD,EAPPLICATION_IS_SIS, EAPPLICATION_IS_BSM,EAPPLICATION_IS_BPM,EAPPLICATION_IS_SHO,EAPPLICATION_IS_SHR,OWNER,METADATA_TYPE,EENVIRONMENT_NAME_FK,EAPPLICATION_NAME,EAPPLICATION_DESCRIPTION,EAPPLICATION_IN,EAPPLICATION_OUT FROM EAPPLICATION WHERE EAPPLICATION_NAME ='Maestro'");
                    // JAN 2, 2017
                    /*
                    resultSetSystems                       		=  selectStatementSystem.executeQuery("SELECT ESYSTEM_ID,ESYSTEM_NAME,ESYSTEM_DESCRIPTION,SYS_ID,INFRA,LAYER,CHANNEL,SEGMENT,BUSINESS_SERVICE,BUSINESS_UNIT FROM ESYSTEM ORDER BY ESYSTEM_ID;");
                    resultSetApplications                       =  selectStatementApplication.executeQuery("SELECT EAPPLICATION_ID,EAPPLICATION_APPLICATION_ID, EAPPLICATION_ESIS_NAME,EAPPLICATION_ESIS,EAPPLICATION_ESIS_SYSTEM_NAME,EAPPLICATION_CLASIFICATION, EAPPLICATION_CLASIF_SERV,EAPPLICATION_CLASIF_CRITIC,EAPPLICATION_CLASIFICATION_CIM,EAPPLICATION_CLASIFICATION_PII,EAPPLICATION_CLASIFICATION_PCI,EAPPLICATION_CLASIFICATION_SOX, EAPPLICATION_TAX_IMPACT,EAPPLICATION_ARCHITECT_CRITIC, EAPPLICATION_DESC_FUNCTIONAL,EAPPLICATION_VP,EAPPLICATION_SUP_DIRECTOR,EAPPLICATION_SUP_MANAGER,EAPPLICATION_DEV_DIRECTOR,EAPPLICATION_DEV_MANAGER,EAPPLICATION_DEV_PRIME,EAPPLICATION_ISM_GROUP,EAPPLICATION_STATUS,EAPPLICATION_BUSINESS_KPI,EAPPLICATION_IS_HPD,EAPPLICATION_IS_SIS, EAPPLICATION_IS_BSM,EAPPLICATION_IS_BPM,EAPPLICATION_IS_SHO,EAPPLICATION_IS_SHR,OWNER,METADATA_TYPE,EENVIRONMENT_NAME_FK,EAPPLICATION_NAME,EAPPLICATION_DESCRIPTION,EAPPLICATION_IN,EAPPLICATION_OUT,BUSINESS_UNIT,BUSINESS_SERVICE,TYPE,EAPP_ID,ESYS_ID_FK,ESYS_NAME FROM EAPPLICATION WHERE TYPE='APPLICATION' ORDER BY EAPPLICATION_ID");
                    */
                    resultSetDomains                       		=  selectStatementDomain.executeQuery("SELECT EDOMAIN_ID,EDOMAIN_NAME,EDOMAIN_DESCRIPTION,COBRA_EDOMAIN_ID,INFRA,LAYER,CHANNEL,SEGMENT,BUSINESS_SERVICE,BUSINESS_UNIT FROM EDOMAIN ORDER BY EDOMAIN_ID");
                     
                    resultSetSystems                       		=  selectStatementSystem.executeQuery("SELECT ESYSTEM_ID,ESYSTEM_NAME,ESYSTEM_DESCRIPTION,SYS_ID,INFRA,LAYER,CHANNEL,SEGMENT,BUSINESS_SERVICE,BUSINESS_UNIT FROM ESYSTEM WHERE SYS_ID IN ('0001','0002','0003','0004','0005','0006','0007','0008','0009','0010','0011','0012','0013','0014','0015','0016') ORDER BY ESYSTEM_ID");
                    resultSetApplications                       =  selectStatementApplication.executeQuery("SELECT EAPPLICATION_ID,EAPPLICATION_APPLICATION_ID, EAPPLICATION_ESIS_NAME,EAPPLICATION_ESIS,EAPPLICATION_ESIS_SYSTEM_NAME,EAPPLICATION_CLASIFICATION, EAPPLICATION_CLASIF_SERV,EAPPLICATION_CLASIF_CRITIC,EAPPLICATION_CLASIFICATION_CIM,EAPPLICATION_CLASIFICATION_PII,EAPPLICATION_CLASIFICATION_PCI,EAPPLICATION_CLASIFICATION_SOX, EAPPLICATION_TAX_IMPACT,EAPPLICATION_ARCHITECT_CRITIC, EAPPLICATION_DESC_FUNCTIONAL,EAPPLICATION_VP,EAPPLICATION_SUP_DIRECTOR,EAPPLICATION_SUP_MANAGER,EAPPLICATION_DEV_DIRECTOR,EAPPLICATION_DEV_MANAGER,EAPPLICATION_DEV_PRIME,EAPPLICATION_ISM_GROUP,EAPPLICATION_STATUS,EAPPLICATION_BUSINESS_KPI,EAPPLICATION_IS_HPD,EAPPLICATION_IS_SIS, EAPPLICATION_IS_BSM,EAPPLICATION_IS_BPM,EAPPLICATION_IS_SHO,EAPPLICATION_IS_SHR,OWNER,METADATA_TYPE,EENVIRONMENT_NAME_FK,EAPPLICATION_NAME,EAPPLICATION_DESCRIPTION,EAPPLICATION_IN,EAPPLICATION_OUT,BUSINESS_UNIT,BUSINESS_SERVICE,TYPE,EAPP_ID,ESYS_ID_FK,ESYS_NAME FROM EAPPLICATION WHERE TYPE='APPLICATION' AND EAPPLICATION_ID < 400 ORDER BY EAPPLICATION_ID");
                  
                    // fo all subdomains
                    selectPreparedStatementSubdomain 			= connection.prepareStatement("SELECT COBRA_ESUBDOMAIN_ID,ESUBDOMAIN_NAME,ESUBDOMAIN_DESCRIPTION FROM ESUBDOMAIN WHERE COBRA_EDOMAIN_ID_FK=?");
                    
                    // FOR ALL THE COMP MODELS
                    selectPreparedStatementComponent            = connection.prepareStatement("SELECT * FROM ECOMPONENT  WHERE METADATA_TYPE IN ('MODEL','INVENTORY') AND EAPPLICATION_NAME_FK=?"); 
                                       
                    //resultSetApplications                       =  selectStatementApplication.executeQuery("SELECT EAPPLICATION_ID,EAPPLICATION_APPLICATION_ID, EAPPLICATION_ESIS_NAME,EAPPLICATION_ESIS,EAPPLICATION_ESIS_SYSTEM_NAME,EAPPLICATION_CLASIFICATION, EAPPLICATION_CLASIF_SERV,EAPPLICATION_CLASIF_CRITIC,EAPPLICATION_CLASIFICATION_CIM,EAPPLICATION_CLASIFICATION_PII,EAPPLICATION_CLASIFICATION_PCI,EAPPLICATION_CLASIFICATION_SOX, EAPPLICATION_TAX_IMPACT,EAPPLICATION_ARCHITECT_CRITIC, EAPPLICATION_DESC_FUNCTIONAL,EAPPLICATION_VP,EAPPLICATION_SUP_DIRECTOR,EAPPLICATION_SUP_MANAGER,EAPPLICATION_DEV_DIRECTOR,EAPPLICATION_DEV_MANAGER,EAPPLICATION_DEV_PRIME,EAPPLICATION_ISM_GROUP,EAPPLICATION_STATUS,EAPPLICATION_BUSINESS_KPI,EAPPLICATION_IS_HPD,EAPPLICATION_IS_SIS, EAPPLICATION_IS_BSM,EAPPLICATION_IS_BPM,EAPPLICATION_IS_SHO,EAPPLICATION_IS_SHR,OWNER,METADATA_TYPE,EENVIRONMENT_NAME_FK,EAPPLICATION_NAME,EAPPLICATION_DESCRIPTION,EAPPLICATION_IN,EAPPLICATION_OUT,BUSINESS_UNIT,BUSINESS_SERVICE FROM EAPPLICATION WHERE METADATA_TYPE IN ('APPLICATION', 'COMPONENT', 'INVENTORY','PHYSICAL')  ORDER BY EAPPLICATION_NAME");
                    
                    // FOR ALL Services
                    selectPreparedStatementService              = connection.prepareStatement("SELECT ESERVICE_NAME,ESERVICE_TYPE,EAPPLICATION_NAME_FK,ECOMPONENT_NAME_FK,ESERVICE_LOCATION FROM ESERVICE WHERE EAPPLICATION_NAME_FK=?");                    
                    selectPreparedStatementInterface            = connection.prepareStatement("SELECT EINTERFACE_NAME,EINTERFACE_TYPE,EAPPLICATION_NAME_FK,ECOMPONENT_NAME_FK,ESERVICE_NAME_FK,EINTERFACE_LOCATION FROM EINTERFACE WHERE EAPPLICATION_NAME_FK=?");                
                    selectPreparedStatementAttribute            = connection.prepareStatement("SELECT EATTRIBUTE_NAME,EATTRIBUTE_TYPE,EAPPLICATION_NAME_FK,ECOMPONENT_NAME_FK,ESERVICE_NAME_FK,EINTERFACE_NAME_FK FROM EATTRIBUTE WHERE EAPPLICATION_NAME_FK=?");
                
                    //DB metadata
                    resultSetMetadataDatabases                  =  selectStatementMetadataDatabases.executeQuery("SELECT DISTINCT EMETADATA_TABLE_OWNER, EMETADATA_COMPONENT_NAME,EMETADATA_APPLICATION_NAME  FROM  EMETADATA_TABLE ORDER BY EMETADATA_TABLE_OWNER");          
                    selectPreparedStatementMetadataTable        = connection.prepareStatement("SELECT EMETADATA_TABLE_NAME FROM EMETADATA_TABLE WHERE EMETADATA_TABLE_OWNER=? ORDER BY EMETADATA_TABLE_NAME");               
                    selectPreparedStatementMetadataColumn       = connection.prepareStatement("SELECT EMETADATA_COLUMN_NAME,EMETADATA_COLUMN_TYPE FROM EMETADATA_COLUMN WHERE EMETADATA_TABLE_NAME=? ORDER BY EMETADATA_COLUMN_NAME");
                    
                   // FOR ALL THE DB MODELS
                    selectPreparedStatementDatabase             = connection.prepareStatement("SELECT * FROM EDATABASE WHERE METADATA_TYPE IN ('MODEL','INVENTORY') AND EAPPLICATION_NAME_FK=?");
                    // FOR ALL HW
                    selectPreparedStatementHardware             = connection.prepareStatement("SELECT EHARDWARE_PHYSICAL_ID, EHARDWARE_PHYSICAL_NAME,EHARDWARE_PHYSICAL_IP,EENVIRONMENT_NAME_FK,EAPPLICATION_MODEL_NAME_FK,SUPPORT_DIRECTOR FROM EHARDWARE_PHYSICAL_TABLE WHERE EAPPLICATION_MODEL_NAME_FK=? ORDER BY EHARDWARE_PHYSICAL_NAME");              
                    selectPreparedStatementSequenceFlow          = connection.prepareStatement("SELECT ESEQUENCE_FLOW_ID,ESEQUENCE_FLOW_NAME,ESEQUENCE_FLOW_DESCRIPTION,ESEQUENCE_FLOW_DATA FROM ESEQUENCE_FLOW_TABLE WHERE EAPPLICATION_NAME_FK=?");
                    
                    resultSetEnvironments          		 		= selectStatementEnvironments.executeQuery("SELECT DISTINCT EENVIRONMENT_NAME_FK FROM EHARDWARE_PHYSICAL_TABLE WHERE EENVIRONMENT_NAME_FK IN ('DEVELOPMENT','DISASTER RECOVERY','LAB', 'NONPRODUCTION', 'PERFORMANCE', 'SADNBOX', 'QA','PREPRODUCTION','PRODUCTION','TEST','TRAIN') ORDER BY EENVIRONMENT_NAME_FK ASC")  ;
                    resultSetOwners         		 			= selectStatementOwners.executeQuery("SELECT DISTINCT SUPPORT_DIRECTOR FROM EHARDWARE_PHYSICAL_TABLE WHERE SUPPORT_DIRECTOR IN ('AARON.LAING','AARON.MINKOVICH','AL.TABONE','ALICE.REAVIE','ANDREW.MILJANOVSKI','ANNALISA.ABOUD','B.SUNTHARALINGAM','BARRINDER.GREWAL','BERNARD.MIN','BIKRAM.SINGH','BILL.AHRENS','BILL.MANOLAKOS','CAMILLE.KIFFER','CATHY.ROY','CHANTELLE.SLATERSHAW','CHRISTINE.WATERS','CRAIG.ADAMCZYK','Christine.Waters','DAN.DUFOUR','DANNY.SU','DISALVOP@CA.IBM.COM','DOUG.BELANGER','DOUG.ILTON','FAIZ.UDDIN',	'FIL.GAMBATESA','FRANK.CROMBEEN', 'GINO.MORETTI','GORDON.HUNTER','GREGORY.MOORS','HOLSTEAD@CA.IBM.COM','IRENE.ZAGUSKIN','IROBINSO@CA.IBM.COM','JBOWES@CA.IBM.COM','JEFF.IVERS','JIBRIL.ESAK','JIM.GRAINGER','JODY.WAYE','JOE.HOPKINS','JOHN.BASTABLE','JOHN.GIOMBANI','JONATHAN.RASIAH','JOSIE.NIGRO','KAY.FOONG','KEN.PEBESMA','KESHAWA.THIRUNAHARI','KUMAR.KANDASAMY','LORIANNE.FITZPATRICK','M.SRINATH@IN.IBM.COM','MAAKRISH@IN.IBM.COM','MARION.THIVY','MARK.PRYCE','MARK.SEGAL','MARK.SHORROCKS','MERAV.MUNZ','MINGLI@CA.IBM.COM', 'MOHAMMAD.MUSHTAQUE','NANCYMJ@CA.IBM.COM','PAUL.ALVES','PAUL.KORBER','PAUL.LANDAU','PAULK@CA.IBM.COM','PETER.CHAFE','RALPH.VONEPPINGHOVEN','RICK.WELLEN','ROBERT.DREWETT','ROBERT.VANDENBERG','STELLA.CORLAZZOLI','STEVE.JOSEPH', 'STEWART.ZANOLLA','TEJDEEP.SINGH@IN.IBM.COM','TONY.BASSON','TREVORROLSTON@CA.IBM.COM','VICTORY.CHAN','VIJAYS@CA.IBM.COM','VIKRAM.VIRK','VINCE.GISMONDI','VIRGINIA.GEROLIN','VLADIMIR.FERNANDEZ','VLADIMIR.RADULOVIC','WILL.GREER','YOLANDA.BARNA','ZUL.SIDI') ORDER BY SUPPORT_DIRECTOR ASC");
                    resultSetPersons         		 			= selectStatementPersons.executeQuery("SELECT EPERSON_NAME FROM EPERSON_TABLE ORDER BY EPERSON_NAME");
                    
                    // DB metadata
                    //JOptionPane.showMessageDialog( null, "loadEOrganismFromDB applicationsCount" +applicationsCount);
                    
                
          // environments     
         if(resultSetEnvironments!=null){
   		             
			             while(resultSetEnvironments.next() ){
			             			tempEnvironment = new EEnvironment();
			             			tempEnvironment.setName(resultSetEnvironments.getString(1));
			             
			             			modelOfEEnvironment.add(tempEnvironment);
			             		    // log4j.debug("loadEOrganismFromDB::| resultSetEnvironments name= " + tempEnvironment.getName());   
			              	
			             }//  while    
	     }//if environments
         // owners
         if(resultSetOwners!=null){
	             
	             while(resultSetOwners.next() ){
	             			tempPerson = new EPerson();
	             			tempPerson.setName(resultSetOwners.getString(1));
	             
	             			modelOfEOwners.add(tempPerson);
	             		    //log4j.debug("loadEOrganismFromDB::| resultSetOwners name= " + tempPerson.getName());   
	              	
	             }//  while    
	             
         }//if owners
         
         
             
         // applications
         if(resultSetApplications!=null){
             
                while(resultSetApplications.next() ){
                                
                    //log4j.debug("loadEOrganismFromDB:: app applicationsCount: " + applicationsCount);   
                	//SELECT EAPPLICATION_ID,EAPPLICATION_APPLICATION_ID, EAPPLICATION_ESIS_NAME,EAPPLICATION_ESIS,EAPPLICATION_ESIS_SYSTEM_NAME,EAPPLICATION_CLASIFICATION, EAPPLICATION_CLASIF_SERV,EAPPLICATION_CLASIF_CRITIC,
                	//EAPPLICATION_CLASIFICATION_CIM,EAPPLICATION_CLASIFICATION_PII,EAPPLICATION_CLASIFICATION_PCI,EAPPLICATION_CLASIFICATION_SOX, 
                	//EAPPLICATION_TAX_IMPACT,EAPPLICATION_ARCHITECT_CRITIC, EAPPLICATION_DESC_FUNCTIONAL,EAPPLICATION_VP,EAPPLICATION_SUP_DIRECTOR,EAPPLICATION_SUP_MANAGER,
                	//EAPPLICATION_DEV_DIRECTOR,EAPPLICATION_DEV_MANAGER,EAPPLICATION_DEV_PRIME,EAPPLICATION_ISM_GROUP,EAPPLICATION_STATUS,EAPPLICATION_BUSINESS_KPI,EAPPLICATION_IS_HPD,EAPPLICATION_IS_SIS, EAPPLICATION_IS_BSM,EAPPLICATION_IS_BPM,
                	//EAPPLICATION_IS_SHO,EAPPLICATION_IS_SHR,OWNER,METADATA_TYPE,EENVIRONMENT_NAME_FK,EAPPLICATION_NAME,EAPPLICATION_DESCRIPTION,EAPPLICATION_IN,EAPPLICATION_OUT,BUSINESS_UNIT,BUSINESS_SERVICE,TYPE,EAPP_ID,ESYS_ID_FK,ESYS_NAME FROM EAPPLICATION ORDER BY EAPPLICATION_NAME;
                	 
                    tempApplication             = new EApplication();
                    //**********************      
                    
                    tempApplication.setApplicationID(resultSetApplications.getInt(1));
                        
                    tempApplication.eSisName=resultSetApplications.getString(3);
                    tempApplication.eSis=resultSetApplications.getString(4);
                    tempApplication.eSisSystemName=resultSetApplications.getString(5);
                    
                    tempApplication.clasification=resultSetApplications.getString(6);
                    tempApplication.clasificationService=resultSetApplications.getString(7);
                    tempApplication.clasificationCriticality=resultSetApplications.getString(8);
                    tempApplication.clasificationCIM=resultSetApplications.getString(9);
                    tempApplication.clasificationPII=resultSetApplications.getString(10);
                    tempApplication.clasificationPCI=resultSetApplications.getString(11);
                    tempApplication.clasificationSOX=resultSetApplications.getString(12);
                    tempApplication.taxImpact=resultSetApplications.getString(13);
                    tempApplication.architecturalCriticality=resultSetApplications.getString(14);
                    tempApplication.descriptionFunctional=resultSetApplications.getString(15);
                    tempApplication.vp=resultSetApplications.getString(16);
                    tempApplication.supportDirector=resultSetApplications.getString(17);
                    tempApplication.supportManager=resultSetApplications.getString(18);
                    tempApplication.developmentDirector=resultSetApplications.getString(19);
                    tempApplication.developmentManager=resultSetApplications.getString(20);
                    tempApplication.developmentPrime=resultSetApplications.getString(21);
                    tempApplication.ismWorkgroup=resultSetApplications.getString(22);
                    tempApplication.status=resultSetApplications.getString(23);
                    tempApplication.businessKPI=resultSetApplications.getString(24);
                    tempApplication.isHpd=resultSetApplications.getString(25);
                    tempApplication.isSiS=resultSetApplications.getString(26);
                    tempApplication.isBsm=resultSetApplications.getString(27);
                    tempApplication.isBPM=resultSetApplications.getString(28);
                    tempApplication.isSho=resultSetApplications.getString(29);
                    tempApplication.isShr=resultSetApplications.getString(30);
                                     
                    // 
                    tempApplication.supportDirector=resultSetApplications.getString(31);//owner
                    tempApplication.metadataType = resultSetApplications.getString(32);//metadata type
                    tempApplication.setEnvironment(resultSetApplications.getString(32)); // environment
                    tempApplication.setApplicationName(resultSetApplications.getString(34));//application name, eSisName
                    tempApplication.setApplicationDescription(resultSetApplications.getString(35)); // esis name, description
                    
                    //IN,OUT         
                    tempApplication.setIn(resultSetApplications.getString(36)); // app in
                    tempApplication.setOut(resultSetApplications.getString(37)); // app out     
                    //
                    tempApplication.businessUnit= resultSetApplications.getString(38); 
                    tempApplication.businessService = resultSetApplications.getString(39); 
                    tempApplication.setType(resultSetApplications.getString(40));
                    
                    // SYS JAN 2, 2017                  
                    //EAPP_ID,ESYS_ID_FK,ESYS_NAME
                    tempApplication.setAppID(resultSetApplications.getString(41));// EAPP_ID
                    tempApplication.setSysID(resultSetApplications.getString(42));// SYS ID
                    tempApplication.setSystemName(resultSetApplications.getString(43));// SYS_NAME
                     
                    					if (isNew(tempApplication.getApplicationName())){
      	
                    							//**********************
					                            log4j.debug("EORGANISM :: loadEOrganismFromDB::| application name, = " + tempApplication.getApplicationName() + "| setAppID=" + tempApplication.getAppID() + "| setSysID =" +tempApplication.getSysID() + " setSystemName=" + tempApplication.getSystemName());   
                    						
					                            selectPreparedStatementComponent.setString(1, tempApplication.getApplicationName());
					                            resultSetComponents = selectPreparedStatementComponent.executeQuery();
					                           
					                            tempComponentsArrayList		= new ArrayList();
					                            tempDatabasesArrayList 		= new ArrayList();
					                            tempHardwaresArrayList 		= new ArrayList();
					                            tempSequenceFlowArrayList 	= new ArrayList();
					                            tempOperationArrayList 		= new ArrayList();
					                            //
					                           
					                            tempInterfaceArrayList 		= new ArrayList();
					                            tempAttributeArrayList 		= new ArrayList();
					                               
					                            if(resultSetComponents!=null){  
					                                    while(resultSetComponents.next()){
					                                          //log4j.debug("loadEOrganismFromDB:: componentsCount: " + componentsCount);   
					                                          tempComponent = new EComponent();
					                                          tempComponent.setEorganismID(resultSetComponents.getInt(1));
					                                          tempComponent.setComponentName(resultSetComponents.getString(2));
					                                          tempComponent.setComponentDescription(resultSetComponents.getString(4));
					                                          tempComponent.setApplicationName(tempApplication.getApplicationName());
					                                          tempComponentsArrayList.add(tempComponent);
					                                          componentsCount++;             
					                                    }// for all components
					                            }  
					                             
					                            selectPreparedStatementDatabase.setString(1, tempApplication.getApplicationName());  
					                            resultSetDatabases = selectPreparedStatementDatabase.executeQuery();
					                            
					                            if(resultSetDatabases!=null){   
					                            	
					                                    while(resultSetDatabases.next()){
					                                          //log4j.debug("loadEOrganismFromDB::databasesCount: " + databasesCount);   
					                                          tempDatabase = new JDatabase();
					                                          tempDatabase.setEorganismID(resultSetDatabases.getInt(1));
					                                          tempDatabase.setDatabaseName(resultSetDatabases.getString(2));
					                                          tempDatabase.setDatabaseDescription(resultSetDatabases.getString(7));
					                                        
					                                          tempDatabasesArrayList.add(tempDatabase);
					                                          databasesCount++;
					                                    
					                                    }// for all databases
					                            }
					                            
					                            if (DISPLAY_HARDWARE ==true){
					                            	
								                            //SELECT EHARDWARE_PHYSICAL_ID, EHARDWARE_PHYSICAL_NAME,EHARDWARE_PHYSICAL_IP,EENVIRONMENT_NAME_FK,EAPPLICATION_MODEL_NAME_FK,SUPPORT_DIRECTOR, 
								                            //FROM EHARDWARE_PHYSICAL_TABLE WHERE EAPPLICATION_MODEL_NAME_FK=?
								                            selectPreparedStatementHardware.setString(1, tempApplication.getApplicationName());  
								                            resultSetHardwares = selectPreparedStatementHardware.executeQuery();
								                            
								                            if(resultSetHardwares!=null){   
								                                    while(resultSetHardwares.next()){
								                                          //log4j.debug("loadEOrganismFromDB:: hardwaresCount: " +hardwaresCount);   
								                                          tempHardware = new EHardware();
								                                          tempHardware.setEorganismID(resultSetHardwares.getInt(1));
								                                          tempHardware.setHardwareName(resultSetHardwares.getString(2));
								                                          tempHardware.setIp(resultSetHardwares.getString(3));  
								                                           
								                                          tempHardware.setEnvironment(resultSetHardwares.getString(4));
								                                          tempHardware.setApplication(resultSetHardwares.getString(5));
								                                          tempHardware.setOwner(resultSetHardwares.getString(6));
								                                          
								                                          
								                                          tempHardwaresArrayList.add(tempHardware);
								                                          modelOfEHardwares.add(tempHardware);
								                                          hardwaresCount++;
								                                    
								                                    }// for all hardwares
								                            }
					                            
					                            }// if we want to diplay HW
					                            
					                   			//log4j.debug("loadEOrganismFromDB:: ADD HARDWARE - modelOfEHardwares.size() =" + modelOfEHardwares.size());
							                     
					                            // SEQUENCE FLOW
					                            selectPreparedStatementSequenceFlow.setString(1, tempApplication.getApplicationName());  
					                            resultSetSequenceFlow = selectPreparedStatementSequenceFlow.executeQuery();
					                            
					                            if(resultSetSequenceFlow!=null){   
					                                while(resultSetSequenceFlow.next()){
					                                      //log4j.debug("loadEOrganismFromDB:: SEQUENCE FLOW:");   
					                                	  tempSequenceFlow =  new ESequenceFlow();
					                                      
					                                	  tempSequenceFlow.setName(resultSetSequenceFlow.getString(2));
					                                	  tempSequenceFlow.setText(resultSetSequenceFlow.getString(4));
					                                    
					                                      tempSequenceFlowArrayList.add(tempSequenceFlow);
					                                      modelOfESequenceFlow.add(tempSequenceFlow);
					                                
					                                }// for all sequence flow
					                            
					                            }// if we have flows
					                            selectPreparedStatementService.setString(1, tempApplication.getApplicationName());  
					                            resultSetServices = selectPreparedStatementService.executeQuery();
					                            
					                            if(resultSetServices!=null){  
					                            	
					                                while(resultSetServices.next()){
					                                      
					                                      tempOperation = new EOperation();
					                                     
					                                      tempOperation.setName(resultSetServices.getString(1));
					                                      tempOperation.setType(resultSetServices.getString(2));
					                                      tempOperation.setApplication(resultSetServices.getString(3));
					                                      tempOperation.setComponent(resultSetServices.getString(4));
					                                      tempOperation.setLocation(resultSetServices.getString(5));
					                                      //tempOperation.set
					                                      
					                                      tempOperationArrayList.add(tempOperation);
					                                      //log4j.debug("loadEOrganismFromDB::  tempOperation.getName()= " + tempOperation.getName() +" ::: "+ tempOperation.getApplication() +":"+  tempOperation.getComponent()); 
					                                      servicesCount++;
					                                
					                                }// for all services
					                            }
					                            // application physical
					                            //selectPreparedStatementInterface
					                    
					                            selectPreparedStatementInterface.setString(1, tempApplication.getApplicationName());  
					                            resultSetInterfaces = selectPreparedStatementInterface.executeQuery();
					                            
					                            if(resultSetInterfaces!=null){  
					                                while(resultSetInterfaces.next()){
					                                      
					                                      tempInterface = new EInterface();
					                                     
					                                      tempInterface.setInterfaceName(resultSetInterfaces.getString(1));
					                                      tempInterface.setInterfaceType(resultSetInterfaces.getString(2));
					                                      //tempInterface.setApplication(resultSetInterfaces.getString(3));
					                                      tempInterface.setComponent(resultSetInterfaces.getString(4));
					                                      tempInterface.setService(resultSetInterfaces.getString(5));
					                                      tempInterface.setLocation(resultSetInterfaces.getString(6));
					                                      
					                                      tempInterface.setApplication(tempApplication.getApplicationName());
					                                      
					                                      tempInterfaceArrayList.add(tempInterface);
					                                       //log4j.debug("loadEOrganismFromDB::  tempInterface.getInterfaceName()= " + tempInterface.getInterfaceName() +"::"+  tempInterface.getService() +"::"+ tempInterface.getComponent()); 
					                                 }// for all interfaces
					                            }
					                                //selectPreparedStatementAttribute
					                                selectPreparedStatementAttribute.setString(1, tempApplication.getApplicationName());  
					                                resultSetAttributes = selectPreparedStatementAttribute.executeQuery();
					                                
					                                if(resultSetAttributes!=null){  
					                                    while(resultSetAttributes.next()){
					                                          
					                                          tempAttribute = new EAttribute();
					                                         
					                                          tempAttribute.setName(resultSetAttributes.getString(1));
					                                          tempAttribute.setType(resultSetAttributes.getString(2));
					                                          //tempAttribute.setApplication(resultSetAttributes.getString(3));
					                                          tempAttribute.setComponent(resultSetAttributes.getString(4));
					                                          tempAttribute.setService(resultSetAttributes.getString(5));
					                                          //tempAttribute.setInterfaceName(resultSetAttributes.getString(6));
					                                           tempAttributeArrayList.add(tempAttribute);
					                                           //log4j.debug("loadEOrganismFromDB::   tempAttribute.getName()= " + tempAttribute.getName()); 
					                                     }// for all attributes
					                                }
					                             
					                            // associate the encapsulation for components > services > interfaces > attributes
					                            tempOperationArrayListWorker = new ArrayList();
					                            tempInterfaceArrayListWorker = new ArrayList();
					                            if (tempComponentsArrayList.size() > 0 ){    
						                                
					                            	for( int i = 0; i <  tempComponentsArrayList.size() ; i++){
						                            	
						                            	tempComponent = new EComponent();
						                            	tempComponent = (EComponent)tempComponentsArrayList.get(i);
						                            	
						                            	tempOperationArrayListWorker = new ArrayList();
						                            	
						                            		if(tempOperationArrayList.size()>0){
						                            	
									                            	for( int j = 0; j <  tempOperationArrayList.size() ; j++){
									                            			//log4j.debug("loadEOrganismFromDB:: ADD INTERFACE ");
										                            		tempOperation = new EOperation();
										                            		tempOperation = (EOperation)tempOperationArrayList.get(j);
									                            		
												                            		if(tempInterfaceArrayList.size()>0){
												                            		
												                            				tempInterfaceArrayListWorker  = new ArrayList();
												                            				
												                            				for( int k = 0; k <  tempInterfaceArrayList.size() ; k++){
												                            					//log4j.debug("loadEOrganismFromDB:: ADD INTERFACE tempInterfaceArrayListWorker");
															                            		tempInterface = new EInterface();
															                            		tempInterface = (EInterface)tempInterfaceArrayList.get(k);
															                        			//log4j.debug("loadEOrganismFromDB:: ADD INTERFACE || tempInterface.getComponent()" + tempInterface.getComponent() + "|| tempOperation.getName()-"+ tempOperation.getName());
															                            		if(tempInterface.getService().equals(tempOperation.getName())){
															                            			
															                            			tempInterfaceArrayListWorker.add(tempInterface);
															                            			//log4j.debug("loadEOrganismFromDB:: ADD INTERFACE - " + tempInterface.getInterfaceName() + "- added to SERVICE -"+ tempOperation.getName());
															                            			
															                            		}//if
														                            		}//for k
												                            		
												                            		}//if
												                            		//add attribute to the service
												                            		if(tempAttributeArrayList.size()>0){
													                            		
											                            				tempAttributeArrayListWorker  = new ArrayList();
											                            				
											                            				for( int z = 0; z <  tempAttributeArrayList.size() ; z ++){
											                            					//log4j.debug("loadEOrganismFromDB:: ADD ATTRIBUTE tempInterfaceArrayListWorker");
														                            		tempAttribute = new EAttribute();
														                            		tempAttribute = (EAttribute)tempAttributeArrayList.get(z);
														                        			
														                            		//log4j.debug("loadEOrganismFromDB:: ADD ATTRIBUTE || tempAttribute.getComponent()" + tempAttribute.getComponent() + "|| tempAttribute.getName()-"+ tempAttribute.getName());
																		                       
														                            		if(tempAttribute.getService().equals(tempOperation.getName())){
														                            			
														                            			tempAttributeArrayListWorker.add(tempAttribute);
														                            			
														                            			//log4j.debug("loadEOrganismFromDB:: ADD ATTRIBUTE - " + tempAttribute.getName() + "- added to SERVICE -"+ tempOperation.getName());
														                            			
														                            		}//if
													                            		}//for k
											                            		
												                            		}//if we have attributes
										                            		
												                            // set interface array list into operation
												                            tempOperation.setArrayListInterface(tempInterfaceArrayListWorker);
												                            tempOperation.setArrayListAttribute(tempAttributeArrayListWorker);
											                            
												                            // set back operation to operation array list
												                            tempOperationArrayList.set(j, tempOperation);
												                            		
												                            // if name is matching
												                            if(tempComponent.getComponentName().equals(tempOperation.getComponent())){
												                            			
												                            			tempOperationArrayListWorker.add(tempOperation);
												                              			
												                            			//log4j.debug("loadEOrganismFromDB:: ADD SERVICE- " + tempOperation.getName() + "- added to COMPONENT-"+ tempComponent.getComponentName());
												   						             
												                            }//if
									                            		}// for j 
							                            	
						                            		}// if operations array
						                            	
							                            	// set arrays to tempComponent
								                            tempComponent.setArrayListService(tempOperationArrayListWorker);
							                            	
							                            	// set back in the array list
							                                tempComponentsArrayList.set(i, tempComponent);
							                             }//for i- all components    
					                              
					                             }// if we have components  
					                            tempApplication.setComponentsArrayList(tempComponentsArrayList);
					                            tempApplication.setOperationArrayList(tempOperationArrayList);
					                            tempApplication.setInterfaceArrayList(tempInterfaceArrayList);
					                            tempApplication.setAttributeArrayList(tempAttributeArrayList);
					                            
					                            tempApplication.setDatabasesArrayList(tempDatabasesArrayList);
					                            tempApplication.setHardwaresArrayList(tempHardwaresArrayList);
					                            tempApplication.setSequenceFlowArrayList(tempSequenceFlowArrayList);
					                           
					                            
					                            //log4j.debug("EORGANISM :: loadEOrganismFromDB::| modelOfEApplication = " + tempApplication.getApplicationName() + "| SYS ID =" + tempApplication.getSysID() + "| SYS NAME =" +tempApplication.getSystemName());   
	                    						
					                            
					                           //create the model Vector
					                            modelOfEApplication.add(tempApplication);
					                            
					         				}// only if i new application
                   
                }// while we have apps  
                
         }// if result set not null  
         
         
         // LOAD DOMAINS
         if(resultSetDomains!=null){
        	 
		        	 //log4j.debug("EORGANISM :: loadEOrganismFromDB resultSetDomains not null");
		        		        
		        	 while(resultSetDomains.next()){
		        		 
		      			tempDomain = new EDomain();
		      			tempDomain.setDomainID(resultSetDomains.getInt(1));
		      			tempDomain.setDomainName(resultSetDomains.getString(2));
		      			tempDomain.setDomainDescription(resultSetDomains.getString(3));
		      			tempDomain.setDomainId(resultSetDomains.getString(4));//COBRA_EDOMAIN_ID
		      			tempDomain.setInfrastructure(resultSetDomains.getString(5));//INFRA
		      			tempDomain.setLayer(resultSetDomains.getString(6));//LAYER
		      			tempDomain.setChannel(resultSetDomains.getString(7));//CHANNEL
		      			tempDomain.setSegment(resultSetDomains.getString(8));//SEGMENT
		      			tempDomain.businessService		=resultSetDomains.getString(9);//BUSINESS_SERVICE
		      			tempDomain.businessUnit			=resultSetDomains.getString(10);//BUSINESS_UNIT
		       				 
		      			
		       			
		      		    //log4j.debug("loadEOrganismFromDB::| modelOfEDomain ADD domain= " + tempDomain.getDomainName());   
		      		    
		      		    // load subdomains
		      		    //SELECT COBRA_ESUBDOMAIN_ID,ESUBDOMAIN_NAME,ESUBDOMAIN_DESCRIPTION FROM ESUBDOMAIN WHERE COBRA_EDOMAIN_ID_FK=?
		      		    selectPreparedStatementSubdomain.setString(1, tempDomain.getDomainId());  
                        resultSetSubdomains = selectPreparedStatementSubdomain.executeQuery();
                        
                        tempSubdomainsArrayList = new ArrayList();
                        
                        if(resultSetSubdomains!=null){
                        	
			                        while(resultSetSubdomains.next()){
			                        	
			                        	tempSubdomain = new ESubdomain();
			                           	tempSubdomain.setSubdomainId(resultSetSubdomains.getString(1));
			                           	tempSubdomain.setSubdomainName(resultSetSubdomains.getString(2));
			                           	tempSubdomain.setSubdomainDescription(resultSetSubdomains.getString(3));
			                           	
			                           	//log4j.debug("loadEOrganismFromDB::| tempSubdomain ID=" + tempSubdomain.getSubdomainId() +": tempSubdomain name=" +tempSubdomain.getSubdomainName() );
			                           	
			                           	tempSubdomainsArrayList.add(tempSubdomain);
			                               	
			                        }// while
		      		    
                        }// if we have subdomains
                        
                        tempDomain.setSubdomainsArrayList(tempSubdomainsArrayList);
                        
                                               
                        // add to master vector
                        modelOfEDomain.add(tempDomain);
		      		    // end load subdomains
		       	
		        	 }//  while  
        	 
        	
         }// if DOMAINS
         
         //log4j.debug("loadEOrganismFromDB::| modelOfEDomains size " + modelOfEDomain.size()); 
         
         //load ESystem
         //setup EApplication to ESystem
         //SELECT ESYSTEM_ID,ESYSTEM_NAME,ESYSTEM_DESCRIPTION,SYS_ID,INFRA,LAYER,CHANNEL,SEGMENT,BUSINESS_SERVICE,BUSINESS_UNIT FROM ESYSTEM ORDER BY ESYSTEM_ID;
         
         if(resultSetSystems!=null){
        	 
        	 //log4j.debug("EORGANISM :: loadEOrganismFromDB resultSetSystems not null");
        		        
        	 while(resultSetSystems.next() ){
        		 
      			tempSystem = new ESystem();
      			tempSystem.setSystemID(resultSetSystems.getInt(1));
      			tempSystem.setName(resultSetSystems.getString(2));
      			tempSystem.setDescription(resultSetSystems.getString(3));
      			tempSystem.setSysID(resultSetSystems.getString(4));//SYS_ID
      			tempSystem.setInfrastructure(resultSetSystems.getString(5));//INFRA
      			tempSystem.setLayer(resultSetSystems.getString(6));//LAYER
      			tempSystem.setChannel(resultSetSystems.getString(7));//CHANNEL
      			tempSystem.setSegment(resultSetSystems.getString(8));//SEGMENT
       			tempSystem.businessService		=resultSetSystems.getString(9);//BUSINESS_SERVICE
       			tempSystem.businessUnit			=resultSetSystems.getString(10);//BUSINESS_UNIT
       			
      			//log4j.debug("loadEOrganismFromDB::| tempSystem SYS NAME= " + tempSystem.getName() + ": SYS ID=" +tempSystem.getSysID()); 
      			
      			
      			// add APPLICATIONS TO SYSTEM
      			tempSystemApplicationArrayList = new ArrayList();
      			
      			for( int w = 0; w <  modelOfEApplication.size() ; w ++){
      	        	 
      	        	 tempApplication1 = new EApplication();
      	        	 tempApplication1= (EApplication) modelOfEApplication.get(w);
      	        	 
      	           //log4j.debug("EORGANISM :: loadEOrganismFromDB::| modelOfEApplication >> tempApplication1 ADD TO SYSTEM = " + tempApplication1.getApplicationName() + "| APP SYS ID =" + tempApplication1.getSysID() + "| APP SYS NAME =" +tempApplication1.getSystemName());   
   				
      	        		 
      	        	 if((null!= tempApplication1.getSysID()) | ""!= tempApplication1.getSysID() | (null!= tempSystem.getSysID())){
      	        		 
	      	        	 if ((tempApplication1.getSysID()).equals(tempSystem.getSysID())){      
	      	        		 
	      	        		tempSystemApplicationArrayList.add(tempApplication1);
	      	        		//log4j.debug("loadEOrganismFromDB::| resultSetSystems tempSystem SYS NAME= " + tempSystem.getName() + ": SYS ID=" +tempSystem.getSysID() + " + add tempApplication1=" + tempApplication1.getApplicationName() + "| APP SYS ID =" + tempApplication1.getSysID() + "| APP SYS NAME =" +tempApplication1.getSystemName()); 
	      	        		
	      	        	 }//if matching
	      	        	 
      	        	 }// not null
      	        	 
      	         }// model of applications
      			 
      			 //log4j.debug("loadEOrganismFromDB::| resultSetSystems tempSystemApplicationArrayList size= " + tempSystemApplicationArrayList.size()); 
      			 tempSystem.setApplicationsArrayList(tempSystemApplicationArrayList);
      			 
      			 modelOfESystem.add(tempSystem);
       			
      		     //log4j.debug("loadEOrganismFromDB::| modelOfESystem ADD name= " + tempSystem.getName());   
       	
        	 }//  while  
        	 
        	
         }// if systems
         
         //log4j.debug("loadEOrganismFromDB::| modelOfESystem size " + modelOfESystem.size()); 
	        	 
            
         // metadata database result set
         
         if(resultSetMetadataDatabases!=null){
             
                while(resultSetMetadataDatabases.next() ){
                	
                	tempDatabase = new JDatabase();
                	tempStringDatabase = resultSetMetadataDatabases.getString(1);
                	
                    tempDatabase.setDatabaseName(tempStringDatabase);
                    tempDatabase.setComponent(resultSetMetadataDatabases.getString(2));
                    tempDatabase.setApplication(resultSetMetadataDatabases.getString(3));
                    
                    tempMetadataTablesArrayList = new ArrayList();
                     
                     selectPreparedStatementMetadataTable.setString(1, tempStringDatabase);
                     resultSetMetadataTable = selectPreparedStatementMetadataTable.executeQuery();
                     
	                     if(resultSetMetadataTable!=null){
	                    	 
		                    	 while(resultSetMetadataTable.next() ){
		                    		 
		                    		 tempTable = new ETable();
		                    		 
		                    		 tempStringTable = resultSetMetadataTable.getString(1);
		                    		 tempTable.setName(tempStringTable);
		                    		 
		                    		 selectPreparedStatementMetadataColumn.setString(1, tempStringTable);
		                             resultSetMetadataColumn = selectPreparedStatementMetadataColumn.executeQuery();
		                    		 
		                             tempMetadataColumnsArrayList  = new ArrayList();
		                             
				                             if(resultSetMetadataColumn!=null){
				                             	 
				                            	 while(resultSetMetadataColumn.next() ){
				                            		 tempColumn = new EColumn();
				                            		 
				                            		 tempColumn.setName(resultSetMetadataColumn.getString(1));
				                            		 tempColumn.setDataType(resultSetMetadataColumn.getString(2));
				                            		 
				                            		 tempMetadataColumnsArrayList.add(tempColumn);  
				                            		 
				                            		 //log4j.debug("loadEOrganismFromDB:: resultSetMetadataColumn: " + tempColumn.getName());
				                            	 }// whileresultSetMetadataColumn
				                            
				                        		 tempTable.setMetadataColumnsArrayList(tempMetadataColumnsArrayList);
				                         		 //log4j.debug("loadEOrganismFromDB:: tempMetadataColumnsArrayList size: " + tempMetadataColumnsArrayList.size());
							                          
				                             }// if resultSetMetadataColumn
				                   //log4j.debug("loadEOrganismFromDB:: resultSetMetadataTable: " + tempStringTable);
				                   tempMetadataTablesArrayList.add(tempTable);   
	                    	 }//while resultSet
	                     
	                      }// if resultSetMetadataTable not null
                      
	                     //log4j.debug("loadEOrganismFromDB:: resultSetMetadataDatabases: " + tempStringDatabase);
	                     tempDatabase.setMetadataTablesArrayList(tempMetadataTablesArrayList);
	                     modelMetadataDatabases.add(tempDatabase);
                }//while setMetadataDatabase
                
         }// if metadataDatabases not null
         
		        selectStatementDomain.close();
		        selectStatementSystem.close();
                selectStatementApplication.close();
                selectStatementMetadataDatabases.close();
                selectStatementEnvironments.close();
                selectStatementOwners.close();
                selectStatementPersons.close();
                
                selectPreparedStatementSubdomain.close();
                selectPreparedStatementComponent.close();
                 //resultSetApplications.close();
                //resultSetEnvironments.close();
                 
                //selectPreparedStatementApplicationPhysical.close();
                selectPreparedStatementDatabase.close();
                //selectPreparedStatementDatabasePhysical.close();
                selectPreparedStatementHardware.close();
                selectPreparedStatementService.close();
                selectPreparedStatementInterface.close();
                selectPreparedStatementAttribute.close();
                
                selectPreparedStatementMetadataTable.close();
                selectPreparedStatementMetadataColumn.close();
                selectPreparedStatementSequenceFlow.close();
                connection.close();
                
                //JOptionPane.showMessageDialog( null, "loadEOrganismFromDB OK");
                log4j.debug("EORGANISM :: loadEOrganismFromDB OK");
            
            } catch(SQLException sqle){
                //JOptionPane.showMessageDialog( null,"SQLException  "    + sqle.toString()+"\n");
            	log4j.error("EORGANISM :: loadEOrganismFromDB:: SQLException: " + sqle.toString());
            	log4j.debug("EORGANISM :: loadEOrganismFromDB:: SQLException: " + sqle.toString());
            } catch(Exception e){
                //JOptionPane.showMessageDialog( null, "Exception  " +e.toString()+"\n");
            	log4j.error("EORGANISM :: loadEOrganismFromDB:: Exception: " + e.toString());
            	log4j.debug("EORGANISM :: loadEOrganismFromDB:: Exception: " + e.toString());
               
            
            } // end catch
       
        }// end loadEOrganismFromDB
        
        public void saveEOrganismTable(int _eorganismId,String _value,String _table, String _type, int _tablePK ){
        	
        	//java.sql.Date today = new java.sql.Date(System.currentTimeMillis());
        	PreparedStatement insertPreparedStatementEOrganism;
        	
        	databaseInitializer();
            checkConnection();
             
        	try{
        		        		
        		insertPreparedStatementEOrganism  = connection.prepareStatement(ConfigFrame.INSERT_INTO_EORGANISM_OBJECTID_MAPPING);
        		
        		insertPreparedStatementEOrganism.setInt(1, _eorganismId);//id
        		insertPreparedStatementEOrganism.setString(2, _value);
        		insertPreparedStatementEOrganism.setString(3, _table);
        		insertPreparedStatementEOrganism.setString(4, _type);
        		insertPreparedStatementEOrganism.setInt(5, _tablePK);// 
        		insertPreparedStatementEOrganism.setDate(6, today);// 
        		insertPreparedStatementEOrganism.setDate(7, today);// 

        		insertPreparedStatementEOrganism.setString(8, ConfigFrame.ADMIN_USER_NAME);
        		insertPreparedStatementEOrganism.setString(9, ConfigFrame.LOAD_APP);
        		
        		result = insertPreparedStatementEOrganism.executeUpdate();
               
        		insertPreparedStatementEOrganism.close();
                connection.close();
        		
        	}   catch(SQLException sqle){
        		
                //JOptionPane.showMessageDialog( null," saveEOrganismTableSQLException  "    + sqle.toString()+"\n");
                log4j.error("EORGANISM :: saveEOrganismTable:: SQLException: " + sqle.toString());
                log4j.debug("EORGANISM :: saveEOrganismTable:: SQLException: " + sqle.toString());
                
            } catch(Exception e){
                JOptionPane.showMessageDialog( null, " saveEOrganismTable Exception  " +e.toString()+"\n");
                log4j.error("EORGANISM :: saveEOrganismTable:: Exception: " + e.toString());
                log4j.debug("EORGANISM :: saveEOrganismTable:: Exception: " + e.toString());
                
            }// end catch
        	
        }// saveEOrganismTable
        
        
        public void saveStateToDatabase(){
        	EDomain    		tempDomain             		= new EDomain();
        	ESubdomain    	tempSubdomain              	= new ESubdomain();
        
        	
        	ESystem    		tempSystem             		= new ESystem();
            EApplication    tempApplication             = new EApplication();
            EComponent      tempComponent               = new EComponent();
            JDatabase       tempDatabase                = new JDatabase();
            EHardware       tempHardware                = new EHardware();
            EOperation      tempOperation               = new EOperation();
            ESequenceFlow   tempSequenceFlow            = new ESequenceFlow();
            EEnvironment           tempEnvironment      = new EEnvironment();
            ERelease               tempRelease          = new ERelease();
            EProject               tempProject          = new EProject();
            
            
            PreparedStatement insertPreparedStatementDomain			;
            PreparedStatement insertPreparedStatementSubdomain		;
            PreparedStatement insertPreparedStatementDomainApplication		;
            
            PreparedStatement insertPreparedStatementSystemApplication		;
            PreparedStatement insertPreparedStatementApplication            ;
            PreparedStatement insertPreparedStatementSystem					;
            PreparedStatement insertPreparedStatementComponent              ;
            PreparedStatement insertPreparedStatementDatabase               ;
            PreparedStatement insertPreparedStatementHardware               ;
            PreparedStatement insertPreparedStatementService                ;
            PreparedStatement insertPreparedStatementInterface              ;
            PreparedStatement insertPreparedStatementSequenceFlow           ;
            PreparedStatement insertPreparedStatementSequenceItem           ;
            PreparedStatement insertPreparedStatementRelease                ;
            PreparedStatement insertPreparedStatementEnvironment            ;
            PreparedStatement insertPreparedStatementProject                ;
            //
            PreparedStatement insertPreparedStatementMetadataService        ;
            PreparedStatement insertPreparedStatementMetadataOperations     ;    
            PreparedStatement insertPreparedStatementMetadataAttributes     ;
            PreparedStatement insertPreparedStatementApplicationLinks 		;
               
            ArrayList       tempSubdomainsArrayList;
            ArrayList       tempApplicationsArrayList;
            ArrayList       tempComponentsArrayList;
            ArrayList       tempDatabasesArrayList;
            ArrayList       tempHardwaresArrayList;
            ArrayList       tempOperationsArrayList;
            ArrayList       tempSequencesArrayList;
            
            EEnvironmentConfig tempConfig= getEnvConfigEOrganismController();
            
            String releaseName;
            String sequenceFlowString;
            String tokenFlowString;
            String tempString;
            StringTokenizer tokenizerFlow;
            StringTokenizer tokenizerItem;
            
            // delete database 
            //deleteDatabase();
            databaseInitializer();
            checkConnection();
            
            int releaseId      		=0;
            int environmentId      	=0;
            int projectId      		=0;
            
           
            int domainIdValue		=0;
            int subdomainIdValue	=0;
            int appIdValue      	=0;
            int systemIdValue		=0;
            int applicationLinkId 	=0;
     
            int  databaseCounter =0;
            int compId			=0;
            int hardwareId		=0;
            int serviceId		=0;
            
              
            String 						tempAppLinkString 		= "";
	 		String 						nextTokenApp 			= "";
	 		StringTokenizer 			tempAppLinkStringTokenizer;
	 		 							eid						= 0;// reset
	 		
	 		int 						sequenceFlowIdString 	= 0;
              
            try{
                    // insert EENVIRONMENT_TABLE, ERELEASE_TABLE, EPROJECT_TABLE
                    insertPreparedStatementRelease                   = connection.prepareStatement("INSERT INTO ERELEASE_TABLE(ERELEASE_ID,ERELEASE_NAME,ERELEASE_DESCRIPTION,EENVIRONMENT_ID_FK,EENVIRONMENT_NAME_FK) VALUES (?,?,?,?,?)");
                    insertPreparedStatementEnvironment               = connection.prepareStatement("INSERT INTO EENVIRONMENT_TABLE(EENVIRONMENT_ID,EENVIRONMENT_NAME,EENVIRONMENT_DESCRIPTION,ERELEASE_ID_FK,ERELEASE_NAME_FK) VALUES (?,?,?,?,?)");
                    insertPreparedStatementProject                   = connection.prepareStatement("INSERT INTO EPROJECT_TABLE(EPROJECT_ID,EPROJECT_NAME,EPROJECT_DESCRIPTION,EPROJECT_STATUS,ERELEASE_ID_FK,ERELEASE_NAME_FK,EENVIRONMENT_ID_FK,EENVIRONMENT_NAME_FK) VALUES (?,?,?,?,?,?,?,?)");
                    
                    insertPreparedStatementSystemApplication = connection.prepareStatement("INSERT INTO EAPPLICATION(EAPPLICATION_ID,EAPPLICATION_NAME,EAPPLICATION_BUSINESS_KPI,EORGANISM_ID,EAPPLICATION_INFRA,EAPPLICATION_LAYER,EAPPLICATION_CHANNEL,EAPPLICATION_SEGMENT,EAPPLICATION_IN,EAPPLICATION_OUT,METADATA_TYPE,EAPPLICATION_CREATED_DATE,EAPPLICATION_LAST_UPDATED_USER,EAPPLICATION_LAST_UPDATED_APP,EENVIRONMENT_NAME_FK,EAPPLICATION_INVENTORY_NAME,BUSINESS_SERVICE,BUSINESS_UNIT,SERVICE_CODE,CREATED_TIMESTAMP,TYPE,ESYS_NAME,ESYS_ID) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    
                    // DOMAIN, SUBDOMAIN JAN 8
                    insertPreparedStatementDomain			 = connection.prepareStatement("INSERT INTO EDOMAIN(EDOMAIN_ID,EDOMAIN_NAME,EDOMAIN_DESCRIPTION,EORGANISM_ID,CREATED_DATE,LAST_UPDATED_USER,LAST_UPDATED_APP,CREATED_TIMESTAMP,COBRA_EDOMAIN_ID,INFRA,LAYER,CHANNEL,SEGMENT,SYS_IN,SYS_OUT,BUSINESS_SERVICE,BUSINESS_UNIT,SERVICE_CODE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    insertPreparedStatementSubdomain		 = connection.prepareStatement("INSERT INTO ESUBDOMAIN(ESUBDOMAIN_ID,ESUBDOMAIN_NAME,ESUBDOMAIN_DESCRIPTION,EORGANISM_ID,CREATED_DATE,LAST_UPDATED_USER,LAST_UPDATED_APP,CREATED_TIMESTAMP,COBRA_ESUBDOMAIN_ID,INFRA,LAYER,CHANNEL,SEGMENT,SYS_IN,SYS_OUT,BUSINESS_SERVICE,BUSINESS_UNIT,SERVICE_CODE,EDOMAIN_ID_FK,EDOMAIN_NAME_FK,COBRA_EDOMAIN_ID_FK) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                      
                    // SYSTEM
                    insertPreparedStatementSystem       	 = connection.prepareStatement("INSERT INTO ESYSTEM(ESYSTEM_ID,ESYSTEM_NAME,ESYSTEM_DESCRIPTION,EORGANISM_ID,CREATED_DATE,LAST_UPDATED_USER,LAST_UPDATED_APP,CREATED_TIMESTAMP,SYS_ID,INFRA,LAYER,CHANNEL,SEGMENT,SYS_IN,SYS_OUT,BUSINESS_SERVICE,BUSINESS_UNIT,SERVICE_CODE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    
                    insertPreparedStatementApplication       = connection.prepareStatement("INSERT INTO EAPPLICATION(EAPPLICATION_ID,EAPPLICATION_NAME,EAPPLICATION_BUSINESS_KPI,EORGANISM_ID,EAPPLICATION_INFRA,EAPPLICATION_LAYER,EAPPLICATION_CHANNEL,EAPPLICATION_SEGMENT,EAPPLICATION_IN,EAPPLICATION_OUT,METADATA_TYPE,EAPPLICATION_CREATED_DATE,EAPPLICATION_LAST_UPDATED_USER,EAPPLICATION_LAST_UPDATED_APP,EENVIRONMENT_NAME_FK,EAPPLICATION_INVENTORY_NAME,BUSINESS_SERVICE,BUSINESS_UNIT,SERVICE_CODE,CREATED_TIMESTAMP,TYPE,ESYS_NAME,ESYS_ID_FK,EAPP_ID) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    
                    
                    insertPreparedStatementComponent         = connection.prepareStatement("INSERT INTO ECOMPONENT(ECOMPONENT_ID,ECOMPONENT_NAME,ECOMPONENT_DESCRIPTION,EORGANISM_EID,EAPPLICATION_ID_FK,EAPPLICATION_NAME_FK,EDATABASE_ID_FK,EDATABASE_NAME_FK,METADATA_TYPE,EENVIRONMENT_NAME_FK,CREATED_TIMESTAMP) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
                    insertPreparedStatementDatabase          = connection.prepareStatement("INSERT INTO EDATABASE (EDATABASE_ID,EDATABASE_NAME,EDATABASE_DESCRIPTION, EORGANISM_EID,EAPPLICATION_ID_FK, EAPPLICATION_NAME_FK,EDATABASE_TYPE,EDATABASE_DRIVER_CLASS,METADATA_TYPE,EENVIRONMENT_NAME_FK,CREATED_TIMESTAMP) VALUES (?,?,?,?,?,?,?,?,?,?,?)");//11
                    insertPreparedStatementHardware          = connection.prepareStatement("INSERT INTO EHARDWARE_PHYSICAL_TABLE (EHARDWARE_PHYSICAL_ID, EHARDWARE_PHYSICAL_NAME, EHARDWARE_PHYSICAL_DESCRIPTION, EORGANISM_EID, EAPPLICATION_MODEL_ID_FK, EAPPLICATION_MODEL_NAME_FK, EENVIRONMENT_NAME_FK,CREATED_TIMESTAMP) VALUES (?,?,?,?,?,?,?,?)");
                    insertPreparedStatementService           = connection.prepareStatement("INSERT INTO ESERVICE(ESERVICE_ID,ESERVICE_NAME,ESERVICE_DESCRIPTION,EORGANISM_EID,EAPPLICATION_ID_FK, EAPPLICATION_NAME_FK,CREATED_TIMESTAMP) VALUES (?,?,?,?,?,?,?)");
                    insertPreparedStatementInterface         = connection.prepareStatement("INSERT INTO EINTERFACE(EINTERFACE_ID,EINTERFACE_NAME,EINTERFACE_DESCRIPTION,EAPPLICATION_ID_FK,EAPPLICATION_NAME_FK,ECOMPONENT_NAME_FK,ESERVICE_NAME_FK,CREATED_TIMESTAMP) VALUES (?,?,?,?,?,?,?,?)");
                     
                    insertPreparedStatementSequenceFlow       = connection.prepareStatement("INSERT INTO ESEQUENCE_FLOW_TABLE(ESEQUENCE_FLOW_ID,ESEQUENCE_FLOW_NAME,ESEQUENCE_FLOW_DESCRIPTION,ESEQUENCE_FLOW_DATA,EAPPLICATION_NAME_FK,EAPPLICATION_ID_FK) VALUES (?,?,?,?,?,?)");
                    
                    //insertPreparedStatementSequenceItem      = connection.prepareStatement("INSERT INTO ESEQUENCE_ITEM_TABLE(ESEQUENCE_ITEM_ID,ESEQUENCE_ITEM_NAME,ESEQUENCE_ITEM_DATA,LAST_UPDATED_APPLICATION,CREATED_DATE,UPDATED_DATE,EAPPLICATION_NAME_FK) VALUES (?,?,?,?,?,?,?)");
                    insertPreparedStatementSequenceItem        = connection.prepareStatement("INSERT INTO ESEQUENCE_ITEM_TABLE(ESEQUENCE_ITEM_ID,ESEQUENCE_ITEM_NAME,ESEQUENCE_ITEM_DATA,ESEQUENCE_FLOW_ID_FK,EAPPLICATION_NAME_FK,EAPPLICATION_ID_FK,LAST_UPDATED_APPLICATION,CREATED_DATE,UPDATED_DATE) VALUES (?,?,?,?,?,?,?,?,?)");
                    
                    //EMETADATA_SERVICE, EMETADATA_INTERFACE, EMETADATA_ATTRIBUTE
                    insertPreparedStatementMetadataService      = connection.prepareStatement("INSERT INTO EMETADATA_SERVICE(EMETADATA_SERVICE_ID,EMETADATA_SERVICE_NAME,EMETADATA_SERVICE_TYPE,EMETADATA_SERVICE_DESCRIPTION,EMETADATA_APPLICATION_NAME,EMETADATA_LAST_UPDATED_DATE,EMETADATA_LAST_UPDATED_USER,EMETADATA_LAST_UPDATED_APP) VALUES (?,?,?,?,?,?,?,?)");
                    insertPreparedStatementMetadataOperations   = connection.prepareStatement("INSERT INTO EMETADATA_INTERFACE(EMETADATA_INTERFACE_ID,EMETADATA_INTERFACE_NAME,EMETADATA_COMPONENT_NAME,EMETADATA_APPLICATION_NAME,EMETADATA_LAST_UPDATED_DATE,EMETADATA_LAST_UPDATED_USER,EMETADATA_LAST_UPDATED_APP) VALUES (?,?,?,?,?,?,?)"); 
                    //insertPreparedStatementMetadataAttributes = connection.prepareStatement("INSERT INTO EMETADATA_ATTRIBUTE( EMETADATA_ATTRIBUTE_ID,EMETADATA_ATTRIBUTE_NAME,EMETADATA_ATTRIBUTE_TYPE,EMETADATA_INTERFACE_NAME,EMETADATA_SERVICE_NAME,EMETADATA_COMPONENT_NAME,EMETADATA_DATABASE_NAME,EMETADATA_APPLICATION_NAME,EMETADATA_LAST_UPDATED_DATE,EMETADATA_LAST_UPDATED_USER,EMETADATA_LAST_UPDATED_APP) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
                    
                    //EAPPLICATION_LINK
                    insertPreparedStatementApplicationLinks   = connection.prepareStatement("INSERT INTO EAPPLICATION_LINK(EAPPLICATION_LINK_ID,EAPPLICATION_LINK_NAME,EAPPLICATION_LINK_DESCRIPTION,EAPPLICATION_ID_FROM,EAPPLICATION_NAME_FROM,EAPPLICATION_ID_TO,EAPPLICATION_NAME_TO,LAST_UPDATED_DATE,LAST_UPDATED_USER,LAST_UPDATED_APPLICATION) VALUES (?,?,?,?,?,?,?,?,?,?)"); 
                         
                // for all releases
	                //ERELEASE_ID,ERELEASE_NAME,ERELEASE_DESCRIPTION,EENVIRONMENT_ID_FK,EENVIRONMENT_NAME_FK
	                for( int t = 0; t <  modelOfERelease.size() ; t++){
	                        tempRelease = new ERelease();
	                        tempRelease = (ERelease) modelOfERelease.elementAt(t);
	                     
	                        //releaseId = getSequenceNextValue("SEQUENCE_RELEASE_TABLE");
	                        //((ERelease)modelOfERelease.elementAt(t)).setId(releaseId);
	                        releaseName = tempRelease.getName(); 
	                        
	                        releaseId 	=  	0;
	                        eid 		=	0;
	                        
	                        releaseId 	=  getSequenceNextValue("ERELEASE_TABLE","ERELEASE_ID");
	                        eid 		=  getSequenceNextValue(ConfigFrame.EORGANISM_OBJECTID_MAPPING,ConfigFrame.EORGANISM_OBJECTID);
	                        
	                        insertPreparedStatementRelease.setInt(1, releaseId);//id
	                        insertPreparedStatementRelease.setString(2, tempRelease.getName());
	                        insertPreparedStatementRelease.setString(3, tempRelease.getType());
	                        insertPreparedStatementRelease.setInt(4, 1);// env_id
	                        insertPreparedStatementRelease.setString(5, tempRelease.getEnvironment());// env name
	                         
	                        result = insertPreparedStatementRelease.executeUpdate();
	                        
	                        // SAVE EORGANISM
	                        saveEOrganismTable(eid,tempRelease.getName(),"ERELEASE","Release", releaseId);
	                           
	                        //EPROJECT_ID,EPROJECT_NAME,EPROJECT_DESCRIPTION,EPROJECT_STATUS,ERELEASE_ID_FK,ERELEASE_NAME_FK,EENVIRONMENT_ID_FK,EENVIRONMENT_NAME_FK
	                         for( int z = 0; z <  (tempRelease.getEProjectVector()).size() ; z++){
	                                        
	                                        tempProject = new EProject();
	                                        tempProject = (EProject)(tempRelease.getEProjectVector()).elementAt(z);
	                                        
	                                        nextValue 	= getSequenceNextValue("EPROJECT_TABLE","EPROJECT_ID");
	                                        eid 		=  getSequenceNextValue(ConfigFrame.EORGANISM_OBJECTID_MAPPING,ConfigFrame.EORGANISM_OBJECTID); 
	                                        
	                                        insertPreparedStatementProject.setInt(1, nextValue);//release id
	                                        insertPreparedStatementProject.setString(2, tempProject.getName());
	                                        insertPreparedStatementProject.setString(3, tempProject.getDescription());
	                                        insertPreparedStatementProject.setString(4, "ACTIVE");// status
	                                        insertPreparedStatementProject.setInt(5, releaseId);// release ID
	                                        insertPreparedStatementProject.setString(6, releaseName);// release name
	                                        insertPreparedStatementProject.setInt(7, 1);// env id
	                                        insertPreparedStatementProject.setString(8, tempRelease.getEnvironment());// env name
	                                    
	                                        result = insertPreparedStatementProject.executeUpdate();
	                                        
	                                        // SAVE EORGANISM
	                                        saveEOrganismTable(eid,tempProject.getName(),"EPROJECT_TABLE","Project", nextValue);
	                        }//z
	                                           
	                    }// all releases
                   
	                   //EENVIRONMENT_ID,EENVIRONMENT_NAME,EENVIRONMENT_DESCRIPTION,ERELEASE_ID_FK,ERELEASE_NAME_FK
	                    // for all environments
	                    for( int p = 0; p <  modelOfEEnvironment.size() ; p++){
	                    
	                                    tempEnvironment = new EEnvironment();
	                                    tempEnvironment = (EEnvironment) modelOfEEnvironment.elementAt(p);
	                                           
	                                    //nextValue = getSequenceNextValue("SEQUENCE_ENVIRONMENT_TABLE");
	                                    //((EEnvironment) modelOfEEnvironment.elementAt(p)).setId(nextValue);
	                                    environmentId 	= 0;
	                                    eid				= 0;
	                                    
	                                    environmentId 	= getSequenceNextValue("EENVIRONMENT_TABLE","EENVIRONMENT_ID");
	                                    eid 			= getSequenceNextValue(ConfigFrame.EORGANISM_OBJECTID_MAPPING,ConfigFrame.EORGANISM_OBJECTID);
	                                    
	                                    insertPreparedStatementEnvironment.setInt(1,  environmentId);//env id
	                                    insertPreparedStatementEnvironment.setString(2, tempEnvironment.getName());// get name
	                                    insertPreparedStatementEnvironment.setString(3, tempEnvironment.getDescription());
	                                    insertPreparedStatementEnvironment.setInt(4, releaseId);// release id
	                                    insertPreparedStatementEnvironment.setString(5, tempEnvironment.getReleaseName());// release_name
	                                    
	                                    result = insertPreparedStatementEnvironment.executeUpdate();
	                                    
	                                    // SAVE EORGANISM
	                                    saveEOrganismTable(eid,tempEnvironment.getName(),"EENVIRONMENT_TABLE","Environment", environmentId);
	                    
	                    }// all enviornments
                    // for all projects
                    /*
                    for( int p = 0; p <  modelOfEEnvironment.size() ; p++){
                       }// all projects
                    */
                    
	                //for all DOMAINS
	                // SELECT EDOMAIN_ID,EDOMAIN_NAME,EDOMAIN_DESCRIPTION,EORGANISM_ID,CREATED_DATE,LAST_UPDATED_USER,LAST_UPDATED_APP,CREATED_TIMESTAMP,COBRA_EDOMAIN_ID,INFRA,LAYER,CHANNEL,SEGMENT,SYS_IN,SYS_OUT,BUSINESS_SERVICE,BUSINESS_UNIT,SERVICE_CODE FROM EDOMAIN;    
	                for( int xx = 0; xx <  modelOfEDomain.size() ; xx++){
	                	  
				                	tempDomain 		= new EDomain();
			                        tempDomain 		= (EDomain) modelOfEDomain.elementAt(xx);
                    
			                        log4j.debug("EORGANISM :: saveStateToDatabase:: passed parsing  from modelOfEDomain"); 
			                        
			                        domainIdValue 	= 	getSequenceNextValue("EDOMAIN", "EDOMAIN_ID");
                                    eid 			=   getSequenceNextValue(ConfigFrame.EORGANISM_OBJECTID_MAPPING,ConfigFrame.EORGANISM_OBJECTID);
                                     
                                    insertPreparedStatementDomain.setInt(1, domainIdValue);//id
                                    insertPreparedStatementDomain.setString(2, tempDomain.getDomainName());
                                    insertPreparedStatementDomain.setString(3, tempDomain.getDomainDescription());
                                    insertPreparedStatementDomain.setInt(4, eid);// eid
                                    insertPreparedStatementDomain.setDate(5, today);// obj id
                                    insertPreparedStatementDomain.setString(6, ConfigFrame.ADMIN_USER_NAME +"@"+ ConfigFrame.ADMIN_USER_ROLE);
                                    insertPreparedStatementDomain.setString(7, ConfigFrame.LOAD_APP +"@"+ConfigFrame.LOAD_ACTION);
                                    insertPreparedStatementDomain.setTimestamp(8, new java.sql.Timestamp(System.currentTimeMillis()));//timestamp 
                                    
                                    // dec 30
                                    insertPreparedStatementDomain.setString(9,  tempDomain.getDomainId());// COBRA_EDOMAIN_ID
                                    insertPreparedStatementDomain.setString(10, tempDomain.getInfrastructure());// infra
                                    insertPreparedStatementDomain.setString(11, tempDomain.getLayer());// layer
                                    insertPreparedStatementDomain.setString(12, tempDomain.getChannel());// channel
                                    insertPreparedStatementDomain.setString(13, tempDomain.getSegment());// segment
                                    insertPreparedStatementDomain.setString(14, tempDomain.getIn());// in 
                                    insertPreparedStatementDomain.setString(15, tempDomain.getOut());// out//
                                    
                                    insertPreparedStatementDomain.setString(16, tempDomain.businessService);//businessService
                                    insertPreparedStatementDomain.setString(17, tempDomain.businessUnit);//businessUnit
                                    insertPreparedStatementDomain.setString(18, tempDomain.serviceCode);//serviceCode 
                                    
                                    result = insertPreparedStatementDomain.executeUpdate();
                                    
                                    // SAVE EORGANISM
                                    saveEOrganismTable(eid,tempDomain.getDomainName(),"DOMAIN","EDomain", systemIdValue);
                                    
                                    log4j.debug("EORGANISM :: saveStateToDatabase:: DOMAIN ID =" + tempDomain.getDomainId() + "DOMAIN NAME ="+ tempDomain.getDomainName() + " SAVED OK@POSITION ="+ xx); 
                                    
			                        // SAVE SUBDOMAINS
                                    tempSubdomainsArrayList  = (ArrayList)tempDomain.getSubdomainsArrayList ();
                                    
                               
				                               if(tempSubdomainsArrayList.size()>0){
				                            	   
						                               for( int yy = 0; yy <  tempSubdomainsArrayList.size() ; yy++){
						                                   
						                                   tempSubdomain = new ESubdomain();
						                                   tempSubdomain = (ESubdomain) tempSubdomainsArrayList.get(yy);
						                           
						                                    //EAPPLICATION
						                                    subdomainIdValue 	= 	getSequenceNextValue("ESUBDOMAIN", "ESUBDOMAIN_ID");
						                                    eid 				=   getSequenceNextValue(ConfigFrame.EORGANISM_OBJECTID_MAPPING,ConfigFrame.EORGANISM_OBJECTID);
						                                    
						                                    //insertPreparedStatementSubdomain		 = connection.prepareStatement("INSERT INTO ESUBDOMAIN(ESUBDOMAIN_ID,ESUBDOMAIN_NAME,ESUBDOMAIN_DESCRIPTION,EORGANISM_ID,CREATED_DATE,LAST_UPDATED_USER,LAST_UPDATED_APP,CREATED_TIMESTAMP,COBRA_ESUBDOMAIN_ID,INFRA,LAYER,CHANNEL,SEGMENT,SYS_IN,SYS_OUT,BUSINESS_SERVICE,BUSINESS_UNIT,SERVICE_CODE,,EDOMAIN_ID_FK,EDOMAIN_NAME_FK) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
						                                    
						                                    
						                                    insertPreparedStatementSubdomain.setInt(1, subdomainIdValue);//id
						                                    insertPreparedStatementSubdomain.setString(2, tempSubdomain.getSubdomainName());
						                                    insertPreparedStatementSubdomain.setString(3, tempSubdomain.getSubdomainDescription());
						                                    insertPreparedStatementSubdomain.setInt(4, eid);// eid
						                                    insertPreparedStatementSubdomain.setDate(5, today);// obj id
						                                    insertPreparedStatementSubdomain.setString(6, ConfigFrame.ADMIN_USER_NAME +"@"+ ConfigFrame.ADMIN_USER_ROLE);
						                                    insertPreparedStatementSubdomain.setString(7, ConfigFrame.LOAD_APP +"@"+ConfigFrame.LOAD_ACTION);
						                                    insertPreparedStatementSubdomain.setTimestamp(8, new java.sql.Timestamp(System.currentTimeMillis()));//timestamp 
						                                    
						                                    // dec 30
						                                    insertPreparedStatementSubdomain.setString(9,  tempSubdomain.getSubdomainId());// COBRA_EDOMAIN_ID
						                                    insertPreparedStatementSubdomain.setString(10, tempDomain.getInfrastructure());// infra
						                                    insertPreparedStatementSubdomain.setString(11, tempDomain.getLayer());// layer
						                                    insertPreparedStatementSubdomain.setString(12, tempDomain.getChannel());// channel
						                                    insertPreparedStatementSubdomain.setString(13, tempDomain.getSegment());// segment
						                                    insertPreparedStatementSubdomain.setString(14, tempDomain.getIn());// in 
						                                    insertPreparedStatementSubdomain.setString(15, tempDomain.getOut());// out//
						                                    
						                                    insertPreparedStatementSubdomain.setString(16, tempDomain.businessService);//businessService
						                                    insertPreparedStatementSubdomain.setString(17, tempDomain.businessUnit);//businessUnit
						                                    insertPreparedStatementSubdomain.setString(18, tempDomain.serviceCode);//serviceCode 
						                                    
						                                    //,EDOMAIN_ID_FK,EDOMAIN_NAME_FK
						                                    insertPreparedStatementSubdomain.setString(19, tempDomain.getDomainId());//EDOMAIN_ID_FK 
						                                    insertPreparedStatementSubdomain.setString(20, tempDomain.getDomainName());//EDOMAIN_NAME_FK 
						                                    insertPreparedStatementSubdomain.setString(21, tempDomain.getDomainId());//COBRA_EDOMAIN_ID_FK 
								                               
						                                    result = insertPreparedStatementSubdomain.executeUpdate();
						                                    
						                                     
						                                    log4j.debug("EORGANISM :: saveStateToDatabase:: SUBDOMAIN = "+ tempSubdomain.getSubdomainName() + "  @ DOMAIN  =" + tempDomain.getDomainName() + " SAVED OK @ POSITION ="+ yy); 
						                                        
						                               }// for all the  subdomains part fo the domain 
				                               
				                               }//if size >0 
                                       
                                      
	                }// for all the DOMAINS
	                    
	                    
                    // ALL SYSTEMS
                    log4j.debug("EORGANISM :: saveStateToDatabase:: modelOfESystem.size()=" + modelOfESystem.size());  
                  
                    for( int w = 0; w <  modelOfESystem.size() ; w++){
                                
                                tempSystem 		= new ESystem();
                                tempSystem 		= (ESystem) modelOfESystem.elementAt(w);
                                
                                log4j.debug("EORGANISM :: saveStateToDatabase:: passed parsing  from modelOfESystem"); 
                        
                                 //EAPPLICATION
                                 
                                 appIdValue = 	getSequenceNextValue("ESYSTEM", "ESYSTEM_ID");
                                 eid 		=   getSequenceNextValue(ConfigFrame.EORGANISM_OBJECTID_MAPPING,ConfigFrame.EORGANISM_OBJECTID);
                                 
                                 
                                 insertPreparedStatementSystemApplication.setInt(1, appIdValue);//id
                                 insertPreparedStatementSystemApplication.setString(2, tempSystem.getApplicationName());
                                 insertPreparedStatementSystemApplication.setString(3, tempSystem.getApplicationDescription());
                                 insertPreparedStatementSystemApplication.setInt(4, eid);// eid
                                 insertPreparedStatementSystemApplication.setString(5, tempSystem.getInfrastructure());// infra
                                 insertPreparedStatementSystemApplication.setString(6, tempSystem.getLayer());// layer
                                 insertPreparedStatementSystemApplication.setString(7, tempSystem.getChannel());// channel
                                 insertPreparedStatementSystemApplication.setString(8, tempSystem.getSegment());// segment
                                 insertPreparedStatementSystemApplication.setString(9, tempSystem.getIn());// in 
                                 insertPreparedStatementSystemApplication.setString(10, tempSystem.getOut());// out
                                 insertPreparedStatementSystemApplication.setString(11, "SYSTEM");// metadata type - MODEL
		                                                          
                                 insertPreparedStatementSystemApplication.setDate(12, today);// obj id
                                 insertPreparedStatementSystemApplication.setString(13, ConfigFrame.ADMIN_USER_NAME +"@"+ ConfigFrame.ADMIN_USER_ROLE);// user and role
                                 insertPreparedStatementSystemApplication.setString(14, ConfigFrame.LOAD_APP +"@"+ConfigFrame.LOAD_ACTION);//app  and action
                                 insertPreparedStatementSystemApplication.setString(15, ConfigFrame.TYPE_ENV_PRODUCTION);//env PRODUCTION
                                 insertPreparedStatementSystemApplication.setString(16, tempSystem.getApplicationName());//application_inventory name 
		                                 //
                                 insertPreparedStatementSystemApplication.setString(17, tempSystem.businessService);//businessService
                                 insertPreparedStatementSystemApplication.setString(18, tempSystem.businessUnit);//businessUnit
                                 insertPreparedStatementSystemApplication.setString(19, tempSystem.serviceCode);//serviceCode 
		                                 
                                 insertPreparedStatementSystemApplication.setTimestamp(20, new java.sql.Timestamp(System.currentTimeMillis()));//timestamp 
                                 insertPreparedStatementSystemApplication.setString(21, "SYSTEM"); //SYSTEM,APPLICATION, COMPONENT 
		                                 
		                                 // new Dec 29,2017 - ESYS_NAME_FK,ESYS_ID_FK,EAPP_ID
                                 insertPreparedStatementSystemApplication.setString(22, tempSystem.getSystemName());//system name
                                 insertPreparedStatementSystemApplication.setString(23, tempSystem.getSysID());//system id
                                 
                                   
                                 result = insertPreparedStatementSystemApplication.executeUpdate();
                                 
                                 log4j.debug("EORGANISM :: saveStateToDatabase:: SYSTEM ID =" + tempSystem.getSysID() + "SYSTEM NAME ="+ tempSystem.getSystemName() + " SAVED OK@POSITION ="+ w); 
                                 
                                 // SAVE EORGANISM
                                 saveEOrganismTable(eid,"SYSTEM-"+ tempSystem.getSystemName(),ConfigFrame.APPLICATION_TABLE,ConfigFrame.APPLICATION, appIdValue);
                                  
                                 // APP LINK IN
                                 if("SYSTEM".equals(tempSystem.getType())){
                                	 
                                	 systemIdValue 	= 	getSequenceNextValue(ConfigFrame.SYSTEM_TABLE, ConfigFrame.SYSTEM_TABLE_PK);
                                     eid 			=   getSequenceNextValue(ConfigFrame.EORGANISM_OBJECTID_MAPPING,ConfigFrame.EORGANISM_OBJECTID);
                                     
                                     
                                     insertPreparedStatementSystem.setInt(1, systemIdValue);//id
                                     insertPreparedStatementSystem.setString(2, tempSystem.getApplicationName());
                                     insertPreparedStatementSystem.setString(3, tempSystem.getApplicationDescription());
                                     insertPreparedStatementSystem.setInt(4, eid);// eid
                                     insertPreparedStatementSystem.setDate(5, today);// obj id
                                     insertPreparedStatementSystem.setString(6, ConfigFrame.ADMIN_USER_NAME +"@"+ ConfigFrame.ADMIN_USER_ROLE);
                                     insertPreparedStatementSystem.setString(7, ConfigFrame.LOAD_APP +"@"+ConfigFrame.LOAD_ACTION);
                                     insertPreparedStatementSystem.setTimestamp(8, new java.sql.Timestamp(System.currentTimeMillis()));//timestamp 
                                     
                                     // dec 30
                                     insertPreparedStatementSystem.setString(9,  tempSystem.getSysID());// SYSTEM ID
                                     insertPreparedStatementSystem.setString(10, tempSystem.getInfrastructure());// infra
                                     insertPreparedStatementSystem.setString(11, tempSystem.getLayer());// layer
                                     insertPreparedStatementSystem.setString(12, tempSystem.getChannel());// channel
                                     insertPreparedStatementSystem.setString(13, tempSystem.getSegment());// segment
                                     insertPreparedStatementSystem.setString(14, tempSystem.getIn());// in 
                                     insertPreparedStatementSystem.setString(15, tempSystem.getOut());// out//
                                     
                                     insertPreparedStatementSystem.setString(16, tempSystem.businessService);//businessService
                                     insertPreparedStatementSystem.setString(17, tempSystem.businessUnit);//businessUnit
                                     insertPreparedStatementSystem.setString(18, tempSystem.serviceCode);//serviceCode 
                                     
                                     result = insertPreparedStatementSystem.executeUpdate();
                                     
                                     // SAVE EORGANISM
                                     saveEOrganismTable(eid,tempSystem.getApplicationName(),ConfigFrame.SYSTEM_TABLE,ConfigFrame.APPLICATION, systemIdValue);
                                  	 
                                 }// if system
                                 
                                 // APP LINK IN
                                 if(tempSystem.getIn()!=null && tempSystem.getIn().length() > 3){
                                	 
                                	 		tempAppLinkString = tempSystem.getIn().trim();
                                	 		
                                	 		nextTokenApp = "";
                                	 		 
                                	 		tempAppLinkStringTokenizer = new StringTokenizer(tempAppLinkString,",");
                                             
                                             while (tempAppLinkStringTokenizer.hasMoreTokens()) {
                                            	 
                                            	 		nextTokenApp = tempAppLinkStringTokenizer.nextToken().trim();
			                                 
						                                 // start   EAPPLICATION_LINK
						                                 //EAPPLICATION_LINK_ID,EAPPLICATION_LINK_NAME,EAPPLICATION_LINK_DESCRIPTION,EAPPLICATION_ID_FROM,EAPPLICATION_NAME_FROM,EAPPLICATION_ID_TO,EAPPLICATION_NAME_TO,LAST_UPDATED_DATE,LAST_UPDATED_USER,LAST_UPDATED_APPLICATION
						                                 //VALUES (?,?,?,?,?,?,?,?,?,?)
                                            	 		
                                            	 		 applicationLinkId 	= getSequenceNextValue("EAPPLICATION_LINK","EAPPLICATION_LINK_ID");
                                            	 		 eid 				= getSequenceNextValue(ConfigFrame.EORGANISM_OBJECTID_MAPPING,ConfigFrame.EORGANISM_OBJECTID);
                                                                                                         	 		  
						                                 insertPreparedStatementApplicationLinks.setInt(1, applicationLinkId);//link id
						                                 
						                                 insertPreparedStatementApplicationLinks.setString(2, "SYSTEM FROM " +nextTokenApp+ ">> SYSTEM TO "+ tempSystem.getApplicationName());//link name
						                                 insertPreparedStatementApplicationLinks.setString(3, "DESCRIPTION :: " +nextTokenApp+ ">> SYSTEM TO "+ tempSystem.getApplicationName());//link description                                              
						                                 
						                                 insertPreparedStatementApplicationLinks.setInt(4, 1);//app ID from
						                                 insertPreparedStatementApplicationLinks.setString(5, nextTokenApp);//app name from                                              
						                                
						                                 insertPreparedStatementApplicationLinks.setInt(6, appIdValue);//app ID to
						                                 insertPreparedStatementApplicationLinks.setString(7, tempSystem.getApplicationName().trim());//app name to                                              
						                                
						                                 insertPreparedStatementApplicationLinks.setDate(8, today);// obj id
						                                 insertPreparedStatementApplicationLinks.setString(9,  ConfigFrame.ADMIN_USER_NAME +"@"+ ConfigFrame.ADMIN_USER_ROLE);// user and role
						                                 insertPreparedStatementApplicationLinks.setString(10, ConfigFrame.LOAD_APP +"@"+ConfigFrame.LOAD_ACTION);//app and action
						                                 
						                                 result = insertPreparedStatementApplicationLinks.executeUpdate();
						                                 
						                                 // SAVE EORGANISM
                                          	 		     saveEOrganismTable(eid,"SYSTEM FROM " +nextTokenApp+ ">> SYSTEM TO "+ tempSystem.getApplicationName(),"EAPPLICATION_LINK","ApplicationLink", applicationLinkId);
						                                   
						                                 //log4j.debug("EORGANISM :: saveStateToDatabase:: EAPPLICATION_LINK: IN" +   "APP FROM " +nextTokenApp+ ">> APP TO "+ tempApplication.getApplicationName());
			                                 
						                                    // create default sequence flow as per APP_IN
						                                 	sequenceFlowIdString = getSequenceNextValue("ESEQUENCE_FLOW_TABLE","ESEQUENCE_FLOW_ID");
						                                 	 eid 				 = getSequenceNextValue(ConfigFrame.EORGANISM_OBJECTID_MAPPING,ConfigFrame.EORGANISM_OBJECTID);
		                                                      
			                                                // INSERT INTO ESEQUENCE_FLOW_TABLE(ESEQUENCE_FLOW_ID,ESEQUENCE_FLOW_NAME,ESEQUENCE_FLOW_DESCRIPTION,ESEQUENCE_FLOW_DATA,EAPPLICATION_NAME_FK,EAPPLICATION_ID_FK) VALUES (?,?,?,?,?,?)");
			                                                
			                                                insertPreparedStatementSequenceFlow.setInt(1, sequenceFlowIdString);
			                                                insertPreparedStatementSequenceFlow.setString(2, "SYSTEM INTEGRATION: IN:: SYSTEM FROM " +nextTokenApp+ ">> SYSTEM TO "+ tempSystem.getApplicationName());
			                                                insertPreparedStatementSequenceFlow.setString(3, "SYSTEM INTEGRATION: IN:: DEFAULT FLOW =" + "SYSTEM FROM " +nextTokenApp+ ">> SYSTEM TO "+ tempSystem.getApplicationName());
			                                                insertPreparedStatementSequenceFlow.setString(4, nextTokenApp + ">>" +tempSystem.getApplicationName());
			                                                insertPreparedStatementSequenceFlow.setString(5, tempSystem.getApplicationName());
			                                                insertPreparedStatementSequenceFlow.setInt(6, appIdValue);// app id
			                                                
			                                                result = insertPreparedStatementSequenceFlow.executeUpdate();
			                                                
			                                                // SAVE EORGANISM
	                                          	 		    saveEOrganismTable(eid,"SYSTEM_LINK IN::SYSTEM FROM " +nextTokenApp+ ">> SYSTEM TO "+ tempSystem.getApplicationName(),"ESEQUENCE_FLOW_TABLE","SequenceFlow", sequenceFlowIdString);
			                                                
                                             }// for all the tokens
			                                 
			                                 // stop   EAPPLICATION_LINK
                                 }// app in
                                 
                                 //log4j.error("EORGANISM :: saveStateToDatabase:: tempSystem.getOut()" +   tempSystem.getOut());
                                 // SYSTEM LINK OUT
                                 
                                 if(tempSystem.getOut()!=null && tempSystem.getOut().length() > 3){
                                	 
	                         	 		tempAppLinkString = tempSystem.getOut().trim();
	                         	 		nextTokenApp = "";
	                        	 		tempAppLinkStringTokenizer = new StringTokenizer(tempAppLinkString,",");
                                      
	                                      while (tempAppLinkStringTokenizer.hasMoreTokens()) {
	                                     	 
	                                     	 		nextTokenApp = tempAppLinkStringTokenizer.nextToken().trim();
			                                 
						                                 // start   EAPPLICATION_LINK
						                                 //EAPPLICATION_LINK_ID,EAPPLICATION_LINK_NAME,EAPPLICATION_LINK_DESCRIPTION,EAPPLICATION_ID_FROM,EAPPLICATION_NAME_FROM,EAPPLICATION_ID_TO,EAPPLICATION_NAME_TO,LAST_UPDATED_DATE,LAST_UPDATED_USER,LAST_UPDATED_APPLICATION
						                                 //VALUES (?,?,?,?,?,?,?,?,?,?)
	                                     	 		
	                                     	 		     applicationLinkId  = getSequenceNextValue("EAPPLICATION_LINK","EAPPLICATION_LINK_ID");
	                                     	 		     eid 				= getSequenceNextValue(ConfigFrame.EORGANISM_OBJECTID_MAPPING,ConfigFrame.EORGANISM_OBJECTID);
	                                                         
						                                 insertPreparedStatementApplicationLinks.setInt(1, applicationLinkId);//link id
						                                 
						                                 insertPreparedStatementApplicationLinks.setString(2, "SYSTEM INTEGRATION: FROM  >" + tempSystem.getApplicationName()+ ">>" +nextTokenApp);//link name
						                                 insertPreparedStatementApplicationLinks.setString(3, "DESCRIPTION :: " + tempSystem.getApplicationName() + ">>" +nextTokenApp);//link description                                              
						                                 
						                                 insertPreparedStatementApplicationLinks.setInt(4, appIdValue);//app ID from
						                                 insertPreparedStatementApplicationLinks.setString(5, tempSystem.getApplicationName().trim() );//app name from                                              
						                                
						                                 insertPreparedStatementApplicationLinks.setInt(6, 1);//app ID to
						                                 insertPreparedStatementApplicationLinks.setString(7, nextTokenApp);//app name to                                              
						                                
						                                 insertPreparedStatementApplicationLinks.setDate(8, today);// obj id
						                                 insertPreparedStatementApplicationLinks.setString(9, "Alex - EOrganism SuperAdmin");// obj id
						                                 insertPreparedStatementApplicationLinks.setString(10, "EORGANISM Swing Client");//app 
						                       
						                                 result = insertPreparedStatementApplicationLinks.executeUpdate();
						                                 
						                                 // SAVE EORGANISM
                                          	 		     saveEOrganismTable(eid,"SYSTEM FROM  >" + tempSystem.getApplicationName()+ ">>" +nextTokenApp,"EAPPLICATION_LINK","ApplicationLink", applicationLinkId);
						                              
						                                 	//log4j.debug("EORGANISM :: saveStateToDatabase:: EAPPLICATION_LINK: OUT" +   " LINK NAME >" + tempApplication.getApplicationName()+ ">>" +nextTokenApp);
			                                 
							                                // create default sequence flow as per APP_IN and APP_OUT
							                                sequenceFlowIdString = getSequenceNextValue("ESEQUENCE_FLOW_TABLE","ESEQUENCE_FLOW_ID");
							                                eid 				 = getSequenceNextValue(ConfigFrame.EORGANISM_OBJECTID_MAPPING,ConfigFrame.EORGANISM_OBJECTID);
		                                                         
				                                                
			                                                // INSERT INTO ESEQUENCE_FLOW_TABLE(ESEQUENCE_FLOW_ID,ESEQUENCE_FLOW_NAME,ESEQUENCE_FLOW_DESCRIPTION,ESEQUENCE_FLOW_DATA,EAPPLICATION_NAME_FK,EAPPLICATION_ID_FK) VALUES (?,?,?,?,?,?)");
			                                                
			                                                insertPreparedStatementSequenceFlow.setInt(1, sequenceFlowIdString);
			                                                insertPreparedStatementSequenceFlow.setString(2, "SYSTEM INTEGRATION: USED BY:  >" + tempSystem.getApplicationName()+ ">>" +nextTokenApp);
			                                                insertPreparedStatementSequenceFlow.setString(3, "SYSTEM INTEGRATION: DEFAULT FLOW =" + "SYSTEM FROM  >" + tempSystem.getApplicationName()+ ">>" +nextTokenApp);
			                                                insertPreparedStatementSequenceFlow.setString(4, tempSystem.getApplicationName()+ ">>" +nextTokenApp);
			                                                insertPreparedStatementSequenceFlow.setString(5, tempSystem.getApplicationName());
			                                                insertPreparedStatementSequenceFlow.setInt(6, appIdValue);// app id
			                                                
			                                                result = insertPreparedStatementSequenceFlow.executeUpdate();
			                                                
			                                                // SAVE EORGANISM
	                                          	 		    saveEOrganismTable(eid,"SYSTEM_LINK OUT::SYSTEM FROM  >" + tempSystem.getApplicationName()+ ">>" +nextTokenApp,"ESEQUENCE_FLOW_TABLE","SequenceFlow", sequenceFlowIdString);
							                              
	                                      }// for all the tokens
		                                 
		                                 // stop   EAPPLICATION_LINK
                               }// app out
                                
                                 
                                 
                               // application components
                               // database components  
                               tempApplicationsArrayList  = (ArrayList)tempSystem.getApplicationsArrayList ();
                               
                               if(tempApplicationsArrayList.size()>0){
                            	   
		                               for( int x = 0; x <  tempApplicationsArrayList.size() ; x++){
		                                   
		                                   tempApplication = new EApplication();
		                                   tempApplication = (EApplication) tempApplicationsArrayList.get(x);
		                           
		                                    //EAPPLICATION
		                                    appIdValue = 	getSequenceNextValue(ConfigFrame.APPLICATION_TABLE, ConfigFrame.APPLICATION_TABLE_PK);
		                                    eid 		=   getSequenceNextValue(ConfigFrame.EORGANISM_OBJECTID_MAPPING,ConfigFrame.EORGANISM_OBJECTID);
		                                    
		                                    
		                                    insertPreparedStatementApplication.setInt(1, appIdValue);//id
		                                    insertPreparedStatementApplication.setString(2, tempApplication.getApplicationName());
		                                    insertPreparedStatementApplication.setString(3, tempApplication.getApplicationDescription());
		                                    insertPreparedStatementApplication.setInt(4, eid);// eid
		                                    insertPreparedStatementApplication.setString(5, tempApplication.getInfrastructure());// infra
		                                    insertPreparedStatementApplication.setString(6, tempApplication.getLayer());// layer
		                                    insertPreparedStatementApplication.setString(7, tempApplication.getChannel());// channel
		                                    insertPreparedStatementApplication.setString(8, tempApplication.getSegment());// segment
		                                    insertPreparedStatementApplication.setString(9, tempApplication.getIn());// in 
		                                    insertPreparedStatementApplication.setString(10, tempApplication.getOut());// out
		                                    insertPreparedStatementApplication.setString(11, ConfigFrame.APPLICATION_TYPE_MODEL);// metadata type - MODEL
		                                                             
		                                    insertPreparedStatementApplication.setDate(12, today);// obj id
		                                    insertPreparedStatementApplication.setString(13, ConfigFrame.ADMIN_USER_NAME +"@"+ ConfigFrame.ADMIN_USER_ROLE);// user and role
		                                    insertPreparedStatementApplication.setString(14, ConfigFrame.LOAD_APP +"@"+ConfigFrame.LOAD_ACTION);//app  and action
		                                    insertPreparedStatementApplication.setString(15, ConfigFrame.TYPE_ENV_PRODUCTION);//env PRODUCTION
		                                    insertPreparedStatementApplication.setString(16, tempApplication.getApplicationName());//application_inventory name 
		                                    //
		                                    insertPreparedStatementApplication.setString(17, tempApplication.businessService);//businessService
		                                    insertPreparedStatementApplication.setString(18, tempApplication.businessUnit);//businessUnit
		                                    insertPreparedStatementApplication.setString(19, tempApplication.serviceCode);//serviceCode 
		                                    
		                                    insertPreparedStatementApplication.setTimestamp(20, new java.sql.Timestamp(System.currentTimeMillis()));//timestamp 
		                                    insertPreparedStatementApplication.setString(21,"APPLICATION"); //SYSTEM,APPLICATION, COMPONENT 
		                                    
		                                    // new Dec 29,2017 - ESYS_NAME_FK,ESYS_ID_FK,EAPP_ID
		                                    insertPreparedStatementApplication.setString(22, tempApplication.getSystemName());//system name
		                                    insertPreparedStatementApplication.setString(23, tempApplication.getSysID());//system id
		                                    insertPreparedStatementApplication.setString(24, tempApplication.getAppID());//app id
		                                       
		                                    result = insertPreparedStatementApplication.executeUpdate();
		                                    
		                                    log4j.debug("EORGANISM :: saveStateToDatabase:: APPLICATION = "+ tempApplication.getApplicationName() + "  @ SYSTEM  =" + tempSystem.getSystemName() + " SAVED OK @ POSITION ="+ x); 
		                                        
		                               }// for all the  applications part fo the system vector
                               
                               }//iif size >0 
                               
                               
                               // database components  
                               tempDatabasesArrayList  = (ArrayList)tempSystem.getDatabasesArrayList ();
                                      
                               if(tempDatabasesArrayList.size()>0){
                                    
                                    for( int k = 0; k  <  tempDatabasesArrayList.size() ; k ++){
                                        tempDatabase = new JDatabase();
                                        tempDatabase = (JDatabase)tempDatabasesArrayList.get(k);
                                        
                                        databaseCounter = getSequenceNextValue("EDATABASE","EDATABASE_ID");
                                        eid 		    = getSequenceNextValue(ConfigFrame.EORGANISM_OBJECTID_MAPPING,ConfigFrame.EORGANISM_OBJECTID);
                                         
                                          
                                        //EDATABASE_ID,EDATABASE_NAME,EDATABASE_DESCRIPTION, EORGANISM_EID,EAPPLICATION_ID_FK, EAPPLICATION_NAME_FK,EDATABASE_TYPE,EDATABASE_DRIVER_CLASS,
                                        //METADATA_TYPE,EENVIRONMENT_NAME_FK
                                        
                                        insertPreparedStatementDatabase.setInt(1, databaseCounter);
                                        insertPreparedStatementDatabase.setString(2, tempDatabase.getDatabaseName());
                                        insertPreparedStatementDatabase.setString(3, tempDatabase.getDatabaseDescription());
                                        insertPreparedStatementDatabase.setInt(4, eid);// eid
                                        insertPreparedStatementDatabase.setInt(5, appIdValue);// app_id
                                        insertPreparedStatementDatabase.setString(6, tempApplication.getApplicationName());// app_name
                                        
                                        // EDATABASE_DESCRIPTION, EDATABASE_TYPE, EDATABASE_DRIVER_CLASS
                                        insertPreparedStatementDatabase.setString(7, tempDatabase.getDatabaseType());// type
                                        insertPreparedStatementDatabase.setString(8, tempDatabase.getDatabaseDriverClass());// driver class
                                        insertPreparedStatementDatabase.setString(9, ConfigFrame.APPLICATION_TYPE_MODEL);// metadate type
                                        insertPreparedStatementDatabase.setString(10,ConfigFrame.TYPE_ENV_PRODUCTION);// env
                                        
                                        insertPreparedStatementDatabase.setTimestamp(11, new java.sql.Timestamp(System.currentTimeMillis()));//timestamp 
                                        
                                        
                                        result = insertPreparedStatementDatabase.executeUpdate();
                                        
                                       // SAVE EORGANISM
                                        saveEOrganismTable(eid,tempDatabase.getDatabaseName(),"EDATABASE","Database", databaseCounter);
                                        
                                    }// for all databases
                                    
                                }//db
                                
                                //log4j.debug("EORGANISM :: saveStateToDatabase:: insertPreparedStatementComponent: tempComponentsArrayList.size()=" + tempComponentsArrayList.size());  
                                
                                  
                    
                    }// all the SYSTEMS
                    // END ALL SYSTEMS
                                
                    // ALL APPLICATIONS
                    log4j.debug("EORGANISM :: saveStateToDatabase:: modelOfEApplication.size()=" + modelOfEApplication.size());  
                  
                    for( int i = 0; i <  modelOfEApplication.size() ; i++){
                                
                                tempApplication = new EApplication();
                                tempApplication = (EApplication) modelOfEApplication.elementAt(i);
                        
                                 //EAPPLICATION
                                 
                                 appIdValue = 	getSequenceNextValue(ConfigFrame.APPLICATION_TABLE, ConfigFrame.APPLICATION_TABLE_PK);
                                 eid 		=   getSequenceNextValue(ConfigFrame.EORGANISM_OBJECTID_MAPPING,ConfigFrame.EORGANISM_OBJECTID);
                                 
                                 
                                 insertPreparedStatementApplication.setInt(1, appIdValue);//id
                                 insertPreparedStatementApplication.setString(2, tempApplication.getApplicationName());
                                 insertPreparedStatementApplication.setString(3, tempApplication.getApplicationDescription());
                                 insertPreparedStatementApplication.setInt(4, eid);// eid
                                 insertPreparedStatementApplication.setString(5, tempApplication.getInfrastructure());// infra
                                 insertPreparedStatementApplication.setString(6, tempApplication.getLayer());// layer
                                 insertPreparedStatementApplication.setString(7, tempApplication.getChannel());// channel
                                 insertPreparedStatementApplication.setString(8, tempApplication.getSegment());// segment
                                 insertPreparedStatementApplication.setString(9, tempApplication.getIn());// in 
                                 insertPreparedStatementApplication.setString(10, tempApplication.getOut());// out
                                 insertPreparedStatementApplication.setString(11, ConfigFrame.APPLICATION_TYPE_MODEL);// metadata type - MODEL
                                                          
                                 insertPreparedStatementApplication.setDate(12, today);// obj id
                                 insertPreparedStatementApplication.setString(13, ConfigFrame.ADMIN_USER_NAME +"@"+ ConfigFrame.ADMIN_USER_ROLE);// user and role
                                 insertPreparedStatementApplication.setString(14, ConfigFrame.LOAD_APP +"@"+ConfigFrame.LOAD_ACTION);//app  and action
                                 insertPreparedStatementApplication.setString(15, ConfigFrame.TYPE_ENV_PRODUCTION);//env PRODUCTION
                                 insertPreparedStatementApplication.setString(16, tempApplication.getApplicationName());//application_inventory name 
                                 //
                                 insertPreparedStatementApplication.setString(17, tempApplication.businessService);//businessService
                                 insertPreparedStatementApplication.setString(18, tempApplication.businessUnit);//businessUnit
                                 insertPreparedStatementApplication.setString(19, tempApplication.serviceCode);//serviceCode 
                                 
                                 insertPreparedStatementApplication.setTimestamp(20, new java.sql.Timestamp(System.currentTimeMillis()));//timestamp 
                                 insertPreparedStatementApplication.setString(21,"APPLICATION"); //SYSTEM,APPLICATION, COMPONENT 
                                 
                                 // new Dec 29,2017 - ESYS_NAME_FK,ESYS_ID_FK,EAPP_ID
                                 insertPreparedStatementApplication.setString(22, tempApplication.getSystemName());//system name
                                 insertPreparedStatementApplication.setString(23, tempApplication.getSysID());//system id
                                 insertPreparedStatementApplication.setString(24, tempApplication.getAppID());//app id
                                    
                                 result = insertPreparedStatementApplication.executeUpdate();
                                 
                                 log4j.debug("EORGANISM :: saveStateToDatabase:: APPLICATION = "+ tempApplication.getApplicationName() + "  SAVED OK @ POSITION ="+ i); 
	                               
                                 
                                 // SAVE EORGANISM
                                 saveEOrganismTable(eid,tempApplication.getApplicationName(),ConfigFrame.APPLICATION_TABLE,ConfigFrame.APPLICATION, appIdValue);
                                     
                                 //log4j.debug("EORGANISM :: saveStateToDatabase:: tempApplication.getIn()" +   tempApplication.getIn());
                                 //log4j.debug("EORGANISM :: saveStateToDatabase:: MODEL : insertPreparedStatementApplication=" + tempApplication.getApplicationName());  
                                 // end EAPPLICATION insert
                                 
                                 //ESYSTEM
                                 //ESYSTEM_ID,
                                 //ESYSTEM_NAME,
                                 //ESYSTEM_DESCRIPTION,
                                 //EORGANISM_ID,
                                 //CREATED_DATE,
                                 //LAST_UPDATED_USER,
                                 //LAST_UPDATED_APP,
                                 //CREATED_TIMESTAMP
                                 
                                 // APP LINK IN
                                 if("SYSTEM".equals(tempApplication.getType())){
                                	 
                                	 systemIdValue 	= 	getSequenceNextValue(ConfigFrame.SYSTEM_TABLE, ConfigFrame.SYSTEM_TABLE_PK);
                                     eid 			=   getSequenceNextValue(ConfigFrame.EORGANISM_OBJECTID_MAPPING,ConfigFrame.EORGANISM_OBJECTID);
                                     
                                     
                                     insertPreparedStatementSystem.setInt(1, systemIdValue);//id
                                     insertPreparedStatementSystem.setString(2, tempApplication.getApplicationName());
                                     insertPreparedStatementSystem.setString(3, tempApplication.getApplicationDescription());
                                     insertPreparedStatementSystem.setInt(4, eid);// eid
                                     insertPreparedStatementSystem.setDate(5, today);// obj id
                                     insertPreparedStatementSystem.setString(6, ConfigFrame.ADMIN_USER_NAME +"@"+ ConfigFrame.ADMIN_USER_ROLE);
                                     insertPreparedStatementSystem.setString(7, ConfigFrame.LOAD_APP +"@"+ConfigFrame.LOAD_ACTION);
                                     insertPreparedStatementSystem.setTimestamp(8, new java.sql.Timestamp(System.currentTimeMillis()));//timestamp 
                                     
                                     // dec 30
                                     insertPreparedStatementSystem.setString(9, tempApplication.getSysID());// SYSTEM ID
                                     insertPreparedStatementSystem.setString(10, tempApplication.getInfrastructure());// infra
                                     insertPreparedStatementSystem.setString(11, tempApplication.getLayer());// layer
                                     insertPreparedStatementSystem.setString(12, tempApplication.getChannel());// channel
                                     insertPreparedStatementSystem.setString(13, tempApplication.getSegment());// segment
                                     insertPreparedStatementSystem.setString(14, tempApplication.getIn());// in 
                                     insertPreparedStatementSystem.setString(15, tempApplication.getOut());// out//
                                     
                                     insertPreparedStatementSystem.setString(16, tempApplication.businessService);//businessService
                                     insertPreparedStatementSystem.setString(17, tempApplication.businessUnit);//businessUnit
                                     insertPreparedStatementSystem.setString(18, tempApplication.serviceCode);//serviceCode 
                                 
                                     
                                     result = insertPreparedStatementSystem.executeUpdate();
                                     
                                     // SAVE EORGANISM
                                     saveEOrganismTable(eid,tempApplication.getApplicationName(),ConfigFrame.SYSTEM_TABLE,ConfigFrame.APPLICATION, systemIdValue);
                                  	 
                                 }// if system
                                 
                                 // APP LINK IN
                                 if(tempApplication.getIn()!=null && tempApplication.getIn().length() > 3){
                                	 
                                	 		tempAppLinkString = tempApplication.getIn().trim();
                                	 		
                                	 		nextTokenApp = "";
                                	 		 
                                	 		tempAppLinkStringTokenizer = new StringTokenizer(tempAppLinkString,",");
                                             
                                             while (tempAppLinkStringTokenizer.hasMoreTokens()) {
                                            	 
                                            	 		nextTokenApp = tempAppLinkStringTokenizer.nextToken().trim();
			                                 
						                                 // start   EAPPLICATION_LINK
						                                 //EAPPLICATION_LINK_ID,EAPPLICATION_LINK_NAME,EAPPLICATION_LINK_DESCRIPTION,EAPPLICATION_ID_FROM,EAPPLICATION_NAME_FROM,EAPPLICATION_ID_TO,EAPPLICATION_NAME_TO,LAST_UPDATED_DATE,LAST_UPDATED_USER,LAST_UPDATED_APPLICATION
						                                 //VALUES (?,?,?,?,?,?,?,?,?,?)
                                            	 		
                                            	 		 applicationLinkId 	= getSequenceNextValue("EAPPLICATION_LINK","EAPPLICATION_LINK_ID");
                                            	 		 eid 				= getSequenceNextValue(ConfigFrame.EORGANISM_OBJECTID_MAPPING,ConfigFrame.EORGANISM_OBJECTID);
                                                                                                         	 		  
						                                 insertPreparedStatementApplicationLinks.setInt(1, applicationLinkId);//link id
						                                 
						                                 insertPreparedStatementApplicationLinks.setString(2, "APP FROM " +nextTokenApp+ ">> APP TO "+ tempApplication.getApplicationName());//link name
						                                 insertPreparedStatementApplicationLinks.setString(3, "DESCRIPTION :: " +nextTokenApp+ ">> APP TO "+ tempApplication.getApplicationName());//link description                                              
						                                 
						                                 insertPreparedStatementApplicationLinks.setInt(4, 1);//app ID from
						                                 insertPreparedStatementApplicationLinks.setString(5, nextTokenApp);//app name from                                              
						                                
						                                 insertPreparedStatementApplicationLinks.setInt(6, appIdValue);//app ID to
						                                 insertPreparedStatementApplicationLinks.setString(7, tempApplication.getApplicationName().trim());//app name to                                              
						                                
						                                 insertPreparedStatementApplicationLinks.setDate(8, today);// obj id
						                                 insertPreparedStatementApplicationLinks.setString(9,  ConfigFrame.ADMIN_USER_NAME +"@"+ ConfigFrame.ADMIN_USER_ROLE);// user and role
						                                 insertPreparedStatementApplicationLinks.setString(10, ConfigFrame.LOAD_APP +"@"+ConfigFrame.LOAD_ACTION);//app and action
						                                 
						                                 result = insertPreparedStatementApplicationLinks.executeUpdate();
						                                 
						                                 // SAVE EORGANISM
                                          	 		     saveEOrganismTable(eid,"APP FROM " +nextTokenApp+ ">> APP TO "+ tempApplication.getApplicationName(),"EAPPLICATION_LINK","ApplicationLink", applicationLinkId);
						                                   
						                                 //log4j.debug("EORGANISM :: saveStateToDatabase:: EAPPLICATION_LINK: IN" +   "APP FROM " +nextTokenApp+ ">> APP TO "+ tempApplication.getApplicationName());
			                                 
						                                    // create default sequence flow as per APP_IN
						                                 	sequenceFlowIdString = getSequenceNextValue("ESEQUENCE_FLOW_TABLE","ESEQUENCE_FLOW_ID");
						                                 	 eid 				 = getSequenceNextValue(ConfigFrame.EORGANISM_OBJECTID_MAPPING,ConfigFrame.EORGANISM_OBJECTID);
		                                                      
			                                                // INSERT INTO ESEQUENCE_FLOW_TABLE(ESEQUENCE_FLOW_ID,ESEQUENCE_FLOW_NAME,ESEQUENCE_FLOW_DESCRIPTION,ESEQUENCE_FLOW_DATA,EAPPLICATION_NAME_FK,EAPPLICATION_ID_FK) VALUES (?,?,?,?,?,?)");
			                                                
			                                                insertPreparedStatementSequenceFlow.setInt(1, sequenceFlowIdString);
			                                                insertPreparedStatementSequenceFlow.setString(2, "APPLICATION INTEGRATION: IN:: APP FROM " +nextTokenApp+ ">> APP TO "+ tempApplication.getApplicationName());
			                                                insertPreparedStatementSequenceFlow.setString(3, "APPLICATION INTEGRATION: IN:: DEFAULT FLOW =" + "APP FROM " +nextTokenApp+ ">> APP TO "+ tempApplication.getApplicationName());
			                                                insertPreparedStatementSequenceFlow.setString(4, nextTokenApp + ">>" +tempApplication.getApplicationName());
			                                                insertPreparedStatementSequenceFlow.setString(5, tempApplication.getApplicationName());
			                                                insertPreparedStatementSequenceFlow.setInt(6, appIdValue);// app id
			                                                
			                                                result = insertPreparedStatementSequenceFlow.executeUpdate();
			                                                
			                                                // SAVE EORGANISM
	                                          	 		    saveEOrganismTable(eid,"APP_LINK IN::APP FROM " +nextTokenApp+ ">> APP TO "+ tempApplication.getApplicationName(),"ESEQUENCE_FLOW_TABLE","SequenceFlow", sequenceFlowIdString);
			                                                
                                             }// for all the tokens
			                                 
			                                 // stop   EAPPLICATION_LINK
                                 }// app in
                                 
                                 //log4j.error("EORGANISM :: saveStateToDatabase:: tempApplication.getOut()" +   tempApplication.getOut());
                                 // APP LINK OUT
                                 if(tempApplication.getOut()!=null && tempApplication.getOut().length() > 3){
                                	 
	                         	 		tempAppLinkString = tempApplication.getOut().trim();
	                         	 		nextTokenApp = "";
	                        	 		tempAppLinkStringTokenizer = new StringTokenizer(tempAppLinkString,",");
                                      
	                                      while (tempAppLinkStringTokenizer.hasMoreTokens()) {
	                                     	 
	                                     	 		nextTokenApp = tempAppLinkStringTokenizer.nextToken().trim();
			                                 
						                                 // start   EAPPLICATION_LINK
						                                 //EAPPLICATION_LINK_ID,EAPPLICATION_LINK_NAME,EAPPLICATION_LINK_DESCRIPTION,EAPPLICATION_ID_FROM,EAPPLICATION_NAME_FROM,EAPPLICATION_ID_TO,EAPPLICATION_NAME_TO,LAST_UPDATED_DATE,LAST_UPDATED_USER,LAST_UPDATED_APPLICATION
						                                 //VALUES (?,?,?,?,?,?,?,?,?,?)
	                                     	 		
	                                     	 		     applicationLinkId  = getSequenceNextValue("EAPPLICATION_LINK","EAPPLICATION_LINK_ID");
	                                     	 		     eid 				= getSequenceNextValue(ConfigFrame.EORGANISM_OBJECTID_MAPPING,ConfigFrame.EORGANISM_OBJECTID);
	                                                         
						                                 insertPreparedStatementApplicationLinks.setInt(1, applicationLinkId);//link id
						                                 
						                                 insertPreparedStatementApplicationLinks.setString(2, "APPLICATION INTEGRATION: FROM  >" + tempApplication.getApplicationName()+ ">>" +nextTokenApp);//link name
						                                 insertPreparedStatementApplicationLinks.setString(3, "DESCRIPTION :: " + tempApplication.getApplicationName() + ">>" +nextTokenApp);//link description                                              
						                                 
						                                 insertPreparedStatementApplicationLinks.setInt(4, appIdValue);//app ID from
						                                 insertPreparedStatementApplicationLinks.setString(5, tempApplication.getApplicationName().trim() );//app name from                                              
						                                
						                                 insertPreparedStatementApplicationLinks.setInt(6, 1);//app ID to
						                                 insertPreparedStatementApplicationLinks.setString(7, nextTokenApp);//app name to                                              
						                                
						                                 insertPreparedStatementApplicationLinks.setDate(8, today);// obj id
						                                 insertPreparedStatementApplicationLinks.setString(9, "Alex - EOrganism SuperAdmin");// obj id
						                                 insertPreparedStatementApplicationLinks.setString(10, "EORGANISM Swing Client");//app 
						                       
						                                 result = insertPreparedStatementApplicationLinks.executeUpdate();
						                                 
						                                 // SAVE EORGANISM
                                          	 		     saveEOrganismTable(eid,"APP FROM  >" + tempApplication.getApplicationName()+ ">>" +nextTokenApp,"EAPPLICATION_LINK","ApplicationLink", applicationLinkId);
						                              
						                                 	//log4j.debug("EORGANISM :: saveStateToDatabase:: EAPPLICATION_LINK: OUT" +   " LINK NAME >" + tempApplication.getApplicationName()+ ">>" +nextTokenApp);
			                                 
							                                // create default sequence flow as per APP_IN and APP_OUT
							                                sequenceFlowIdString = getSequenceNextValue("ESEQUENCE_FLOW_TABLE","ESEQUENCE_FLOW_ID");
							                                eid 				 = getSequenceNextValue(ConfigFrame.EORGANISM_OBJECTID_MAPPING,ConfigFrame.EORGANISM_OBJECTID);
		                                                         
				                                                
			                                                // INSERT INTO ESEQUENCE_FLOW_TABLE(ESEQUENCE_FLOW_ID,ESEQUENCE_FLOW_NAME,ESEQUENCE_FLOW_DESCRIPTION,ESEQUENCE_FLOW_DATA,EAPPLICATION_NAME_FK,EAPPLICATION_ID_FK) VALUES (?,?,?,?,?,?)");
			                                                
			                                                insertPreparedStatementSequenceFlow.setInt(1, sequenceFlowIdString);
			                                                insertPreparedStatementSequenceFlow.setString(2, "APPLICATION INTEGRATION: USED BY:  >" + tempApplication.getApplicationName()+ ">>" +nextTokenApp);
			                                                insertPreparedStatementSequenceFlow.setString(3, "APPLICATION INTEGRATION: DEFAULT FLOW =" + "APP FROM  >" + tempApplication.getApplicationName()+ ">>" +nextTokenApp);
			                                                insertPreparedStatementSequenceFlow.setString(4,  tempApplication.getApplicationName()+ ">>" +nextTokenApp);
			                                                insertPreparedStatementSequenceFlow.setString(5, tempApplication.getApplicationName());
			                                                insertPreparedStatementSequenceFlow.setInt(6, appIdValue);// app id
			                                                
			                                                result = insertPreparedStatementSequenceFlow.executeUpdate();
			                                                
			                                                // SAVE EORGANISM
	                                          	 		    saveEOrganismTable(eid,"APP_LINK OUT::APP FROM  >" + tempApplication.getApplicationName()+ ">>" +nextTokenApp,"ESEQUENCE_FLOW_TABLE","SequenceFlow", sequenceFlowIdString);
							                              
	                                      }// for all the tokens
		                                 
		                                 // stop   EAPPLICATION_LINK
                                 }// app out
                                
                                 // application components
                                 
                                        tempComponentsArrayList = (ArrayList)tempApplication.getComponentsArrayList();
                                        tempDatabasesArrayList  = (ArrayList)tempApplication.getDatabasesArrayList ();
                                        tempHardwaresArrayList  = (ArrayList)tempApplication.getHardwaresArrayList ();
                                        tempOperationsArrayList = (ArrayList)tempApplication.getOperationArrayList ();
                                        tempSequencesArrayList  = (ArrayList)tempApplication.getSequenceFlowArrayList();
                                
                                if(tempDatabasesArrayList.size()>0){
                                    
                                    for( int k = 0; k  <  tempDatabasesArrayList.size() ; k ++){
                                        tempDatabase = new JDatabase();
                                        tempDatabase = (JDatabase)tempDatabasesArrayList.get(k);
                                        
                                        databaseCounter = getSequenceNextValue("EDATABASE","EDATABASE_ID");
                                        eid 		    = getSequenceNextValue(ConfigFrame.EORGANISM_OBJECTID_MAPPING,ConfigFrame.EORGANISM_OBJECTID);
                                         
                                          
                                        //EDATABASE_ID,EDATABASE_NAME,EDATABASE_DESCRIPTION, EORGANISM_EID,EAPPLICATION_ID_FK, EAPPLICATION_NAME_FK,EDATABASE_TYPE,EDATABASE_DRIVER_CLASS,
                                        //METADATA_TYPE,EENVIRONMENT_NAME_FK
                                        
                                        insertPreparedStatementDatabase.setInt(1, databaseCounter);
                                        insertPreparedStatementDatabase.setString(2, tempDatabase.getDatabaseName());
                                        insertPreparedStatementDatabase.setString(3, tempDatabase.getDatabaseDescription());
                                        insertPreparedStatementDatabase.setInt(4, eid);// eid
                                        insertPreparedStatementDatabase.setInt(5, appIdValue);// app_id
                                        insertPreparedStatementDatabase.setString(6, tempApplication.getApplicationName());// app_name
                                        
                                        // EDATABASE_DESCRIPTION, EDATABASE_TYPE, EDATABASE_DRIVER_CLASS
                                        insertPreparedStatementDatabase.setString(7, tempDatabase.getDatabaseType());// type
                                        insertPreparedStatementDatabase.setString(8, tempDatabase.getDatabaseDriverClass());// driver class
                                        insertPreparedStatementDatabase.setString(9, ConfigFrame.APPLICATION_TYPE_MODEL);// metadate type
                                        insertPreparedStatementDatabase.setString(10,ConfigFrame.TYPE_ENV_PRODUCTION);// env
                                        
                                        insertPreparedStatementDatabase.setTimestamp(11, new java.sql.Timestamp(System.currentTimeMillis()));//timestamp 
                                        
                                        
                                        result = insertPreparedStatementDatabase.executeUpdate();
                                        
                                       // SAVE EORGANISM
                                        saveEOrganismTable(eid,tempDatabase.getDatabaseName(),"EDATABASE","Database", databaseCounter);
                                        
                                    }// for all databases
                                    
                                }//db
                                
                                //log4j.debug("EORGANISM :: saveStateToDatabase:: insertPreparedStatementComponent: tempComponentsArrayList.size()=" + tempComponentsArrayList.size());  
                                
                                // INSERT COMPONENTS
                                if(tempComponentsArrayList.size()>0){
                                    
                                             for( int j = 0; j <  tempComponentsArrayList.size() ; j++){
                                                
                                                tempComponent = new EComponent();
                                                tempComponent = (EComponent) tempComponentsArrayList.get(j);
                                              
                                                 //ECOMPONENT
                                                 //ECOMPONENT_ID,ECOMPONENT_NAME,ECOMPONENT_DESCRIPTION,EORGANISM_EID,EAPPLICATION_ID_FK,EAPPLICATION_NAME_FK,EDATABASE_ID_FK,EDATABASE_NAME_FK,METADATA_TYPE)

                                                 compId			= getSequenceNextValue("ECOMPONENT","ECOMPONENT_ID");
                                                 eid 			= getSequenceNextValue(ConfigFrame.EORGANISM_OBJECTID_MAPPING,ConfigFrame.EORGANISM_OBJECTID);
	                                                
                                                 insertPreparedStatementComponent.setInt(1, compId);
                                                 insertPreparedStatementComponent.setString(2, tempComponent.getComponentName());
                                                 insertPreparedStatementComponent.setString(3, tempComponent.getComponentDescription());
                                                 insertPreparedStatementComponent.setInt(4, eid);// eid
                                                 insertPreparedStatementComponent.setInt(5, appIdValue);// app_id
                                                 insertPreparedStatementComponent.setString(6, tempApplication.getApplicationName());// app_name
                                                 insertPreparedStatementComponent.setInt(7, 1);//db_id
                                                 insertPreparedStatementComponent.setString(8,  tempDatabase.getDatabaseName());//db_name
                                                 insertPreparedStatementComponent.setString(9,  ConfigFrame.APPLICATION_TYPE_MODEL);//metadata type
                                                 insertPreparedStatementComponent.setString(10, ConfigFrame.TYPE_ENV_PRODUCTION);//env - PRODUCTION
                                                 
                                                 insertPreparedStatementComponent.setTimestamp(11, new java.sql.Timestamp(System.currentTimeMillis()));//timestamp 
                                                 
                                                 
                                                 result = insertPreparedStatementComponent.executeUpdate();
                                                // SAVE EORGANISM
                                                 saveEOrganismTable(eid,tempComponent.getComponentName(),"ECOMPONENT","Component", compId);
                                                 
                                             }//for all components
                                             
                                }// if we have components
                                
                                // // INSERT COMPONENTS AS APPLICATIONS AS WELL
                                if(tempComponentsArrayList.size()>0){
                                    
                                    for( int j = 0; j <  tempComponentsArrayList.size() ; j++){
                                       
                                       tempComponent = new EComponent();
                                       tempComponent = (EComponent) tempComponentsArrayList.get(j);
                                        
                                        		//INSERT tempComponent EAPPLICATION Table
                                 
				                                 appIdValue = getSequenceNextValue(ConfigFrame.APPLICATION_TABLE,ConfigFrame.APPLICATION_TABLE_PK);
				                                 eid 		= getSequenceNextValue(ConfigFrame.EORGANISM_OBJECTID_MAPPING,ConfigFrame.EORGANISM_OBJECTID);
	                                                 
				                                 insertPreparedStatementApplication.setInt(1, appIdValue);//id
				                                 insertPreparedStatementApplication.setString(2, tempComponent.getComponentName());// app _name
				                                 insertPreparedStatementApplication.setString(3, "COMP > APP" + tempApplication.getApplicationName()+":" + tempComponent.getComponentName());
				                                 insertPreparedStatementApplication.setInt(4, eid);// eid
				                                 insertPreparedStatementApplication.setString(5, "");// infra
				                                 insertPreparedStatementApplication.setString(6, "");// layer
				                                 insertPreparedStatementApplication.setString(7, "");// channel
				                                 insertPreparedStatementApplication.setString(8, "");// segment
				                                 insertPreparedStatementApplication.setString(9, "");// in 
				                                 insertPreparedStatementApplication.setString(10, "");// out
				                                 insertPreparedStatementApplication.setString(11, ConfigFrame.COMPONENT);// metadata type
				                                                                                    
				                                 insertPreparedStatementApplication.setDate(12, today);// obj id
				                                 insertPreparedStatementApplication.setString(13, ConfigFrame.ADMIN_USER_NAME + ":"+ConfigFrame.ADMIN_USER_ROLE);// obj id
				                                 insertPreparedStatementApplication.setString(14, ConfigFrame.LOAD_APP + ":"+ConfigFrame.LOAD_ACTION);//app action
				                                 insertPreparedStatementApplication.setString(15, ConfigFrame.TYPE_ENV_PRODUCTION);//env PRODUCTION
				                                 insertPreparedStatementApplication.setString(16, tempApplication.getApplicationName());//application_inventory name 
				                                 //
				                                 insertPreparedStatementApplication.setString(17, tempApplication.businessService);//businessService
				                                 insertPreparedStatementApplication.setString(18, tempApplication.businessUnit);//businessUnit
				                                 insertPreparedStatementApplication.setString(19, tempApplication.serviceCode);//serviceCode 
				                                 
				                                 insertPreparedStatementApplication.setTimestamp(20, new java.sql.Timestamp(System.currentTimeMillis()));//timestamp 
	                                            
				                                 result = insertPreparedStatementApplication.executeUpdate();
				                                 
				                                 // SAVE EORGANISM 
				                                 saveEOrganismTable(eid,tempApplication.getApplicationName(),ConfigFrame.APPLICATION_TABLE,ConfigFrame.APPLICATION,appIdValue);
                                         
                                    	}//for all components
                                    
                                	}// if we have components
                                 
                                if(tempHardwaresArrayList.size()>0){
    
                                        for( int l = 0; l  <  tempHardwaresArrayList.size() ; l ++){
                                            
                                            tempHardware = new EHardware();
                                            tempHardware = (EHardware)tempHardwaresArrayList.get(l);
                                            
                                            hardwareId		= getSequenceNextValue("EHARDWARE_PHYSICAL_TABLE","EHARDWARE_PHYSICAL_ID");
                                            eid 			= getSequenceNextValue(ConfigFrame.EORGANISM_OBJECTID_MAPPING,ConfigFrame.EORGANISM_OBJECTID);
                                             
                                            insertPreparedStatementHardware.setInt(1, hardwareId);
                                            
                                            insertPreparedStatementHardware.setString(2, tempHardware.getHardwareName());
                                            insertPreparedStatementHardware.setString(3, tempHardware.getDescription());
                                            insertPreparedStatementHardware.setInt(4, eid);// eid
                                            insertPreparedStatementHardware.setInt(5, nextValue);// app_id
                                             
                                            insertPreparedStatementHardware.setString(6, tempApplication.getApplicationName());// app_name
                                            insertPreparedStatementHardware.setString(7, tempConfig.getName()+ ":"+tempConfig.getSegment()+":"+tempConfig.getReleaseName());// env name
                                            
                                            insertPreparedStatementHardware.setTimestamp(8, new java.sql.Timestamp(System.currentTimeMillis()));//timestamp 
                                             
                                            result = insertPreparedStatementHardware.executeUpdate();
                                            
                                            // SAVE EORGANISM
                                            saveEOrganismTable(eid,tempHardware.getHardwareName(),"EHARDWARE_PHYSICAL_TABLE","Hardware", hardwareId);
                                        
                                        }// for
                                }// hw
                                if(tempOperationsArrayList.size()>0){
                                            
                                            for( int m = 0; m  <  tempOperationsArrayList.size() ; m ++){
                                                
                                                tempOperation = new EOperation();
                                                tempOperation = (EOperation)tempOperationsArrayList.get(m);
                                                 
                                                serviceId		= getSequenceNextValue("ESERVICE","ESERVICE_ID");
                                                eid 			= getSequenceNextValue(ConfigFrame.EORGANISM_OBJECTID_MAPPING,ConfigFrame.EORGANISM_OBJECTID);
	                                            
                                                insertPreparedStatementService.setInt(1, serviceId);
                                                insertPreparedStatementService.setString(2, tempOperation.getName());
                                                insertPreparedStatementService.setString(3, tempOperation.getDescription());
                                                insertPreparedStatementService.setInt(4, eid);// eid
                                                insertPreparedStatementService.setInt(5, nextValue);// app_id
                                                insertPreparedStatementService.setString(6, tempApplication.getApplicationName());// app_name
                                                insertPreparedStatementHardware.setTimestamp(7, new java.sql.Timestamp(System.currentTimeMillis()));//timestamp 
                                                 
                                                result = insertPreparedStatementService.executeUpdate();
                                                
                                                // SAVE EORGANISM
                                                saveEOrganismTable(eid,tempOperation.getName(),"ESERVICE","Service", serviceId);
                                                
                                                //log4j.debug("EORGANISM :: saveStateToDatabase:: ESERVICE: " +   tempOperation.getName());
                                                  
                                                //EMETADATA_SERVICE_ID,EMETADATA_SERVICE_NAME,EMETADATA_SERVICE_TYPE,EMETADATA_SERVICE_DESCRIPTION,EMETADATA_APPLICATION_NAME,EMETADATA_LAST_UPDATED_DATE,EMETADATA_LAST_UPDATED_USER,EMETADATA_LAST_UPDATED_APP) VALUES (?,?,?,?,?,?,?,?)");
                                                
                                                insertPreparedStatementMetadataService.setInt(1, getSequenceNextValue("EMETADATA_SERVICE","EMETADATA_SERVICE_ID"));
                                                insertPreparedStatementMetadataService.setString(2, tempOperation.getName());
                                                insertPreparedStatementMetadataService.setString(3, "TYPE - "+tempOperation.getName());
                                                insertPreparedStatementMetadataService.setString(4, "DESCRIPTION - "+tempOperation.getDescription());
                                                insertPreparedStatementMetadataService.setString(5, tempApplication.getApplicationName());// app_name
                                                insertPreparedStatementMetadataService.setDate(6, today);// 
                                                insertPreparedStatementMetadataService.setString(7, ConfigFrame.LOAD_APP);// 
                                                insertPreparedStatementMetadataService.setString(8, "XML Loader Command");// 
                                                
                                                 result = insertPreparedStatementMetadataService.executeUpdate();
                                                
                                                //EINTERFACE_ID,EINTERFACE_NAME,EINTERFACE_DESCRIPTION,EAPPLICATION_ID_FK,EAPPLICATION_NAME_FK,ECOMPONENT_NAME_FK,ESERVICE_NAME_FK
                                                insertPreparedStatementInterface.setInt(1, getSequenceNextValue("EINTERFACE","EINTERFACE_ID"));
                                                insertPreparedStatementInterface.setString(2, tempOperation.getMethod());
                                                insertPreparedStatementInterface.setString(3, tempOperation.getDescription());
                                                insertPreparedStatementInterface.setInt(4, nextValue);// app_id
                                                insertPreparedStatementInterface.setString(5,tempApplication.getApplicationName());// app name
                                                insertPreparedStatementInterface.setString(6, tempOperation.getService());//
                                                insertPreparedStatementInterface.setString(7, tempOperation.getSegment());// 
                                                
                                                result = insertPreparedStatementInterface.executeUpdate();
                                                
                                                //log4j.debug("EORGANISM :: saveStateToDatabase:: EINTERFACE: " +   tempOperation.getMethod());
                                                
                                                // each interface will have a sequnece item associated - one records per each method
                                                
                                               /*
                                                insertPreparedStatementSequenceItem.setInt(1, getSequenceNextValue("ESEQUENCE_ITEM_TABLE","ESEQUENCE_ITEM_ID"));
                                                insertPreparedStatementSequenceItem.setString(2, "AUTOMATIC -- ITEM FROM INTERFACE");
                                                insertPreparedStatementSequenceItem.setString(3, tempApplication.getApplicationName()+":"+ tempOperation.getService()+":"+tempOperation.getMethod());
                                                insertPreparedStatementSequenceItem.setString(4,"APP XML FIlE loader");// updated app
                                                insertPreparedStatementSequenceItem.setDate(5, today);//created date
                                                insertPreparedStatementSequenceItem.setDate(6, today);//UPDATED date    
                                                insertPreparedStatementSequenceItem.setString(7, tempApplication.getApplicationName());// app name 
                                                */

                                                insertPreparedStatementSequenceItem.setInt(1, getSequenceNextValue("ESEQUENCE_ITEM_TABLE","ESEQUENCE_ITEM_ID"));
                                                insertPreparedStatementSequenceItem.setString(2, "NO SEQ FLOW");
                                                insertPreparedStatementSequenceItem.setString(3, "NO SEQ FLOW");
                                                
                                                insertPreparedStatementSequenceItem.setInt(4, 0); // seq flow id
                                                insertPreparedStatementSequenceItem.setString(5, tempApplication.getApplicationName());//app name
                                                insertPreparedStatementSequenceItem.setInt(6, nextValue);// app id
                                               
                                                insertPreparedStatementSequenceItem.setString(7,"Swing Admin - INTERFACES DEFAULT - APP XML FIlE loader");// updated app
                                                insertPreparedStatementSequenceItem.setDate(8, today);//created date
                                                insertPreparedStatementSequenceItem.setDate(9, today);//UPDATED date 
                                                 
                                                result = insertPreparedStatementSequenceItem.executeUpdate();
                                                
                                                // insert in metadata 
                                                //insertPreparedStatementMetadataOperations   = connection.prepareStatement("INSERT INTO EMETADATA_INTERFACE(
                                                //EMETADATA_INTERFACE_ID,EMETADATA_INTERFACE_NAME,EMETADATA_COMPONENT_NAME,EMETADATA_APPLICATION_NAME,EMETADATA_LAST_UPDATED_DATE,EMETADATA_LAST_UPDATED_USER,EMETADATA_LAST_UPDATED_APP)VALUES (?,?,?,?,?,?,?)"); 
                                                      
                                                insertPreparedStatementMetadataOperations.setInt(1, getSequenceNextValue("EMETADATA_INTERFACE","EMETADATA_INTERFACE_ID"));
                                                insertPreparedStatementMetadataOperations.setString(2, tempOperation.getMethod());
                                                insertPreparedStatementMetadataOperations.setString(3, tempOperation.getService());// component
                                                insertPreparedStatementMetadataOperations.setString(4,tempApplication.getApplicationName());// app name
                                                insertPreparedStatementMetadataOperations.setDate(5, today);// 
                                                insertPreparedStatementMetadataOperations.setString(6, "Alex SUPER ADMIN");// 
                                                insertPreparedStatementMetadataOperations.setString(7, "XML Loader Command");// 
          
                                                result = insertPreparedStatementMetadataOperations.executeUpdate();
                                                    
                                             
                                            }// for all operations/  interfaces
                                            
                                }// services
                       
                                //sequence flows
                                //ESEQUENCE_FLOW_ID,ESEQUENCE_FLOW_NAME,ESEQUENCE_FLOW_DESCRIPTION,ESEQUENCE_FLOW_DATA
                                if(tempSequencesArrayList.size()>0){
                                            
                                            for( int k = 0; k  <  tempSequencesArrayList.size() ; k ++){
                                                //log4j.debug("EORGANISM :: saveStateToDatabase:: tempSequencesArrayList: " +  k);
                                                
                                                tempSequenceFlow = new ESequenceFlow();
                                                tempSequenceFlow = (ESequenceFlow)tempSequencesArrayList.get(k);
                                                
                                                sequenceFlowString = tempSequenceFlow.getText();
                                                
                                                sequenceFlowIdString = getSequenceNextValue("ESEQUENCE_FLOW_TABLE","ESEQUENCE_FLOW_ID");
                                                eid 			     = getSequenceNextValue(ConfigFrame.EORGANISM_OBJECTID_MAPPING,ConfigFrame.EORGANISM_OBJECTID);
	                                            // INSERT INTO ESEQUENCE_FLOW_TABLE(ESEQUENCE_FLOW_ID,ESEQUENCE_FLOW_NAME,ESEQUENCE_FLOW_DESCRIPTION,ESEQUENCE_FLOW_DATA,EAPPLICATION_NAME_FK,EAPPLICATION_ID_FK) VALUES (?,?,?,?,?,?)");
                                                
                                                insertPreparedStatementSequenceFlow.setInt(1, sequenceFlowIdString);
                                                insertPreparedStatementSequenceFlow.setString(2, tempSequenceFlow.getName());
                                                insertPreparedStatementSequenceFlow.setString(3, "DESCRIPTION =" + tempSequenceFlow.getName());
                                                insertPreparedStatementSequenceFlow.setString(4, tempSequenceFlow.getText());
                                                insertPreparedStatementSequenceFlow.setString(5, tempApplication.getApplicationName());
                                                insertPreparedStatementSequenceFlow.setInt(6, nextValue);// app id
                                                
                                                result = insertPreparedStatementSequenceFlow.executeUpdate();
                                                
                                                // SAVE EORGANISM
                                                saveEOrganismTable(eid,tempSequenceFlow.getName(),"ESEQUENCE_FLOW_TABLE","Sequence Flow", sequenceFlowIdString);
                                                  //tokenize the flow values and insert in sequence flow item
                                                
                                                tokenizerFlow = new StringTokenizer(sequenceFlowString,"##");
                                                
                                                     while (tokenizerFlow.hasMoreTokens()) {
                                                         
                                                         	tokenFlowString = tokenizerFlow.nextToken().trim();
                                                         
                                                         	//log4j.debug("saveStateToDatabase:: ESEQUENCE_ITEM_TABLE: " + tokenFlowString);
                                                           	//tokenizerItem = new StringTokenizer(tokenFlowString,":");
                                                          	//INSERT INTO ESEQUENCE_ITEM_TABLE(ESEQUENCE_ITEM_ID,ESEQUENCE_ITEM_NAME,ESEQUENCE_ITEM_DATA,ESEQUENCE_FLOW_ID_FK,EAPPLICATION_NAME_FK,EAPPLICATION_ID_FK,LAST_UPDATED_APPLICATION,CREATED_DATE,UPDATED_DATE) VALUES (?,?,?,?,?,?,?,?,?)");
                                                               insertPreparedStatementSequenceItem.setInt(1, getSequenceNextValue("ESEQUENCE_ITEM_TABLE","ESEQUENCE_ITEM_ID"));
                                                             insertPreparedStatementSequenceItem.setString(2, tokenFlowString);
                                                             insertPreparedStatementSequenceItem.setString(3, tokenFlowString);
                                                             
                                                             insertPreparedStatementSequenceItem.setInt(4, sequenceFlowIdString); // seq flow id
                                                             insertPreparedStatementSequenceItem.setString(5, tempApplication.getApplicationName());//app name
                                                             insertPreparedStatementSequenceItem.setInt(6, nextValue);// app id
                                                            
                                                            
                                                             insertPreparedStatementSequenceItem.setString(7,"Swing Admin - FLOW - APP XML FIlE loader");// updated app
                                                             insertPreparedStatementSequenceItem.setDate(8, today);//created date
                                                             insertPreparedStatementSequenceItem.setDate(9, today);//UPDATED date    
                                                             
                                                             result = insertPreparedStatementSequenceItem.executeUpdate();
                                                             
                                                         //}// item
                                                         
                                                     }// flow
                    
                                            }// for all sequences
                                            
                                           
                                }// for all the sequence flows
                             
                                // insert INTERFACE_MODEL_TABLE ,ESEQUENCE_FLOW,ESEQUENCE_ITEM,ERELATION_SEQ,ERELATION_APP,ERELATION_COMP
                               // log4j.debug("EORGANISM :: saveStateToDatabase OK XXXXXXXXXXXX:: I="+i+":: app=" + tempApplication.getApplicationName()+ " - "+ tempEnvironment.getName());  
                  
                     }// all the applications
                  
                     insertPreparedStatementRelease.close();
                     insertPreparedStatementEnvironment.close();
                     insertPreparedStatementProject.close();
                     insertPreparedStatementApplication.close(); 
                     insertPreparedStatementSystem.close(); 
                     insertPreparedStatementApplicationLinks.close(); 
                     insertPreparedStatementComponent.close();
                     insertPreparedStatementDatabase.close();
                     insertPreparedStatementHardware.close();
                     insertPreparedStatementService.close();
                     insertPreparedStatementInterface.close();
                     insertPreparedStatementSequenceFlow.close();
                     insertPreparedStatementSequenceItem.close();
                     insertPreparedStatementMetadataService.close();
                     insertPreparedStatementMetadataOperations.close();
                        
                     connection.close();
                  
                     //JOptionPane.showMessageDialog( null, "saveDatabase OK");
                
                     log4j.debug("EORGANISM :: saveStateToDatabase:: insertPreparedStatement: FINAL OK - SAVE STATE TO DB OK ##############");              
                
                } catch(SQLException sqle){
                	 log4j.error("EORGANISM :: saveStateToDatabase:: SQLException: " + sqle.toString());
                     log4j.debug("EORGANISM :: saveStateToDatabase:: SQLException: " + sqle.toString());
                   
                     JOptionPane.showMessageDialog( null,"SQLException  "    + sqle.toString()+"\n");
                   
                } catch(Exception e){
                	log4j.error("EORGANISM :: saveStateToDatabase:: Exception: " + e.toString());
                    log4j.debug("EORGANISM :: saveStateToDatabase:: Exception: " + e.toString());
                    
                    JOptionPane.showMessageDialog( null, "Exception  " +e.toString()+"\n");
                    
    
                }// end catch
           
        }// saveStateToDatabase
        
        public void loadWSDLLoaderFile(){
            
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            
            //int result = fileChooser.showSaveDialog(null);
            
            int result = fileChooser.showOpenDialog(null);
                                                                    
            if(result == JFileChooser.CANCEL_OPTION){
                    return;
            }// if
              
            File csvLoadFile = fileChooser.getSelectedFile();
        
             if( (csvLoadFile == null) || csvLoadFile.getName().equals("")){
                    //JOptionPane.showMessageDialog(  null, "EORGANISM :: Open CSV LOAD :: Invalid CSV LOAD Project File","EORGANISM :: Open CSV LOAD :: Invalid CSV LOAD Project File",JOptionPane.ERROR_MESSAGE);
            	 	log4j.debug("EORGANISM :: loadWSDLLoaderFile :: Open CSV LOAD :: Invalid CSV LOAD Project File");
             } //
             
             String filePathName = csvLoadFile.getPath();

             log4j.debug("EORGANISM :: loadWSDLLoaderFile :: load filePathName " + filePathName );  
             loadWSDLFile(filePathName);
             
        }//
         
        //loadPersonCSV();
    	//savePerson();

    	//loadBusinessUnitCSV();
    	//saveBusinessUnit();
         
    	//loadBusinessServiceCSV();
    	//saveBusinessService();
        
        
        public void loadPersonCSV(String _fileLocation){
            //String csvFileToRead        = _file;  
            //BufferedReader br           = null;  
            String line                 = "";  
            String splitBy              = ",";  
             
            EPerson serviceObjectWorker    = new EPerson();  
            
            try {  
              
                        br = new BufferedReader(new FileReader(_fileLocation));  
                        
                        while ((line = br.readLine()) != null) {  
                          
                            // split on comma(',')  
                            String[] servicesArray = line.split(splitBy);  
                          
                            // create service object to store values  
                            serviceObjectWorker = new EPerson();  
                          
                            // add values from csv to service worker object  
                            serviceObjectWorker.setName(servicesArray[0].trim()); // probe Name 
                            
                            
                            // adding service objects to a list  
                            personArrayList.add(serviceObjectWorker);  
                            serviceObjectWorker.printToString();
                           
                           // log4j.debug(serviceObjectWorker.printToString());  
                        
                        	}// while
                          
                        	log4j.debug("****** loadPersonCSV - ESERVICE load OK " ); 
                
                 } catch (FileNotFoundException e) {  
                     			//JOptionPane.showMessageDialog(  null, "EORGANISM :: loadPersonCSV CSV LOAD :: FileNotFoundException","EORGANISM :: loadPersonCSV CSV LOAD :: FileNotFoundException", JOptionPane.ERROR_MESSAGE);
                	 			log4j.error("EORGANISM :: loadPersonCSV CSV LOAD :: FileNotFoundException"); 
                	 			log4j.debug("EORGANISM :: loadPersonCSV CSV LOAD :: FileNotFoundException"); 
                	 			e.printStackTrace();  
                                
                 } catch (IOException e) {  
                	 			//JOptionPane.showMessageDialog(  null, "EORGANISM :: loadPersonCSV CSV LOAD :: IOException", "EORGANISM :: loadPersonCSV CSV LOAD :: IOException",JOptionPane.ERROR_MESSAGE);
                	 			log4j.error("EORGANISM :: loadPersonCSV CSV LOAD :: IOException"); 
                	 			log4j.debug("EORGANISM :: loadPersonCSV CSV LOAD :: IOException"); 
                	 			e.printStackTrace();  
                                
                } catch (Exception e) {  
                       			//JOptionPane.showMessageDialog(  null, "EORGANISM :: loadPersonCSV CSV LOAD :: File Format Exception", "EORGANISM :: loadPersonCSV CSV LOAD :: File Format Exception", JOptionPane.ERROR_MESSAGE);
                				log4j.error("EORGANISM :: loadPersonCSV CSV LOAD :: Exception"); 
                				log4j.debug("EORGANISM :: loadPersonCSV CSV LOAD :: Exception"); 
                				e.printStackTrace();  
                } finally {  
                           if (br != null) {  
                                try {  
                                    br.close();  
                                } catch (IOException e) {  
                                    e.printStackTrace();  
                                }  
                        }  //if
                }// finally

            
        }//loadPersonCSV
         
        public void loadBusinessUnitCSV(String _fileLocation){
            //String csvFileToRead        = _file;  
            //BufferedReader br           = null;  
            String line                 = "";  
            String splitBy              = ",";  
             
            EBusinessUnit eBusinessUnitWorker    = new EBusinessUnit();  
            
            try {  
              
                        br = new BufferedReader(new FileReader(_fileLocation));  
                        
                        while ((line = br.readLine()) != null) {  
                          
                            // split on comma(',')  
                            String[] servicesArray = line.split(splitBy);  
                          
                            // create service object to store values  
                            eBusinessUnitWorker = new EBusinessUnit();  
                          
                            // add values from csv to service worker object  
                            eBusinessUnitWorker.setName(servicesArray[0].trim()); // service unit Name 
                            
                            
                            // adding service objects to a list  
                            businessUnitArrayList.add(eBusinessUnitWorker);  
                           
                            log4j.debug(eBusinessUnitWorker.printToString());  
                        
                        }// while
                          
                        log4j.debug("****** loadBusinessUnitCSV - ESERVICE load OK " ); 
                
                 } catch (FileNotFoundException fnfe) {  
                     				//JOptionPane.showMessageDialog(  null,  "EORGANISM :: loadBusinessUnitCSV CSV LOAD :: FileNotFoundException", "EORGANISM :: loadBusinessUnitCSV CSV LOAD :: FileNotFoundException", JOptionPane.ERROR_MESSAGE);
                					fnfe.printStackTrace();  
                     				log4j.error("EORGANISM :: loadBusinessUnitCSV CSV LOAD :: FileNotFoundException", "EORGANISM :: loadBusinessUnitCSV CSV LOAD :: FileNotFoundException" + fnfe.toString());
                     				log4j.debug("EORGANISM :: loadBusinessUnitCSV CSV LOAD :: FileNotFoundException", "EORGANISM :: loadBusinessUnitCSV CSV LOAD :: FileNotFoundException" + fnfe.toString());
                                    
                 } catch (IOException ioe) {  
                	 				//JOptionPane.showMessageDialog(  null, "EORGANISM :: loadBusinessUnitCSV CSV LOAD :: IOException","EORGANISM :: loadBusinessUnitCSV CSV LOAD :: IOException",  JOptionPane.ERROR_MESSAGE);
                	 				ioe.printStackTrace();  
                	 				log4j.error("EORGANISM :: loadBusinessUnitCSV CSV LOAD :: FileNotFoundException", "EORGANISM :: loadBusinessUnitCSV CSV LOAD :: IOException"+ ioe.toString());
                   	 				log4j.debug("EORGANISM :: loadBusinessUnitCSV CSV LOAD :: FileNotFoundException", "EORGANISM :: loadBusinessUnitCSV CSV LOAD :: IOException"+ ioe.toString());
                   	 	         
                 } catch (Exception e) {  
                       				//JOptionPane.showMessageDialog(  null,"EORGANISM :: loadBusinessUnitCSV CSV LOAD :: File Format Exception", "EORGANISM :: loadBusinessUnitCSV CSV LOAD :: File Format Exception", JOptionPane.ERROR_MESSAGE);
                                   e.printStackTrace();  
                                   log4j.error("EORGANISM :: loadBusinessUnitCSV CSV LOAD :: FileNotFoundException", "EORGANISM :: loadBusinessUnitCSV CSV LOAD :: Exception"+ e.toString());
                                   log4j.debug("EORGANISM :: loadBusinessUnitCSV CSV LOAD :: FileNotFoundException", "EORGANISM :: loadBusinessUnitCSV CSV LOAD :: Exception"+ e.toString());
                                          
                 } finally {  
                           if (br != null) {  
                                try {  
                                    br.close();  
                                } catch (IOException e) {  
                                    e.printStackTrace();  
                                }  
                        }  //if
                }// finally

            
        }//loadBusinessUnitCSV
        
        
        
        
        public void loadBusinessServiceCSV(String _fileLocation){
            //String csvFileToRead        = _file;  
            //BufferedReader br           = null;  
            String line                 = "";  
            String splitBy              = ",";  
             
            EBusinessService eBusinessServiceWorker    = new EBusinessService();  
            
            try {  
              
                        br = new BufferedReader(new FileReader(_fileLocation));  
                        
                        while ((line = br.readLine()) != null) {  
                          
                            // split on comma(',')  
                            String[] servicesArray = line.split(splitBy);  
                          
                            // create service object to store values  
                            eBusinessServiceWorker = new EBusinessService();  
                          
                            // add values from csv to service worker object  
                            eBusinessServiceWorker.setBusinessUnit (servicesArray[0].trim()); // business Unit  Name  
                            eBusinessServiceWorker.setName(servicesArray[1].trim()); // business Service  Name 
                             
                            
                            // adding service objects to a list  
                            businessServiceArrayList.add(eBusinessServiceWorker);  
                            
                            log4j.debug(eBusinessServiceWorker.printToString());  
                        
                        }// while
                          
                        log4j.debug("****** loadBusinessServiceCSV - ESERVICE load OK " ); 
                
                 } catch (FileNotFoundException fnfe) {  
                     JOptionPane.showMessageDialog(  null,
                                                     "EORGANISM :: loadBusinessServiceCSV CSV LOAD :: FileNotFoundException",
                                                     "EORGANISM :: loadBusinessServiceCSV CSV LOAD :: FileNotFoundException",
                                                     JOptionPane.ERROR_MESSAGE);
                     			log4j.error("EORGANISM :: loadBusinessServiceCSV CSV LOAD :: FileNotFoundException" + fnfe.toString());
                     			log4j.debug("EORGANISM :: loadBusinessServiceCSV CSV LOAD :: FileNotFoundException" + fnfe.toString());
                                fnfe.printStackTrace();  
                 } catch (IOException ioe) {  
                	 
                    JOptionPane.showMessageDialog(  null,
                                                    "EORGANISM :: loadBusinessServiceCSV CSV LOAD :: IOException",
                                                    "EORGANISM :: loadBusinessServiceCSV CSV LOAD :: IOException",
                                                    JOptionPane.ERROR_MESSAGE);
                    			log4j.error("EORGANISM :: loadBusinessServiceCSV CSV LOAD :: IOException" + ioe.toString());
                    			log4j.debug("EORGANISM :: loadBusinessServiceCSV CSV LOAD :: IOException" + ioe.toString());
                                ioe.printStackTrace(); 
                                
                } catch (Exception e) {  
                       JOptionPane.showMessageDialog(  null,
                                                       "EORGANISM :: loadBusinessServiceCSV CSV LOAD :: File Format Exception",
                                                       "EORGANISM :: loadBusinessServiceCSV CSV LOAD :: File Format Exception",
                                                       JOptionPane.ERROR_MESSAGE);
                       
                       			log4j.error("EORGANISM :: loadBusinessServiceCSV CSV LOAD :: File Format Exception" + e.toString());
                       			log4j.debug("EORGANISM :: loadBusinessServiceCSV CSV LOAD :: File Format Exception" + e.toString());
                                 e.printStackTrace();  
                                   
                } finally {  
                           if (br != null) {  
                                try {  
                                    br.close();  
                                } catch (IOException e) { 
                                	log4j.error("EORGANISM :: IOException CSV LOAD :: " + e.toString());
                                    e.printStackTrace();  
                                }  
                        }  //if
                }// finally

            
        }//loadBusinessServiceCSV
        
        public void loadOperationsFromCsvFile(String _fileLocation){
            //String csvFileToRead        = _file;  
            //BufferedReader br           = null;  
            String line                 = "";  
            String splitBy              = ",";  
             
            // reset
            operationsArrayList = new ArrayList();
            			
            EInterface  operationObjectWorker;
            try {  
              
	                        br = new BufferedReader(new FileReader(_fileLocation));  
	                        
	                        while ((line = br.readLine()) != null) {  
	                          
	                            // split on comma(',')  
	                            String[] servicesArray = line.split(splitBy);  
	                          
	                            // create interface object to store values  
	                            operationObjectWorker = new EInterface();  
	                          
	                            // add values from csv to interface worker object  
	                            operationObjectWorker.setApplication(servicesArray[0].trim()); // application  name 
	                            operationObjectWorker.setComponent(servicesArray[1].trim()); // component  name 
	                            operationObjectWorker.setService(servicesArray[2].trim());// service  name
	                           
	                            operationObjectWorker.setInterfaceName(servicesArray[3].trim());// method/ interface  name
	                            operationObjectWorker.setInterfaceType(servicesArray[4].trim());// method type  
	                            //operationObjectWorker.setDescription();// method description
	                            operationObjectWorker.setInformation(servicesArray[5].trim());
	                             // adding service objects to a list  
	                            operationsArrayList.add(operationObjectWorker);  
	                             //log4j.debug(operationObjectWorker.toString());  
	                         }// while
                          
	                        log4j.debug("****** loadOperationsFromCsvFile - OPERATIONS CSV LOAD  OK " ); 
                
                 } catch (FileNotFoundException fnfe) {  
                     JOptionPane.showMessageDialog(  null,
                                                     "EORGANISM :: loadOperationsFromCsvFile CSV LOAD :: FileNotFoundException",
                                                     "EORGANISM :: loadOperationsFromCsvFile CSV LOAD :: FileNotFoundException",
                                                     JOptionPane.ERROR_MESSAGE);
                     			fnfe.printStackTrace();  
                                log4j.error("EORGANISM :: loadOperationsFromCsvFile CSV LOAD :: FileNotFoundException" + fnfe.toString());
                                log4j.error("EORGANISM :: loadOperationsFromCsvFile CSV LOAD :: FileNotFoundException"+ fnfe.toString());
                                
                 } catch (IOException ioe) {  
                    JOptionPane.showMessageDialog(  null,
                                                    "EORGANISM :: loadOperationsFromCsvFile CSV LOAD :: IOException",
                                                    "EORGANISM :: loadOperationsFromCsvFile CSV LOAD :: IOException",
                                                    JOptionPane.ERROR_MESSAGE);
                                ioe.printStackTrace();  
                                log4j.error("EORGANISM :: loadOperationsFromCsvFile CSV LOAD :: IOException" + ioe.toString());
                                log4j.error("EORGANISM :: loadOperationsFromCsvFile CSV LOAD :: IOException" + ioe.toString());
                                
                } catch (Exception e) {  
                       JOptionPane.showMessageDialog(  null,
                                                       "EORGANISM :: loadOperationsFromCsvFile CSV LOAD :: File Format Exception",
                                                       "EORGANISM :: loadOperationsFromCsvFile CSV LOAD :: File Format Exception",
                                                       JOptionPane.ERROR_MESSAGE);
                                   e.printStackTrace();  
                                   log4j.error("EORGANISM :: loadOperationsFromCsvFile CSV LOAD :: File Format Exception" + e.toString());
                                   log4j.error("EORGANISM :: loadOperationsFromCsvFile CSV LOAD :: File Format Exception" + e.toString());
                                   
                } finally {  
                           if (br != null) {  
                                try {  
                                    br.close();  
                                } catch (IOException e) {  
                                    e.printStackTrace();  
                                }  
                        }  //if
                }// finally

            
        }//loadOperationsFromCsvFile
        
        public void loadLocationsMappingFromCSV(String _fileLocation){
            //String csvFileToRead        = _file;  
            //BufferedReader br           = null;  
            String line                 = "";  
            String splitBy              = ",";  
            // reset
            locationMappingArrayList 			= new ArrayList();   			
            EHardware hardwareObjectWorker    	= new EHardware();
            
            try {  
              
	                        br = new BufferedReader(new FileReader(_fileLocation));  
	                        
	                        while ((line = br.readLine()) != null) {  
	                          
	                            // split on comma(',')  
	                            String[] locationMappingArray = line.split(splitBy);  
	                          
	                            // create hardware object to store values  
	                            hardwareObjectWorker = new EHardware();  
	                            /*LOCATION CODE,
	                            LCOATION ADDRESS,
	                            FULL LOCATION,
	                            LOCATION SHORT NAME,
	                            DATA CENTER NAME
	                            */
	                            // add values from csv to interface worker object  
	                            hardwareObjectWorker.setLocationCode(locationMappingArray[0].trim()); // location code 
	                            hardwareObjectWorker.setLocationAddress(locationMappingArray[1].trim());
	                            hardwareObjectWorker.setLocation(locationMappingArray[2].trim()); // full location  
	                            hardwareObjectWorker.setLocationDataCenterCode(locationMappingArray[3].trim()); // location short name ; Data center short name
	                            
	                            hardwareObjectWorker.setLocationDataCenterName(locationMappingArray[4].trim()); // Data center  name
	                            // adding harware object to the array list  
	                            locationMappingArrayList.add(hardwareObjectWorker);  
	                            //log4j.debug("loadLocationsMappingFromCSV " +  hardwareObjectWorker.toString());  
	                        
	                        }// while
                          
	                        log4j.debug("****** loadLocationsMappingFromCSV - Location Mapping CSV LOAD  OK locationMappingArrayList.LENGTH = " + locationMappingArrayList.size()); 
                
                 } catch (FileNotFoundException fnfe) {  
                     JOptionPane.showMessageDialog(  null,
                                                     "EORGANISM :: loadLocationsMappingFromCSV CSV LOAD :: FileNotFoundException",
                                                     "EORGANISM :: loadLocationsMappingFromCSV CSV LOAD :: FileNotFoundException",
                                                     JOptionPane.ERROR_MESSAGE);
                     			log4j.error("EORGANISM :: loadLocationsMappingFromCSV CSV LOAD :: FileNotFoundException" + fnfe.toString());
                     			log4j.debug("EORGANISM :: loadLocationsMappingFromCSV CSV LOAD :: FileNotFoundException" + fnfe.toString());
                     			
                                fnfe.printStackTrace();  
                 
                 } catch (IOException ioe) {  
                	 JOptionPane.showMessageDialog(  null,
                                                    "EORGANISM :: loadLocationsMappingFromCSV CSV LOAD :: IOException",
                                                    "EORGANISM :: loadLocationsMappingFromCSV CSV LOAD :: IOException",
                                                    JOptionPane.ERROR_MESSAGE);
                    
				                    log4j.error("EORGANISM :: loadLocationsMappingFromCSV CSV LOAD :: IOException" + ioe.toString());
				         			log4j.debug("EORGANISM :: loadLocationsMappingFromCSV CSV LOAD :: IOException" + ioe.toString());
				         		
                                ioe.printStackTrace();  
                
                 } catch (Exception e) {
 
                       JOptionPane.showMessageDialog(  null,
                                                       "EORGANISM :: loadLocationsMappingFromCSV CSV LOAD :: File Format Exception",
                                                       "EORGANISM :: loadLocationsMappingFromCSV CSV LOAD :: File Format Exception",
                                                       JOptionPane.ERROR_MESSAGE);
                       
			                       log4j.error("EORGANISM :: loadLocationsMappingFromCSV CSV LOAD :: Exception" + e.toString());
				         		   log4j.debug("EORGANISM :: loadLocationsMappingFromCSV CSV LOAD :: Exception" + e.toString());
				         		   
                                   e.printStackTrace();  
                } finally {  
                           if (br != null) {  
                                try {  
                                    br.close();  
                                } catch (IOException e) {  
                                    e.printStackTrace();  
                                }  
                        }  //if
                }// finally

            
        }//loadLocationsMappingFromCSV
        
      
        
        public void loadWSDLFile(String _fileLocation){
            //String csvFileToRead        = _file;  
            //BufferedReader br           = null;  
            String line                 = "";  
            String splitBy              = ",";  
             
            EService serviceObjectWorker    = new EService();  
            
            serviceArrayList = new ArrayList();
            
            try {  
              
                        br = new BufferedReader(new FileReader(_fileLocation));  
                        
                        while ((line = br.readLine()) != null) {  
                          
                            // split on comma(',')  
                            String[] servicesArray = line.split(splitBy);  
                          
                            // create service object to store values  
                            serviceObjectWorker = new EService();  
                          
                            // add values from csv to service worker object  
                            
                            //0 Type	| 1 SYSTEM |	2 SYSYEM ID	|  3 APPLICATION COMPONENT | 	4 APP ID |5 COMPONENT|  6 COMP ID	| 7 Host	| 8 IP	| 9 SERVICE	| 10 WSDL |11 	Load Location	| 12 Type	| 13 Subtype	| 14 Environment


                            serviceObjectWorker.setProbeName(servicesArray[0].trim()); // serviceType
                            
                            serviceObjectWorker.setSystem(servicesArray[1].trim()); // SYSTEM 
                            serviceObjectWorker.setSystemID(servicesArray[2].trim()); // SYSTEM ID 
                            
                            serviceObjectWorker.setSystemID(prependZeroToString(serviceObjectWorker.getSystemID(),"0",4));
                            
                            serviceObjectWorker.setApplication(servicesArray[3].trim()); // APP 
                            serviceObjectWorker.setApplicationID(servicesArray[4].trim()); // APP ID 
                            
                            serviceObjectWorker.setApplicationID(prependZeroToString(serviceObjectWorker.getApplicationID(),"0",4));
                            serviceObjectWorker.setAppID(prependZeroToString(serviceObjectWorker.getApplicationID(),"0",4));
                             
                            
                            serviceObjectWorker.setComponent(servicesArray[5].trim()); // COMP  
                            serviceObjectWorker.setComponentID(servicesArray[6].trim()); // COMP ID 
                           
                            serviceObjectWorker.setServiceHost(servicesArray[7].trim());  // host
                            serviceObjectWorker.setServiceIP(servicesArray[8].trim());//ip
                            serviceObjectWorker.setServiceName(servicesArray[9].trim());  // service_name
                            serviceObjectWorker.setDescriptorFile(servicesArray[10].trim());  //wsdl
                            serviceObjectWorker.setServiceLocation(servicesArray[11].trim());  // service location - USED TO LOAD WSDL URL
                            serviceObjectWorker.setServiceType(servicesArray[12].trim()); // WS, JMS,CTRL-M, TUXEDO  - type
                            serviceObjectWorker.setServiceSubtype(servicesArray[13].trim()); // WLS, WS, Apchae WS, AWS, Cloud WS
                            serviceObjectWorker.setEnvironment(servicesArray[14].trim()); // environment 
                             
                            // adding service objects to a list  
                            serviceArrayList.add(serviceObjectWorker);  
                            serviceObjectWorker.printToString();
                           
                            log4j.debug("****** loadWSDLFile :: serviceObjectWorker.getStringState() =  " +serviceObjectWorker.getStringState() );  
                        
                    }  // while
                          // print values stored in carList  
                           //printServiceList(serviceArrayList);  
                
                        log4j.debug("******  loadWSDLFile - load OK " ); 
                
                 } catch (FileNotFoundException fnfe) {  
                       			//JOptionPane.showMessageDialog(  null,"EORGANISM :: Open CSV LOAD :: FileNotFoundException","EORGANISM :: Open CSV LOAD :: FileNotFoundException", JOptionPane.ERROR_MESSAGE);
                	 
                	 			log4j.error("EORGANISM :: loadWSDLFile :: FileNotFoundException" +  fnfe.toString()); 
                	 			log4j.debug("EORGANISM :: loadWSDLFile :: FileNotFoundException" +  fnfe.toString()); 
                	 			
                	 			fnfe.printStackTrace();  
                	 			
                 } catch (IOException ioe) {  
                       //JOptionPane.showMessageDialog(  null,"EORGANISM :: Open CSV LOAD :: IOException","EORGANISM :: Open CSV LOAD :: IOException",JOptionPane.ERROR_MESSAGE);
		                	 log4j.error("EORGANISM :: loadWSDLFile :: IOException" +  ioe.toString()); 
		     	 			 log4j.debug("EORGANISM ::loadWSDLFile :: IOException" +  ioe.toString()); 
		     	 			 ioe.printStackTrace();  
                } catch (Exception e) {  
                       //JOptionPane.showMessageDialog(  null,"EORGANISM :: Open CSV LOAD :: File Format Exception","EORGANISM :: Open CSV LOAD :: Exception",JOptionPane.ERROR_MESSAGE);
			                	 log4j.error("EORGANISM :: loadWSDLFile :: Exception" +  e.toString()); 
			     	 			 log4j.debug("EORGANISM :: loadWSDLFile :: Exception" +  e.toString()); 
			     	 			 
                                 e.printStackTrace();  
                } finally {  
                           if (br != null) {  
                                try {  
                                    br.close();  
                                } catch (IOException e) {  
                                    e.printStackTrace();  
                                }  
                        }  //if
                }// finally

            
        }//loadWSDLFile
        
        public void loadExtendedWSDLFile(String _fileLocation){
            String line                 = "";  
            String splitBy              = ",";  
             
            EService serviceObjectWorker    = new EService();  
            
            serviceArrayList = new ArrayList();
            
            try {  
              
                        br = new BufferedReader(new FileReader(_fileLocation));  
                        
                        while ((line = br.readLine()) != null) {  
                          
                            // split on comma(',')  
                            String[] servicesArray = line.split(splitBy);  
                          
                            // create service object to store values  
                            serviceObjectWorker = new EService();  
                          
                            serviceObjectWorker.setProbeName(servicesArray[0].trim()); // serviceType
                            
                            serviceObjectWorker.setSystem(servicesArray[1].trim()); // SYSTEM 
                            serviceObjectWorker.setSystemID(servicesArray[2].trim()); // SYSTEM ID 
                            serviceObjectWorker.setSystemID(prependZeroToString(serviceObjectWorker.getSystemID(),"0",4));
                            
                            
                            serviceObjectWorker.setApplication(servicesArray[3].trim()); // APP 
                            serviceObjectWorker.setAppID(servicesArray[4].trim()); // APP ID
                            
                            serviceObjectWorker.setAppID(prependZeroToString(serviceObjectWorker.getAppID(),"0",4));
                            serviceObjectWorker.setApplicationID(prependZeroToString(serviceObjectWorker.getAppID(),"0",4));
                               
                            
                            serviceObjectWorker.setComponent(servicesArray[5].trim()); // COMP  
                            serviceObjectWorker.setCompID(servicesArray[6].trim()); // COMP ID 
                           
                            serviceObjectWorker.setServiceHost(servicesArray[7].trim());  // host
                            serviceObjectWorker.setServiceIP(servicesArray[8].trim());//ip
                            serviceObjectWorker.setServiceName(servicesArray[9].trim());  // service_name
                            serviceObjectWorker.setDescriptorFile(servicesArray[10].trim());  //wsdl
                            serviceObjectWorker.setServiceLocation(servicesArray[11].trim());  // service location - USED TO LOAD WSDL URL
                            serviceObjectWorker.setServiceType(servicesArray[12].trim()); // WS, JMS,CTRL-M, TUXEDO  - type
                            serviceObjectWorker.setServiceSubtype(servicesArray[13].trim()); // WLS, WS, Apchae WS, AWS, Cloud WS
                            serviceObjectWorker.setEnvironment(servicesArray[14].trim()); // environment 
                            
                            
                            // adding service objects to a list  
                            serviceArrayList.add(serviceObjectWorker);  
                            serviceObjectWorker.printToString();
                           
                            log4j.debug("****** loadWSDLFile - load OK " );  
                        
                    }  // while
                          // print values stored in carList  
                           //printServiceList(serviceArrayList);  
                
                        log4j.debug("****** loadWSDLFile - load OK " ); 
                
                 } catch (FileNotFoundException fnfe) {  
                       			//JOptionPane.showMessageDialog(  null,"EORGANISM :: Open CSV LOAD :: FileNotFoundException","EORGANISM :: Open CSV LOAD :: FileNotFoundException", JOptionPane.ERROR_MESSAGE);
                	 
                	 			log4j.error("EORGANISM :: loadWSDLFile :: FileNotFoundException" +  fnfe.toString()); 
                	 			log4j.debug("EORGANISM :: loadWSDLFile :: FileNotFoundException" +  fnfe.toString()); 
                	 			
                	 			fnfe.printStackTrace();  
                	 			
                 } catch (IOException ioe) {  
                       //JOptionPane.showMessageDialog(  null,"EORGANISM :: Open CSV LOAD :: IOException","EORGANISM :: Open CSV LOAD :: IOException",JOptionPane.ERROR_MESSAGE);
		                	 log4j.error("EORGANISM :: loadWSDLFile :: IOException" +  ioe.toString()); 
		     	 			 log4j.debug("EORGANISM ::loadWSDLFile :: IOException" +  ioe.toString()); 
		     	 			 ioe.printStackTrace();  
                } catch (Exception e) {  
                       //JOptionPane.showMessageDialog(  null,"EORGANISM :: Open CSV LOAD :: File Format Exception","EORGANISM :: Open CSV LOAD :: Exception",JOptionPane.ERROR_MESSAGE);
			                	 log4j.error("EORGANISM :: loadWSDLFile :: Exception" +  e.toString()); 
			     	 			 log4j.debug("EORGANISM :: loadWSDLFile :: Exception" +  e.toString()); 
			     	 			 
                                 e.printStackTrace();  
                } finally {  
                           if (br != null) {  
                                try {  
                                    br.close();  
                                } catch (IOException e) {  
                                    e.printStackTrace();  
                                }  
                        }  //if
                }// finally

            
        }//loadExtendedWSDLFile
        
        public void loadMetadataTableFromCSV(String _fileLocation){
            //String csvFileToRead        = _file;  
            //BufferedReader br           = null;  
            String line                 = "";  
            String splitBy              = ",";  
             
            ETable tableWorker    = new ETable();  
            
            try {  
              
                        br = new BufferedReader(new FileReader(_fileLocation));  
                        
                        while ((line = br.readLine()) != null) {  
                          
                            // split on comma(',')  
                            String[] tableArray = line.split(splitBy);  
                          
                            // create service object to store values  
                            tableWorker = new ETable();  
                          
                            // OWNER,TABLE_NAME,STATUS,Database,Component,Application,Environment
                            // add values from csv to service worker object  
                            tableWorker.setOwner(tableArray[0].trim());    // owner 
                            tableWorker.setName(tableArray[1].trim());     // table name
                            tableWorker.setStatus(tableArray[2].trim());   // status
                            tableWorker.setDatabase(tableArray[3].trim());   // database
                            tableWorker.setComponent(tableArray[4].trim());   // component
                            tableWorker.setApplication(tableArray[5].trim());   // application
                            tableWorker.setEnvironment(tableArray[6].trim());   // environment
                            
                            // adding service objects to a list  
                            tableArrayList.add(tableWorker);  
                          
                        }  // while
                          
                        log4j.debug("****** loadMetadataTableFromCSV -  load OK for file =  " + _fileLocation );    
                
                
                 } catch (FileNotFoundException fnfe) {  
                        	//JOptionPane.showMessageDialog(  null, "EORGANISM :: loadMetadataTableFromCSV :: FileNotFoundException", "EORGANISM :: loadMetadataTableFromCSVD :: FileNotFoundException", JOptionPane.ERROR_MESSAGE);
                 	 		
                	 		log4j.error("EORGANISM :: loadMetadataTableFromCSV :: FileNotFoundException" +  fnfe.toString()); 
                	 		log4j.debug("EORGANISM :: loadMetadataTableFromCSV :: FileNotFoundException" +  fnfe.toString()); 
                	 		
                	 		fnfe.printStackTrace();  
                	 		
                 } catch (IOException ioe) {  
                	 		//JOptionPane.showMessageDialog(  null, "EORGANISM :: loadMetadataTableFromCSV :: IOException",   "EORGANISM :: loadMetadataTableFromCSV :: IOException", JOptionPane.ERROR_MESSAGE);
		                	 log4j.error("EORGANISM :: loadMetadataTableFromCSV :: IOException" +  ioe.toString()); 
		         	 		 log4j.debug("EORGANISM :: loadMetadataTableFromCSV :: IOException" +  ioe.toString()); 
		                	 ioe.printStackTrace();  
		                	 
                } catch (Exception e) {  
                       //JOptionPane.showMessageDialog(  null, "EORGANISM :: loadMetadataTableFromCSV :: File Format Exception", "EORGANISM :: loadMetadataTableFromCSV :: File Format Exception", JOptionPane.ERROR_MESSAGE);
		                	log4j.error("EORGANISM :: loadMetadataTableFromCSV :: Exception" +  e.toString()); 
		        	 		log4j.debug("EORGANISM :: loadMetadataTableFromCSV :: Exception" +  e.toString()); 
		                	
		                	e.printStackTrace();  
                } finally {  
                           if (br != null) {  
                                try {  
                                    br.close();  
                                } catch (IOException e) {  
                                    e.printStackTrace();  
                                }  
                        }  //if
                }// finally

            
        }//loadMetadataTableFromCSV(commandFileName);
        
        public void printServiceList(ArrayList _serviceListToPrint){
            EService serviceObjectWorker;  
            
            for (int i = 0; i < _serviceListToPrint.size(); i++) {  
                  serviceObjectWorker    = new EService();
                  serviceObjectWorker = (EService)_serviceListToPrint.get(i);
                   serviceObjectWorker.printToString();
            }  // for object in ArrayList
         
        }//printServiceList
        
        
        // save eperson table
        
        public void loadMetadataColumnFromCSV(String _fileLocation){
		    //String csvFileToRead        = _file;  
		    //BufferedReader br           = null;  
		    String line                 = "";  
		    String splitBy              = ",";  
		     
		    EColumn columnWorker    = new EColumn();  
		    
		    try {  
		      
		                br = new BufferedReader(new FileReader(_fileLocation));  
		                
		                while ((line = br.readLine()) != null) {  
		                  
		                    // split on comma(',')  
		                    String[] columnArray = line.split(splitBy);  
		                  
		                    // create service object to store values  
		                    columnWorker = new EColumn();  
		                    
		                    //OWNER,TABLE_NAME,COLUMN_NAME,DATA_TYPE,Database,Component,Application,Environment
		                  
		                    // add values from csv to service worker object  
		                    columnWorker.setOwner(columnArray[0]);      // owner 
		                    columnWorker.setTableName(columnArray[1]);  //table name
		                    columnWorker.setName(columnArray[2]);       // column name
		                    columnWorker.setDataType(columnArray[3]);   // data type
		                    
		                    columnWorker.setDatabase(columnArray[4]);      // database
		                    columnWorker.setComponent(columnArray[5]);     // component
		                    columnWorker.setApplication(columnArray[6]);   // application
		                    columnWorker.setEnvironment(columnArray[7]);   // environment
		                      
		                    // adding service objects to a list  
		                    columnArrayList.add(columnWorker);  
		                  
		                }  // while
		                  
		                log4j.debug("****** loadMetadataColumnFromCSV -  load OK for file =  " + _fileLocation );    
		        
		        
		         } catch (FileNotFoundException fnfe) {  
		               //JOptionPane.showMessageDialog(  null, "EORGANISM :: loadMetadataColumnFromCSV :: FileNotFoundException", "EORGANISM :: loadMetadataColumnFromCSV :: FileNotFoundException",JOptionPane.ERROR_MESSAGE);
		        		log4j.error("EORGANISM :: loadMetadataColumnFromCSV :: FileNotFoundException" +  fnfe.toString()); 
            	 		log4j.debug("EORGANISM :: loadMetadataColumnFromCSV :: FileNotFoundException" +  fnfe.toString());     
		        	 
            	 		fnfe.printStackTrace();  
            	 		
		         } catch (IOException ioe) {  
		               //JOptionPane.showMessageDialog(  null, "EORGANISM :: loadMetadataColumnFromCSV :: IOException","EORGANISM :: loadMetadataColumnFromCSV :: IOException",JOptionPane.ERROR_MESSAGE);
		        		log4j.error("EORGANISM :: loadMetadataColumnFromCSV :: IOException" +  ioe.toString()); 
            	 		log4j.debug("EORGANISM :: loadMetadataColumnFromCSV :: IOException" +  ioe.toString());     
		        	  
            	 		ioe.printStackTrace();  
		        } catch (Exception e) {  
		               //JOptionPane.showMessageDialog(  null, "EORGANISM :: loadMetadataColumnFromCSV :: File Format Exception","EORGANISM :: loadMetadataColumnFromCSV :: File Format Exception",JOptionPane.ERROR_MESSAGE);
		        		log4j.error("EORGANISM :: loadMetadataColumnFromCSV :: Exception" +  e.toString()); 
		        		log4j.debug("EORGANISM :: loadMetadataColumnFromCSV :: Exception" +  e.toString());  
        	 		
		        		e.printStackTrace();  
		        } finally {  
		                   if (br != null) {  
		                        try {  
		                            br.close();  
		                        } catch (IOException e) {  
		                            e.printStackTrace();  
		                        }  
		                }  //if
		        }// finally
		
		    
		}//loadMetadataColumnFromCSV(commandFileName);
        
		public void savePerson(){
			
            PreparedStatement insertPreparedStatementPersonTable =null;
            EPerson personObjectWorker;
             
            databaseInitializer();
            checkConnection();
            
            int personId		=0;
            int eid  			=0;	
             
            //java.sql.Date today = new java.sql.Date(System.currentTimeMillis());
            try{
                      
            	insertPreparedStatementPersonTable = connection.prepareStatement("INSERT INTO EPERSON_TABLE(EPERSON_ID,EPERSON_NAME,EORGANISM_ID,CREATED_DATE,CREATED_APPLICATION,UPDATED_DATE,LAST_UPDATED_APPLICATION,LAST_UPDATED_USER) VALUES (?,?,?,?,?,?,?,?)");
            	
            	      
                    for (int i = 1; i < personArrayList.size(); i++) {  
                    	
                    		personObjectWorker    = new EPerson();
                    		personObjectWorker 	= (EPerson)personArrayList.get(i);
                    		
                    		personId 			= getSequenceNextValue("EPERSON_TABLE","EPERSON_ID");
                        	eid 				= getSequenceNextValue("EORGANISM_OBJECTID_MAPPING","EORGANISM_OBJECTID");    
                          
                                         
                    		insertPreparedStatementPersonTable.setInt(1, personId);								//id
                    		insertPreparedStatementPersonTable.setString(2, personObjectWorker.getName());     	// name
                    		insertPreparedStatementPersonTable.setInt(3, eid);				// eorganism_id
                    	    insertPreparedStatementPersonTable.setDate(4, today); 			// created date
                    		insertPreparedStatementPersonTable.setString(5, ConfigFrame.LOAD_APP); // created application
                   		    insertPreparedStatementPersonTable.setDate(6, today);           // last updated date
                   		    insertPreparedStatementPersonTable.setString(7, ConfigFrame.LOAD_APP); // last updated application
                   		    insertPreparedStatementPersonTable.setString(8, ConfigFrame.ADMIN_USER_NAME);  // last updated user
                   	
                   		    result = insertPreparedStatementPersonTable.executeUpdate();
                   		    
                            //log4j.debug("savePerson:: " +personObjectWorker.getName());
                            
                            // SAVE EORGANISM
                            saveEOrganismTable(eid,personObjectWorker.getName(),"EPERSON_TABLE","Person", personId);
                         
                         
                    }  // i for object in ArrayList
                
                    //CLOSE
                    insertPreparedStatementPersonTable.close(); 
                    
                    connection.close();
                    
                    log4j.debug("Operations savePerson:: OK");      
                    //JOptionPane.showMessageDialog( null, "EORGANISM :: savePerson OK");
                
                } catch(SQLException sqle){
                    //JOptionPane.showMessageDialog( null,"SQLException  "    + sqle.toString()+"\n");
                	log4j.debug("EORGANISM ::savePerson :: SQLException: " + sqle.toString());
                	log4j.error("EORGANISM ::savePerson :: SQLException: " + sqle.toString());
                	
                } catch(Exception e){
                    //JOptionPane.showMessageDialog( null, "Exception  " +e.toString()+"\n");
                	log4j.debug("EORGANISM ::savePerson :: Exception: " + e.toString());
                	log4j.error("EORGANISM ::savePerson :: Exception: " + e.toString());
                	
                }finally{
                    try{
                    	 if (insertPreparedStatementPersonTable!=null){insertPreparedStatementPersonTable.close(); }
                         if (connection!=null){connection.close(); }
                      
                    }catch(SQLException sqle2){
                    	log4j.debug("EORGANISM ::savePerson :: SQLException:finally " + sqle2.toString());
                    	log4j.error("EORGANISM ::savePerson :: SQLException:finally " + sqle2.toString());
                    }//
                
                }//finally
            
        }// end savePerson
        
        
        public void saveBusinessUnit(){
        	
            PreparedStatement insertPreparedStatementBusinessUnitTable =null;
            
            EBusinessUnit businessUnitWorker;
             
            databaseInitializer();
            checkConnection();
            
            int businessUnitId		=0;
            int eid  				=0;	
             
            //java.sql.Date today = new java.sql.Date(System.currentTimeMillis());
            
            try{
                      
            	insertPreparedStatementBusinessUnitTable = connection.prepareStatement("INSERT INTO EBUSINESS_UNIT(EBUSINESS_UNIT_ID,EBUSINESS_UNIT_NAME,EORGANISM_ID,CREATED_DATE,CREATED_APPLICATION,LAST_UPDATED_DATE,LAST_UPDATED_APP,LAST_UPDATED_USER) VALUES (?,?,?,?,?,?,?,?)");
            	
                    
                    for (int i = 1; i < businessUnitArrayList.size(); i++) {  
                    		businessUnitWorker    = new EBusinessUnit();
                    		businessUnitWorker 	  = (EBusinessUnit)businessUnitArrayList.get(i);
                    		
                    		businessUnitId 		= getSequenceNextValue("EBUSINESS_UNIT","EBUSINESS_UNIT_ID");
                    	  	 eid 				=  getSequenceNextValue(ConfigFrame.EORGANISM_OBJECTID_MAPPING,ConfigFrame.EORGANISM_OBJECTID);
                              
                                         
                    		insertPreparedStatementBusinessUnitTable.setInt(1, businessUnitId);							//id
                    		insertPreparedStatementBusinessUnitTable.setString(2, businessUnitWorker.getName());     	// name
                    		insertPreparedStatementBusinessUnitTable.setInt(3, eid);				// eorganism_id
                    		insertPreparedStatementBusinessUnitTable.setDate(4, today); 			// created date
                    		insertPreparedStatementBusinessUnitTable.setString(5, ConfigFrame.LOAD_APP); // created application
                    		insertPreparedStatementBusinessUnitTable.setDate(6, today);           // last updated date
                    		insertPreparedStatementBusinessUnitTable.setString(7, ConfigFrame.LOAD_APP); // last updated application
                    		insertPreparedStatementBusinessUnitTable.setString(8, ConfigFrame.ADMIN_USER_NAME);  // last updated user
                   	
                   		    result = insertPreparedStatementBusinessUnitTable.executeUpdate();
                            
                   		    log4j.debug("saveBusinessUnit: " + businessUnitWorker.getName());
                            
                            
                            // SAVE EORGANISM
                            saveEOrganismTable(eid,businessUnitWorker.getName(),"EBUSINESS_UNIT","BusinessUnit", businessUnitId);
                         
                         
                    }  // i for object in ArrayList
                
                    //CLOSE
                    insertPreparedStatementBusinessUnitTable.close(); 
                    
                    connection.close();
                    
                    log4j.debug("EORGANISM saveBusinessUnit:: OK");      
                    //JOptionPane.showMessageDialog( null, "EORGANISM :: saveBusinessUnit OK");
                
                } catch(SQLException sqle){
                    //JOptionPane.showMessageDialog( null,"SQLException  "    + sqle.toString()+"\n");
                	log4j.error("EORGANISM ::saveBusinessUnit :: SQLException: " + sqle.toString());
                	log4j.debug("EORGANISM ::saveBusinessUnit :: SQLException: " + sqle.toString());
                	
                } catch(Exception e){
                    //JOptionPane.showMessageDialog( null, "Exception  " +e.toString()+"\n");
                	log4j.error("EORGANISM ::saveBusinessUnit :: Exception: " + e.toString());
                	log4j.debug("EORGANISM ::saveBusinessUnit :: Exception: " + e.toString());
                	
                }finally{
                    try{
                    	 if (insertPreparedStatementBusinessUnitTable!=null){insertPreparedStatementBusinessUnitTable.close(); }
                         if (connection!=null){connection.close(); }
                      
                    }catch(SQLException sqle2){
                    		log4j.error("EORGANISM ::saveBusinessUnit :: SQLException:finally " + sqle2.toString());
                    		log4j.debug("EORGANISM ::saveBusinessUnit :: SQLException:finally " + sqle2.toString());
                    }//
                
                }//finally
             
            
        }// end saveBusinessUnit
        
        public void saveBusinessService(){
        	
            PreparedStatement insertPreparedStatementBusinessServiceTable =null;
            
            EBusinessService businessServiceWorker;
             
            databaseInitializer();
            checkConnection();
            
            int businessServiceId		=0;
            int eid  					=0;	
             
            //java.sql.Date today 		= new java.sql.Date(System.currentTimeMillis());
            try{
                      
            	insertPreparedStatementBusinessServiceTable = connection.prepareStatement("INSERT INTO EBUSINESS_SERVICE(EBUSINESS_SERVICE_ID,EBUSINESS_SERVICE_NAME,EORGANISM_ID,CREATED_DATE,CREATED_APPLICATION,LAST_UPDATED_DATE,LAST_UPDATED_APP,LAST_UPDATED_USER,EBUSINESS_UNIT_NAME) VALUES (?,?,?,?,?,?,?,?,?)");
            	
                   
                    for (int i = 1; i < businessServiceArrayList.size(); i++) {  
                    		businessServiceWorker    = new EBusinessService();
                    		businessServiceWorker 	  = (EBusinessService)businessServiceArrayList.get(i);
                    		
                    		businessServiceId 	= getSequenceNextValue("EBUSINESS_SERVICE","EBUSINESS_SERVICE_ID");                    	
                    	  	eid 				=  getSequenceNextValue(ConfigFrame.EORGANISM_OBJECTID_MAPPING,ConfigFrame.EORGANISM_OBJECTID);
                                         
                    		insertPreparedStatementBusinessServiceTable.setInt(1, businessServiceId);						//id
                    		insertPreparedStatementBusinessServiceTable.setString(2, businessServiceWorker.getName());     	// name
                    		
                    		insertPreparedStatementBusinessServiceTable.setInt(3, eid);				// eorganism_id
                    		insertPreparedStatementBusinessServiceTable.setDate(4, today); 			// created date
                    		insertPreparedStatementBusinessServiceTable.setString(5, ConfigFrame.LOAD_APP); // created application
                    		insertPreparedStatementBusinessServiceTable.setDate(6, today);           // last updated date
                    		insertPreparedStatementBusinessServiceTable.setString(7, ConfigFrame.LOAD_APP); // last updated application
                    		insertPreparedStatementBusinessServiceTable.setString(8, "ALEX ADMIN");  // last updated user
                    		insertPreparedStatementBusinessServiceTable.setString(9, businessServiceWorker.geBusinessUnit());  // business unit name
                           	
                    		
                   		    result = insertPreparedStatementBusinessServiceTable.executeUpdate();
                   		    log4j.debug("saveBusinessService:: " +businessServiceWorker.getName());
                            
                            
                            // SAVE EORGANISM
                            saveEOrganismTable(eid,businessServiceWorker.getName(),"EBUSINESS_SERVICE","BusinessService", businessServiceId);
                         
                         
                    }  // i for object in ArrayList
                
                    //CLOSE
                    insertPreparedStatementBusinessServiceTable.close(); 
                    
                    connection.close();
                    
                    log4j.debug("EORGANISM saveBusinessService:: OK");      
                    //JOptionPane.showMessageDialog( null, "EORGANISM :: saveBusinessService OK");
                
                } catch(SQLException sqle){
                    //JOptionPane.showMessageDialog( null,"SQLException  "    + sqle.toString()+"\n");
                	log4j.error("EORGANISM ::saveBusinessService :: SQLException: " + sqle.toString());
                	log4j.debug("EORGANISM ::saveBusinessService :: SQLException: " + sqle.toString());
                    
                } catch(Exception e){
                    //JOptionPane.showMessageDialog( null, "Exception  " +e.toString()+"\n");
                	log4j.error("EORGANISM ::saveBusinessService :: Exception: " + e.toString());
                	log4j.debug("EORGANISM ::saveBusinessService :: Exception: " + e.toString());
            
                }finally{
                    try{
                    	 if (insertPreparedStatementBusinessServiceTable!=null){insertPreparedStatementBusinessServiceTable.close(); }
                         if (connection!=null){connection.close(); }
                      
                    }catch(SQLException sqle2){
                    	log4j.error("EORGANISM ::saveBusinessService :: SQLException:finally " + sqle2.toString());
                    	log4j.debug("EORGANISM ::saveBusinessService :: SQLException:finally " + sqle2.toString());
                    }//
                
                }//finally
             
            
        }// end saveBusinessService
        
        public void saveLogging(String _message,String _process, String _object){
        	
            PreparedStatement insertPreparedStatementLogging =null;
             
            databaseInitializer();
            checkConnection();
             
              try{
                      
            	  			insertPreparedStatementLogging = connection.prepareStatement("INSERT INTO TABLE_LOGGING(EVENT_ID,PROCESS_NAME,OBJECT_NAME,MSG,CREATED_TIMESTAMP) VALUES (?,?,?,?,?)");
                        		
                    		eid 							= getSequenceNextValue("TABLE_LOGGING","EVENT_ID");                    	
                                          
                    	  	insertPreparedStatementLogging.setInt(1, eid);				// event id
                    	  	insertPreparedStatementLogging.setString(2, _process);     	// _process
                    	  	insertPreparedStatementLogging.setString(3, _object);     	// _object	
                    	  	insertPreparedStatementLogging.setString(4, _message);		// _message
                    	  	insertPreparedStatementLogging.setTimestamp(5, new java.sql.Timestamp(System.currentTimeMillis()));//timestamp 
                    	  	
                   		    result = insertPreparedStatementLogging.executeUpdate();
                   		    
                   		    log4j.debug("EORGANISM :: saveLogging:: " +_process+":"+_object+"::message"+ _message);
                             
                            // SAVE EORGANISM
                            saveEOrganismTable(eid,_object,_process,"LoggingService", eid);
                     
		                    //CLOSE
                            insertPreparedStatementLogging.close(); 
                    
		                    connection.close();
		                    
		                    log4j.debug("EORGANISM saveLogging:: OK");      
                 
                } catch(SQLException sqle){
                	log4j.error("EORGANISM ::saveLogging :: SQLException: " + sqle.toString());
                	log4j.debug("EORGANISM ::saveLogging :: SQLException: " + sqle.toString());
                    
                } catch(Exception e){
                 	log4j.error("EORGANISM ::saveLogging :: Exception: " + e.toString());
                	log4j.debug("EORGANISM ::saveLogging :: Exception: " + e.toString());
            
                }finally{
                    try{
                    	 if (insertPreparedStatementLogging!=null){insertPreparedStatementLogging.close(); }
                         if (connection!=null){connection.close(); }
                      
                    }catch(SQLException sqle2){
                    	log4j.error("EORGANISM ::saveLogging :: SQLException:finally " + sqle2.toString());
                    	log4j.debug("EORGANISM ::saveLogging :: SQLException:finally " + sqle2.toString());
                    }//
                
                }//finally
            
        }// end saveLogging
        
        public void saveOperationsToDB(){
        	
            PreparedStatement insertPreparedStatementOperations =null;
            
            EInterface  operationObjectWorker;
             
            databaseInitializer();
            checkConnection();
            
            int	eid			=0;
            int interfaceId	=0;	
                			
              
            //java.sql.Date today 		= new java.sql.Date(System.currentTimeMillis());
            try{
             	
            		// insert EINTERFACE
            		insertPreparedStatementOperations         	= connection.prepareStatement("INSERT INTO EINTERFACE(EINTERFACE_ID,EINTERFACE_NAME,EINTERFACE_DESCRIPTION,EAPPLICATION_ID_FK,EAPPLICATION_NAME_FK,ECOMPONENT_NAME_FK,ESERVICE_NAME_FK,EINTERFACE_TYPE,EINTERFACE_INFORMATION,EORGANISM_ID) VALUES (?,?,?,?,?,?,?,?,?,?)");
                 
                    for (int i = 1; i < operationsArrayList.size(); i++) {  
                    	     operationObjectWorker    		= new EInterface();
                    	     operationObjectWorker 	   		= (EInterface)operationsArrayList.get(i);
                                           
                        	 interfaceId = getSequenceNextValue(ConfigFrame.EINTERFACE_TABLE,ConfigFrame.EINTERFACE_ID);
                        	 eid 		=  getSequenceNextValue(ConfigFrame.EORGANISM_OBJECTID_MAPPING,ConfigFrame.EORGANISM_OBJECTID);
                        		 
                             // insert EINTERFACE
                             //EINTERFACE_ID,EINTERFACE_NAME,EINTERFACE_DESCRIPTION,EAPPLICATION_ID_FK,EAPPLICATION_NAME_FK,ECOMPONENT_NAME_FK,ESERVICE_NAME_FK,EINTERFACE_TYPE,EINTERFACE_INFORMATION
                             insertPreparedStatementOperations.setInt(1, interfaceId);
                             insertPreparedStatementOperations.setString(2, operationObjectWorker.getInterfaceName());// interface name
                             insertPreparedStatementOperations.setString(3, operationObjectWorker.getApplication()+"."+operationObjectWorker.getComponent()+"."+operationObjectWorker.getService()+"."+operationObjectWorker.getInterfaceName());// description
                             insertPreparedStatementOperations.setInt(4, 1);// app_id
                             insertPreparedStatementOperations.setString(5, operationObjectWorker.getApplication());// app name
                             insertPreparedStatementOperations.setString(6, operationObjectWorker.getComponent());//comp
                             insertPreparedStatementOperations.setString(7, operationObjectWorker.getService());// service
                             insertPreparedStatementOperations.setString(8, operationObjectWorker.getInterfaceType());// service
                             insertPreparedStatementOperations.setString(9, operationObjectWorker.getInformation());// interface information
                             insertPreparedStatementOperations.setInt(10, eid);// eid
                                 
                             result = insertPreparedStatementOperations.executeUpdate();
                             
                             // SAVE EORGANISM
                             saveEOrganismTable(eid,operationObjectWorker.getInterfaceName(),ConfigFrame.EINTERFACE_TABLE,ConfigFrame.EINTERFACE, interfaceId);
                             //log4j.debug("Operations saveOperationsToDB:: OK - " +  operationObjectWorker.getInterfaceName());    
                         
                    }  // i for object in ArrayList
                
                    //CLOSE
                    insertPreparedStatementOperations.close(); 
                    
                    connection.close();
                    
                    log4j.debug("EORGANISM saveOperationsToDB:: SAVE OPERATIONS TO DB OK");      
                    //JOptionPane.showMessageDialog( null, "EORGANISM :: saveBusinessService OK");
                
                } catch(SQLException sqle){
                    //JOptionPane.showMessageDialog( null,"SQLException  "    + sqle.toString()+"\n");
                	log4j.error("EORGANISM ::saveOperationsToDB :: SQLException: " + sqle.toString());
                	log4j.debug("EORGANISM ::saveOperationsToDB :: SQLException: " + sqle.toString());
                	
                } catch(Exception e){
                    //JOptionPane.showMessageDialog( null, "Exception  " +e.toString()+"\n");
                	log4j.error("EORGANISM ::saveOperationsToDB :: Exception: " + e.toString());
                	log4j.debug("EORGANISM ::saveOperationsToDB :: Exception: " + e.toString());
                }finally{
                    try{
                    	 if (insertPreparedStatementOperations!=null){insertPreparedStatementOperations.close(); }
                         if (connection!=null){connection.close(); }
                      
                    }catch(SQLException sqle2){
                    	log4j.error("EORGANISM ::saveOperationsToDB :: SQLException:finally " + sqle2.toString());
                    	log4j.debug("EORGANISM ::saveOperationsToDB :: SQLException:finally " + sqle2.toString());
                    }//
                
                }//finally
             
            
        }// end saveOperationsToDB
          
        public void saveWsdlFileToFileSystem(){
        	
        	EService serviceObjectWorker;
        	// init
        	url 			= null;
            urlConnection 	= null;
            
            //https stuff
              
            java.util.Date 		date;
            
            FileOutputStream 	fos			  	= null;
            String 				localFileName 	= null;
            InputStream 		inputString 	= null;
            StringTokenizer 	st;
            
            String 				location1 ="";
            String 				location2 ="";
            String 				location3 ="";
            String 				fileURLPath="";
            File 				file;
            File 				filePath;
                        
            String 				locationWeb1 ="";
            String 				locationWeb2 ="";
            String 				locationWebsite ="";
            String 				locationDBPath="";
            
            
            File 				fileWeb;
            File 				filePathWeb;
            File 				fileDBPath;
            File 				fileDB;
            
            
            //fileName 			= "C:\\EOrganism_MySQL\\tempfile.xml";
            for (int i = 1; i < serviceArrayList.size(); i++) {  
            	
                  serviceObjectWorker    = new EService();
                  serviceObjectWorker 	= (EService)serviceArrayList.get(i);
            
                  // if location is not null attempt to load the WSDL of FILE from that location
                  if(serviceObjectWorker.getServiceLocation()!=null && serviceObjectWorker.getServiceLocation()!=""&& (serviceObjectWorker.getServiceLocation()).length()>4){
                      	
                	  try{
                 		  
				                		  if((serviceObjectWorker.getServiceLocation().toLowerCase()).startsWith("https")){
				                			  
							                			  	urlHttps 			= new URL(serviceObjectWorker.getServiceLocation());
							                			  	
							                			    log4j.debug("EORGANISM ::saveWsdlFileToFileSystem :: ###### Opening HTTPS connection to " + serviceObjectWorker.getServiceLocation() + "...");
							                        		
							                			    httpsURLConnection	= (HttpsURLConnection)urlHttps.openConnection();
				  		         				
				                                			inputString = urlHttps.openStream();
				                			    
							                      		  	fos=null;
							                                // Get only file name
							                      		  	
							                                fileURLPath= urlHttps.getFile();
							                                log4j.debug("HTTPS fileURLPath: " +  fileURLPath);
							                                
							                                serviceObjectWorker.setCertificateInformation(getSecurityCertificatesFromHttpsURL(httpsURLConnection));
							    		         			
							                                
				                		  } else if ((serviceObjectWorker.getServiceLocation().toLowerCase()).startsWith("http")) {                   
															 url  = new URL(serviceObjectWorker.getServiceLocation());
															                              
															 log4j.debug("EORGANISM ::saveWsdlFileToFileSystem ::Opening HTTP connection to " + serviceObjectWorker.getServiceLocation() + "...");
															 urlConnection = url.openConnection();
															                    		  
															  // Copy resource to local file, use remote file
															  // if no local file name specified
															                    		  
															  // IF ELSE HTTP/ HTTPS
															  inputString = url.openStream();
															                              
															  // Print info about resource
															  log4j.debug("EORGANISM ::saveWsdlFileToFileSystem ::Copying HTTP resource (type: " +  urlConnection.getContentType());
															   date=new java.util.Date(urlConnection.getLastModified());
															                              
															  log4j.debug("EORGANISM ::saveWsdlFileToFileSystem :: modified on: " +  date.toLocaleString() + ")...");
															  System.out.flush();
															                              
															  fos=null;
															  // Get only file name
															                              
															  fileURLPath= url.getFile();
															                               
															  log4j.debug("fileURLPath: " +  fileURLPath); 
							            	 				
							               } 
			            	   
			            	   				//preparations valid for HTTP and HTTPS
                		  
			                                fileURLPath = fileURLPath.replace("?WSDL", ".xml");
			                                fileURLPath = fileURLPath.replace("?Wsdl", ".xml");
			                                fileURLPath = fileURLPath.replace("?wsdl", ".xml");
			                                fileURLPath = fileURLPath.replace(".jws", "");
			                                fileURLPath = fileURLPath.replace("?", ".");
			                                fileURLPath = fileURLPath.replace("=", ".");
			                                
			                                log4j.debug("EORGANISM ::saveWsdlFileToFileSystem ::HTTPS fileURLPath after replaceString: " +  fileURLPath);
			                                
			                                st=new StringTokenizer(fileURLPath, "/:");
			                            
			                                // file name is last token
			                                // last 
			                                while (st.hasMoreTokens()){
			                             		    localFileName=st.nextToken();
			                                }// while
			                               
			                                serviceObjectWorker.setfileName(localFileName);
			                                
			                                log4j.debug("EORGANISM ::saveWsdlFileToFileSystem :: Copying resource (localFileName: " +  localFileName);
			                                 
			                                location1 		=  eOrganismPath+"\\COPY_WSDL\\"+serviceObjectWorker.getSystem().replace(" ","-")+"\\"+serviceObjectWorker.getApplication().replace(" ","-")+"\\"+serviceObjectWorker.getComponent().replace(" ","-")+"\\"+serviceObjectWorker.getServiceName().replace(" ","-")+"\\"+ localFileName;
			                                serviceObjectWorker.setLocalFilePath(location1);
			                                
			                                //location2 	=  eOrganismPath+"\\COPY_WSDL\\"+serviceObjectWorker.getApplication()+"."+serviceObjectWorker.getComponent()+"."+serviceObjectWorker.getServiceName()+"."+ localFileName;
			                                location2 		=  eOrganismPath+"\\COPY_WSDL\\"+serviceObjectWorker.getSystem().replace(" ","-")+"\\"+serviceObjectWorker.getApplication().replace(" ","-")+"."+serviceObjectWorker.getComponent().replace(" ","-")+"."+serviceObjectWorker.getServiceName().replace(" ","-")+"."+ localFileName;
			          	                  
			                                //location3 	=  eOrganismPath+"\\COPY_WSDL\\"+serviceObjectWorker.getApplication()+"."+serviceObjectWorker.getComponent()+"."+serviceObjectWorker.getServiceName()+"."+ localFileName;
			                                location3 		=  eOrganismPath+"\\COPY_WSDL\\"+serviceObjectWorker.getSystem().replace(" ","-")+"."+serviceObjectWorker.getApplication().replace(" ","-")+"."+serviceObjectWorker.getComponent().replace(" ","-")+"."+serviceObjectWorker.getServiceName().replace(" ","-")+"."+ localFileName;
			          	                 
			                                
			                                locationWebsite = eOrganismWebsite+"/"+serviceObjectWorker.getSystem().replace(" ","-")+"/"+serviceObjectWorker.getApplication().replace(" ","-")+"/"+serviceObjectWorker.getComponent().replace(" ","-")+"/"+serviceObjectWorker.getServiceName()+"/"+ localFileName;
			                                serviceObjectWorker.setWebsiteUrl(locationWebsite);
				                             
			                                // web
			                                
			                                locationWeb1 	= eOrganismWebPath+"\\COPY_WSDL\\"+serviceObjectWorker.getSystem().replace(" ","-")+"\\"+(serviceObjectWorker.getApplication()).replace(" ","-")+"\\"+(serviceObjectWorker.getComponent()).replace(" ","-")+"\\"+serviceObjectWorker.getServiceName().replace(" ","-")+"\\"+ localFileName;
			                                serviceObjectWorker.setWebFilePath(locationWeb1);
			                                
			                                log4j.debug("saveWsdlFileToFileSystem  serviceObjectWorker.getWebFilePath()=" +  serviceObjectWorker.getWebFilePath());
				                              
			                           
			  	                            locationWeb2 	=  eOrganismWebPath+"\\COPY_WSDL\\"+serviceObjectWorker.getSystem().replace(" ","-")+"."+serviceObjectWorker.getApplication().replace(" ","-")+"."+serviceObjectWorker.getComponent().replace(" ","-")+"."+serviceObjectWorker.getServiceName().replace(" ","-")+"."+ localFileName;
			                                 			                                  
			                                locationDBPath 	= eOrganismDBDataPath+"\\"+serviceObjectWorker.getSystem().replace(" ","-")+"."+(serviceObjectWorker.getApplication()).replace(" ","-")+"."+(serviceObjectWorker.getComponent()).replace(" ","-")+"."+serviceObjectWorker.getServiceName().replace(" ","-")+"."+ localFileName;
			                                serviceObjectWorker.setDBPath(locationDBPath);
			                                  
			                                
			                                filePath = new File(eOrganismPath+"\\COPY_WSDL\\"+serviceObjectWorker.getSystem().replace(" ","-"));
			                                filePath.mkdir();
			                                
			                                filePath = new File(eOrganismPath+"\\COPY_WSDL\\"+serviceObjectWorker.getSystem().replace(" ","-")+"\\"+serviceObjectWorker.getApplication().replace(" ","-"));
			                                filePath.mkdir();
			                                			                                
			                                filePath = new File(eOrganismPath+"\\COPY_WSDL\\"+serviceObjectWorker.getSystem().replace(" ","-")+"\\"+serviceObjectWorker.getApplication().replace(" ","-")+"\\"+serviceObjectWorker.getComponent().replace(" ","-"));
			                                filePath.mkdir();
			                                
			                                filePath = new File(eOrganismPath+"\\COPY_WSDL\\"+serviceObjectWorker.getSystem().replace(" ","-")+"\\"+serviceObjectWorker.getApplication().replace(" ","-")+"\\"+serviceObjectWorker.getComponent().replace(" ","-")+"\\"+serviceObjectWorker.getServiceName().replace(" ","-"));
			                                filePath.mkdir();
			                                
			                                log4j.debug("creating Directory resource Path: " +  eOrganismPath+"\\COPY_WSDL\\"+serviceObjectWorker.getSystem().replace(" ","-")+"\\"+(serviceObjectWorker.getApplication()).replace(" ","-")+"\\"+(serviceObjectWorker.getComponent()).replace(" ","-")+"\\"+serviceObjectWorker.getServiceName().replace(" ","-"));
                                            
			                                log4j.debug("creating Directory resource :: filePath.getAbsolutePath(): " +  filePath.getAbsolutePath());
			                                
			                                file = new File(serviceObjectWorker.getLocalFilePath());
			                                // make for tomcat
			                                filePath = new File(eOrganismWebPath+"\\COPY_WSDL\\"+serviceObjectWorker.getSystem().replace(" ","-"));
			                                filePath.mkdir();
			                                
			                                filePath = new File(eOrganismWebPath+"\\COPY_WSDL\\"+serviceObjectWorker.getSystem().replace(" ","-")+"\\"+serviceObjectWorker.getApplication().replace(" ","-"));
			                                filePath.mkdir();
			                                			                                
			                                filePath = new File(eOrganismWebPath+"\\COPY_WSDL\\"+serviceObjectWorker.getSystem().replace(" ","-")+"\\"+serviceObjectWorker.getApplication().replace(" ","-")+"\\"+serviceObjectWorker.getComponent().replace(" ","-"));
			                                filePath.mkdir();
			                                
			                                filePath = new File(eOrganismWebPath+"\\COPY_WSDL\\"+serviceObjectWorker.getSystem().replace(" ","-")+"\\"+serviceObjectWorker.getApplication().replace(" ","-")+"\\"+serviceObjectWorker.getComponent().replace(" ","-")+"\\"+serviceObjectWorker.getServiceName().replace(" ","-"));
			                                filePath.mkdir();
			                                
			                                file = new File(serviceObjectWorker.getWebFilePath());
			                                
			                               
			                                 
			                                log4j.debug("EORGANISM ::saveWsdlFileToFileSystem ::File " + serviceObjectWorker.getLocalFilePath()+ " created OK location: ");
			                             
			                                fos = new FileOutputStream(serviceObjectWorker.getLocalFilePath());
			                                
			                                log4j.debug("EORGANISM ::saveWsdlFileToFileSystem ::Copying resource (location: " +  serviceObjectWorker.getLocalFilePath());
			                                
			                                int oneChar, count=0;
			                                
			                                while ((oneChar=inputString.read()) != -1){
			                                   fos.write(oneChar);
			                                   count++;
			                                }
			                                
			                                log4j.debug("EORGANISM ::saveWsdlFileToFileSystem ::File SAVED at location: " +  serviceObjectWorker.getLocalFilePath());
				                             
			                                inputString.close();
			                                fos.close();
			                                
			                                // location 1 to location 2
			                                copyFile(new File(serviceObjectWorker.getLocalFilePath()), new File(location2));
			                                copyFile(new File(serviceObjectWorker.getLocalFilePath()), new File(location3));
			                                
			                                //log4j.debug("EORGANISM ::saveWsdlFileToFileSystem ::copyFile1 : location1" +  location1);
			                                
			                                //copyFile(new File(serviceObjectWorker.getLocalFilePath()), new File(serviceObjectWorker.getWebsiteUrl()));
			                                //log4j.debug("EORGANISM ::saveWsdlFileToFileSystem ::copyFile2 : location1" +  serviceObjectWorker.getWebsiteUrl());
			                                
			                                //copyFile(new File(serviceObjectWorker.getLocalFilePath()), new File(serviceObjectWorker.getWebsiteUrl()));
			                                copyFile(new File(serviceObjectWorker.getLocalFilePath()), new File(serviceObjectWorker.getWebFilePath()));
			                                copyFile(new File(serviceObjectWorker.getLocalFilePath()), new File(serviceObjectWorker.getDBPath()));
			                                
			                                // set content
			                                serviceObjectWorker.setServiceFileContent(getFileContentAsString(serviceObjectWorker.getLocalFilePath()));
                 
                		  
                	  }catch (MalformedURLException mlue){
                		  log4j.error("EORGANISM ::saveWsdlFileToFileSystem :: MalformedURLException :: " + mlue.toString());
                		  log4j.debug("EORGANISM ::saveWsdlFileToFileSystem :: MalformedURLException ::" + mlue.toString());
                		  
                		  saveLogging("EORGANISM ::saveWsdlFileToFileSystem :: MalformedURLException" + mlue.toString(),"EORGANISM.EOrganismController.saveWsdlFileToFileSystem",serviceObjectWorker.getServiceLocation());
                		  		
                	  }catch (IOException ioe){ 
                		  log4j.error("EORGANISM ::saveWsdlFileToFileSystem :: IOException :: " + ioe.toString()); 
                		  log4j.debug("EORGANISM ::saveWsdlFileToFileSystem :: IOException :: " + ioe.toString()); 
                		  
                		  saveLogging("EORGANISM ::saveWsdlFileToFileSystem :: MalformedURLException" + ioe.toString(),"EORGANISM.EOrganismController.saveWsdlFileToFileSystem",serviceObjectWorker.getServiceLocation());
                 		 
                		  
                	  }catch (Exception e){ 
                		  log4j.error("EORGANISM ::saveWsdlFileToFileSystem :: Exception :: " + e.toString()); 
                		  log4j.debug("EORGANISM ::saveWsdlFileToFileSystem :: Exception :: " + e.toString()); 
                		  
                		  saveLogging("EORGANISM ::saveWsdlFileToFileSystem :: MalformedURLException" + e.toString(),"EORGANISM.EOrganismController.saveWsdlFileToFileSystem",serviceObjectWorker.getServiceLocation());
                		  
                	  }

                	  
                  }// if
            
            
            
            }// for
  
        	
        }// saveWsdlFileToFileSystem
        
        
        public void setupWsdlInfo(){
        	
        	EService serviceObjectWorker;
        	// init
        	url 			= null;
            urlConnection 	= null;
            
            //https stuff
              
            java.util.Date 		date;
            
            FileOutputStream 	fos			  	= null;
            String 				localFileName 	= null;
            InputStream 		inputString 	= null;
            StringTokenizer 	st;
            
            String 				location1 ="";
            String 				fileURLPath="";
            File 				file;
            File 				filePath;
                        
            String 				locationWeb1 ="";
            String 				locationWeb2 ="";
            String 				locationWebsite ="";
            String 				locationDBPath="";
            
            
            File 				fileWeb;
            File 				filePathWeb;
            File 				fileDBPath;
            File 				fileDB;
            
            
            //fileName 			= "C:\\EOrganism_MySQL\\tempfile.xml";
            for (int i = 1; i < serviceArrayList.size(); i++) {  
            	
                  serviceObjectWorker    = new EService();
                  serviceObjectWorker 	= (EService)serviceArrayList.get(i);
            
                  // if location is not null attempt to load the WSDL of FILE from that location
                  if(serviceObjectWorker.getServiceLocation()!=null && serviceObjectWorker.getServiceLocation()!=""&& (serviceObjectWorker.getServiceLocation()).length()>4){
                      	
                	  try{
                 		  
				                		  if((serviceObjectWorker.getServiceLocation().toLowerCase()).startsWith("https")){
				                			  
							                			  	urlHttps 			= new URL(serviceObjectWorker.getServiceLocation());
							                			  	
							                			    log4j.debug("EORGANISM ::setupWsdlInfo :: ###### Opening HTTPS connection to " + serviceObjectWorker.getServiceLocation() + "...");
							                        		
							                			    httpsURLConnection	= (HttpsURLConnection)urlHttps.openConnection();
				  		         				
				                                			inputString = urlHttps.openStream();
				                			    
							                      		  	fos=null;
							                                // Get only file name
							                      		  	
							                                fileURLPath= urlHttps.getFile();
							                                log4j.debug("HTTPS fileURLPath: " +  fileURLPath);
							                                
							                                serviceObjectWorker.setCertificateInformation(getSecurityCertificatesFromHttpsURL(httpsURLConnection));
							    		         			
							                                
				                		  } else if ((serviceObjectWorker.getServiceLocation().toLowerCase()).startsWith("http")) {                   
															 url  = new URL(serviceObjectWorker.getServiceLocation());
															                              
															 log4j.debug("EORGANISM ::setupWsdlInfo ::Opening HTTP connection to " + serviceObjectWorker.getServiceLocation() + "...");
															 urlConnection = url.openConnection();
															                    		  
															  // Copy resource to local file, use remote file
															  // if no local file name specified
															                    		  
															  // IF ELSE HTTP/ HTTPS
															  inputString = url.openStream();
															                              
															  // Print info about resource
															  log4j.debug("EORGANISM ::setupWsdlInfo ::Copying HTTP resource (type: " +  urlConnection.getContentType());
															  date=new java.util.Date(urlConnection.getLastModified());
															                              
															  log4j.debug("EORGANISM ::setupWsdlInfo :: modified on: " +  date.toLocaleString() + ")...");
															  System.out.flush();
															                              
															  fos=null;
															  // Get only file name
															                              
															  fileURLPath= url.getFile();
															                               
															  log4j.debug("fileURLPath: " +  fileURLPath); 
							            	 				
							               } 
			            	   
			            	   				//preparations valid for HTTP and HTTPS
                		  
			                                fileURLPath = fileURLPath.replace("?WSDL", ".xml");
			                                fileURLPath = fileURLPath.replace("?Wsdl", ".xml");
			                                fileURLPath = fileURLPath.replace("?wsdl", ".xml");
			                                fileURLPath = fileURLPath.replace(".jws", "");
			                                fileURLPath = fileURLPath.replace("?", ".");
			                                fileURLPath = fileURLPath.replace("=", ".");
			                                
			                                log4j.debug("EORGANISM ::setupWsdlInfo ::HTTPS fileURLPath after replaceString: " +  fileURLPath);
			                                
			                                st=new StringTokenizer(fileURLPath, "/:");
			                            
			                                // file name is last token
			                                // last 
			                                while (st.hasMoreTokens()){
			                             		    localFileName=st.nextToken();
			                                }// while
			                               
			                                serviceObjectWorker.setfileName(localFileName);
			                                
			                                log4j.debug("EORGANISM ::setupWsdlInfo :: Copying resource (localFileName: " +  localFileName);
			                                 
			                                location1 		=  eOrganismPath+"\\COPY_WSDL\\"+serviceObjectWorker.getSystem().replace(" ","-")+"\\"+serviceObjectWorker.getApplication().replace(" ","-")+"\\"+serviceObjectWorker.getComponent().replace(" ","-")+"\\"+serviceObjectWorker.getServiceName().replace(" ","-")+"\\"+ localFileName;
			                                serviceObjectWorker.setLocalFilePath(location1);
			                                
			                                
			                                locationWebsite = eOrganismWebsite+"/"+serviceObjectWorker.getSystem().replace(" ","-")+"/"+serviceObjectWorker.getApplication().replace(" ","-")+"/"+serviceObjectWorker.getComponent().replace(" ","-")+"/"+serviceObjectWorker.getServiceName()+"/"+ localFileName;
			                                serviceObjectWorker.setWebsiteUrl(locationWebsite);
				                             
			                                // web
			                                locationWeb1 	= eOrganismWebPath+"\\COPY_WSDL\\"+serviceObjectWorker.getSystem().replace(" ","-")+"\\"+(serviceObjectWorker.getApplication()).replace(" ","-")+"\\"+(serviceObjectWorker.getComponent()).replace(" ","-")+"\\"+serviceObjectWorker.getServiceName().replace(" ","-")+"\\"+ localFileName;
			                                serviceObjectWorker.setWebFilePath(locationWeb1);
			                                
			                                log4j.debug("setupWsdlInfo  serviceObjectWorker.getWebFilePath()=" +  serviceObjectWorker.getWebFilePath());
				                              
			                           
			  	                            locationWeb2 	=  eOrganismWebPath+"\\COPY_WSDL\\"+serviceObjectWorker.getSystem().replace(" ","-")+"."+serviceObjectWorker.getApplication().replace(" ","-")+"."+serviceObjectWorker.getComponent().replace(" ","-")+"."+serviceObjectWorker.getServiceName().replace(" ","-")+"."+ localFileName;
			                                 			                                  
			                                locationDBPath 	= eOrganismDBDataPath+"\\"+serviceObjectWorker.getSystem().replace(" ","-")+"."+(serviceObjectWorker.getApplication()).replace(" ","-")+"."+(serviceObjectWorker.getComponent()).replace(" ","-")+"."+serviceObjectWorker.getServiceName().replace(" ","-")+"."+ localFileName;
			                                serviceObjectWorker.setDBPath(locationDBPath);
			                                                    
			                                // set content
			                                serviceObjectWorker.setServiceFileContent(getFileContentAsString(serviceObjectWorker.getLocalFilePath()));
                 
                		  
                	  }catch (MalformedURLException mlue){
                		  log4j.error("EORGANISM ::setupWsdlInfo :: MalformedURLException :: " + mlue.toString());
                		  log4j.debug("EORGANISM ::setupWsdlInfo :: MalformedURLException ::" + mlue.toString());
                		  
                		  saveLogging("EORGANISM ::setupWsdlInfo :: MalformedURLException" + mlue.toString(),"EORGANISM.EOrganismController.setupWsdlInfo",serviceObjectWorker.getServiceLocation());
                		  		
                	  }catch (IOException ioe){ 
                		  log4j.error("EORGANISM ::setupWsdlInfo :: IOException :: " + ioe.toString()); 
                		  log4j.debug("EORGANISM ::setupWsdlInfo :: IOException :: " + ioe.toString()); 
                		  
                		  saveLogging("EORGANISM ::setupWsdlInfo :: MalformedURLException" + ioe.toString(),"EORGANISM.EOrganismController.setupWsdlInfo",serviceObjectWorker.getServiceLocation());
                 		 
                		  
                	  }catch (Exception e){ 
                		  log4j.error("EORGANISM ::setupWsdlInfo :: Exception :: " + e.toString()); 
                		  log4j.debug("EORGANISM ::setupWsdlInfo :: Exception :: " + e.toString()); 
                		  
                		  saveLogging("EORGANISM ::setupWsdlInfo :: MalformedURLException" + e.toString(),"EORGANISM.EOrganismController.setupWsdlInfo",serviceObjectWorker.getServiceLocation());
                		  
                	  }

                	  
                  }// if
            
            
            
            }// for
  
        	
        }// setupWsdlInfo
        
       	 
		 public void loadWsdlServiceStringContent(){
			 	
		 	 EService serviceObjectWorker;
			 
		     
		     for (int i = 1; i < serviceArrayList.size(); i++) {  
		           serviceObjectWorker    	= new EService();
		           serviceObjectWorker 		= (EService)serviceArrayList.get(i);
		     
		           // if location is not null attempt to load the WSDL of FILE from that location
		           if(serviceObjectWorker.getServiceLocation()!=null && serviceObjectWorker.getServiceLocation()!=""&& (serviceObjectWorker.getServiceLocation()).length()>4){
		               	
		         	  try{
		         		  
		         		 //serviceObjectWorker.setServiceFileContent(readFile(serviceObjectWorker.getServiceLocation(), StandardCharsets.UTF_8));
		                  
		         		 serviceObjectWorker.setServiceFileContent(readFileURL(serviceObjectWorker.getServiceLocation()));
		         	  
		         	  
		         	  }catch (MalformedURLException mlue){
                		  log4j.error("EORGANISM ::loadWsdlServiceStringContent :: MalformedURLException: " + mlue.toString());
                		  log4j.debug("EORGANISM ::loadWsdlServiceStringContent :: MalformedURLException: " + mlue.toString());
                		  saveLogging("EORGANISM ::loadWsdlServiceStringContent :: MalformedURLException: " + mlue.toString(),"EORGANISM.EOrganismController.loadWsdlServiceStringContent",serviceObjectWorker.getServiceLocation());
                		  		
                	  }catch (IOException ioe){ 
                		  log4j.error("EORGANISM ::loadWsdlServiceStringContent :: IOException: " + ioe.toString()); 
                		  log4j.debug("EORGANISM ::loadWsdlServiceStringContent :: IOException: " + ioe.toString()); 
                		  saveLogging("EORGANISM ::loadWsdlServiceStringContent :: IOException:  " + ioe.toString(),"EORGANISM.EOrganismController.loadWsdlServiceStringContent",serviceObjectWorker.getServiceLocation());
                		  
                	  }catch (Exception e){ 
                		  log4j.error("EORGANISM ::loadWsdlServiceStringContent :: Exception" + e.toString()); 
                		  log4j.debug("EORGANISM ::loadWsdlServiceStringContent :: Exception" + e.toString()); 
                		  saveLogging("EORGANISM ::loadWsdlServiceStringContent :: Exception:  " + e.toString(),"EORGANISM.EOrganismController.loadWsdlServiceStringContent",serviceObjectWorker.getServiceLocation());
                 		  
                	  }
		
		         	  
		           }// if
		     
		           serviceArrayList.set(i, serviceObjectWorker);
		     
		     
		     }// for
		
		 	
		 }// loadWsdlServiceStringContent
		 
		 
		 public void copyFile(File _sourceFile, File _destFile) {
		 
			 FileChannel source = null;
			 FileChannel destination = null;
			
			 try{
					 
				 	 if(!_destFile.exists()) {
					  _destFile.createNewFile();
					 }
					
					  source 			= new FileInputStream(_sourceFile).getChannel();
					  destination 		= new FileOutputStream(_destFile).getChannel();
					  destination.transferFrom(source, 0, source.size());
					  
					  if(source != null) {
						   source.close();
					  }
					  if(destination != null) {
						 destination.close();
					  }
					  
					  log4j.error("EORGANISM :: copyFile::COPY OK source =" + _sourceFile.getAbsolutePath() + " to destination = " +_destFile.getAbsolutePath());
					  
			}catch(IOException ioe){
		        	 	log4j.error("EORGANISM :: copyFile::IOException source =" + _sourceFile.getAbsolutePath() + " to destination = " +_destFile.getAbsolutePath());
		        	 	log4j.error("EORGANISM :: copyFile::IOException ioe =" + ioe.toString());
		        	 	
			}finally{
				
			}// finally
			 
		 }//copy files
		 
		 
		 public String getFileContentAsString(String _location) {
			 
			 fileContentStringTemp = "";
			 try{
					 if(_location !=null){
						 		fileContentStringTemp = new String(Files.readAllBytes(Paths.get(_location)));
					 }
					  	log4j.error("EORGANISM :: getFileContactAsString:: OK _location =" + _location );
					  
			}catch(IOException ioe){
			      	 	log4j.error("EORGANISM :: getFileContactAsString::IOException _location =" + _location );
		        	 	log4j.error("EORGANISM :: getFileContactAsString::IOException ioe =" + ioe.toString());
			}finally{
				
			}// finally
			 
			 
			 return fileContentStringTemp;
			 
		 }//getFileContactAsString
 		 
		 // 
		 public void loadCompleteServicesWSDLInformation(){
			 // file are copied to local file system at location - serviceObjectWorker.getLocalFilePath();
			 
		 	 EService serviceObjectWorker;
		 	 //reset variables
		     String tempLocation		= "";
		     
		     size =0;
		     
	            Document              document; 
	            NodeList              operations 	= null; 
	            NodeList              elements 		= null; 
	            NodeList              services 		= null; 
	            NodeList              schemas 		= null;
	            NodeList              schemas2 		= null;
	            NodeList              schemas3 		= null;
	            NodeList              schemasImports = null;
	            NodeList              schemasImportsLocations = null;
	            NodeList              schemasTemp 	= null;
	            NodeList              elementsTemp 	= null;
	            NodeList              schemaImports 		= null;
	            
	            
	            Element              schemaImportsElement 	= null;
	            
	            
	            
	            String                operationName 			="";
	            String                schemaName 				="";
	            String                complexTypeName 			="";
	            String                elementName 				="";
	            String                schemaImportLocationName 	="";
	            
	            String				  typeTemp					="";
	            EInterface			  interfaceTemp 			= new EInterface();
	            ESchema				  schemaTemp 				= new ESchema();
	 		     
		     for (int i = 1; i < serviceArrayList.size(); i++) {  
		    	 
		    	 	// reset ArrayList
		    	  	interfacesArrayListTemp 	= new ArrayList();		// EInterface
		            schemasArrayListTemp 		= new ArrayList();		// ESchema
		            schemasArrayListTemp2 		= new ArrayList();		// ESchema
		            complexTypeArrayListTemp 	= new ArrayList();		// EComplexType
		            elementsArrayListTemp 		= new ArrayList();		// EElements
		             
		            serviceObjectWorker    		= new EService();
		            serviceObjectWorker 		= (EService)serviceArrayList.get(i);
		           
		            tempLocation 	= serviceObjectWorker.getLocalFilePath();// already locaded and setup - IMPORTANT - load XML from local location
		            typeTemp 		= serviceObjectWorker.getServiceSubtype();// JWS, Apache WS
		          	  
					/// load operations - for each operation 
					//- load complextypes, elemenets - set to each operation
					//- load schemas - parse the complextypes, elemenets from schema
					//setup all schemas to ServiceWorker
					//--externalize the schema to external XSD
					         		
					// load the import schemas, each import XSD, parse the XSD with XSOM
					//- load schemas - parse the complextypes, elemenets from schema imports
			       		
			       		
			       // setup to each eperation
			       // setup XML Request
			       // setup XML response
			       // externalize   SOAP Request XML file
			       // externalize   SOAP Response XML file
				   // setup SOAP XML Request file location
			       // setup SOAP XML response file location
			       		
				    //setup all operations collection to ServiceWorker	
		         	  
		         	  
		         // load operations - per each serviceWorker - WSDL file is locally copied
				   try {
				         		 
				          				//log4j.debug("EORGANISM :: loadCompleteServicesWSDLInformation::****** #2  location = "+  tempLocation ); 
				         				document     = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new FileInputStream(tempLocation));
				         				//log4j.debug("EORGANISM :: loadCompleteServicesWSDLInformation::****** #3  location = "+  tempLocation );
				                       
				         				operations   		= document.getElementsByTagName(ConfigFrame.wsdl_operation);
				                        services     		= document.getElementsByTagName(ConfigFrame.wsdl_service);
				                        schemas		 		= document.getElementsByTagName(ConfigFrame.xs_schema);
				                        schemas2	 		= document.getElementsByTagName(ConfigFrame.s_schema);
				                        schemas3	 		= document.getElementsByTagName(ConfigFrame.xsd_schema);
				                        schemasImports 		= document.getElementsByTagName("xsd:import");
				                        
				                        // Parse XML to XSD
				                        serviceObjectWorker.setGeneratedXSDFileName(serviceObjectWorker.getFileName()+"-generated.xsd");
				                        serviceObjectWorker.setGeneratedXSDLocalFileLocation("C:\\apache\\Tomcat 8.0\\webapps\\eorganism\\GENERATED_XSD\\"+serviceObjectWorker.getGeneratedXSDFileName());
				                        serviceObjectWorker.setGeneratedXSDFileLocation("http://localhost:9090/eorganism/GENERATED_XSD/"+serviceObjectWorker.getGeneratedXSDFileName());
				                        
				                        
				                        // set DB location
				                        String locationXSDFileDBPath 	= eOrganismDBDataPath+"\\"+(serviceObjectWorker.getSystem()).replace(" ","-")+'.'+(serviceObjectWorker.getApplication()).replace(" ","-")+"."+(serviceObjectWorker.getComponent()).replace(" ","-")+"."+serviceObjectWorker.getServiceName().replace(" ","-")+"."+ serviceObjectWorker.getGeneratedXSDFileName();
				                        
				                        serviceObjectWorker.setGeneratedXSDFileLocationDB(locationXSDFileDBPath);
				                        serviceObjectWorker.setGeneratedXSDFileContent(getFileContentAsString(serviceObjectWorker.getGeneratedXSDLocalFileLocation()));
				                         
				                        // #3 externalize the SCHEMA elements into Schema XSD document
									       
				                    	log4j.debug("EORGANISM :: loadCompleteServicesWSDLInformation:: wsdlToXSD****** START  location = "+  tempLocation ); 
					         			
				                    	wsdlToXSD(new FileInputStream(new File(tempLocation)), new FileOutputStream( new File(serviceObjectWorker.getGeneratedXSDLocalFileLocation())));
				                        
				                    	// copy extrenalized XSD to the DB location path; so XSD Genrated can be easily inserted to the DB as file
				                    	copyFile(new File(serviceObjectWorker.getGeneratedXSDLocalFileLocation()), new File(serviceObjectWorker.getGeneratedXSDFileLocationDB()));
					                       
				                    	//log4j.debug("EORGANISM :: loadCompleteServicesWSDLInformation:: wsdlToXSD******  END generated XSD location = "+  serviceObjectWorker.getGeneratedXSDLocalFileLocation() );
					                      
				                        size = operations.getLength();
				                        
				                         //log4j.debug("EORGANISM :: loadCompleteServicesWSDLInformation::****** #4 wsdl:operatio size="+ size + " ********* s0:operatioloadOperationsFromWSDL - s0:_urlLocation = " + _urlLocation  +"load type=" +_type ); 
				                        if ((typeTemp.trim()).equals("JWS")){
				                            
				                             //log4j.debug("EORGANISM :: loadOperationsFromWSDL::****** JWS ********* WL5G3N0 loadOperationsFromWSDL _location = " + _location  +"load type=" +_type ); 
				                        	 operations     = document.getElementsByTagName(ConfigFrame.WL5G3N0_operation);
				                         
				                             services     	= document.getElementsByTagName(ConfigFrame.WL5G3N0_service);
				                             size = operations.getLength();
				                             //log4j.debug("EORGANISM :: loadOperationsFromWSDL::******* JWS ** WL5G3N0:operation size="+ size + " ********* s0:operatioloadOperationsFromWSDL - s0:_urlLocatio = " + _urlLocation  +"load type=" +_type ); 
				                         }// end if
					                       // log4j.debug("EORGANISM :: loadOperationsFromWSDL::****** #5 wsdl:operatio size="+ size + " ********* s0:operatioloadOperationsFromWSDL - s0:_urlLocation = " + _urlLocation  +"load type=" +_type ); 
					                       // parsing logic didn't work out
					                       if (size==0){
					                                //log4j.debug("EORGANISM :: loadOperationsFromWSDL::****** size==0 ********* xsd:operation loadOperationsFromWSDL _location = " + _location  +"load type=" +_type ); 
					                    	   		operations     	= document.getElementsByTagName(ConfigFrame.xsd_operation);
					                                services     	= document.getElementsByTagName(ConfigFrame.xsd_service);
					                                size 			= operations.getLength();
					                                //log4j.debug("EORGANISM :: loadOperationsFromWSDL::******xsd:operation size="+ size + " ********* s0:operatioloadOperationsFromWSDL - s0:operation _urlLocatio = " + _urlLocation  +"load type=" +_type ); 
					                                
					                                if (size==0){
					                                    
					                                         //log4j.debug("EORGANISM :: loadOperationsFromWSDL::****** size==0 ********* s0:operation loadOperationsFromWSDL - s0:operation _location = " + _location  +"load type=" +_type ); 
					                                		 operations     = document.getElementsByTagName(ConfigFrame.s0_operation);
					                                         services     	= document.getElementsByTagName(ConfigFrame.s0_service);
					                                         size 			= operations.getLength();
					                                         //log4j.debug("EORGANISM :: loadOperationsFromWSDL::******s0:operation size="+ size + " ********* s0:operatioloadOperationsFromWSDL - s0:_urlLocatio = " + _urlLocation  +"load type=" +_type ); 
					                                            
					                                }
					                                //ESF WSDL
					                                if (size==0){
					                                    
					                             	    	log4j.debug("EORGANISM :: loadCompleteServicesWSDLInformation::****** size==0 ********* ESF WSDL :: <operation loadOperationsFromWSDL _location = " + tempLocation  +"load type=" +typeTemp ); 
					                             	    	operations     	= document.getElementsByTagName(ConfigFrame.operation);
					                                        size 			= operations.getLength();
					                                        log4j.debug("EORGANISM :: loadCompleteServicesWSDLInformation::******ESF WSDL <operation size="+ size + " ********* operatioloadOperationsFromWSDL - s0:_urlLocatio = " + tempLocation  +"load type=" +typeTemp ); 
					                                           
					                                }// end if
					                                
					                                
					                                
					                       }// end if
                       
               
						         // handle exception creating DocumentBuilder
						         }catch ( FileNotFoundException fnfe ) {
						                                        
						                                       
						                                        //JOptionPane.showMessageDialog( null, "EORGANISM :: loadOperationsFromWSDL : FileNotFoundException when loaded from  XML file: "   + _location  +"\n"+ fnfe.toString());
						                                        log4j.error("EORGANISM :: loadCompleteServicesWSDLInformation::FileNotFoundException _location=" +tempLocation );
						                                        log4j.error("EORGANISM :: loadCompleteServicesWSDLInformation::FileNotFoundException fnfe=" +fnfe.toString() );
						                                        
						                                        log4j.debug("EORGANISM :: loadCompleteServicesWSDLInformation::FileNotFoundException _location=" +tempLocation );
						                                        log4j.debug("EORGANISM :: loadCompleteServicesWSDLInformation::FileNotFoundException fnfe=" +fnfe.toString() );
						                                        fnfe.printStackTrace();
						         
						         }catch ( ParserConfigurationException parserException ) {
						         	 							//JOptionPane.showMessageDialog( null,"EORGANISM :: loadOperationsFromWSDL: ParserConfigurationException when loaded from  XML file: "   + _location  +"\n"+ parserException.toString());
						             
										                        	log4j.error("EORGANISM :: loadCompleteServicesWSDLInformation::ParserConfigurationException _location=" +tempLocation );
										                            log4j.error("EORGANISM :: loadCompleteServicesWSDLInformation::ParserConfigurationException parserException=" +parserException.toString() );
										                            
										                            log4j.debug("EORGANISM :: loadCompleteServicesWSDLInformation::ParserConfigurationException _location=" +tempLocation );
										                            log4j.debug("EORGANISM :: loadCompleteServicesWSDLInformation::ParserConfigurationException parserException=" +parserException.toString() );
										                            parserException.printStackTrace();
						                         
						          }// handle exception parsing Document
						          catch ( SAXException saxException ) {
						         	 							//JOptionPane.showMessageDialog( null,"EORGANISM ::loadOperationsFromWSDL: SAXException when loaded from  XML file: "   + _location +"\n"+  saxException.toString());
						              
										                        	log4j.error("EORGANISM :: loadCompleteServicesWSDLInformation::SAXException _location=" +tempLocation );
										                            log4j.error("EORGANISM :: loadCompleteServicesWSDLInformation::SAXException saxException=" +saxException.toString() );
										                            
										                            log4j.debug("EORGANISM :: loadCompleteServicesWSDLInformation::SAXException _location=" +tempLocation );
										                            log4j.debug("EORGANISM :: loadCompleteServicesWSDLInformation::SAXException saxException=" +saxException.toString() );
										                            saxException.printStackTrace();
						                         
						          }// handle exception reading/writing data
						          catch ( IOException ioException ) {
							                                         //JOptionPane.showMessageDialog( null,  "EORGANISM :: loadOperationsFromWSDL: IOException when loaded from  XML file: "   + _location + "\n" + ioException.toString()+"\n"+ "Pls make sure to have the configuration XML file " + _location + "\n" +" in the same root directory as EOrganism");
									                        	 	log4j.error("EORGANISM :: loadCompleteServicesWSDLInformation::IOException _location=" +tempLocation );
										                            log4j.error("EORGANISM :: loadCompleteServicesWSDLInformation::IOException ioException=" +ioException.toString() );
										                            
										                            log4j.debug("EORGANISM :: loadCompleteServicesWSDLInformation::IOException _location=" +tempLocation );
										                            log4j.debug("EORGANISM :: loadCompleteServicesWSDLInformation::IOException ioException=" +ioException.toString() );
										                            ioException.printStackTrace();
							                                          
							                                        //System.exit( 1 );
						          }//
						         catch (Exception _exception ) {
						             						//JOptionPane.showMessageDialog( null,  "loadOperationsFromWSDL: _exception when loaded from  XML file: "   + _location + "\n" + _exception.toString()+"\n"+ "Pls make sure to have the configuration XML file " + _location + "\n" +" in the same root directory as EOrganism");
									                        	log4j.error("EORGANISM :: loadCompleteServicesWSDLInformation::Exception _location=" +tempLocation );
									                            log4j.error("EORGANISM :: loadCompleteServicesWSDLInformation::Exception _exception=" +_exception.toString() );
									                            
									                            log4j.debug("EORGANISM :: loadCompleteServicesWSDLInformation::Exception _location=" +tempLocation );
									                            log4j.debug("EORGANISM :: loadCompleteServicesWSDLInformation::Exception _exception=" +_exception.toString() );
									                        	_exception.printStackTrace();
										                        	//System.exit( 1 );
						         }//end catch
						         
						         
				   				// all operations are in this Array of Strings
				   				operationsArrayString = new String[size];
						         
						        log4j.debug("EORGANISM :: loadCompleteServicesWSDLInformation::******  operationsArrayString size= " + operationsArrayString.length);  
						          
						         for (int k = 0; k < size; k++) {
						             
						               operationName           = (operations.item(k).getAttributes().getNamedItem(ConfigFrame.name).getNodeValue()).toString();
						               //operationsArrayList.add(operations.item(i).getAttributes().getNamedItem("name").getNodeValue());
						               
						               operationsArrayString[k]= operationName;
						               //log4j.debug("EORGANISM :: loadOperationsFromWSDL::****** #3 loadOperationsFromWSDL _urlLocation :: "+_urlLocation+"::loadOperationsFromWSDL  operationName = " + operationName );  
						        
						        }// for
						         
						         // remove duplicates from array String operations
						         end = operationsArrayString.length;
						         set = new HashSet<String>();
						
						         for(int t = 0; t < end; t++){
						           set.add(operationsArrayString[t]);
						         }
						         
						         int setSize= 0;
						         setSize = set.size();
						         
						         operationsArrayStringNew = new String[setSize];
						         		
						         iterator = set.iterator();
						         
						         int p=0;
						         while(iterator.hasNext()) {
						         	operationsArrayStringNew[p] = (String)iterator.next();
						         	//log4j.debug("EORGANISM :: loadCompleteServicesWSDLInformation****** #2 loadOperationsFromWSDL - UNIQUE OPERATIONS ***** loadOperationsFromWSDL operationsArrayStringNew["+p+"]  = " + operationsArrayStringNew[p]  );  
						         	p++;
						         }// while elements in the set
         
						         interfacesArrayListTemp 	= new ArrayList();	
						         
						         //set the interfacesArrayListTemp - construct ArrayList from Array of String operations
						         for (int x = 0; x < operationsArrayStringNew.length; x++) {
						        	 
						        	 interfaceTemp = new EInterface();
						        	 interfaceTemp.setInterfaceName(operationsArrayStringNew[x]);
						        	 
						        	 interfaceTemp.setLocation(serviceObjectWorker.getServiceLocation());
						        	 interfaceTemp.setHardwareIP(serviceObjectWorker.getServiceIP());
						        	 interfaceTemp.setHardwareName(serviceObjectWorker.getServiceHost());
						        	 interfaceTemp.setInterfaceType(serviceObjectWorker.getServiceType());
						        	 interfaceTemp.setInterfaceSubType(serviceObjectWorker.getServiceSubtype());
						        	 
						        	 interfacesArrayListTemp.add(interfaceTemp);
						         }//
						         
						         // PRINT INTERFACES
						         for (int v = 0; v < interfacesArrayListTemp.size(); v++) {
						        	 
						        	 interfaceTemp = new EInterface();
						        	 interfaceTemp = (EInterface) interfacesArrayListTemp.get(v);
						        	 
						             log4j.debug("EORGANISM :: loadCompleteServicesWSDLInformation::****** PRINT INTERFACES:: INTERFACES = " + interfaceTemp.getInterfaceName() + ":  service= "+  serviceObjectWorker.getServiceName());  
										
						         }//
		         	  
						         // #1 - set INTERFACES to EService object
						         serviceObjectWorker.setInterfaceArrayList(interfacesArrayListTemp);
						          
						         // set schemas arrayList
						         schemasArrayListTemp 	= new ArrayList();	 
						         
						         for (int y = 0; y < schemas.getLength(); y++) {
						               
						        	   schemaTemp = new ESchema();
						               
						               if (schemas.item(y).getAttributes().getNamedItem("targetNamespace")!=null){
						            	   schemaTemp.setName((schemas.item(y).getAttributes().getNamedItem("targetNamespace").getNodeValue()).toString());
						               }
						               schemaTemp.setFileLocation(serviceObjectWorker.getGeneratedXSDLocalFileLocation());
		                    						                
						               schemasArrayListTemp.add(schemaTemp);
						               
						               //log4j.debug("EORGANISM :: loadCompleteServicesWSDLInformation::****** schema name = " + schemaTemp.getName() + ":  location= "+  schemaTemp.getFileLocation() );  
						        
						        }// for
						         
						        // set schemas to load second set of schemas
						        						         
						        for (int u = 0; u < schemas2.getLength(); u++) {
						               schemaTemp = new ESchema();
						               
						               if (schemas2.item(u).getAttributes().getNamedItem("targetNamespace") !=null){
						            	   schemaTemp.setName((schemas2.item(u).getAttributes().getNamedItem("targetNamespace").getNodeValue()).toString());
						               }
						               
						               schemaTemp.setFileLocation(serviceObjectWorker.getGeneratedXSDLocalFileLocation());
						                
						               schemasArrayListTemp.add(schemaTemp);
						               
						               //log4j.debug("EORGANISM :: loadCompleteServicesWSDLInformation::****** schema2 name = " + schemaTemp.getName() + ":  location= "+  schemaTemp.getFileLocation() );  
											        
						        }// for
						         
						       
						        
						        // schemas3	 = document.getElementsByTagName(ConfigFrame.xsd_schema);
						        // load using xsd_schema
						         
						        for (int b = 0; b < schemas3.getLength(); b++) {
						               schemaTemp = new ESchema();
						               
						               if((schemas3.item(b).getAttributes()).getNamedItem("namespace") !=null ){
						            		   schemaTemp.setName((schemas3.item(b).getAttributes().getNamedItem("namespace").getNodeValue()).toString());
	   				                   }
						               schemaTemp.setFileLocation(serviceObjectWorker.getGeneratedXSDLocalFileLocation());
						               // load schema from imports
						               schemasArrayListTemp.add(schemaTemp);
						               
						               //log4j.debug("EORGANISM :: loadCompleteServicesWSDLInformation::****** schema3 name = " + schemaTemp.getName() + ":  location= "+  schemaTemp.getFileLocation() );  
											        
						        }// for
						        
						       	
						        //schemasImports
						        for (int temp = 0; temp < schemasImports.getLength(); temp++) {
						               schemaTemp = new ESchema();
						               
						               schemaImportsElement =  (Element) schemasImports.item(temp);
						               
						               //schemaTemp.setName(schemaImportsElement.getAttribute("namespace"));
						               schemaTemp.setType(ESchema.SCHEMA_TYPE_IMPORT);							               
						               // load schema from imports
						               
						               if( schemaImportsElement.getAttribute("schemaLocation") !=null ){
						            	   
						            	   schemaTemp.setImportedFileLocation(schemaImportsElement.getAttribute("schemaLocation"));
						            	   
						               }// if not null
						                
						               schemasArrayListTemp.add(schemaTemp);
						               
						               log4j.debug("EORGANISM :: loadCompleteServicesWSDLInformation::****** serviceObjectWorker.getDescriptorFileDeploymentLocation() = " + serviceObjectWorker.getServiceName() );  
										
						               log4j.debug("EORGANISM :: loadCompleteServicesWSDLInformation::****** IMPORTS schemasImports name = " + schemaTemp.getName() + ":  getImportedFileLocation= "+  schemaTemp.getImportedFileLocation() );  
											        
						        }// for
						        
						        
						        
						        for (int w = 0; w < schemasArrayListTemp.size(); w++) {
						        	
						               schemaTemp = new ESchema();
						               						                
						               schemaTemp = (ESchema) schemasArrayListTemp.get(w);
						               
						               log4j.debug("EORGANISM :: loadCompleteServicesWSDLInformation::****** PRINT :: schemasArrayListTemp= " + schemaTemp.getName() + ":  location= "+  schemaTemp.getFileLocation() + ":  getImportedFileLocation= "+  schemaTemp.getImportedFileLocation() + ", schema type = "+ schemaTemp.getType());  
											        
						        }// for
						        
						        // #4 parse XSD - load XSD already externalized from inside WSDL XML to outside XSD or imports - parse all content
						        // #5 parse XSD - load XSD from schema imports - parse all content
						        
						        // populate ComplexType, Element - set them to Schema, and set schemas to Service
						        
						        // #6 - set SCHEMAS to EService object
							    serviceObjectWorker.setSchemaArrayList(schemasArrayListTemp);
			   
							    serviceArrayList.set(i, serviceObjectWorker);
		       
		     }// for all serviceworker
		
		 	
		 }// loadCompleteServicesWSDLInformation
		 
		 
		 public static void wsdlToXSD(InputStream is , OutputStream os) {
				DocumentBuilder builder;
				try {
						builder = getDocBuilder();
						Document wsdlDoc = builder.parse(is);
						Document xsdDoc = getDocBuilder().newDocument();
						populateXsdDoc(wsdlDoc, xsdDoc);
						writeToStream(xsdDoc,os);
						is.close();
						os.flush();
						os.close();
					} catch (Exception e) {
						StringWriter sw = new StringWriter();
						e.printStackTrace(new PrintWriter(sw));
						log4j.debug(sw.toString());
					}
		}//
		 
		private static void populateXsdDoc(Document wsdlDoc, Document xsdDoc) {
					Element root = xsdDoc.createElement("xsd:schema");
					root.setAttribute("xmlns:xsd", "http://www.w3.org/2001/XMLSchema");
					xsdDoc.appendChild(root);
					
					//Element element = wsdlDoc.getDocumentElement();
					Node node = wsdlDoc.getElementsByTagName("types").item(0);
					
					NodeList lst = null;
					
					if (node != null){
							lst = node.getChildNodes();
							for (int i = 0; i < lst.getLength(); i++) {
										Node nd = lst.item(i);
										if(nd.getNodeName().equals("xsd:schema") | nd.getNodeName().equals("schema") |nd.getNodeName().equals("so:schema") | nd.getNodeName().equals("xs:schema")){
											NodeList xsdNodes = nd.getChildNodes();
											for (int j = 0; j < xsdNodes.getLength(); j++) {
												Node temp = xsdNodes.item(j);
												Node toAdded = xsdDoc.importNode(temp, true);
												root.appendChild(toAdded);
											}
										}
							}// for
							
					}// if not null
					
					
					node = wsdlDoc.getElementsByTagName("WL5G3N0:types").item(0);
					
					if (node!= null){
							lst = node.getChildNodes();
							for (int i = 0; i < lst.getLength(); i++) {
										Node nd = lst.item(i);
										if(nd.getNodeName().equals("xsd:schema") | nd.getNodeName().equals("schema") |nd.getNodeName().equals("so:schema") | nd.getNodeName().equals("xs:schema")){
											NodeList xsdNodes = nd.getChildNodes();
											for (int j = 0; j < xsdNodes.getLength(); j++) {
												Node temp = xsdNodes.item(j);
												Node toAdded = xsdDoc.importNode(temp, true);
												root.appendChild(toAdded);
											}
										}
							}// for
					}
					
					
					node = wsdlDoc.getElementsByTagName("s0:types").item(0);
					
					if (node != null){
							lst = node.getChildNodes();
							for (int i = 0; i < lst.getLength(); i++) {
										Node nd = lst.item(i);
										if(nd.getNodeName().equals("xsd:schema") | nd.getNodeName().equals("schema") |nd.getNodeName().equals("so:schema") | nd.getNodeName().equals("xs:schema")){
											NodeList xsdNodes = nd.getChildNodes();
											for (int j = 0; j < xsdNodes.getLength(); j++) {
												Node temp = xsdNodes.item(j);
												Node toAdded = xsdDoc.importNode(temp, true);
												root.appendChild(toAdded);
											}
										}
							}// for
					}// if not null
					
					node = wsdlDoc.getElementsByTagName("types").item(0);
					if (node != null){
							lst = node.getChildNodes();
							for (int i = 0; i < lst.getLength(); i++) {
										Node nd = lst.item(i);
										if(nd.getNodeName().equals("xsd:schema") | nd.getNodeName().equals("schema") |nd.getNodeName().equals("so:schema") | nd.getNodeName().equals("xs:schema")){
											NodeList xsdNodes = nd.getChildNodes();
											for (int j = 0; j < xsdNodes.getLength(); j++) {
												Node temp = xsdNodes.item(j);
												Node toAdded = xsdDoc.importNode(temp, true);
												root.appendChild(toAdded);
											}
										}
							}// for
					}
					
					
		}//populateXsdDoc
		
		private static void writeToStream(Document document, OutputStream os) throws IOException {
						DOMImplementationLS domImplementationLS = (DOMImplementationLS) document.getImplementation();
						LSSerializer lsSerializer = domImplementationLS.createLSSerializer();
						String xsd = lsSerializer.writeToString(document);
						
						PrintWriter pw = new PrintWriter(os);
						// logger.debug(xsd);
						pw.write(xsd);
						pw.flush();
						
		}//writeToStream
		
		
		private static DocumentBuilder getDocBuilder() throws ParserConfigurationException{
						DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
						DocumentBuilder builder = factory.newDocumentBuilder();
						return builder;
		}//getDocBuilder
 
 		public static String readFile(String path, Charset encoding) throws IOException{
 			
		  byte[] encoded = Files.readAllBytes(Paths.get(path));
		  return new String(encoded, encoding);
		
 		}// readFile
 		
 		
 		public String readFileURL(String _urlPath) throws Exception {
 			
 			url1 	= new URL(_urlPath);
 			
 	        in = new BufferedReader( new InputStreamReader(url1.openStream()));
 	        
 	         returnString		="";
 	         inputLine1 		="";
 	        
 	        while ((inputLine1 = in.readLine()) != null){
 	        	returnString = returnString + inputLine1 +"\r\n";;
 	        }// while
 	        
 	        in.close();
 	        	
 			return returnString;
 	 	
 		}// readFileURL
 		
 		
 		
 		//
 		public String getFileContentFromHttpsURL(HttpsURLConnection _httpsConnection) throws Exception {
 			
 			 buferredReader = new BufferedReader(new InputStreamReader(_httpsConnection.getInputStream()));
 			 
 		     // init temp var
 	         returnString		="";
 	         inputLine1 		="";
 	        
 	         while ((inputLine1 = buferredReader.readLine()) != null){
 	        	returnString = returnString + inputLine1 +"\r\n";;
 	         }// while
 	        
 	         in.close();
 	        	
 			 return returnString;
 	 	
 		}// getFileContentFromHttpsURL
 		
 		
 		public String getSecurityCertificatesFromHttpsURL(HttpsURLConnection _httpsConnection) throws Exception {
 			
 			// init			     
	        returnString		="";
	        EFile tempFile = new EFile();
	        
	        // new ArrayList for each invocation
	        securityCertificatesTemp = new ArrayList();
	        
	        ESecurityCertificate tempCertificate;
	        
	        try {
	        	
	        	tempFile.setResponseCode(_httpsConnection.getResponseCode());
	        	tempFile.setCipherSuite(_httpsConnection.getCipherSuite());
	        	tempFile.setExpiration(_httpsConnection.getExpiration());
	        	tempFile.setContentEncoding( _httpsConnection.getContentEncoding());
	          	tempFile.setURL(_httpsConnection.getURL().getFile());
	          	
	        	returnString	= returnString+ " Response Code : " + tempFile.getResponseCode();
	          	returnString	= returnString+ "\n";
	          	returnString	= returnString+ " Cipher Suite : " + tempFile.getCipherSuite();
	        	returnString	= returnString+ "\n";
	         	returnString  	= returnString+" HTTPS Expiration Type : " + tempFile.getExpiration();
	        	returnString 	= returnString+ "\n";        	
	        	returnString  	= returnString+" HTTPS Content Type : " + tempFile.getContentEncoding();
	        	returnString 	= returnString+ "\n"; 
	        	returnString  	= returnString+" HTTPS URL : " + tempFile.getURL();
	        	returnString 	= returnString+ "\n";
	        	
	        	Certificate[] certs = _httpsConnection.getServerCertificates();
	          		        	
	        	// loop thorugh certificates
	        	for(Certificate cert : certs){
	        		
	        		// new object every cycle - reset 
	        		tempCertificate = new ESecurityCertificate();
	        		
	        		
	        		tempCertificate.setType(cert.getType());
	        		tempCertificate.setHashCode(""+cert.hashCode());
	        		tempCertificate.setEncoded(""+cert.getEncoded());
	        		tempCertificate.setAlgorithm(""+cert.getPublicKey().getAlgorithm());
	        		tempCertificate.setFormat(""+cert.getPublicKey().getFormat());
	        		tempCertificate.setSerialVersionUID(""+(cert.getPublicKey()).serialVersionUID);
	        		
	        		returnString  = returnString+" Cert Type : " +tempCertificate.getType();
	        		returnString = returnString+ "\n";
	        		returnString = returnString+" Cert Hash Code : " + tempCertificate.getHashCode();
	        		returnString = returnString+ "\n";
	        		returnString = returnString+" Cert get encoded : " +  tempCertificate.getEncoded();
	        		returnString = returnString+ "\n";
	        		returnString = returnString+" Cert Public Key Algorithm : "  + tempCertificate.getAlgorithm();
	        		returnString = returnString+ "\n";
	                returnString = returnString+" Cert Public Key Format : "  + tempCertificate.getFormat();
	                returnString = returnString+ "\n";
	                returnString = returnString+" Cert Public Key Serial Version : "  +  tempCertificate.getSerialVersionUID();
	                returnString = returnString+ "\n";
	                
	                securityCertificatesTemp.add(tempCertificate);
	        		
	        	}//for
	        	
	        	// setup the security certificates to HTTPS File and add each EFile Instance to the global Vector -one at the time
	        	tempFile.setESecurityCertificateArrayList(securityCertificatesTemp);
	        	modelOfEFileVector.add(tempFile);
	        	 
	        	 
	        	} catch (SSLPeerUnverifiedException sslpue) {
	        		log4j.error("EORGANISM ::getSecurityCertificatesFromHttpsURL :: SSLPeerUnverifiedException" + sslpue.toString()); 
           		  	log4j.debug("EORGANISM ::getSecurityCertificatesFromHttpsURL :: SSLPeerUnverifiedException" + sslpue.toString()); 
           		  	sslpue.printStackTrace();
                	saveLogging("EORGANISM ::getSecurityCertificatesFromHttpsURL :: SSLPeerUnverifiedException: " + sslpue.toString(),"EORGANISM.EOrganismController.getSecurityCertificatesFromHttpsURL","_httpsConnection.getServerCertificates()");
            		
	        	} catch (IOException ioe){
	        		log4j.error("EORGANISM ::getSecurityCertificatesFromHttpsURL :: IOException" + ioe.toString()); 
           		  	log4j.debug("EORGANISM ::getSecurityCertificatesFromHttpsURL :: IOException" + ioe.toString()); 
           		 	saveLogging("EORGANISM ::getSecurityCertificatesFromHttpsURL :: IOException: " + ioe.toString(),"EORGANISM.EOrganismController.getSecurityCertificatesFromHttpsURL","_httpsConnection.getServerCertificates()");
                    
	        		ioe.printStackTrace();
	        	}catch (Exception e){
	        		log4j.error("EORGANISM ::getSecurityCertificatesFromHttpsURL :: Exception" + e.toString()); 
           		  	log4j.debug("EORGANISM ::getSecurityCertificatesFromHttpsURL :: Exception" + e.toString()); 
         		 	saveLogging("EORGANISM ::getSecurityCertificatesFromHttpsURL :: Exception: " + e.toString(),"EORGANISM.EOrganismController.getSecurityCertificatesFromHttpsURL","_httpsConnection.getServerCertificates()");
         	          
	        		e.printStackTrace();
	        	}//catch
	        
	       
			return returnString;
	 	
		}// getSecurityCertificatesFromHttpsURL
 	     
        public void saveWsdlMetadataToDatabase(){
        	
        	log4j.debug("EORGANISM ::saveWsdlMetadataToDatabase ::saveWsdlMetadataToDatabase:: modelOfEfileVector: size = " + modelOfEFileVector.size());
             
        	String updateFileContentIntoService="";
        	Statement updateStatementService 							=null;
        	
            PreparedStatement insertPreparedStatementMetadataService 	=null;
            PreparedStatement insertPreparedStatementService 			=null;
            PreparedStatement insertPreparedStatementFile				=null;
            int resultSetUpdateService = 0;
              
            EService serviceObjectWorker;
            
            //loadWSDLLoaderFile();
            //
            databaseInitializer();
            checkConnection();
             
            //java.sql.Date today = new java.sql.Date(System.currentTimeMillis());
            
            int serviceModelId  =0;
            
            int eid  			=0;	
             
            try{
             		updateStatementService                  = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);    

                    insertPreparedStatementMetadataService  = connection.prepareStatement("INSERT INTO EMETADATA_SERVICE(EMETADATA_SERVICE_ID,EMETADATA_SERVICE_NAME,EMETADATA_SERVICE_TYPE,EMETADATA_SERVICE_DESCRIPTION,EMETADATA_COMPONENT_NAME, EMETADATA_DATABASE_NAME,EMETADATA_APPLICATION_NAME,EMETADATA_SERVICE_LOCATION,EMETADATA_SERVICE_DESCRIPTOR,EMETADATA_LAST_UPDATED_DATE,EMETADATA_LAST_UPDATED_USER,EMETADATA_LAST_UPDATED_APP) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
                    
                    //insertPreparedStatementService          = connection.prepareStatement("INSERT INTO ESERVICE(ESERVICE_ID,ESERVICE_NAME,ESERVICE_DESCRIPTION,EORGANISM_EID,EAPPLICATION_ID_FK, EAPPLICATION_NAME_FK, ESERVICE_TYPE,ECOMPONENT_NAME_FK,ESERVICE_FILE_CONTENT_BLOB,ESERVICE_METADATA_URL,ESERVICE_FILE_LOCAL_PATH,ESERVICE_FILE_WEB_PATH,ESERVICE_FILE_WEBSITE_PATH,ESERVICE_DESCRIPTOR,ESERVICE_LOCATION,CREATED_TIMESTAMP,ESERVICE_FILE_DB_PATH,ESERVICE_FILE_NAME,ESERVICE_FILE_CONS,SECURITY_CERTIFICATE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    
                    insertPreparedStatementService          = connection.prepareStatement("INSERT INTO ESERVICE(ESERVICE_ID,ESERVICE_NAME,ESERVICE_DESCRIPTION,EORGANISM_EID,EAPPLICATION_ID_FK, EAPPLICATION_NAME_FK, ESERVICE_TYPE,ECOMPONENT_NAME_FK,ESERVICE_FILE_CONTENT_BLOB,ESERVICE_METADATA_URL,ESERVICE_FILE_LOCAL_PATH,ESERVICE_FILE_WEB_PATH,ESERVICE_FILE_WEBSITE_PATH,ESERVICE_DESCRIPTOR,ESERVICE_LOCATION,CREATED_TIMESTAMP,ESERVICE_FILE_DB_PATH,ESERVICE_FILE_NAME,ESERVICE_FILE_CONS,SECURITY_CERTIFICATE,EENVIRONMENT_NAME_FK,SYSTEM_NAME,SYSTEM_ID_FK,APP_ID_FK,COMP_ID_FK,METADATA_SUBTYPE,SERVICE_HOST_NAME,SERVICE_HOST_IP,XSD_FILE_NAME,XSD_FILE_WEB_LOCATION,XSD_FILE_DB_LOCATION,XSD_FILE_LOCAL_LOCATION) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    
                    
                    insertPreparedStatementFile          	= connection.prepareStatement("INSERT INTO EFILE_TABLE (EFILE_ID,EFILE_NAME,CREATED_DATE,EFILE_CONTENT) VALUES (?,?,?,?)");
                    
                       
                    for (int i = 1; i < serviceArrayList.size(); i++) {  
                          serviceObjectWorker    = new EService();
                          serviceObjectWorker = (EService)serviceArrayList.get(i);
                             
                        //12
                        //SELECT EMETADATA_SERVICE_ID,EMETADATA_SERVICE_NAME,
                        //EMETADATA_SERVICE_TYPE,EMETADATA_SERVICE_DESCRIPTION,
                        // EMETADATA_COMPONENT_NAME,EMETADATA_DATABASE_NAME,
                        // EMETADATA_APPLICATION_NAME,EMETADATA_SERVICE_LOCATION,
                        //EMETADATA_SERVICE_DESCRIPTOR,EMETADATA_LAST_UPDATED_DATE,
                        //EMETADATA_LAST_UPDATED_USER,EMETADATA_LAST_UPDATED_APP FROM EMETADATA_SERVICE;                 
                        insertPreparedStatementMetadataService.setInt(1, getSequenceNextValue("EMETADATA_SERVICE","EMETADATA_SERVICE_ID"));//id
                        insertPreparedStatementMetadataService.setString(2, serviceObjectWorker.getServiceName());//NAME
                        insertPreparedStatementMetadataService.setString(3, serviceObjectWorker.getServiceType());//type
                        //insertPreparedStatementMetadataService.setString(4, serviceObjectWorker.getServiceDescription());//desc
                        insertPreparedStatementMetadataService.setString(4, serviceObjectWorker.getApplication()+"."+serviceObjectWorker.getComponent()+"."+serviceObjectWorker.getServiceName());//desc
                        
                        insertPreparedStatementMetadataService.setString(5, serviceObjectWorker.getComponent());//comp
                        insertPreparedStatementMetadataService.setString(6, "DB:"+ serviceObjectWorker.getApplication()+":"+ serviceObjectWorker.getDatabase());//db
                        insertPreparedStatementMetadataService.setString(7, serviceObjectWorker.getApplication());//app
                        insertPreparedStatementMetadataService.setString(8, serviceObjectWorker.getServiceLocation());//location
                        insertPreparedStatementMetadataService.setString(9, serviceObjectWorker.getDescriptorFile());//WSDL
                        insertPreparedStatementMetadataService.setDate(10, today);//created date
                        insertPreparedStatementMetadataService.setString(11,   "Alex - SUPER ADMIN");//last updated user
                        insertPreparedStatementMetadataService.setString(12,   "CSV FIlE loader");//last updated app
                        
                        // new fields dec 29
                        
                         
                        result = insertPreparedStatementMetadataService.executeUpdate();
                                               
                        //log4j.debug("EORGANISM ::saveWsdlMetadataToDatabase ::saveWsdlMetadataToDatabase:: insertPreparedStatementMetadataTable: EMETADATA_SERVICE: " +serviceObjectWorker.getApplication() +" >> "+  serviceObjectWorker.getServiceName() +" >> "+serviceObjectWorker.getComponent()+" >> "+serviceObjectWorker.getServiceLocation());
                        
                        //ESERVICE_ID, "
	                    //ESERVICE_NAME, "
	                    //ESERVICE_DESCRIPTION, "
	                    //EORGANISM_EID,"
	                    //EAPPLICATION_ID_FK, "
	                    //EAPPLICATION_NAME_FK, "
	                    //ESERVICE_TYPE,"
	                    //ECOMPONENT_NAME_FK,"
	                    //ESERVICE_LOCATION,"
	                    //EENVIRONMENT_NAME_FK,) VALUES (?,?,?,?,?,?,?,?,?,?)");
                        serviceModelId	= getSequenceNextValue("ESERVICE","ESERVICE_ID");
                        eid 			= getSequenceNextValue(ConfigFrame.EORGANISM_OBJECTID_MAPPING,ConfigFrame.EORGANISM_OBJECTID);
                    	
                        //ESERVICE_ID, ESERVICE_NAME, ESERVICE_DESCRIPTION, EORGANISM_EID, EAPPLICATION_ID_FK, EAPPLICATION_NAME_FK, ESERVICE_TYPE,ECOMPONENT_NAME_FK) VALUES (?,?,?,?,?,?,?,?)");
                            
                        insertPreparedStatementService.setInt(1, serviceModelId);
                        insertPreparedStatementService.setString(2, serviceObjectWorker.getServiceName());
                        //insertPreparedStatementService.setString(3, serviceObjectWorker.getServiceDescription());
                        insertPreparedStatementService.setString(3, serviceObjectWorker.getApplication()+"."+serviceObjectWorker.getComponent()+"."+serviceObjectWorker.getServiceName());// description
                        
                        insertPreparedStatementService.setInt(4, eid);// eid
                        insertPreparedStatementService.setInt(5, 1);// app_id
                        insertPreparedStatementService.setString(6, serviceObjectWorker.getApplication());// app_name
                        insertPreparedStatementService.setString(7, serviceObjectWorker.getServiceType());// service type
                        insertPreparedStatementService.setString(8, serviceObjectWorker.getComponent());// service comp
                        //insertPreparedStatementService.setString(9, serviceObjectWorker.getServiceFileContent());// WSDL String Content
                        insertPreparedStatementService.setString(9, "FILE CONTENT");// WSDL String Content
                        insertPreparedStatementService.setString(10, serviceObjectWorker.getServiceLocation());//location
                        insertPreparedStatementService.setString(11, serviceObjectWorker.getLocalFilePath());//LOCAL FILE PATH LOCATION
                        insertPreparedStatementService.setString(12, serviceObjectWorker.getWebFilePath());//LOCAL FILE PATH LOCATION
                        insertPreparedStatementService.setString(13, serviceObjectWorker.getWebsiteUrl());//website LOCATION
                        //
                        insertPreparedStatementService.setString(14, serviceObjectWorker.getDescriptorFile());//SERVICE_DESCRIPTOR
                        insertPreparedStatementService.setString(15, serviceObjectWorker.getServiceLocation());//SERVICE_LOCATION
                        
                        insertPreparedStatementService.setTimestamp(16, new java.sql.Timestamp(System.currentTimeMillis()));//timestamp 
                        insertPreparedStatementService.setString(17, serviceObjectWorker.getDBPath());//File DB path
                        insertPreparedStatementService.setString(18, serviceObjectWorker.getFileName());//File name
                        insertPreparedStatementService.setString(19, serviceObjectWorker.getConsolidatedFileName());//File name consolidated
                        insertPreparedStatementService.setString(20, serviceObjectWorker.getCertificateInformation());//security certificate
                        insertPreparedStatementService.setString(21, serviceObjectWorker.getEnvironment());//environment
                        
                        // new fields Dec 29 - SYSTEM_NAME,SYSTEM_ID_FK,APP_ID_FK,COMPONENT_ID_FK, METADATA_SUBTYPE
                        insertPreparedStatementService.setString(22, serviceObjectWorker.getSystem());//SYSTEM
                        insertPreparedStatementService.setString(23, serviceObjectWorker.getSystemID());//SYSTEM_ID_FK
                        insertPreparedStatementService.setString(24, serviceObjectWorker.getApplicationID());//APP_ID_FK
                        insertPreparedStatementService.setString(25, serviceObjectWorker.getComponentID());//COMPONENT_ID_FK
                        insertPreparedStatementService.setString(26, serviceObjectWorker.getServiceSubtype());//METADATA_SUBTYPE
                        insertPreparedStatementService.setString(27, serviceObjectWorker.getServiceHost());//SERVICE_HOST_NAME
                        insertPreparedStatementService.setString(28, serviceObjectWorker.getServiceIP());//SERVICE_HOST_IP
                        //xsd
                        insertPreparedStatementService.setString(29, serviceObjectWorker.getGeneratedXSDFileName());//XSD_FILE_NAME
                        insertPreparedStatementService.setString(30, serviceObjectWorker.getGeneratedXSDFileLocation());//XSD_FILE_WEB_LOCATION
                        insertPreparedStatementService.setString(31, serviceObjectWorker.getGeneratedXSDFileLocationDB());//XSD_FILE_DB_LOCATION
                        insertPreparedStatementService.setString(32, serviceObjectWorker.getGeneratedXSDLocalFileLocation());//XSD_FILE_LOCAL_LOCATION
                                                 
                        result = insertPreparedStatementService.executeUpdate();
                        
                        // SAVE EORGANISM
                        saveEOrganismTable(eid,serviceObjectWorker.getServiceName(),"ESERVICE","Service", serviceModelId);
                        
                        // update and add file content   
                        updateFileContentIntoService="";
                        
                        
                        log4j.debug("EORGANISM ::saveWsdlMetadataToDatabase ::saveWsdlMetadataToDatabase:: updateStatementService: ESERVICE: file name is " + serviceObjectWorker.getFileName());
                        
                        //LOAD WSDL XML WEB SERVICE DESCRIPTOR FILE
                        resultSetUpdateService 	= updateStatementService.executeUpdate("UPDATE ESERVICE SET SERVICE_DESCRIPTOR_CONTENT_TEXT = LOAD_FILE('"+serviceObjectWorker.getDBPath()+"') WHERE ESERVICE_ID ='"+serviceModelId+"'");	
                        
                        resultSetUpdateService 	= updateStatementService.executeUpdate("UPDATE ESERVICE SET SERVICE_DESCRIPTOR_CONTENT_BLOB = LOAD_FILE('"+serviceObjectWorker.getDBPath()+"') WHERE ESERVICE_ID ='"+serviceModelId+"'");	
                        
                        //LOAD XSD SCHEMA FILE
                        resultSetUpdateService 	= updateStatementService.executeUpdate("UPDATE ESERVICE SET XSD_DESCRIPTOR_CONTENT_TEXT = LOAD_FILE('"+serviceObjectWorker.getGeneratedXSDFileLocationDB()+"') WHERE ESERVICE_ID ='"+serviceModelId+"'");	
                        
                        resultSetUpdateService 	= updateStatementService.executeUpdate("UPDATE ESERVICE SET XSD_DESCRIPTOR_CONTENT_BLOB = LOAD_FILE('"+serviceObjectWorker.getGeneratedXSDFileLocationDB()+"') WHERE ESERVICE_ID ='"+serviceModelId+"'");	
                     
                        
                        log4j.debug("EORGANISM ::saveWsdlMetadataToDatabase ::saveWsdlMetadataToDatabase:: updateStatementService: ESERVICE: " +"UPDATE ESERVICE SET SERVICE_DESCRIPTOR_CONTENT_TEXT = LOAD_FILE('"+serviceObjectWorker.getConsolidatedFileName()+"') WHERE ESERVICE_ID ='"+serviceModelId+"'");
                        
                        
                        log4j.debug("EORGANISM ::saveWsdlMetadataToDatabase ::saveWsdlMetadataToDatabase:: updateStatementService: ESERVICE: " +serviceObjectWorker.getApplication() +" >> "+  serviceObjectWorker.getServiceName() +" >> "+serviceObjectWorker.getComponent()+" >> load file from "+serviceObjectWorker.getLocalFilePath());
                         
                        // if location is not null attempt to load the WSDL of FILE from that location
                        if(serviceObjectWorker.getServiceLocation()!=null && serviceObjectWorker.getServiceLocation()!=""&& (serviceObjectWorker.getServiceLocation()).length()>4){
                            	loadOperationsAttributesMetadataToDB(serviceObjectWorker);
                            	//log4j.debug("EORGANISM ::saveWsdlMetadataToDatabase ::########### Operations saveWsdlMetadataToDatabase::loadOperationsAttributesMetadataToDB(serviceObjectWorker) load WSDL from location:"+ serviceObjectWorker.getServiceLocation());
                        
	                            // attempt to load the file from URL and load location; and insert the file content into the DB
	                           /* 
	                            try {
	                            	
	                            	url            = new URL(serviceObjectWorker.getServiceLocation());
                                    urlConnection  = url.openConnection();
	                           	
	                            	bufferedReader = new BufferedReader( new InputStreamReader(urlConnection.getInputStream()));
	                                inputLine ="";
             
                                    //save to this filename to local file system
                                   
                                    file = new File(fileNameURL);
            
                                    if (!file.exists()) {
                                            file.createNewFile();
                                    }
             
                                    //use FileWriter to write file
                                    fileWriter              	= new FileWriter(file.getAbsoluteFile());
                                    bufferWritter        		= new BufferedWriter(fileWriter);
             
                                    while ((inputLine = bufferedReader.readLine()) != null) {
                                            bufferWritter.write(inputLine);
                                    }// while
             
                                    bufferWritter.close();
                                    bufferedReader.close();
			                           
                                    // this is what we insert into DB; loaded from local filesystem
                                    file 			= new File(fileNameURL);
                                    inputStream 	= new FileInputStream(file);
                                    
                                     
			                    
	                            } catch (IOException ex) {
	                                ex.printStackTrace();
	                            } catch (Exception e1) {
	                                e1.printStackTrace();
	                            } 
	                            
	                            //INSERT INTO EFILE_TABLE (EFILE_ID, EFILE_NAME, CREATED_DATE, EFILE_CONTENT) VALUES (?,?,?,?)
	                            insertPreparedStatementFile.setInt(1, getSequenceNextValue("EFILE_TABLE","EFILE_ID"));//id
	                            insertPreparedStatementFile.setString(2, serviceObjectWorker.getServiceLocation());//file name
	                            insertPreparedStatementFile.setDate(3, today);//date
	                            //insertPreparedStatementFile.setBlob(4, inputStream);//content content from URL for WSDL
	                            insertPreparedStatementFile.setBinaryStream(4, inputStream, file.length());//content content from URL for WSDL
	                             result = insertPreparedStatementFile.executeUpdate();
	                            log4j.debug("EORGANISM ::saveWsdlMetadataToDatabase ::########### Operations saveWsdlMetadataToDatabase:: INSERT FILE INTO DB" + serviceObjectWorker.getServiceLocation());
                                 */
                        }//
                          
                           
                    }  // i for object in ArrayList
                
                    //CLOSE
                    updateStatementService.close();
                    insertPreparedStatementMetadataService.close(); 
                    insertPreparedStatementService.close(); 
                    insertPreparedStatementFile.close(); 
                    
                    connection.close();
                    
                    //log4j.debug("EORGANISM ::saveWsdlMetadataToDatabase :: OK");      
                    //JOptionPane.showMessageDialog( null, "EORGANISM :: saveWsdlMetadataToDatabase OK");
                
                } catch(SQLException sqle){
                    //JOptionPane.showMessageDialog( null,"SQLException  "    + sqle.toString()+"\n");
                	log4j.error("EORGANISM ::saveWsdlMetadataToDatabase :: SQLException: " + sqle.toString());
                   	log4j.debug("EORGANISM ::saveWsdlMetadataToDatabase :: SQLException: " + sqle.toString());
                  
                } catch(Exception e){
                    //JOptionPane.showMessageDialog( null, "Exception  " +e.toString()+"\n");
                	log4j.error("EORGANISM ::saveWsdlMetadataToDatabase :: Exception: " + e.toString());
                 	log4j.debug("EORGANISM ::saveWsdlMetadataToDatabase :: Exception: " + e.toString());
                	saveLogging("EORGANISM ::saveWsdlMetadataToDatabase :: Exception: " + e.toString(),"EORGANISM.EOrganismController.saveWsdlMetadataToDatabase","serviceArrayList");
             
                }finally{
                    try{
                         
                         if (insertPreparedStatementMetadataService!=null){ insertPreparedStatementMetadataService.close(); }
                         if (insertPreparedStatementService!=null){ insertPreparedStatementService.close(); }
                         if (connection!=null){connection.close(); }
                         
                    
                    }catch(SQLException sqle2){
                    	log4j.error("EORGANISM ::saveWsdlMetadataToDatabase :: SQLException:finally: " + sqle2.toString());
                    	log4j.debug("EORGANISM ::saveWsdlMetadataToDatabase :: SQLException:finally: " + sqle2.toString());
                    	saveLogging("EORGANISM ::saveWsdlMetadataToDatabase :: SQLException:finally: " + sqle2.toString(),"EORGANISM.EOrganismController.saveWsdlMetadataToDatabase","serviceArrayList");
                        
                    }//
                
                }//finally
              
        }// end saveWsdlMetadataToDatabase
         
        public void saveCTRLMMetadataToDatabase(){
            PreparedStatement insertPreparedStatementMetadataService 	=null;
            PreparedStatement insertPreparedStatementService 			=null;
            
            EService serviceObjectWorker;
             
            databaseInitializer();
            checkConnection();
            
            int serviceModelId  =0;
            int eid  			=0;	
             
            //java.sql.Date today = new java.sql.Date(System.currentTimeMillis());
            try{
                    insertPreparedStatementMetadataService  	= connection.prepareStatement("INSERT INTO EMETADATA_SERVICE(EMETADATA_SERVICE_ID,EMETADATA_SERVICE_NAME,EMETADATA_SERVICE_TYPE,EMETADATA_SERVICE_DESCRIPTION,EMETADATA_COMPONENT_NAME, EMETADATA_DATABASE_NAME,EMETADATA_APPLICATION_NAME,EMETADATA_SERVICE_LOCATION,EMETADATA_SERVICE_DESCRIPTOR,EMETADATA_LAST_UPDATED_DATE,EMETADATA_LAST_UPDATED_USER,EMETADATA_LAST_UPDATED_APP) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
                    insertPreparedStatementService     			= connection.prepareStatement("INSERT INTO ESERVICE (ESERVICE_ID, ESERVICE_NAME, ESERVICE_DESCRIPTION, EORGANISM_EID, EAPPLICATION_ID_FK, EAPPLICATION_NAME_FK, ESERVICE_TYPE,ECOMPONENT_NAME_FK,EMETADATA_LOAD_LOCATION_URL,EMETADATA_DEPLOY_LOCATION,CREATED_TIMESTAMP) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
                     
                    for (int i = 1; i < serviceArrayList.size(); i++) {  
                          serviceObjectWorker    = new EService();
                          serviceObjectWorker = (EService)serviceArrayList.get(i);
                              
                        // EMETADATA_SERVICE(EMETADATA_SERVICE_ID,EMETADATA_SERVICE_NAME,EMETADATA_SERVICE_TYPE,EMETADATA_SERVICE_DESCRIPTION,EMETADATA_COMPONENT_NAME, EMETADATA_DATABASE_NAME,EMETADATA_APPLICATION_NAME,EMETADATA_SERVICE_LOCATION,EMETADATA_SERVICE_DESCRIPTOR,EMETADATA_LAST_UPDATED_DATE,EMETADATA_LAST_UPDATED_USER,EMETADATA_LAST_UPDATED_APP)  
                           
                        insertPreparedStatementMetadataService.setInt(1, getSequenceNextValue("EMETADATA_SERVICE","EMETADATA_SERVICE_ID"));//id
                        insertPreparedStatementMetadataService.setString(2, serviceObjectWorker.getServiceName());//NAME
                        insertPreparedStatementMetadataService.setString(3, serviceObjectWorker.getServiceType());//type
                        insertPreparedStatementMetadataService.setString(4, serviceObjectWorker.getServiceDescription());//desc
                        insertPreparedStatementMetadataService.setString(5, serviceObjectWorker.getComponent());//comp
                        insertPreparedStatementMetadataService.setString(6, "DB:"+ serviceObjectWorker.getApplication()+":"+ serviceObjectWorker.getDatabase());//db
                        insertPreparedStatementMetadataService.setString(7, serviceObjectWorker.getApplication());//app
                        insertPreparedStatementMetadataService.setString(8, serviceObjectWorker.getServiceLocation());//location
                        insertPreparedStatementMetadataService.setString(9, serviceObjectWorker.getDescriptorFile());//WSDL
                        insertPreparedStatementMetadataService.setDate(10, today);//created date
                        insertPreparedStatementMetadataService.setString(11,   "Alex - SUPER ADMIN");//last updated user
                        insertPreparedStatementMetadataService.setString(12,   "CSV FIlE loader");//l;ast updated app
                        
                         result = insertPreparedStatementMetadataService.executeUpdate();
                        //log4j.debug("EORGANISM ::saveCTRLMMetadataToDatabase :: insertPreparedStatementMetadataTable:EORGANISM: " +serviceObjectWorker.getApplication() +" >> "+  serviceObjectWorker.getServiceName() +" >> "+serviceObjectWorker.getComponent()+" >> "+serviceObjectWorker.getServiceLocation());
                        
                         //  ESERVICE (ESERVICE_ID, ESERVICE_NAME, ESERVICE_DESCRIPTION, EORGANISM_EID, EAPPLICATION_ID_FK, EAPPLICATION_NAME_FK, ESERVICE_TYPE,ECOMPONENT_NAME_FK) VALUES (?,?,?,?,?,?,?,?)");
                        
                        serviceModelId	=  getSequenceNextValue("ESERVICE","ESERVICE_ID");
                        eid 			=  getSequenceNextValue(ConfigFrame.EORGANISM_OBJECTID_MAPPING,ConfigFrame.EORGANISM_OBJECTID);
                      
                        /*
                        ESERVICE_ID, 
                        ESERVICE_NAME,
                        ESERVICE_DESCRIPTION, 
                        EORGANISM_EID, 
                        EAPPLICATION_ID_FK, 
                        EAPPLICATION_NAME_FK, 
                        ESERVICE_TYPE,
                        ECOMPONENT_NAME_FK,
                        EMETADATA_LOAD_LOCATION_URL,
                        EMETADATA_DEPLOY_LOCATION,
                        CREATED_TIMESTAMP
                        ?,?,?,?,?,?,?,?,?,?,?
                        */
                                                       
                        insertPreparedStatementService.setInt(1, serviceModelId);
                        insertPreparedStatementService.setString(2, serviceObjectWorker.getServiceName());
                        insertPreparedStatementService.setString(3, serviceObjectWorker.getServiceDescription());
                        insertPreparedStatementService.setInt(4, eid);// eid
                        insertPreparedStatementService.setInt(5, 1);// app_id
                        insertPreparedStatementService.setString(6, serviceObjectWorker.getApplication());// app_name
                        insertPreparedStatementService.setString(7, serviceObjectWorker.getServiceType());// service type
                        insertPreparedStatementService.setString(8, serviceObjectWorker.getComponent());// service type
                        insertPreparedStatementService.setString(9, serviceObjectWorker.getServiceLocation());// service LOCATION
                        insertPreparedStatementService.setString(10, serviceObjectWorker.getDescriptorFile());// wsdl
                        
                        insertPreparedStatementService.setTimestamp(11, new java.sql.Timestamp(System.currentTimeMillis()));//timestamp 
                         
                        result = insertPreparedStatementService.executeUpdate();
                         // SAVE EORGANISM
                        saveEOrganismTable(eid,serviceObjectWorker.getServiceName(),"ESERVICE","Service", serviceModelId);
                        
                    }  // i for object in ArrayList
                
                    //CLOSE
                    insertPreparedStatementMetadataService.close(); 
                    insertPreparedStatementService.close(); 
                    connection.close();
                    
                    log4j.debug("EORGANISM ::saveCTRLMMetadataToDatabase ::  OK");      
                    //JOptionPane.showMessageDialog( null, "EORGANISM :: saveCTRLMMetadataToDatabase OK");
                
                } catch(SQLException sqle){
                    //JOptionPane.showMessageDialog( null,"SQLException  "    + sqle.toString()+"\n");
                	log4j.error("EORGANISM ::saveCTRLMMetadataToDatabase :: SQLException: " + sqle.toString());
                	log4j.debug("EORGANISM ::saveCTRLMMetadataToDatabase :: SQLException: " + sqle.toString());
                 	saveLogging("EORGANISM ::saveCTRLMMetadataToDatabase :: SQLException: " + sqle.toString(),"EORGANISM.EOrganismController.saveCTRLMMetadataToDatabase","serviceArrayList");
                    
                } catch(Exception e){
                    //JOptionPane.showMessageDialog( null, "Exception  " +e.toString()+"\n");
                	log4j.error("EORGANISM ::saveCTRLMMetadataToDatabase :: Exception: " + e.toString());
                	log4j.debug("EORGANISM ::saveCTRLMMetadataToDatabase :: Exception: " + e.toString());
                	saveLogging("EORGANISM ::saveCTRLMMetadataToDatabase :: Exception: " + e.toString(),"EORGANISM.EOrganismController.saveCTRLMMetadataToDatabase","serviceArrayList");
               
                }finally{
                    try{
                         
                         if (insertPreparedStatementMetadataService!=null){ insertPreparedStatementMetadataService.close(); }
                         if (insertPreparedStatementService!=null){ insertPreparedStatementService.close(); }
                         if (connection!=null){connection.close(); }
                    
                    }catch(SQLException sqle2){
                    	log4j.error("EORGANISM ::saveCTRLMMetadataToDatabase :: SQLException:finally: " + sqle2.toString());
                    	log4j.debug("EORGANISM ::saveCTRLMMetadataToDatabase :: SQLException:finally: " + sqle2.toString());
                    	saveLogging("EORGANISM ::saveCTRLMMetadataToDatabase :: SQLException:SQLException:finally: " + sqle2.toString(),"EORGANISM.EOrganismController.saveCTRLMMetadataToDatabase","serviceArrayList");
                        
                    }//
                
                }//finally
             
            
        }// end saveCTRLMMetadataToDatabase
        
        public void saveMetadataTableFromCSVToDatabase(){
        	
            PreparedStatement insertPreparedStatementMetadataTable =null;
            
            ETable tableObjectWorker;
             
            databaseInitializer();
            checkConnection();
             
            int tableModelId  	=0;
            int eid  			=0;	
            //java.sql.Date today = new java.sql.Date(System.currentTimeMillis());
            
            try{
                      
                    insertPreparedStatementMetadataTable = connection.prepareStatement("INSERT INTO EMETADATA_TABLE(EMETADATA_TABLE_ID,EMETADATA_TABLE_NAME,EMETADATA_TABLE_OWNER,EMETADATA_TABLE_STATUS,EMETADATA_DATABASE_NAME,EMETADATA_COMPONENT_NAME,EMETADATA_APPLICATION_NAME,EMETADATA_TABLE_CREATED,EMETADATA_LAST_UPDATED_DATE,EORGANISM_ID) VALUES (?,?,?,?,?,?,?,?,?,?)");
                    
                    for (int i = 1; i < tableArrayList.size(); i++) {  
                          
                    		tableObjectWorker    = new ETable();
                    		tableObjectWorker = (ETable)tableArrayList.get(i);
                    		
                    		tableModelId 	 = getSequenceNextValue("EMETADATA_TABLE","EMETADATA_TABLE_ID");
                    		eid 			=  getSequenceNextValue(ConfigFrame.EORGANISM_OBJECTID_MAPPING,ConfigFrame.EORGANISM_OBJECTID);
                                         
                            insertPreparedStatementMetadataTable.setInt(1, tableModelId);//id
                            insertPreparedStatementMetadataTable.setString(2, tableObjectWorker.getName());      //name
                            insertPreparedStatementMetadataTable.setString(3, tableObjectWorker.getOwner());     //owner 
                            insertPreparedStatementMetadataTable.setString(4, tableObjectWorker.getStatus());    //status
                            
                            insertPreparedStatementMetadataTable.setString(5, tableObjectWorker.getDatabase());
                            insertPreparedStatementMetadataTable.setString(6, tableObjectWorker.getComponent());
                            insertPreparedStatementMetadataTable.setString(7, tableObjectWorker.getApplication());
                           
                            insertPreparedStatementMetadataTable.setDate(8, today);
                            insertPreparedStatementMetadataTable.setDate(9, today);                              //last updated date
                            insertPreparedStatementMetadataTable.setInt(10, eid);                                //EORGANISM_ID
                                
                            result = insertPreparedStatementMetadataTable.executeUpdate();
                            //log4j.debug("EORGANISM ::saveMetadataTableFromCSVToDatabase :: :: " +tableObjectWorker.getName());
                            
                            // SAVE EORGANISM
                            saveEOrganismTable(eid,tableObjectWorker.getName(),"EMETADATA_TABLE","Table", tableModelId);
                         
                    }  // i for object in ArrayList
                
                    //CLOSE
                    insertPreparedStatementMetadataTable.close(); 
                    connection.close();
                    log4j.debug("EORGANISM ::saveMetadataTableFromCSVToDatabase ::  OK");      
                    //JOptionPane.showMessageDialog( null, "EORGANISM :: saveMetadataTableFromCSVToDatabase OK");
                
                } catch(SQLException sqle){
                    //JOptionPane.showMessageDialog( null,"SQLException  "    + sqle.toString()+"\n");
                	log4j.error("EORGANISM ::saveMetadataTableFromCSVToDatabase :: SQLException: " + sqle.toString());
                	log4j.debug("EORGANISM ::saveMetadataTableFromCSVToDatabase :: SQLException: " + sqle.toString());
                   	saveLogging("EORGANISM ::saveMetadataTableFromCSVToDatabase :: SQLException: " + sqle.toString(),"EORGANISM.EOrganismController.saveMetadataTableFromCSVToDatabase","tableArrayList");
                 	
                } catch(Exception e){
                    //JOptionPane.showMessageDialog( null, "Exception  " +e.toString()+"\n");
                	log4j.error("EORGANISM ::saveMetadataTableFromCSVToDatabase :: Exception: " + e.toString());
                	log4j.debug("EORGANISM ::saveMetadataTableFromCSVToDatabase :: Exception: " + e.toString());
                 	saveLogging("EORGANISM ::saveMetadataTableFromCSVToDatabase :: Exception: " + e.toString(),"EORGANISM.EOrganismController.saveMetadataTableFromCSVToDatabase","tableArrayList");
                    
                }finally{
                    try{
                    	 if (insertPreparedStatementMetadataTable!=null){insertPreparedStatementMetadataTable.close(); }
                         if (connection!=null){connection.close(); }
                      
                    }catch(SQLException sqle2){
                    	log4j.error("EORGANISM ::saveMetadataTableFromCSVToDatabase :: SQLException:finally: " + sqle2.toString());
                    	log4j.debug("EORGANISM ::saveMetadataTableFromCSVToDatabase :: SQLException:finally: " + sqle2.toString());
                     	saveLogging("EORGANISM ::saveMetadataTableFromCSVToDatabase :: SQLException:finally: " + sqle2.toString(),"EORGANISM.EOrganismController.saveMetadataTableFromCSVToDatabase","tableArrayList");
                     }//
                
                }//finally
             
        }// end saveMetadataTableFromCSVToDatabase
        
        
        public void saveMetadataColumnFromCSVToDatabase(){
        	
            PreparedStatement insertPreparedStatementMetadataColumn;
            
            EColumn columnObjectWorker;
             
            databaseInitializer();
            checkConnection();
            
            int columnModelId  	=0;
            int eid  			=0;	
            
            //java.sql.Date today = new java.sql.Date(System.currentTimeMillis());
            try{
                    insertPreparedStatementMetadataColumn  = connection.prepareStatement("INSERT INTO EMETADATA_COLUMN(EMETADATA_COLUMN_ID,EMETADATA_COLUMN_NAME,EMETADATA_COLUMN_TYPE,EMETADATA_OWNER,EMETADATA_TABLE_NAME,EMETADATA_DATABASE_NAME,EMETADATA_COMPONENT_NAME,EMETADATA_APPLICATION_NAME,EMETADATA_LAST_UPDATED_DATE,EORGANISM_ID) VALUES (?,?,?,?,?,?,?,?,?,?)");
                     
                    for (int i = 1; i < columnArrayList.size(); i++) {  
                          columnObjectWorker    = new EColumn();
                          columnObjectWorker = (EColumn)columnArrayList.get(i);
                          
                          columnModelId =  getSequenceNextValue("EMETADATA_COLUMN","EMETADATA_COLUMN_ID");
                          eid 			=  getSequenceNextValue(ConfigFrame.EORGANISM_OBJECTID_MAPPING,ConfigFrame.EORGANISM_OBJECTID);
                                         
                        insertPreparedStatementMetadataColumn.setInt(1,  columnModelId);//id
                        insertPreparedStatementMetadataColumn.setString(2, columnObjectWorker.getName());               //name
                        insertPreparedStatementMetadataColumn.setString(3, columnObjectWorker.getDataType());           //data type
                        insertPreparedStatementMetadataColumn.setString(4, columnObjectWorker.getOwner());              //owner
                        insertPreparedStatementMetadataColumn.setString(5, columnObjectWorker.getTableName());          //table name
                        
                        insertPreparedStatementMetadataColumn.setString(6, columnObjectWorker.getDatabase());            //database name
                        insertPreparedStatementMetadataColumn.setString(7, columnObjectWorker.getComponent());           //component name
                        insertPreparedStatementMetadataColumn.setString(8, columnObjectWorker.getApplication());         //application name
                        
                        insertPreparedStatementMetadataColumn.setDate(9, today);                                        //last updated date
                        insertPreparedStatementMetadataColumn.setInt(10, eid);                                        //EORGANISM_ID
                                
                        result = insertPreparedStatementMetadataColumn.executeUpdate();
                        //log4j.debug("EORGANISM ::saveMetadataColumnFromCSVToDatabase ::" +columnObjectWorker.getName());
                        
                        // SAVE EORGANISM
                        saveEOrganismTable(eid,columnObjectWorker.getName(),"EMETADATA_COLUMN","Column", columnModelId);
                         
                    }  // i for object in ArrayList
                
                    //CLOSE
                    insertPreparedStatementMetadataColumn.close(); 
                    
                    connection.close();
                    
                    //log4j.debug("Operations saveMetadataColumnFromCSVToDatabase:: OK");      
                    //JOptionPane.showMessageDialog( null, "EORGANISM :: saveMetadataColumnFromCSVToDatabase OK");
                
                } catch(SQLException sqle){
                    //JOptionPane.showMessageDialog( null,"SQLException  "    + sqle.toString()+"\n");
                	log4j.error("EORGANISM ::saveMetadataColumnFromCSVToDatabase :: SQLException: " + sqle.toString());
                	log4j.debug("EORGANISM ::saveMetadataColumnFromCSVToDatabase :: SQLException: " + sqle.toString());
                	saveLogging("EORGANISM ::saveMetadataColumnFromCSVToDatabase :: SQLException: " + sqle.toString(),"EORGANISM.EOrganismController.saveMetadataColumnFromCSVToDatabase","columnArrayList");
                    
                    
                } catch(Exception e){
                    //JOptionPane.showMessageDialog( null, "Exception  " +e.toString()+"\n");
                	log4j.error("EORGANISM ::saveMetadataColumnFromCSVToDatabase :: Exception: " + e.toString());
                	log4j.debug("EORGANISM ::saveMetadataColumnFromCSVToDatabase :: Exception: " + e.toString());
                	saveLogging("EORGANISM ::saveMetadataColumnFromCSVToDatabase :: Exception: " + e.toString(),"EORGANISM.EOrganismController.saveMetadataColumnFromCSVToDatabase","columnArrayList");
                    
            
                }finally{
                    try{
                         
                         if (connection!=null){connection.close(); }
                    
                    }catch(SQLException sqle2){
                    	log4j.error("EORGANISM :: saveMetadataColumnFromCSVToDatabase :: SQLException:finally " + sqle2.toString());
                    	log4j.debug("EORGANISM :: saveMetadataColumnFromCSVToDatabase :: SQLException:finally " + sqle2.toString());
                    	saveLogging("EORGANISM :: saveMetadataColumnFromCSVToDatabase :: SQLException:finally " + sqle2.toString(),"EORGANISM.EOrganismController.saveMetadataColumnFromCSVToDatabase","columnArrayList");
                        
                    }//
                
                }//finally
             
            
        }// end saveMetadataColumnFromCSVToDatabase
        
        public String[] loadFileOperationsFromURL(EService _serviceworker){
            
            EService tempServiceWorker;
            tempServiceWorker =  _serviceworker;
                
            String urlLocation = tempServiceWorker.getServiceLocation();
             
            try{
                
                // get URL content
                                        url            = new URL(urlLocation);
                                        urlConnection  = url.openConnection();
                 
                                        // open the stream and put it into BufferedReader
                                        bufferedReader = new BufferedReader( new InputStreamReader(urlConnection.getInputStream()));
                 
                                        String inputLine;
                 
                                        //save to this filename
                                        file = new File(fileName);
                
                                        if (!file.exists()) {
                                             file.createNewFile();
                                        }
                 
                                        //use FileWriter to write file
                                        fileWriter               = new FileWriter(file.getAbsoluteFile());
                                        bufferWritter        	 = new BufferedWriter(fileWriter);
                 
                                        while ((inputLine = bufferedReader.readLine()) != null) {
                                                bufferWritter.write(inputLine);
                                        }// while
                 
                                        bufferWritter.close();
                                        bufferedReader.close();
                 
                                        log4j.debug("EORGANISM :: loadFileOperationsFromURL :: saved the file to urlLocation="+urlLocation);
    
            }catch (MalformedURLException murle) {
                                        //JOptionPane.showMessageDialog(  null, "EORGANISM :: loadFileOperationsFromURL MalformedURLException ","loadFileFromURL :: MalformedURLExceptione", JOptionPane.ERROR_MESSAGE);
						            	log4j.error("EORGANISM :: loadFileOperationsFromURL :: MalformedURLException urlLocation="+ urlLocation);
						            	log4j.error("EORGANISM :: loadFileOperationsFromURL :: MalformedURLException ="+ murle.toString());
										
						            	log4j.debug("EORGANISM :: loadFileOperationsFromURL :: MalformedURLException urlLocation="+ murle.toString());
						  				log4j.debug("EORGANISM :: loadFileOperationsFromURL :: MalformedURLException ="+ murle.toString());
            							murle.printStackTrace();
                                   
            }catch (IOException ioe) {
                
                                       //JOptionPane.showMessageDialog(  null,  "EORGANISM :: loadFileOperationsFromURL :: IOException ", "loadFileFromURL :: loadFileFromURL :: IOException", JOptionPane.ERROR_MESSAGE);
						            	log4j.error("EORGANISM :: loadFileOperationsFromURL :: IOException urlLocation="+ urlLocation);
						            	log4j.error("EORGANISM :: loadFileOperationsFromURL :: IOException ="+ ioe.toString());
						            	
						            	log4j.debug("EORGANISM :: loadFileOperationsFromURL :: IOException urlLocation=="+ urlLocation);
            							log4j.debug("EORGANISM :: loadFileOperationsFromURL :: IOException ="+ ioe.toString());
            							ioe.printStackTrace();
            							
            }catch (Exception e) {
						             	//JOptionPane.showMessageDialog(  null,  "EORGANISM :: loadFileOperationsFromURL :: Exception ", "loadFileFromURL :: loadFileFromURL :: Exception", JOptionPane.ERROR_MESSAGE);
										log4j.error("EORGANISM :: loadFileOperationsFromURL :: Exception urlLocation="+ urlLocation);
										log4j.error("EORGANISM :: loadFileOperationsFromURL :: Exception ="+ e.toString());
										
										log4j.debug("EORGANISM :: loadFileOperationsFromURL :: Exception urlLocation="+ urlLocation);
										log4j.debug("EORGANISM :: loadFileOperationsFromURL :: Exception ="+ e.toString());
										e.printStackTrace();
        	
            }//
            
            // print operations
            //loadMembraneOperationsFromWSDL(tempServiceWorker.getServiceLocation());
            
            return loadOperationsFromWSDL(tempServiceWorker.getServiceLocation(), fileName,tempServiceWorker.getServiceType());
            
        }//loadFileOperationsFromURL
        
        
        public void saveURLFileToLocal(String _urlLocation, String _localLocation){
                  
            String urlLocation = _urlLocation;
             
            try{
                
                						// get URL content
                                        url            = new URL(urlLocation);
                                        urlConnection  = url.openConnection();
                 
                                        // open the stream and put it into BufferedReader
                                        bufferedReader = new BufferedReader( new InputStreamReader(urlConnection.getInputStream()));
                 
                                        String inputLine;
                 
                                        //save to this _localLocation
                                        file = new File(_localLocation);
                
                                        if (!file.exists()) {
                                             file.createNewFile();
                                        }
                 
                                        //use FileWriter to write file
                                        fileWriter               = new FileWriter(file.getAbsoluteFile());
                                        bufferWritter        	 = new BufferedWriter(fileWriter);
                 
                                        while ((inputLine = bufferedReader.readLine()) != null) {
                                                bufferWritter.write(inputLine);
                                        }// while
                 
                                        bufferWritter.close();
                                        bufferedReader.close();
                 
                                        log4j.debug("EORGANISM :: saveURLFileToLocal :: saved the file to urlLocation="+urlLocation);
    
            }catch (MalformedURLException murle) {
                                        //JOptionPane.showMessageDialog(  null, "EORGANISM :: saveURLFileToLocal MalformedURLException ","loadFileFromURL :: MalformedURLExceptione", JOptionPane.ERROR_MESSAGE);
						            	log4j.error("EORGANISM :: saveURLFileToLocal :: MalformedURLException urlLocation="+ urlLocation);
						            	log4j.error("EORGANISM :: saveURLFileToLocal :: MalformedURLException ="+ murle.toString());
										
						            	log4j.debug("EORGANISM :: saveURLFileToLocal :: MalformedURLException urlLocation="+ murle.toString());
						  				log4j.debug("EORGANISM :: saveURLFileToLocal :: MalformedURLException ="+ murle.toString());
            							murle.printStackTrace();
                                   
            }catch (IOException ioe) {
                
                                       //JOptionPane.showMessageDialog(  null,  "EORGANISM :: saveURLFileToLocal :: IOException ", "loadFileFromURL :: loadFileFromURL :: IOException", JOptionPane.ERROR_MESSAGE);
						            	log4j.error("EORGANISM :: saveURLFileToLocal :: IOException urlLocation="+ urlLocation);
						            	log4j.error("EORGANISM :: saveURLFileToLocal :: IOException ="+ ioe.toString());
						            	
						            	log4j.debug("EORGANISM :: saveURLFileToLocal :: IOException urlLocation=="+ urlLocation);
            							log4j.debug("EORGANISM :: saveURLFileToLocal :: IOException ="+ ioe.toString());
            							ioe.printStackTrace();
            							
            }catch (Exception e) {
						             	//JOptionPane.showMessageDialog(  null,  "EORGANISM :: saveURLFileToLocal :: Exception ", "loadFileFromURL :: loadFileFromURL :: Exception", JOptionPane.ERROR_MESSAGE);
										log4j.error("EORGANISM :: saveURLFileToLocal :: Exception urlLocation="+ urlLocation);
										log4j.error("EORGANISM :: saveURLFileToLocal :: Exception ="+ e.toString());
										
										log4j.debug("EORGANISM :: saveURLFileToLocal :: Exception urlLocation="+ urlLocation);
										log4j.debug("EORGANISM :: saveURLFileToLocal :: Exception ="+ e.toString());
										e.printStackTrace();
        	
            }//
    
        }//end saveURLFileToLocal
        
        
        public String[] loadFileAttributesFromURL(EService _serviceworker){
            
            EService tempServiceWorker;
            tempServiceWorker =  _serviceworker;
                
            String urlLocation = tempServiceWorker.getServiceLocation();
                
            try{
                
                // get URL content
                                        url            = new URL(urlLocation);
                                        urlConnection  = url.openConnection();
                 
                                        // open the stream and put it into BufferedReader
                                        bufferedReader = new BufferedReader( new InputStreamReader(urlConnection.getInputStream()));
                 
                                        inputLine ="";;
                 
                                        //save to this fileNameAttribute
                                       
                                        file = new File(fileNameAttribute);
                
                                        if (!file.exists()) {
                                                file.createNewFile();
                                        }
                 
                                        //use FileWriter to write file
                                        fileWriter               = new FileWriter(file.getAbsoluteFile());
                                        bufferWritter        = new BufferedWriter(fileWriter);
                 
                                        while ((inputLine = bufferedReader.readLine()) != null) {
                                                bufferWritter.write(inputLine);
                                        }// while
                 
                                        bufferWritter.close();
                                        bufferedReader.close();
                 
                                        //log4j.debug("EORGANISM :: loadFileAttributesFromURL :: saved the file to location="+fileName);
        
            }catch (MalformedURLException murle) {
                                        //JOptionPane.showMessageDialog(  null, "loadFileAttributesFromURL :: MalformedURLException ",  "loadFileAttributesFromURL :: MalformedURLExceptione", JOptionPane.ERROR_MESSAGE);
            							log4j.error("EORGANISM :: loadFileAttributesFromURL :: MalformedURLException urlLocation="+urlLocation);
            							log4j.error("EORGANISM :: loadFileAttributesFromURL :: MalformedURLException ="+murle.toString());
            							
            							log4j.debug("EORGANISM :: loadFileAttributesFromURL :: MalformedURLException urlLocation="+urlLocation);
            							log4j.debug("EORGANISM :: loadFileAttributesFromURL :: MalformedURLException ="+murle.toString());
            							murle.printStackTrace();                                  
            }catch (IOException ioe) {
                
                                    //JOptionPane.showMessageDialog(  null, "EORGANISM :: loadFileAttributesFromURL :: IOException ", "loadFileAttributesFromURL :: loadFileFromURL :: IOException", JOptionPane.ERROR_MESSAGE);
            						log4j.error("EORGANISM :: loadFileAttributesFromURL :: IOException urlLocation="+ urlLocation);
            						log4j.error("EORGANISM :: loadFileAttributesFromURL :: IOException ="+ ioe.toString());
            						
            						log4j.debug("EORGANISM :: loadFileAttributesFromURL :: IOException urlLocation ="+ ioe.toString());
            						log4j.debug("EORGANISM :: loadFileAttributesFromURL :: IOException ="+ ioe.toString());
                                    ioe.printStackTrace();
            }catch (Exception e) {
     
					            	//JOptionPane.showMessageDialog(  null, "EORGANISM :: loadFileAttributesFromURL :: Exception ", "loadFileAttributesFromURL :: loadFileFromURL :: IOException", JOptionPane.ERROR_MESSAGE);
									log4j.error("EORGANISM :: loadFileAttributesFromURL :: Exception urlLocation="+ urlLocation);
									log4j.debug("EORGANISM :: loadFileAttributesFromURL :: Exception ="+ e.toString());
									
									log4j.error("EORGANISM :: loadFileAttributesFromURL :: Exception urlLocation="+ urlLocation);
									log4j.error("EORGANISM :: loadFileAttributesFromURL :: Exception ="+ e.toString());
					                e.printStackTrace();
            
            }//
            
            return loadAttributesFromWSDL(fileName, tempServiceWorker.getServiceType());

        }//loadFileAttributesFromURL - return String[]
        
        
        public void loadMembraneOperationsFromWSDL(String _location){
        	
        	com.predic8.wsdl.WSDLParser parser = new com.predic8.wsdl.WSDLParser();
        	 
        	com.predic8.wsdl.Definitions defs = parser.parse(_location);
         
            for (com.predic8.wsdl.PortType pt : defs.getPortTypes()) {
            	
            	  log4j.debug(pt.getName());
            	  // print all operations
	              for (com.predic8.wsdl.Operation op : pt.getOperations()) {
	            	  log4j.debug(" -" + op.getName());
	              }// for inner

            }// for
        	
        }//loadMembraneOperations
        
        
        public String getSOAPRequestForOperationFromWSDL(String _operation, String _wsdlLocation, String _serviceName){
        	
        	com.predic8.wsdl.WSDLParser parser = new com.predic8.wsdl.WSDLParser();
        	com.predic8.wsdl.Definitions wsdl = parser.parse(_wsdlLocation);
             
            StringWriter writer = new StringWriter();
             
            //SOAPRequestCreator constructor: SOARequestCreator(Definitions, Creator, MarkupBuilder)
            SOARequestCreator creator = new SOARequestCreator(wsdl, new RequestTemplateCreator(), new MarkupBuilder(writer));
             
            //creator.createRequest(PortType name, Operation name, Binding name);
            creator.createRequest(_serviceName, _operation, _serviceName+"Binding");
            
            log4j.debug("getSOAPRequestForOperationFromWSDL "+_serviceName+"::"+_operation+ "::"+_wsdlLocation);
            log4j.debug("getSOAPRequestForOperationFromWSDL "+ writer.toString());
            
            
            return writer.toString();

        }//getSOAPRequestForOperationFromWSDL
          
        
        public String[] loadOperationsFromWSDL(String _urlLocation, String _location, String _type){
              	//log4j.debug("EORGANISM :: loadOperationsFromWSDL:: ****** #1 loadOperationsFromWSDL _urlLocation = " + _urlLocation  +"load type=" +_type ); 
                	size =0;
                Document              document; 
	            NodeList              elements 		= null; 
	            NodeList              services 		= null; 
	            String                operationName ="";
                try {
                         				//log4j.debug("EORGANISM :: loadOperationsFromWSDL::****** #2 loadOperationsFromWSDL" ); 
                         	
                        				document     = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new FileInputStream(_location));
                        				
                        				//log4j.debug("EORGANISM :: loadOperationsFromWSDL::****** #3 loadOperationsFromWSDL" ); 
                                      
                                       elements     = document.getElementsByTagName(ConfigFrame.wsdl_operation);
                                       services     = document.getElementsByTagName(ConfigFrame.wsdl_service);
                                       size = elements.getLength();
                                        //log4j.debug("EORGANISM :: loadOperationsFromWSDL::****** #4 wsdl:operatio size="+ size + " ********* s0:operatioloadOperationsFromWSDL - s0:_urlLocation = " + _urlLocation  +"load type=" +_type ); 
                                       if ((_type.trim()).equals("JWS")){
                                           
                                            //log4j.debug("EORGANISM :: loadOperationsFromWSDL::****** JWS ********* WL5G3N0 loadOperationsFromWSDL _location = " + _location  +"load type=" +_type ); 
                                            elements     = document.getElementsByTagName(ConfigFrame.WL5G3N0_operation);
                                        
                                            services     = document.getElementsByTagName(ConfigFrame.WL5G3N0_service);
                                            size = elements.getLength();
                                            //log4j.debug("EORGANISM :: loadOperationsFromWSDL::******* JWS ** WL5G3N0:operation size="+ size + " ********* s0:operatioloadOperationsFromWSDL - s0:_urlLocatio = " + _urlLocation  +"load type=" +_type ); 
                                        }
                                      // log4j.debug("EORGANISM :: loadOperationsFromWSDL::****** #5 wsdl:operatio size="+ size + " ********* s0:operatioloadOperationsFromWSDL - s0:_urlLocation = " + _urlLocation  +"load type=" +_type ); 
                                      // parsing logic didn't work out
                                      if (size==0){
                                               //log4j.debug("EORGANISM :: loadOperationsFromWSDL::****** size==0 ********* xsd:operation loadOperationsFromWSDL _location = " + _location  +"load type=" +_type ); 
                                               elements     = document.getElementsByTagName(ConfigFrame.xsd_operation);
                                               services     = document.getElementsByTagName(ConfigFrame.xsd_service);
                                               size = elements.getLength();
                                               //log4j.debug("EORGANISM :: loadOperationsFromWSDL::******xsd:operation size="+ size + " ********* s0:operatioloadOperationsFromWSDL - s0:operation _urlLocatio = " + _urlLocation  +"load type=" +_type ); 
                                               
                                               if (size==0){
                                                   
                                                        //log4j.debug("EORGANISM :: loadOperationsFromWSDL::****** size==0 ********* s0:operation loadOperationsFromWSDL - s0:operation _location = " + _location  +"load type=" +_type ); 
                                                        elements     = document.getElementsByTagName(ConfigFrame.s0_operation);
                                                        services     = document.getElementsByTagName(ConfigFrame.s0_service);
                                                        size = elements.getLength();
                                                        //log4j.debug("EORGANISM :: loadOperationsFromWSDL::******s0:operation size="+ size + " ********* s0:operatioloadOperationsFromWSDL - s0:_urlLocatio = " + _urlLocation  +"load type=" +_type ); 
                                                           
                                               }
                                               //ESF WSDL
                                               if (size==0){
                                                   
                                            	    	log4j.debug("EORGANISM :: loadOperationsFromWSDL::****** size==0 ********* ESF WSDL :: <operation loadOperationsFromWSDL _location = " + _location  +"load type=" +_type ); 
                                                          elements     = document.getElementsByTagName(ConfigFrame.operation);
                                                          size = elements.getLength();
                                                        log4j.debug("EORGANISM :: loadOperationsFromWSDL::******ESF WSDL <operation size="+ size + " ********* operatioloadOperationsFromWSDL - s0:_urlLocatio = " + _urlLocation  +"load type=" +_type ); 
                                                          
                                               }
                                               
                                               
                                               
                                      }
                                      
                              
                           // handle exception creating DocumentBuilder
                        }catch ( FileNotFoundException fnfe ) {
                                                       
                                                      
                                                       //JOptionPane.showMessageDialog( null, "EORGANISM :: loadOperationsFromWSDL : FileNotFoundException when loaded from  XML file: "   + _location  +"\n"+ fnfe.toString());
                                                       log4j.error("EORGANISM :: loadOperationsFromWSDL::FileNotFoundException _location=" +_location );
                                                       log4j.error("EORGANISM :: loadOperationsFromWSDL::FileNotFoundException fnfe=" +fnfe.toString() );
                                                       
                                                       log4j.debug("EORGANISM :: loadOperationsFromWSDL::FileNotFoundException _location=" +_location );
                                                       log4j.debug("EORGANISM :: loadOperationsFromWSDL::FileNotFoundException fnfe=" +fnfe.toString() );
                                                       fnfe.printStackTrace();
                        
                        }catch ( ParserConfigurationException parserException ) {
                        	 							//JOptionPane.showMessageDialog( null,"EORGANISM :: loadOperationsFromWSDL: ParserConfigurationException when loaded from  XML file: "   + _location  +"\n"+ parserException.toString());
                            
							                        	log4j.error("EORGANISM :: loadOperationsFromWSDL::ParserConfigurationException _location=" +_location );
							                            log4j.error("EORGANISM :: loadOperationsFromWSDL::ParserConfigurationException parserException=" +parserException.toString() );
							                            
							                            log4j.debug("EORGANISM :: loadOperationsFromWSDL::ParserConfigurationException _location=" +_location );
							                            log4j.debug("EORGANISM :: loadOperationsFromWSDL::ParserConfigurationException parserException=" +parserException.toString() );
							                            parserException.printStackTrace();
                                        
                         }// handle exception parsing Document
                         catch ( SAXException saxException ) {
                        	 							//JOptionPane.showMessageDialog( null,"EORGANISM ::loadOperationsFromWSDL: SAXException when loaded from  XML file: "   + _location +"\n"+  saxException.toString());
                             
							                        	log4j.error("EORGANISM :: loadOperationsFromWSDL::SAXException _location=" +_location );
							                            log4j.error("EORGANISM :: loadOperationsFromWSDL::SAXException saxException=" +saxException.toString() );
							                            
							                            log4j.debug("EORGANISM :: loadOperationsFromWSDL::SAXException _location=" +_location );
							                            log4j.debug("EORGANISM :: loadOperationsFromWSDL::SAXException saxException=" +saxException.toString() );
							                            saxException.printStackTrace();
                                        
                         }// handle exception reading/writing data
                         catch ( IOException ioException ) {
				                                         //JOptionPane.showMessageDialog( null,  "EORGANISM :: loadOperationsFromWSDL: IOException when loaded from  XML file: "   + _location + "\n" + ioException.toString()+"\n"+ "Pls make sure to have the configuration XML file " + _location + "\n" +" in the same root directory as EOrganism");
						                        	 	log4j.error("EORGANISM :: loadOperationsFromWSDL::IOException _location=" +_location );
							                            log4j.error("EORGANISM :: loadOperationsFromWSDL::IOException ioException=" +ioException.toString() );
							                            
							                            log4j.debug("EORGANISM :: loadOperationsFromWSDL::IOException _location=" +_location );
							                            log4j.debug("EORGANISM :: loadOperationsFromWSDL::IOException ioException=" +ioException.toString() );
							                            ioException.printStackTrace();
				                                          
				                                        //System.exit( 1 );
                         }//
                        catch (Exception _exception ) {
                            						//JOptionPane.showMessageDialog( null,  "loadOperationsFromWSDL: _exception when loaded from  XML file: "   + _location + "\n" + _exception.toString()+"\n"+ "Pls make sure to have the configuration XML file " + _location + "\n" +" in the same root directory as EOrganism");
						                        	log4j.error("EORGANISM :: loadOperationsFromWSDL::Exception _location=" +_location );
						                            log4j.error("EORGANISM :: loadOperationsFromWSDL::Exception _exception=" +_exception.toString() );
						                            
						                            log4j.debug("EORGANISM :: loadOperationsFromWSDL::Exception _location=" +_location );
						                            log4j.debug("EORGANISM :: loadOperationsFromWSDL::Exception _exception=" +_exception.toString() );
						                        	_exception.printStackTrace();
							                        	//System.exit( 1 );
                        }//end catch
                        
	                    operationsArrayString = new String[size];
	                    
	                    //log4j.debug("EORGANISM :: loadOperationsFromWSDL::****** #2 loadOperationsFromWSDL operationsArrayString= " + operationsArrayString.length );  
		                 
	                    for (int i = 0; i < size; i++) {
	                        
	                          operationName           = (elements.item(i).getAttributes().getNamedItem(ConfigFrame.name).getNodeValue()).toString();
	                          //operationsArrayList.add(elements.item(i).getAttributes().getNamedItem("name").getNodeValue());
	                          
	                          operationsArrayString[i]= operationName;
	                          //log4j.debug("EORGANISM :: loadOperationsFromWSDL::****** #3 loadOperationsFromWSDL _urlLocation :: "+_urlLocation+"::loadOperationsFromWSDL  operationName = " + operationName );  
	                   
	                   }// for
	                    
	                    // remove duplicates from array
	                    end = operationsArrayString.length;
	                    set = new HashSet<String>();

	                    for(int i = 0; i < end; i++){
	                      set.add(operationsArrayString[i]);
	                    }
	                    
	                    int setSize= 0;
	                    setSize = set.size();
	                    
	                    operationsArrayStringNew = new String[setSize];
	                    		
	                    iterator = set.iterator();
	                    
	                    i=0;
	                    while(iterator.hasNext()) {
	                    	operationsArrayStringNew[i] = (String)iterator.next();
	                    	//log4j.debug("EORGANISM :: loadOperationsFromWSDL****** #2 loadOperationsFromWSDL - UNIQUE OPERATIONS ***** _urlLocation :: "+ _urlLocation+ "loadOperationsFromWSDL operationsArrayStringNew["+i+"]  = " + operationsArrayStringNew[i]  );  
	                    	i++;
	                    }// while elements in the set
	                    
	                  
                    //return operationsArrayList.toArray(new String[operationsArrayList.size()]);
                    return operationsArrayStringNew;
            
        }//loadOperationsFromWSDL String[]
        
           
    public String[] loadAttributesFromWSDL(String _location, String _type){
            
             //log4j.debug("EORGANISM :: loadAttributesFromWSDL::_location = " + _location ); 
             int size =0;

            Document              document; 
            NodeList              elements  =null; 
            NodeList              services  =null; 
            NodeList              atrributes =null; 
            
            String                operationName ="";
            String                attributeName ="";
              
                        try {
                                      document      = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new FileInputStream(_location));
                                      atrributes    = document.getElementsByTagName(ConfigFrame.element);
                                      size 			= atrributes.getLength();
                                     
                                      //log4j.debug("EORGANISM :: loadAttributesFromWSDL:: ******element size="+ size + "loadAttributesFromWSDL" );
                                      
                                      if (size==0){
                                          
                                          //log4j.debug("EORGANISM :: loadOperationsFromWSDL::****** JWS ********* loadAttributesFromWSDL " ); 
                                          atrributes    = document.getElementsByTagName(ConfigFrame.xs_element);
                                          size = atrributes.getLength();
                                          //log4j.debug("EORGANISM :: loadAttributesFromWSDL::****** JWS ******** element size="+ size + "loadAttributesFromWSDL" );
                                      }
                                      
                                      if (size==0){
                                    	  
                                    	  //log4j.debug("EORGANISM :: loadOperationsFromWSDL::****** WS EIF xs:attribute ********* loadAttributesFromWSDL" );
                                          atrributes    = document.getElementsByTagName(ConfigFrame.xs_attribute);
                                          size = atrributes.getLength();
                                          //log4j.debug("EORGANISM :: loadAttributesFromWSDL::****** JWS ******** element size="+ size + "loadAttributesFromWSDL" );
                                      }
                                        
                                     // parsing logic didn't work out
                                      if (size==0){
                                                    
                                          //log4j.debug("EORGANISM :: loadAttributesFromWSDL::****** size==0 ********* s:element loadAttributesFromWSDL -" ); 
                                          elements     = document.getElementsByTagName(ConfigFrame.s_element);
                                          size = elements.getLength();
                                                        
                                          //log4j.debug("EORGANISM :: loadAttributesFromWSDL::******s:element size="+ size + " ********* s0:loadAttributesFromWSDL - s0:operation _location = " + _location  +"load type=" +_type ); 
                                             
                                       }
                                      // parsing logic didn't work out even at this stage - ESF WSDL
                                      if (size==0){
                                                    
                                          log4j.debug("EORGANISM :: loadAttributesFromWSDL::****** size==0 ********* message loadAttributesFromWSDL -" ); 
                                          elements     = document.getElementsByTagName(ConfigFrame.message);
                                          size = elements.getLength();
                                                        
                                          log4j.debug("EORGANISM :: loadAttributesFromWSDL::******message size="+ size + " ********* sloadAttributesFromWSDL - _location = " + _location  +"load type=" +_type ); 
                                             
                                       }
                                      
	                              //log4j.debug("EORGANISM :: loadAttributesFromWSDL::****** loadAttributesFromWSDL atrributes.getLength() = " + size );  
	                          
                            // handle exception creating DocumentBuilder
                        	}catch ( FileNotFoundException fnfe ) {
                            
                            
		                            //JOptionPane.showMessageDialog( null, "EORGANISM :: loadAttributesFromWSDL : FileNotFoundException when loaded from  XML file: "   + _location  +"\n"+ fnfe.toString());
		                            log4j.error("EORGANISM :: loadAttributesFromWSDL::FileNotFoundException _location=" +_location );
		                            log4j.error("EORGANISM :: loadAttributesFromWSDL::FileNotFoundException fnfe=" +fnfe.toString() );
		                            
		                            log4j.debug("EORGANISM :: loadAttributesFromWSDL::FileNotFoundException _location=" +_location );
		                            log4j.debug("EORGANISM :: loadAttributesFromWSDL::FileNotFoundException fnfe=" +fnfe.toString() );
		                            fnfe.printStackTrace();

                        		}catch ( ParserConfigurationException parserException ) {
	 							//JOptionPane.showMessageDialog( null,"EORGANISM :: loadAttributesFromWSDL: ParserConfigurationException when loaded from  XML file: "   + _location  +"\n"+ parserException.toString());
 
		                        	log4j.error("EORGANISM :: loadAttributesFromWSDL::ParserConfigurationException _location=" +_location );
		                            log4j.error("EORGANISM :: loadAttributesFromWSDL::ParserConfigurationException parserException=" +parserException.toString() );
		                            
		                            log4j.debug("EORGANISM :: loadAttributesFromWSDL::ParserConfigurationException _location=" +_location );
		                            log4j.debug("EORGANISM :: loadAttributesFromWSDL::ParserConfigurationException parserException=" +parserException.toString() );
		                            parserException.printStackTrace();
             
             
								}// handle exception parsing Document
								catch ( SAXException saxException ) {
									 							//JOptionPane.showMessageDialog( null,"EORGANISM ::loadAttributesFromWSDL: SAXException when loaded from  XML file: "   + _location +"\n"+  saxException.toString());
								  
									                        	log4j.error("EORGANISM :: loadAttributesFromWSDL::SAXException _location=" +_location );
									                            log4j.error("EORGANISM :: loadAttributesFromWSDL::SAXException saxException=" +saxException.toString() );
									                            
									                            log4j.debug("EORGANISM :: loadAttributesFromWSDL::SAXException _location=" +_location );
									                            log4j.debug("EORGANISM :: loadAttributesFromWSDL::SAXException saxException=" +saxException.toString() );
									                            saxException.printStackTrace();
								             
								}// handle exception reading/writing data
								catch ( IOException ioException ) {
								                              //JOptionPane.showMessageDialog( null,  "EORGANISM :: loadAttributesFromWSDL: IOException when loaded from  XML file: "   + _location + "\n" + ioException.toString()+"\n"+ "Pls make sure to have the configuration XML file " + _location + "\n" +" in the same root directory as EOrganism");
								                     	 		log4j.error("EORGANISM :: loadAttributesFromWSDL::IOException _location=" +_location );
									                            log4j.error("EORGANISM :: loadAttributesFromWSDL::IOException ioException=" +ioException.toString() );
									                            
									                            log4j.debug("EORGANISM :: loadAttributesFromWSDL::IOException _location=" +_location );
									                            log4j.debug("EORGANISM :: loadAttributesFromWSDL::IOException ioException=" +ioException.toString() );
									                            ioException.printStackTrace();
								                               
								                             //System.exit( 1 );
								}//
								catch (Exception _exception ) {
								 						//JOptionPane.showMessageDialog( null,  "loadAttributesFromWSDL: _exception when loaded from  XML file: "   + _location + "\n" + _exception.toString()+"\n"+ "Pls make sure to have the configuration XML file " + _location + "\n" +" in the same root directory as EOrganism");
								                     	log4j.error("EORGANISM :: loadAttributesFromWSDL::Exception _location=" +_location );
								                         log4j.error("EORGANISM :: loadAttributesFromWSDL::Exception _exception=" +_exception.toString() );
								                         
								                         log4j.debug("EORGANISM :: loadAttributesFromWSDL::Exception _location=" +_location );
								                         log4j.debug("EORGANISM :: loadAttributesFromWSDL::Exception _exception=" +_exception.toString() );
								                     	_exception.printStackTrace();
									                    //System.exit( 1 );
								}//end catch
                        
                        // parsing the results
                        String[]  atrributesArrayString = new String[size];
                        
                        for (int i = 0; i < size; i++) {
                              attributeName           = (atrributes.item(i).getAttributes().getNamedItem("name").getNodeValue()).toString();
                              
                              atrributesArrayString[i]= attributeName;
                              //log4j.debug("EORGANISM :: loadAttributesFromWSDL::****** loadAttributesFromWSDL attributeName = " + attributeName );  
                        }// for
                    
                        return atrributesArrayString;
            
        }//loadAttributesFromWSDL String[]
       
        public void loadOperationsAttributesMetadataToDB(EService _serviceObjectWorker){
                     
                    String operationString              		="";
                    String tempSOAPRequestString 				="";
                    String tempSOAPResponsetString 				="";
                      
                    EService    serviceObjectWorkerTemp = new EService();
                                serviceObjectWorkerTemp = _serviceObjectWorker;

                    ArrayList   operationsArrayList;
                    String[]    operationsArrayListString;
                    
                    String[]    attributesArrayListString;
                    ArrayList   attributesArrayList;
                     
                    PreparedStatement insertPreparedStatementMetadataOperations;
                    PreparedStatement insertPreparedStatementSequenceItem;       
                    PreparedStatement insertPreparedStatementMetadataAttributes;
                    PreparedStatement insertPreparedStatementInterface;
                    PreparedStatement insertPreparedStatementAttributes;
                    
                    EInterface operationObjectWorker;
                    EParameter attributeObjectWorker;                     
                    
                    databaseInitializer();
                    checkConnection();
                   
                    int	eid			=0;
                    int interfaceId	=0;
                    int attributeId	=0;
    
                    //java.sql.Date today = new java.sql.Date(System.currentTimeMillis());
             
            try{
                    //EMETADATA_INTERFACE
                    insertPreparedStatementMetadataOperations   = connection.prepareStatement("INSERT INTO EMETADATA_INTERFACE(EMETADATA_INTERFACE_ID,EMETADATA_INTERFACE_NAME,EMETADATA_INTERFACE_TYPE,EMETADATA_COMPONENT_NAME,EMETADATA_DATABASE_NAME,EMETADATA_SERVICE_NAME,EMETADATA_APPLICATION_NAME,EMETADATA_SERVICE_LOCATION,EINTERFACE_LOCAL_LOCATION,EINTERFACE_WEB_LOCATION,EMETADATA_LAST_UPDATED_DATE,EMETADATA_LAST_UPDATED_USER,EMETADATA_LAST_UPDATED_APP,EORGANISM_ID,EINTERFACE_REQUEST) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"); 
            
                    // insert into SEQUENCE_FLOW_ITEM
                    insertPreparedStatementSequenceItem         = connection.prepareStatement("INSERT INTO ESEQUENCE_ITEM_TABLE(ESEQUENCE_ITEM_ID,ESEQUENCE_ITEM_NAME,ESEQUENCE_ITEM_DATA,LAST_UPDATED_APPLICATION,CREATED_DATE,UPDATED_DATE,EORGANISM_ID) VALUES (?,?,?,?,?,?,?)");
                   
                    // insert EINTERFACE
                    insertPreparedStatementInterface         	= connection.prepareStatement("INSERT INTO EINTERFACE(EINTERFACE_ID,EINTERFACE_NAME,EINTERFACE_DESCRIPTION,EAPPLICATION_ID_FK,EAPPLICATION_NAME_FK,ECOMPONENT_NAME_FK,ESERVICE_NAME_FK,EINTERFACE_LOCATION,EINTERFACE_LOCAL_LOCATION,EINTERFACE_WEB_LOCATION,EINTERFACE_WEBSITE_LOCATION,EORGANISM_ID,CREATED_TIMESTAMP,EINTERFACE_REQUEST,EINTERFACE_RESPONSE,SYS_ID_FK,APP_ID_FK,COMP_ID_FK,ESYSTEM_NAME_FK) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                     
                    // EMETADATA_ATTRIBUTE
                    insertPreparedStatementMetadataAttributes   = connection.prepareStatement("INSERT INTO EMETADATA_ATTRIBUTE( EMETADATA_ATTRIBUTE_ID,EMETADATA_ATTRIBUTE_NAME,EMETADATA_ATTRIBUTE_TYPE,EMETADATA_INTERFACE_NAME,EMETADATA_SERVICE_NAME,EMETADATA_COMPONENT_NAME,EMETADATA_DATABASE_NAME,EMETADATA_APPLICATION_NAME,EMETADATA_LAST_UPDATED_DATE,EMETADATA_LAST_UPDATED_USER,EMETADATA_LAST_UPDATED_APP,EORGANISM_ID) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
                    
                    //EATTRIBUTE
                    insertPreparedStatementAttributes        	= connection.prepareStatement("INSERT INTO EATTRIBUTE(EATTRIBUTE_ID,EATTRIBUTE_NAME,EATTRIBUTE_TYPE,EATTRIBUTE_SUBTYPE,ESERVICE_NAME_FK,ECOMPONENT_NAME_FK,EAPPLICATION_NAME_FK,EORGANISM_ID,CREATED_TIMESTAMP) VALUES (?,?,?,?,?,?,?,?,?)");
                    
                     	
                    operationsArrayListString = loadFileOperationsFromURL(serviceObjectWorkerTemp);
                    //log4j.debug("EORGANISM :: loadOperationsAttributesMetadataToDB::#1 -  loadFileOperationsFromURL(" +serviceObjectWorkerTemp.getServiceLocation());
                   
                      
                    //operationsArrayListString = loadOperationsFromWSDL(serviceObjectWorkerTemp.getServiceLocation(), serviceObjectWorkerTemp.getServiceType());
                    
                    //loadWSDLFileIntoDB(operationObjectWorker.getServiceLocation());
                
                    //log4j.debug("EORGANISM :: loadOperationsAttributesMetadataToDB::#2 - Operation:operationsArrayListString.length  " +  operationsArrayListString.length);
       
                    if (operationsArrayListString.length >0){
                    	
                    					 
                    	   				//log4j.debug("EORGANISM :: loadOperationsAttributesMetadataToDB::#3 -");
                    	
                                        for (int i = 0; i < operationsArrayListString.length; i++) {  
                                             
                                        	//#1
                                        	 interfaceId =  getSequenceNextValue(ConfigFrame.EMETADATA_INTERFACE,ConfigFrame.EMETADATA_INTERFACE_ID); 
                                        	 eid 		 =  getSequenceNextValue(ConfigFrame.EORGANISM_OBJECTID_MAPPING,ConfigFrame.EORGANISM_OBJECTID);
                                        	 
                                        	// get SOAP Request 
                                        	tempSOAPRequestString 	="";
                                        	// To be done
                         					//tempSOAPRequestString   = getSOAPRequestForOperationFromWSDL(operationsArrayListString[i], serviceObjectWorkerTemp.getServiceLocation(),serviceObjectWorkerTemp.getLocalFilePath());
                                            
                                        	tempSOAPResponsetString = "";
                                        	//tempSOAPResponsetString   = getSOAPResponseForOperationFromWSDL(operationsArrayListString[i], serviceObjectWorkerTemp.getServiceLocation(),serviceObjectWorkerTemp.getServiceName());
                                            	
                                              //10
                                            insertPreparedStatementMetadataOperations.setInt(1, interfaceId);//id interface ID
                                            insertPreparedStatementMetadataOperations.setString(2, operationsArrayListString[i]);//NAME
                                            insertPreparedStatementMetadataOperations.setString(3, serviceObjectWorkerTemp.getServiceType());//type
                                            insertPreparedStatementMetadataOperations.setString(4, serviceObjectWorkerTemp.getComponent());//component name
                                            insertPreparedStatementMetadataOperations.setString(5, "DB:"+ serviceObjectWorkerTemp.getApplication()+":"+ serviceObjectWorkerTemp.getDatabase());//db
                                            insertPreparedStatementMetadataOperations.setString(6, serviceObjectWorkerTemp.getServiceName());//service name 
                                            insertPreparedStatementMetadataOperations.setString(7, serviceObjectWorkerTemp.getApplication());//app
                                            insertPreparedStatementMetadataOperations.setString(8, serviceObjectWorkerTemp.getServiceLocation());//location
                                            insertPreparedStatementMetadataOperations.setString(9, serviceObjectWorkerTemp.getLocalFilePath());// local interface location
                                            insertPreparedStatementMetadataOperations.setString(10, serviceObjectWorkerTemp.getWebFilePath());// web interface location - local
                                            insertPreparedStatementMetadataOperations.setDate(11, today);//created date
                                            insertPreparedStatementMetadataOperations.setString(12, ConfigFrame.ADMIN_USER_NAME +"@"+ ConfigFrame.ADMIN_USER_ROLE);//last updated user
                                            insertPreparedStatementMetadataOperations.setString(13, ConfigFrame.LOAD_APP +"@"+ConfigFrame.LOAD_ACTION + "CSV METADATA FIlE loader");//last updated app
                                            insertPreparedStatementMetadataOperations.setInt(14,   eid);//eid
                                            insertPreparedStatementMetadataOperations.setString(15,  tempSOAPRequestString);//SOAPRequest
                                            
                                            
                                            result = insertPreparedStatementMetadataOperations.executeUpdate();
                                            saveEOrganismTable(eid,operationsArrayListString[i],ConfigFrame.EMETADATA_INTERFACE,ConfigFrame.EINTERFACE, interfaceId);
                                            
                                            //#2
                                       	 	interfaceId  =  getSequenceNextValue(ConfigFrame.ESEQUENCE_ITEM_TABLE,ConfigFrame.ESEQUENCE_ITEM_ID); 
                                       	 	eid 		 =  getSequenceNextValue(ConfigFrame.EORGANISM_OBJECTID_MAPPING,ConfigFrame.EORGANISM_OBJECTID);
                                             
                                           //ESEQUENCE_ITEM_ID,ESEQUENCE_ITEM_NAME,ESEQUENCE_ITEM_DATA,LAST_UPDATED_APPLICATION,CREATED_DATE,UPDATED_DATE
                                            insertPreparedStatementSequenceItem.setInt(1, interfaceId );
                                            insertPreparedStatementSequenceItem.setString(2, "ITEM NAME :" + operationsArrayListString[i]);
                                            insertPreparedStatementSequenceItem.setString(3, serviceObjectWorkerTemp.getApplication() +"."+serviceObjectWorkerTemp.getComponent() +"."+serviceObjectWorkerTemp.getServiceName()+"."+operationsArrayListString[i]);
                                            insertPreparedStatementSequenceItem.setString(4,ConfigFrame.LOAD_APP +"@"+ConfigFrame.LOAD_ACTION + "CSV METADATA WSDL FIlE loader");// updated app
                                            insertPreparedStatementSequenceItem.setDate(5, today);//created date
                                            insertPreparedStatementSequenceItem.setDate(6, today);//UPDATED date    
                                            insertPreparedStatementSequenceItem.setInt(7, eid);//eid    
                                                 
                                            result = insertPreparedStatementSequenceItem.executeUpdate(); 
                                            saveEOrganismTable(eid,operationsArrayListString[i],ConfigFrame.ESEQUENCE_ITEM_TABLE,ConfigFrame.ESEQUENCE, interfaceId);
                                           
                                            
                                            //#3
                                            interfaceId =  getSequenceNextValue(ConfigFrame.EINTERFACE_TABLE,ConfigFrame.EINTERFACE_ID);
                                            eid 		=  getSequenceNextValue(ConfigFrame.EORGANISM_OBJECTID_MAPPING,ConfigFrame.EORGANISM_OBJECTID);
                                        	  
                                            // insert EINTERFACE
                                            //EINTERFACE_ID,EINTERFACE_NAME,EINTERFACE_DESCRIPTION,EAPPLICATION_ID_FK,EAPPLICATION_NAME_FK,ECOMPONENT_NAME_FK,ESERVICE_NAME_FK
                                            insertPreparedStatementInterface.setInt(1, interfaceId);
                                            insertPreparedStatementInterface.setString(2, operationsArrayListString[i]);
                                            insertPreparedStatementInterface.setString(3, serviceObjectWorkerTemp.getApplication()+"."+serviceObjectWorkerTemp.getComponent() +"."+serviceObjectWorkerTemp.getServiceName()+"."+operationsArrayListString[i]);
                                            insertPreparedStatementInterface.setInt(4, 1);// app_id
                                            insertPreparedStatementInterface.setString(5, serviceObjectWorkerTemp.getApplication());// app name
                                            insertPreparedStatementInterface.setString(6, serviceObjectWorkerTemp.getComponent());//comp
                                            insertPreparedStatementInterface.setString(7, serviceObjectWorkerTemp.getServiceName());// service
                                            insertPreparedStatementInterface.setString(8, serviceObjectWorkerTemp.getServiceLocation());// service/ interface URL WSDL location
                                            insertPreparedStatementInterface.setString(9, serviceObjectWorkerTemp.getLocalFilePath());// local interface location
                                            insertPreparedStatementInterface.setString(10, serviceObjectWorkerTemp.getWebFilePath());// web interface location - local
                                            insertPreparedStatementInterface.setString(11, serviceObjectWorkerTemp.getWebsiteUrl());// website interface location - local
                                            insertPreparedStatementInterface.setInt(12, eid);//eid    
                                            insertPreparedStatementInterface.setTimestamp(13, new java.sql.Timestamp(System.currentTimeMillis()));//timestamp 
                                             
                                            insertPreparedStatementInterface.setString(14, tempSOAPRequestString);//SOAP Request 
                                            insertPreparedStatementInterface.setString(15, tempSOAPResponsetString);//SOAP RESPONSE 
                                            insertPreparedStatementInterface.setString(16, serviceObjectWorkerTemp.getSystemID());//SYS ID
                                            insertPreparedStatementInterface.setString(17, serviceObjectWorkerTemp.getApplicationID());//APP ID
                                            insertPreparedStatementInterface.setString(18, serviceObjectWorkerTemp.getComponentID());//APP ID
                                            insertPreparedStatementInterface.setString(19, serviceObjectWorkerTemp.getSystem());//ESYSTEM_NAME_FK
                                            
                                                                                       
                                            result = insertPreparedStatementInterface.executeUpdate();
                                            saveEOrganismTable(eid,operationsArrayListString[i],ConfigFrame.EINTERFACE_TABLE,ConfigFrame.EINTERFACE, interfaceId);
                                            //log4j.debug("EORGANISM :: loadOperationsAttributesMetadataToDB:::: operationsArrayListString[i]: "  +operationsArrayListString[i]);
                                            
                                           
                                        } // i for all operations in ArrayLists String                       
                      
                       }//if   we have operations 
                   
                       // attributesArrayListString = loadAttributesFromWSDL(serviceObjectWorkerTemp.getServiceLocation(), operationObjectWorker);
                       attributesArrayListString = loadFileAttributesFromURL(serviceObjectWorkerTemp);
                       
                       //log4j.debug("EORGANISM :: loadOperationsAttributesMetadataToDB:: attributesArrayListString.length:" + attributesArrayListString.length);
                                 
                       if (attributesArrayListString.length >0){
                                                                   
                                for (int j = 0; j < attributesArrayListString.length; j++) { 
                                                                        
                                									//#4
                                    								attributeId =  getSequenceNextValue(ConfigFrame.EMETADATA_ATTRIBUTE,ConfigFrame.EMETADATA_ATTRIBUTE_ID);
                                	  								eid 		=  getSequenceNextValue(ConfigFrame.EORGANISM_OBJECTID_MAPPING,ConfigFrame.EORGANISM_OBJECTID);
                                  	
                                									insertPreparedStatementMetadataAttributes.setInt(1, attributeId);//id
                                                                    insertPreparedStatementMetadataAttributes.setString(2, attributesArrayListString[j]);//NAME
                                                                    insertPreparedStatementMetadataAttributes.setString(3, "WEB SERVICE ATTRIBUTE");//type
                                                                    insertPreparedStatementMetadataAttributes.setString(4, "TBD");//interface
                                                                    insertPreparedStatementMetadataAttributes.setString(5, serviceObjectWorkerTemp.getServiceName());//service
                                                                    insertPreparedStatementMetadataAttributes.setString(6, serviceObjectWorkerTemp.getComponent());//comp
                                                                    insertPreparedStatementMetadataAttributes.setString(7, "DB:"+ serviceObjectWorkerTemp.getApplication()+":"+ serviceObjectWorkerTemp.getDatabase());//db
                                                                    insertPreparedStatementMetadataAttributes.setString(8, serviceObjectWorkerTemp.getApplication());//app
                                                                    insertPreparedStatementMetadataAttributes.setDate(9, today);//created date
                                                                    insertPreparedStatementMetadataAttributes.setString(10,   ConfigFrame.ADMIN_USER_NAME +"@"+ ConfigFrame.ADMIN_USER_ROLE);//last updated user
                                                                    insertPreparedStatementMetadataAttributes.setString(11,   ConfigFrame.LOAD_APP +"@"+ConfigFrame.LOAD_ACTION +"CSV FIlE WSDL loader");//last updated app
                                                                    insertPreparedStatementMetadataAttributes.setInt(12, eid);//eid    
                                                                    
                                                                    result = insertPreparedStatementMetadataAttributes.executeUpdate();
                                                                    //log4j.debug("EORGANISM ::loadOperationsAttributesMetadataToDB:: EMETADATA_ATTRIBUTE:" +attributesArrayListString[j]);
                                                                
                                                                    //#5
                                                                    attributeId = getSequenceNextValue(ConfigFrame.EATTRIBUTE_TABLE,ConfigFrame.EATTRIBUTE_ID);
                                                                    eid 		=  getSequenceNextValue(ConfigFrame.EORGANISM_OBJECTID_MAPPING,ConfigFrame.EORGANISM_OBJECTID);
                                                                	      
                                                                     // INSERT  EATTRIBUTE
                                                                    insertPreparedStatementAttributes.setInt(1, attributeId);//id
                                                                    insertPreparedStatementAttributes.setString(2, attributesArrayListString[j]);//NAME
                                                                    insertPreparedStatementAttributes.setString(3, "WEB SERVICE ATTRIBUTE");//type
                                                                    insertPreparedStatementAttributes.setString(4, "WEB SERVICE ATTRIBUTE");//subtype
                                                                    insertPreparedStatementAttributes.setString(5, serviceObjectWorkerTemp.getServiceName());//service
                                                                    insertPreparedStatementAttributes.setString(6, serviceObjectWorkerTemp.getComponent());//comp
                                                                    insertPreparedStatementAttributes.setString(7, serviceObjectWorkerTemp.getApplication());//app
                                                                    insertPreparedStatementAttributes.setInt(8, eid);//eid  
                                                                    
                                                                    insertPreparedStatementAttributes.setTimestamp(9, new java.sql.Timestamp(System.currentTimeMillis()));//timestamp 
                                                                    
                                                                    
                                                                    result = insertPreparedStatementAttributes.executeUpdate();
                                                                    // SAVE EORGANISM
                                                                    saveEOrganismTable(eid,attributesArrayListString[j],ConfigFrame.EATTRIBUTE_TABLE,ConfigFrame.EATTRIBUTE,attributeId);
                                                                                                                                      
                                                                    //log4j.debug("EORGANISM ::loadOperationsAttributesMetadataToDB:: EATTRIBUTE:" +attributesArrayListString[j]);
                                                                    
                              }// for all the attributes
                        
                       }// if we have attributes
                                         
                       
                    //CLOSE
                    insertPreparedStatementMetadataOperations.close(); 
                    insertPreparedStatementSequenceItem.close(); 
                    insertPreparedStatementInterface.close();
                    insertPreparedStatementMetadataAttributes.close();
                    insertPreparedStatementAttributes.close();
                
                    connection.close();
                          
                    //log4j.debug("EORGANISM ::loadOperationsAttributesMetadataToDB :: loadOperationsAttributesMetadataToDB OK");
                
                } catch(SQLException sqle){
                    //JOptionPane.showMessageDialog( null,"SQLException  "    + sqle.toString()+"\n");
                	log4j.error("EORGANISM ::loadOperationsAttributesMetadataToDB :: SQLException: " + sqle.toString());
                	log4j.debug("EORGANISM ::loadOperationsAttributesMetadataToDB :: SQLException: " + sqle.toString());
                	saveLogging("EORGANISM ::loadOperationsAttributesMetadataToDB :: SQLException" + sqle.toString(),"EORGANISM.EOrganismController.loadOperationsAttributesMetadataToDB","attributesArrayListString");
                 	
                } catch(Exception e){
                    //JOptionPane.showMessageDialog( null, "Exception  " +e.toString()+"\n");
                	log4j.error("EORGANISM ::loadOperationsAttributesMetadataToDB :: Exception: " + e.toString());
                	log4j.debug("EORGANISM ::loadOperationsAttributesMetadataToDB :: Exception: " + e.toString());
                	saveLogging("EORGANISM ::loadOperationsAttributesMetadataToDB :: Exception" + e.toString(),"EORGANISM.EOrganismController.loadOperationsAttributesMetadataToDB","attributesArrayListString");
             
                }finally{
                    try{
                         
                         if (connection!=null){connection.close(); }
                    
                    }catch(SQLException sqle2){
                    	log4j.error("EORGANISM ::loadOperationsAttributesMetadataToDB :: SQLException:finally " + sqle2.toString());
                    	log4j.debug("EORGANISM ::loadOperationsAttributesMetadataToDB :: SQLException:finally " + sqle2.toString());
                    	saveLogging("EORGANISM ::loadOperationsAttributesMetadataToDB :: SQLException:finally" + sqle2.toString(),"EORGANISM.EOrganismController.loadOperationsAttributesMetadataToDB","attributesArrayListString");
                        
                    }//
                
                }//finally
                
        
        }//loadOperationsAttributesMetadataToDB
        
        
        public void loadApplicationLoaderFile(){
            
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            
            //int result = fileChooser.showSaveDialog(null);
            
            int result = fileChooser.showOpenDialog(null);
                                                                    
            if(result == JFileChooser.CANCEL_OPTION){
                    return;
            }// if
              
            File csvLoadFile = fileChooser.getSelectedFile();
         
             if( (csvLoadFile == null) || csvLoadFile.getName().equals("")){
                    
                    JOptionPane.showMessageDialog(  null,
                                                    "EORGANISM ::loadApplicationLoaderFile:: Open CSV LOAD :: Invalid CSV LOAD Project File",
                                                    "EORGANISM ::loadApplicationLoaderFile:: Open CSV LOAD :: Invalid CSV LOAD Project File",
                                                    JOptionPane.ERROR_MESSAGE);
                    log4j.error("EORGANISM ::loadApplicationLoaderFile:: Open CSV LOAD :: Invalid CSV LOAD Project File name=" + csvLoadFile.getName());
                	log4j.debug("EORGANISM ::loadApplicationLoaderFile:: Open CSV LOAD :: Invalid CSV LOAD Project File name=" +csvLoadFile.getName() );
                    
             } //
             
             String filePathName = csvLoadFile.getPath();

             log4j.debug("EORGANISM ::loadApplicationLoaderFile::****** ESERVICE load filePathName " + filePathName );  
             loadApplicationInventoryCSVFile(filePathName);
             
        }//loadApplicationLoaderFile
       
        public void loadApplicationInventoryCSVFile(String _fileLocation){
            //String csvFileToRead        = _file;  
            //BufferedReader br           = null;  
            String line                 = "";  
            String splitBy              = ",";  
             
            EApplication applicationObjectWorker    = new EApplication();  
            
            applicationInventoryArrayList = new ArrayList();
            
            try {  
              
                        br = new BufferedReader(new FileReader(_fileLocation));  
                /*
                        String[] applicationsArray = new String[27];
                        for(int i = 0; i < applicationsArray.length; i++) {
                                 applicationsArray[i] = "";
                        }
*/
                        while ((line = br.readLine()) != null) {  
                          
                            // split on comma(',')  
                            String[] applicationsArray = line.split(splitBy);  
                          
                            // create application object to store values  
                            applicationObjectWorker = new EApplication();  
                                     
                            	   	applicationObjectWorker.appID						 = applicationsArray[0];
                            	   
                            	 //log4j.debug("EORGANISM ::loadApplicationInventoryCSVFile::  BEFORE PREPEND applicationObjectWorker.getAppID() " +applicationObjectWorker.getAppID() );  
                            	   
                            	 	applicationObjectWorker.setAppID(prependZeroToString(applicationObjectWorker.getAppID(), "0", 4));//appid
             	                 
                                 //log4j.debug("EORGANISM ::loadApplicationInventoryCSVFile::  AFTER PREPEND applicationObjectWorker.getAppID() " +applicationObjectWorker.getAppID() );  
                                     
                                   applicationObjectWorker.eSisName                      = applicationsArray[1];		// esis
                                   applicationObjectWorker.eSis                          = applicationsArray[2];		// application name
                                   applicationObjectWorker.eSisSystemName                = applicationsArray[3];		// system name
                                   applicationObjectWorker.clasificationCriticality      = applicationsArray[4];
                                   applicationObjectWorker.clasificationService          = applicationsArray[5];
                                   applicationObjectWorker.clasificationCIM              = applicationsArray[6];
                                   applicationObjectWorker.clasificationPII              = applicationsArray[7];
                                   //applicationObjectWorker.clasificationPCI            = applicationsArray[9];
                                   //applicationObjectWorker.clasificationSOX            = applicationsArray[11];
                                   applicationObjectWorker.taxImpact                     = applicationsArray[8];     
                                   applicationObjectWorker.taxImpact                     = applicationsArray[9]; 	//10
                                   applicationObjectWorker.architecturalCriticality      = applicationsArray[10];
                                   applicationObjectWorker.descriptionFunctional         = applicationsArray[11];
                                   applicationObjectWorker.vp                            = applicationsArray[12];
                                   applicationObjectWorker.supportDirector               = applicationsArray[13];
                                   applicationObjectWorker.supportManager                = applicationsArray[14];
                                   applicationObjectWorker.developmentDirector           = applicationsArray[15];
                                   applicationObjectWorker.developmentManager            = applicationsArray[16];
                                   applicationObjectWorker.developmentPrime              = applicationsArray[17];
                                   applicationObjectWorker.ismWorkgroup                  = applicationsArray[18];
                                   applicationObjectWorker.status                        = applicationsArray[19];
                                   applicationObjectWorker.businessKPI                   = applicationsArray[20];
                                   applicationObjectWorker.isHpd                         = applicationsArray[21];
                                   applicationObjectWorker.isSiS                         = applicationsArray[22];
                                   applicationObjectWorker.isBsm                         = applicationsArray[23];
                                   applicationObjectWorker.isBPM                         = applicationsArray[24];
                                   applicationObjectWorker.isSho                         = applicationsArray[25];
                                   applicationObjectWorker.isShr                         = applicationsArray[26];      
                                   
                                   applicationObjectWorker.setApplicationNameGlobalEsis();          
                                   
                                   applicationObjectWorker.setApplicationName(applicationObjectWorker.getApplicationNameGlobal());
                                   
                                   
                             // adding service objects to a list  
                            applicationInventoryArrayList.add(applicationObjectWorker);  
                            //applicationObjectWorker.printToString();
                            //log4j.debug("EORGANISM ::loadApplicationInventoryCSVFile::  load OK " +applicationObjectWorker.eSis );  
                        
                    }  // while
                          // print values stored in carList  
                           
                        log4j.debug("EORGANISM ::loadApplicationInventoryCSVFile:: load OK for _fileLocation = " + _fileLocation);
                         
                          
                 } catch (FileNotFoundException fnfe) {  
                     				JOptionPane.showMessageDialog(  null,
                                                     "EORGANISM :loadApplicationFile: Open CSV LOAD :: FileNotFoundException",
                                                     "EORGANISM :loadApplicationFile: Open CSV LOAD :: FileNotFoundException",
                                                     JOptionPane.ERROR_MESSAGE);
                     				log4j.error("EORGANISM ::loadApplicationInventoryCSVFile:: FileNotFoundException _fileLocation = " + _fileLocation);
                     				log4j.error("EORGANISM ::loadApplicationInventoryCSVFile:: FileNotFoundException  = " + fnfe.toString());
                     			
                     				log4j.debug("EORGANISM ::loadApplicationInventoryCSVFile:: FileNotFoundException _fileLocation = " + _fileLocation);
                     				log4j.debug("EORGANISM ::loadApplicationInventoryCSVFile:: FileNotFoundException  = " + fnfe.toString());
                     				
                     				
                     				fnfe.printStackTrace();  
                 } catch (IOException ioe) {  
                	 				JOptionPane.showMessageDialog(  null,
                                                    "EORGANISM :loadApplicationFile: Open CSV LOAD :: IOException",
                                                    "EORGANISM :loadApplicationFile: Open CSV LOAD :: IOException",
                                                    JOptionPane.ERROR_MESSAGE);
                	 				
                	 				log4j.error("EORGANISM ::loadApplicationInventoryCSVFile:: IOException _fileLocation = " + _fileLocation);
                     				log4j.error("EORGANISM ::loadApplicationInventoryCSVFile:: IOException  = " + ioe.toString());
                     			
                     				log4j.debug("EORGANISM ::loadApplicationInventoryCSVFile:: IOException _fileLocation = " + _fileLocation);
                     				log4j.debug("EORGANISM ::loadApplicationInventoryCSVFile:: IOException  = " + ioe.toString());
                     				
                	 				ioe.printStackTrace();  
                } catch (Exception e) {  
                       				JOptionPane.showMessageDialog(  null,
                                                       "EORGANISM :loadApplicationFile: Open CSV LOAD :: File Format Exception",
                                                       "EORGANISM :loadApplicationFile: Open CSV LOAD :: File Format Exception",
                                                       JOptionPane.ERROR_MESSAGE);
                       				
                       				log4j.error("EORGANISM ::loadApplicationInventoryCSVFile:: Exception _fileLocation = " + _fileLocation);
                     				log4j.error("EORGANISM ::loadApplicationInventoryCSVFile:: Exception  = " + e.toString());
                     			
                     				log4j.debug("EORGANISM ::loadApplicationInventoryCSVFile:: Exception _fileLocation = " + _fileLocation);
                     				log4j.debug("EORGANISM ::loadApplicationInventoryCSVFile:: Exception  = " + e.toString());
                                    e.printStackTrace();  
                } finally {  
                           if (br != null) {  
                                try {  
                                    br.close();  
                                } catch (IOException e) {  
                                    e.printStackTrace();  
                                }  
                        }  //if
                }// finally

            
        }//loadApplicationInventoryCSVFile
    
        public void loadDatabaseInventoryFile(String _fileLocation){
            
            //String csvFileToRead        = _file;  
            //BufferedReader br           = null;  
            String line                 = "";  
            String splitBy              = ",";  
             
            EDatabasePhysical databaseObjectWorker    = new EDatabasePhysical();  
            
            try {  
              
                        br = new BufferedReader(new FileReader(_fileLocation));  
                
                        while ((line = br.readLine()) != null) {  
                          
                            // split on comma(',')  
                            String[] databaseArray = line.split(splitBy);  
                          
                            // create application object to store values  
                            databaseObjectWorker = new EDatabasePhysical();  
                            
                                databaseObjectWorker.databaseInstanceName   =databaseArray[0];//databaseInstanceName
                                databaseObjectWorker.description            =databaseArray[1];//database name
                                databaseObjectWorker.owner                  =databaseArray[3];// desc
                                 
                                databaseObjectWorker.environment            =databaseArray[5];// env
                                databaseObjectWorker.status                 =databaseArray[6];// status
                                databaseObjectWorker.databaseName           =databaseArray[7];// desc
                                databaseObjectWorker.version                =databaseArray[8];// desc
                                databaseObjectWorker.databaseCreateDate     =databaseArray[9];
                                         
                                databaseObjectWorker.failover               =databaseArray[10];
                                databaseObjectWorker.sad                    =databaseArray[12];
                                databaseObjectWorker.rac                    =databaseArray[13];

                                //databaseObjectWorker.serverName         	=databaseArray[14];//
                                
                                databaseObjectWorker.serverHostName         =databaseArray[14];//
                                
                                databaseObjectWorker.setServerName();		// first token from serverHostName is the server name from serrver inventory HW
                                
                                databaseObjectWorker.serverHostOS           =databaseArray[15];// OS
                        
                                databaseObjectWorker.serverEnvironment      =databaseArray[16];// environment PROD etc
                                  
                               // adding service objects to a list  
                               databaseInventoryArrayList.add(databaseObjectWorker);  
                               
                               //databaseObjectWorker.printToString();
                               //log4j.debug("EORGANISM ::loadDatabaseInventoryFile::  load OK - " +databaseObjectWorker.databaseInstanceName);  
                        
                    }  // while
                            
                        log4j.debug("EORGANISM ::loadDatabaseInventoryFile::  load OK for _fileLocation= " + _fileLocation);  
                
                 } catch (FileNotFoundException fnfe) {  
                     			JOptionPane.showMessageDialog(  null,
                                                     "EORGANISM :loadDatabaseInventoryFile: Open CSV LOAD :: FileNotFoundException",
                                                     "EORGANISM :loadDatabaseInventoryFile: Open CSV LOAD :: FileNotFoundException",
                                                     JOptionPane.ERROR_MESSAGE);
                     
			                     log4j.error("EORGANISM ::loadDatabaseInventoryFile:: FileNotFoundException _fileLocation = " + _fileLocation);
			      				 log4j.error("EORGANISM ::loadDatabaseInventoryFile:: FileNotFoundException  = " + fnfe.toString());
			      			
			      				 log4j.debug("EORGANISM ::loadDatabaseInventoryFile:: FileNotFoundException _fileLocation = " + _fileLocation);
			      				 log4j.debug("EORGANISM ::loadDatabaseInventoryFile:: FileNotFoundException  = " + fnfe.toString());
			                     
			                     fnfe.printStackTrace();  
                 } catch (IOException ioe) {  
                    JOptionPane.showMessageDialog(  null,
                                                    "EORGANISM :loadDatabaseInventoryFile: Open CSV LOAD :: IOException",
                                                    "EORGANISM :loadDatabaseInventoryFile: Open CSV LOAD :: IOException",
                                                    JOptionPane.ERROR_MESSAGE);
                    
			                    log4j.error("EORGANISM ::loadDatabaseInventoryFile:: IOException _fileLocation = " + _fileLocation);
			     				log4j.error("EORGANISM ::loadDatabaseInventoryFile:: IOException  = " + ioe.toString());
			     			
			     				log4j.debug("EORGANISM ::loadDatabaseInventoryFile:: IOException _fileLocation = " + _fileLocation);
			     				log4j.debug("EORGANISM ::loadDatabaseInventoryFile:: IOException  = " + ioe.toString());
                    			
                                ioe.printStackTrace();  
                } catch (Exception e) {  
                       JOptionPane.showMessageDialog(  null,
                                                       "EORGANISM :loadDatabaseInventoryFile: Open CSV LOAD :: File Format Exception",
                                                       "EORGANISM :loadDatabaseInventoryFile: Open CSV LOAD :: File Format Exception",
                                                       JOptionPane.ERROR_MESSAGE);
                       
		                        log4j.error("EORGANISM ::loadDatabaseInventoryFile:: Exception _fileLocation = " + _fileLocation);
		        				log4j.error("EORGANISM ::loadDatabaseInventoryFile:: Exception  = " + e.toString());
		        			
		        				log4j.debug("EORGANISM ::loadDatabaseInventoryFile:: Exception _fileLocation = " + _fileLocation);
		        				log4j.debug("EORGANISM ::loadDatabaseInventoryFile:: Exception  = " + e.toString());
                       			
                       			  
                                e.printStackTrace();  
                } finally {  
                           if (br != null) {  
                                try {  
                                    br.close();  
                                } catch (IOException e2) { 
                                	
                                	log4j.debug("EORGANISM ::loadDatabaseInventoryFile:: Exception _fileLocation = " + _fileLocation);
    		        				log4j.debug("EORGANISM ::loadDatabaseInventoryFile:: Exception  = " + e2.toString());
                                    e2.printStackTrace();  
                                }  
                        }  //if
                }// finally

            
        }//loadDatabaseInventoryFile
        
        public void loadServersInventoryFile(String _fileLocation){
            //String csvFileToRead        = _file;  
            //BufferedReader br           = null;  
            String line                 = "";  
            String splitBy              = ",";  
            String tempName				= "";
             
            EHardware hardwareObjectWorker    = new EHardware();  
            int i=0;
            try {  
              
                        br = new BufferedReader(new FileReader(_fileLocation));  
                /*
                        String[] hardwareArray = new String[27];
                        for(int i = 0; i < hardwareArray.length; i++) {
                                 hardwareArray[i] = "";
                        }
                */
                
                        while ((line = br.readLine()) != null) {  
                        	i++;
                            // split on comma(',')  
                            String[] hardwareArray = line.split(splitBy);  
                          
                            // create application object to store values  
                            hardwareObjectWorker = new EHardware();  
                             
                            // DEC 9, 2015
                            /*
                            0- SERVER HOSTNAME	
                            1- CINUM	
                            2- SERVER DESCRIPTION	
                            3- CI OWNER - PRIME SUPPORT DIRECTOR	
                            4- BUSINESS OWNER - PRIME SUPPORT MANAGER	
                            5- Application ID	
                            6- CRITICALITY	
                            7- APPLICATION DESCRIPTION	
                            8- APPLICATION SUPPORT DIRECTOR	
                            9 -APPLICATION SUPPORT MANAGER	
                            10- - MANAGED BY	
                            11- - IP ADDRESS	
                            12 - MANGEMENT IP ADDRESS
                            
                            13-- OPERATING ENVIRONMENT	
                            14 - CI OPERATING SYSTEM	
                            15 -O/S PLATFORM	
                            16 -VIRTUAL	
                            17- SERIALNUMBER	
                            18 - MANUFACTURER	
                            19 - MODEL	
                            20 - CPU TYPE	
                            21 - # of CPUs	
                            
                            22 - CORES PER CPU
                            23 - # OF CORES
                                                        
                            24 -MEMORYSIZE	
                            25- LOCATION	
                            26 -SERVER STATUS	
                            27 -APPLICATION STATUS	
                            28- ISM WORKGROUP	
                            29 -SOX
                            
                            30- INSTALL DATE
                            
                            */
                            
	                        hardwareObjectWorker.setHardwareName(hardwareArray[0]);//name
	                        hardwareObjectWorker.setCIName(hardwareArray[1]);//CI name
	                        hardwareObjectWorker.setDescription(hardwareArray[2]);// desc
	                        hardwareObjectWorker.setOwner(hardwareArray[3]);// owner
		                       
	                        hardwareObjectWorker.setBusinessOwner(hardwareArray[4]);// businessOwner
			                  
	                        hardwareObjectWorker.setApplicationID(hardwareArray[5]);// app Id
	                        
	                   	 //log4j.debug("EORGANISM ::loadServersInventoryFile::  BEFORE PREPEND hardwareObjectWorker.getApplicationID() " +hardwareObjectWorker.getApplicationID() );  
                         
	                        hardwareObjectWorker.setApplicationID(prependZeroToString(hardwareObjectWorker.getApplicationID(), "0", 4));
	                        
	                     //log4j.debug("EORGANISM ::loadServersInventoryFile::  AFTER PREPEND hardwareObjectWorker.getApplicationID() " +hardwareObjectWorker.getApplicationID() );  
	                         
	                        
	                        hardwareObjectWorker.setCriticality(hardwareArray[6]);// criticality
	                        //??
	                        hardwareObjectWorker.setApplicationDescription(hardwareArray[7]);// set application description from app description
	                        
	                        
	                        hardwareObjectWorker.setIp(hardwareArray[11]);
	                        //
	                        hardwareObjectWorker.setEnvironment(hardwareArray[13]);
	                        hardwareObjectWorker.setOperatingSystem(hardwareArray[14]);
	                        hardwareObjectWorker.setVersion(hardwareArray[15]);
	                        
	                        hardwareObjectWorker.setSerialNumber(hardwareArray[17]);
	                        hardwareObjectWorker.setManufacturer(hardwareArray[18]);
	                        hardwareObjectWorker.setModel(hardwareArray[19]);
	                        hardwareObjectWorker.setCPU(hardwareArray[20]);
	                        
	                        hardwareObjectWorker.setRamMemory(hardwareArray[24]);    
	                        hardwareObjectWorker.setLocation(hardwareArray[25]);// LOCATION
	                        hardwareObjectWorker.setStatus(hardwareArray[26]); // status
	                        
	                        hardwareObjectWorker.setApplicationNameGlobal();// correct the applciation names
	                        
	                        
	                        
                       // adding service objects to a list  
                       serverInventoryArrayList.add(hardwareObjectWorker);  
                       
                       //log4j.debug("EORGANISM :: loadServersInventoryFile :: ******- POSITION -"+i +"loadServersInventoryFile  load OK " +hardwareObjectWorker.toString() );  
                        
                    }  // while
                          // print values stored in carList  
                           
                        log4j.debug("EORGANISM :: loadServersInventoryFile ::  load OK for file = " + _fileLocation); 
                
                
                 } catch (FileNotFoundException fnfe) {  
                     				JOptionPane.showMessageDialog(  null,
                                                     "EORGANISM :loadServersInventoryFile: Open CSV LOAD :: FileNotFoundException",
                                                     "EORGANISM :loadServersInventoryFile: Open CSV LOAD :: FileNotFoundException",
                                                     JOptionPane.ERROR_MESSAGE);
                     				
				                     log4j.error("EORGANISM ::loadServersInventoryFile:: FileNotFoundException _fileLocation = " + _fileLocation);
				      				 log4j.error("EORGANISM ::loadServersInventoryFile:: FileNotFoundException  = " + fnfe.toString());
				      			
				      				 log4j.debug("EORGANISM ::loadServersInventoryFile:: FileNotFoundException _fileLocation = " + _fileLocation);
				      				 log4j.debug("EORGANISM ::loadServersInventoryFile:: FileNotFoundException  = " + fnfe.toString());
                     
				      				 fnfe.printStackTrace();  
                 } catch (IOException ioe) {  
                	 JOptionPane.showMessageDialog(  null,
                                                    "EORGANISM :loadServersInventoryFile: Open CSV LOAD :: IOException",
                                                    "EORGANISM :loadServersInventoryFile: Open CSV LOAD :: IOException",
                                                    JOptionPane.ERROR_MESSAGE);
                    
				                    log4j.error("EORGANISM ::loadServersInventoryFile:: IOException _fileLocation = " + _fileLocation);
				     				log4j.error("EORGANISM ::loadServersInventoryFile:: IOException  = " + ioe.toString());
				     			
				     				log4j.debug("EORGANISM ::loadServersInventoryFile:: IOException _fileLocation = " + _fileLocation);
				     				log4j.debug("EORGANISM ::loadServersInventoryFile:: IOException  = " + ioe.toString());
				     				
                                ioe.printStackTrace();  
                } catch (Exception e) {  
                       JOptionPane.showMessageDialog(  null,
                                                       "EORGANISM :loadServersInventoryFile: Open CSV LOAD :: File Format Exception",
                                                       "EORGANISM :loadServersInventoryFile: Open CSV LOAD :: File Format Exception",
                                                       JOptionPane.ERROR_MESSAGE);
                       
			                       	log4j.error("EORGANISM ::loadServersInventoryFile:: Exception _fileLocation = " + _fileLocation);
			       					log4j.error("EORGANISM ::loadServersInventoryFile:: Exception  = " + e.toString());
			       			
			       					log4j.debug("EORGANISM ::loadServersInventoryFile:: Exception _fileLocation = " + _fileLocation);
			       					log4j.debug("EORGANISM ::loadServersInventoryFile:: Exception  = " + e.toString());
			       					
                       
                                   e.printStackTrace();  
                } finally {  
                           if (br != null) {  
                                try {  
                                    br.close();  
                                } catch (IOException e) {  
                                    e.printStackTrace();  
                                }  
                        }  //if
                }// finally

            
        }//loadServersInventoryFile
        
        
        public void loadDatabaseMetadataFile(String _fileLocation){
            //String csvFileToRead        = _file;  
            //BufferedReader br           = null;  
            String line                 = "";  
            String splitBy              = ",";  
             
            eDBConnectionTemp = new EDBConnection();
            eDBConnectionVector = new Vector(); 
            
            try {  
                        br = new BufferedReader(new FileReader(_fileLocation));  
                        
                        while ((line = br.readLine()) != null) {  
                          
                                // split on comma(',')  
                                String[] databaseMetadataArray = line.split(splitBy);  
                              
                                // create application object to store values  
                                eDBConnectionTemp = new EDBConnection();  
                          
                                eDBConnectionTemp.setDatabaseName(databaseMetadataArray[0]);//name
                                eDBConnectionTemp.setDatabaseOwner(databaseMetadataArray[1]);//instance
                                eDBConnectionTemp.setDatabaseUsername(databaseMetadataArray[2]);//username
                                eDBConnectionTemp.setDatabasePassword(databaseMetadataArray[3]);//password
                                eDBConnectionTemp.setDatabaseURL(databaseMetadataArray[4]);//url
                                         
                                eDBConnectionVector.add(eDBConnectionTemp);    
                        
                                //log4j.debug("EORGANISM :: loadDatabaseMetadataFile ::  load OK " +eDBConnectionTemp.getDatabaseName() );  
                        
                    }  // while
                
                
                        log4j.debug("EORGANISM :: loadDatabaseMetadataFile ::  load OK for file" + _fileLocation );  
                        
                  } catch (FileNotFoundException e) {  
                     JOptionPane.showMessageDialog(  null,
                                                     "EORGANISM :loadDatabaseMetadataFile: Open CSV LOAD :: FileNotFoundException",
                                                     "EORGANISM :loadDatabaseMetadataFile: Open CSV LOAD :: FileNotFoundException",
                                                     JOptionPane.ERROR_MESSAGE);
                                e.printStackTrace();  
                 } catch (IOException e) {  
                    JOptionPane.showMessageDialog(  null,
                                                    "EORGANISM :loadDatabaseMetadataFile: Open CSV LOAD :: IOException",
                                                    "EORGANISM :loadDatabaseMetadataFile: Open CSV LOAD :: IOException",
                                                    JOptionPane.ERROR_MESSAGE);
                                e.printStackTrace();  
                } catch (Exception e) {  
                       JOptionPane.showMessageDialog(  null,
                                                       "EORGANISM :loadDatabaseMetadataFile: Open CSV LOAD :: File Format Exception",
                                                       "EORGANISM :loadDatabaseMetadataFile: Open CSV LOAD :: File Format Exception",
                                                       JOptionPane.ERROR_MESSAGE);
                                   e.printStackTrace();  
                } finally {  
                           if (br != null) {  
                                try {  
                                    br.close();  
                                } catch (IOException e) {  
                                    e.printStackTrace();  
                                }  
                        }  //if
                }// finally

            
        }//loadDatabaseMetadataFile(String _fileLocation)
        
  
        
        public String prependZeroToString(String _stringTempTo, String _tokentoPrepend, int _tokenWantedSize){
        	//log4j.debug("EORGANISM ::prependZeroToString:: INPUT STRING = " + _stringTempTo);
        	
        	while(_stringTempTo.length()< _tokenWantedSize){
        			
        			_stringTempTo=_tokentoPrepend+_stringTempTo;// prepend token until token size is 
        		
        	}// end while
          	
        	//log4j.debug("EORGANISM ::prependZeroToString:: OUTPUT STRING = " + _stringTempTo);
        	return _stringTempTo;
        	
        }//appendTrailingZeroToString
        
        public void loadApplicationInventoryToDatabase(){
        	
            PreparedStatement insertPreparedStatementApplicationInventory;
            EApplication applicationObjectWorker;
            
            loadApplicationLoaderFile();
            //
            databaseInitializer();
            checkConnection();
             // check all db connections
            //databaseConnectionsInitializer();
            //java.sql.Date today = new java.sql.Date(System.currentTimeMillis());
            int	eid			=0;
            int appId		=0;
            
            try{
                    insertPreparedStatementApplicationInventory  = connection.prepareStatement("INSERT INTO EAPPLICATION(EAPPLICATION_ID,EAPP_ID,EAPPLICATION_ESIS_NAME,EAPPLICATION_ESIS,EAPPLICATION_ESIS_SYSTEM_NAME,EAPPLICATION_CLASIFICATION, EAPPLICATION_CLASIF_SERV,EAPPLICATION_CLASIF_CRITIC,EAPPLICATION_CLASIFICATION_CIM,EAPPLICATION_CLASIFICATION_PII,EAPPLICATION_CLASIFICATION_PCI,EAPPLICATION_CLASIFICATION_SOX, EAPPLICATION_TAX_IMPACT,EAPPLICATION_ARCHITECT_CRITIC, EAPPLICATION_DESC_FUNCTIONAL,EAPPLICATION_VP,EAPPLICATION_SUP_DIRECTOR,EAPPLICATION_SUP_MANAGER,EAPPLICATION_DEV_DIRECTOR,EAPPLICATION_DEV_MANAGER,EAPPLICATION_DEV_PRIME,EAPPLICATION_ISM_GROUP,EAPPLICATION_STATUS,EAPPLICATION_BUSINESS_KPI,EAPPLICATION_IS_HPD,EAPPLICATION_IS_SIS, EAPPLICATION_IS_BSM,EAPPLICATION_IS_BPM,EAPPLICATION_IS_SHO,EAPPLICATION_IS_SHR,EAPPLICATION_CREATED_DATE,EAPPLICATION_LAST_UPDATED_USER,EAPPLICATION_LAST_UPDATED_APP,OWNER,METADATA_TYPE,EAPPLICATION_NAME,EAPPLICATION_SHORT_DESCRIPTION,EORGANISM_ID,EAPPLICATION_NAME_GLOBAL,ESYSTEM_NAME,CREATED_TIMESTAMP) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    
                    for (int i = 1; i < applicationInventoryArrayList.size(); i++) {  
                          
                          applicationObjectWorker    	= new EApplication();
                          applicationObjectWorker 		= (EApplication)applicationInventoryArrayList.get(i);
                          
                          // setup system name
                          applicationObjectWorker.setupSystemName();
                        
                          appId 	=  getSequenceNextValue(ConfigFrame.APPLICATION_TABLE, ConfigFrame.APPLICATION_TABLE_PK);
                          eid 		=  getSequenceNextValue(ConfigFrame.EORGANISM_OBJECTID_MAPPING,ConfigFrame.EORGANISM_OBJECTID);
                          
                         
                        insertPreparedStatementApplicationInventory.setInt(1, appId);//id
                        
                        insertPreparedStatementApplicationInventory.setString(2,applicationObjectWorker.appID);//app id - 
                        insertPreparedStatementApplicationInventory.setString(3,applicationObjectWorker.eSisName);
                        insertPreparedStatementApplicationInventory.setString(4,applicationObjectWorker.eSis);
                        insertPreparedStatementApplicationInventory.setString(5,applicationObjectWorker.eSisSystemName);
                        insertPreparedStatementApplicationInventory.setString(6,applicationObjectWorker.clasification);
                        insertPreparedStatementApplicationInventory.setString(7,applicationObjectWorker.clasificationService);
                        insertPreparedStatementApplicationInventory.setString(8,applicationObjectWorker.clasificationCriticality);
                        insertPreparedStatementApplicationInventory.setString(9,applicationObjectWorker.clasificationCIM);
                        insertPreparedStatementApplicationInventory.setString(10,applicationObjectWorker.clasificationPII);
                        insertPreparedStatementApplicationInventory.setString(11,applicationObjectWorker.clasificationPCI);
                        insertPreparedStatementApplicationInventory.setString(12,applicationObjectWorker.clasificationSOX);
                        insertPreparedStatementApplicationInventory.setString(13,applicationObjectWorker.taxImpact);
                        insertPreparedStatementApplicationInventory.setString(14,applicationObjectWorker.architecturalCriticality);
                        insertPreparedStatementApplicationInventory.setString(15,applicationObjectWorker.descriptionFunctional);
                        insertPreparedStatementApplicationInventory.setString(16,applicationObjectWorker.vp);
                        insertPreparedStatementApplicationInventory.setString(17,applicationObjectWorker.supportDirector);
                        insertPreparedStatementApplicationInventory.setString(18,applicationObjectWorker.supportManager);
                        insertPreparedStatementApplicationInventory.setString(19,applicationObjectWorker.developmentDirector);
                        insertPreparedStatementApplicationInventory.setString(20,applicationObjectWorker.developmentManager);
                        insertPreparedStatementApplicationInventory.setString(21,applicationObjectWorker.developmentPrime);
                        insertPreparedStatementApplicationInventory.setString(22,applicationObjectWorker.ismWorkgroup);
                        insertPreparedStatementApplicationInventory.setString(23,applicationObjectWorker.status);
                        insertPreparedStatementApplicationInventory.setString(24,applicationObjectWorker.businessKPI);
                        insertPreparedStatementApplicationInventory.setString(25,applicationObjectWorker.isHpd);
                        insertPreparedStatementApplicationInventory.setString(26,applicationObjectWorker.isSiS);
                        insertPreparedStatementApplicationInventory.setString(27,applicationObjectWorker.isBsm);
                        insertPreparedStatementApplicationInventory.setString(28,applicationObjectWorker.isBPM);
                        insertPreparedStatementApplicationInventory.setString(29,applicationObjectWorker.isSho);
                        insertPreparedStatementApplicationInventory.setString(30,applicationObjectWorker.isShr);
                        
                        insertPreparedStatementApplicationInventory.setDate(31, today);//created date
                        insertPreparedStatementApplicationInventory.setString(32,   ConfigFrame.ADMIN_USER_NAME +"@"+ ConfigFrame.ADMIN_USER_ROLE);//last updated user
                        insertPreparedStatementApplicationInventory.setString(33,   ConfigFrame.LOAD_APP +"@"+ConfigFrame.LOAD_ACTION);// LOAD_APP  @ LOAD ACTION
                        
                        // 
                        insertPreparedStatementApplicationInventory.setString(34,  applicationObjectWorker.supportDirector);//owner
                        insertPreparedStatementApplicationInventory.setString(35,  ConfigFrame.APPLICATION_TYPE_INVENTORY);//metadata type INVENTORY
                        
                        //
                        insertPreparedStatementApplicationInventory.setString(36,  applicationObjectWorker.getApplicationName() );//application name
                        insertPreparedStatementApplicationInventory.setString(37,  applicationObjectWorker.eSisName);//system name, description
                        insertPreparedStatementApplicationInventory.setString(38,  applicationObjectWorker.eSisSystemName);//system name
                        
                        //dec 2015
                        insertPreparedStatementApplicationInventory.setInt(39,  eid);//eid
                        insertPreparedStatementApplicationInventory.setString(40,  applicationObjectWorker.getApplicationNameGlobal());//application_global name
                        insertPreparedStatementApplicationInventory.setString(41,  applicationObjectWorker.getSystemName());//ESYSTEM_NAME
                        
                        insertPreparedStatementApplicationInventory.setTimestamp(42, new java.sql.Timestamp(System.currentTimeMillis()));//timestamp 
                        
                        result = insertPreparedStatementApplicationInventory.executeUpdate();
                        
                        //log4j.debug("###############");
                        //log4j.debug("################loadMetadata:: insertPreparedStatementApplicationInventory:EORGANISM" +applicationObjectWorker.eSis);
                        //log4j.debug("###############");
                        
                        // SAVE EORGANISM
                        saveEOrganismTable(eid,applicationObjectWorker.eSisName,ConfigFrame.APPLICATION_TABLE,ConfigFrame.APPLICATION, appId);
                        
                    }  // i for object in ArrayList
                
                    //CLOSE
                    insertPreparedStatementApplicationInventory.close(); 
                    //insertPreparedStatementMetadataInterface.close();
                    //insertPreparedStatementMetadataAttribute.close();
                 
                    connection.close();
                          
                    //JOptionPane.showMessageDialog( null, "EORGANISM :: insertPreparedStatementApplicationInventory OK");
                    log4j.debug("loadMetadata:: insertPreparedStatementApplicationInventory:APP INVENTORY EORGANISM");
                       
                        
                } catch(SQLException sqle){
                    JOptionPane.showMessageDialog( null,"SQLException  "    + sqle.toString()+"\n");
                    log4j.error("insertPreparedStatementApplicationInventory:: SQLException: " + sqle.toString());
                    
                } catch(Exception e){
                    JOptionPane.showMessageDialog( null, "Exception  " +e.toString()+"\n");
                    log4j.error("insertPreparedStatementApplicationInventory:: Exception: " + e.toString());
            
                }finally{
                    try{
                         
                         if (connection!=null){connection.close(); }
                    
                    }catch(SQLException sqle2){
                    	log4j.error("insertPreparedStatementApplicationInventory:: SQLException:finally " + sqle2.toString());
                    }//
                
                }//finally
             
            
        }// end loadApplicationInventoryToDatabase
        
        
        public void loadServerInventoryToDatabase(String _loc){
            loadServersInventoryFile(_loc);
            
            Statement         	selectStatementApplications;
            Statement         	selectStatementLocation;
            
            ResultSet 			resultSetApplications;
            ResultSet 			resultSetLocation;
              
            PreparedStatement 	 insertPreparedStatementServerInventory;
            PreparedStatement    insertPreparedStatementApplicationNew;
            PreparedStatement    selectPreparedStatementLocation;
              
            int 		appIdValue			=0;
            String 		applicationName		="";
            String 		tempLocation		="";
            
            boolean 	resultBoolean		= false;
            EHardware hardwareObjectWorker;
            
            databaseInitializer();
            checkConnection();
            
            int	eid				=0;
            int serverId		=0;
            
            String applicationNameTemp = "";
             //java.sql.Date today = new java.sql.Date(System.currentTimeMillis());
             
            try{
                    //LOCATION_NAME, DATA_CENTER_LOCATION
            		//SELECT EDATACENTER_CODE FROM ELOCATION_MAPPING WHERE ELOCATION_NAME='CA-GSCAA+T-0050/BLDG - 50 Reid Dr; Barrie; ON; CA; L4N 0M4 (BDC)'; 
             	 	selectStatementLocation                  	= connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);                
                 
            	 	insertPreparedStatementServerInventory      = connection.prepareStatement("INSERT INTO EHARDWARE_PHYSICAL_TABLE(EHARDWARE_PHYSICAL_ID,EHARDWARE_PHYSICAL_NAME,CINAME,EHARDWARE_PHYSICAL_DESCRIPTION,APPLICATION_DESCRIPTION,OWNER,EHARDWARE_PHYSICAL_IP,EENVIRONMENT_NAME_FK,OPERATING_SYSTEM,OPERATING_SYSTEM_VERSION,SERIAL_NUMBER,MANUFACTURER,MODEL,CPU_NAME,MEMORY,LOCATION,STATUS,EORGANISM_EID,EAPPLICATION_ID,EAPPLICATION_NAME_GLOBAL,LOCATION_NAME,CREATED_TIMESTAMP) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");  
                    insertPreparedStatementApplicationNew       = connection.prepareStatement("INSERT INTO EAPPLICATION(EAPPLICATION_ID,EAPPLICATION_NAME,METADATA_TYPE,EAPPLICATION_CREATED_DATE,EAPPLICATION_LAST_UPDATED_USER,EAPPLICATION_LAST_UPDATED_APP,EORGANISM_ID,EAPPLICATION_NAME_GLOBAL,CREATED_TIMESTAMP) VALUES (?,?,?,?,?,?,?,?,?)");
                    
                    selectStatementApplications                 =  connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                  
                    for (int i = 1; i < serverInventoryArrayList.size(); i++) {  
                          
                          hardwareObjectWorker    = new EHardware();
                          hardwareObjectWorker = (EHardware)serverInventoryArrayList.get(i);
                          
                        //  
                        //log4j.debug("###1 NEW :: selectPreparedStatementLocation-" + hardwareObjectWorker.getLocation());
                        
                        // dec 30 2015
                        //log4j.debug("###1 1 NEW :: SELECT -" + "SELECT DISTINCT EDATACENTER_CODE FROM ELOCATION_MAPPING WHERE ELOCATION_CODE='"+hardwareObjectWorker.getLocation()+"'");
                        
                        resultSetLocation 	= selectStatementLocation.executeQuery(ConfigFrame.SELECT_EDATACENTER_CODE_FROM_ELOCATION_MAPPING +hardwareObjectWorker.getLocation()+"'");
                        while(resultSetLocation.next() ){
                        	
                        	tempLocation=resultSetLocation.getString(1);
                        	
                        	//log4j.debug("###2 NEW :: selectPreparedStatementLocation-tempLocation CODE" + tempLocation);
                        	hardwareObjectWorker.setLocationCode(tempLocation);
                        	//log4j.debug("###3 NEW :: selectPreparedStatementLocation-hardwareObjectWorker.setLocationCode" + hardwareObjectWorker.getLocationCode());
                             
                        }
	                  
                        serverId	=  getSequenceNextValue(ConfigFrame.EHARDWARE_PHYSICAL_TABLE,ConfigFrame.EHARDWARE_PHYSICAL_ID);
                        eid 		=  getSequenceNextValue(ConfigFrame.EORGANISM_OBJECTID_MAPPING,ConfigFrame.EORGANISM_OBJECTID);
                        
                        insertPreparedStatementServerInventory.setInt(1, serverId);//id
                         
                        insertPreparedStatementServerInventory.setString(2,hardwareObjectWorker.getHardwareName()); //server name
                        insertPreparedStatementServerInventory.setString(3,hardwareObjectWorker.getCIName());// ci name
                        insertPreparedStatementServerInventory.setString(4,hardwareObjectWorker.getDescription());// desc
                        insertPreparedStatementServerInventory.setString(5,hardwareObjectWorker.getApplicationDescription());// app description
                                                                                                                        
                        insertPreparedStatementServerInventory.setString(6,hardwareObjectWorker.getOwner());//owner
                        insertPreparedStatementServerInventory.setString(7,hardwareObjectWorker.getIp());// ip
                        insertPreparedStatementServerInventory.setString(8,hardwareObjectWorker.getEnvironment());// env
                        insertPreparedStatementServerInventory.setString(9,hardwareObjectWorker.getOperatingSystem());// os
                        
                        insertPreparedStatementServerInventory.setString(10,hardwareObjectWorker.getVersion());// version                     
                        insertPreparedStatementServerInventory.setString(11,hardwareObjectWorker.getSerialNumber());// serial number
                        insertPreparedStatementServerInventory.setString(12,hardwareObjectWorker.getManufacturer());// manufacturer
                        insertPreparedStatementServerInventory.setString(13,hardwareObjectWorker.getModel());// model
                        insertPreparedStatementServerInventory.setString(14,hardwareObjectWorker.getCPU());// CPU
                        insertPreparedStatementServerInventory.setString(15,hardwareObjectWorker.getRamMemory());// RAM
                        insertPreparedStatementServerInventory.setString(16,hardwareObjectWorker.getLocation());// location
                        insertPreparedStatementServerInventory.setString(17,hardwareObjectWorker.getStatus());// status
                        insertPreparedStatementServerInventory.setInt(18,eid);// eid
                        insertPreparedStatementServerInventory.setString(19,hardwareObjectWorker.getApplicationID());// application ID
                        insertPreparedStatementServerInventory.setString(20,hardwareObjectWorker.getApplicationNameGlobal());// application Global name
                        insertPreparedStatementServerInventory.setString(21,hardwareObjectWorker.getLocationCode());// location code
                        
                        insertPreparedStatementServerInventory.setTimestamp(22, new java.sql.Timestamp(System.currentTimeMillis()));//timestamp 
                          
                        //LOCATION
                          
                        result = insertPreparedStatementServerInventory.executeUpdate();
                        
                        // SAVE EORGANISM
                        saveEOrganismTable(eid,hardwareObjectWorker.getHardwareName() + "@" + hardwareObjectWorker.getApplicationDescription(),ConfigFrame.HARDWARE_TABLE,ConfigFrame.HARDWARE, serverId);
                        
                        //log4j.debug("NEW :: insertPreparedStatementServerInventory:HARDWARE-" + hardwareObjectWorker.getHardwareName()+"@ APP-"+hardwareObjectWorker.getApplication());
                           
                    }  // i for object in ArrayList
                    
                    // insert into applications
                    
                   
                	//"SELECT DISTINCT EAPPLICATION_NAME_GLOBAL FROM EHARDWARE_PHYSICAL_TABLE ORDER BY EAPPLICATION_NAME_GLOBAL"
                	     
                    resultSetApplications    =  selectStatementApplications.executeQuery(ConfigFrame.SELECT_EAPPLICATION_NAME_GLOBAL_FROM_EHARDWARE_PHYSICAL_TABLE);
                    
                    if(resultSetApplications!=null){
                    	  
                        while(resultSetApplications.next() ){
                        	
                            applicationName = resultSetApplications.getString(1);
                            
                            //log4j.debug("loadServerInventoryToDatabase::resultSetApplications!=null:: applicationName=" + applicationName);  
                            
                            //
                            appIdValue 		=  getSequenceNextValue(ConfigFrame.APPLICATION_TABLE,ConfigFrame.APPLICATION_TABLE_PK);
                            eid 			=  getSequenceNextValue(ConfigFrame.EORGANISM_OBJECTID_MAPPING,ConfigFrame.EORGANISM_OBJECTID);
                             
                           
                            insertPreparedStatementApplicationNew.setInt(1, appIdValue);
                            insertPreparedStatementApplicationNew.setString(2, applicationName);// application name
                            insertPreparedStatementApplicationNew.setString(3, ConfigFrame.APPLICATION_TYPE_PHYSICAL);// metadata type
                            
                            insertPreparedStatementApplicationNew.setDate(4, today);// obj id
                            insertPreparedStatementApplicationNew.setString(5,  ConfigFrame.ADMIN_USER_NAME +"@"+ ConfigFrame.ADMIN_USER_ROLE);//last updated user
                            insertPreparedStatementApplicationNew.setString(6,  "LOAD_SERVERS_HW" +"@"+ConfigFrame.LOAD_ACTION);// LOAD_APP  @ LOAD ACTION
                            insertPreparedStatementApplicationNew.setInt(7, eid);//eid 
                            insertPreparedStatementApplicationNew.setString(8, applicationName);//application global name 
                            insertPreparedStatementApplicationNew.setTimestamp(9, new java.sql.Timestamp(System.currentTimeMillis()));//timestamp 
                            
                            result = insertPreparedStatementApplicationNew.executeUpdate();
                            // SAVE EORGANISM
                            saveEOrganismTable(eid,applicationName,ConfigFrame.APPLICATION_TABLE,ConfigFrame.APPLICATION, appIdValue);
                            
                            //log4j.debug("loadServerInventoryToDatabase::PHYSICAL:: applicationName=" + applicationName);  
                            //log4j.debug("loadServerInventoryToDatabase::PHYSICAL:: applicationName=" + applicationNameTemp);        
                            
                        }// while
                   
                    }// if
                    
                    //CLOSE
                    selectStatementApplications.close();
                    selectStatementLocation.close();
                    
                    
                    insertPreparedStatementApplicationNew.close(); 
                    insertPreparedStatementServerInventory.close(); 
                    
                    connection.close();
                          
                    //JOptionPane.showMessageDialog( null, "EORGANISM :: insertPreparedStatementServerInventory OK");
                
                    //log4j.debug("EORGANISM :: insertPreparedStatementServerInventory:: LOAD SERVERS INVENTORY TO DB OK");
                
                } catch(SQLException sqle){
                    JOptionPane.showMessageDialog( null,"SQLException  "    + sqle.toString()+"\n");
                    log4j.error("insertPreparedStatementServerInventory:: SQLException: " + sqle.toString());
                    
                } catch(Exception e){
                    JOptionPane.showMessageDialog( null, "Exception  " +e.toString()+"\n");
                    log4j.error("insertPreparedStatementServerInventory:: Exception: " + e.toString());
            
                }finally{
                    try{
                    	log4j.debug("insertPreparedStatementServerInventory:: SQLException:finally try ");
                         
                    	 if (connection!=null)
                         {connection.close(); }
                    
                    }catch(SQLException sqle2){
                    	log4j.error("insertPreparedStatementServerInventory:: SQLException:finally " + sqle2.toString());
                    }//
                
                }//finally
             
            
        }// end loadServerInventoryToDatabase
        
        public void saveLocationsMappingToDB(){
           
            //log4j.debug("DEC 20:: saveLocationsMappingToDB - 1");
            
            PreparedStatement    insertPreparedStatementLocationMapping;
            
            
            EHardware hardwareObjectWorker;
            
            databaseInitializer();
            checkConnection();
            
            int locationMappingID		=  0;
            int eid 					=  0; 
             
            try{
                    
            		insertPreparedStatementLocationMapping      = connection.prepareStatement("INSERT INTO ELOCATION_MAPPING(ELOCATION_MAPPING_ID,ELOCATION_CODE,ELOCATION_NAME,ELOCATION_ADDRESS,EDATACENTER_CODE,EDATACENTER_NAME,EORGANISM_ID) VALUES (?,?,?,?,?,?,?)");  
                      
            		//log4j.debug("DEC 20:: saveLocationsMappingToDB - 2");
            	          
                    for (int i = 1; i < locationMappingArrayList.size(); i++) {  
                          
                          hardwareObjectWorker    	= new EHardware();
                          hardwareObjectWorker 		= (EHardware)locationMappingArrayList.get(i);
                          
                          locationMappingID		=  0;
                          eid 					=  0; 
	                  
                        locationMappingID	=  getSequenceNextValue("ELOCATION_MAPPING","ELOCATION_MAPPING_ID");
                        eid 				=  getSequenceNextValue(ConfigFrame.EORGANISM_OBJECTID_MAPPING,ConfigFrame.EORGANISM_OBJECTID);
                        
                        
                        insertPreparedStatementLocationMapping.setInt(1, locationMappingID);//id
                         
                        insertPreparedStatementLocationMapping.setString(2,hardwareObjectWorker.getLocationCode()); //location code
                        insertPreparedStatementLocationMapping.setString(3,hardwareObjectWorker.getLocation());//  name
                        insertPreparedStatementLocationMapping.setString(4,hardwareObjectWorker.getLocationAddress());// location address name
                        insertPreparedStatementLocationMapping.setString(5,hardwareObjectWorker.getLocationDataCenterCode());// Data Center code
                        insertPreparedStatementLocationMapping.setString(6,hardwareObjectWorker.getLocationDataCenterName());// Data Center Name
                        insertPreparedStatementLocationMapping.setInt(7,eid);// eid
                          
                        //LOCATION
                          
                        result = insertPreparedStatementLocationMapping.executeUpdate();
                        
                        // SAVE EORGANISM
                        saveEOrganismTable(eid,"","ELOCATION_MAPPING",ConfigFrame.HARDWARE + "LOCATION MAPPING", locationMappingID);
                        
                        //log4j.debug("NEW :: insertPreparedStatementLocationMapping-" + hardwareObjectWorker.getLocationCode() +":"+hardwareObjectWorker.getLocation() +":"+hardwareObjectWorker.getLocationDataCenterCode()+":"+hardwareObjectWorker.getLocationDataCenterName());
                           
                    }  // i for object in ArrayList
                    
                   
                    
                    insertPreparedStatementLocationMapping.close(); 
                    
                    connection.close();
                          
                    //JOptionPane.showMessageDialog( null, "EORGANISM :: insertPreparedStatementServerInventory OK");
                
                    log4j.debug("EORGANISM :: insertPreparedStatementLocationMapping:: LOAD LOCATION MAPPING TO DB OK");
                
                } catch(SQLException sqle){
                    JOptionPane.showMessageDialog( null,"SQLException  "    + sqle.toString()+"\n");
                    log4j.error("insertPreparedStatementLocationMapping:: SQLException: " + sqle.toString());
                    
                } catch(Exception e){
                    JOptionPane.showMessageDialog( null, "Exception  " +e.toString()+"\n");
                    log4j.error("insertPreparedStatementLocationMapping:: Exception: " + e.toString());
            
                }finally{
                    try{
                         
                         if (connection!=null){connection.close(); }
                    
                    }catch(SQLException sqle2){
                    	log4j.error("insertPreparedStatementLocationMapping:: SQLException:finally " + sqle2.toString());
                    }//
                
                }//finally
             
            
        }// end saveLocationsMappingToDB
        
        
        public void loadDatabaseInventoryToDatabase(String _loc){
            
            loadDatabaseInventoryFile(_loc);
            
            PreparedStatement insertPreparedStatementDatabaseInventory;
            
            EDatabasePhysical databaseObjectWorker;
            
            databaseInitializer();
            checkConnection();
            
            int	eid				=0;
            int databaseId		=0;
            
            // check all db connections
            //databaseConnectionsInitializer();
             
            //java.sql.Date today = new java.sql.Date(System.currentTimeMillis());
            
            String              tempString      = "";
            StringTokenizer     tokenizerString ;
            int count           =0;
            
            try{
                    
                        		insertPreparedStatementDatabaseInventory  = connection.prepareStatement("INSERT INTO EDATABASE(EDATABASE_ID,EDATABASE_NAME,EDATABASE_DESCRIPTION,OWNER,EENVIRONMENT_NAME_FK,STATUS,DATABASE_NAME,DATABASE_VERSION,FAILOVER,RAC,CINAME,EHARDWARE_NAME_FK,EHARDWARE_OS,EORGANISM_EID,METADATA_TYPE,SERVER_HOSTNAME,CREATED_TIMESTAMP) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                     
                                for (int i = 1; i < databaseInventoryArrayList.size(); i++) {  
                                      
                                    databaseObjectWorker      = new EDatabasePhysical();
                                    databaseObjectWorker      = (EDatabasePhysical)databaseInventoryArrayList.get(i);
                                           
                                    databaseId  =  getSequenceNextValue(ConfigFrame.DATABASE_TABLE,ConfigFrame.DATABASE_TABLE_PK);
                                    eid 		=  getSequenceNextValue(ConfigFrame.EORGANISM_OBJECTID_MAPPING,ConfigFrame.EORGANISM_OBJECTID);
                                    
                                    /*
                                    resultSetApplicationInfo 	= selectStatementApplicationInfo.executeQuery(ConfigFrame.SELECT_APPLICATION_ID_FROM_EHARDWARE_PHYSICAL_TABLE +databaseObjectWorker.serverName+"'");
                                    while(resultSetApplicationInfo.next() ){
                                    	
                                    	tempLocation=resultSetApplicationInfo.getString(1);
                                          
                                    }*/
                                        
                                    insertPreparedStatementDatabaseInventory.setInt(1, databaseId);//id
                                    insertPreparedStatementDatabaseInventory.setString(2,databaseObjectWorker.databaseInstanceName); //instance name
                                    insertPreparedStatementDatabaseInventory.setString(3,databaseObjectWorker.description);// ci name
                                    insertPreparedStatementDatabaseInventory.setString(4,databaseObjectWorker.owner);// OWNER
                                    insertPreparedStatementDatabaseInventory.setString(5,databaseObjectWorker.environment);// env
                                    insertPreparedStatementDatabaseInventory.setString(6,databaseObjectWorker.status);//
                                    insertPreparedStatementDatabaseInventory.setString(7,databaseObjectWorker.databaseName);// database name
                                    insertPreparedStatementDatabaseInventory.setString(8,databaseObjectWorker.version);// version
                                    insertPreparedStatementDatabaseInventory.setString(9,databaseObjectWorker.failover);// 
                                    insertPreparedStatementDatabaseInventory.setString(10,databaseObjectWorker.rac);// rac
                                    insertPreparedStatementDatabaseInventory.setString(11,databaseObjectWorker.CIName);// CIName
                                    insertPreparedStatementDatabaseInventory.setString(12,databaseObjectWorker.serverName);// hardware physical name for matching hw inventory
                                    insertPreparedStatementDatabaseInventory.setString(13,databaseObjectWorker.serverHostOS);// database server OS
                                    insertPreparedStatementDatabaseInventory.setInt(14, eid);//eid 
                                    insertPreparedStatementDatabaseInventory.setString(15, ConfigFrame.PHYSICAL);//metadata type 
                                    insertPreparedStatementDatabaseInventory.setString(16, databaseObjectWorker.serverHostName);//SERVER_HOSTNAME - full server name
                                    
                                    insertPreparedStatementDatabaseInventory.setTimestamp(17, new java.sql.Timestamp(System.currentTimeMillis()));//timestamp 
                                    
                                    result = insertPreparedStatementDatabaseInventory.executeUpdate();
                                    
                                   // SAVE EORGANISM
                                    saveEOrganismTable(eid,databaseObjectWorker.databaseInstanceName,ConfigFrame.DATABASE_TABLE,ConfigFrame.DATABASE, databaseId);
                                    
                                    //log4j.debug("loadMetadata:: insertPreparedStatementDatabaseInventory:EORGANISM - " + databaseObjectWorker.databaseInstanceName);
                                   
                                }  // i for object in ArrayList
                
                                //log4j.debug("loadMetadata:: insertPreparedStatementDatabaseInventory:EORGANISM -OK for file  " + _loc);
                                
                
                    //CLOSE
                    insertPreparedStatementDatabaseInventory.close(); 
                    connection.close();
                            
                } catch(SQLException sqle){
                    JOptionPane.showMessageDialog( null,"SQLException  "    + sqle.toString()+"\n");
                    log4j.error("insertPreparedStatementDatabaseInventory:: SQLException: " + sqle.toString());
                    
                } catch(Exception e){
                    JOptionPane.showMessageDialog( null, "Exception  " +e.toString()+"\n");
                    log4j.error("insertPreparedStatementDatabaseInventory:: Exception: " + e.toString());
            
                }finally{
                    try{
                         
                         if (connection!=null){connection.close(); }
                    
                    }catch(SQLException sqle2){
                    	log4j.error("insertPreparedStatementDatabaseInventory:: SQLException:finally " + sqle2.toString());
                    }//
                
                }//finally
             
            
        }// end loadDatabaseInventoryToDatabase
  
        
        public void loadApplicationInventoryToDatabase(String _fileName){
               
            PreparedStatement insertPreparedStatementApplicationInventory;
            
            EApplication applicationObjectWorker;
            
            loadApplicationInventoryCSVFile(_fileName);
            //
            databaseInitializer();
            checkConnection();
            
            // check all db connections
            //databaseConnectionsInitializer();
             
            //java.sql.Date today = new java.sql.Date(System.currentTimeMillis());
            
            int	eid			=0;
            int appId		=0;
             
            try{
                    
                    insertPreparedStatementApplicationInventory  = connection.prepareStatement("INSERT INTO EAPPLICATION(EAPPLICATION_ID,EAPPLICATION_APPLICATION_ID, EAPPLICATION_ESIS_NAME,EAPPLICATION_ESIS,EAPPLICATION_ESIS_SYSTEM_NAME,EAPPLICATION_CLASIFICATION, EAPPLICATION_CLASIF_SERV,EAPPLICATION_CLASIF_CRITIC,EAPPLICATION_CLASIFICATION_CIM,EAPPLICATION_CLASIFICATION_PII,EAPPLICATION_CLASIFICATION_PCI,EAPPLICATION_CLASIFICATION_SOX, EAPPLICATION_TAX_IMPACT,EAPPLICATION_ARCHITECT_CRITIC, EAPPLICATION_DESC_FUNCTIONAL,EAPPLICATION_VP,EAPPLICATION_SUP_DIRECTOR,EAPPLICATION_SUP_MANAGER,EAPPLICATION_DEV_DIRECTOR,EAPPLICATION_DEV_MANAGER,EAPPLICATION_DEV_PRIME,EAPPLICATION_ISM_GROUP,EAPPLICATION_STATUS,EAPPLICATION_BUSINESS_KPI,EAPPLICATION_IS_HPD,EAPPLICATION_IS_SIS, EAPPLICATION_IS_BSM,EAPPLICATION_IS_BPM,EAPPLICATION_IS_SHO,EAPPLICATION_IS_SHR,EAPPLICATION_CREATED_DATE,EAPPLICATION_LAST_UPDATED_USER,EAPPLICATION_LAST_UPDATED_APP,OWNER,METADATA_TYPE,EENVIRONMENT_NAME_FK,EAPPLICATION_NAME,EAPPLICATION_DESCRIPTION,EORGANISM_ID,EAPPLICATION_NAME_GLOBAL,ESYSTEM_NAME,CREATED_TIMESTAMP) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                      
                    for (int i = 1; i < applicationInventoryArrayList.size(); i++) {  
                          
                          applicationObjectWorker    = new EApplication();
                          applicationObjectWorker 	= (EApplication)applicationInventoryArrayList.get(i);
                          
                       // setup system name
                          applicationObjectWorker.setupSystemName();
                           
                          appId 	=  getSequenceNextValue(ConfigFrame.APPLICATION_TABLE, ConfigFrame.APPLICATION_TABLE_PK);
                          eid 		=  getSequenceNextValue(ConfigFrame.EORGANISM_OBJECTID_MAPPING,ConfigFrame.EORGANISM_OBJECTID);
                       
                        insertPreparedStatementApplicationInventory.setInt(1, appId);//id
                        insertPreparedStatementApplicationInventory.setString(2,applicationObjectWorker.appID);                      //app id
                        insertPreparedStatementApplicationInventory.setString(3,applicationObjectWorker.eSisName);
                        insertPreparedStatementApplicationInventory.setString(4,applicationObjectWorker.eSis);
                        insertPreparedStatementApplicationInventory.setString(5,applicationObjectWorker.eSisSystemName);
                        insertPreparedStatementApplicationInventory.setString(6,applicationObjectWorker.clasification);
                        insertPreparedStatementApplicationInventory.setString(7,applicationObjectWorker.clasificationService);
                        insertPreparedStatementApplicationInventory.setString(8,applicationObjectWorker.clasificationCriticality);
                        insertPreparedStatementApplicationInventory.setString(9,applicationObjectWorker.clasificationCIM);
                        insertPreparedStatementApplicationInventory.setString(10,applicationObjectWorker.clasificationPII);
                        insertPreparedStatementApplicationInventory.setString(11,applicationObjectWorker.clasificationPCI);
                        insertPreparedStatementApplicationInventory.setString(12,applicationObjectWorker.clasificationSOX);
                        insertPreparedStatementApplicationInventory.setString(13,applicationObjectWorker.taxImpact);
                        insertPreparedStatementApplicationInventory.setString(14,applicationObjectWorker.architecturalCriticality);
                        insertPreparedStatementApplicationInventory.setString(15,applicationObjectWorker.descriptionFunctional);
                        insertPreparedStatementApplicationInventory.setString(16,applicationObjectWorker.vp);
                        insertPreparedStatementApplicationInventory.setString(17,applicationObjectWorker.supportDirector);
                        insertPreparedStatementApplicationInventory.setString(18,applicationObjectWorker.supportManager);
                        insertPreparedStatementApplicationInventory.setString(19,applicationObjectWorker.developmentDirector);
                        insertPreparedStatementApplicationInventory.setString(20,applicationObjectWorker.developmentManager);
                        insertPreparedStatementApplicationInventory.setString(21,applicationObjectWorker.developmentPrime);
                        insertPreparedStatementApplicationInventory.setString(22,applicationObjectWorker.ismWorkgroup);
                        insertPreparedStatementApplicationInventory.setString(23,applicationObjectWorker.status);
                        insertPreparedStatementApplicationInventory.setString(24,applicationObjectWorker.businessKPI);
                        insertPreparedStatementApplicationInventory.setString(25,applicationObjectWorker.isHpd);
                        insertPreparedStatementApplicationInventory.setString(26,applicationObjectWorker.isSiS);
                        insertPreparedStatementApplicationInventory.setString(27,applicationObjectWorker.isBsm);
                        insertPreparedStatementApplicationInventory.setString(28,applicationObjectWorker.isBPM);
                        insertPreparedStatementApplicationInventory.setString(29,applicationObjectWorker.isSho);
                        insertPreparedStatementApplicationInventory.setString(30,applicationObjectWorker.isShr);
                        
                        insertPreparedStatementApplicationInventory.setDate(31, today);//created date
                        insertPreparedStatementApplicationInventory.setString(32,   ConfigFrame.ADMIN_USER_NAME +"@"+ ConfigFrame.ADMIN_USER_ROLE);//last updated user
                        insertPreparedStatementApplicationInventory.setString(33,    ConfigFrame.LOAD_APP +"@"+ConfigFrame.LOAD_ACTION);//last updated app
                        // 
                        insertPreparedStatementApplicationInventory.setString(34,  applicationObjectWorker.supportDirector);//owner
                        insertPreparedStatementApplicationInventory.setString(35,  ConfigFrame.INVENTORY);//metadata type
                        insertPreparedStatementApplicationInventory.setString(36,  ConfigFrame.TYPE_ENV_PRODUCTION);//environment
                        //
                        insertPreparedStatementApplicationInventory.setString(37,  applicationObjectWorker.eSisName );//application name
                        insertPreparedStatementApplicationInventory.setString(38,  applicationObjectWorker.eSisSystemName);//system name
                        //dec 2015
                        insertPreparedStatementApplicationInventory.setInt(39,  eid);//eid
                        insertPreparedStatementApplicationInventory.setString(40,  applicationObjectWorker.getApplicationNameGlobal());//app global name
                        insertPreparedStatementApplicationInventory.setString(41,  applicationObjectWorker.systemName);//ESYSTEM_NAME
                        
                        insertPreparedStatementApplicationInventory.setTimestamp(42, new java.sql.Timestamp(System.currentTimeMillis()));//timestamp 
                         
                        // model fields
                      
                        result = insertPreparedStatementApplicationInventory.executeUpdate();
                        //System.out.println("loadMetadata:: insertPreparedStatementApplicationInventory:EORGANISM " +applicationObjectWorker.eSis + " - "+applicationObjectWorker.eSisSystemName + " :EID="+eid);
                       
                        // SAVE EORGANISM
                        saveEOrganismTable(eid,applicationObjectWorker.eSisName,ConfigFrame.APPLICATION_TABLE,ConfigFrame.APPLICATION, appId);
                        
                    }  // i for object in ArrayList
                
                    //CLOSE
                    insertPreparedStatementApplicationInventory.close(); 
                   
                    connection.close();
                          
                    //JOptionPane.showMessageDialog( null, "EORGANISM :: insertPreparedStatementApplicationInventory OK");
                    log4j.debug("loadMetadata:: insertPreparedStatementApplicationInventory:EORGANISM OK");
                    
                
                } catch(SQLException sqle){
                    //JOptionPane.showMessageDialog( null,"SQLException  "    + sqle.toString()+"\n");
                	log4j.error("insertPreparedStatementApplicationInventory:: SQLException: " + sqle.toString());
                    
                } catch(Exception e){
                    //JOptionPane.showMessageDialog( null, "Exception  " +e.toString()+"\n");
                	log4j.error("insertPreparedStatementApplicationInventory:: Exception: " + e.toString());
            
                }finally{
                    try{
                         
                         if (connection!=null){connection.close(); }
                    
                    }catch(SQLException sqle2){
                    	log4j.error("insertPreparedStatementApplicationInventory:: SQLException:finally " + sqle2.toString());
                    }//
                
                }//finally
             
            
        }// end loadApplicationInventoryToDatabase
          
          
        public void loadDbCollectionsMetadatatoDatabase(String _location){
            // check all db connections
            // initialize connections to all source databases
           
            //eDBConnectionVectorInitializer();
            loadDatabaseMetadataFile(_location);
            
            for( int i = 1; i <  eDBConnectionVector.size() ; i++){
                eDBConnectionTemp = (EDBConnection)eDBConnectionVector.get(i);
                loadDBMetadata(eDBConnectionTemp);     
                log4j.debug("loadDbCollectionsMetadatatoDatabase:: eDBConnectionVector.size() i: " +i);
            }//
            
            log4j.debug("loadDbCollectionsMetadatatoDatabase:: eDBConnectionVector.size(): " +eDBConnectionVector.size());
             
        }// loadDbCollectionsMetadatatoDatabase
        
        
        public void loadDBMetadata(EDBConnection _eDBConnection){
           
            eDBConnectionTemp = new EDBConnection();
            eDBConnectionTemp = _eDBConnection;
            
            PreparedStatement selectPreparedStatementFromDBTables;
            PreparedStatement selectPreparedStatementFromDBColumns;
            
            ResultSet resultSetDbMetadataTables;
            ResultSet resultSetDbMetadataColumns;
             
            PreparedStatement insertPreparedStatementMetadataTable;
            PreparedStatement insertPreparedStatementMetadataColumn;
            PreparedStatement insertPreparedStatementAttributes;
            
            // initilaize EOrganism DB connection for target
            // initilaize EOrganism DB connection for target
            databaseInitializer();
            checkConnection();
            //checkDatabase(connectionSeq);
                          
            //java.sql.Date today = new java.sql.Date(System.currentTimeMillis());
            
            connectionWorker = createDatabaseConnection(eDBConnectionTemp.getDatabaseURL(),eDBConnectionTemp.getDatabaseUsername(),eDBConnectionTemp.getDatabasePassword());
              
            try{
                    //log4j.debug("loadMetadata:: resultSetDbMetadataTables:EORGANISM 1");
                    
                    selectPreparedStatementFromDBTables = connectionWorker.prepareStatement("SELECT OWNER,TABLE_NAME,STATUS FROM ALL_TABLES WHERE OWNER=?");
                    insertPreparedStatementMetadataTable = connection.prepareStatement("INSERT INTO EMETADATA_TABLE(EMETADATA_TABLE_ID,EMETADATA_TABLE_OWNER,EMETADATA_TABLE_NAME,EMETADATA_TABLE_STATUS,EMETADATA_TABLE_CREATED,EMETADATA_DATABASE_NAME) VALUES (?,?,?,?,?,?)");
                    
                    selectPreparedStatementFromDBColumns = connectionWorker.prepareStatement("SELECT OWNER,TABLE_NAME,COLUMN_NAME,DATA_TYPE FROM all_tab_columns WHERE OWNER=?");
                    
                    insertPreparedStatementMetadataColumn = connection.prepareStatement("INSERT INTO EMETADATA_COLUMN (EMETADATA_COLUMN_ID,EMETADATA_OWNER,EMETADATA_TABLE_NAME,EMETADATA_COLUMN_NAME,EMETADATA_COLUMN_TYPE,EMETADATA_COLUMN_CREATED,EMETADATA_DATABASE_NAME) VALUES (?,?,?,?,?,?,?)");
                    insertPreparedStatementAttributes     = connection.prepareStatement("INSERT INTO EATTRIBUTE(EATTRIBUTE_ID,EATTRIBUTE_NAME,EATTRIBUTE_TYPE,EATTRIBUTE_SUBTYPE,EDATABASE_NAME_FK) VALUES (?,?,?,?,?)");
                    
                    //
                    selectPreparedStatementFromDBTables.setString(1, eDBConnectionTemp.getDatabaseOwner());//id
                    //log4j.debug("loadMetadata:: resultSetDbMetadataTables:EORGANISM 2");
                                    
                    resultSetDbMetadataTables = selectPreparedStatementFromDBTables.executeQuery();
                
                    //log4j.debug("loadMetadata:: resultSetDbMetadataTables:EORGANISM");
                   
                    if(resultSetDbMetadataTables!=null){
                        
                           while(resultSetDbMetadataTables.next() ){
                               
                               insertPreparedStatementMetadataTable.setInt(1, getSequenceNextValue("EMETADATA_TABLE","EMETADATA_TABLE_ID"));//id
                               insertPreparedStatementMetadataTable.setString(2, resultSetDbMetadataTables.getString(1));//owner
                               insertPreparedStatementMetadataTable.setString(3, resultSetDbMetadataTables.getString(2));//table_name
                               insertPreparedStatementMetadataTable.setString(4, resultSetDbMetadataTables.getString(3));//status
                               insertPreparedStatementMetadataTable.setDate(5, today);//id
                               insertPreparedStatementMetadataTable.setString(6,   eDBConnectionTemp.getDatabaseName());//database name
                               
                               result = insertPreparedStatementMetadataTable.executeUpdate();
                               //log4j.debug("loadMetadata:: insertPreparedStatementMetadataTable:EORGANISM");
                                      
                           }// while we have resultSet  
                           
                    }// if result set not null  
                    
                     selectPreparedStatementFromDBColumns.setString(1,eDBConnectionTemp.getDatabaseOwner());//id
                                     
                     resultSetDbMetadataColumns = selectPreparedStatementFromDBColumns.executeQuery();
                    
                    if(resultSetDbMetadataColumns!=null){
                        
                           while(resultSetDbMetadataColumns.next() ){
                               
                               insertPreparedStatementMetadataColumn.setInt(1, getSequenceNextValue("EMETADATA_COLUMN","EMETADATA_COLUMN_ID"));//id
                               insertPreparedStatementMetadataColumn.setString(2, resultSetDbMetadataColumns.getString(1));//owner
                               insertPreparedStatementMetadataColumn.setString(3, resultSetDbMetadataColumns.getString(2));//table_name
                               insertPreparedStatementMetadataColumn.setString(4, resultSetDbMetadataColumns.getString(3));//COLUMN_NAME
                               insertPreparedStatementMetadataColumn.setString(5, resultSetDbMetadataColumns.getString(4));//COLUMN_TYPE
                               insertPreparedStatementMetadataColumn.setDate(6, today);//DATE
                               insertPreparedStatementMetadataColumn.setString(7,eDBConnectionTemp.getDatabaseName());//database name
               
                               result = insertPreparedStatementMetadataColumn.executeUpdate();
                               //log4j.debug("loadMetadata:: insertPreparedStatementMetadataColumn:EORGANISM");
                               
                                 // INSERT INTO EATTRIBUTE_MODEL_TABLE
                               //insertPreparedStatementAttributes- EATTRIBUTE_MODEL_ID,EATTRIBUTE_MODEL_NAME,EATTRIBUTE_MODEL_TYPE,EATTRIBUTE_MODEL_SUBTYPE,EDATABASE_MODEL_NAME_FK
                                   
                               insertPreparedStatementAttributes.setInt(1, getSequenceNextValue("EATTRIBUTE","EATTRIBUTE_ID"));//id
                               insertPreparedStatementAttributes.setString(2, resultSetDbMetadataColumns.getString(3));//NAME
                               insertPreparedStatementAttributes.setString(3, "COLUMN");//type
                               insertPreparedStatementAttributes.setString(4, resultSetDbMetadataColumns.getString(4));//COLUMN_TYPE
                               insertPreparedStatementAttributes.setString(5, eDBConnectionTemp.getDatabaseName());//DATABASE
                                 
                               result = insertPreparedStatementAttributes.executeUpdate();
                               //log4j.debug("loadMetadata:: EATTRIBUTE_MODEL_TABLE:" + resultSetDbMetadataColumns.getString(3));
                               
                               
                           }// while we have resultSet  
                                 
                    }// if result set not null  
            
                    if (resultSetDbMetadataTables  != null){resultSetDbMetadataTables.close();}
                    if (resultSetDbMetadataColumns != null){resultSetDbMetadataColumns.close();}
                    
		                     selectPreparedStatementFromDBTables.close();
		                     selectPreparedStatementFromDBColumns.close();
		                       
		                     insertPreparedStatementMetadataTable.close();
		                     insertPreparedStatementMetadataColumn.close();
		                     insertPreparedStatementAttributes.close();
                    
                                connection.close();
                                //connectionSeq.close();
                                connectionWorker.close();
                      
                
                    //JOptionPane.showMessageDialog( null, "loadMetadata OK");
                                log4j.debug("*************loadMetadata:: OK:*********** " +eDBConnectionTemp.getDatabaseName());
                    
                } catch(SQLException sqle){
                    //JOptionPane.showMessageDialog( null,"SQLException  "    + sqle.toString()+"\n");
                	log4j.error("loadMetadata:: SQLException: " + sqle.toString());
                    
                } catch(Exception e){
                    //JOptionPane.showMessageDialog( null, "Exception  " +e.toString()+"\n");
                	log4j.error("loadMetadata:: Exception: " + e.toString());
        
                }finally{
                    try{
                         
                         if (connection!=null){connection.close(); }
                         if (connectionWorker!=null){connectionWorker.close(); }
                    
                    }catch(SQLException sqle2){
                    	log4j.error("loadMetadata:: SQLException:finally " + sqle2.toString());
                    }//
                
                }//finally
           
        }// loadDBMetadata(EDBConnection)
        
        public void loadDbMetadatatoDatabase(){
            
            PreparedStatement insertPreparedStatementMetadataTable            ;
            PreparedStatement insertPreparedStatementMetadataColumn           ;
            
            // delete database 
            // deleteDatabase();
            databaseInitializer();
            checkConnection();
            
            // check all db connections
            //databaseConnectionsInitializer();
             
            //java.sql.Date today = new java.sql.Date(System.currentTimeMillis());
              
            try{
                    insertPreparedStatementMetadataTable            = connection.prepareStatement("INSERT INTO EMETADATA_TABLE (emetadata_table_owner,emetadata_table_name,emetadata_table_status, emetadata_table_CREATED,emetadata_DATABASE_NAME) SELECT OWNER, TABLE_NAME, STATUS, SYSDATE,?  FROM ALL_TABLES WHERE OWNER=?");
                    insertPreparedStatementMetadataColumn           = connection.prepareStatement("INSERT INTO EMETADATA_COLUMN (emetadata_owner,emetadata_table_name,emetadata_COLUMN_NAME,emetadata_COLUMN_TYPE,emetadata_CREATED,emetadata_DATABASE_NAME) SELECT OWNER,TABLE_NAME,COLUMN_NAME,DATA_TYPE,SYSDATE,? FROM all_tab_columns WHERE OWNER=?");
                       
                     
                    // table metadata
                                insertPreparedStatementMetadataTable.setString(1, "EORGANISM");//id
                                insertPreparedStatementMetadataTable.setString(2, "EORGANISM");//id
                                result = insertPreparedStatementMetadataTable.executeUpdate();
        
                                insertPreparedStatementMetadataTable.setString(1, "ABACUS");//id
                                insertPreparedStatementMetadataTable.setString(2, "ABACUS");//id
                                result = insertPreparedStatementMetadataTable.executeUpdate();
                
                                insertPreparedStatementMetadataTable.setString(1, "JOMO");//id
                                insertPreparedStatementMetadataTable.setString(2, "JOMO");//id
                                result = insertPreparedStatementMetadataTable.executeUpdate();
                                
                                insertPreparedStatementMetadataTable.setString(1, "JCONNECTOR");//id
                                insertPreparedStatementMetadataTable.setString(2, "JCONNECTOR");//id
                                result = insertPreparedStatementMetadataTable.executeUpdate();
                    
                    
                            // column metadata
                            insertPreparedStatementMetadataColumn.setString(1, "EORGANISM");//id
                            insertPreparedStatementMetadataColumn.setString(2, "EORGANISM");//id
                            result = insertPreparedStatementMetadataColumn.executeUpdate();
                            
                            insertPreparedStatementMetadataColumn.setString(1, "ABACUS");//id
                            insertPreparedStatementMetadataColumn.setString(2, "ABACUS");//id
                            result = insertPreparedStatementMetadataColumn.executeUpdate();
                            
                            insertPreparedStatementMetadataColumn.setString(1, "JOMO");//id
                            insertPreparedStatementMetadataColumn.setString(2, "JOMO");//id
                            result = insertPreparedStatementMetadataColumn.executeUpdate();
                            
                            insertPreparedStatementMetadataColumn.setString(1, "JCONNECTOR");//id
                            insertPreparedStatementMetadataColumn.setString(2, "JCONNECTOR");//id
                            result = insertPreparedStatementMetadataColumn.executeUpdate();
                          
                             insertPreparedStatementMetadataTable.close(); 
                             insertPreparedStatementMetadataColumn.close();
                            
                            
                             connection.close();
                          
                             //JOptionPane.showMessageDialog( null, "loadDbMetadata OK");
                
                } catch(SQLException sqle){
                    //JOptionPane.showMessageDialog( null,"SQLException  "    + sqle.toString()+"\n");
                	log4j.error("loadDbMetadata:: SQLException: " + sqle.toString());
                    
                } catch(Exception e){
                    //JOptionPane.showMessageDialog( null, "Exception  " +e.toString()+"\n");
                	log4j.error("loadDbMetadata:: Exception: " + e.toString());
        
                }finally{
                    try{
                         
                         if (connection!=null){connection.close(); }
                    
                    }catch(SQLException sqle2){
                    	log4j.error("loadDbMetadata:: SQLException:finally " + sqle2.toString());
                    }//
                
                }//finally
           
        }// loadDbMetadatatoDatabase
                
	 public void setEOrganismFromXML(String xmlFile){
                String loadXmlFileName  = "";
	 }// end loadEOrganismFromXML :: load EOrganism model configuration from Xml file
	 
	 
	 
	 public void printEOrganismXML(){
		  
						try{
						            fileForPrint     		= new File( fileXML );
						            fileOutputForPrint    	= new FileOutputStream(fileForPrint);
						
						
						            for (int i = 0; i < printEOrganismXMLString.length(); ++i){
						                    fileOutputForPrint.write( printEOrganismXMLString.charAt(i) );
						            }
						            
						            fileOutputForPrint.flush();// flush
						            fileOutputForPrint.close();
							
						}catch (IOException ioe) {
									log4j.error("printEOrganismXML ||IO exception - Input Output Error at creating the interface file:");
									log4j.error("Error:"+ ioe.getMessage());
						            JOptionPane.showMessageDialog( null,
						                                           "IOException" +"\n"+
						                                           ioe.getMessage());
						            return;
						} catch (Exception e) {
									log4j.error("printEOrganismXML|| - Error creating the properties file:");
									log4j.error("Error:"+ e.getMessage());
						            JOptionPane.showMessageDialog( null,
						                                           "Exception " +"\n"+
						                                           e.getMessage());
						            return;
						}// end catch
	 
	 }//printEOrganismXML
	 
	 
	 public void addBranch(Document _doc, Element _branchToAddTo, String _attributeName){
		 
		   //declarations brachroot
		   Element tempBranch = _doc.createElement("branch");
		   _branchToAddTo.appendChild(tempBranch);
		   
		   //declarations attribute element
		   Element tempAttribute = _doc.createElement("attribute");
		   tempBranch.appendChild(tempAttribute);
		   
		   //set attribute to staff element
		   Attr tempAttributeName = _doc.createAttribute("name");
		   tempAttributeName.setValue("name");
		   
		   Attr tempAttributeValue = _doc.createAttribute("value");
		   tempAttributeValue.setValue(_attributeName);
		   
		   tempBranch.setAttributeNode(tempAttributeName);
		   tempBranch.setAttributeNode(tempAttributeValue);
		 
		 
	 }// end addBranch
	 
	 
	 public void generateIndexTypesMainFile(){
		 
		 
		 
	 }//generateIndexTypesMainFile
	 
	 public void generateEachTypeIndexFileAndContent(){
		    
		 EApplication 	tempApplication = null;
		 
		 String 		tempApplicationName="";
		 
		 String applicationCMDBIndex 			="";
		 String applicationITSIndex 			="";
		 String applicationModelIndex 			="";
		 String applicationPhysicalIndex 		="";
			
		 
		 String fileContent					="";
		 String indexFileContent			="";
		 
		 String tempFileContent				="";
		 String tempIndexContent			="";
		 
		 
		 String originalIndexFileContent  ="<html>"
		 		 + "\n"+"<head>"
			         + "\n"+"<meta http-equiv=\"content-type\" content=\"text/html; charset=Cp1252\" />"
			         + "\n"+"<script src=\"../dbdoc.js\" type=\"text/javascript\"></script>"
			         + "\n"+ "<link href=\"../dbdoc.css\" type=\"text/css\" rel=\"stylesheet\">"
			         + "\n"+ "</head>"
			         + "\n"+"<body id=\"bottom_left\">"
			         + "\n"+ "<h2>Tables"
			         + "\n"+"<div id=\"search\"><input type=\"text\" onkeyup=\"$d_Find('narrow',this.value,'a')\"/></div></h2><div id=\"narrow\">"
			         + "\n"+ "###replace_application_index###"
			         + "\n"+"</div></body> </html>" ; 
		
  
		  String originalfileContent="<html>"
				  			+ "\n"+		"<head>"
					         + "\n"+		"<meta http-equiv=\"content-type\" content=\"text/html; charset=Cp1252\" />"
					         + "\n"+			"<script src=\"../dbdoc.js\" type=\"text/javascript\"></script>"
					         + "\n"+			"<link href=\"../dbdoc.cs\" type=\"text/css\" rel=\"stylesheet\">"
					         + "\n"+			"</head>"
					         + "\n"+			"<body class=\"object\">"
							+ "\n"+			"<div id=\"header\"><h2>###replace_application_name###</h2>"
							+ "\n"+			"<div class=\"tabs clearfix\">"
							+ "\n"+			""
							+ "\n"+			"<div class=\"tab\" id=\"current\"><div><a href=\"###replace_application_name###.html\">Columns</a></div></div>"
							+ "\n"+			"<div class=\"tab\"><div><a href=\"###replace_application_name###_Constraints.html\">Constraints</a></div></div>"
							+ "\n"+			"<div class=\"tab\"><div><a href=\"###replace_application_name###_Grants.html\">Grants</a></div></div>"
							+ "\n"+			"<div class=\"tab\"><div><a href=\"###replace_application_name###_Statistics.html\">Statistics</a></div></div>"
							+ "\n"+			"<div class=\"tab\"><div><a href=\"###replace_application_name###_Triggers.html\">Triggers</a></div></div>"
							+ "\n"+			"<div class=\"tab\"><div><a href=\"###replace_application_name###_Dependencies.html\">Dependencies</a></div></div>"
							+ "\n"+			"<div class=\"tab\"><div><a href=\"###replace_application_name###_Details.html\">Details</a></div></div>"
							+ "\n"+			"<div class=\"tab\"><div><a href=\"###replace_application_name###_Partitions.html\">Partitions</a></div></div>"
							+ "\n"+			"<div class=\"tab\"><div><a href=\"###replace_application_name###_Indexes.html\">Indexes</a></div></div>"
							+ "\n"+			"</div></div><br/>"
							+ "\n"+			"<div class=\"tab-panes\">"
							+ "\n"+			"<div id=\"COMPONENTS\"> <table cellpadding=\"0\" cellspacing=\"0\" cellspacing=\"0\" summary=\"\"><tr>"
							+ "\n"+					"<th>COLUMN_NAME</th>"
							+ "\n"+					"<th>DATA_TYPE</th>"
							+ "\n"+					"<th>NULLABLE</th>"
							+ "\n"+					"<th>DATA_DEFAULT</th>"
							+ "\n"+					"<th>COLUMN_ID</th>"
								+ "\n"+					"<th>COMMENTS</th>"
								+ "\n"+			"</tr>"
								+ "\n"+				"<tr>"
								+ "\n"+			"<td>EAPPLICATION_INVENTORY_ID</td>"
								+ "\n"+			"<td>NUMBER</td>"
								+ "\n"+			"<td>Yes</td>"
								+ "\n"+			"<td>null</td>"
								+ "\n"+			"<td>1</td>"
								+ "\n"+			"<td>null</td>"
								+ "\n"+				"</tr>"
								+ "\n"+				"<tr>"
								+ "\n"+			"<td>EAPPLICATION_INVENTORY_NAME</td>"
								+ "\n"+			"<td>VARCHAR2(200 BYTE)</td>"
								+ "\n"+			"<td>Yes</td>"
								+ "\n"+			"<td>null</td>"
								+ "\n"+			"<td>2</td>"
								+ "\n"+			"<td>null</td>"
								+ "\n"+					"</tr>"
								
								+ "\n"+			"</table></div></body></html>";
				
			
         for(int index =0 ; index < modelOfEApplication.size(); index ++){

                    // manipulator
       	  		 tempApplication = (EApplication) modelOfEApplication.elementAt(index);
       	  		 
       	  		 tempApplicationName = tempApplication.getApplicationName();
       	  		 
       	  		 	
       	  		    System.out.println("generateEachTypeIndexFileAndContent:: tempApplication##tempApplicationName ="+ tempApplicationName + "##index ="+ index);
        	  		
       	  		 
	       	  	 if (tempApplication==null){
	  	  			 
	  	  			 tempApplication = new EApplication();
	  	  			 tempApplication.setApplicationName("DEFAULT APP NULL");
	  	  			 System.out.println("generateEachTypeIndexFileAndContent:: tempApplication ##tempApplication==null ");
	  	  		 
	       	  	 }
	       	  	 
	       	  	 if (tempApplicationName==null){
       	  			 
       	  			 	tempApplicationName ="DEFAULT APP NAME NULL";
       	  			 	System.out.println("tempApplicationName:: tempApplication##tempApplicationName==null ");
       	  		 }
       	  		 
       	  		 
       	  		 if (tempApplicationName==""){
  	  			 
	  			 	tempApplicationName ="DEFAULT APP NAME EMPTY";
	  			 	System.out.println("tempApplicationName EMPTY:: ##tempApplicationName= ");
       	  		 }
       	  		 
       	  		 if (tempApplicationName.trim().length() == 0){
       	  			  tempApplicationName ="DEFAULT APP NAME ZERO";
       	  		      System.out.println("tempApplicationName SIZE ZERO:: ##tempApplicationName.trim().length()==0 ");
       	  			  tempApplicationName = tempApplication.getApplicationName().toUpperCase();
       	  		 }

       	  			 
       	  		 tempApplicationName = tempApplicationName.replaceAll(" ", "_");
       	  		 tempApplicationName = tempApplicationName.replaceAll("\"", "_");
       	  		 tempApplicationName = tempApplicationName.replaceAll(":", "_");
       	  		 tempApplicationName = tempApplicationName.replaceAll("-", "_");
       	  		 //tempApplicationName = tempApplicationName.replaceAll("{", "_");
       	  		 //tempApplicationName = tempApplicationName.replaceAll("(", "_");
       	  		 //tempApplicationName = tempApplicationName.replaceAll(")","_");
       	  		 //tempApplicationName = tempApplicationName.replaceAll("}","_");
       	  	
       	   	     
       	  	     tempApplicationName = tempApplicationName.toString().trim().toUpperCase();
       	  	     tempApplicationName = tempApplicationName.replace(" !@#$%^&*(),+=~`'?.,><-/.:{}","_");
       	  			 
                 if ((tempApplication.metadataType).equals(ConfigFrame.APPLICATION_TYPE_INVENTORY )){
                    
                	 applicationITSIndex = applicationITSIndex+"\n"+"<a href =\""+tempApplicationName+".html\" target=\"ObjectDetailsFrame\">"+tempApplicationName+"</a>";
                	 
                	 fileContent = originalfileContent;
                  	 printFile(fileContent.replaceAll("###replace_application_name###", tempApplicationName), tempApplicationName+".html", ConfigFrame.webGeneratedPath + "/applicationits");
                	 
                	  	 
                 }else if((tempApplication.metadataType).equals(ConfigFrame.APPLICATION_TYPE_MODEL)){
                	 
                	 applicationModelIndex = applicationModelIndex+"\n"+"<a href =\""+tempApplicationName+".html\" target=\"ObjectDetailsFrame\">"+tempApplicationName+"</a>";
                	 
                	 fileContent = originalfileContent;
                	 printFile(fileContent.replaceAll("###replace_application_name###", tempApplicationName), tempApplicationName+".html", ConfigFrame.webGeneratedPath + "/applicationmodel");
                	
                 	 
                 }else if((tempApplication.metadataType).equals(ConfigFrame.APPLICATION_TYPE_PHYSICAL)){
                	 
                	 applicationPhysicalIndex = applicationPhysicalIndex+"\n"+"<a href =\""+tempApplicationName+".html\" target=\"ObjectDetailsFrame\">"+tempApplicationName+"</a>";
                	 
                	 fileContent = originalfileContent;
                	 printFile(fileContent.replaceAll("###replace_application_name###", tempApplicationName), tempApplicationName+".html", ConfigFrame.webGeneratedPath + "/applicationcmdb");
            	 
                 }
                 

         }// for all the applications
         
          
		  			indexFileContent  =originalIndexFileContent; 
        	 	 	printFile(indexFileContent.replaceAll("###replace_application_index###", applicationITSIndex), "index.html", ConfigFrame.webGeneratedPath + "/applicationits");
	      	 
        	 	 	indexFileContent  =originalIndexFileContent; 
        	 	 	printFile(indexFileContent.replaceAll("###replace_application_index###", applicationModelIndex), "index.html",ConfigFrame.webGeneratedPath + "/applicationmodel");
	        		 
	        	 	indexFileContent  =originalIndexFileContent; 
	    	    	printFile(indexFileContent.replaceAll("###replace_application_index###", applicationCMDBIndex), "index.html", ConfigFrame.webGeneratedPath + "/applicationcmdb");
	        
		 
	 }//generateEachTypeIndexFileAndContent
	 
	 public void generateEachElementFile(){
		 
		 // each inidvidual HTML
		 
	 }//generateEachElementFile
	 
	 public void createEOrganismWeb(){
		 
		 generateIndexTypesMainFile();
		 
		 generateEachTypeIndexFileAndContent();
		 
			 
	 }// createEOrganismWeb 
		  
	 public void printFile(String _filecontent, String _fileName, String _fileLocation){
	  
	    //outputFileName          =   ConfigFrame.webGeneratedPath + "/applications/"+ appDirectory + "/sourcecode/"+  fileController.getBeanName()+".java";
      
			 try{
			
			          fileForPrint          = new File(_fileLocation+"/"+_fileName);
			          fileOutputForPrint    = new FileOutputStream(fileForPrint);
			
			          for (int i = 0; i < _filecontent.length(); ++i){
			                  fileOutputForPrint.write( _filecontent.charAt(i) );
			          }
			          
			          fileOutputForPrint.flush();// flush
			          fileOutputForPrint.close();
			
				}catch (IOException ioe) {
							log4j.error("printSomeFile:: IOException - Input Output Error at creating the Web file:"+ _fileLocation+"/"+_fileName);
							log4j.error("Error:"+ ioe.getMessage());
							JOptionPane.showMessageDialog( null,
				                                         "IOException "   + _fileLocation+"/"+_fileName  +"\n"+
				                                         ioe.getMessage());
				
				          return;
				} catch (Exception e) {
							log4j.error("printSomeFile:: Exception - Exception creating the bean file:"+ _fileLocation+"/"+_fileName);
							log4j.error("Error:"+ e.getMessage());
							JOptionPane.showMessageDialog( null,
				                                         "Exception "   + _fileLocation+"/"+_fileName  +"\n"+
				                                         e.getMessage());
				          return;
				}// end catch
		
			     
			 log4j.debug("printSomeFile:: ##location "+ _fileLocation +"## filename:" +_fileName);
		        
			 
	 }//printFile
	  
	  public void printAllApplicationsWebContent(){

          EApplication 	tempApplication = null;

          String 		type ="";

          JFile 		tempJFile = null ;

          for(int index =0 ; index < modelOfEApplication.size(); index ++){

                     // manipulator
        	  		 tempApplication = (EApplication) modelOfEApplication.elementAt(index);

                   

          }// for all the applications

	  }// end printAllApplicationsWebContent

	 
	 public void createEOrganismXML(){
	
		 EApplication tempEApplication = new EApplication();
		 // add subnodes to new children of the root node
         EComponent 		tempComponent 		= new EComponent();
         JDatabase 			tempDatabase 		= new JDatabase();
         EHardware 			tempHardware	 	= new EHardware();
         ESequenceFlow 		tempSequenceFlow 	= new ESequenceFlow();
         EOperation 		tempOperation 		= new EOperation();
         EInterface      	tempInterface     = new EInterface();
         EAttribute      	tempAttribute     = new EAttribute();
		 
		 try{
		   DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		   DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		   //root elements
		   Document doc = docBuilder.newDocument();

		   Element rootElement = doc.createElement("tree");
		   doc.appendChild(rootElement);

		   //declarations element
		   Element declarations = doc.createElement("declarations");
		   rootElement.appendChild(declarations);
		   

		   //declarations element
		   Element attributeDeclElem = doc.createElement("attributeDecl");
		   declarations.appendChild(attributeDeclElem);

		   //set attribute to staff element
		   Attr attribute1 = doc.createAttribute("name");
		   attribute1.setValue("name");
		   Attr attribute11 = doc.createAttribute("type");
		   attribute11.setValue("String");
		   
		   attributeDeclElem.setAttributeNode(attribute1);
		   attributeDeclElem.setAttributeNode(attribute11);
			 
		   //declarations brachroot
		   Element branch1 = doc.createElement("branch");
		   rootElement.appendChild(branch1);

		  
		   //declarations attribute element
		   Element attribute2 = doc.createElement("attribute");
		   branch1.appendChild(attribute2);
		   
		   //set attribute to staff element
		   Attr attribute3 = doc.createAttribute("name");
		   attribute3.setValue("name");
		   
		   Attr attribute33 = doc.createAttribute("value");
		   attribute33.setValue("Enterprise");
		   
		   attribute2.setAttributeNode(attribute3);
		   attribute2.setAttributeNode(attribute33);
		   
		   // applicationroot
		   
		   //declarations brachroot
		   Element branchApp = doc.createElement("branch");
		   branch1.appendChild(branchApp);
  
		   //declarations attribute element
		   Element attributeApp = doc.createElement("attribute");
		   branchApp.appendChild(attributeApp);
		   
		   //set attribute to staff element
		   Attr attributeName = doc.createAttribute("name");
		   attributeName.setValue("name");
		   
		   Attr attributeValue = doc.createAttribute("value");
		   attributeValue.setValue("Applications");
		   
		   branchApp.setAttributeNode(attributeName);
		   branchApp.setAttributeNode(attributeValue);
		   
		 //declarations brachroot
		   Element branchDB = doc.createElement("branch");
		   branch1.appendChild(branchDB);
  
		   //declarations attribute element
		   Element attributeDB = doc.createElement("attribute");
		   branchDB.appendChild(attributeDB);
		   
		   //set attribute to staff element
		   Attr attributeNameDB = doc.createAttribute("name");
		   attributeNameDB.setValue("name");
		   
		   Attr attributeValueDB = doc.createAttribute("value");
		   attributeValueDB.setValue("Databases");
		   
		   branchDB.setAttributeNode(attributeNameDB);
		   branchDB.setAttributeNode(attributeValueDB);
			  
		   for( int i = 0; i <  modelOfEApplication.size() ; i++){
			   
			   tempEApplication = new EApplication();
	        	 
	           tempEApplication = (EApplication) modelOfEApplication.elementAt(i);
	        	
			   if((tempEApplication.metadataType).equals("MODEL")){
		  	 
	        	 //declarations brachroot
				   Element branch2 = doc.createElement("branch");
				   branchApp.appendChild(branch2);
				   
				   //declarations attribute element
				   Element attribute4 = doc.createElement("attribute");
				   branch2.appendChild(attribute4);
				   
				   //set attribute to staff element
				   Attr attribute5 = doc.createAttribute("name");
				   attribute5.setValue("name");
				   
				   Attr attribute55 = doc.createAttribute("value");
				   attribute55.setValue(tempEApplication.getApplicationName());
				   	
				   
				   attribute4.setAttributeNode(attribute5);
				   attribute4.setAttributeNode(attribute55);
	                
				   log4j.debug("...............createEOrganismXML(tempEApplication): "+i+"......................."+tempEApplication.getApplicationName());
	        
			  
			       //log4j.debug("createEOrganismXML - OK i= " +i);
			   
			   
	        	   if(tempEApplication.getComponentsArrayList().size()>0){
  	           	    
	                   for(int j = 0; j < (tempEApplication.getComponentsArrayList()).size(); j++){
 	   
	                	   		   tempComponent = new EComponent();
	                	   		   tempComponent = (EComponent) tempEApplication.getComponentsArrayList().get(j);
                  	 	 	
							       //declarations brachroot
								   Element branch3 = doc.createElement("branch");
								   branch2.appendChild(branch3);
								   
								   //declarations attribute element
								   Element attribute6 = doc.createElement("attribute");
								   branch3.appendChild(attribute6);
								   
								   //set attribute to staff element
								   Attr attribute7 = doc.createAttribute("name");
								   attribute7.setValue("name");
									  
								   
								   Attr attribute77 = doc.createAttribute("value");
								   attribute77.setValue(tempComponent.getComponentName());
									  
								   attribute6.setAttributeNode(attribute7);
								   attribute6.setAttributeNode(attribute77);
								   
								   // if we have services
								   if((tempComponent.getArrayListService()).size()> 0){
  	                    	 		 
	    	                    	 	 	for(int k = 0; k < (tempComponent.getArrayListService()).size(); k++){
	    	                    	 	 		
	    	                    	 	 				   tempOperation = (EOperation)tempComponent.getArrayListService().get(k);
		    	                    	 			   
														   //declarations brachroot
														   Element branch4 = doc.createElement("branch");
														   branch3.appendChild(branch4);
														   
														   //declarations attribute element
														   Element attribute71 = doc.createElement("attribute");
														   branch4.appendChild(attribute71);
															  
														   
														   //set attribute to staff element
														   Attr attribute8 = doc.createAttribute("name");
														   attribute8.setValue("name");
														   
														   Attr attribute88 = doc.createAttribute("value");
														   attribute88.setValue(tempOperation.getName() + "::"+tempOperation.getType());
															  
														   attribute71.setAttributeNode(attribute8);
														   attribute71.setAttributeNode(attribute88);
														   
														   
														   // if we have interfaces
														   if(tempOperation.getArrayListInterface().size()>0){
					    	                    	 			
					    	                    	 			for(int t= 0; t < (tempOperation.getArrayListInterface()).size(); t++){
					    	                    	 				
					    	                    	 				   tempInterface = (EInterface)tempOperation.getArrayListInterface().get(t);
					    	                    	 				
					    	                    	 					//declarations interfaces brachroot
																	   Element branchInterface = doc.createElement("branch");
																	   branch4.appendChild(branchInterface);
														
																	   //declarations attribute element
																	   Element attributeInterface = doc.createElement("attribute");
																	   branchInterface.appendChild(attributeInterface);
																		  
																	   
																	   //set attribute to staff element
																	   Attr attributeInterfaceName = doc.createAttribute("name");
																	   attributeInterfaceName.setValue("name");
																	   
																	   Attr attributeInterfaceValue = doc.createAttribute("value");
																	   attributeInterfaceValue.setValue(tempInterface.getInterfaceName());
																				  
																	   attributeInterface.setAttributeNode(attributeInterfaceName);
																	   attributeInterface.setAttributeNode(attributeInterfaceValue);
					    	                    	 			
					    	                    	 			}//for
					    	                    	 			
					    	                    	 		}//if we have interfaces
					    	                    	 		
														   /*
					    	                    	 		//  if we have attributes
					    	                    	 		if(tempOperation.getArrayListAttribute().size()>0){
					    	                    	 				
					    	                    	 			for(int p = 0; p < (tempOperation.getArrayListAttribute()).size(); p++){
					    	                    	 				
					    	                    	 				tempAttribute = (EAttribute)tempOperation.getArrayListAttribute().get(p);
					    	                    	 				
					    	                    	 				tempAttribute.getName())
					    	                    	 			
					    	                    	 			}//for
					    	                    	 			
					    	                    	 			
					    	                    	 			
					    	                    	 		}//if we have attributes
					    	                    	 		
					    	                    	 		*/
															  
														   //log4j.debug("createEOrganismXML - OK k = " +k);
													   
												   }//k
								   
								   			}// if we have services
				                     
				                     }// j - for all components
				  
	        	   
	        	   }// if j - if we have components
				   
			   }// if model application   
			   
		   }//i - applications
		   
		   
		   //write the content into xml file
		   TransformerFactory transformerFactory = TransformerFactory.newInstance();
		   Transformer transformer = transformerFactory.newTransformer();
		   DOMSource source = new DOMSource(doc);
		   //C:\apache\Tomcat 8.0\webapps\hexplorer
		   //StreamResult result =  new StreamResult(new File("C:\\apache\\Tomcat 8.0\\webapps\\hexplorer\\eorganism-test.xml"));
		   
		   StreamResult result =  new StreamResult(new File(fileXML));
		   
		   
		   transformer.transform(source, result);

		   log4j.debug("createEOrganismXML - OK");

			}catch(ParserConfigurationException pce){
					 pce.printStackTrace();
			}catch(TransformerException tfe){
					 tfe.printStackTrace();
				
			
			} catch (Exception e) {
						log4j.error("createEOrganismXML|| - Exception:");
						log4j.error("Error:"+ e.getMessage());
			            JOptionPane.showMessageDialog( null,
			                                           "Exception " +"\n"+
			                                           e.getMessage());
			            return;
			}// end catch

	 }//createEOrganismXML
	 
	 
	 public void createEOrganismGraphML(){
	
		 EApplication 					tempEApplication 		= new EApplication();
		 String 						tempAppLinkString 		= "";
	 	 String 						nextTokenApp 			= "";
	 	 StringTokenizer 				tempAppLinkStringTokenizer;
	 	 
	 	 EComponent 					tempComponent		=null;
		  
		 try{
		   DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		   DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		   //root element
		   Document doc = docBuilder.newDocument();
		   
		   Attr attributeRootElement = doc.createAttribute("xmlns");
		   attributeRootElement.setValue("http://graphml.graphdrawing.org/xmlns");
		  
		   Element rootElement = doc.createElement("graphml");
		   rootElement.setAttributeNode(attributeRootElement);
		   doc.appendChild(rootElement);

		   //declarations graph
		   Attr attributeGraph = doc.createAttribute("edgedefault");
		   attributeGraph.setValue("undirected");
		   
		   Element graph = doc.createElement("graph");
		   graph.setAttributeNode(attributeGraph);
		   
		   //declaration key - 
		   //<key id="name" for="node" attr.name="name" attr.type="string"/>
		   Element keyElement = doc.createElement("key");
		   		  			   
		   //set attribute to key
		   Attr keyIDAttribute = doc.createAttribute("id");
		   keyIDAttribute.setValue("name");
		   
		   Attr keyForAttribute = doc.createAttribute("for");
		   keyForAttribute.setValue("node");
		   
		   Attr keyNameAttribute = doc.createAttribute("attr.name");
		   keyNameAttribute.setValue("name");
		   	
		   Attr keyTypeAttribute = doc.createAttribute("attr.type");
		   keyTypeAttribute.setValue("string");
		   
		   keyElement.setAttributeNode(keyIDAttribute);
		   keyElement.setAttributeNode(keyForAttribute);
		   keyElement.setAttributeNode(keyNameAttribute);
		   keyElement.setAttributeNode(keyTypeAttribute);
		   
		   //<key id="business_unit" for="node" attr.name="business_unit" attr.type="string"/>
		     
		   Element keyBusinessUnitElement = doc.createElement("key");
 			   
		   //set attribute to key
		   Attr keyIDAttributeBusinessUnit = doc.createAttribute("id");
		   keyIDAttributeBusinessUnit.setValue("business_unit");
		   
		   Attr keyForAttributeBusinessUnit = doc.createAttribute("for");
		   keyForAttributeBusinessUnit.setValue("node");
		   
		   Attr keyNameAttributeBusinessUnit = doc.createAttribute("attr.name");
		   keyNameAttributeBusinessUnit.setValue("business_unit");
		   	
		   Attr keyTypeAttributeBusinessUnit = doc.createAttribute("attr.type");
		   keyTypeAttributeBusinessUnit.setValue("string");
		   
		   keyBusinessUnitElement.setAttributeNode(keyIDAttributeBusinessUnit);
		   keyBusinessUnitElement.setAttributeNode(keyForAttributeBusinessUnit);
		   keyBusinessUnitElement.setAttributeNode(keyNameAttributeBusinessUnit);
		   keyBusinessUnitElement.setAttributeNode(keyTypeAttributeBusinessUnit);
		   
		   // business service
		   //<key id="business_service" for="node" attr.name="business_service" attr.type="string"/>
		  
		   Element keyBusinessServiceElement = doc.createElement("key");
			   
		   //set attribute to key
		   Attr keyIDAttributeBusinessService = doc.createAttribute("id");
		   keyIDAttributeBusinessService.setValue("business_service");
		   
		   Attr keyForAttributeBusinessService = doc.createAttribute("for");
		   keyForAttributeBusinessService.setValue("node");
		   
		   Attr keyNameAttributeBusinessService = doc.createAttribute("attr.name");
		   keyNameAttributeBusinessService.setValue("business_service");
		   	
		   Attr keyTypeAttributeBusinessService = doc.createAttribute("attr.type");
		   keyTypeAttributeBusinessService.setValue("string");
		   
		   keyBusinessServiceElement.setAttributeNode(keyIDAttributeBusinessService);
		   keyBusinessServiceElement.setAttributeNode(keyForAttributeBusinessService);
		   keyBusinessServiceElement.setAttributeNode(keyNameAttributeBusinessService);
		   keyBusinessServiceElement.setAttributeNode(keyTypeAttributeBusinessService);
		   
		   //<key id="owner" for="node" attr.name="owner" attr.type="string"/>
		   
		   Element keyOwnerElement = doc.createElement("key");
		   
		   //set attribute to key
		   Attr keyIDAttributeOwner = doc.createAttribute("id");
		   keyIDAttributeOwner.setValue("owner");
		   
		   Attr keyForAttributeOwner = doc.createAttribute("for");
		   keyForAttributeOwner.setValue("node");
		   
		   Attr keyNameAttributeOwner = doc.createAttribute("attr.name");
		   keyNameAttributeOwner.setValue("owner");
		   	
		   Attr keyTypeAttributeOwner = doc.createAttribute("attr.type");
		   keyTypeAttributeOwner.setValue("string");
		   
		   keyOwnerElement.setAttributeNode(keyIDAttributeOwner);
		   keyOwnerElement.setAttributeNode(keyForAttributeOwner);
		   keyOwnerElement.setAttributeNode(keyNameAttributeOwner);
		   keyOwnerElement.setAttributeNode(keyTypeAttributeOwner);
		   
		  
		   Element nodeElement;
		   Element dataNameElement;
		   Element dataBusinessServiceElement;
		   Element dataBusinessUnitElement;
		   Element dataOwnerElement;
		   Element edgeElement;
		   
		   Attr nodeTempIdAttribute;
		   Attr dataNameAttribute;
		   Attr dataBusinessServiceAttribute;
		   Attr dataBusinessUnitAttribute;
		   Attr dataOwnerAttribute;
		   Attr edgeElementSourceAttribute;
		   Attr edgeElementTargetAttribute;
		   
		   
		   graph.appendChild(keyElement);
		   graph.appendChild(keyBusinessUnitElement);
		   graph.appendChild(keyBusinessServiceElement);
		   graph.appendChild(keyOwnerElement);
		   
		   rootElement.appendChild(graph);
	  
		   // generate nodes for applications
		   for( int i = 0; i <  modelOfEApplication.size() ; i++){
			   
			   tempEApplication = new EApplication();
	           tempEApplication = (EApplication) modelOfEApplication.elementAt(i);
			   
			   if("MODEL".equals(tempEApplication.metadataType)){
				 		   
					           /*
					            <node id="CSM" >
									 <data key="name">SuperSystem Billing	SC01</data>
									 <data key="business_unit">Consumer IT</data>
									 <data key="business_service">Cable Billing</data>
									 <data key="owner">Shantelle</data>
								</node>
					            */
					           nodeElement = doc.createElement("node");
							   //set attribute to key
							   nodeTempIdAttribute = doc.createAttribute("id");
							   nodeTempIdAttribute.setValue(tempEApplication.getApplicationName());
							   nodeElement.setAttributeNode(nodeTempIdAttribute);
							   
							   dataNameElement= doc.createElement("data");
							   
							   dataNameAttribute = doc.createAttribute("key");
							   dataNameAttribute.setValue("name");
							   dataNameElement.setAttributeNode(dataNameAttribute);
							   dataNameElement.setTextContent(tempEApplication.getApplicationName());
							   
							   dataBusinessUnitElement= doc.createElement("data");
							   
							   dataBusinessUnitAttribute = doc.createAttribute("key");
							   dataBusinessUnitAttribute.setValue("business_unit");
							   dataBusinessUnitElement.setAttributeNode(dataBusinessUnitAttribute);
							   dataBusinessUnitElement.setTextContent(tempEApplication.businessUnit);
							   
							   dataBusinessServiceElement= doc.createElement("data");
							   
							   dataBusinessServiceAttribute = doc.createAttribute("key");
							   dataBusinessServiceAttribute.setValue("business_service");
							   dataBusinessServiceElement.setAttributeNode(dataBusinessServiceAttribute);
							   dataBusinessServiceElement.setTextContent(tempEApplication.businessService);
							   
							   dataOwnerElement= doc.createElement("data");
							   
							   dataOwnerAttribute = doc.createAttribute("key");
							   dataOwnerAttribute.setValue("owner");
							   dataOwnerElement.setAttributeNode(dataOwnerAttribute);
							   dataOwnerElement.setTextContent(tempEApplication.owner);
							   
							   
							   nodeElement.appendChild(dataNameElement);
							   nodeElement.appendChild(dataBusinessUnitElement);
							   nodeElement.appendChild(dataBusinessServiceElement);
							   nodeElement.appendChild(dataOwnerElement);
							   
							   //important
							   graph.appendChild(nodeElement);
							   
							   log4j.debug("createEOrganismGraphML - add node MODEL app: "+ tempEApplication.getApplicationName());
			   }//f app model
	   		   			   
		   }//i - applications
		   
		   // generate edges from applications links
		   for( int i = 0; i <  modelOfEApplication.size() ; i++){
			   
			   	tempEApplication = new EApplication();
			   	tempEApplication = (EApplication) modelOfEApplication.elementAt(i);
	           
			   	// <edge source="8" target="16"></edge>
	           
				           	// APP LINK OUT
			               	if("MODEL".equals(tempEApplication.metadataType)&& tempEApplication.getOut()!=null && tempEApplication.getOut().length() > 3){
			              	 
			           	 			tempAppLinkString 	= tempEApplication.getOut().trim();
			           	 			nextTokenApp 		= "";
			           	 			tempAppLinkStringTokenizer = new StringTokenizer(tempAppLinkString,",");
			                    
			                        while (tempAppLinkStringTokenizer.hasMoreTokens()) {
			                       	 
			                       	 				nextTokenApp = tempAppLinkStringTokenizer.nextToken().trim();
			                       	 				
			                       	 				
			                       	 				if(checkTokenExistInApplications(nextTokenApp)){
			                           
				                       	 				edgeElement = doc.createElement("edge");
				                       				    //set source attribute to edgeElement
				                       		            edgeElementSourceAttribute = doc.createAttribute("source");
				                       		            edgeElementSourceAttribute.setValue(tempEApplication.getApplicationName());
				                       		            edgeElement.setAttributeNode(edgeElementSourceAttribute);
				                       		            
				                       		            //set target attribute to edgeElement
				                       		            edgeElementTargetAttribute = doc.createAttribute("target");
				                       		            edgeElementTargetAttribute.setValue(nextTokenApp);
				                       		            edgeElement.setAttributeNode(edgeElementTargetAttribute);
				                       		            
				                       		            edgeElement.setTextContent(" ");			                       				   
				                       		            //important
				                       		            graph.appendChild(edgeElement);
				                       		            
				                       		            log4j.debug("createEOrganismGraphML - add edge for app: "+ tempEApplication.getApplicationName() + ">>" + nextTokenApp);
			                       		            
			                       	 				}//if valid
					                                 
			                        }// for all the app out tokens
			                        

			                        // create also links based to APPLICATIONS > COMPONENTS mapping
			                        
			                        // if has components
								       	             if(tempEApplication.getComponentsArrayList().size()>0){
								       	            	 
								       	            	     
								       	                     for(int j = 0; j < (tempEApplication.getComponentsArrayList()).size(); j++){
					
								       	                    	 	 tempComponent = new EComponent();
					
								       	                    	 	 tempComponent = (EComponent) tempEApplication.getComponentsArrayList().get(j);
								       	                     	 	 
								       	                    	 	 edgeElement = doc.createElement("edge");
							                       				    //set source attribute to edgeElement
							                       		            edgeElementSourceAttribute = doc.createAttribute("source");
							                       		            edgeElementSourceAttribute.setValue(tempEApplication.getApplicationName());
							                       		            edgeElement.setAttributeNode(edgeElementSourceAttribute);
							                       		            
							                       		            //set target attribute to edgeElement
							                       		            edgeElementTargetAttribute = doc.createAttribute("target");
							                       		            edgeElementTargetAttribute.setValue(tempComponent.getComponentName());
							                       		            edgeElement.setAttributeNode(edgeElementTargetAttribute);
							                       		            
							                       		            edgeElement.setTextContent(" ");			                       				   
							                       		            //important
							                       		            graph.appendChild(edgeElement);
							                       		            
							                       		            log4j.debug("createEOrganismGraphML - add edge for app: "+ tempEApplication.getApplicationName() + ">> COMP " + tempComponent.getComponentName());
								       	                    	 	
					
								       	                     }// for
								       	                     
								       	                     
								       	             }// if we have components
			                        
			                       
			                        
			                       
			                       // stop   EAPPLICATION_LINK
			               }// if app out
	     		   			   
		   }//for i - applications
		  
			  
		   
		   //write the content into xml file
		   TransformerFactory transformerFactory = TransformerFactory.newInstance();
		   Transformer transformer = transformerFactory.newTransformer();
		   DOMSource source = new DOMSource(doc);
		   //C:\apache\Tomcat 8.0\webapps\hexplorer
		   //StreamResult result =  new StreamResult(new File("C:\\apache\\Tomcat 8.0\\webapps\\hexplorer\\eorganism-graphml.xml"));
		   
		   StreamResult result =  new StreamResult(new File(fileGraphML));
		   transformer.transform(source, result);

		   log4j.debug("createEOrganismGraphML - OK");

			}catch(ParserConfigurationException pce){
					 pce.printStackTrace();
			}catch(TransformerException tfe){
					 tfe.printStackTrace();
				
			
			} catch (Exception e) {
						log4j.error("createEOrganismGraphML|| - Exception:");
						log4j.error("Error:"+ e.getMessage());
			            JOptionPane.showMessageDialog( null,
			                                           "Exception " +"\n"+
			                                           e.getMessage());
			            return;
			}// end catch
		 
	 }//createEOrganismGraphML
	 
	 
	 public boolean checkTokenExistInApplications(String _nextTokenApp){
			 boolean booleanResult = false;
			 EApplication 					tempEApplication 		= new EApplication();
			   //check all
			   for( int i = 0; i <  modelOfEApplication.size() ; i++){
				   
				   	tempEApplication = new EApplication();
				   	tempEApplication = (EApplication) modelOfEApplication.elementAt(i);
				   	
				   	if (_nextTokenApp.equals(tempEApplication.getApplicationName())){
				   		 
				   		 if("APPLICATION".equals(tempEApplication.metadataType) | "COMPONENT".equals(tempEApplication.metadataType)){
				   			 return true;
				   		 }// if valid all MODEL
				   		
				   	}// if valid token
			   }// for
		 
		 return booleanResult;
	 }//checkTokenExistInApplications
	 
	 public void createEOrganismJSON(){
			
		 EApplication 		tempEApplication = new EApplication();
		 // add subnodes to new children of the root node
         EComponent 		tempComponent 		= new EComponent();
         JDatabase 			tempDatabase 		= new JDatabase();
         EHardware 			tempHardware	 	= new EHardware();
         ESequenceFlow 		tempSequenceFlow 	= new ESequenceFlow();
         EOperation 		tempOperation 		= new EOperation();
         EInterface      	tempInterface     	= new EInterface();
         EAttribute      	tempAttribute     	= new EAttribute();
         EHardware			tempEHardware 		= new EHardware();
        
         String				jsonContent	 		= "";
         
         String				jsonApplicationString			    = "";
         String				jsonTempApplicationString			= "";
	     String				jsonTempComponentString				= "";
	     String				jsonTempSequenceFlowString			= "";
	     String				jsonTempHardwareString				= "";
         String				jsonTempServiceString				= "";
         String				jsonTempInterfaceString				= "";
         String				jsonTempAttributeString				= "";
         String				jsonTempDatabasesString				= "";
         
  		
         String 			replaceOperationString				= "";
         
         String				childrenString						= "";
    			  
		 for( int i = 0; i <  modelOfEApplication.size() ; i++){
			 
			 			      	   
			   tempEApplication = new EApplication();
	        	 
	           tempEApplication = (EApplication) modelOfEApplication.elementAt(i);
	        	
			   if((tempEApplication.metadataType).equals("MODEL")){
					   
				   		log4j.debug("...............createEOrganismJSON(tempEApplication): "+i+"......................."+tempEApplication.getApplicationName());
			          
			           if(i==0){
			        	   jsonTempApplicationString =  (tempEApplication.getApplicationJSON());
			        	   
			        	   
			           }else{
			           
			        	   jsonTempApplicationString =  (tempEApplication.getApplicationJSON())+"\n"+
			        			   					","+
			        	  						    jsonTempApplicationString;
			           }//
			          
				      
			   } // if 
			   
		   }//for i - applications
		 
			 if (modelOfESequenceFlow.size() > 0 ){
			   
				 for( int m = 0; m <  modelOfESequenceFlow.size() ; m++){
				        	 
				        	 tempSequenceFlow = new ESequenceFlow();
				        	 
				        	 tempSequenceFlow = (ESequenceFlow) modelOfESequenceFlow.elementAt(m);
				        	 
				        	   if(m==0){
				        		   jsonTempSequenceFlowString =  (tempSequenceFlow.getJSON());
					        	   
					        	   
					           }else{
					           
					        	   jsonTempSequenceFlowString =  (tempSequenceFlow.getJSON())+"\n"+
					        			   					","+
					        			   					jsonTempSequenceFlowString;
					           }//
				} // for
			
			 }//if sequences
		 
			 if (modelOfEHardwares.size() > 0 ){
			   
				 for( int k = 0; k <  modelOfEHardwares.size() ; k++){
		        	 
		        	 tempEHardware = new EHardware();
		         	 tempEHardware = (EHardware) modelOfEHardwares.elementAt(k);
		         	 
			         	if(k==0){
			         		jsonTempHardwareString =  (tempEHardware.getJSON());
			        	   
			        	   
			           }else{
			           
			        	   jsonTempHardwareString =  (tempEHardware.getJSON())+"\n"+
			        			   					","+
			        			   					jsonTempHardwareString;
			           }//
		        	 
		         } // for all hardwares
			   
			 }// if hardware 
						 
			// modelOfEEnvironment    		= new Vector();// EEnvironment
		    // modelOfERelease    			= new Vector();// ERelease
			// modelOfEProcess                = new Vector();// EProcess
			// modelOfEGroup                  = new Vector();// EGroup
			// modelOfEPerson                 = new Vector();// EPerson
			
			if (modelOfJDatabases .size() > 0 ){
			   
				 for( int t = 0; t <  modelOfJDatabases.size() ; t++){
		        	 
					 tempDatabase 		= new JDatabase();
					 tempDatabase = (JDatabase) modelOfJDatabases.elementAt(t);
		         	 
			           if(t==0){
			         		jsonTempDatabasesString =  (tempDatabase.getJSON());
			        	   
			        	   
			           }else{
			           
			        	   jsonTempDatabasesString =    (tempDatabase.getJSON())+"\n"+
				        			   					","+
				        			   					jsonTempDatabasesString;
			           }//
		        	 
		         } // for all databases
			   
			 }// if database 
		 
		    jsonTempApplicationString.replaceAll("\n", "\n"+",");
		    jsonTempSequenceFlowString.replaceAll("\n", "\n"+",");
		    jsonTempHardwareString.replaceAll("\n", "\n"+",");
		    jsonTempDatabasesString.replaceAll("\n", "\n"+",");
		   		
		 	childrenString = 		 "{\"name\": \"APPLICATIONS\",\"description\": \"APPLICATIONS DESC\",\"url\": \"http://www.rogers.com\", \"children\": ["+ jsonTempApplicationString +"] }" + "\n"+","+
		 							 "{\"name\": \"DATABASES\",\"description\": \"DATABASES DESC\",\"url\": \"http://www.rogers.com\", \"children\": ["+ jsonTempDatabasesString +"] }" + "\n"+","+
	     							 "{\"name\": \"SEQUENCE FLOWS\",\"description\": \"SEQUENCE DESC\",\"url\": \"http://www.rogers.com\", \"children\": ["+ jsonTempSequenceFlowString +"] }"+ "\n"+","+
	  							     "{\"name\": \"HARDWARE\",\"description\": \"HARDWARE DESC\",\"url\": \"http://www.rogers.com\", \"children\": ["+ jsonTempHardwareString +"] }";
	 	
		 	jsonContent	 = "{"+"\n"+
				        	   "\"name\": \"Rogers Enterprise EOrganism\","+"\n"+
				        	   "\"children\": ["+"\n"+
				        	   						childrenString+"\n"+
						        	   	
						        	    "]"+"\n"+
								"}";
		   
		   printFile(jsonContent, "eorganism.json","C:/apache/Tomcat 8.0/webapps/hexplorer");
		 
		   log4j.debug("createEOrganismJSON - OK");
		   
		   //System.out.println("createEOrganismJSON - JSON" + jsonContent);

	 }//createEOrganismJSON
	 
	 
	 public void createEOrganismDbXML(){
		  
         JDatabase 			tempJDatabase 		= new JDatabase();
         ETable				tempTable 			= new ETable();
         EColumn			tempColumn 			= new EColumn();
		
         try{
        	 
		   DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		   DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		   //root elements
		   Document doc = docBuilder.newDocument();

		   Element rootElement = doc.createElement("tree");
		   doc.appendChild(rootElement);

		   //declarations element
		   Element declarations = doc.createElement("declarations");
		   rootElement.appendChild(declarations);
		   

		   //declarations element
		   Element attributeDeclElem = doc.createElement("attributeDecl");
		   declarations.appendChild(attributeDeclElem);

		   //set attribute to staff element
		   Attr attribute1 = doc.createAttribute("name");
		   attribute1.setValue("name");
		   Attr attribute11 = doc.createAttribute("type");
		   attribute11.setValue("String");
		   
		   attributeDeclElem.setAttributeNode(attribute1);
		   attributeDeclElem.setAttributeNode(attribute11);
			 
		   //declarations brachroot
		   Element branch1 = doc.createElement("branch");
		   rootElement.appendChild(branch1);

		  
		   //declarations attribute element
		   Element attribute2 = doc.createElement("attribute");
		   branch1.appendChild(attribute2);
		   
		   //set attribute to staff element
		   Attr attribute3 = doc.createAttribute("name");
		   attribute3.setValue("name");
		   
		   Attr attribute33 = doc.createAttribute("value");
		   attribute33.setValue("Enterprise DB Metamodel");
		   
		   attribute2.setAttributeNode(attribute3);
		   attribute2.setAttributeNode(attribute33);
		   
		   // applicationroot
		   
		   //declarations brachroot
		   Element branchApp = doc.createElement("branch");
		   branch1.appendChild(branchApp);
  
		   //declarations attribute element
		   Element attributeApp = doc.createElement("attribute");
		   branchApp.appendChild(attributeApp);
		   
		   //set attribute to staff element
		   Attr attributeName = doc.createAttribute("name");
		   attributeName.setValue("name");
		   
		   Attr attributeValue = doc.createAttribute("value");
		   attributeValue.setValue("Databases");
		   
		   branchApp.setAttributeNode(attributeName);
		   branchApp.setAttributeNode(attributeValue);
		   
		 //declarations brachroot
		   Element branchDB = doc.createElement("branch");
		   branch1.appendChild(branchDB);
  
		   //declarations attribute element
		   Element attributeDB = doc.createElement("attribute");
		   branchDB.appendChild(attributeDB);
		   
		   //set attribute to staff element
		   Attr attributeNameDB = doc.createAttribute("name");
		   attributeNameDB.setValue("name");
		   
		   Attr attributeValueDB = doc.createAttribute("value");
		   attributeValueDB.setValue("Databases");
		   
		   branchDB.setAttributeNode(attributeNameDB);
		   branchDB.setAttributeNode(attributeValueDB);
		   
		   
		   for( int t = 0; t <  modelMetadataDatabases.size() ; t++){
	        	 
	        	 tempJDatabase = new JDatabase();
	        	 
	        	 tempJDatabase = (JDatabase) modelMetadataDatabases.elementAt(t);
	        	 
	        	 //declarations brachroot
				   Element branch2 = doc.createElement("branch");
				   branchApp.appendChild(branch2);
				   
				   //declarations attribute element
				   Element attribute4 = doc.createElement("attribute");
				   branch2.appendChild(attribute4);
				   
				   //set attribute to staff element
				   Attr attribute5 = doc.createAttribute("name");
				   attribute5.setValue("name");
				   
				   Attr attribute55 = doc.createAttribute("value");
				   attribute55.setValue(tempJDatabase.getDatabaseName() + ":: APP: "+ tempJDatabase.getApplication());
				   	
				   
				   attribute4.setAttributeNode(attribute5);
				   attribute4.setAttributeNode(attribute55);
	                
				   log4j.debug("...............createEOrganismDbXML(tempEApplication): "+ t +  " - "+tempJDatabase.getDatabaseName());
			  
			       //System.out.println("createEOrganismDbXML - OK t= " +t);
	        	 
	        	  if(tempJDatabase.getMetadataTablesArrayList().size()>0){
	            	 
	        		 
  	            	     for(int z = 0; z < (tempJDatabase.getMetadataTablesArrayList().size()); z++){

  	                    	 	 tempTable = new ETable();

  	                    	 	 tempTable = (ETable) (tempJDatabase.getMetadataTablesArrayList()).get(z);
  	                    	 	
  	                    	 	 //declarations brachroot
								   Element branch3 = doc.createElement("branch");
								   branch2.appendChild(branch3);
								   
								   //declarations attribute element
								   Element attribute6 = doc.createElement("attribute");
								   branch3.appendChild(attribute6);
								   
								   //set attribute to staff element
								   Attr attribute7 = doc.createAttribute("name");
								   attribute7.setValue("name");
									  
								   
								   Attr attribute77 = doc.createAttribute("value");
								   attribute77.setValue(tempTable.getName()+"::TABLE");
									  
								   attribute6.setAttributeNode(attribute7);
								   attribute6.setAttributeNode(attribute77);
  	                    	 	 
  	                    	 	 
  	                    	 	 	// add columns to existing tables
	    	                    	for(int x = 0; x < (tempTable.getMetadataColumnsArrayList().size()); x++){
	
			       	                    	 	 tempColumn = new EColumn();
			
			       	                    	 	 tempColumn = (EColumn) (tempTable.getMetadataColumnsArrayList()).get(x);
			       	                    	 	 
			       	                    	 	   //declarations brachroot
												   Element branch4 = doc.createElement("branch");
												   branch3.appendChild(branch4);
												   
												   //declarations attribute element
												   Element attribute71 = doc.createElement("attribute");
												   branch4.appendChild(attribute71);
													  
												   
												   //set attribute to staff element
												   Attr attribute8 = doc.createAttribute("name");
												   attribute8.setValue("name");
												   
												   Attr attribute88 = doc.createAttribute("value");
												   attribute88.setValue(tempColumn.getName() +" :: type-" + tempColumn.getDataType()+"::COL");
													  
												   attribute71.setAttributeNode(attribute8);
												   attribute71.setAttributeNode(attribute88);
			       	                    	 	 
			       	                    	 	 
			       	                    	     //log4j.debug(".....ADD COLUMNS TO TABLE...."+ tempTable.getName() +"::"+ tempColumn.getName());
	
	    	                    	 }// for all columns
	    	                    	 
	                    	 	 
  	                     }// for all tables
  	              
	        	  }// tempJDatabase.getMetadataTablesArrayList().size()
	        	 
	        	
		   } //modelMetadataDatabases
		   
		   
		   //write the content into xml file
		   TransformerFactory transformerFactory = TransformerFactory.newInstance();
		   Transformer transformer = transformerFactory.newTransformer();
		   DOMSource source = new DOMSource(doc);
 		   
		   //C:\apache\Tomcat 8.0\webapps\hexplorer
		   //StreamResult result =  new StreamResult(new File("C:\\apache\\Tomcat 8.0\\webapps\\hexplorer\\eorganism-db-view.xml"));
		   
		   StreamResult result =  new StreamResult(new File(dbXML));
		  
		   transformer.transform(source, result);

		   System.out.println("createEOrganismDbXML - OK");

			}catch(ParserConfigurationException pce){
					 pce.printStackTrace();
			}catch(TransformerException tfe){
					 tfe.printStackTrace();
				
			
			} catch (Exception e) {
						log4j.error("createEOrganismDbXML|| - Exception:");
						log4j.error("Error:"+ e.getMessage());
			            JOptionPane.showMessageDialog( null,"Exception " +"\n"+ e.getMessage());
			            return;
			}// end catch

	 }//createEOrganismDbXML
	 
	 public void createEOrganismDbJSON(){
	
	 }//createEOrganismDbJSON
	 
     public JTree getEOrganismTree(){

         //System.out.println("getEnterpriseTree modelOfBeans.size(): " + modelOfBeans.size());
    	 ArrayList tempESystemApplicationArrayList = new ArrayList();
    	 ArrayList tempESubdomainArrayList = new ArrayList();
     	 
    	 
    	 EOrganism tempEorganism ;
    	 
    	 EDomain 		tempEDomain ;
     	 ESystem 		tempESystem ;
     	 ESubdomain 	tempESubdomain ;
    	 
    	 EApplication 	tempEApplication ;
    	 EApplication 	tempEApplication2;
    	 EApplication 	tempEApplicationSystem ;
     	 
    	 EApplication 	tempEnvironmentApplication;
         EParameter     tempEnvironmentParameter ;
    	 EEnvironment 	tempEnvironment ;
    	 ERelease 		tempRelease ;
    	 EProject 		tempProject;
         EProcess       tempProcess;
         EGroup         tempGroup;
         EPerson        tempPerson;
    	 EHardware 		tempEHardware ;
    	 JDatabase		tempJDatabase ;
    	 EComponent 	tempComponent 		= new EComponent();
    	 EOperation 	tempOperation 		= new EOperation();
    	 ESequenceFlow  tempSequenceFlow 	= new ESequenceFlow();
    	 ETable 		tempTable 			= new ETable();
    	 EColumn 		tempColumn			= new EColumn();
     	 
    	 DefaultMutableTreeNode tempESystemApplicationNode;
    	 DefaultMutableTreeNode applicationTemp ;
    	 DefaultMutableTreeNode hardwareTemp ;
    	 DefaultMutableTreeNode databaseTemp ;
    	 
    	 DefaultMutableTreeNode components;
    	 DefaultMutableTreeNode operations;
    	 DefaultMutableTreeNode sequences;
    	 DefaultMutableTreeNode processes;
    	 DefaultMutableTreeNode groups;
    	 DefaultMutableTreeNode persons;
         DefaultMutableTreeNode business;
         DefaultMutableTreeNode actors;
         
         // 
         DefaultMutableTreeNode applicationsHardwarePhysical;
         DefaultMutableTreeNode applicationDomains;// CRM. CSM, V21, OE, BILLING, SUPPLY CHAIN, FINANCE, MEDIA, CALL CENTER, MW
         DefaultMutableTreeNode applicationType;// Web Channel
         DefaultMutableTreeNode applicationIntegration;//
         DefaultMutableTreeNode applicationInfrastructureType;//
         DefaultMutableTreeNode applicationLayers;// WEB, DESKTOP, MW1, MW2, MW3, MW EAI/ EIF, MW APP, APP, DB
         DefaultMutableTreeNode applicationSegment;//
         DefaultMutableTreeNode applicationCritical;// VITAL, CRITICAL, IMPORTANT
    	 
    	 DefaultMutableTreeNode applicationComponentsTemp;
    	 DefaultMutableTreeNode applicationOperationsTemp;
    	 DefaultMutableTreeNode applicationInterfacesTemp;
    	 DefaultMutableTreeNode applicationEntitiesTemp;
    	 DefaultMutableTreeNode envApplicationNodeTemp;
    	 DefaultMutableTreeNode projectsNodeTemp;
         DefaultMutableTreeNode processesNodeTemp;
         DefaultMutableTreeNode groupsNodeTemp;
         DefaultMutableTreeNode personsNodeTemp;
         DefaultMutableTreeNode ownersNodeTemp;
    	 DefaultMutableTreeNode applicationDatabasesTemp;
    	 DefaultMutableTreeNode applicationHardwareTemp;
    	 DefaultMutableTreeNode applicationPropertiesTemp;
    	 DefaultMutableTreeNode applicationBusinessFunctionsTemp;
    	 DefaultMutableTreeNode applicationActivityTemp	;
    	 DefaultMutableTreeNode applicationSequenceFlowTemp	;
    	 DefaultMutableTreeNode applicationInfrastructureTemp;
    	 DefaultMutableTreeNode applicationEnvironmentTemp;
         DefaultMutableTreeNode envParameterNodeTemp;
        
    	 DefaultMutableTreeNode applicationComponentsCollectionTemp;
    	 DefaultMutableTreeNode applicationOperationsCollectionTemp;
    	 DefaultMutableTreeNode applicationInterfacesCollectionTemp;
    	 DefaultMutableTreeNode applicationEntitiesCollectionTemp;
    	 DefaultMutableTreeNode applicationDatabasesCollectionTemp;
    	 DefaultMutableTreeNode applicationHardwareCollectionTemp;
    	 DefaultMutableTreeNode applicationPropertiesCollectionTemp;
    	 DefaultMutableTreeNode applicationBusinessFunctionsCollectionTemp;
    	 DefaultMutableTreeNode applicationActivityCollectionTemp;
    	 DefaultMutableTreeNode applicationSequenceFlowCollectionTemp;
    	 DefaultMutableTreeNode applicationInfrastructureCollectionTemp;
    	 DefaultMutableTreeNode applicationEnvironmentCollectionTemp;
    	 
    	 DefaultMutableTreeNode tempDomain;
    	 DefaultMutableTreeNode tempEDomainNode;
    	 DefaultMutableTreeNode tempSystem;
    	 DefaultMutableTreeNode tempESystemNode;
    	 DefaultMutableTreeNode tempSystem2;
    	 
    	 DefaultMutableTreeNode hardwareMachineCollectionTemp;
    	 DefaultMutableTreeNode hardwareMVMCollectionTemp;

    	 DefaultMutableTreeNode hardwareMachineTemp;
    	 DefaultMutableTreeNode hardwareMVMTemp;
    	 
    	 DefaultMutableTreeNode metadataDatabaseNodeTemp;
    	 DefaultMutableTreeNode tablesNodeTemp;
    	 DefaultMutableTreeNode metadataTableNodeTemp;
    	 
         DefaultMutableTreeNode   tempBeanNode;
         DefaultTreeModel         tempBeanTreeModel;
         DefaultMutableTreeNode   tempTableNode;
         DefaultTreeModel         tempTableTreeModel;

         JTree                    tempEorganismTree;
         JTree                    tempTableTree;

         // Root node
         enterpriseRootNode          = new DefaultMutableTreeNode("Enterprise" );
         enterpriseTreeCellRenderer  = new DefaultTreeCellRenderer();
        // enterpriseTreeCellRenderer.setOpenIcon((Icon)iconOpen);
         //enterpriseTreeCellRenderer.setClosedIcon((Icon)iconClose);
        // enterpriseTreeCellRenderer.setLeafIcon((Icon)iconNew);
         
         // new
         //systemsModelRootNode		= new DefaultMutableTreeNode("System Model :: Applications...");
         domainsModel          			= new DefaultMutableTreeNode("COBRA :: DOMAIN :: MODEL");
         systemsModel          			= new DefaultMutableTreeNode("System :: MODEL");
         
         applicationsInventory          = new DefaultMutableTreeNode("Applications :: ITS Inventory :: Application <> Owner ::  VIEW" );
         applicationsHardwarePhysical   = new DefaultMutableTreeNode("Applications :: CMDB Inventory :: Application <> Hardware <> Physical ::  VIEW" );
         applications 			        = new DefaultMutableTreeNode("Applications :: MetaModel :: Application <> Component <> Service <> Interface :: MODEL VIEW ");
         applicationsMaster 			= new DefaultMutableTreeNode("Applications :: MASTER VIEW");
         applicationsModel 				= new DefaultMutableTreeNode("Applications :: MODEL VIEW");
            
         databasesMetamodel 			= new DefaultMutableTreeNode("Databases :: MetaModel :: Database <> Table <> Column :: MODEL VIEW ");
         
         business                       = new DefaultMutableTreeNode("Business Model :: Business");
         actors                         = new DefaultMutableTreeNode("System Actors");
         
			         // new
			        business.add(new DefaultMutableTreeNode("CABLE: High Speed Internet service"));
			        business.add(new DefaultMutableTreeNode("CABLE: Cable TV service"));
			        business.add(new DefaultMutableTreeNode("CABLE: Rogers Home Phone"));
			        business.add(new DefaultMutableTreeNode("CABLE: Rogers Smart Home Monitoring"));
			        business.add(new DefaultMutableTreeNode("CABLE: Rogers Smart Home Automation"));
			        business.add(new DefaultMutableTreeNode("WIRELINE Phone: Rogers Home Phone"));
			        business.add(new DefaultMutableTreeNode("WIRELESS Phone: Wireless Communications"));
			        business.add(new DefaultMutableTreeNode("WIRELESS Phone: Wireless Internet Access"));
			        business.add(new DefaultMutableTreeNode("WIRELESS Access: Wireless Home Internet Access"));
			        business.add(new DefaultMutableTreeNode("CABLE WIRELESS: Rogers Home phone Internet"));
			        business.add(new DefaultMutableTreeNode("MEDIA : TV : Rogers TELEVISION Services"));
			        business.add(new DefaultMutableTreeNode("MEDIA : MAGAZINES : Rogers MAgazines Services"));
			        business.add(new DefaultMutableTreeNode("MEDIA : INTERNET : Rogers Internet Services"));
			        business.add(new DefaultMutableTreeNode("MEDIA : CABLE : Rogers Internet Services"));
			        business.add(new DefaultMutableTreeNode("MEDIA : WIRELESS : Rogers Internet Services"));
			        business.add(new DefaultMutableTreeNode("RON - Rogers One Number"));
			        business.add(new DefaultMutableTreeNode("BANKING - Rogers Banking Services"));       
			        business.add(new DefaultMutableTreeNode("Rogers Business Services"));       
			        //
			        actors.add(new DefaultMutableTreeNode("CUSTOMER: CABLE: CABLE TV, INTERNET, MEDIA, SHM"));
			        actors.add(new DefaultMutableTreeNode("CUSTOMER: WIRELESS: WIRELESS PHONE, WIRELESS INTERNET, WIRELSS MEDIA, WIRELESS SHM"));
			        actors.add(new DefaultMutableTreeNode("CUSTOMER: BANK: BANKING SERVICES"));
			        actors.add(new DefaultMutableTreeNode("CUSTOMER: MEDIA: MEDIA CHANNELS, MEDIA NEWSPAPAERS, MEDIA INTERNET"));
			        
			        actors.add(new DefaultMutableTreeNode("BUSINESS CUSTOMER: CABLE: CABLE TV, INTERNET, MEDIA, SHM"));
			        actors.add(new DefaultMutableTreeNode("BUSINESS CUSTOMER: WIRELESS: WIRELESS PHONE, WIRELESS INTERNET, WIRELSS MEDIA, WIRELESS SHM"));
			        actors.add(new DefaultMutableTreeNode("BUSINESS CUSTOMER: BANK: BANKING SERVICES"));
			        actors.add(new DefaultMutableTreeNode("BUSINESS CUSTOMER: MEDIA: MEDIA CHANNELS, MEDIA NEWSPAPAERS, MEDIA INTERNET"));
			         
			        actors.add(new DefaultMutableTreeNode("BUSINESS OWNER: CABLE: CABLE TV, INTERNET, MEDIA, SHM"));
			        actors.add(new DefaultMutableTreeNode("BUSINESS OWNER: WIRELESS: WIRELESS PHONE, WIRELESS INTERNET, WIRELSS MEDIA, WIRELESS SHM"));
			        actors.add(new DefaultMutableTreeNode("BUSINESS OWNER: BANK: BANKING SERVICES"));
			        actors.add(new DefaultMutableTreeNode("BUSINESS OWNER: MEDIA: MEDIA CHANNELS, MEDIA NEWSPAPAERS, MEDIA INTERNET"));
			        
			        actors.add(new DefaultMutableTreeNode("IT OWNER: CABLE: CABLE TV, INTERNET, MEDIA, SHM"));
			        actors.add(new DefaultMutableTreeNode("IT  OWNER: WIRELESS: WIRELESS PHONE, WIRELESS INTERNET, WIRELSS MEDIA, WIRELESS SHM"));
			        actors.add(new DefaultMutableTreeNode("IT  OWNER: BANK: BANKING SERVICES"));
			        actors.add(new DefaultMutableTreeNode("IT  OWNER: MEDIA: MEDIA CHANNELS, MEDIA NEWSPAPAERS, MEDIA INTERNET"));
			        
			        actors.add(new DefaultMutableTreeNode("CUSTOMER SUPPORT REP: CABLE: CABLE TV, INTERNET, MEDIA, SHM"));
			        actors.add(new DefaultMutableTreeNode("CUSTOMER SUPPORT REP: WIRELESS: WIRELESS PHONE, WIRELESS INTERNET, WIRELSS MEDIA, WIRELESS SHM"));
			        actors.add(new DefaultMutableTreeNode("CUSTOMER SUPPORT REP: BANK: BANKING SERVICES"));
			        actors.add(new DefaultMutableTreeNode("CUSTOMER SUPPORT REP: MEDIA: MEDIA CHANNELS, MEDIA NEWSPAPAERS, MEDIA INTERNET"));
			         
			        actors.add(new DefaultMutableTreeNode("APP SUPPORT: CABLE: CABLE TV, INTERNET, MEDIA, SHM"));
			        actors.add(new DefaultMutableTreeNode("APP SUPPORT: WIRELESS: WIRELESS PHONE, WIRELESS INTERNET, WIRELSS MEDIA, WIRELESS SHM"));
			        actors.add(new DefaultMutableTreeNode("APP SUPPORT: BANK: BANKING SERVICES"));
			        actors.add(new DefaultMutableTreeNode("APP SUPPORT: MEDIA: MEDIA CHANNELS, MEDIA NEWSPAPAERS, MEDIA INTERNET"));
			        
			        actors.add(new DefaultMutableTreeNode("APP DEV: CABLE: CABLE TV, INTERNET, MEDIA, SHM"));
			        actors.add(new DefaultMutableTreeNode("APP DEV: WIRELESS: WIRELESS PHONE, WIRELESS INTERNET, WIRELSS MEDIA, WIRELESS SHM"));
			        actors.add(new DefaultMutableTreeNode("APP DEV: BANK: BANKING SERVICES"));
			        actors.add(new DefaultMutableTreeNode("APP DEV: MEDIA: MEDIA CHANNELS, MEDIA NEWSPAPAERS, MEDIA INTERNET"));
			        
			        actors.add(new DefaultMutableTreeNode("APPLICATION: CABLE: CABLE TV, INTERNET, MEDIA, SHM"));
			        actors.add(new DefaultMutableTreeNode("APPLICATION: WIRELESS: WIRELESS PHONE, WIRELESS INTERNET, WIRELSS MEDIA, WIRELESS SHM"));
			        actors.add(new DefaultMutableTreeNode("APPLICATION: BANK: BANKING SERVICES"));
			        actors.add(new DefaultMutableTreeNode("APPLICATION: MEDIA: MEDIA CHANNELS, MEDIA NEWSPAPAERS, MEDIA INTERNET"));
			        
			        actors.add(new DefaultMutableTreeNode("EXTERNAL COMPANIES: CABLE: CABLE TV, INTERNET, MEDIA, SHM"));
			        actors.add(new DefaultMutableTreeNode("EXTERNAL COMPANIES: WIRELESS PHONE, WIRELESS INTERNET, WIRELSS MEDIA, WIRELESS SHM"));
			        actors.add(new DefaultMutableTreeNode("EXTERNAL COMPANIES: BANK: BANKING SERVICES"));
			        actors.add(new DefaultMutableTreeNode("EXTERNAL COMPANIES: MEDIA: MEDIA CHANNELS, MEDIA NEWSPAPAERS, MEDIA INTERNET"));
			         
			         // Dec 8, 2013
			          applicationDomains            = new DefaultMutableTreeNode("Application Domains");
			          applicationType               = new DefaultMutableTreeNode("Application Types");
			          applicationIntegration        = new DefaultMutableTreeNode("Application Intregration Types");
			          applicationInfrastructureType = new DefaultMutableTreeNode("Application Infrastructure Types");
			          applicationLayers             = new DefaultMutableTreeNode("Application Layers");
			          applicationSegment            = new DefaultMutableTreeNode("Application Segments");
			          applicationCritical           = new DefaultMutableTreeNode("Application Critical Level");
			                 
			        applicationDomains.add(new DefaultMutableTreeNode("MAESTRO")); 
			        applicationDomains.add(new DefaultMutableTreeNode("CRM"));
			        applicationDomains.add(new DefaultMutableTreeNode("CSM"));
			        applicationDomains.add(new DefaultMutableTreeNode("V21"));
			        applicationDomains.add(new DefaultMutableTreeNode("SS"));
			        applicationDomains.add(new DefaultMutableTreeNode("OE"));
			        applicationDomains.add(new DefaultMutableTreeNode("BILLING"));
			        applicationDomains.add(new DefaultMutableTreeNode("SUPPLY CHAIN"));
			        applicationDomains.add(new DefaultMutableTreeNode("MEDIA"));
			        applicationDomains.add(new DefaultMutableTreeNode("BANKING"));
			        applicationDomains.add(new DefaultMutableTreeNode("CALL CENTER APPS"));
			        applicationDomains.add(new DefaultMutableTreeNode("MIDLLEWARE"));
			        applicationDomains.add(new DefaultMutableTreeNode("ESF"));
			        applicationDomains.add(new DefaultMutableTreeNode("SSO"));
			        
			        applicationType.add(new DefaultMutableTreeNode("ORDER ENTRY")); 
			        applicationType.add(new DefaultMutableTreeNode("CRM")); 
			        applicationType.add(new DefaultMutableTreeNode("CONSUMER WEB")); 
			        applicationType.add(new DefaultMutableTreeNode("DEALER WEB")); 
			        applicationType.add(new DefaultMutableTreeNode("SUPPLY CHAIN")); 
			        applicationType.add(new DefaultMutableTreeNode("THIRD PARTY")); 
			        applicationType.add(new DefaultMutableTreeNode("FINANCE")); 
			        applicationType.add(new DefaultMutableTreeNode("CITRIX")); 
			        applicationType.add(new DefaultMutableTreeNode("ALLSTREAM")); 
			        applicationType.add(new DefaultMutableTreeNode("MDC")); 
			        applicationType.add(new DefaultMutableTreeNode("EBI"));
			        
			        applicationCritical.add(new DefaultMutableTreeNode("VITAL")); 
			        applicationCritical.add(new DefaultMutableTreeNode("CRITICAL")); 
			        applicationCritical.add(new DefaultMutableTreeNode("IMPORTANT")); 
			        applicationCritical.add(new DefaultMutableTreeNode("STANDARD")); 
			        
			        applicationLayers.add(new DefaultMutableTreeNode("WEB")); 
			        applicationLayers.add(new DefaultMutableTreeNode("DESKTOP")); 
			        applicationLayers.add(new DefaultMutableTreeNode("MIDDLEWARE APP1")); 
			        applicationLayers.add(new DefaultMutableTreeNode("MIDDLEWARE 2")); 
			        applicationLayers.add(new DefaultMutableTreeNode("MIDDLEWARE 3")); 
			        applicationLayers.add(new DefaultMutableTreeNode("MIDDLEWARE EI")); 
			        applicationLayers.add(new DefaultMutableTreeNode("MIDDLEWARE END APP")); 
			        applicationLayers.add(new DefaultMutableTreeNode("DATA LAYER")); 
			        applicationLayers.add(new DefaultMutableTreeNode("VITAL")); 
			        
			        //applicationIntegration;//
			        applicationIntegration.add(new DefaultMutableTreeNode("ENTRY FRONT SYSTEM")); 
			        applicationIntegration.add(new DefaultMutableTreeNode("MIDDLE SYSTEM SYSTEM")); 
			        applicationIntegration.add(new DefaultMutableTreeNode("END SYSTEM")); 
			        
			        // applicationInfrastructure;//
			        applicationInfrastructureType.add(new DefaultMutableTreeNode("Web Channel ECommerce")); 
			        applicationInfrastructureType.add(new DefaultMutableTreeNode("Third Party")); 
			        applicationInfrastructureType.add(new DefaultMutableTreeNode("CITRIX")); 
			        applicationInfrastructureType.add(new DefaultMutableTreeNode("ALLSTREAM INFRA")); 
			        applicationInfrastructureType.add(new DefaultMutableTreeNode("Mobile Wallet ECommerce")); 
			    
			        
			        applicationSegment.add(new DefaultMutableTreeNode("V21")); 
			        applicationSegment.add(new DefaultMutableTreeNode("SS CABLE")); 
			        applicationSegment.add(new DefaultMutableTreeNode("MEDIA")); 
			        applicationSegment.add(new DefaultMutableTreeNode("BANK")); 
        
         
         applicationComponents				= new DefaultMutableTreeNode("Application Components");// CRM APP, CRm IGW, app, reporting app, monitoring app, launchPad, SSO Integrated Launch, LIC, 
         applicationOperations				= new DefaultMutableTreeNode("Application Operations");// CustomerService, AccountService,UserService, ContactService
         applicationInterfaces				= new DefaultMutableTreeNode("Application Interfaces");// OUT - getAccountInfo, updateAccount, IN- EIF: getAccountInfoProxy
         applicationEntities				= new DefaultMutableTreeNode("Application Entities");//Contact, Customer, FAccount, User, Roles
         applicationDatabases				= new DefaultMutableTreeNode("Application Databases");
         applicationHardware				= new DefaultMutableTreeNode("Application Hardwares");
         applicationProperties				= new DefaultMutableTreeNode("Application Properties");
         applicationBusinessFunctions   	= new DefaultMutableTreeNode("Application Business Functions");// Customer Relationship Management
         applicationActivity				= new DefaultMutableTreeNode("Application Activity Flow UML");
         applicationSequenceFlow			= new DefaultMutableTreeNode("Application Sequence Diagram Flow UML");
         applicationInfrastructure			= new DefaultMutableTreeNode("Application Infrastruture"); //Call center applications, Desktop, Citrix-ICM, Hardware, Maestro INFRaA (Maestro CRM DS,Maestro CRM DS, Maestro CRm Profiles, Maestro CRM ESB, Maestro CRM RCIS )
         applicationEnvironment				= new DefaultMutableTreeNode("Application Environments");  //PROD, QA, DEV, PrePROD, PET, DR, DEV-INT
        
         //technologyModelRootNode 	                = new DefaultMutableTreeNode("Technology Model");
         hardwares					= new DefaultMutableTreeNode("Hardware :  Hardware <> Application <> Owner :: Physical View");
         software					= new DefaultMutableTreeNode("System Model :: Software ");
         network					= new DefaultMutableTreeNode("System Model :: Network ");
         
         databases					= new DefaultMutableTreeNode("System Model :: Databases ");
         
         //databasesModelRootNode		        = new DefaultMutableTreeNode("Database Model ");
         databasesEntities			        			= new DefaultMutableTreeNode("Databases Entities");
         flowsModelRootNode			        			= new DefaultMutableTreeNode("Logical Model :: Enterprise Model Controller ");
         businessFlowsRootNode		                	= new DefaultMutableTreeNode("Business flows ");
         sequenceDiagramFlowsRootNode                   = new DefaultMutableTreeNode("Sequence Flows :: Metaprocess CONTROLLER: Application <> Component <> Interface - REFERENCE:: Logical View ");
         projectsModelRootNode		                	= new DefaultMutableTreeNode("Projects Space ");
         projectsSpaceRootNode		                	= new DefaultMutableTreeNode("Projects Space... ");
         projects										= new DefaultMutableTreeNode("Projects ");
         releases										= new DefaultMutableTreeNode("Releases ");
        processes                                       = new DefaultMutableTreeNode("Logical Model :: Processes ");
        groups                                          = new DefaultMutableTreeNode("Groups ");
        persons                                         = new DefaultMutableTreeNode("Persons :: Person <> Application <> Hardware");
         
         people						= new DefaultMutableTreeNode("People Space");
         //environmentsModelRootNode	                = new DefaultMutableTreeNode("Environments Model");
         environments				        = new DefaultMutableTreeNode("Environments :: Environment <> Application <> Hardware :: Physical View ");
         
         components					= new DefaultMutableTreeNode("Components ");
         operations					= new DefaultMutableTreeNode("Logical Model :: Operations ");
         sequences					= new DefaultMutableTreeNode("Logical Model :: Sequence Flows ");
         
         processesNodeTemp		= new DefaultMutableTreeNode("components ");
         groupsNodeTemp			= new DefaultMutableTreeNode("components ");
         personsNodeTemp		= new DefaultMutableTreeNode("components ");
         ownersNodeTemp			= new DefaultMutableTreeNode("Owners");
         
          
         //DOMAINS
         for( int xx = 0; xx <  modelOfEDomain.size() ; xx++){
        	 
        	 tempEDomain = new EDomain();
        	 tempEDomain = (EDomain) modelOfEDomain.elementAt(xx);
         	 
        	 tempEDomainNode = new DefaultMutableTreeNode( tempEDomain.getDomainId() +":"+ tempEDomain.getDomainName()+"-"+tempEDomain.getSegment()+ " >>> ["+tempEDomain.businessUnit+":"+":BUSINESS SERVICE ="+tempEDomain.businessService+"]");
        	 
        	 //log4j.debug("getEOrganismTree::| modelOfEDomain " + tempEDomain.getName()); 
        	 //log4j.debug("getEOrganismTree::| modelOfEDomain size " + modelOfEDomain.size()); 
        	 
        	 tempESubdomainArrayList = tempEDomain.getSubdomainsArrayList();
        	 
		        	 for( int p = 0;p <  tempESubdomainArrayList.size() ; p++){
		        		 
		        		 tempESubdomain 			= new ESubdomain();
		        		 tempESubdomain 	= (ESubdomain) tempESubdomainArrayList.get(p);
		        		 
		        		 log4j.debug("getEOrganismTree::| tempESubdomain PRINT state " + tempESubdomain.getInfo()); 
		              		 
		        	 }// for all subdomain
        	 
		        	 for( int tt = 0;tt <  tempESubdomainArrayList.size() ; tt++){
		        		 
		        		 tempESubdomain 			= new ESubdomain();
		        		 tempESubdomain 	= (ESubdomain) tempESubdomainArrayList.get(tt);
		        		 
		        		 //log4j.debug("getEOrganismTree::| tempESubdomain " + tempESubdomain.getSubdomainName() + " : subdomainID="+ tempESubdomain.getSubdomainId()); 
		        			 	
		        		 tempEDomainNode.add(new DefaultMutableTreeNode(tempESubdomain.getSubdomainId()+":"+tempESubdomain.getSubdomainName() ));
				        
		             		 
		        	 }// for all subdomains
        	 
		  	 domainsModel.add(tempEDomainNode);
            
                 
         }// for domains
         
         
         //SYSTEMS
         for( int x = 0; x <  modelOfESystem.size() ; x++){
        	 
        	 tempESystem = new ESystem();
        	 tempESystem = (ESystem) modelOfESystem.elementAt(x);
        	 
        	 tempESystemApplicationArrayList = tempESystem.getApplicationsArrayList();
        	 
        	 tempESystemNode = new DefaultMutableTreeNode( tempESystem.getName() +":"+tempESystem.getSysID()+"-"+tempESystem.getSegment()+ " >>> ["+tempESystem.businessUnit+":"+":BUSINESS SERVICE ="+tempESystem.businessService+"]");
        	 
        	 
        	 //log4j.debug("getEOrganismTree::| tempESystem " + tempESystem.getName() + " : tempESystemApplicationArrayList="+ tempESystemApplicationArrayList.size()); 
        	 //log4j.debug("getEOrganismTree::| modelOfESystem size " + modelOfESystem.size()); 
         	
        	 tempESystemApplicationNode = new DefaultMutableTreeNode(tempESystem.getName() + " Applications Components");
        	 for( int u = 0; u <  tempESystemApplicationArrayList.size() ; u++){
        		 
        		 tempEApplicationSystem = new EApplication();
        		 tempEApplicationSystem = (EApplication) tempESystemApplicationArrayList.get(u);
        		 
        		 //log4j.debug("getEOrganismTree::| tempESystem " + tempESystem.getName() + " : tempEApplicationSystem="+ tempEApplicationSystem.getApplicationName()); 
        		 
        		 if(null!=tempEApplicationSystem.getApplicationName() | null!=tempEApplicationSystem.getName()){
        			 
        			 	 //log4j.debug("getEOrganismTree::| tempESystem " + tempESystem.getName() + " : tempEApplicationSystem="+ tempEApplicationSystem.getApplicationName() + "- START"); 
        			    
        			 	 //tempESystemApplicationNode = new DefaultMutableTreeNode();
		        		 tempESystemApplicationNode.add(createEApplicationTreeNode(tempEApplicationSystem));
		        		 
		        		 //log4j.debug("getEOrganismTree::| tempESystem " + tempESystem.getName() + " : tempEApplicationSystem="+ tempEApplicationSystem.getApplicationName() + " - END"); 
        		 }
        		 
        		 //log4j.debug("getEOrganismTree::| tempESystem " + tempESystem.getName() + " : tempEApplicationSystem="+ tempEApplicationSystem.getApplicationName() + " : added"); 
        		 
        	 }
        	 
        	 tempESystemNode.add(tempESystemApplicationNode);
        			 
        	 systemsModel.add(tempESystemNode);
            
                 
         }// for systems
          
         for( int i = 0; i <  modelOfEApplication.size() ; i++){
        	 
        	 tempEApplication = new EApplication();
        	 
        	 tempEApplication = (EApplication) modelOfEApplication.elementAt(i);
             
                 /*
        	 	if ((tempEApplication.metadataType).equals("INVENTORY")){
                         applicationsInventory.add(createEApplicationTreeNode(tempEApplication));
                 }else if((tempEApplication.metadataType).equals("MODEL")){
                         applications.add(createEApplicationTreeNode(tempEApplication));
                 }else if((tempEApplication.metadataType).equals("PHYSICAL")){
                	     applicationsHardwarePhysical.add(createEApplicationTreeNode(tempEApplication));
                	 
                 }
                 */
        	 	
        	 
                 if ((tempEApplication.metadataType).equals(ConfigFrame.APPLICATION_TYPE_INVENTORY )){
                     applicationsInventory.add(createEApplicationTreeNode(tempEApplication));
                 }else if((tempEApplication.metadataType).equals(ConfigFrame.APPLICATION_TYPE_MODEL)){
                     applications.add(createEApplicationTreeNode(tempEApplication));
                 } else if((tempEApplication.metadataType).equals(ConfigFrame.APPLICATION_TYPE_PHYSICAL)){
            	     applicationsHardwarePhysical.add(createEApplicationTreeNode(tempEApplication));
                 }else if((tempEApplication.metadataType).equals("MODEL") | (tempEApplication.getType()).equals(ConfigFrame.APPLICATION_TYPE_APPLICATION)){
            	     applicationsModel.add(createEApplicationTreeNode(tempEApplication));
                 }// end else if
        	 
                 //applicationsMaster.add(createEApplicationTreeNode(tempEApplication));
        	    //log4j.debug("...............createEApplicationTreeNode(tempEApplication): "+i+".......................");
         } 
         
         
		for( int t = 0; t <  modelMetadataDatabases.size() ; t++){
			        	 
			        	 tempJDatabase = new JDatabase();
			        	 
			        	 tempJDatabase = (JDatabase) modelMetadataDatabases.elementAt(t);
			        	 
			        	 metadataDatabaseNodeTemp = new DefaultMutableTreeNode("Database - "  + tempJDatabase.getDatabaseName() + " :: Application - "+ tempJDatabase.getApplication()  + " : Component - "+ tempJDatabase.getComponent() );
			        	 
			        	 if(tempJDatabase.getMetadataTablesArrayList().size()>0){
	    	            	 
			        		 tablesNodeTemp = new DefaultMutableTreeNode("Tables -");
    	                     
		    	            	     for(int z = 0; z < (tempJDatabase.getMetadataTablesArrayList().size()); z++){
		
		    	                    	 	 tempTable = new ETable();
		
		    	                    	 	 tempTable = (ETable) (tempJDatabase.getMetadataTablesArrayList()).get(z);
		    	                    	 	
		    	                    	 	 metadataTableNodeTemp = new DefaultMutableTreeNode(tempTable.getName());
		    	     			        	 
		    	                    	 	 // add columns to existing tables
			    	                    	 for(int x = 0; x < (tempTable.getMetadataColumnsArrayList().size()); x++){
			
					       	                    	 	 tempColumn = new EColumn();
					
					       	                    	 	 tempColumn = (EColumn) (tempTable.getMetadataColumnsArrayList()).get(x);
					       	               
					       	                    	 	 metadataTableNodeTemp.add(new DefaultMutableTreeNode("Column -" + tempColumn.getName() +":: type -" + tempColumn.getDataType()));
					       	                    	 	 
					       	                    	     //log4j.debug(".....ADD COLUMNS TO TABLE...."+ tempTable.getName() +"::"+ tempColumn.getName());
			
			    	                    	 }// for all columns
			    	                    	 
			    	                    	 
			    	                    	 //log4j.debug("modelMetadataDatabases -"+tempJDatabase.getDatabaseName() +"  -  " + tempTable.getName());
		    	                    	 	 tablesNodeTemp.add(metadataTableNodeTemp);
		    	                    	 	 
		    	                     }// for
		    	            
		    	            
		    	             //log4j.debug("modelMetadataDatabases -"+ metadataDatabaseNodeTemp.add(tablesNodeTemp) +".......................");
    	            	     metadataDatabaseNodeTemp.add(tablesNodeTemp);
    	                 }// if we have components
			        	 
			        	 databasesMetamodel.add(metadataDatabaseNodeTemp);
			        	 //log4j.debug("..............databasesMetamodel.add(metadataDatabaseNodeTemp);: "+t+".......................");
		
		} //modelMetadataDatabases
		
		
		
           //modelOfEEnvironment
          for( int w = 0; w <  modelOfEEnvironment.size() ; w++){
        	 
        	 tempEnvironment = new EEnvironment();
         	 tempEnvironment = (EEnvironment) modelOfEEnvironment.elementAt(w);
        	 
         	 envApplicationNodeTemp = new DefaultMutableTreeNode("ENV:: "  + tempEnvironment.getName() + ":" +  tempEnvironment.getReleaseName()+ ":" +  tempEnvironment.getStartDate());
       	 
        	 for( int u = 0; u <  (tempEnvironment.getEApplicationVector()).size() ; u++){
        		 tempEnvironmentApplication = new EApplication();
        		 tempEnvironmentApplication = (EApplication)(tempEnvironment.getEApplicationVector()).elementAt(u);
        		 envApplicationNodeTemp.add(new DefaultMutableTreeNode("ENV APP:: " + tempEnvironmentApplication.getApplicationName() + tempEnvironmentApplication.getSegment()));
                 
                        envParameterNodeTemp = new DefaultMutableTreeNode("ENV PARAMS");
                         // for all the parameters
                     
                         if (tempEnvironmentApplication.getEParameterVector()!=null){
                             
                                 for( int z = 0; z <  (tempEnvironmentApplication.getEParameterVector()).size() ; z++){
                                     tempEnvironmentParameter = new EParameter();
                                     tempEnvironmentParameter =  (EParameter)(tempEnvironmentApplication.getEParameterVector()).elementAt(z);
                                     
                                     envParameterNodeTemp.add(new DefaultMutableTreeNode(tempEnvironmentParameter.getType() +"::"+ tempEnvironmentParameter.getName() +"::"+ tempEnvironmentParameter.getValue()));
                                     envApplicationNodeTemp.add(new DefaultMutableTreeNode(tempEnvironmentParameter.getType() +"::"+ tempEnvironmentParameter.getName() +"::"+ tempEnvironmentParameter.getValue()));
                                     
                                     
                                     //log4j.debug("..............tempEnvironmentParameter): "+z+".......................");
                                            
                                 }// zz all parameters
                                 
                                 //log4j.debug("...............createEEnvironmentTreeNode(tempEnvironmentApplication): "+u+".......................");
                         }//not null
                
                         envApplicationNodeTemp.add(envParameterNodeTemp);
                 }// for all env apps
        	
        	 if (DISPLAY_HARDWARE ==true){
        		 
	        	//set hardware
	             for( int t = 0;t <  modelOfEHardwares.size() ; t++){
	            	 tempEHardware = new EHardware();
	            	 
	            	 tempEHardware = (EHardware) modelOfEHardwares.elementAt(t);
	            	 if (tempEnvironment.getName()!=null && tempEHardware.getEnvironment()!=null){		
			             	 if(tempEHardware.getEnvironment().equals(tempEnvironment.getName())){
			             		envApplicationNodeTemp.add(new DefaultMutableTreeNode("APP HW:: " + tempEHardware.getApplication() + ":" + tempEHardware.getHardwareName()));
			             				             		
			             	 }//
	            	 }// not null
	             }
        	 }
        	 environments.add(envApplicationNodeTemp);
         	
        	 log4j.debug("...............createEEnvironmentTreeNode(tempEEnvironment): "+tempEnvironment.getName()+".......................");
             
         } //environments
         
          //modelOfOwners
          for( int zz = 0; zz <  modelOfEOwners.size() ; zz++){
        	  tempPerson = new EPerson();
        	  tempPerson = (EPerson) modelOfEOwners.elementAt(zz);
        	  
        	  ownersNodeTemp= new DefaultMutableTreeNode("Owner:: " + tempPerson.getName());
        	  
        	  		// for all hardwares
		        	  for( int xx = 0;xx <  modelOfEHardwares.size() ; xx++){
		             	 tempEHardware = new EHardware();
		             	 
		             	 tempEHardware = (EHardware) modelOfEHardwares.elementAt(xx);
		             	 if (tempPerson.getName()!=null && tempEHardware.getOwner()!=null){		
		 		             	 if(tempPerson.getName().equals(tempEHardware.getOwner())){
		 		             		ownersNodeTemp.add(new DefaultMutableTreeNode("APP OWNER:: " + tempEHardware.getApplication() + ":" + tempEHardware.getHardwareName()));
		 		             				             		
		 		             	 }//
		             	 }// not null
		              }
		        	  
		       persons.add(ownersNodeTemp);
          } 
          
        
          
          
         for( int w = 0; w <  modelOfERelease.size() ; w++){
        	 
        	 tempRelease = new ERelease();
        	 
        	 tempRelease = (ERelease) modelOfERelease.elementAt(w);
        	 projectsNodeTemp = new DefaultMutableTreeNode("Release: "  + tempRelease.getName() + ":" +  tempRelease.getEnvironment()+ ":" +  tempRelease.getStartDate());
        	 
        	 for( int z = 0; z <  (tempRelease.getEProjectVector()).size() ; z++){
        		 tempProject = new EProject();
        		 tempProject = (EProject)(tempRelease.getEProjectVector()).elementAt(z);
        		 projectsNodeTemp.add(new DefaultMutableTreeNode("Projects: " + tempProject.getName() + tempProject.getDescription()));
        		 projectsSpaceRootNode.add(new DefaultMutableTreeNode("Projects: " + tempProject.getName() + tempProject.getDescription()));
        		 
        		 //log4j.debug("...............createEEnvironmentTreeNode(tempProject): "+z+".......................");
              
        	 }//z
        	 releases.add(projectsNodeTemp);
        	
        	 //log4j.debug("...............createEEnvironmentTreeNode(tempRelease): "+w+".......................");
         } 
         
        for( int p = 0; p <  modelOfEProcess.size() ; p++){
                tempProcess = new EProcess();
                tempProcess = (EProcess) modelOfEProcess.elementAt(p);
                processesNodeTemp = new DefaultMutableTreeNode("Process: "  + tempProcess.getName() + ":" +  tempProcess.getFlow()+ ":" +  tempProcess.getOwnerApplication());
                processes.add(processesNodeTemp);
            
                //log4j.debug("...............create processesNodeTemp(tempProject): "+p+".......................");
               
        } // pr print processes
        
        for( int g = 0; g <  modelOfEGroup.size() ; g++){
                tempGroup = new EGroup();
                tempGroup = (EGroup) modelOfEGroup.elementAt(g);
                groupsNodeTemp = new DefaultMutableTreeNode("Group: "  + tempGroup.getName() + ":" +  tempGroup.getOwner()+ ":" +  tempGroup.getApplication());
                groups.add(groupsNodeTemp);
               
        } // g print groups
         
        for( int u = 0; u <  modelOfEPerson.size() ; u++){
                tempPerson = new EPerson();
                tempPerson = (EPerson) modelOfEPerson.elementAt(u);
                personsNodeTemp = new DefaultMutableTreeNode("Person: "  + tempPerson.getName() + ":" +  tempPerson.getRole()+ ":" +  tempPerson.getType());
                persons.add(personsNodeTemp);
               
        } // u print persons
        
         for( int j = 0; j <  modelOfJDatabases.size() ; j++){
        	 
        	 tempJDatabase = new JDatabase();
        	 
        	 tempJDatabase = (JDatabase) modelOfJDatabases.elementAt(j);
        	 databases.add(new DefaultMutableTreeNode("DB: "  + tempJDatabase.getDatabaseName() + ":" +  tempJDatabase.getDatabaseURL()+ ":" +  tempJDatabase.getServer()+ ":" +  tempJDatabase.getInfra()));
        	 //log4j.debug("...............modelOfJDatabases(tempJDatabase): "+j+".......................");
         } 
         
         log4j.debug("###########getEOrganismTree - modelOfEHardwares.size= "+modelOfEHardwares.size()+"#################");
         
         
         for( int k = 0; k <  modelOfEHardwares.size() ; k++){
        	 
        	 tempEHardware = new EHardware();
        	 
        	 tempEHardware = (EHardware) modelOfEHardwares.elementAt(k);
        	 hardwares.add(new DefaultMutableTreeNode("HW: "  + tempEHardware.getHardwareName() + ":: APP: " +  tempEHardware.getApplication() +"::IP: " +tempEHardware.getIp() +"::ENV: " + tempEHardware.getEnvironment() + "::OWNER: " + tempEHardware.getOwner()));
        	 log4j.debug("...............modelOfEHardwares(tempEHardware): "+k+".......................");
         } 
         
         for( int l = 0; l <  modelOfEComponents.size() ; l++){
        	 
        	 tempComponent = new EComponent();
        	 
        	 tempComponent = (EComponent) modelOfEComponents.elementAt(l);
        	 components.add(new DefaultMutableTreeNode( tempComponent.getComponentName() + " "+tempComponent.getComponentDescription()+ " "+tempComponent.getSoaID()));
        	 
        	 //log4j.debug("...............modelOfEComponents(tempComponent): "+l+".......................");
         } 
         
         for( int m = 0; m <  modelOfESequenceFlow.size() ; m++){
        	 
        	 tempSequenceFlow = new ESequenceFlow();
        	 
        	 tempSequenceFlow = (ESequenceFlow) modelOfESequenceFlow.elementAt(m);
        	 sequenceDiagramFlowsRootNode.add(new DefaultMutableTreeNode( tempSequenceFlow.getName() + "."+tempSequenceFlow.getText()+ "."));
        	 //log4j.debug("...............modelOfJSequenceFlow(tempSequenceFlow): "+m+".......................");
         } 
         
         //sequenceDiagramFlowsRootNode.add(sequences);
         
         for( int n = 0; n <  modelOfEOperations.size() ; n++){
        	 
        	 tempOperation = new EOperation();
        	 
        	 tempOperation = (EOperation) modelOfEOperations.elementAt(n);
        	 operations.add(new DefaultMutableTreeNode( tempOperation.getComponent() + "."+tempOperation.getService()+ "." +tempOperation.getMethod()));
        	 //log4j.debug("...............modelOfJOperations(tempOperation): "+n+".......................");
         } 
                 
          eorganismTreeCellRenderer = new DefaultTreeCellRenderer();
          //beanTreeCellRenderer.setOpenIcon((Icon)iconBlue);
          //eorganismTreeCellRenderer.setLeafIcon((Icon)iconYellow);
         

          tableTreeCellRenderer = new DefaultTreeCellRenderer();
          //tableTreeCellRenderer.setOpenIcon((Icon)iconBlue);
          //tableTreeCellRenderer.setLeafIcon((Icon)iconYellow);

         // add subnodes to new children of the root node
              				
         flowsModelRootNode.add(businessFlowsRootNode);
         flowsModelRootNode.add(sequenceDiagramFlowsRootNode);
         
         projectsModelRootNode.add(projects);
         projectsModelRootNode.add(projectsSpaceRootNode);					
         //environmentsModelRootNode.add(environments);
        
         //enterpriseRootNode.add(systemsModelRootNode);
         //enterpriseRootNode.add(technologyModelRootNode);
         //enterpriseRootNode.add(databasesModelRootNode);
         flowsModelRootNode.add(processes);;
         flowsModelRootNode.add(components);
         flowsModelRootNode.add(operations);
         
         // COBRA Domains
         enterpriseRootNode.add(domainsModel);
         
         // system model - for all applications, hardwares, databases in models
         enterpriseRootNode.add(systemsModel);
         enterpriseRootNode.add(applicationsHardwarePhysical);
         enterpriseRootNode.add(applicationsInventory);
         enterpriseRootNode.add(applications);
         enterpriseRootNode.add(applicationsMaster);
         enterpriseRootNode.add(applicationsModel);
         
         enterpriseRootNode.add(databasesMetamodel);
         enterpriseRootNode.add(sequenceDiagramFlowsRootNode);
         // add system model : environments
         enterpriseRootNode.add(environments);
         // add hardwares
         enterpriseRootNode.add(hardwares);
         // persons
         enterpriseRootNode.add(persons);    
         
         // add logical model
         // enterpriseRootNode.add(flowsModelRootNode); 
         // enterpriseRootNode.add(applicationDomains);  
         // enterpriseRootNode.add(applicationType);
         // enterpriseRootNode.add(applicationIntegration);
         // enterpriseRootNode.add(applicationInfrastructureType);
         // enterpriseRootNode.add(applicationLayers);
         // enterpriseRootNode.add(applicationSegment); 
         // enterpriseRootNode.add(applicationCritical);
        
        
        //enterpriseRootNode.add(network);
        //enterpriseRootNode.add(databases);
        //enterpriseRootNode.add(software);
        
        //add business model
        //enterpriseRootNode.add(business);
         //enterpriseRootNode.add(projectsModelRootNode);
         //enterpriseRootNode.add(people);
        //enterpriseRootNode.add(releases);
         
         //enterpriseRootNode.add(groups);
         //enterpriseRootNode.add(actors);
         
         enterpriseTreeModel   = new DefaultTreeModel(enterpriseRootNode);
         enterpriseTreeTemp    = new JTree(enterpriseTreeModel);

         enterpriseTreeTemp.setCellRenderer(enterpriseTreeCellRenderer);

         return enterpriseTreeTemp;

    }// end getEnterpriseTree
     
     
     public void setDescriptorsLeftTabs(){
         leftTabbedPane              = new JTabbedPane(JTabbedPane.BOTTOM);
         databaseSchemaEditor        = new JEditorPane();
         //attributesEditor          = new JEditorPane();

         // important
         attributeScrollPane         = new JScrollPane();
         tablesScrollPane            = new JScrollPane();
         enterpriseScrollPane        = new JScrollPane(enterpriseTree);
        
         columnsEditor               = new JEditorPane();
         databaseSchemaEditorScroll  = new JScrollPane(databaseSchemaEditor);
         leftTabbedPane.setFont(fontClasses);
         //leftTabbedPane.setBackground(Color.white);
         leftTabbedPane.setForeground(Color.blue);

         //selectedBeanTree.setFont(fontClasses);
         //selectedTableTree.setFont(fontClasses);

         verticalLeftListSplitPanel  =  new JSplitPane(
                                         JSplitPane.VERTICAL_SPLIT,
                                         true,
                                         new JScrollPane(  ),
                                         attributeScrollPane);

         verticalLeftListSplitPanel.setDividerSize(7);
         verticalLeftListSplitPanel.setDividerLocation(300);
         verticalLeftListSplitPanel.setOneTouchExpandable( true );

         verticalLeftTableSplitPanel =   new JSplitPane(
                                         JSplitPane.VERTICAL_SPLIT,
                                         true,
                                         new JScrollPane(  ),
                                         tablesScrollPane );

         verticalLeftTableSplitPanel.setDividerSize(7);
         verticalLeftTableSplitPanel.setDividerLocation(300);
         verticalLeftTableSplitPanel.setOneTouchExpandable( true );
          leftTabbedPane.addTab( "Enterprise Metamodel :: EOrganism" ,
                                    null,
                                    (Component) enterpriseScrollPane,
                                    "Presentation/View Layer ::  Model Layer ::   Controller:: State/Model Transfer :: Deployment View");
          
          leftTabbedPane.addTab( "eOrganism :: Applications:: Explorer",
        		  					null,
                                      (Component) verticalLeftListSplitPanel,
                                      "eOrganism :: Applications:: Explorer" );
          
          leftTabbedPane.addTab(  "eOrganism :: Hardware:: Explorer",
        		  					null,
                                    (Component) verticalLeftSplitPanel,
                                    "eOrganism :: Hardware:: Explorer");
          
          leftTabbedPane.addTab(  "eOrganism :: Databases:: Architect" ,
        		   					null,
                                    (Component) verticalLeftTableSplitPanel,
                                    "eOrganism :: Databases:: Architect" );
           
          leftTabbedPane.addTab( "eOrganism :: Network:: Topology" ,
            						null,
                                    (Component) verticalLeftTableSplitPanel,
                                    "eOrganism :: Network:: Topology");
          
          leftTabbedPane.addTab( "eOrganism :: People:: Explorer" ,
									null,
				                  (Component) verticalLeftTableSplitPanel,
				                  "eOrganism :: People:: Explorer");
          
          leftTabbedPane.addTab( "eOrganism :: Environment:: Explorer" ,
										null,
					                (Component) verticalLeftTableSplitPanel,
					                "eOrganism :: Environment:: Explorer");
          leftTabbedPane.addTab( "eOrganism :: Sequence Diagrams Flows:: Explorer" ,
										null,
					                (Component) verticalLeftTableSplitPanel,
					                "eOrganism :: Sequence Diagrams Flows:: Explorer");
          
          leftTabbedPane.addTab( "eOrganism :: Business Flows:: Explorer" ,
										null,
					              (Component) verticalLeftTableSplitPanel,
					              "eOrganism :: Business Flows:: Explorer");
          
          leftTabbedPane.addTab( "eOrganism :: Projects:: Explorer" ,
										null,
					            (Component) verticalLeftTableSplitPanel,
					            "eOrganism :: Projects:: Explorer");
          
          leftTabbedPane.addTab( "eOrganism :: Release:: Explorer" ,
											null,
						          (Component) verticalLeftTableSplitPanel,
						          "eOrganism :: Release:: Explorer");
          
            
          leftTabbedPane.addTab( "eOrganism :: Applications Properties:: Explorer" ,
            		 				null,
                                    (Component) verticalLeftTableSplitPanel,
                                    "eOrganism :: Applications Properties:: Explorer");
          
  

}//end setDescriptorsLeftTabs
     
 
     public void createEOrganismTreeVectors(){

         EOrganism                tempEorganism;
         DefaultMutableTreeNode   tempEorganismTreeNode;
         DefaultTreeModel         tempEorganismTreeModel;
        
         JTree                    tempEorganismTree;
         
         treeEorganisms   = new Vector();
         for(int i =0; i < 10; i++){
              eorganismTreeCellRenderer = new DefaultTreeCellRenderer();
              //eorganismTreeCellRenderer.setOpenIcon((Icon)iconBlue);
              //eorganismTreeCellRenderer.setLeafIcon((Icon)iconYellow);
              tempEorganism = new EOrganism();
              tempEorganismTreeNode   = createEOrganismTreeNode(tempEorganism);
              tempEorganismTreeModel   = new DefaultTreeModel(tempEorganismTreeNode);
             //tempEorganismNodeTree.setFont(fontClasses);
              
              //tempEorganismNodeTree.setCellRenderer(eorganismTreeCellRenderer);
             
              // create the vector
              treeEorganisms.add(i, tempEorganismTreeNode);
 
         }// end for all beans

     }// end createEOrganismTreeVectors
     
     public DefaultMutableTreeNode createEOrganismTreeNode(EOrganism tempEOrganismArg){
    	   tempEOrganismArg.setName(""+System.nanoTime());
    	   eorganismRootNode    = new DefaultMutableTreeNode(tempEOrganismArg);
           return eorganismRootNode;

     }// end createEOrganismTreeNode
 
     public void createEOrganismTabbedPane(){

               eorganismTabbedPane = new JTabbedPane(JTabbedPane.NORTH);

               // bery bery important
               //eorganismTabbedPane.addChangeListener(new TabbedPaneListener());

               String tempString = "";

               String tempStringDescription = "";

               EOrganism  tempEorganism;
               EOrganism  tempEorganismCounter   = new EOrganism();
               JModelPane tempEorganismPanelTemp;

               String eorganismName         = "";
               String eorganismNameCounter  = "";
               String eorganismType         = "";
               String eorganisnCounter  = "";
                              
               Icon tempIcon = new ImageIcon();
               
               // create the interface
               for( int indexOfPanes = 0; indexOfPanes < 5; indexOfPanes ++){
                      
                   // manipulators
            	      tempEorganism = new EOrganism();
            	      

            	      eorganismName = tempEorganism.getName();
            	      eorganismType = eorganismName + "type";
 
                      tempStringDescription = "Type :" + eorganismType +" @ Table : "+ eorganismName +" @ index :"+ indexOfPanes  ;

                      // create the panel element
                      tempEorganismPanelTemp = new JModelPane();// null was here

                      // create all the componets of the gui array elements
                      tempEorganismPanelTemp.createGUIComponent(indexOfPanes,
                    		  							eorganismName ,
                    		  							eorganismType,
                    		  							eorganismType);


                      //tempEorganismPanelTemp.beansTypeCodeTabbedPane.addChangeListener(new TabbedPaneTypeListener());

/*
                      beansTabbedPane.insertTab(beanName,
                                                (Icon)imageIcon,
                                                beanPanelTemp.beansTypeCodeTabbedPane,
                                                tempStringDescription,
                                                indexOfPanes);
*/                           
                      eorganismTabbedPane.addTab(eorganismName,
	                                            tempIcon,
	                                            (Component )tempEorganismPanelTemp,
	                                            eorganismName );
                  
                      eorganismPanelVector.add(indexOfPanes, eorganismTabbedPane);


          }// for all the beans

               eorganismTabbedPane.setSelectedIndex(0);

               eorganismTabbedPane.setFont(fontClasses);
          //beansTabbedPane.setBackground(Color.white);
               eorganismTabbedPane.setForeground(Color.green);

     }// end createEOrganismTabbedPane
     
     
     public DefaultMutableTreeNode createEApplicationTreeNode(EApplication tempApplicationArg){
            
         //DefaultMutableTreeNode tempApplicationRootNode         = new DefaultMutableTreeNode(tempApplicationArg.getApplicationName());
         DefaultMutableTreeNode tempApplicationRootNode           = new DefaultMutableTreeNode(tempApplicationArg.getApplicationName()+"::" + tempApplicationArg.metadataType + "::" + tempApplicationArg.clasificationCriticality+":: APP ID="+tempApplicationArg.appID+"::"+tempApplicationArg.eSis + ": owner"+tempApplicationArg.owner + "-"+tempApplicationArg.developmentDirector);
    	    		 
    	 			 DefaultMutableTreeNode tempComponentsRootNode;
    	 			 DefaultMutableTreeNode tempDatabasesRootNode;
    	 			 DefaultMutableTreeNode tempHardwareRootNode;
    	 			 DefaultMutableTreeNode tempSequenceFlowRootNode;
    	 			 DefaultMutableTreeNode tempOperationRootNode;
                     DefaultMutableTreeNode tempInterfaceRootNode;
                     DefaultMutableTreeNode tempAttributeRootNode;
                     
                     DefaultMutableTreeNode metadataDatabaseNodeTemp;
                     DefaultMutableTreeNode tablesNodeTemp;
                     DefaultMutableTreeNode metadataTableNodeTemp;
                     //
                     DefaultMutableTreeNode tempComponentsNode;
                     DefaultMutableTreeNode tempOperationsNode;
                     DefaultMutableTreeNode tempAttributesRootNode;
                     DefaultMutableTreeNode tempAttributesNode;
                     
    	             // add subnodes to new children of the root node
    	              EComponent 	tempComponent 		= new EComponent();
    	              JDatabase 	tempDatabase 		= new JDatabase();
    	              EHardware 	tempHardware	 	= new EHardware();
    	              ESequenceFlow tempSequenceFlow 	= new ESequenceFlow();
    	              EOperation 	tempOperation 		= new EOperation();
                      EInterface      tempInterface     = new EInterface();
                      EAttribute      tempAttribute     = new EAttribute();
                      
                      EColumn 	tempColumn 		= new EColumn();
                      ETable 	tempTable 		= new ETable();
                    
                    tempApplicationRootNode.add(new DefaultMutableTreeNode("APP INTEGRATION INTERFACES >>>   IN :"+ tempApplicationArg.getIn() + " @CALLED BY")); 
                    tempApplicationRootNode.add(new DefaultMutableTreeNode("APP INTEGRATION INTERFACES OUT  >>> :"+ tempApplicationArg.getOut()+ " @IS CALLING")); 
                    
                    //tempApplicationRootNode.add(new DefaultMutableTreeNode("DETAILS ||:: Critical Level="+tempApplicationArg.getCritical()+":: LAYER="+tempApplicationArg.getLayer()+":: DOMAIN="+tempApplicationArg.getDomain()+":: INFRA="+tempApplicationArg.getInfrastructure()+ ":: TYPE="+tempApplicationArg.getType()+ "::INTGR="+tempApplicationArg.getIntegrationType())); 
    	    
    	             // if has components
    	             if(tempApplicationArg.getComponentsArrayList().size()>0){
    	            	 
    	            	     tempComponentsRootNode   = new DefaultMutableTreeNode("Components");
    	                     for(int i = 0; i < (tempApplicationArg.getComponentsArrayList()).size(); i++){

    	                    	 	 tempComponent = new EComponent();

    	                    	 	 tempComponent = (EComponent) tempApplicationArg.getComponentsArrayList().get(i);
    	                    	 	 
    	                    	 	 tempComponentsNode= new DefaultMutableTreeNode(tempComponent.getComponentName() + "::"+tempComponent.getComponentDescription()+ "::"+tempComponent.getSoaID());
    	                    	 	 
    	                    	 	 if((tempComponent.getArrayListService()).size()> 0){
    	                    	 		 
		    	                    	 	 for(int j = 0; j < (tempComponent.getArrayListService()).size(); j++){
		    	                    	 	 
		    	                    	 		tempOperation = (EOperation)tempComponent.getArrayListService().get(j);
		    	                    	 		
		    	                    	 		tempOperationsNode= new DefaultMutableTreeNode(tempOperation.getName() + "::"+tempOperation.getType() +"Service location -" + tempOperation.getLocation());
		    	                    	 		
		    	                    	 		//log4j.debug("...............createEApplicationTreeNode(tempOperationsNode) SERVICE : "+tempOperation.getName() + "::"+tempOperation.getType()+".......................");
		    	                    	 		
		    	                    	 		if(tempOperation.getArrayListInterface().size()>0){
		    	                    	 			
		    	                    	 			for(int k = 0; k < (tempOperation.getArrayListInterface()).size(); k++){
		    	                    	 				
		    	                    	 					
		    	                    	 				
		    	                    	 				tempInterface = (EInterface)tempOperation.getArrayListInterface().get(k);
		    	                    	 				
		    	                    	 				tempOperationsNode.add(new DefaultMutableTreeNode(tempInterface.getInterfaceName()));
		    	                    	 				
		    	                    	 				//log4j.debug("...............createEApplicationTreeNode(tempInterface) INTERFACE : "+tempInterface.getInterfaceName()+".......................");
			    		    	                    	 
		    	                    	 			
		    	                    	 			}//for
		    	                    	 			
		    	                    	 		}//if
		    	                    	 		
		    	                    	 		// add attributes
		    	                    	 		if(tempOperation.getArrayListAttribute().size()>0){
		    	                    	 			
		    	                    	 			tempAttributesRootNode = new DefaultMutableTreeNode("Attributes");
		    	                    	 			tempAttributesNode = new DefaultMutableTreeNode();
		    	                    	 			
		    	                    	 			for(int p = 0; p < (tempOperation.getArrayListAttribute()).size(); p++){
		    	                    	 				
		    	                    	 				tempAttribute = (EAttribute)tempOperation.getArrayListAttribute().get(p);
		    	                    	 				
		    	                    	 				tempAttributesRootNode.add(new DefaultMutableTreeNode(tempAttribute.getName()));
		    	                    	 			
		    	                    	 			}//for
		    	                    	 			
		    	                    	 			tempAttributesRootNode.add(tempAttributesNode);
		    	                    	 			tempOperationsNode.add(tempAttributesRootNode);
		    	                    	 			
		    	                    	 		}//if
		     	                    	 		
		    	                    	 		tempComponentsNode.add(tempOperationsNode);

		    	                    	 	 }
    	                    	 	 
    	                    	 	 }// if we have 

    	                    	 	 tempComponentsRootNode.add(tempComponentsNode);

    	                     }// for
    	                     
    	                     tempApplicationRootNode.add(tempComponentsRootNode);
    	             }// if we have components
    	              
    	            
    	             // if we have DB collections
    	             if(tempApplicationArg.getDatabasesArrayList().size()>0){
    	            	 
    	            	     tempDatabasesRootNode   = new DefaultMutableTreeNode("Databases");
    	                     for(int i = 0; i < (tempApplicationArg.getDatabasesArrayList()).size(); i++){

    	                    	 	 tempDatabase = new JDatabase();

    	                    	 	 tempDatabase = (JDatabase) (tempApplicationArg.getDatabasesArrayList()).get(i);

    	                    	 	 tempDatabasesRootNode.add(new DefaultMutableTreeNode(tempDatabase.getDatabaseName() + "::"+tempDatabase.getServer()+ "::"+tempDatabase.getDatabaseURL()+"::"+tempDatabase.getInfra()));
                          	 	 
    	                     }// for
    	                     
    	                     tempApplicationRootNode.add(tempDatabasesRootNode);
    	                      
    	             }// if 
    	             
    	             // add complete DB model
	             	 for( int t = 0; t <  modelMetadataDatabases.size() ; t++){
	             			        	 
	             						tempDatabase = new JDatabase();
	             			        	 
	             						tempDatabase = (JDatabase) modelMetadataDatabases.elementAt(t);
	             			        	
	             						if(tempApplicationArg.getApplicationName() != null && tempDatabase.getApplication()!=null ){
	             			        	 
		             			        	 if(tempDatabase.getApplication().equals(tempApplicationArg.getApplicationName())){
			 	             			        	 
		             			        		 				metadataDatabaseNodeTemp = new DefaultMutableTreeNode("Database - "  + tempDatabase.getDatabaseName() + " :: Application - "+ tempDatabase.getApplication()  + " : Component - "+ tempDatabase.getComponent() );
		     	             			        		 		tablesNodeTemp = new DefaultMutableTreeNode("Tables -");
			 	                 	                     
			 	             		    	            	     for(int z = 0; z < (tempDatabase.getMetadataTablesArrayList().size()); z++){
			 	             		
			 	             		    	                    	 	 tempTable = new ETable();
			 	             		
			 	             		    	                    	 	 tempTable = (ETable) (tempDatabase.getMetadataTablesArrayList()).get(z);
			 	             		    	                    	 	
			 	             		    	                    	 	 metadataTableNodeTemp = new DefaultMutableTreeNode(tempTable.getName());
			 	             		    	     			        	 
			 	             		    	                    	 	 // add columns to existing tables
			 	             			    	                    	 for(int x = 0; x < (tempTable.getMetadataColumnsArrayList().size()); x++){
			 	             			
			 	             					       	                    	 	 tempColumn = new EColumn();
			 	             					
			 	             					       	                    	 	 tempColumn = (EColumn) (tempTable.getMetadataColumnsArrayList()).get(x);
			 	             					       	               
			 	             					       	                    	 	 metadataTableNodeTemp.add(new DefaultMutableTreeNode("Column -" + tempColumn.getName() +":: type -" + tempColumn.getDataType()));
			 	             					       	                    	 	 
			 	             					       	                    	     //log4j.debug(".....ADD COLUMNS TO TABLE...."+ tempTable.getName() +"::"+ tempColumn.getName());
			 	             			
			 	             			    	                    	 }// for x all columns
			 	             			    	                    	 
			 	             			    	                    	 //log4j.debug("modelMetadataDatabases -"+tempJDatabase.getDatabaseName() +"  -  " + tempTable.getName());
			 	             		    	                    	 	 tablesNodeTemp.add(metadataTableNodeTemp);
			 	             		    	                    	 	 
			 	             		    	                     }// for z
			 	             		    	            
			 	             		    	            
			 	             		    	             //log4j.debug("modelMetadataDatabases -"+ metadataDatabaseNodeTemp.add(tablesNodeTemp) +".......................");
			 	                 	            	     metadataDatabaseNodeTemp.add(tablesNodeTemp);
			 	                 	            	     tempApplicationRootNode.add(metadataDatabaseNodeTemp);
		             			        	 }
	             			        	 
	             						}// if app name not null and db get app not null
	             			        	
	             			        	 //log4j.debug("..............databasesMetamodel.add(metadataDatabaseNodeTemp);: "+t+".......................");
	             		
	             		} //modelMetadataDatabases
    	              
    	             // if has hardware
    	             if(tempApplicationArg.getHardwaresArrayList().size()>0){
    	            	 
    	            	     tempHardwareRootNode   = new DefaultMutableTreeNode("Hardware");
    	                     for(int i = 0; i < (tempApplicationArg.getHardwaresArrayList()).size(); i++){

    	                    	 	tempHardware = new EHardware();

    	                    	 	tempHardware = (EHardware) tempApplicationArg.getHardwaresArrayList().get(i);

    	                    	 	tempHardwareRootNode.add(new DefaultMutableTreeNode(tempHardware.getHardwareName() + "::"+tempHardware.getIp()+ "::" + tempHardware.getInfra()));

    	                     }// for
    	                     
    	                     tempApplicationRootNode.add(tempHardwareRootNode);
    	             }// if we have hardware
    
    	             // if has sequence
    	             if(tempApplicationArg.getSequenceFlowArrayList().size()>0){
    	            	 
    	            	 	tempSequenceFlowRootNode   = new DefaultMutableTreeNode("Sequence Diagram Flow");
    	                     for(int i = 0; i < (tempApplicationArg.getSequenceFlowArrayList()).size(); i++){

    	                    	 	tempSequenceFlow = new ESequenceFlow();

    	                    	 	tempSequenceFlow = (ESequenceFlow) tempApplicationArg.getSequenceFlowArrayList().get(i);

    	                    	 	tempSequenceFlowRootNode.add(new DefaultMutableTreeNode(tempSequenceFlow.getName() + "::"+tempSequenceFlow.getText()+ "::" + tempApplicationArg.getApplicationName()));
    	                    	 	
    	                     }// for
    	                     
    	                     tempApplicationRootNode.add(tempSequenceFlowRootNode);
    	             }// if we have hardware

    	            // if we have operations
    	             if(tempApplicationArg.getOperationArrayList().size()>0){
    	            	 
    	            	     tempOperationRootNode   = new DefaultMutableTreeNode("Services");
    	                     
    	            	     for(int i = 0; i < (tempApplicationArg.getOperationArrayList()).size(); i++){

    	                    	 	tempOperation = new EOperation();

    	                    	 	tempOperation = (EOperation) tempApplicationArg.getOperationArrayList().get(i);

    	                    	 	tempOperationRootNode.add(new DefaultMutableTreeNode(tempOperation.getApplication()+"."+ tempOperation.getComponent()+"."+tempOperation.getService()+"."+tempOperation.getName() + "::"+tempOperation.getType()+":" + tempOperation.getLocation()));

    	                         //tempOperation.setName(resultSetServices.getString(1));
    	                         //tempOperation.setType(resultSetServices.getString(2));
    	                         

    	                     }// for
    	                     
    	                     tempApplicationRootNode.add(tempOperationRootNode);
    	             }// if we have operations
                     
    	             // if we have interfaces
    	              if(tempApplicationArg.getInterfaceArrayList().size()>0){
    	                  
    	                      tempInterfaceRootNode   = new DefaultMutableTreeNode("Interfaces");
    	                      
    	                      for(int i = 0; i < (tempApplicationArg.getInterfaceArrayList()).size(); i++){

    	                                 tempInterface = new EInterface();

    	                                 tempInterface = (EInterface) tempApplicationArg.getInterfaceArrayList().get(i);

    	                                 tempInterfaceRootNode.add(new DefaultMutableTreeNode(tempInterface.getApplication()+"."+tempInterface.getComponent()+"."+ tempInterface.getService()+"."+tempInterface.getInterfaceName()+ "::"+ tempInterface.getInterfaceType()));
 
    	                      }// for
    	                      
    	                      tempApplicationRootNode.add(tempInterfaceRootNode);
                              
    	              }// if we have operations
                      
                      
    	              // if we have attributes
    	               if(tempApplicationArg.getAttributeArrayList().size()>0){
    	                   
    	                       tempAttributeRootNode   = new DefaultMutableTreeNode("Attributes");
    	                       
    	                       for(int i = 0; i < (tempApplicationArg.getAttributeArrayList()).size(); i++){

    	                                  tempAttribute = new EAttribute();

    	                                  tempAttribute = (EAttribute) tempApplicationArg.getAttributeArrayList().get(i);

    	                                  tempAttributeRootNode.add(new DefaultMutableTreeNode(tempAttribute.getName() + "::"+tempAttribute.getType()));
    	              
	                       }// for
    	                       
    	                       tempApplicationRootNode.add(tempAttributeRootNode);
    	                       
    	                       
    	               }// if we have attributes
                     
                      
    	             return tempApplicationRootNode;

    	}// end createEApplicationTreeNode
     
        // actions
    	private class SaveDatabaseAction extends AbstractAction implements ActionListener {

    	               // set up action's name, icon, descriptions and mnemonic
    	               public void saveDatabaseAction(){
    	                  putValue( NAME, "Save" );
    	                  putValue( SMALL_ICON, new ImageIcon( getClass().getResource( "../../../../images/add16.gif" ) ) );
    	                  putValue( SHORT_DESCRIPTION, "Save DB" );
    	                  putValue( LONG_DESCRIPTION,  "Save DBsaveDatabaseAction" );
    	                  putValue( MNEMONIC_KEY, new Integer( 'S' ) );
    	               }
    	               // display window in which user can input entry
    	               public void actionPerformed( ActionEvent e ){

    	                 saveStateToDatabase();

    	               }//actionPerformed

    	}  // end inner class SaveDatabaseAction
        
    	private class DeleteDatabaseAction extends AbstractAction implements ActionListener {

    	               // set up action's name, icon, descriptions and mnemonic
    	               public void deleteDatabaseAction(){
    	                  putValue( NAME, "Delete" );
    	                  putValue( SMALL_ICON, new ImageIcon( getClass().getResource( "../../../../images/add16.gif" ) ) );
    	                  putValue( SHORT_DESCRIPTION, "Delete DB" );
    	                  putValue( LONG_DESCRIPTION,  "Delete DB deleteDatabaseAction" );
    	                  putValue( MNEMONIC_KEY, new Integer( 'S' ) );
    	               }
    	               // display window in which user can input entry
    	               public void actionPerformed( ActionEvent e ){

    	                 //deleteDatabase();

    	               }//actionPerformed

    	}  // end inner class DeleteDatabaseAction
          	
        private class AddApplicationsAction extends AbstractAction implements ActionListener{

    	               // set up action's name, icon, descriptions and mnemonic
    	               public AddApplicationsAction(){
    	                  putValue( NAME, "Add Applications" );
    	                  putValue( SMALL_ICON, new ImageIcon( getClass().getResource( "../../../../images/BeanAdd16.gif" ) ) );
    	                  putValue( SHORT_DESCRIPTION, "Add New Applications to EOrganism " );
    	                  putValue( LONG_DESCRIPTION,  "Add New Applications to EOrganism Project/ Model" );
    	                  putValue( MNEMONIC_KEY, new Integer( 'A' ) );
    	               }
    	               // display window in which user can input entry
    	               public void actionPerformed( ActionEvent e ){

    	                      // create lookup frame
    	                      addApplicationsFrame = new LookupApplicationFrame();

    	                      AddApplicationsActionListener addApplicationsListener = new AddApplicationsActionListener();
    	                      addApplicationsListener.setAddFrame(addApplicationsFrame);

    	                      addApplicationsFrame.updateApplicationsButton.addActionListener(addApplicationsListener);

    	                      Dimension preferredFrameSize   = addApplicationsFrame.getPreferredSize();
    	                      Dimension frameSize            = internalFrame.getSize();
    	                      Point loc                      = addApplicationsFrame.getLocation();

    	                      addApplicationsFrame.setLocation((frameSize.width  - preferredFrameSize.width) / 2 + loc.x,
                                                                (frameSize.height - preferredFrameSize.height) / 2 + loc.y);

    	                      addApplicationsFrame.pack();
    	                      addApplicationsFrame.show();

    	              }//actionPerformed

    	}  // end inner class AddApplicationsAction
        
        class AddApplicationsActionListener implements ActionListener{

    	          private LookupApplicationFrame addFrame = new LookupApplicationFrame();

    	          public LookupApplicationFrame getAddFrame()               { return addFrame; }
    	          public void setAddFrame(LookupApplicationFrame _addFrame){ addFrame  = _addFrame;}

    	          public void actionPerformed(ActionEvent actionEvent){

    	               EApplication tempApplication = null;

    	              //
    	               
    	               // TO DO
    	              
    	          }// actionPerformed

    	}// end AddApplicationsActionListener
        
    	private class AddComponentsAction extends AbstractAction implements ActionListener{

    	               // set up action's name, icon, descriptions and mnemonic
    	               public AddComponentsAction(){
    	                  putValue( NAME, "Add Components" );
    	                  putValue( SMALL_ICON, new ImageIcon( getClass().getResource( "../../../../images/BeanAdd16.gif" ) ) );
    	                  putValue( SHORT_DESCRIPTION, "Add New Components to EOrganism Application " );
    	                  putValue( LONG_DESCRIPTION,  "Add New Components to Application Project/ Model" );
    	                  putValue( MNEMONIC_KEY, new Integer( 'C' ) );
    	               }
    	               // display window in which user can input entry
    	               public void actionPerformed( ActionEvent e ){

    	                      // create lookup frame
    	                      addComponentsFrame = new MainFormEOrganismComponentsFrame();
    	                      
                              
                              AddComponentsActionListener addComponentsListener = new AddComponentsActionListener();
    	                      addComponentsListener.setCompFrame(addComponentsFrame);

                              Dimension preferredFrameSize   = addComponentsFrame.getPreferredSize();
    	                      Dimension frameSize            = internalFrame.getSize();
    	                      Point loc                      = addComponentsFrame.getLocation();

    	                      addComponentsFrame.setLocation((frameSize.width  - preferredFrameSize.width) / 2 + loc.x,
                                                            (frameSize.height - preferredFrameSize.height) / 2 + loc.y);

    	                      addComponentsFrame.pack();
    	                      addComponentsFrame.show();

    	              }//actionPerformed

    	}  // end inner class AddComponentsAction
    	
    	class AddComponentsActionListener implements ActionListener{

    	          private MainFormEOrganismComponentsFrame addCompFrame = new MainFormEOrganismComponentsFrame();

    	          public MainFormEOrganismComponentsFrame getCompFrame()                { return addCompFrame; }
    	          public void setCompFrame(MainFormEOrganismComponentsFrame _addCompFrame){ addCompFrame  = _addCompFrame;}

    	          public void actionPerformed(ActionEvent actionEvent){

    	               EApplication tempApplication = null;
       
    	               // TO DO
    	              
    	          }// actionPerformed

    	}// end AddComponentsActionListener
        
    	private class LoadWSDLMetadataAction extends AbstractAction implements ActionListener{

    	               // set up action's name, icon, descriptions and mnemonic
    	               public LoadWSDLMetadataAction(){
    	                  putValue( NAME, "DYNAMIC WSDL Metadata LOAD" );
    	                  putValue( SMALL_ICON, new ImageIcon( getClass().getResource( "../../../../images/BeanAdd16.gif" ) ) );
    	                  putValue( SHORT_DESCRIPTION, "DYNAMIC WSDL Metadata LOAD" );
    	                  putValue( LONG_DESCRIPTION,  "DYNAMIC WSDL Metadata LOAD - all configured WSDLs" );
    	                  putValue( MNEMONIC_KEY, new Integer( 'L' ) );
    	               }
    	               // display window in which user can input entry
    	               public void actionPerformed( ActionEvent e ){

    	                  saveWsdlMetadataToDatabase();

    	              }//actionPerformed

    	}  // end inner class LoadWSDLMetadataAction
    	
    	class LoadWSDLMetadataActionListener implements ActionListener{

    	          private MainFormEOrganismComponentsFrame addCompFrame = new MainFormEOrganismComponentsFrame();

    	          public MainFormEOrganismComponentsFrame getCompFrame()                { return addCompFrame; }
    	          public void setCompFrame(MainFormEOrganismComponentsFrame _addCompFrame){ addCompFrame  = _addCompFrame;}

    	          public void actionPerformed(ActionEvent actionEvent){

    	               EApplication tempApplication = null;
                       
                       
    	               loadWSDLLoaderFile();
    	               saveWsdlMetadataToDatabase();
    	              
    	          }// actionPerformed

    	}// end AddWSDLMetadataActionListener
        
    private class LoadDBMetadataAction extends AbstractAction implements ActionListener{

                   // set up action's name, icon, descriptions and mnemonic
                   public LoadDBMetadataAction(){
                      putValue( NAME, "DYNAMIC DB METADATA LOAD" );
                      putValue( SMALL_ICON, new ImageIcon( getClass().getResource( "../../../../images/BeanAdd16.gif" ) ) );
                      putValue( SHORT_DESCRIPTION, "DYNAMIC DB METADATA LOAD " );
                      putValue( LONG_DESCRIPTION,  "DYNAMIC DB METADATA LOAD FOR ALL CONFIGURED DBs" );
                      putValue( MNEMONIC_KEY, new Integer( 'D' ) );
                   }
                   // display window in which user can input entry
                   public void actionPerformed( ActionEvent e ){

                      //loadDbMetadataToDatabase();
                      //loadDbCollectionsMetadatatoDatabase();

                  }//actionPerformed

    }  // end inner class LoadDBMetadataAction
    
    class ApplicationInventoryActionListener implements ActionListener{
            public void actionPerformed(ActionEvent actionEvent){
               loadApplicationInventoryToDatabase();
            }// actionPerformed

    }// end ApplicationInventoryActionListener
     
    private class ApplicationInventoryAction extends AbstractAction implements ActionListener{

                   // set up action's name, icon, descriptions and mnemonic
                   public ApplicationInventoryAction(){
                      putValue( NAME, "APPLICATION INVENTORY LOAD" );
                      putValue( SMALL_ICON, new ImageIcon( getClass().getResource( "../../../../images/BeanAdd16.gif" ) ) );
                      putValue( SHORT_DESCRIPTION, "APPLICATION INVENTORY LOAD " );
                      putValue( LONG_DESCRIPTION,  "APPLICATION INVENTORY LOAD FOR ALL APPLICATIONS" );
                      putValue( MNEMONIC_KEY, new Integer( 'R' ) );
                   }
                   // display window in which user can input entry
                   public void actionPerformed( ActionEvent e ){

                           loadApplicationInventoryToDatabase();

                   }//actionPerformed

    }  // end inner class ApplicationInventoryAction
    
    class LoadDBMetadataActionListener implements ActionListener{
            public void actionPerformed(ActionEvent actionEvent){

                  //loadDbMetadataToDatabase();
                  //loadDbCollectionsMetadatatoDatabase();
                
              }// actionPerformed

    }// end LoadDBMetadataActionListener
    
    
    private class DeleteWSDLMetadataAction extends AbstractAction implements ActionListener{

                   // set up action's name, icon, descriptions and mnemonic
                   public DeleteWSDLMetadataAction(){
                      putValue( NAME, "DELETE WSDL Metadata LOAD" );
                      putValue( SMALL_ICON, new ImageIcon( getClass().getResource( "../../../../images/BeanAdd16.gif" ) ) );
                      putValue( SHORT_DESCRIPTION, "DELETE WSDL Metadata from EOrganism Application " );
                      putValue( LONG_DESCRIPTION,  "DELETE WSDL Metadata from EOrganism Application " );
                      putValue( MNEMONIC_KEY, new Integer( 'M' ) );
                   }
                   // display window in which user can input entry
                   public void actionPerformed( ActionEvent e ){

                         //

                  }//actionPerformed

    }  // end inner class DeleteWSDLMetadataAction
    
    class DeleteWSDLMetadataActionListener implements ActionListener{

              private MainFormEOrganismComponentsFrame addCompFrame = new MainFormEOrganismComponentsFrame();

              public MainFormEOrganismComponentsFrame getCompFrame()                { return addCompFrame; }
              public void setCompFrame(MainFormEOrganismComponentsFrame _addCompFrame){ addCompFrame  = _addCompFrame;}

              public void actionPerformed(ActionEvent actionEvent){

                   EApplication tempApplication = null;
    
                   // TO DO
                  
              }// actionPerformed

    }// end DeleteWSDLMetadataActionListener
    
    private class DeleteDBMetadataAction extends AbstractAction implements ActionListener{

               // set up action's name, icon, descriptions and mnemonic
               public DeleteDBMetadataAction(){
                  putValue( NAME, "DELETE DB METADATA" );
                  putValue( SMALL_ICON, new ImageIcon( getClass().getResource( "../../../../images/BeanAdd16.gif" ) ) );
                  putValue( SHORT_DESCRIPTION, "DELETE DB METADATA from EOrganism Application " );
                  putValue( LONG_DESCRIPTION,  "DELETE DB METADATA from EOrganism Application for all configured DBs" );
                  putValue( MNEMONIC_KEY, new Integer( 'D' ) );
               }
               // display window in which user can input entry
               public void actionPerformed( ActionEvent e ){

                     //

              }//actionPerformed

    }  // end inner class DeleteDBMetadataAction
    
    class DeleteDBMetadataActionListener implements ActionListener{

          private MainFormEOrganismComponentsFrame addCompFrame = new MainFormEOrganismComponentsFrame();

          public MainFormEOrganismComponentsFrame getCompFrame()                { return addCompFrame; }
          public void setCompFrame(MainFormEOrganismComponentsFrame _addCompFrame){ addCompFrame  = _addCompFrame;}

          public void actionPerformed(ActionEvent actionEvent){

               EApplication tempApplication = null;
    
               // TO DO
              
          }// actionPerformed

    }// end DeleteDBMetadataActionListener
     
} // end class JEOrganismController


