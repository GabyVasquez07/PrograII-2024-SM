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
    TabHost tbh;
    TextView tempVal;
    Spinner spn;
    Button btn;
    Area area = new Area();
    EditText editTextMetrosConsumidos;
    Button btnCalcular;
    TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tbh = findViewById(R.id.tbhParcial1);
        tbh.setup();
        tbh.addTab(tbh.newTabSpec("AREA").setIndicator("AREAA", null).setContent(R.id.Area));
        tbh.addTab(tbh.newTabSpec("TARI").setIndicator("TARIFA",null).setContent(R.id.Tarifa));

        btn = findViewById(R.id.btnConvertir1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spn = findViewById(R.id.spnDeArea);
                int de = spn.getSelectedItemPosition();

                spn = findViewById(R.id.spnAArea);
                int a = spn.getSelectedItemPosition();

                tempVal = findViewById(R.id.txtCantidad1);
                try {
                    double cantidad = Double.parseDouble(tempVal.getText().toString());
                    double resp = area.convertir(0, de, a, cantidad);
                    mostrarResultado(resp);
                } catch (NumberFormatException e) {
                    mostrarError("Ingresa una cantidad válida.");
                }
            }
        });

    }

    private void mostrarResultado(double resultado) {
        String mensaje = String.format("Respuesta: %.1f", resultado);
        Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();
    }

    private void mostrarError(String mensaje) {
        Toast.makeText(getApplicationContext(), "Error: " + mensaje, Toast.LENGTH_LONG).show();
    }
}

class Area {
    double[][] valores =  {
            {1,6988, 75218.1332, 10000,  0.0001187, 16, 0.6988}
    };

    public double convertir(int opcion, int de, int a, double cantidad) {
        return valores[opcion][a] / valores[opcion][de] * cantidad;
    }
}