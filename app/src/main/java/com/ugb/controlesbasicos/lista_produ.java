package com.ugb.controlesbasicos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class lista_produ extends AppCompatActivity {
    Bundle paramatros = new Bundle();
    DB db;
    ListView lts;
    Cursor cAmigos;
    final ArrayList<amigos> alAmigos = new ArrayList<amigos>();
    final ArrayList<amigos> alAmigosCopy = new ArrayList<amigos>();
    amigos datosProductos;
    FloatingActionButton btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_produ);
        btn = findViewById(R.id.fabAgregarAmigos);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paramatros.putString("accion","nuevo");
                abrirActividad(paramatros);
            }
        });
        obtenerAmigos();
        buscarAmigos();
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mimenu, menu);

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        cAmigos.moveToPosition(info.position);
        menu.setHeaderTitle("Que deseas hacer con "+ cAmigos.getString(1));//1 es el campo nombre
    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        try{
            switch (item.getItemId()){
                case R.id.mnxAgregar:
                    paramatros.putString("accion", "nuevo");
                    abrirActividad(paramatros);
                    break;
                case R.id.mnxModificar:
                    String[] poductos = {
                            cAmigos.getString(0), //idproducto
                            cAmigos.getString(1), //codigo
                            cAmigos.getString(2), //descripcion
                            cAmigos.getString(3), //marca
                            cAmigos.getString(4), //presentacion
                            cAmigos.getString(5), //precio
                            cAmigos.getString(6), //foto
                    };
                    paramatros.putString("accion", "modificar");
                    paramatros.putStringArray("productos", poductos);
                    abrirActividad(paramatros);
                    break;
                case R.id.mnxEliminar:
                    eliminarProducto();
                    break;
            }
            return true;
        }catch (Exception e){
            mostrarMsg("Error al seleccionar el item: "+ e.getMessage());
            return super.onContextItemSelected(item);
        }
    }
    private void eliminarProducto(){
        try {
            AlertDialog.Builder confirmar = new AlertDialog.Builder(lista_produ.this);
            confirmar.setTitle("Esta seguro de eliminar el producto: ");
            confirmar.setMessage(cAmigos.getString(1));
            confirmar.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    String respuesta = db.administrar_amigos("eliminar", new String[]{cAmigos.getString(0)});
                    if( respuesta.equals("ok") ){
                        mostrarMsg("Producto eliminado con exito");
                        obtenerAmigos();
                    }else{
                        mostrarMsg("Error al eliminar el producto: "+ respuesta);
                    }
                }
            });
            confirmar.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            confirmar.create().show();
        }catch (Exception e){
            mostrarMsg("Error al eliminar: "+ e.getMessage());
        }
    }
    private void buscarAmigos(){
        TextView tempVal = findViewById(R.id.txtBuscarProdu);
        tempVal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try{
                    alAmigos.clear();
                    String valor = tempVal.getText().toString().trim().toLowerCase();
                    if( valor.length()<=0 ){
                        alAmigos.addAll(alAmigosCopy);
                    }else{
                        for (amigos productos : alAmigosCopy){
                            String codigo = productos.getCodigo();
                            String descripcion = productos.getDescripcion();
                            String marca = productos.getMarca();
                            String presentacion = productos.getPresentacion();
                            if( codigo.trim().toLowerCase().contains(valor) ||
                                    descripcion.trim().toLowerCase().contains(valor) ||
                                    marca.trim().contains(valor) ||
                                    presentacion.trim().toLowerCase().contains(valor)){
                                alAmigos.add(productos);
                            }
                        }
                        adaptadorimagenes adImagenes=new adaptadorimagenes(getApplicationContext(), alAmigos);
                        lts.setAdapter(adImagenes);
                    }
                }catch (Exception e){
                    mostrarMsg("Error al buscar: "+ e.getMessage());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    private void abrirActividad(Bundle parametros){
        Intent abrirActividad = new Intent(getApplicationContext(), MainActivity.class);
        abrirActividad.putExtras(parametros);
        startActivity(abrirActividad);
    }
    private void obtenerAmigos(){
        try{
            alAmigos.clear();
            alAmigosCopy.clear();

            db = new DB(getApplicationContext(),"", null, 1);
            cAmigos = db.obtener_amigos();
            if( cAmigos.moveToFirst() ){
                lts = findViewById(R.id.ltsProducto);
                do{
                    datosProductos = new amigos(
                            cAmigos.getString(0),//idproducto
                            cAmigos.getString(1),//codigo
                            cAmigos.getString(2),//descripcion
                            cAmigos.getString(3),//marca
                            cAmigos.getString(4),//presentacion
                            cAmigos.getString(5),//precio
                            cAmigos.getString(6) //foto
                    );
                    alAmigos.add(datosProductos);
                }while (cAmigos.moveToNext());
                alAmigosCopy.addAll(alAmigos);

                adaptadorimagenes adImagenes = new adaptadorimagenes(getApplicationContext(), alAmigos);
                lts.setAdapter(adImagenes);

                registerForContextMenu(lts);
            }else {
                paramatros.putString("accion", "nuevo");
                abrirActividad(paramatros);
                mostrarMsg("No hay Datos de productos.");
            }
        }catch (Exception e){
            mostrarMsg("Error al obtener los productos: "+ e.getMessage());
        }
    }
    private void mostrarMsg(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}