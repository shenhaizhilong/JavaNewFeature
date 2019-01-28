# Interface 
___

## Java 7 
在Java 7 及之前的版本，一个接口内部只有：

- 1. 常量
- 2. 抽象方法

在接口内部没有方法实现

## Java 8

可以有方法的实现:静态方法和默认方法

- 1. 常量
- 2. 抽象方法
- 3. 默认方法
- 4. 静态方法

## Java 9
引入private 私有方法

- 1. 常量
- 2. 抽象方法
- 3. 默认方法
- 4. 静态方法
- 5. 私有方法
- 6. 私有静态方法

```java

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

```

```java
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
```
output:
```java
Info - This is Info
Warn - This is Warn
Fatal - This is Fatal
Error - This is Error
Interface public static method
```

## 接口内定义私有方法的规则
- 1. 应有private 修饰符修饰这些方法
- 2. private 与abstract 修饰符不能一起修饰一个方法
     > private 修饰的方法意味着完全实现的方法，因为子类无法继承与覆盖该方法
     > abstract 修饰的方法意味着没有实现的方法，子类应该继承和覆盖这个方法
- 3.私有方法必须在接口内包含实现

     


```java
// can't do this, private and abstract can't together.
 private abstract void log(String message, String msgPrefix)
    {
        // Step 1: Connect to DataStore
        // Step 2: Log Message with Prefix and styles etc.
        // Step 3: Close the DataStore connection
        System.out.println(String.format("%s - %s", msgPrefix, message));
    }
``` 


##  参考文献

- [java9 private methods interfaces](https://www.journaldev.com/12850/java-9-private-methods-interfaces)