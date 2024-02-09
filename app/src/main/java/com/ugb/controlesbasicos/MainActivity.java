package com.ugb.controlesbasicos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tempVal;
    Button btn;
    RadioGroup opt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btnCalcular);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tempVal = findViewById(R.id.txtnum1);
                double num1 = Double.parseDouble(tempVal.getText().toString());

                tempVal = findViewById(R.id.txtnum2);
                double num2 = Double.parseDouble(tempVal.getText().toString());

               double respuesta = 0;
                opt = findViewById(R.id.optOpciones);
                switch (opt.getCheckedRadioButtonId()){
                    case R.id.optSuma:
                        respuesta = num1+num2;
                        break;
                    case R.id.optResta:
                        respuesta = num1-num2;
                        break;
                    case R.id.optMultiplicacion:
                        respuesta = num1*num2;
                        break;
                    case R.id.optDivision:
                        respuesta = num1/num2;
                        break;
                    case R.id.optPorcentaje:
                        respuesta = num1 * num2 / 100;
                        break;
                    case R.id.optExponenciacion:
                        respuesta = Math.pow(num1,num2);
                        break;
                    case R.id.optFactorial:
                        if (num1 == 0 || num1 == 1){
                            respuesta = 1;
                        }
                        else {
                            double factorial = 1;
                            for( int i = 2; i <= num1; i++){
                                factorial *= i;
                            }
                            respuesta = factorial;
                        }
                        break;
                    case R.id.optRaiz:
                        respuesta = Math.pow(num1,1.0/num2);
                        break;
                }

                tempVal = findViewById(R.id.lblrespuesta);
                tempVal.setText("Respuesta: "+ respuesta);
            }
        });
    }
}