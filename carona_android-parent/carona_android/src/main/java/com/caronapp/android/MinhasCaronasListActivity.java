package com.caronapp.android;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.caronapp.model.Carona;
import com.google.analytics.tracking.android.EasyTracker;

public class MinhasCaronasListActivity extends ListActivity {

	TextView content;

	List<Carona> caronas;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_minhas_caronas);

		new FetchMinhasCaronasTask(this).execute();
	}

	public void updateCaronas(List<Carona> caronas) {
		this.caronas = caronas;

		CaronaAdapterListView adapter = new CaronaAdapterListView(this, caronas);
		setListAdapter(adapter);

		this.getListView().setLongClickable(true);

	}

	public List<Carona> getCaronas() {
		return caronas;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	  public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    // action with ID action_refresh was selected
	    case R.id.action_refresh:
	    	Intent intent = new Intent(getApplicationContext(), CadastroCaronaActivity.class);
	        startActivity(intent);
	      break;
	    // action with ID action_settings was selected
	    case R.id.action_settings:
	      Toast.makeText(this, "Settings selected", Toast.LENGTH_SHORT)
	          .show();
	      break;
	    default:
	      break;
	    }

	    return true;
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
    
    @Override
    protected void onResume() {
    	super.onResume();
    	updateCaronas(new ArrayList<Carona>());
		new FetchMinhasCaronasTask(this).execute();
    }
    
}
