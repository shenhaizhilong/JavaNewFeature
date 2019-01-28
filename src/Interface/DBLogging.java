package Interface;

/**
 * @author: shenhaizhilong
 * @date: 2019/1/27 23:36
 */
public interface DBLogging {
    String MONGO_DB_NAME = "Tom_Mongo_DB";
    String SQL_DB_NAME = "Tom_SQL_DB";

    default void logInfo(String message)
    {
        log(message, "Info");
    }

    default void logWarn(String message)
    {
        log(message, "Warn");
    }

    default void logError(String message)
    {
        log(message, "Error");
    }

    default void logFatal(String message)
    {
        log(message, "Fatal");
    }

    private void log(String message, String msgPrefix)
    {
        // Step 1: Connect to DataStore
        // Step 2: Log Message with Prefix and styles etc.
        // Step 3: Close the DataStore connection
        System.out.println(String.format("%s - %s", msgPrefix, message));
    }

    private static void test()
    {
        System.out.println("Interface private static method");
    }

    public static void test2()
    {
        System.out.println("Interface public static method");
    }

    public static void main(String[] args) {
        DBLogging.test();
        DBLogging.test2();
    }
}
