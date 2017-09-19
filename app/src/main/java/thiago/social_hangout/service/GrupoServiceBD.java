package thiago.social_hangout.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import thiago.social_hangout.model.Grupo;

/**
 * Created by thiago on 05/09/17.
 */

public class GrupoServiceBD extends SQLiteOpenHelper {

    private static final String TAG = "gruposerviceBD";
    private static final String NAME = "grupo.sqlite";
    private static final int VERSION = 1;
    private static GrupoServiceBD grupoServiceBD = null;

    private GrupoServiceBD(Context context) {
        super(context, NAME, null, VERSION);
        getWritableDatabase();
    }

    public static GrupoServiceBD getInstance(Context context){
        if(grupoServiceBD == null){
            grupoServiceBD = new GrupoServiceBD(context);
            return grupoServiceBD;
        }else {
            Log.d(TAG, "Tabela ja existente. Aguarde ...");
            return grupoServiceBD;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table if not exists grupo " +
                "(_id integer primary key autoincrement, " +
                "nome text, " +
                "foto text);";
        Log.d(TAG, "Criando tabela grupo. Aguarde ...");
        sqLiteDatabase.execSQL(sql);
        Log.d(TAG, "Tabela grupo criada com sucesso.");
        new Task().execute();
        Log.d(TAG, "Tabela grupo populada com sucesso.");

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public List<Grupo> getAll() {

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        try {
            return toList(sqLiteDatabase.rawQuery("select * from grupo", null));
        } finally {
            sqLiteDatabase.close();
        }
    }
        public List<Grupo> getByName(String name) {
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();

            try {
                return toList(sqLiteDatabase.rawQuery("select * fom grupo where name='" + name + "'", null));
            } finally {
                sqLiteDatabase.close();
            }
        }

    private List<Grupo> toList(Cursor c){
        List<Grupo> grupos = new ArrayList<>();

        if(c.moveToFirst()){
            do {
                Grupo grupo = new Grupo();

                grupo.id = c.getLong(c.getColumnIndex("_id"));
                grupo.nome = c.getString(c.getColumnIndex("nome"));
                grupo.foto = c.getString(c.getColumnIndex("foto"));

                grupos.add(grupo);

            }while (c.moveToNext());
        }

        return grupos;
    }

    //Create
    public long save(Grupo grupo){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        try{
            ContentValues values = new ContentValues();
            values.put("nome", grupo.nome);
            values.put("foto", grupo.foto);

            if(grupo.id == null){
                return sqLiteDatabase.insert("grupo", null, values);
            }else{
                values.put("_id", grupo.id);
                return sqLiteDatabase.update("grupo", values, "_id" + grupo.id, null);
            }
        }finally {
            sqLiteDatabase.close();
        }
    }

    public long delete(Grupo grupo){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        try{
            return sqLiteDatabase.delete("grupo", "_id=?", new String[] {String.valueOf(grupo.id)});

        }finally {
            sqLiteDatabase.close();
        }
    }

    private class Task extends AsyncTask<Void, Void, Boolean>{

        @Override
        protected Boolean doInBackground(Void... voids) {
            return popularTabelaGrupo();
        }

        private boolean popularTabelaGrupo() {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();

            try {

                ContentValues values = new ContentValues();
                values.put("nome", "Turma");
                sqLiteDatabase.insert("grupo", null, values);

                values = new ContentValues();
                values.put("nome", "Amigos");
                sqLiteDatabase.insert("grupo", null, values);

            } catch (SQLException e){
                e.printStackTrace();
                return false;
            }finally {
                sqLiteDatabase.close();
            }
            return true;
        }
    }
}
