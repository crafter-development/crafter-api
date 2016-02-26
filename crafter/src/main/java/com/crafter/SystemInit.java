package com.crafter;

import static com.crafter.constants.Constants.*;

import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;

public class SystemInit {
	
	//Initialize DB connection
	
	public static DB SPOTOUT_DB;
	
	public static DBCollection VENUES;
	public static DBCollection USERS;
	public static DBCollection VENUE_FOLLOWERS;
	public static DBCollection USER_FOLLOWERS;
	public static DBCollection NOTIFICATIONS;
	//public static DBCollection NOTIFICATION_SUBSCRIPTIONS;
	public static DBCollection STREAMS;
	public static DBCollection STREAM_LIKES;
	public static DBCollection STREAM_WATCHED;
	public static DBCollection CATEGORIES_MAPPING;
	public static DBCollection LOAD_INFO;

	
	public static ExecutorService NOTIFICATION_SERVICE;

	
	static {
		
		MongoClient mongoClient = null;
		//MongoCredential credential = MongoCredential.createMongoCRCredential("root","crafter1","Crafter789".toCharArray());
		//mongoClient = new MongoClient(
		MongoClientURI uri = new MongoClientURI("mongodb://root:Crafter789@"+DB_HOST+"/?authSource=admin");
		mongoClient = new MongoClient(uri);
		 SPOTOUT_DB = mongoClient.getDB(DB_NAME);		 
		 USERS					= 	SPOTOUT_DB.getCollection(DB_USERS);



	}

}
