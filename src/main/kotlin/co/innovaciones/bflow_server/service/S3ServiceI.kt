package co.innovaciones.bflow_server.service

import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.services.s3.model.Bucket


interface S3ServiceI {
    fun listBuckets(): MutableList<Bucket>

    fun createBucket(bucketName: String)

    fun deleteBucket(bucketName: String)

    fun uploadObject(bucketName: String, key: String, content: RequestBody)

    fun deleteObject(bucketName: String, key: String)

    fun getTemporaryUrl(defaultBucket: String, key: String?): String?
}
