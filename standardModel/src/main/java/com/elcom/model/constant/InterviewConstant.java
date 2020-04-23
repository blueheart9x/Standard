package com.elcom.model.constant;

public class InterviewConstant {

    /* REDIS SERVER TEST 103.21.151.171 */
    public static final String REDIS_HOST = "127.0.0.1";
    public static final String REDIS_PASSWORD = "dev-eonline";
    public static final int REDIS_PORT = 6379; // Defaul port
    public static final int REDIS_TIME_OUT = 1000 * 2; // Default is 2 seconds
    public static final boolean REDIS_SSL = false;

    public static final String MAIL_SEND_ACC = "hoangyen918588@gmail.com";
    public static final String MAIL_SEND_PW = "khokhanquanhi123";
    public static final String MAIL_PORT = "465";
    public static final String MAIL_HOST = "smtp.gmail.com";

    public static final String EP_LINK_SCHEDULE = "http://ep.elcom.com.vn/user/schedules/";
    public static final String SCHEDULE_STATUS_KEY = "SCHEDULE_STAT";

    public static final int JDBC_BATCH_SIZE = 100;

    public static final long SYNC_EVENTS_SLEEP_TIME = 1000 * 60 * 30; // 30 minutes
    public static final long FILL_CALENDAR_SLEEP_TIME = 1000 * 60 * 60 * 4; // 4 hours
    public static final String[] SYNC_EVENTS_HOURS = new String[]{
        "09",
        "14",
        "01"
    };

    public static final String[] NGHI_CHE_DO = new String[]{"CO", "DS", "KT", "TS", "VD", "NO", "ST"};
    public static final String[] NGHI_HUONG_LUONG = new String[]{"NC", "NT", "NL", "NM"};
    
    //For web constant
    public static String ROOT_DIR = "";
    public static String ROOT_URL = "";
    
    //File progress constant
    public static final String RECORD_LOG_FOLDER = "/home/truongdx/.jitsi-meet-cfg/jibri/tmp";
    public static final String RECORD_FOLDER =     "/home/truongdx/.jitsi-meet-cfg/jibri/recordings";
}
