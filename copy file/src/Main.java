import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println(System.getProperty("user.dir"));

        List<String> list1=readFile("MyCustom.txt");

            cmd(list1);

    }

    public static List<String> readFile(String fileName){

        List<String> list1=new ArrayList<>();
        File file = new File(fileName);

        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null){
                list1.add(line);
            }
        }catch (Exception e){}
        return list1;
    }



    public static void cmd(List<String > list1){

        String workPath= System.getProperty("user.dir");
        String myblogPath=workPath.substring(0,workPath.lastIndexOf("\\"))+"\\myblog";


        for(String u1:list1) {
            try {
                String s1=myblogPath+"\\"+u1;
                String s2=workPath.substring(0,workPath.lastIndexOf("\\"))+"\\CopyResult\\"+u1;
                File sourceFile = new File(s1);

                File targetFile = new File(s2);

                new File(s2.substring(0,s2.lastIndexOf("\\"))).mkdirs();

                Files.copy(sourceFile.toPath(),targetFile.toPath());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}