package com.caronapp.web;

import java.util.Calendar;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.caronapp.model.Carona;
import com.caronapp.web.util.DBUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

public class Bootstrap extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		
		DBCollection collection = DBUtil.getInstance().getDatabase().getCollection(DBUtil.COLLECTION_CARONAS);
		
		collection.drop();
		
		Carona carona1 = new Carona(1,"100000075065275",
									"Luiz Felipe" ,"Barra","Fundão", 
									Calendar.getInstance().getTime());
		collection.insert(RetornaQuery(carona1));
		
		Carona carona2 = new Carona(2,"100000075065275",
				"Luiz Felipe" ,"Barra","Barra Music", 
				Calendar.getInstance().getTime());
		
		collection.insert(RetornaQuery(carona2));
		
		Carona carona3 = new Carona(3,"100000075065275",
				"Luiz Felipe" ,"Via Parque","Botafogo", 
				Calendar.getInstance().getTime());
		
		collection.insert(RetornaQuery(carona3));
	}

	private BasicDBObject RetornaQuery(Carona carona) {
		BasicDBObject query = new BasicDBObject();

		query.put("_id" ,carona.getId());
		query.put("motoristaFacebookId" ,carona.getMotoristaFacebookId());
		query.put("nome" ,carona.getNome());
		query.put("origem" ,carona.getOrigem());
		query.put("destino" ,carona.getDestino());
		query.put("data" ,carona.getData());
		
		return query;
	}

	@Override
	public void destroy() {
		
	}
	
}
