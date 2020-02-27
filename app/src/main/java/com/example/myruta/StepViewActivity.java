package com.example.myruta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.shuhart.stepview.StepView;

import java.util.ArrayList;


public class StepViewActivity extends AppCompatActivity {

    TextView txtContenido;
    ImageView imgHospi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.step_main);
        txtContenido = findViewById(R.id.txtIndicacion);
        imgHospi = findViewById(R.id.imageView);

        // Se instancia el objeto StepView
        final StepView stepView = findViewById(R.id.step_view);

        // Metodo para pasar variables entre activity
        Intent intent = this.getIntent();
        Bundle extra = intent.getExtras();
        final String cadena = extra.getString("respuesta");
        final String valor = extra.getString("message");
        final int contador_dos = Integer.parseInt(valor);

        stepView.getState()
                // Array donde introducimos los states o los pasos introducidos
                .steps(new ArrayList<String>() {{
                    for (int i = 1; i <= contador_dos; i++) {
                        add("Paso" + i);
                    }
                }})
                .stepsNumber(contador_dos)
                .commit();

        // Listener para cuando clickas en cada estado o paso
        stepView.setOnStepClickListener(new StepView.OnStepClickListener() {
            @Override
            public void onStepClick(int step) {
                Toast.makeText(StepViewActivity.this, "Step " + step, Toast.LENGTH_LONG).show();
                txtContenido.setVisibility(View.VISIBLE);
                imgHospi.setVisibility(View.VISIBLE);

                // bucle para que recorra todos los states creados
                int a = 2;
                for (int i = 0; i <= contador_dos; i++) {
                    // Condionales para cada step
                    //for (int a = 2; a == cadena.length(); a++) {

                    if (step == i) {
                        char read = cadena.charAt(a);
                        String cadena_dos = Integer.toString(a);
                        //txtContenido.setText(cadena_dos);
                        if (read == 'r') {
                            //txtContenido.setText("Radiologia");
                            stepView.go(step, true);
                            stepView.getState()
                                    .selectedCircleColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent))
                                    .commit();
                            txtContenido.setText(getString(R.string.ruta_uno));
                            imgHospi.setImageResource(R.drawable.pasillo_uno);
                        }
                        if (read == 'u') {
                            //txtContenido.setText("Urgencias");
                            stepView.go(step, true);
                            stepView.getState()
                                    .selectedCircleColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent))
                                    .commit();
                            txtContenido.setText(getString(R.string.ruta_dos));
                            imgHospi.setImageResource(R.drawable.pasillo_dos);
                        }
                        if (read == 't') {
                            //txtContenido.setText("Traumatologia");
                            stepView.go(step, true);
                            stepView.getState()
                                    .selectedCircleColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent))
                                    .commit();
                            txtContenido.setText(getString(R.string.ruta_tres));
                            imgHospi.setImageResource(R.drawable.pasillo_tres);
                        }
                        if (read == 'a') {
                            //txtContenido.setText("Ascensor");
                            stepView.go(step, true);
                            stepView.getState()
                                    .selectedCircleColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent))
                                    .commit();
                            txtContenido.setText(getString(R.string.ruta_cuatro));
                            imgHospi.setImageResource(R.drawable.pasillo_tres);
                        }
                    }
                    a++;
                }

                /*

                            if (read == 't') {

                                stepView.go(step, true);
                                stepView.getState()
                                        .selectedCircleColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent))
                                        .commit();
                                txtContenido.setText(getString(R.string.ruta_tres));
                                imgHospi.setImageResource(R.drawable.pasillo_tres);
                            }
                            if (read == 'a') {

                                stepView.go(step, true);
                                stepView.getState()
                                        .selectedCircleColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent))
                                        .commit();
                                txtContenido.setText(getString(R.string.ruta_tres));
                                imgHospi.setImageResource(R.drawable.pasillo_tres);
                            }*/


            }

        });
    }
}

