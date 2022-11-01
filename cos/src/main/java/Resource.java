import com.qcloud.cos.COSClient;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.COSObjectSummary;
import com.qcloud.cos.model.ListObjectsRequest;
import com.qcloud.cos.model.ObjectListing;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

public class Resource implements Config{
    public static void run(COSClient cosClient) {


// 存储桶的命名格式为 BucketName-APPID，此处填写的存储桶名称必须为此格式
        String bucketName = bucketName2;

        ListObjectsRequest listObjectsRequest = new ListObjectsRequest();
// 设置 bucket 名称
        listObjectsRequest.setBucketName(bucketName);
// prefix 表示列出的对象名以 prefix 为前缀
// 这里填要列出的目录的相对 bucket 的路径
        listObjectsRequest.setPrefix(selectDir1);
// delimiter 表示目录的截断符, 例如：设置为 / 则表示对象名遇到 / 就当做一级目录）
//        listObjectsRequest.setDelimiter("/");
// 设置最大遍历出多少个对象, 一次 listobject 最大支持1000
        listObjectsRequest.setMaxKeys(1000);

// 保存每次列出的结果
        ObjectListing objectListing = null;

        List<COSObjectSummary> cosObjectSummaries;

        do {
            try {
                objectListing = cosClient.listObjects(listObjectsRequest);
            } catch (CosServiceException e) {
                e.printStackTrace();
                return;
            } catch (CosClientException e) {
                e.printStackTrace();
                return;
            }

            // 这里保存列出来的子目录
            List<String> commonPrefixes = objectListing.getCommonPrefixes();
            for (String commonPrefix : commonPrefixes) {
                System.out.println(commonPrefix);
            }

            // 这里保存列出的对象列表
            cosObjectSummaries = objectListing.getObjectSummaries();
            for (COSObjectSummary cosObjectSummary : cosObjectSummaries) {
                // 对象的 key
                String key = cosObjectSummary.getKey();
                // 对象的etag
                String etag = cosObjectSummary.getETag();
                // 对象的长度
                long fileSize = cosObjectSummary.getSize();
                // 对象的存储类型
                String storageClasses = cosObjectSummary.getStorageClass();
            }


            // 标记下一次开始的位置
            String nextMarker = objectListing.getNextMarker();
            listObjectsRequest.setMarker(nextMarker);
        } while (objectListing.isTruncated());

        JSONArray arr=new JSONArray();
        for (COSObjectSummary cosObjectSummary : cosObjectSummaries) {
            JSONObject subObj = new JSONObject();
            String key = cosObjectSummary.getKey();
            String url = String.valueOf(cosClient.getObjectUrl(bucketName, key));
            System.out.println(url);
            subObj.put(key, url);

            arr.put(subObj);

        }


        try {
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(outDir1), "UTF-8");
            osw.write("data=\r\n");
            osw.write(arr.toString().replace("},{","},\r\n{"));
            osw.flush();//清空缓冲区，强制输出数据
            osw.close();//关闭输出流
        } catch (Exception e) { }
    }
}
