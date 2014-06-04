package com.caronapp.util;

import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class JsonUtil {

	public static Gson getGson() {
		GsonBuilder gsonBuilder = new GsonBuilder();

		gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
		
		return gsonBuilder.create();
	}

	static class DateDeserializer implements JsonDeserializer<Date> {
		
		//FIXME: Acertar o parse do date
		public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
			
			String date = json.toString();
			long milliSeconds= Long.parseLong(date);
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(milliSeconds);
			
			return calendar.getTime();	
			
		}


	}

}
