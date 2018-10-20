package com.validate;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Properties;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;

import com.response.GetResponse;

import org.junit.Assert;
import com.common.Constants;


/*
 * This Class will validate the api response through various test cases
 * @version 0.1
 * @author Aditi
 */

public class ValidateApiResponse {
	
	static JSONObject obj;
	static Logger log = Logger.getLogger(ValidateApiResponse.class);
	static Properties prop = new Properties();
	
	
	static {
		log.info("Inside Static Block");
		InputStream input = null;
		String workspace = System.getProperty("user.dir");
		try {
			input = new FileInputStream(workspace + "/src/main/resources/config.properties");
			prop.load(input);
		} catch (FileNotFoundException e) {
			log.error("FileNotFoundException in ValidateApiResponse:", e);
		} catch (IOException e) {
			log.error("IOException in ValidateApiResponse:", e);
		}

	}

	@BeforeClass
	public static void setUp() throws Exception {
		log.info("Inside Setup()");
		GetResponse api = new GetResponse();
		BasicConfigurator.configure();
		log.info("Getting Api Response");
		String ExpectedString = api.GetapiResponse(prop);
		obj = new JSONObject(ExpectedString);
	}

	/*
	 * This Method will Return True 
	 * When defaultGameFrequency is 300000
	 */
	@Test
	public void shouldReturn300000WhendefaultGameFrequencyValidated() throws JSONException {

		JSONObject str = obj.getJSONObject(Constants.BINGOLOBBYINFORESOURCE);
		JSONArray arr = str.getJSONArray(Constants.STREAMS);

		for (int i = 0; i < arr.length(); i++) {
			Long defaultGameFrequency = (Long)arr.getJSONObject(i).get(Constants.DEFAULTGAMEFREQUENCY);
			Assert.assertTrue(defaultGameFrequency==Constants.THREEHUNDREDTHOUSAND_INT);
		}

	}

	
	/*
	 * This Method will Return True 
	 * When STARTTIME is Future Time Stamp
	 */
	
	@Test
	public void shouldReturnTrueWhenstartTimeIsFuture() throws JSONException {

		JSONObject str = obj.getJSONObject(Constants.BINGOLOBBYINFORESOURCE);
		JSONArray arr = str.getJSONArray(Constants.STREAMS);
		Timestamp timestamp;
		for (int i = 0; i < arr.length(); i++) {
			Long startTime = (Long) arr.getJSONObject(i).get(Constants.STARTTIME);
			timestamp = new Timestamp(System.currentTimeMillis());
			Assert.assertTrue(timestamp.getTime() < startTime);
		}

	}
}
