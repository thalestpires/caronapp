package com.caronapp.android;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.TextView;

import com.caronapp.model.Carona;

public class CaronaListActivity extends ListActivity {

	TextView content;

	List<Carona> caronas;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		new FetchCaronasTask(this).execute();
	}

	public void updateCaronas(List<Carona> caronas){
		this.caronas = caronas;

		CaronaAdapterListView adapter = new CaronaAdapterListView(this, caronas);
		setListAdapter(adapter); 

		this.getListView().setLongClickable(true);
		this.getListView().setOnItemLongClickListener(new OnItemLongClickListener() {
			public boolean onItemLongClick(AdapterView<?> parent, View v, int position, long id) {

				startActivity(new Intent(Intent.ACTION_VIEW, 
						Uri.parse("fb://messaging/" + getCaronas().get(position).getMotoristaFacebookId() )));

				return true;
			}
		});
	}

	public List<Carona> getCaronas(){
		return caronas;
	}
}
