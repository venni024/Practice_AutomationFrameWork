package dr.ert;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Formatter;
import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;




public class WrfWebServiceTesting {
	// read this from odr.cm.settings.hmac.yaml file 'hmac' is the key name
	// TODO get it from config
	private static final String hmac_value = "680fe0270b92fe7a4634e1a0f2ce0fce030685cd4c28436aaae9d8c0db897b51";

	// name space name can be configured based on the environment and tenant we
	// use
	// TODO get it from config
	static String nameSpace = "ugbu-odr--odrqa--se--qa0--1-0";

	// Define the base URL
	static String baseUrl = "http://odr-jws-svc." + nameSpace + ":8080/odr/";
	
	public static void main(String args[]) {
		
		//getStorms("Completed");
		updateStorm(80912312, "In-Progress", "User3");
	}

	public static HttpResponse getStorms(String wrfStatus) {

		// Encode required parameters
		String envName = "odr";
		String encodedEnvName = "";
		String encodedWrfStatus = "";
		
		int transactionID = getRandomNumber(); //we use other library also, to generate unique number
		
		long time = new Date().getTime();
		try {
			encodedEnvName = URLEncoder.encode(envName, "UTF-8");
			encodedWrfStatus = URLEncoder.encode(wrfStatus, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String subUrlPath = "wrf/getStorms" + "?envName=" + encodedEnvName + "&transactionID=" + transactionID
				+ "&time=" + time + "&wrfStatus=" + encodedWrfStatus;

		String signature = getHMACKey(hmac_value, subUrlPath);
		System.out.println("signature:***" + signature);
		
		System.out.println("get url params:***" + "?envName=" + encodedEnvName + "&transactionID=" + transactionID
				+ "&time=" + time+ "&wrfStatus=" + encodedWrfStatus + "&signature=" + signature);
		// Create URL with all parameters
		String urlWithParams = baseUrl + subUrlPath + "&signature=" + signature;

		System.out.println("url with params: " + urlWithParams);

		HttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(urlWithParams);

		// Set headers
		httpGet.addHeader("Content-Type", "application/x-www-form-urlencoded");

		HttpResponse response = null;

		try {
			System.out.println("calling get******************************");
			response = httpClient.execute(httpGet);

			// Check the status code, and handle errors if needed
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == 200) { // 200 OK
				// Extract the response content as a string
				String responseBody = EntityUtils.toString(response.getEntity());

				// Now, responseBody contains the response body as a string
				System.out.println("response body: " + responseBody);
			} else {
				// Handle error cases based on the status code
				System.out.println("HTTP Error: " + statusCode);
			}
		} catch (ClientProtocolException e) {
			System.out.println("exception: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("exception: " + e.getMessage());
			e.printStackTrace();
		}
		return response;
	}

	public static void updateStorm(int modelId, String wrfStatus, String userID) {

		// Encode required parameters
		String envName = "odr";
		String encodedEnvName = "";
		String encodedwrfStatus = "";
		String encodeduserID = "";

		int transactionID = getRandomNumber(); //we use other library also, to generate unique number
		long time = new Date().getTime();
		try {
			encodedEnvName = URLEncoder.encode(envName, "UTF-8");
			encodedwrfStatus = URLEncoder.encode(wrfStatus, "UTF-8");
			encodeduserID = URLEncoder.encode(userID, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String subUrlPath = "wrf/updateStorm" + "?envName=" + encodedEnvName + "&transactionID=" + transactionID
				+ "&time=" + time + "&modelId=" + modelId + "&wrfStatus=" + encodedwrfStatus + "&userID="
				+ encodeduserID;

		String signature = getHMACKey(hmac_value, subUrlPath);
		System.out.println("signature:***" + signature);
		
		System.out.println("post body: " + "envName=" + encodedEnvName + "&transactionID=" + transactionID
				+ "&time=" + time + "&modelId=" + modelId + "&wrfStatus=" + encodedwrfStatus + "&userID="
				+ encodeduserID+ "&signature=" + signature);

		// Create URL with all parameters
		String urlWithParams = baseUrl + subUrlPath + "&signature=" + signature;

		System.out.println("url with params: " + urlWithParams);

		HttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(urlWithParams);

		// Set headers
		// httpPost.addHeader("Authorization", "Bearer YourAccessToken");
		httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");

		try {
			// Set the request body if required
			// String requestBody = "{\"key1\":\"value1\",\"key2\":\"value2\"}";
			// httpPost.setEntity(new StringEntity(requestBody));

			HttpResponse response = httpClient.execute(httpPost);
			// Check the status code, and handle errors if needed
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == 200) { // 200 OK
				// Extract the response content as a string
				String responseBody = EntityUtils.toString(response.getEntity());

				// Now, responseBody contains the response body as a string
				System.out.println("response body: " + responseBody);
			} else {
				// Handle error cases based on the status code
				System.out.println("HTTP Error: " + statusCode);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static int getRandomNumber() {
		Random random = new Random();

		// Generate a random 6-digit integer
		int min = 100000; // Smallest 6-digit number
		int max = 999999; // Largest 6-digit number
		int randomSixDigitInt = random.nextInt((max - min) + 1) + min;
		return randomSixDigitInt;
	}

	/*
	 * private String getHMACKey(String key) {
	 * 
	 * /// Convert the starting key to a SecretKey object SecretKey startingKey
	 * = new SecretKeySpec(key.getBytes(), "HmacSHA256"); Mac hmac = null; try {
	 * // Initialize the HMAC with the starting key hmac =
	 * Mac.getInstance("HmacSHA256"); // You can change the // algorithm if
	 * needed hmac.init(startingKey); } catch (Exception e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } String hash =
	 * Base64.getEncoder().encodeToString(hmac.doFinal());
	 * 
	 * System.out.println("Generated HMAC Key (Hexadecimal): " + hash); return
	 * hash; }
	 */

	private static String getHMACKey(String key1, String message1) {
		// Define your key and message
		String key = key1;
		String message = message1;
		String digestMode = "HmacSHA256"; // You can change the algorithm as
											// needed

		// Convert the key to a SecretKey object
		SecretKey secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), digestMode);

		// Initialize the HMAC with the SecretKey and digest mode
		Mac hmac = null;
		try {
			hmac = Mac.getInstance(digestMode);
			hmac.init(secretKey);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Calculate the HMAC hash for the message
		byte[] hmacBytes = hmac.doFinal(message.getBytes(StandardCharsets.UTF_8));

		// Convert the bytes to a hexadecimal string
		String signature = bytesToHex(hmacBytes);

		System.out.println("HMAC Signature: " + signature);
		return signature;
	}

	// Helper method to convert bytes to a hexadecimal string
	private static String bytesToHex(byte[] bytes) {
		Formatter formatter = new Formatter();
		for (byte b : bytes) {
			formatter.format("%02x", b);
		}
		return formatter.toString();
	}

}
