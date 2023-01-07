package com.jomo.eorganism.metamodel.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

import java.awt.*;
import java.io.File;

public class ApplicationConfiguration {

    // Possible Look & Feels
    private static final String METAL    =        "javax.swing.plaf.metal.MetalLookAndFeel";
    private static final String MOTIF    =        "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
    private static final String WINDOWS  =        "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
    private static final String GTK      =        "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
    private static final String MAC      =        "com.sun.java.swing.plaf.mac.MacLookAndFeel";

    private static final int METAL_INDEX    =  0;
    private static final int MOTIF_INDEX    =  1;
    private static final int WINDOWS_INDEX  =  2;
    private static final int GTK_INDEX      =  3;
    private static final int MAC_INDEX      =  4;

    private static final int DEFAULT_METAL_THEME         = 0;
    private static final int GREEN_METAL_THEME           = 1;
    private static final int AQUA_METAL_THEME            = 2;
    private static final int KHAKI_METAL_THEME           = 3;
    private static final int CONTRAST_METAL_THEME        = 4;
    private static final int MYTHEME_METAL_THEME         = 5;
    private static final int JOMO_METAL_THEME            = 6;

    private Color color = Color.lightGray;

    public static final Font  attributeFont = new Font("New Font", Font.TRUETYPE_FONT, 11);

    private static int fileIndex;
    private static int frameIndex;
    public static final int NEWAPPSCHELETON  =0;
    public static final int NEWAPPLOOKUP     =1;
    public static final int OPENFROMXML      =2;
    public static final int SAVE             =3;
    public static final int SAVEALL          =4;
    public static final int EXIT             =5;
    public static final int ABOUT            =6;
    public static final int OPEN_ENTERPRISE  =7;

    public static final String NEWAPPSCHELETON_TEXT  = "New JEE App from Scheleton";
    public static final String NEWAPPLOOKUP_TEXT     = "New JEE App from DB Lookup";
    public static final String OPENFROMDB_TEXT       = "Open JEE Application from DB";
    public static final String OPENFROMXML_TEXT      = "Open Project from XML Metamodel";
    public static final String SAVE_TEXT             = "Save Project";
    public static final String SAVEALL_TEXT          = "Save All Projects";
    public static final String EXIT_TEXT             = "Exit EOrganism";
    public static final String ABOUT_TEXT            = "About EOrganism";
    public static final String OPEN_ENTERPRISE_TEXT  = "OPEN_ENTERPRISE";

    // EORGANISM
    public static final String LOAD_XML_MODEL_PHYSICAL_LOGICAL             = "LOAD_XML_MODEL_PHYSICAL_LOGICAL";
    public static final String LOAD_DB_MODEL_PHYSICAL_LOGICAL              = "LOAD_DB_MODEL_PHYSICAL_LOGICAL";
    public static final String OPEN_ENTERPRISE_EORGANISM_ALL_ENVIRONMENTS  = "OPEN_ENTERPRISE_EORGANISM_ALL_ENVIRONMENTS";
    public static final String OPEN_ENTERPRISE_EORGANISM_FROM_ENVIRONMENT  = "OPEN_ENTERPRISE_EORGANISM_FROM_ENVIRONMENT";

    public static final String OPEN_ENTERPRISE_EORGANISM_FROM_XML  	    = "OPEN_ENTERPRISE_EORGANISM_FROM_XML";
    public static final String OPEN_APPLICATION_FROM_XML                 = "OPEN_APPLICATION_FROM_XML";
    public static final String LOAD_COMMAND_XML_FILE                     = "LOAD_COMMAND_XML_FILE";


    public static final String SAVE_ENTERPRISE_EORGANISM  		        = "SAVE_ENTERPRISE_EORGANISM";
    public static final String CLOSE_ENTERPRISE_EORGANISM  		        = "CLOSE_ENTERPRISE_EORGANISM";
    public static final String CLONE_ENTERPRISE_EORGANISM  		        = "CLONE_ENTERPRISE_EORGANISM";
    public static final String COMPARE_ENTERPRISE_EORGANISM  	        = "COMPARE_ENTERPRISE_EORGANISM";


    public static final String OPEN_APPLICATION_EORGANISM            =  "OPEN_APPLICATION_EORGANISM";
    public static final String OPEN_APPLICATION_EORGANISM_XML_URL    =  "OPEN_APPLICATION_EORGANISM_XML_URL";
    public static final String OPEN_APPLICATION_EORGANISM_DB_URL     =  "OPEN_APPLICATION_EORGANISM_DB_URL";
    public static final String NEW_DB_CONNECTION                     =  "NEW_DB_CONNECTION ";
    public static final String OPEN_HARDWARE_EORGANISM               =  "OPEN_HARDWARE_EORGANISM";
    public static final String OPEN_SOFTWARE_EORGANISM               =  "OPEN_SOFTWARE_EORGANISM";
    public static final String OPEN_PEOPLE_EORGANISM                 =  "OPEN_PEOPLE_EORGANISM";
    public static final String OPEN_DATABASE_EORGANISM               =  "OPEN_DATABASE_EORGANISM";
    public static final String OPEN_BUSINESS_EORGANISM               =  "OPEN_BUSINESS_EORGANISM";
    public static final String OPEN_SEQUENCE_FLOW_EORGANISM          =  "OPEN_SEQUENCE_FLOW_EORGANISM";
    public static final String OPEN_ENVIRONMENT_EORGANISM            =  "OPEN_ENVIRONMENT_EORGANISM";
    public static final String OPEN_RELEASE_EORGANISM                =  "OPEN_RELEASE_EORGANISM";
    public static final String OPEN_PROJECT_EORGANISM                =  "OPEN_PROJECTS_EORGANISM";

    public static final String ADD_APPLICATIONS_EORGANISM =  "ADD_APPLICATIONS_EORGANISM";
    public static final String ADD_COMPONENTS_EORGANISM =  "ADD_COMPONENTS_EORGANISM";
    public static final String ADD_OPERATIONS_EORGANISM =  "ADD_OPERATIONS_EORGANISM";
    public static final String ADD_INTERFACES_EORGANISM =  "ADD_INTERFACES_EORGANISM";
    public static final String ADD_HARDWARES_EORGANISM =  "ADD_HARDWARES_EORGANISM";
    public static final String ADD_SOFTWARES_EORGANISM =  "ADD_SOFTWARES_EORGANISM";
    public static final String ADD_PEOPLES_EORGANISM =  "ADD_PEOPLES_EORGANISM";
    public static final String ADD_DATABASES_EORGANISM =  "ADD_DATABASES_EORGANISM";
    public static final String ADD_BUSINESSES_EORGANISM =  "ADD_BUSINESSES_EORGANISM";
    public static final String ADD_SEQUENCE_FLOWS_EORGANISM =  "ADD_SEQUENCE_FLOWS_EORGANISM";
    public static final String ADD_ENVIRONMENTS_EORGANISM =  "ADD_ENVIRONMENTS_EORGANISM";
    public static final String ADD_RELEASES_EORGANISM =  "ADD_RELEASES_EORGANISM";
    public static final String ADD_PROJECTS_EORGANISM =  "ADD_PROJECTS_EORGANISM";

    // no args constructor
    public ApplicationConfiguration() {
    }
}
