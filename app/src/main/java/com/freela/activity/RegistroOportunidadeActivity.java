package com.freela.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.freela.R;

import java.util.Calendar;

public class RegistroOportunidadeActivity extends Activity implements View.OnClickListener {
    private EditText titulo;
    private EditText descricao;
    private TextView dtInicio;
    private TextView dtFinal;
    private Button salvar;

    private Calendar c;
    private int dia, mes, ano;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_oportunidade);

        titulo = (EditText) findViewById(R.id.etTitulo);
        descricao = (EditText) findViewById(R.id.etDescricao);

        dtInicio = (TextView) findViewById(R.id.etDtInicio);
        dtInicio.setOnClickListener(this);

        dtFinal = (TextView) findViewById(R.id.etDtFinal);
        dtFinal.setOnClickListener(this);

        salvar = (Button) findViewById(R.id.btSalvar);
        salvar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        c  = Calendar.getInstance();
        dia = c.get(Calendar.DAY_OF_MONTH);
        mes = c.get(Calendar.MONTH);
        ano = c.get(Calendar.YEAR);

        switch (view.getId()){

            case R.id.etDtInicio:
                DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {


                            dtInicio.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, ano, mes, dia);

                datePickerDialog.show();
                break;

            case R.id.etDtFinal:
                DatePickerDialog datePickerDialog2 = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {


                                dtFinal.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, ano, mes, dia);

                datePickerDialog2.show();
                break;

        }
    }

    private void voltar() {

        startActivity(new Intent(this, DashboardActivity.class));

    }
}
