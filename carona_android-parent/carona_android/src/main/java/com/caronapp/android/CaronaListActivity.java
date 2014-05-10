package com.caronapp.android;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
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
	}

}
