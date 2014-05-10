package com.caronapp.android;

import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.caronaapp.util.UserFacebookSession;
import com.caronapp.model.Carona;

public class CadastroCaronaActivity extends Activity implements OnClickListener {

	private Carona carona = new Carona();
	
	private TextView origem;
	private TextView destino;
	private Button cadastrarButton;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_carona);
        
        origem = (TextView)findViewById(R.id.editOrigem);
        destino = (TextView)findViewById(R.id.editDestino);
        
        cadastrarButton = (Button)findViewById(R.id.ok);
        cadastrarButton.setOnClickListener(this);
        
        carona.setMotoristaFacebookId(UserFacebookSession.USER_ID);
        carona.setNome(UserFacebookSession.USER_NOME);

        carona.setData(new Date());
   }

	public void onClick(View v) {
		if (v.equals(cadastrarButton)) {
			carona.setOrigem(origem.getText().toString());
			carona.setDestino(destino.getText().toString());
			Toast.makeText(this, carona.toString(), Toast.LENGTH_SHORT).show();
		}
	}

}

