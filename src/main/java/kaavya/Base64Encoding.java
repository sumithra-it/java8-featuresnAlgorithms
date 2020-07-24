package kaavya;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.OutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Base64Encoding {

	public static void main(String args[]) {
		
		String str = "This is a secret. Encode me";
		try{
			String encodedStr = Base64.getEncoder().encodeToString(str.getBytes("utf-8"));
			System.out.println("Encoded String: " +  encodedStr);
			
			//Decode
			byte[] encodedBytes = Base64.getDecoder().decode(encodedStr);		
			System.out.println("Original String is:" + new String(encodedBytes, "utf-8"));
			
			
			String encodedString = "dXNlcm5hbWU6cGFzc3dvcmQ=";
			Base64.Decoder decoder = Base64.getDecoder();
			byte[] decodedByteArray = decoder.decode(encodedString);
			//Verify the decoded string
			System.out.println(new String(decodedByteArray));
		} catch(UnsupportedEncodingException e) {
			System.out.println("Exception: " + e);
		}
		
		//MIME type
		Path originalPath = Paths.get("/Users/sr/docker/kaavyaApp", "app.js");
		Path targetPath = Paths.get("/Users/sr/docker/kaavyaApp", "test.txt");
		Base64.Encoder mimeEncoder = Base64.getMimeEncoder();
		try(OutputStream output = Files.newOutputStream(targetPath)){

			//Copy the encoded file content to target file
		    Files.copy(originalPath, mimeEncoder.wrap(output));
		    
		    //Or simply use the encoded output stream
		//    OutputStream encodedStream = mimeEncoder.wrap(output);
		} catch(IOException e) {
			System.out.println("Exception: " + e);			
		}
		

	}
}
