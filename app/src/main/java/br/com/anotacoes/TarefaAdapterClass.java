package br.com.anotacoes;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TarefaAdapterClass extends RecyclerView.Adapter<TarefaAdapterClass.ViewHolder> {
    List<TarefaModelClass> tarefa;
    Context context;
    DatabaseTarefaClass databaseHelperClass;

    public TarefaAdapterClass(List<TarefaModelClass> tarefa, Context context) {
        this.tarefa = tarefa;
        this.context = context;
        databaseHelperClass = new DatabaseTarefaClass(context);
        Toast.makeText(context, tarefa.get(1).tarefa, Toast.LENGTH_SHORT).show();

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.tarefa_item_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final TarefaModelClass tarefaModelClass = tarefa.get(position);

        holder.text_id.setText(tarefa.get(position).id.toString());
        holder.ed_tarefa.setText(tarefa.get(position).tarefa);

        holder.button_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringName = holder.ed_tarefa.getText().toString();

                databaseHelperClass.updateEmployee(new TarefaModelClass(tarefaModelClass.getId(),stringName));
                notifyDataSetChanged();
                ((Activity) context).finish();
                context.startActivity(((Activity) context).getIntent());
            }
        });

        holder.button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelperClass.deleteEmployee(tarefaModelClass.getId());
                tarefa.remove(position);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return tarefa.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView text_id;
        EditText ed_tarefa;
        Button button_Edit;
        Button button_delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            text_id = itemView.findViewById(R.id.text_id);
            ed_tarefa = itemView.findViewById(R.id.ed_tarefa);
            button_delete = itemView.findViewById(R.id.button_delete);
            button_Edit = itemView.findViewById(R.id.button_edit);

        }
    }
}
