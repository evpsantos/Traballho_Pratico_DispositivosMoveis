package br.com.anotacoes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText_tarefa;
    Button button_add,button_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText_tarefa = findViewById(R.id.ed_tarefa);
        button_view = findViewById(R.id.btm_view);
        button_add = findViewById(R.id.btm_confirmar);

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringTarefa = editText_tarefa.getText().toString();

                if (stringTarefa.length() <=0 ){
                    Toast.makeText(MainActivity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                }else {
                    DatabaseTarefaClass databaseHelperClass = new DatabaseTarefaClass(MainActivity.this);
                    TarefaModelClass tarefaModelClass = new TarefaModelClass(stringTarefa);
                    databaseHelperClass.addEmployee(tarefaModelClass);
                    Toast.makeText(MainActivity.this, "Tarefa adicionada com sucesso", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(getIntent());
                }
            }
        });

        button_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activity_Tarefa.class);
                startActivity(intent);
            }
        });
    }

}