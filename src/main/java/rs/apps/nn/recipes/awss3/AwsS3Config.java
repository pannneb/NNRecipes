package rs.apps.nn.recipes.awss3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

public class AwsS3Config {

	String awsAccessKeyPublic = "AKIAWRJXV2XUC7TGQYEL";
	String awsAccessKeyPrivate = "CNroI675R61EFkv+nk7LFKviXyh9rHccQsYFmbWz";
	

    @Bean(destroyMethod = "close")
    public S3Client s3Client() {
        return  S3Client.builder()
                // .region(Region.of(Region.US_EAST_1))
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(awsAccessKeyPublic, awsAccessKeyPrivate)))
                .build();
    }
}
