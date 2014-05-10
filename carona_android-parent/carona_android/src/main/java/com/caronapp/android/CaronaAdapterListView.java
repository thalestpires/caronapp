package com.caronapp.android;

import java.util.List;

import com.caronapp.model.Carona;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CaronaAdapterListView extends BaseAdapter {

	private LayoutInflater mInflater;
	private List<Carona> caronas;

	public CaronaAdapterListView(Context context, List<Carona> itens) {
		// Itens da list
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

		TextView origem = ((TextView) view.findViewById(R.id.origem)); 
		origem.setText(carona.getOrigem());
		
		TextView destino = ((TextView) view.findViewById(R.id.destino)); 
		destino.setText(carona.getDestino());

		//retorna a view com as informações 
		return view; 
	} 
}
