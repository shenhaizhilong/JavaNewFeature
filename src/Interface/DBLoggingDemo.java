package Interface;

/**
 * @author: shenhaizhilong
 * @date: 2019/1/27 23:41
 */
public class DBLoggingDemo implements DBLogging {

    public DBLoggingDemo()
    {

    }
    public static void main(String[] args) {
        DBLogging log = new DBLoggingDemo();
        log.logInfo("This is Info");
        log.logWarn("This is Warn");
        log.logFatal("This is Fatal");
        log.logError("This is Error");
        DBLogging.test2();
    }
}
