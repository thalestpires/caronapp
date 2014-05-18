package com.caronaapp.util;

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

			//			String date = json.getAsJsonObject().get("$date").toString();
//
//			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
//			formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
//
//			try {
//				return formatter.parse(date);
//			} catch (ParseException e) {
//				return null;
//			}
			
			return Calendar.getInstance().getTime();
		}


	}

}
