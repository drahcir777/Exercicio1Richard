package br.com.faculdadedelta.exercicio1richard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ValidacaoActivity extends AppCompatActivity {

    public static final int RESULT_CODE_SUCCESS = 1;
    public static final int RESULT_CODE_ERROR = 0;
    private String peso, altura, nome, dataNasc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validacao);

        //Traz os dados da intent da view anterior
        Intent intent = getIntent();
        if(intent != null){

            nome = intent.getStringExtra("nomeParam");
            dataNasc = intent.getStringExtra("dataParam");
            peso = intent.getStringExtra("pesoParam");
            altura = intent.getStringExtra("alturaParam");

            TextView tvNome = findViewById(R.id.tvNome);
            tvNome.setText("Nome: " + nome);

            TextView tvData = findViewById(R.id.tvData);
            tvData.setText("Data: " + dataNasc);

            TextView tvPeso = findViewById(R.id.tvPeso);
            tvPeso.setText("Peso: " + peso);

            TextView tvaltura = findViewById(R.id.tvAltura);
            tvaltura.setText("Altura" + altura);

        }
    }

    public void validar(View view){

        String resposta = "";
        double pesoLimite = 60.4;
        Double pesoConv = Double.parseDouble(peso);

        if(nomeAprovada() && pesoConv > pesoLimite){
            resposta = "Informações Validadas com sucesso";
        }else if (pesoConv < pesoLimite){
            resposta = "Peso Invalido";
        }else if (nomeAprovada() == false){
            resposta = "Nome Invalido";
        }

        SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;

        try {
            date = form.parse(dataNasc);
        }catch (ParseException e){
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        if(date.after(new Date())){
            resposta = "A data de nascimento está incorreta";
        }

        Intent data = new Intent();
        data.putExtra("resposta", resposta);

        setResult(RESULT_CODE_SUCCESS, data);
        finish();
    }
    public boolean nomeAprovada(){return nome.length() > 5;}

}

