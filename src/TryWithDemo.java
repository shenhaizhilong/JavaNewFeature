import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author: shenhaizhilong
 * @date: 2019/1/29 0:04
 */
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
