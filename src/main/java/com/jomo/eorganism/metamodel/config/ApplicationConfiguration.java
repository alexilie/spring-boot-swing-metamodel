package com.jomo.eorganism.metamodel.config;

import lombok.AllArgsConstructor;

import java.awt.Color;
import java.awt.Font;

@AllArgsConstructor
public class ApplicationConfiguration {
    public static final String APPLICATION =  "APPLICATION";
    public static final String COMPONENT   =  "COMPONENT";
    public static final String DATABASE    =  "DATABASE";
    public static final String DOMAIN      =  "DOMAIN";
    public static final String ENVIRONMENT =  "ENVIRONMENT";
    public static final String RELEASE     =  "RELEASE";
    public static final String SYSTEM      =  "SYSTEM";
    public static final String BUSINESS    =  "BUSINESS";
    public static final String CAPABILITY  =  "CAPABILITY";
    public static final String SERVICE     =  "SERVICE";
    public static final String INTERFACE   =  "INTERFACE";
    public static final String OBJECT      =  "OBJECT";
    public static final String ATTRIBUTE   =  "ATTRIBUTE";
    public static final String FUNCTION    =  "FUNCTION";

    public static final String METAL    =        "javax.swing.plaf.metal.MetalLookAndFeel";
    public static final String MOTIF    =        "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
    public static final String WINDOWS  =        "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
    public static final String GTK      =        "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
    public static final String MAC      =        "com.sun.java.swing.plaf.mac.MacLookAndFeel";
    public static final int METAL_INDEX    =  0;
    public static final int MOTIF_INDEX    =  1;
    public static final int WINDOWS_INDEX  =  2;
    public static final int GTK_INDEX      =  3;
    public static final int MAC_INDEX      =  4;
    public static final int DEFAULT_METAL_THEME         = 0;
    public static final int GREEN_METAL_THEME           = 1;
    public static final int AQUA_METAL_THEME            = 2;
    public static final int KHAKI_METAL_THEME           = 3;
    public static final int CONTRAST_METAL_THEME        = 4;
    public static final int MYTHEME_METAL_THEME         = 5;
    public static final int JOMO_METAL_THEME            = 6;
    //public static int fileIndex;
    //public static int frameIndex;
    public static final int NEWAPPSCHELETON  = 0;
    public static final int NEWAPPLOOKUP     = 1;
    public static final int OPENFROMXML      = 2;
    public static final int SAVE             = 3;
    public static final int SAVEALL          = 4;
    public static final int EXIT             = 5;
    public static final int ABOUT            = 6;
    public static final int OPEN_ENTERPRISE  = 7;

    public static final int BUTTONS_NUMBER           = 8;

    public static final String NEWAPPSCHELETON_TEXT  = "New JEE App from Scheleton";
    public static final String NEWAPPLOOKUP_TEXT     = "New JEE App from DB Lookup";
    public static final String OPENFROMDB_TEXT       = "Open JEE Application from DB";
    public static final String OPENFROMXML_TEXT      = "Open Project from XML Metamodel";
    public static final String SAVE_TEXT             = "Save Project";
    public static final String SAVEALL_TEXT          = "Save All Projects";
    public static final String EXIT_TEXT             = "Exit EOrganism";
    public static final String ABOUT_TEXT            = "About EOrganism";
    public static final String OPEN_ENTERPRISE_TEXT  = "OPEN_ENTERPRISE";
    public static final String LOAD_XML_MODEL_PHYSICAL_LOGICAL             = "LOAD_XML_MODEL_PHYSICAL_LOGICAL";
    public static final String LOAD_DB_MODEL_PHYSICAL_LOGICAL              = "LOAD_DB_MODEL_PHYSICAL_LOGICAL";
    public static final String OPEN_ENTERPRISE_EORGANISM_ALL_ENVIRONMENTS  = "OPEN_ENTERPRISE_EORGANISM_ALL_ENVIRONMENTS";
    public static final String OPEN_ENTERPRISE_EORGANISM_FROM_ENVIRONMENT  = "OPEN_ENTERPRISE_EORGANISM_FROM_ENVIRONMENT";
    public static final String OPEN_ENTERPRISE_EORGANISM_FROM_XML          = "OPEN_ENTERPRISE_EORGANISM_FROM_XML";
    public static final String OPEN_APPLICATION_FROM_XML                   = "OPEN_APPLICATION_FROM_XML";
    public static final String LOAD_COMMAND_XML_FILE                       = "LOAD_COMMAND_XML_FILE";
    public static final String SAVE_ENTERPRISE_EORGANISM                   = "SAVE_ENTERPRISE_EORGANISM";
    public static final String CLOSE_ENTERPRISE_EORGANISM                  = "CLOSE_ENTERPRISE_EORGANISM";
    public static final String CLONE_ENTERPRISE_EORGANISM                  = "CLONE_ENTERPRISE_EORGANISM";
    public static final String COMPARE_ENTERPRISE_EORGANISM                = "COMPARE_ENTERPRISE_EORGANISM";
    public static final String OPEN_APPLICATION_EORGANISM                  =  "OPEN_APPLICATION_EORGANISM";
    public static final String OPEN_APPLICATION_EORGANISM_XML_URL          =  "OPEN_APPLICATION_EORGANISM_XML_URL";
    public static final String OPEN_APPLICATION_EORGANISM_DB_URL           =  "OPEN_APPLICATION_EORGANISM_DB_URL";
    public static final String NEW_DB_CONNECTION                           =  "NEW_DB_CONNECTION ";
    public static final String OPEN_HARDWARE_EORGANISM                     =  "OPEN_HARDWARE_EORGANISM";
    public static final String OPEN_SOFTWARE_EORGANISM                     =  "OPEN_SOFTWARE_EORGANISM";
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
    public static Color color = Color.lightGray;
    public static final Font  attributeFont = new Font("New Font", Font.TRUETYPE_FONT, 11);
    public static final String APPLICATION_FRAME_NAME =  "EORGANISM MetaModel :: Enterprise Architecture :: Main";

}
