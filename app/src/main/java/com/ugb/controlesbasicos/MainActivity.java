package com.ugb.controlesbasicos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    TextView tempVal;
    Button btn;
    String accion = "nuevo";
    String id = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btnGuardarAgendaAmigos);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    tempVal = findViewById(R.id.txtnombre);
                    String nombre = tempVal.getText().toString();

                    tempVal= findViewById(R.id.txtdireccion);
                    String direccion = tempVal.getText().toString();

                    tempVal = findViewById(R.id.txtTelefono);
                    String telefono = tempVal.getText().toString();

                    tempVal = findViewById(R.id.txtemail);
                    String email = tempVal.getText().toString();

                    tempVal = findViewById(R.id.txtdui);
                    String dui = tempVal.getText().toString();

                    DB db = new DB(getApplicationContext(),"", null,1);
                    String[] datos = new String[]{id, nombre, direccion, telefono, email, dui};
                    String respuesta = db.administrar_amigos(accion, datos);
                    if(respuesta.equals("ok")){
                        Toast.makeText(getApplicationContext(), "Amigo guardado con exito", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "Error al intentar guardar el amigo:" + respuesta, Toast.LENGTH_LONG).show();
                    }


                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Error:" + e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
 }
}