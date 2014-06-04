package com.caronapp.android;

import org.json.JSONException;
import org.json.JSONStringer;

import android.os.AsyncTask;
import android.util.Log;

import com.caronaapp.util.HttpUtil;

public class InsertCaronaTask extends AsyncTask<String, String, String> {

	private final CadastroCaronaActivity cadastroCaronaActivity;

	@Override
	protected void onPreExecute() {

	}
	protected InsertCaronaTask(CadastroCaronaActivity cadastroCaronaActivity) {
		this.cadastroCaronaActivity = cadastroCaronaActivity;
	}

	@Override
	protected String doInBackground(String... uri) {
		String url = cadastroCaronaActivity.getString(R.string.rest_api_url);

		JSONStringer vm = null;

		try {
			vm = new JSONStringer().object()
					.key("nome")
					.value(cadastroCaronaActivity.carona.getNome())
					.key("motoristaFacebookId")
					.value(cadastroCaronaActivity.carona.getMotoristaFacebookId())
					.key("origem")
					.value(cadastroCaronaActivity.carona.getOrigem())
					.key("destino")
					.value(cadastroCaronaActivity.carona.getDestino())
					.key("data")
					.value(cadastroCaronaActivity.carona.getData().getTime())
					.endObject();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return HttpUtil.doPOST(url, vm.toString());

	}

	@Override
	protected void onPostExecute(String result) {
		Log.i("PostExecute", result);
	}
}
