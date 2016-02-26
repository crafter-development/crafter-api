package com.crafter;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;

import static com.crafter.SystemInit.USERS;
public class TestDB {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		USERS.insert(new BasicDBObject("user_id","44s5"));
	}

}
