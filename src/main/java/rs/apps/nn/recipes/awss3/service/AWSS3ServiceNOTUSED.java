package rs.apps.nn.recipes.awss3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.exception.SdkServiceException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.core.sync.ResponseTransformer;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
import software.amazon.awssdk.core.sync.ResponseTransformer;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// @Service
public class AWSS3ServiceNOTUSED {
	
	    private  final Logger logger = LoggerFactory.getLogger(AWSS3ServiceNOTUSED.class);

	    @Autowired
	    private S3Client s3Client;

	    @Value("${aws.s3.bucket}")
	    private String bucket;

	    public String upload(String keyName, byte[] attachment) {
	        try {
	            logger.info("Uploading a PDF to S3 - {}", keyName);
	            PutObjectResponse putObjectResult = s3Client.putObject(
	                    PutObjectRequest.builder()
	                            .bucket(bucket)
	                            .key(keyName)
	                            .contentType(MediaType.APPLICATION_PDF.toString())
	                            .contentLength((long) attachment.length)
	                            .build(),
	                    RequestBody.fromByteBuffer(ByteBuffer.wrap(attachment)));
	            final URL reportUrl = s3Client.utilities().getUrl(GetUrlRequest.builder().bucket(bucket).key(keyName).build());
	            logger.info("putObjectResult = " + putObjectResult);
	            logger.info("reportUrl = " + reportUrl);
	            return reportUrl.toString();
	        } catch (SdkServiceException ase) {
	            logger.error("Caught an AmazonServiceException, which " + "means your request made it "
	                    + "to Amazon S3, but was rejected with an error response" + " for some reason.", ase);
	            logger.info("Error Message:    " + ase.getMessage());
	            logger.info("Key:       " + keyName);
	            throw ase;
	        } catch (SdkClientException ace) {
	            logger.error("Caught an AmazonClientException, which " + "means the client encountered "
	                    + "an internal error while trying to " + "communicate with S3, "
	                    + "such as not being able to access the network.", ace);
	            logger.error("Error Message: {}, {}", keyName, ace.getMessage());
	            throw ace;
	        }
	    }
	    
	    public byte[] getObject(String keyName) {
	        try {
	            logger.info("Retrieving file from S3 for key: {}/{}", bucket, keyName);
	            ResponseBytes<GetObjectResponse> s3Object = s3Client.getObject(
	                    GetObjectRequest.builder().bucket(bucket).key(keyName).build(),
	                    ResponseTransformer.toBytes());
	            final byte[] bytes = s3Object.asByteArray();
	            return bytes;
	        } catch (SdkClientException ase) {
	            logger.error("Caught an AmazonServiceException, which " + "means your request made it "
	                    + "to Amazon S3, but was rejected with an error response" + " for some reason: " + keyName, ase);
	            throw ase;
	        } catch (SdkServiceException ace) {
	            logger.error("Caught an AmazonClientException, which " + "means the client encountered "
	                    + "an internal error while trying to " + "communicate with S3, "
	                    + "such as not being able to access the network: " + keyName, ace);
	            throw ace;
	        }
	    }
	    

	    public void transferTo(String keyName, Path destination) {
	    	logger.info("Downloading file from S3 for key: {}/{}", bucket, keyName);
	        var getRequest = GetObjectRequest.builder().bucket(bucket).key(keyName).build();
	        s3Client.getObject(getRequest, ResponseTransformer.toFile(destination));
	    }
	}