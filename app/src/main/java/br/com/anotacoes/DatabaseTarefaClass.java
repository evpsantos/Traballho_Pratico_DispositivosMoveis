package br.com.anotacoes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseTarefaClass extends SQLiteOpenHelper {
    //Database version
    private static final int DATABASE_VERSION = 1;
    //Database Name
    private static final String DATABASE_NAME = "tarefa_database";
    //Database Table name
    private static final String TABLE_NAME = "TAREFA";
    //Table columns
    public static final String ID = "id";
    public static final String TAREFA = "tarefa";
    private SQLiteDatabase sqLiteDatabase;


    //creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME +"("+ID+
            " INTEGER PRIMARY KEY AUTOINCREMENT," + TAREFA + " TEXT NOT NULL);";
    //Constructor
    public DatabaseTarefaClass(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //Add Employee Data
    public void addEmployee(TarefaModelClass tarefaModelClass){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseTarefaClass.TAREFA, tarefaModelClass.getTarefa());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(DatabaseTarefaClass.TABLE_NAME, null,contentValues);
    }

    public List<TarefaModelClass> getTarefaList(){
        String sql = "select * from " + TABLE_NAME;
        sqLiteDatabase = this.getReadableDatabase();
        List<TarefaModelClass> storeEmployee = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String tarefa = cursor.getString(1);
                storeEmployee.add(new TarefaModelClass(id,tarefa));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return storeEmployee;
    }

    public void updateEmployee(TarefaModelClass employeeModelClass){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseTarefaClass.TAREFA,employeeModelClass.getTarefa());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.update(TABLE_NAME,contentValues,ID + " = ?" , new String[]
                {String.valueOf(employeeModelClass.getId())});
    }

    public void deleteEmployee(int id){
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, ID + " = ? ", new String[]
                {String.valueOf(id)});
    }
}
