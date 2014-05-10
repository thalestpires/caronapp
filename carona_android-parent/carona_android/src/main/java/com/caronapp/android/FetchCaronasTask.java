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

class FetchCaronasTask extends AsyncTask<String, String, String> {
	
	private static final Type caronasListType = new TypeToken<List<Carona>>(){}.getType();

	private final Gson gson = JsonUtil.getGson();

	private final CaronaListActivity caronaListActivity;
	private final Context mContext;

	protected FetchCaronasTask(CaronaListActivity caronaActivity) {
		this.caronaListActivity = caronaActivity;
		this.mContext = caronaActivity;
	}

	@Override
	protected void onPreExecute() {
		
	}

	@Override
	protected String doInBackground(String... uri) {
		String url = caronaListActivity.getString(R.string.rest_api_url);
		return HttpUtil.doGET(url);
	}

	@Override
	protected void onPostExecute(String result) {
	
		List<Carona> caronas = gson.fromJson(result, caronasListType);
		
		caronaListActivity.updateCaronas(caronas);
	}
}

