import com.qcloud.cos.COSClient;

public class ResourceMain {
    public static void main(String[] args){

        COSClient resourceClient=Client.createCOSClient(2);


        Resource.run(resourceClient);



        Client.close(resourceClient);




    }
}
