package com.caronapp.android;

import java.text.SimpleDateFormat;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.caronapp.model.Carona;
import com.facebook.widget.ProfilePictureView;

public class CaronaAdapterListView extends BaseAdapter {

	private LayoutInflater mInflater;
	private List<Carona> caronas;

	public CaronaAdapterListView(Context context, List<Carona> itens) {
		this.caronas = itens; 
		mInflater = LayoutInflater.from(context);
	}

	public int getCount() { 
		return caronas.size(); 
	} 

	public Carona getItem(int position) { 
		return caronas.get(position); 
	} 

	public long getItemId(int position) { 
		return position; 
	}

	public View getView(int position, View view, ViewGroup parent) { 

		Carona carona = caronas.get(position);

		//infla o layout para podermos pegar as views 
		view = mInflater.inflate(R.layout.item_list, null); 

		TextView nome = ((TextView) view.findViewById(R.id.nome));
		nome.setText(carona.getNome());

		TextView origem = ((TextView) view.findViewById(R.id.origem)); 
		origem.setText(carona.getOrigem());

		TextView destino = ((TextView) view.findViewById(R.id.destino)); 
		destino.setText(carona.getDestino());

		TextView data = ((TextView) view.findViewById(R.id.data));
		data.setText(new SimpleDateFormat("dd/MM/yyyy").format(carona.getData()));

		TextView hora = ((TextView) view.findViewById(R.id.hora));
		hora.setText(new SimpleDateFormat("hh:mm").format(carona.getData()));
		
		ProfilePictureView photo = (ProfilePictureView) view.findViewById(R.id.fotoProfile);
		photo.setProfileId(carona.getMotoristaFacebookId());
		
		return view; 
	} 
}
