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
    tiempo miti = new conversores();
    alma miObj2 = new alma();
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
                double cantidad= Double.parseDouble(tempVal.getText().toString());

                double resp = miObj.convertir(0, de, a, cantidad);

                Toast.makeText(getApplicationContext(), "Respuesta: "+ resp, Toast.LENGTH_LONG).show();
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
                double cantidad= Double.parseDouble(tempVal.getText().toString());

                double resp = miObj2.convertir2(0, de, a, cantidad);

                Toast.makeText(getApplicationContext(), "Respuesta: "+ resp, Toast.LENGTH_LONG).show();
            }
        });
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
            {1, 8, 8192, 8388608, 1048576, 1073741824}
    };
    public double convertir2(int opcion, int de, int a, double cantidad){
        return valores2[opcion][a] / valores2[opcion][de] * cantidad;
    }
}
class tiempo{
    double[][] valores2 = {
            {1, 8, 8192, 8388608, 1048576, 1073741824}
    };
    public double convertir2(int opcion, int de, int a, double cantidad){
        return valores2[opcion][a] / valores2[opcion][de] * cantidad;
    }
}