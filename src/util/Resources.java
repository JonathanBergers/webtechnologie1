package util;

/**
 * Created by jonathan on 7-9-15.
 */
public class Resources {

    //pages
    public static final String PAGE_ROOT = "/Kamerverhuur";

    public static final String PAGE_MAIN = "/index.html";
    public static final String PAGE_NO_ACCESS = PAGE_ROOT + "/NoAccess";
    public static final String PAGE_FOUTE_LOGIN = "/WEB-INF/fouteLogin.html";

    public static final String PAGE_HUURDER_MAIN = PAGE_ROOT + "/Huurder/Main";
    public static final String PAGE_VERHUURDER_MAIN = PAGE_ROOT + "/Verhuurder/Main";
    public static final String PAGE_BEHEERDER_MAIN = PAGE_ROOT + "/Beheerder/Main";

    // filters
    public static final String AUTH_FILTER = "/Kamerverhuur/*";
    public static final String LOGIN_FILTER= "/index.html";

    public static final String VERHUURDER_FILTER = PAGE_ROOT + "/Verhuurder/*";
    public static final String BEHEER_FILTER = PAGE_ROOT + "/Beheerder/*";
    public static final String HUURDER_FILER = PAGE_ROOT + "/Huurder/*";




    // session scope
    public static final String SESSION_USER = "currentuser";


    // application scope
    public static final String APPLICATION_USERS = "users";
    public static final String MODEL = "model";

    public static final String PATH_ACCOUNTS = "/home/jonathan/Dropbox/Studie/Jaar 2/Workspace/Kamerverhuur/accounts.xml";

}