import com.qcloud.cos.COSClient;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.exception.MultiObjectDeleteException;
import com.qcloud.cos.model.*;

import java.util.ArrayList;
import java.util.List;

public class Delete implements Config{


    public static void run(COSClient cosClient){
        // 列目录实现参考：列出对象 -> 简单操作 -> 列出目录下的对象和子目录
// 批量删除实现参考本页：批量删除对象

// 调用 COS 接口之前必须保证本进程存在一个 COSClient 实例，如果没有则创建
// 详细代码参见本页：简单操作 -> 创建 COSClient
//        COSClient cosClient = createCOSClient();

// 存储桶的命名格式为 BucketName-APPID，此处填写的存储桶名称必须为此格式
        String bucketName = bucketName1;

// 要删除的目录，这里是相对于 bucket 的路径
        String delDir = deleteDir1;

        ListObjectsRequest listObjectsRequest = new ListObjectsRequest();
// 设置 bucket 名称
        listObjectsRequest.setBucketName(bucketName);
// prefix 表示列出的对象名以 prefix 为前缀
// 这里填要列出的目录的相对 bucket 的路径
        listObjectsRequest.setPrefix(delDir);
// 设置最大遍历出多少个对象, 一次 listobject 最大支持1000
        listObjectsRequest.setMaxKeys(1000);

// 保存每次列出的结果
        ObjectListing objectListing = null;

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

            // 这里保存列出的对象列表
            List<COSObjectSummary> cosObjectSummaries = objectListing.getObjectSummaries();

            ArrayList<DeleteObjectsRequest.KeyVersion> delObjects = new ArrayList<DeleteObjectsRequest.KeyVersion>();

            for (COSObjectSummary cosObjectSummary : cosObjectSummaries) {
                delObjects.add(new DeleteObjectsRequest.KeyVersion(cosObjectSummary.getKey()));
            }

            DeleteObjectsRequest deleteObjectsRequest = new DeleteObjectsRequest(bucketName);

            deleteObjectsRequest.setKeys(delObjects);

            try {
                DeleteObjectsResult deleteObjectsResult = cosClient.deleteObjects(deleteObjectsRequest);
                List<DeleteObjectsResult.DeletedObject> deleteObjectResultArray = deleteObjectsResult.getDeletedObjects();
            } catch (MultiObjectDeleteException mde) {
                // 如果部分删除成功部分失败, 返回 MultiObjectDeleteException
                List<DeleteObjectsResult.DeletedObject> deleteObjects = mde.getDeletedObjects();
                List<MultiObjectDeleteException.DeleteError> deleteErrors = mde.getErrors();
            } catch (CosServiceException e) {
                e.printStackTrace();
                return;
            } catch (CosClientException e) {
                e.printStackTrace();
                return;
            }

            // 标记下一次开始的位置
            String nextMarker = objectListing.getNextMarker();
            listObjectsRequest.setMarker(nextMarker);
        } while (objectListing.isTruncated());
    }


}
