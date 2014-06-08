package com.caronapp.android;
 
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.caronapp.util.UserSessionData;
import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.Request.Callback;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.google.analytics.tracking.android.EasyTracker;

public class MenuActivity extends FragmentActivity {
	
	private LoginFacebookFragment loginFacebookFragment;
	private UiLifecycleHelper uiHelper;
	
	private Session.StatusCallback callback = new Session.StatusCallback() {
	    public void call(Session session, SessionState state, Exception exception) {
	        onSessionStateChange(session, state, exception);
	    }
	};
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
    	
    	uiHelper = new UiLifecycleHelper(this, callback);
        uiHelper.onCreate(savedInstanceState);
        
	    if (savedInstanceState == null) {
	        // Add the fragment on initial activity setup
	    	setContentView(R.layout.activity_menu);
	    	Typeface tf = Typeface.createFromAsset(getAssets(),
	                "fonts/FertigoPro.otf");
	        TextView tv = (TextView) findViewById(R.id.fonteFertigo);
	        tv.setTypeface(tf);
	    	
	    	
	    } else {
	        // Or set the fragment from restored state info
	    	loginFacebookFragment = (LoginFacebookFragment) getSupportFragmentManager()
	        .findFragmentById(android.R.id.content);
	    }
    }
    
    private void carregaFontes(){
    	//Fontes
    	Typeface tfFertigo = Typeface.createFromAsset(getAssets(),
                "fonts/FertigoPro.otf");
    	Typeface tfHellvetica = Typeface.createFromAsset(getAssets(),
                 "fonts/Helvetica.ttf");
        TextView tv = (TextView) findViewById(R.id.fonteFertigo);
        tv.setTypeface(tfFertigo);
        
        tv = (TextView) findViewById(R.string.dar_carona);
        tv.setTypeface(tfHellvetica); 
        
        tv = (TextView) findViewById(R.string.pegar_carona);
        tv.setTypeface(tfHellvetica); 
    }

    private void onSessionStateChange(Session session, SessionState state, Exception exception) {
        if (state.isOpened()) {
        	carregaDadosFacebook(session);   
        	toggleBotoes(View.VISIBLE);
        }
        else{
        	UserSessionData.USER_ID = "";
        	UserSessionData.USER_NAME = "";
        	toggleBotoes(View.INVISIBLE);
        }
    }

    private void toggleBotoes(int visibility){
    	((Button)findViewById(R.id.pegarCarona)).setVisibility(visibility);
    	((Button)findViewById(R.id.darCarona)).setVisibility(visibility);
    }
    
	private void carregaDadosFacebook(Session session) {
		Bundle params = new Bundle();
		params.putString("fields", "id,name");
		
		Request req = new Request(session, "me", params, HttpMethod.GET, new Callback(){
		    public void onCompleted(Response response) {
		    	UserSessionData.USER_ID = (String) response.getGraphObject().getProperty("id");
		    	UserSessionData.USER_NAME = (String) response.getGraphObject().getProperty("name"); 
		    }
		});
		req.executeAsync();
	}
    
    public void darCarona(View v){
    	Intent intent = new Intent(getApplicationContext(), MinhasCaronasListActivity.class);
        startActivity(intent);
    }
    
    public void pegarCarona(View v){
    	Intent intent = new Intent(getApplicationContext(), CaronaListActivity.class);
        startActivity(intent);
    }
    
    @Override
    public void onResume() {
        super.onResume();
        uiHelper.onResume();
        
        if (!UserSessionData.USER_NAME.equals("")){
        	toggleBotoes(View.VISIBLE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        uiHelper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onPause() {
        super.onPause();
        uiHelper.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        uiHelper.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        uiHelper.onSaveInstanceState(outState);
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
}
