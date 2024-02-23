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

        editTextMetrosConsumidos = findViewById(R.id.editTextMetrosConsumidos);
        btnCalcular = findViewById(R.id.btnCalcular);
        tvResultado = findViewById(R.id.tvResultado);
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

        btnCalcular.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Obtener los metros consumidos ingresados por el usuario
            String metrosConsumidosStr = editTextMetrosConsumidos.getText().toString();
            if (!metrosConsumidosStr.isEmpty()) {
                // Convertir la entrada a un número
                int metrosConsumidos = Integer.parseInt(metrosConsumidosStr);
                // Calcular el costo total
                double costoTotal = calcularCostoTotal(metrosConsumidos);
                // Mostrar el resultado
                tvResultado.setText("El costo total es: $" + costoTotal);
            } else {
                // Manejar el caso en el que el usuario no ingresa nada
                tvResultado.setText("Por favor ingrese los metros consumidos");
            }
        }
    });
}
    private double calcularCostoTotal(int metrosConsumidos) {
        double costoTotal = 0.0;
        if (metrosConsumidos >= 1 && metrosConsumidos <= 18) {
            costoTotal = 6.0;
        } else if (metrosConsumidos >= 19 && metrosConsumidos <= 28) {
            costoTotal = 6.0 + (metrosConsumidos - 18) * 0.45;
        } else if (metrosConsumidos >= 29) {
            costoTotal = 6.0 + (28 - 18) * 0.45 + (metrosConsumidos - 28) * 0.65;
        }
            return costoTotal;
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