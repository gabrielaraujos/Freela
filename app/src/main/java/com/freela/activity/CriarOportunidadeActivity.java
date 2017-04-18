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

public class CriarOportunidadeActivity extends Activity implements View.OnClickListener {

    private EditText etTitulo;
    private EditText etDescricao;
    private TextView dtInicial;
    private TextView dtFinal;
    private Button btSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.criar_oportunidade);

        etTitulo = (EditText) findViewById(R.id.oportunidade_et_titulo);
        etDescricao = (EditText) findViewById(R.id.oportunidade_et_descricao);

        dtInicial = (TextView) findViewById(R.id.oportunidade_dt_inicial);
        dtInicial.setOnClickListener(this);

        dtFinal = (TextView) findViewById(R.id.oportunidade_dt_final);
        dtFinal.setOnClickListener(this);

        btSalvar = (Button)  findViewById(R.id.oportunidade_bt_salvar);
        btSalvar.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        Calendar c = Calendar.getInstance();
        int ano = c.get(Calendar.YEAR);
        int mes = c.get(Calendar.MONTH);
        int dia = c.get(Calendar.DAY_OF_MONTH);

        switch (view.getId()) {
            case R.id.oportunidade_dt_inicial:
                DatePickerDialog dpd1 = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                String dtIni = formatarData(year, monthOfYear, dayOfMonth);

                                dtInicial.setText(dtIni);

                            }
                        }, ano, mes, dia);

                dpd1.show();
                break;

            case R.id.oportunidade_dt_final:

                DatePickerDialog dpd2 = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                String dtFim = formatarData(year, monthOfYear, dayOfMonth);

                                dtFinal.setText(dtFim);

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
