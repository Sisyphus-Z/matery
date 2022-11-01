import com.qcloud.cos.COSClient;

public class UploadMain {
    public static void main(String[] args){
        COSClient uploadCOSClient=Client.createCOSClient(1);


        //存储桶如果是空的,可能会报错
        Delete.run(uploadCOSClient);




        UpLoad.run(uploadCOSClient);




        Client.close(uploadCOSClient);


    }
}
