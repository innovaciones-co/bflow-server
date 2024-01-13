package co.innovaciones.bflow_server.service


import org.springframework.stereotype.Service
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.*
import software.amazon.awssdk.services.s3.presigner.S3Presigner
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest
import java.time.Duration

@Service
class S3Service(private val s3Client: S3Client, private val s3Presigner: S3Presigner) : S3ServiceI {

    override fun listBuckets(): MutableList<Bucket> {
        val listBucketsRequest = ListBucketsRequest.builder().build()

        return s3Client.listBuckets(listBucketsRequest).buckets()
    }

    private fun verifyAndCreateBucket(bucketName: String) {
        try {
            val headBucketRequest = HeadBucketRequest.builder()
                .bucket(bucketName)
                .build()

            s3Client.headBucket(headBucketRequest)
        } catch (e: S3Exception) {
            if (e.statusCode() == 404) {
                createBucket(bucketName)
            } else {
                throw e
            }
        }
    }

    override fun createBucket(bucketName: String) {
        val createBucketRequest = CreateBucketRequest.builder().bucket(bucketName).build()

        s3Client.createBucket(createBucketRequest)
    }

    override fun deleteBucket(bucketName: String) {
        val deleteBucketRequest = DeleteBucketRequest.builder().bucket(bucketName).build()

        s3Client.deleteBucket(deleteBucketRequest)
    }

    override fun uploadObject(bucketName: String, key: String, content: RequestBody) {
        verifyAndCreateBucket(bucketName)
        val putObjectRequest = PutObjectRequest.builder().bucket(bucketName).key(key).build()

        s3Client.putObject(putObjectRequest, content)
    }

    override fun deleteObject(bucketName: String, key: String) {
        val deleteObjectRequest = DeleteObjectRequest.builder().bucket(bucketName).key(key).build()

        s3Client.deleteObject(deleteObjectRequest)
    }

    override fun getTemporaryUrl(defaultBucket: String, key: String?): String? {

        val getObjectRequest = GetObjectRequest.builder()
            .bucket(defaultBucket)
            .key(key)
            .build()
        val getObjectPresignedRequest: GetObjectPresignRequest = GetObjectPresignRequest.builder().signatureDuration(
            Duration.ofHours(20)
        ).getObjectRequest(getObjectRequest).build()

        val presignedRequest: PresignedGetObjectRequest =
            s3Presigner.presignGetObject(getObjectPresignedRequest)

        return  presignedRequest.url().toString()
    }

}
