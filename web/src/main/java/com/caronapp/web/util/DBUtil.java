package com.caronapp.web.util;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class DBUtil {

	private static String DB_HOST     = "10.10.13.191";
	private static int DB_PORT        = 27017;
	private static String DB_USERNAME = "";
	private static String DB_PASSWORD = "";
	private static String DB_NAME     = "mydb";
	
	public static final String COLLECTION_CARONAS = "caronas";

	private static DBUtil instance = new DBUtil();
	private DB database;
	
	public static DBUtil getInstance() {
		if (instance.getDatabase() == null) {
			try {
				MongoClient mongoClient = new MongoClient(DB_HOST, DB_PORT);
				instance.setDatabase(mongoClient.getDB( DB_NAME ));
				instance.getDatabase().authenticate(DB_USERNAME, DB_PASSWORD.toCharArray());
			} catch (UnknownHostException e) {
				throw new IllegalStateException("Host do banco não encontrado.", e);
			}
		}
		return instance;
	}

	public DB getDatabase() {
		return database;
	}
	
	private void setDatabase(DB database) {
		this.database = database;
	}

	public static void main(String[] args) {
		DB database = DBUtil.getInstance().getDatabase();
		System.out.println(database);
	}
}
