package com.caronapp.web.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import com.caronapp.web.util.DBUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

@Path("/carona")
public class CaronaREST extends AbstractREST {

	Logger logger = Logger.getLogger(CaronaREST.class);
	
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
