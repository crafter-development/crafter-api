package com.crafter.api.v1.endpoints;

import static com.crafter.SystemInit.USERS;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.crafter.api.v1.models.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import static com.crafter.util.Util.*;

/**
* This class implements UserAPI
*
* @author  Sekar SN
* 
*/

@Path("user")
@Api(description = "User API", value = "user",position = 1)
public class UserApi {
	static ObjectMapper objectMapper = new ObjectMapper();	

	@POST
	@Path("/signup")
	@Consumes("application/json")
	@ApiOperation(value = "New user signUp", response = String.class,position = 10)
	
	public String signup(User newuser) throws Exception{		
		ObjectNode user = objectMapper.readValue("{}", ObjectNode.class);		
		
/*		if("".equals(newuser.access_token)){
			return apiResponse("error","access_token is empty");
		}*/
		if("".equals(newuser.user_id)){
			return apiResponse("error","user_id is empty");
		}

		if("".equals(newuser.user_name)){
			return apiResponse("error","name is empty");
		}
		if("".equals(newuser.email)){
			return apiResponse("error","email is empty");
		}		
	
		try{
			DBObject dbnewuser = (DBObject)JSON.parse(new ObjectMapper().writeValueAsString(newuser));			
			USERS.insert(dbnewuser);
			user.put("user_id", newuser.user_id);
			user.put("user_name", newuser.user_name);
			user.put("profile_picture",newuser.profile_picture);

		}catch(com.mongodb.MongoException me){
			if(me.getClass().toString().contains("com.mongodb.DuplicateKeyException"))
				return apiResponse("error","User already exists");
			else
				throw me;
		}catch(Exception e){
			DBObject queryUser =  USERS.findOne(new BasicDBObject("user_id", newuser.user_id));
			USERS.remove(queryUser);
			return apiResponse("error",e.getMessage());
		}
		
		return  apiResponse("ok","signup success",objectMapper.writeValueAsString(user));
	}
}