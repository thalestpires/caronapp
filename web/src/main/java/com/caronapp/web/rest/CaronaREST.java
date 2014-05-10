package com.caronapp.web.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;
import org.json.JSONException;

import com.caronapp.web.util.DBUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

@Path("/carona")
public class CaronaREST extends AbstractREST {

	Logger logger = Logger.getLogger(CaronaREST.class);
	
	@GET
	@Produces(JSON_UTF8)
	public String findCaronas() throws JSONException {
		DBCollection collection = DBUtil.getInstance().getDatabase().getCollection(DBUtil.COLLECTION_CARONAS);
		BasicDBObject query = new BasicDBObject();
		DBCursor cursor = collection.find(query);
		return prettySerialize(cursor);
	}
	
	@POST
	@Produces(JSON_UTF8)
	public void guardaCarona(String carona) throws JSONException {
		DBCollection collection = DBUtil.getInstance().getDatabase().getCollection(DBUtil.COLLECTION_CARONAS);
		
		DBObject dbObject = (DBObject) JSON.parse(carona);
		
		collection.insert(dbObject);
	}
	
	//TODO fazer query de caronas
	@GET
	@Path("{caronaID}")
	@Produces(JSON_UTF8)
	public String findCaronas(@PathParam("userID") Long userID) {
		DBCollection collection = DBUtil.getInstance().getDatabase().getCollection(DBUtil.COLLECTION_CARONAS);
		BasicDBObject query = new BasicDBObject();
		//TODO fazer query de caronas
		DBCursor cursor = collection.find(query);
		return serialize(cursor);
	}
	
	@GET
	@Path("/minha/{meuID}")
	@Produces(JSON_UTF8)
	public String findMinhasCaronas(@PathParam("meuID") Long userID) {
		DBCollection collection = DBUtil.getInstance().getDatabase().getCollection(DBUtil.COLLECTION_CARONAS);
		BasicDBObject query = new BasicDBObject();
		//TODO fazer query de caronas
		DBCursor cursor = collection.find(query);
		return serialize(cursor);
	}
	

}
