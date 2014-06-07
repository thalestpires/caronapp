	package com.caronapp.android;

import java.lang.reflect.Type;
import java.util.List;

import android.os.AsyncTask;

import com.caronapp.model.Carona;
import com.caronapp.util.HttpUtil;
import com.caronapp.util.JsonUtil;
import com.caronapp.util.UserSessionData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class FetchMinhasCaronasTask extends AsyncTask<String, String, String> {
		
		private static final Type caronasListType = new TypeToken<List<Carona>>(){}.getType();

		private final Gson gson = JsonUtil.getGson();

		private final MinhasCaronasListActivity minhasCaronasListActivity;

		protected FetchMinhasCaronasTask(MinhasCaronasListActivity minhasCaronasActivity) {
			this.minhasCaronasListActivity = minhasCaronasActivity;
		}

		@Override
		protected void onPreExecute() {
		}

		@Override
		protected String doInBackground(String... uri) {
			String url = minhasCaronasListActivity.getString(R.string.rest_api_url_minhas) + UserSessionData.USER_ID;
			return HttpUtil.doGET(url);
		}

		@Override
		protected void onPostExecute(String result) {
			List<Carona> caronas = gson.fromJson(result, caronasListType);
			
			minhasCaronasListActivity.updateCaronas(caronas);
		}
}
