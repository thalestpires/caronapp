	package com.caronapp.android;

import java.lang.reflect.Type;
import java.util.List;

import android.os.AsyncTask;
import android.view.View;
import android.widget.LinearLayout;

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
		private View rootView;
		private LinearLayout linlaHeaderProgress;
		protected FetchMinhasCaronasTask(MinhasCaronasListActivity minhasCaronasActivity, View rootView) {
			this.minhasCaronasListActivity = minhasCaronasActivity;
			this.rootView = rootView;
			this.linlaHeaderProgress = (LinearLayout) rootView.findViewById(R.id.linlaHeaderProgress);
		}

		@Override
		protected void onPreExecute() {
			 // SHOW THE SPINNER WHILE LOADING FEEDS
		    linlaHeaderProgress.setVisibility(View.VISIBLE);
		}

		@Override
		protected String doInBackground(String... uri) {
			String url = minhasCaronasListActivity.getString(R.string.rest_api_url_minhas) + UserSessionData.USER_ID;
			return HttpUtil.doGET(url);
		}

		@Override
		protected void onPostExecute(String result) {
		  // HIDE THE SPINNER AFTER LOADING FEEDS
		    linlaHeaderProgress.setVisibility(View.GONE);
			List<Carona> caronas = gson.fromJson(result, caronasListType);
			
			minhasCaronasListActivity.updateCaronas(caronas);
		}
}
