package com.freela.fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.freela.R;
import com.freela.activity.MainActivity;
import com.freela.model.Usuario;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class OportunidadeFragment extends Fragment implements View.OnClickListener {
    private EditText etTitulo;
    private EditText etDescricao;
    private TextView dtInicial;
    private TextView dtFinal;
    private Button btSalvar;
    private Button btVoltar;
    private Calendar calendar;
    private int dia, mes, ano;
    private Usuario usuario;


    public OportunidadeFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_oportunidade, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etTitulo = (EditText) view.findViewById(R.id.oportunidade_et_titulo);
        etDescricao = (EditText) view.findViewById(R.id.oportunidade_et_descricao);

        dtInicial = (TextView) view.findViewById(R.id.oportunidade_dt_inicial);
        dtInicial.setOnClickListener(this);

        dtFinal = (TextView) view.findViewById(R.id.oportunidade_dt_final);
        dtFinal.setOnClickListener(this);

        btSalvar = (Button)  view.findViewById(R.id.oportunidade_bt_salvar);
        btSalvar.setOnClickListener(this);

        btVoltar = (Button)  view.findViewById(R.id.oportunidade_bt_voltar);
        btVoltar.setOnClickListener(this);

        calendar = Calendar.getInstance();
        ano = calendar.get(Calendar.YEAR);
        mes = calendar.get(Calendar.MONTH);
        dia = calendar.get(Calendar.DAY_OF_MONTH);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.oportunidade_dt_inicial:
                DatePickerDialog dpd1 = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int ano,
                                                  int mes, int dia) {
                                dtInicial.setText(formatarData(ano, mes, dia));
                            }
                        }, ano, mes+1, dia);
                dpd1.show();
                break;

            case R.id.oportunidade_dt_final:

                DatePickerDialog dpd2 = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int ano,
                                                  int mes, int dia) {
                                dtFinal.setText(formatarData(ano, mes, dia));
                            }
                        }, ano, mes+1, dia);
                dpd2.show();
                break;
        }

    }

    private String formatarData(int ano, int mes, int dia) {
        Calendar data = Calendar.getInstance();
        data.set(Calendar.DAY_OF_MONTH, dia);
        data.set(Calendar.MONTH, mes + 1);
        data.set(Calendar.YEAR, ano);

        SimpleDateFormat df = new SimpleDateFormat("EEEE, dd MMMM 'de' yyyy");
        return df.format(data.getTime());
    }


}
