import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println(System.getProperty("user.dir"));

        List<String> list1=readFile("MyCustom.txt");

        try {
            copy(list1);
        }catch (Exception e){
            e.printStackTrace();
        };

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



    public static void copy(List<String > list1) throws IOException, InterruptedException {

        String workPath= System.getProperty("user.dir");
        String myblogPath=workPath.substring(0,workPath.lastIndexOf("\\"))+"\\myblog";
        String resultPath=workPath.substring(0,workPath.lastIndexOf("\\"))+"\\CopyResult";

        Runtime.getRuntime().exec("cmd /c rmdir /s/q"+" "+resultPath);
        Thread.sleep(1000);

        for(String u1:list1) {

                String s1=myblogPath+"\\"+u1;
                String s2=resultPath+"\\"+u1;


            if(new File(s1).isFile()) {

                new File(s2.substring(0, s2.lastIndexOf("\\"))).mkdirs();
                Files.copy(new File(s1).toPath(), new File(s2).toPath());

            }else {
                if (!new File(s2).exists()) {
                    new File(s2).mkdirs();
                }
                copyFolder(new File(s1),new File(s2));
            }



        }
    }



    //复制目录
    private static void copyFolder(File srcFolder, File targetFolder) throws IOException {
        //遍历源文件夹所有的文件，复制到目标文件夹下
        File[] files = srcFolder.listFiles();
        for (File f : files) {
            //如果f是子文件就直接复制
            if (f.isFile()) {
                //复制文件
                copyFiles(f, targetFolder);

            } else {
                //递归
                //如果是文件夹,就创建文件夹，然后递归复制目录
                File ff = new File(targetFolder.getAbsolutePath(), f.getName());
                ff.mkdirs();
                copyFolder(f,ff);

            }
        }
    }

    //复制文件
    private static void copyFiles(File f, File targetFolder) throws IOException {
        FileInputStream in = new FileInputStream(f);
        FileOutputStream out = new FileOutputStream(new File(targetFolder, f.getName()));
        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len = in.read(bytes)) != -1) {
            out.write(bytes, 0, len);
            out.flush();
        }
        in.close();
        out.close();
    }

}