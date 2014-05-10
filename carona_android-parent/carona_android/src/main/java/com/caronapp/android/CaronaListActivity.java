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
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       
        List<Carona> caronas = getCaronas();
        
        CaronaAdapterListView adapter = new CaronaAdapterListView(this, caronas);

        setListAdapter(adapter); 
   }

	private List<Carona> getCaronas() {
		List<Carona> caronas = new ArrayList<Carona>();
        Carona carona = new Carona(12346l, "Motorista", "FacebookId", "Fundao", "Tijcua", new Date());
        caronas.add(carona);
		return caronas;
	}

}
