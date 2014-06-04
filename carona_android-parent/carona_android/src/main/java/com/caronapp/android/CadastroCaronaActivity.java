package com.caronapp.android;

import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.caronaapp.util.UserFacebookSession;
import com.caronapp.model.Carona;

public class CadastroCaronaActivity extends Activity implements OnClickListener {

	public Carona carona = new Carona();

	private TextView origem;
	private TextView destino;
	private Button cadastrarButton;

	// Widget GUI
	Button btnCalendar, btnTimePicker;
	EditText txtDate, txtTime;
	int diaMarcado, mesMarcado, anoMarcado, minutoMarcado, HoraMarcada;
	// Variable for storing current date and time
	private int mYear, mMonth, mDay, mHour, mMinute;

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

		//carona.setData(new Date());

		//Parte dos Pickers

		btnCalendar = (Button) findViewById(R.id.btnCalendar);
		btnTimePicker = (Button) findViewById(R.id.btnTimePicker);

		txtDate = (EditText) findViewById(R.id.txtDate);
		txtTime = (EditText) findViewById(R.id.txtTime);

		btnCalendar.setOnClickListener(this);
		btnTimePicker.setOnClickListener(this);
	}

	public void onClick(View v) {
		if (v.equals(cadastrarButton)) {
			carona.setOrigem(origem.getText().toString());
			carona.setDestino(destino.getText().toString());

			if(carona.getOrigem().equals("")){
				Toast.makeText(this, "Por favor preencha uma origem", Toast.LENGTH_SHORT).show();
			}else if(carona.getDestino().equals("")){
				Toast.makeText(this, "Por favor preencha uma destino", Toast.LENGTH_SHORT).show();
			}else if(txtDate.getText().toString().matches("")){
				Toast.makeText(this, "Por favor preencha uma data", Toast.LENGTH_SHORT).show();				
			}else if(txtTime.getText().toString().matches("")){
				Toast.makeText(this, "Por favor preencha uma hora", Toast.LENGTH_SHORT).show();				
			}
			else{
				Calendar cal = Calendar.getInstance();
				cal.set(Calendar.YEAR, anoMarcado);
				cal.set(Calendar.MONTH, mesMarcado);
				cal.set(Calendar.DAY_OF_MONTH, diaMarcado);
				cal.set(Calendar.HOUR_OF_DAY, HoraMarcada);
				cal.set(Calendar.MINUTE, minutoMarcado);
				Date dateRepresentation = cal.getTime();
				carona.setData(dateRepresentation);
				new InsertCaronaTask(this).execute();				
			} 
		}
		if (v == btnCalendar) {

			// Process to get Current Date
			final Calendar c = Calendar.getInstance();
			mYear = c.get(Calendar.YEAR);
			mMonth = c.get(Calendar.MONTH);
			mDay = c.get(Calendar.DAY_OF_MONTH);

			// Launch Date Picker Dialog
			DatePickerDialog dpd = new DatePickerDialog(this,
					new DatePickerDialog.OnDateSetListener() {

				//@Override
				public void onDateSet(DatePicker view, int year,
						int monthOfYear, int dayOfMonth) {
					// Display Selected date in textbox
					diaMarcado = dayOfMonth;
					mesMarcado = monthOfYear;
					anoMarcado = year;
					txtDate.setText(dayOfMonth + "-"
							+ (monthOfYear + 1) + "-" + year);

				}
			}, mYear, mMonth, mDay);
			dpd.show();
		}
		if (v == btnTimePicker) {

			// Process to get Current Time
			final Calendar c = Calendar.getInstance();
			mHour = c.get(Calendar.HOUR_OF_DAY);
			mMinute = c.get(Calendar.MINUTE);

			// Launch Time Picker Dialog
			TimePickerDialog tpd = new TimePickerDialog(this,
					new TimePickerDialog.OnTimeSetListener() {

				//@Override
				public void onTimeSet(TimePicker view, int hourOfDay,
						int minute) {
					HoraMarcada = hourOfDay;
					minutoMarcado = minute;
					txtTime.setText(hourOfDay + ":" + minute);
				}
			}, mHour, mMinute, false);
			tpd.show();
		}
	}

}

