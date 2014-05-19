package com.caronapp.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }
    
    public void darCarona(View v){
    	Intent intent = new Intent(getApplicationContext(), MinhasCaronasListActivity.class);
        startActivity(intent);
    }
    
    public void pegarCarona(View v){
    	Intent intent = new Intent(getApplicationContext(), CaronaListActivity.class);
        startActivity(intent);
    }

}
