# Java SE 9: Try-With-Resources Improvements
___

Before 9 
```java 

public static void testARM_Before_Java9() throws IOException{
 BufferedReader reader1 = new BufferedReader(new FileReader("journaldev.txt"));
 try (BufferedReader reader2 = reader1) {
   System.out.println(reader2.readLine());
 }
}
```

java 9
```java

public class TryWithDemo {

    public static void readFile(String fileName)
    {
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            try(reader)
            {
                var line = "";
                while ((line = reader.readLine()) != null)
                {
                    System.out.println(line);
                }
            }
        }catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }catch (IOException ex)
        {
            ex.printStackTrace();
        }
        
    }
    public static void main(String[] args) {
        readFile("Jshell.md");
    }
}


```

## 参考资料
- [try with resource](https://www.journaldev.com/12940/javase9-try-with-resources-improvements)