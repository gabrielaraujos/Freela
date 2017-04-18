package com.freela.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.freela.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Gabriel on 16/11/2016.


public class RegistroOportunidadeActivity_old extends Activity implements View.OnClickListener {

    private EditText titulo;
    private EditText descricao;
    private TextView dtInicial;
    private TextView dtFinal;
    private Button btnSalvar;
    private Calendar c = Calendar.getInstance();
    private int ano = c.get(Calendar.YEAR);
    private int mes = c.get(Calendar.MONTH);
    private int dia = c.get(Calendar.DAY_OF_MONTH);

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        titulo = (EditText) findViewById(R.id.titulo_opotunidade);
        descricao = (EditText) findViewById(R.id.desc_oportunidade);

        dtInicial = (TextView) findViewById(R.id.periodo_oportunidade);
        dtInicial.setOnClickListener(this);

        dtFinal = (TextView) findViewById(R.id.periodo_oportunidade);
        dtFinal.setOnClickListener(this);

        btnSalvar = (Button)  findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Calendar c = Calendar.getInstance();
        int ano = c.get(Calendar.YEAR);
        int mes = c.get(Calendar.MONTH);
        int dia = c.get(Calendar.DAY_OF_MONTH);

        switch (view.getId()) {
            case R.id.dt_inicial:

                DatePickerDialog dpd1 = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                String dt = formatarData(year, monthOfYear, dayOfMonth);

                                dtInicial.setText(dt);

                            }
                        }, ano, mes, dia);

                dpd1.show();

                break;

            case R.id.dt_final:

                DatePickerDialog dpd2 = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                String dt = formatarData(year, monthOfYear, dayOfMonth);

                                dtInicial.setText(dt);

                            }
                        }, ano, mes, dia);

                dpd2.show();

                break;
        }

    }

    private String formatarData(int ano, int mes, int dia) {

        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, dia);
        c.set(Calendar.MONTH, mes + 1);
        c.set(Calendar.YEAR, ano);

        SimpleDateFormat df = new SimpleDateFormat("EEEE, dd MMMM de yyyy");
        return df.format(c.getTime());

    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int ano = c.get(Calendar.YEAR);
            int mes = c.get(Calendar.MONTH);
            int dia = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, ano, mes, dia);
        }

        public void onDateSet(DatePicker view, int ano, int mes, int dia) {
            // Do something with the date chosen by the user

            Calendar c = Calendar.getInstance();
            c.set(Calendar.DAY_OF_MONTH, dia);
            c.set(Calendar.MONTH, mes + 1);
            c.set(Calendar.YEAR, ano);

            SimpleDateFormat df = new SimpleDateFormat("EEEE, dd MMMM de yyyy");
            String dt = df.format(c.getTime());


        }

    }
}
        */