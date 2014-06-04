package com.caronapp.android;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.Request.Callback;
import com.facebook.Response;
import com.facebook.Session;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;

public class MenuActivity extends FragmentActivity {

	private LoginFacebookFragment loginFacebookFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);

	    if (savedInstanceState == null) {
	        // Add the fragment on initial activity setup
	    	loginFacebookFragment = new LoginFacebookFragment();
	        getSupportFragmentManager()
	        .beginTransaction()
	        .add(android.R.id.content, loginFacebookFragment)
	        .commit();
	    } else {
	        // Or set the fragment from restored state info
	    	loginFacebookFragment = (LoginFacebookFragment) getSupportFragmentManager()
	        .findFragmentById(android.R.id.content);
	    }
    }
    
    public void darCarona(View v){
    	Session session = Session.getActiveSession();
    	Bundle params = new Bundle();
    	params.putString("fields", "id,name,picture");
    	Request req = new Request(session, "me/mutualfriends/otherUserId", params, HttpMethod.GET, new Callback(){
    	    
    	    public void onCompleted(Response response) {
    	        Response resposta = response;
    	        System.out.println(resposta.getRawResponse());
    	    }
    	});
    	req.executeAsync();
    	Intent intent = new Intent(getApplicationContext(), MinhasCaronasListActivity.class);
        startActivity(intent);
    }
    
    public void pegarCarona(View v){
    	Intent intent = new Intent(getApplicationContext(), CaronaListActivity.class);
        startActivity(intent);
    }

}
