package com.caronapp.android;

import java.lang.reflect.Type;
import java.util.List;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.LinearLayout;

import com.caronapp.model.Carona;
import com.caronapp.util.HttpUtil;
import com.caronapp.util.JsonUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

class FetchCaronasTask extends AsyncTask<String, String, String> {

	private static final Type caronasListType = new TypeToken<List<Carona>>() {
	}.getType();

	private final Gson gson = JsonUtil.getGson();

	private final CaronaListActivity caronaListActivity;
	private final Context mContext;
	private View rootView;
	private LinearLayout linlaHeaderProgress;
	protected FetchCaronasTask(CaronaListActivity caronaActivity, View rootView) {
		this.caronaListActivity = caronaActivity;
		this.mContext = caronaActivity;
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
		String url = caronaListActivity.getString(R.string.rest_api_url);
		return HttpUtil.doGET(url);
	}

	@Override
	protected void onPostExecute(String result) {
		  // HIDE THE SPINNER AFTER LOADING FEEDS
	    linlaHeaderProgress.setVisibility(View.GONE);
		List<Carona> caronas = gson.fromJson(result, caronasListType);

		caronaListActivity.updateCaronas(caronas);
	}
}
