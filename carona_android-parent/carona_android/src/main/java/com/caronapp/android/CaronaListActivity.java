package com.caronapp.android;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.ContentProviderOperation;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.Contacts.Data;
import android.provider.ContactsContract.RawContacts;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.caronapp.model.Carona;
import com.caronapp.util.AndroidUtil;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.MapBuilder;

public class CaronaListActivity extends ListActivity implements OnCheckedChangeListener{

	TextView content;

	List<Carona> caronas;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		carregaFontes();
		
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
				
				final String nome = getCaronas().get(position).getNome();
				final String telefone = getCaronas().get(position).getTelefone();
				if(!AndroidUtil.contactExists(getApplicationContext(), telefone)){
					adicionarContato(nome, telefone);  					
				}
				Handler delayHandler= new Handler();
				Runnable r = new Runnable()
				{
					public void run() {
						Uri uri = Uri.parse("smsto:" + telefone);
						Intent i = new Intent(Intent.ACTION_SENDTO, uri);
						i.setPackage("com.whatsapp");  
						startActivity(Intent.createChooser(i, ""));
					}
				};
				
				showLoadingToast();
				
				delayHandler.postDelayed(r, 5000);

				return true;
			}
		});
	}

	protected void showLoadingToast() {
		Toast.makeText(this, R.string.carregando, Toast.LENGTH_LONG).show();
		
	}

	private void adicionarContato(String name, String phoneNumber){
		ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();

		ops.add(ContentProviderOperation.newInsert(RawContacts.CONTENT_URI)
				.withValue(RawContacts.ACCOUNT_TYPE, null)
				.withValue(RawContacts.ACCOUNT_NAME, null).build());

		ops.add(ContentProviderOperation
				.newInsert(ContactsContract.Data.CONTENT_URI)
				.withValueBackReference(Data.RAW_CONTACT_ID,0)
				.withValue(Data.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE)
				.withValue(StructuredName.DISPLAY_NAME, name) // Name of the person
				.build());

		ops.add(ContentProviderOperation
				.newInsert(ContactsContract.Data.CONTENT_URI)
				.withValueBackReference(
						ContactsContract.Data.RAW_CONTACT_ID,   0)
						.withValue(Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE)
						.withValue(Phone.NUMBER, phoneNumber) // Number of the person
						.withValue(Phone.TYPE, Phone.TYPE_MOBILE).build()); // Type of mobile number                    
		try
		{
			getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
		}
		catch (RemoteException e)
		{ 
		}
		catch (OperationApplicationException e) 
		{
		}   
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
	private void carregaFontes(){
		//TODO ver como carrego uma fonte para toda activity
		Typeface tf = Typeface.createFromAsset(getAssets(),
                "fonts/Helvetica.ttf");
        TextView tv = (TextView) findViewById(R.id.switchAmigos);
        tv.setTypeface(tf);
        
	}
}
