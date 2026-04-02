package rs.apps.nn.recipes.awss3.api.v2;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import rs.apps.nn.recipes.common.Utils;
import rs.apps.nn.recipes.domain.FileData;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.exception.SdkServiceException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

@Slf4j
@PropertySource("classpath:aws_nn.properties")
@Service
public class S3Service {

	@Autowired
	private S3Client s3Client;

	@Value("${aws.s3.bucket}")
	private String bucket;

	public String upload(String keyName, byte[] attachment) throws IOException {
		try {

			// String filePath = "/Users/Nebojsa/Documents/awsS3Dummy/AwsDummyFile.txt";
			// String filePath1 = "/Users/Nebojsa/Documents/awsS3Dummy/MajicaV2.png";
			String filePath2 = "/Users/Nebojsa/Documents/awsS3Dummy/IMGPejzaz.JPG";
			// File f = new File(filePath);
			String fileName = "IMGPejzaz.JPG";
			InputStream targetStream = new FileInputStream(filePath2);
			uploadFile(fileName, targetStream);
			System.out.println("File name = " + fileName);
			log.debug("The file has been uploaded successfully");
// 			message = "The file has been uploaded successfully";
//
//	        client.putObject(request,
//	                RequestBody.fromInputStream(inputStream, inputStream.available()));
//			
//			log.info("Uploading a PDF to S3 - {}", keyName);
//			PutObjectResponse putObjectResult = s3Client
//					.putObject(
//							PutObjectRequest.builder().bucket(bucket).key(keyName)
//									.contentType(MediaType.APPLICATION_PDF.toString())
//									.contentLength((long) attachment.length).build(),
//							RequestBody.fromByteBuffer(ByteBuffer.wrap(attachment)));
//			final URL reportUrl = s3Client.utilities()
//					.getUrl(GetUrlRequest.builder().bucket(bucket).key(keyName).build());
//			log.info("putObjectResult = " + putObjectResult);
//			log.info("reportUrl = " + reportUrl);
//			return reportUrl.toString();
			return "OK";
		} catch (SdkServiceException ase) {
			log.error("Caught an AmazonServiceException, which " + "means your request made it "
					+ "to Amazon S3, but was rejected with an error response" + " for some reason.", ase);
			log.info("Error Message:    " + ase.getMessage());
			log.info("Key:       " + keyName);
			throw ase;
		} catch (SdkClientException ace) {
			log.error("Caught an AmazonClientException, which " + "means the client encountered "
					+ "an internal error while trying to " + "communicate with S3, "
					+ "such as not being able to access the network.", ace);
			log.error("Error Message: {}, {}", keyName, ace.getMessage());
			throw ace;
		}
	}

	private void uploadFile(String fileName, InputStream inputStream)
			throws S3Exception, AwsServiceException, SdkClientException, IOException {

		PutObjectRequest request = PutObjectRequest.builder().bucket(bucket).key(fileName).acl("public-read").build();
		s3Client.putObject(request, RequestBody.fromInputStream(inputStream, inputStream.available()));
	}
	
	//	private uploadFile () {
	//		 S3Client client = S3Client.builder().build();
	//         
	//	        PutObjectRequest request = PutObjectRequest.builder()
	//	                                .bucket(BUCKET)
	//	                                .key(fileName)
	//	                                .acl("public-read")
	//	                                .build();
	//	        client.putObject(request,
	//	                RequestBody.fromInputStream(inputStream, inputStream.available()));
	//	}

	public String uploadAWSS3(MultipartFile file) throws IOException {
		try {
			// String fileName = org.springframework.util.StringUtils.cleanPath(file.getOriginalFilename());
			String awsName = Utils.getAlphaNumericString(25).concat(".").concat(FilenameUtils.getExtension(file.getOriginalFilename()));
			
			// String fileName = org.springframework.util.StringUtils.cleanPath(file.getOriginalFilename());
			
			//FileData fileData = new FileData(fileName, file.getContentType(), file.getBytes());
			//fileData.setId(imageId);
			
			//String filePath2 = "/Users/Nebojsa/Documents/awsS3Dummy/IMGPejzaz.JPG";
			// File f = new File(filePath);
			// String fileName = "IMGPejzaz.JPG";
		//	InputStream targetStream = new FileInputStream(filePath2);
			uploadFileAwsS3(file, awsName);
			System.out.println("File name = " + awsName);
			log.debug("The file has been uploaded successfully");

			return awsName;
		} catch (SdkServiceException ase) {
			log.error("Caught an AmazonServiceException, which " + "means your request made it "
					+ "to Amazon S3, but was rejected with an error response" + " for some reason.", ase);
			log.info("Error Message:    " + ase.getMessage());
			//log.info("Key:       " + keyName);
			throw ase;
		} catch (SdkClientException ace) {
			log.error("Caught an AmazonClientException, which " + "means the client encountered "
					+ "an internal error while trying to " + "communicate with S3, "
					+ "such as not being able to access the network.", ace);
			//log.error("Error Message: {}, {}", keyName, ace.getMessage());
			throw ace;
		}
	}

	private void uploadFileAwsS3(MultipartFile file, String fileName)
			throws S3Exception, AwsServiceException, SdkClientException, IOException {

		PutObjectRequest request = PutObjectRequest.builder().bucket(bucket).key(fileName).acl("public-read").build();

		// MultipartFile file1 = file;
		BufferedImage image = ImageIO.read(file.getInputStream());
		BufferedImage imgResized = Scalr.resize(image, 700);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(imgResized, FilenameUtils.getExtension(file.getOriginalFilename()), baos);
        byte[] bytes = baos.toByteArray();
		s3Client.putObject(request, RequestBody.fromBytes(bytes));
	}

}
