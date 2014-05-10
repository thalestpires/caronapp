package com.caronapp.web.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;
import org.json.JSONException;

import com.caronapp.model.Carona;
import com.caronapp.web.util.DBUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

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
	
	//TODO fazer query de caronas
	@GET
	@Path("{userID}")
	@Produces(JSON_UTF8)
	public String findCaronas(@PathParam("userID") Long userID) {
		DBCollection collection = DBUtil.getInstance().getDatabase().getCollection(DBUtil.COLLECTION_CARONAS);
		BasicDBObject query = new BasicDBObject();
		//TODO fazer query de caronas
		DBCursor cursor = collection.find(query);
		return serialize(cursor);
	}
}
