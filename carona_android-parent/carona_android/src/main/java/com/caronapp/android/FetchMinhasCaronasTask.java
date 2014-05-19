package com.caronapp.android;

import java.lang.reflect.Type;
import java.util.List;

import android.content.Context;
import android.os.AsyncTask;

import com.caronaapp.util.HttpUtil;
import com.caronaapp.util.JsonUtil;
import com.caronapp.model.Carona;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class FetchMinhasCaronasTask extends AsyncTask<String, String, String> {
		
		private static final Type caronasListType = new TypeToken<List<Carona>>(){}.getType();

		private final Gson gson = JsonUtil.getGson();

		private final MinhasCaronasListActivity minhasCaronasListActivity;
		private final Context mContext;


		protected FetchMinhasCaronasTask(MinhasCaronasListActivity minhasCaronasActivity) {
			this.minhasCaronasListActivity = minhasCaronasActivity;
			this.mContext = minhasCaronasActivity;
		}

		@Override
		protected void onPreExecute() {
			
		}

		@Override
		protected String doInBackground(String... uri) {
			String url = minhasCaronasListActivity.getString(R.string.rest_api_url_minhas) + "100000023518026";
			return HttpUtil.doGET(url);
		}

		@Override
		protected void onPostExecute(String result) {
		
			List<Carona> caronas = gson.fromJson(result, caronasListType);
			
			minhasCaronasListActivity.updateCaronas(caronas);
		}
}
