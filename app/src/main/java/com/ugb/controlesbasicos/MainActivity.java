package com.ugb.controlesbasicos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TabHost tbh;
    TextView tempVal;
    Spinner spn;
    Button btn;
    conversores miObj = new conversores();
    tiempo tiem = new tiempo();
    alma miObj2 = new alma();
    monedas mon = new monedas();
    masa mas = new masa();
    volumen vol = new volumen();
    trans transferencia = new trans();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tbh = findViewById(R.id.tbhConversores);
        tbh.setup();
        tbh.addTab(tbh.newTabSpec("LON").setIndicator("LONGITUD", null).setContent(R.id.tabLongitud));
        tbh.addTab(tbh.newTabSpec("MON").setIndicator("MONEDAS", null).setContent(R.id.tabMonedas));
        tbh.addTab(tbh.newTabSpec("ALM").setIndicator("ALMACENAMIENTO", null).setContent(R.id.tabAlmacenamiento));
        tbh.addTab(tbh.newTabSpec("MAS").setIndicator("MASA",null).setContent(R.id.tabMasa));
        tbh.addTab(tbh.newTabSpec("TIE").setIndicator("TIEMPO",null).setContent(R.id.tabTiempo));
        tbh.addTab(tbh.newTabSpec("VOL").setIndicator("VOLUMEN",null).setContent(R.id.tabTrasferenciaDatos));
        tbh.addTab(tbh.newTabSpec("TRANS").setIndicator("TRANFERENCIA DE DATOS",null).setContent(R.id.tabTrasferenciaDatos));

        btn = findViewById(R.id.btnConvertirLongitud);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spn = findViewById(R.id.spnDeLongitud);
                int de = spn.getSelectedItemPosition();
                spn = findViewById(R.id.spnALongitud);
                int a = spn.getSelectedItemPosition();
                tempVal = findViewById(R.id.txtCantidadLongitud);
                try {
                    double cantidad = Double.parseDouble(tempVal.getText().toString());
                    double resp = miObj.convertir(0, de, a, cantidad);
                    mostrarResultado(resp);
                } catch (NumberFormatException e) {
                    mostrarError("Ingresa una cantidad válida.");
                }
            }
        });
        btn = findViewById(R.id.btnConvertirMon);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spn = findViewById(R.id.spnDeMon);
                int de = spn.getSelectedItemPosition();
                spn = findViewById(R.id.spnAMon);
                int a = spn.getSelectedItemPosition();
                tempVal = findViewById(R.id.txtCantidadMon);
                try {
                    double cantidad = Double.parseDouble(tempVal.getText().toString());
                    double resp = mon.convertir2(0, de, a, cantidad);
                    mostrarResultado(resp);
                } catch (NumberFormatException e) {
                    mostrarError("Ingresa una cantidad válida.");
                }
            }
        });
        btn = findViewById(R.id.btnConvertirAlma);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spn = findViewById(R.id.spnDeAlma);
                int de = spn.getSelectedItemPosition();
                spn = findViewById(R.id.spnAAlma);
                int a = spn.getSelectedItemPosition();
                tempVal = findViewById(R.id.txtCantidadAlma);
                try {
                    double cantidad = Double.parseDouble(tempVal.getText().toString());
                    double resp = miObj2.convertir2(0, de, a, cantidad);
                    mostrarResultado(resp);
                } catch (NumberFormatException e) {
                    mostrarError("Ingresa una cantidad válida.");
                }
            }
        });
        btn = findViewById(R.id.btnConvertirTiem);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spn = findViewById(R.id.spnDeTiem);
                int de = spn.getSelectedItemPosition();
                spn = findViewById(R.id.spnATiem);
                int a = spn.getSelectedItemPosition();
                tempVal = findViewById(R.id.txtCantidadTiem);
                try {
                    double cantidad = Double.parseDouble(tempVal.getText().toString());
                    double resp = tiem.convertir2(0, de, a, cantidad);
                    mostrarResultado(resp);
                } catch (NumberFormatException e) {
                    mostrarError("Ingresa una cantidad válida.");
                }
            }
        });


        btn = findViewById(R.id.btnConvertirMasa);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spn = findViewById(R.id.spnDeMasa);
                int de = spn.getSelectedItemPosition();
                spn = findViewById(R.id.spnAMasa);
                int a = spn.getSelectedItemPosition();
                tempVal = findViewById(R.id.txtCantidadMasa);
                try {
                    double cantidad = Double.parseDouble(tempVal.getText().toString());
                    double resp = mas.convertir2(0, de, a, cantidad);
                    mostrarResultado(resp);
                } catch (NumberFormatException e) {
                    mostrarError("Ingresa una cantidad válida.");
                }
            }
        });
        btn = findViewById(R.id.btnConvertirTrans);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spn = findViewById(R.id.spnDeTrans);
                int de = spn.getSelectedItemPosition();
                spn = findViewById(R.id.spnATrans);
                int a = spn.getSelectedItemPosition();
                tempVal = findViewById(R.id.txtCantidadTrans);
                try {
                    double cantidad = Double.parseDouble(tempVal.getText().toString());
                    double resp = transferencia.convertir2(0, de, a, cantidad);
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
class conversores{
    double[][] valores = {
            {1, 100, 39.3701, 3.28084, 1.193, 1.09361, 0.001, 0.000621371}
    };
    public double convertir(int opcion, int de, int a, double cantidad){
        return valores[opcion][a] / valores[opcion][de] * cantidad;
    }
}
class alma{
    double[][] valores2 = {
            {1,0.001,0.000001,1e-9,1e-12,1e-15,8,0.008,0.000008,8e-9,8e-12,8e-15}
        };
    public double convertir2(int opcion, int de, int a, double cantidad){
        return valores2[opcion][a] / valores2[opcion][de] * cantidad;
    }
}
class monedas{
    double[][] valores2 = {
            {1.0, 0.93, 17.19, 8.75}
    };
    public double convertir2(int opcion, int de, int a, double cantidad){
        return valores2[opcion][a] / valores2[opcion][de] * cantidad;
    }
}
class tiempo{
    double[][] valores3 = {
            {31557600,315576000,31557600,525960,8766,365.25,52.1785,12,1}
    };
    public double convertir2(int opcion, int de, int a, double cantidad){
        return valores3[opcion][a] / valores3[opcion][de] * cantidad;
    }
}
class volumen{
    double[][] valores3 = {
            {0.001,1,1000,1000000,0.035314666721489, 61.0237440,0.2641720523}
    };
    public double convertir2(int opcion, int de, int a, double cantidad){
        return valores3[opcion][a] / valores3[opcion][de] * cantidad;
    }
}
class masa{
    double[][] valores3 = {
            {1,0.001,1e-6,1000,1e+6,0.00220462,0.035274,0.000157473,98421e-7}
    };
    public double convertir2(int opcion, int de, int a, double cantidad){
        return valores3[opcion][a] / valores3[opcion][de] * cantidad;
    }
}
class trans{
    double[][] valores3 = {
            {1,1000,100000,1000000,1000000000,1000000000}
    };
    public double convertir2(int opcion, int de, int a, double cantidad){
        return valores3[opcion][a] / valores3[opcion][de] * cantidad;
    }
}
