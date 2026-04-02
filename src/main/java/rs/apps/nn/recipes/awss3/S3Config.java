package rs.apps.nn.recipes.awss3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config {

	private String awsAccessKeyPublic = "AKIAWRJXV2XUC7TGQYEL";
	private String awsAccessKeyPrivate = "CNroI675R61EFkv+nk7LFKviXyh9rHccQsYFmbWz";
	public static String AWS_ACCESS_S3_URL_BASE = "https://nnrecipesbucket.s3.us-east-2.amazonaws.com/";

	@Bean(destroyMethod = "close")
    public S3Client s3Client() {
        return  S3Client.builder()
                .region(Region.of(Region.US_EAST_2.toString()))
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(awsAccessKeyPublic, awsAccessKeyPrivate)))
                .build();
    }
}