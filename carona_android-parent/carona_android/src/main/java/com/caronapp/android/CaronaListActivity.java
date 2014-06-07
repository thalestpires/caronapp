package com.caronapp.android;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.caronapp.model.Carona;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.MapBuilder;

public class CaronaListActivity extends ListActivity implements OnCheckedChangeListener{

	TextView content;

	List<Carona> caronas;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Switch switchFiltraAmigos = (Switch) findViewById(R.id.switchAmigos);
		switchFiltraAmigos.setOnCheckedChangeListener(this);
		
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
	
	@Override
    protected void onStop() {
    	super.onStop();
    	EasyTracker.getInstance(this).activityStop(this);
    }
    
    @Override
    protected void onStart() {
    	EasyTracker.getInstance(this).activityStart(this);
    	super.onStart();
    }
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if (isChecked) {
			EasyTracker.getInstance(this).send(
				MapBuilder.createEvent("fake_feature", "only_friends", null, null).build()
			);
			Toast.makeText(this, R.string.disponivelBreve, Toast.LENGTH_SHORT).show();
		}
		
	}
}
