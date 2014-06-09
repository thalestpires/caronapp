package com.caronapp.android;

import java.io.StringWriter;

import org.codehaus.jackson.map.ObjectMapper;

import android.os.AsyncTask;
import android.util.Log;

import com.caronapp.model.Carona;
import com.caronapp.util.HttpUtil;

public class InsertCaronaTask extends AsyncTask<String, String, String> {

	private final CadastroCaronaActivity cadastroCaronaActivity;
	
    private InsertCaronaCallback mCallback = null;

	@Override
	protected void onPreExecute() {

	}
	protected InsertCaronaTask(CadastroCaronaActivity cadastroCaronaActivity, InsertCaronaCallback callback) {
		this.cadastroCaronaActivity = cadastroCaronaActivity;
		mCallback = callback;
	}

	@Override
	protected String doInBackground(String... uri) {
		String url = cadastroCaronaActivity.getString(R.string.rest_api_url);
		Carona carona = cadastroCaronaActivity.carona;

		ObjectMapper mapper = new ObjectMapper();
		StringWriter stringWriter = new StringWriter();
		try {
			mapper.writeValue(stringWriter, carona);
		} catch (Exception e) {
			Log.e(null, "Erro ao gerar JSON.", e);
		}

		return HttpUtil.doPOST(url, stringWriter.toString());
	}

	@Override
	protected void onPostExecute(String result) {
		Log.i("PostExecute", result);
		if(mCallback != null){
			mCallback.onInsertCaronaCallback();
		}
	}
	
    public interface InsertCaronaCallback {
        public void onInsertCaronaCallback();
    }
}
