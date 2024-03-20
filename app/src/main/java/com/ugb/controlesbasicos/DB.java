package com.ugb.controlesbasicos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DB extends SQLiteOpenHelper {
    private static final String dbname = "amigos";
    private static final int v =1;
    private static final String SQLdb = "CREATE TABLE amigos(idAmigo integer primary key autoincrement, " +
            "nombre text, direccion text, telefono text, email text, dui text, foto text)";
    public DB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, dbname, factory, v);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQLdb);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //cambiar estructura de la BD
    }
    public String administrar_amigos(String accion, String[] datos){
        try {
            SQLiteDatabase db = getWritableDatabase();
            String sql = "";
            if( accion.equals("nuevo") ){
                sql = "INSERT INTO amigos(nombre,direccion,telefono,email,dui, foto) VALUES('"+ datos[1] +"', '"+ datos[2] +"', '"+ datos[3] +"', " +
                        "'"+ datos[4] +"','"+ datos[5] +"', '"+ datos[6] +"' )";
            } else if (accion.equals("modificar")) {
                sql = "UPDATE amigos SET nombre='"+ datos[1] +"', direccion='"+ datos[2] +"', telefono='"+ datos[3] +"', email=" +
                        "'"+ datos[4] +"', dui='"+ datos[5] +"', foto='"+ datos[6] +"' WHERE idAmigo='"+ datos[0] +"'";
            } else if (accion.equals("eliminar")) {
                sql = "DELETE FROM amigos WHERE idAmigo='"+ datos[0] +"'";
            }
            db.execSQL(sql);
            return "ok";
        }catch (Exception e){
            return e.getMessage();
        }
    }
    public Cursor obtener_amigos(){
        Cursor cursor;
        SQLiteDatabase db = getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM amigos ORDER BY nombre", null);
        return cursor;
    }
}