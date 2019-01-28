import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author: shenhaizhilong
 * @date: 2019/1/28 18:52
 */
public class VarDemo {

    public  void readFile() throws IOException {
        var fileName = "String.md";
        var line = "";
        var fileReader = new FileReader(fileName);
        var bufferedReader = new BufferedReader(fileReader);
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
        bufferedReader.close();
    }

    public static void main(String[] args) throws IOException {
        VarDemo demo = new VarDemo();
        demo.readFile();
    }
}
