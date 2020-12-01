package br.com.anotacoes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class Activity_Tarefa extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__tarefa);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        DatabaseTarefaClass databaseTarefaClass = new DatabaseTarefaClass(this);
        List<TarefaModelClass> tarefaModelClasses = databaseTarefaClass.getTarefaList();

        if (tarefaModelClasses.size() > 0){
            TarefaAdapterClass tarefaAdapterClass = new TarefaAdapterClass(tarefaModelClasses,Activity_Tarefa.this);
            recyclerView.setAdapter(tarefaAdapterClass);
        }else {
            Toast.makeText(this, "There is no employee in the database: " + tarefaModelClasses.size() , Toast.LENGTH_SHORT).show();
        }

    }
}