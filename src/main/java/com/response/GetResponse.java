package com.response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.validate.ValidateApiResponse;

/*
 * This Class help to get the response from API url
 * GetapiResponse method will return the Response body as string
 * @version 0.1
 * @author Aditi
 */
public class GetResponse {
	static Logger log = Logger.getLogger(ValidateApiResponse.class);

	public String GetapiResponse(Properties prop) throws IOException {
		log.info("Connecting to URL");
		URL url = new URL(prop.getProperty("url"));
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod(prop.getProperty("GET"));
		StringBuffer result = new StringBuffer();
		int responseCode = con.getResponseCode();
		log.info("Response Code Recieved-->" + responseCode);
		try {
			if (responseCode == 200) {
				System.out.println();
				log.info("Get Response is Successfull");
				BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String line = "";
				while ((line = reader.readLine()) != null) {
					result.append(line);
				}
			}
			return result.toString();

		} catch (Exception ex) {
			log.error("Get Response failed-->" + ex);
			result.append("Get Response Failed");
			return result.toString();
		} finally {
			con.disconnect();
			log.info("Connection Closed");
		}

	}
}
