package br.com.anotacoes;

public class TarefaModelClass {
    Integer id;
    String tarefa;

    public TarefaModelClass(String tarefa) {
        this.tarefa = tarefa;
    }

    public TarefaModelClass(Integer id, String tarefa) {
        this.id = id;
        this.tarefa = tarefa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTarefa() {
        return tarefa;
    }

    public void setTarefa(String tarefa) {
        this.tarefa = tarefa;
    }

}
