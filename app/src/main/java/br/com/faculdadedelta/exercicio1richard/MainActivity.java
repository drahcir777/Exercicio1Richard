package br.com.faculdadedelta.exercicio1richard;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final int CODIGO_RETORNO = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void enviar(View view){

         //Pegar os componentes da tela View
        EditText etNome = findViewById(R.id.tvNome);
        EditText etData = findViewById(R.id.tvData);
        EditText etPeso = findViewById(R.id.tvPeso);
        EditText etAltura = findViewById(R.id.tvAltura);

        //Cria uma intent e pega a onde está e para onde ela vai
        Intent intent = new Intent(getBaseContext(), ValidacaoActivity.class);
        String nomeParam = etNome.getText().toString();
        String dataParam = etData.getText().toString();
        String pesoParam = etPeso.getText().toString();
        String alturaParam = etAltura.getText().toString();

        //Verifica se não está vazio os campos digitados
        if(nomeParam.isEmpty()){
            Toast.makeText(getBaseContext(), "O campo nome é obrigatório!",
                    Toast.LENGTH_LONG).show();
        }else if (dataParam.isEmpty()) {
            Toast.makeText(getBaseContext(),
                    "O campo data de nascimento é obrigatório!",
                    Toast.LENGTH_LONG).show();
        }else if (pesoParam.isEmpty()) {
            Toast.makeText(getBaseContext(),
                    "O campo peso é obrigatório!",
                    Toast.LENGTH_LONG).show();
        }else if (alturaParam.isEmpty()) {
            Toast.makeText(getBaseContext(),
                    "O campo altura é obrigatório!",
                    Toast.LENGTH_LONG).show();
        }else {
            // Passa o valor para a intent
            intent.putExtra("nomeParam", nomeParam);
            intent.putExtra("dataParam", dataParam);
            intent.putExtra("pesoParam", pesoParam);
            intent.putExtra("alturaParam", alturaParam);
            startActivityForResult(intent, CODIGO_RETORNO);
        }
    }

    public void limparCampos(View view){

        EditText etNome = findViewById(R.id.tvNome);
        EditText etData = findViewById(R.id.tvData);
        EditText etPeso = findViewById(R.id.tvPeso);
        EditText etAltura = findViewById(R.id.tvAltura);

        etNome.setText("");
        etData.setText("");
        etPeso.setText("");
        etAltura.setText("");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CODIGO_RETORNO){
            if (resultCode == ValidacaoActivity.RESULT_CODE_SUCCESS) {
                String resposta = data.getStringExtra("resposta");
                Toast.makeText(getBaseContext(), resposta, Toast.LENGTH_LONG).show();
            }
        }
    }
}
