package com.crafter.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.mongodb.BasicDBObject;

import org.bson.types.ObjectId;

import static com.crafter.constants.Constants.*;
import static com.crafter.SystemInit.*;


public class Util {	


	static ObjectMapper objectMapper = new ObjectMapper();

	
	 	

	
	


	
	public static String trimQ(String s){
		
		if(s.startsWith("\"") && s.endsWith("\"")){
			s = s.substring(1, s.length()-1);
		}
		return s;
	}
	
	
	
	public static String apiResponse(String status,String message){
		
		return "{ \"status\":\""+status+"\",\"message\": \""+message+"\"}";
		
	}
	
	public static String apiResponse(String status,String message, String data){
		
		return "{ \"status\":\""+status+"\",\"message\": \""+message+"\",\"data\": "+data+"}";
		
	}
	
/*	public static String apiResponse(String status,String message, JsonNode data){
		
		return "{ \"status\":\""+status+"\",\"message\": \""+message+"\",\"data\": "+data+"}";
		
	}
	*/
	public static String apiResponse(String status,String message, ArrayNode arrData, int pageOffset, int pageLimit) throws IOException{
		
		if(pageOffset == 0 && pageLimit == 0){
			pageLimit = 20; //default page limit
		}
		
		String data = null;
		if(pageOffset < 0){
			return apiResponse("error", "page_offset should not be negative");
		}
		if(pageLimit < 1){
			return apiResponse("error", "page_limit should be greater than 0");
		}
		
		 if(pageOffset < arrData.size()){
			ArrayNode resultArray = objectMapper.readValue("[]", ArrayNode.class);
			int startIndex = pageOffset;
			int endIndex = (pageOffset + pageLimit) < arrData.size() ? pageOffset + pageLimit : arrData.size();
			
			for(int i = startIndex; i< endIndex; i++){
				resultArray.add(arrData.get(i));
			}
	
			data = objectMapper.writeValueAsString(resultArray);
		 }
		return "{ \"status\":\""+status+"\",\"message\": \""+message+"\",\"data\": "+data+",\"page_offset\":"+pageOffset+",\"page_limit\":"+pageLimit+" }";
		
	}
	

	
/*	public static String generateGUID(String objectType){
		BasicDBObject doc = new BasicDBObject( "object_type", objectType );
		GUID.insert( doc );
		ObjectId id = (ObjectId)doc.get( "_id" );
		return id.toString();
		
	}*/
	
	public static String epochToUTC(long date){
		 DateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	        format.setTimeZone(TimeZone.getTimeZone("UTC"));
	       return format.format(date);
	}
	
}
