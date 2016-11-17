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

public class RegistroOportunidadeActivity extends Activity implements View.OnClickListener {

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_oportunidade);

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
}
