package co.innovaciones.bflow_server.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.presigner.S3Presigner
import java.net.URI

@Configuration
class AwsConfig {

    @Value("\${aws.s3.access-key}")
    private val accessKey: String = ""

    @Value("\${aws.s3.secret-key}")
    private val secretKey: String = ""

    @Value("\${aws.s3.region}")
    private val region: String = ""

    @Value("\${aws.s3.endpoint-url}")
    private val endpointUrl: String = ""

    @Bean
    fun s3Client(): S3Client {
        println("Creating s3Client with ${endpointUrl}")
        return S3Client.builder()
            .region(Region.US_EAST_1)
            .endpointOverride(URI.create(endpointUrl))
            .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey)))
            .build()
    }

    @Bean
    fun s3Presigner(s3Client: S3Client): S3Presigner {
        return S3Presigner.builder()
            .region(Region.of(region))
            .endpointOverride(URI.create(endpointUrl))
            .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey)))
            .build()
    }
}
