package com.example.myruta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.content.ContextCompat;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shuhart.stepview.StepView;

import java.util.ArrayList;


public class StepViewActivity extends AppCompatActivity {

    TextView txtContenido, txtvalor, txtvalor2;
    ImageView imgHospi;
    private int currentStep = -1;
    int a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.step_main);
        txtContenido = findViewById(R.id.txtIndicacion);
        imgHospi = findViewById(R.id.imageView);
        txtvalor = findViewById(R.id.textView3);
        txtvalor2 = findViewById(R.id.textView4);

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

        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (currentStep < stepView.getStepCount() - 1) {
                    currentStep++;
                    //stepView.go(currentStep, true);
                    txtContenido.setVisibility(View.VISIBLE);
                    imgHospi.setVisibility(View.VISIBLE);
                    a = 2;
                    // bucle para que recorra todos los states creados
                    for (int i = 0; i <= contador_dos; i++) {
                        // Condionales para cada step
                        if (currentStep == i) {
                            char read = cadena.charAt(a);
                            String c = Integer.toString(a);
                            txtvalor.setText(c);
                            if (read == 'r') {
                                stepView.go(currentStep, true);
                                stepView.getState()
                                        .selectedCircleColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent))
                                        .commit();
                                txtContenido.setText(getString(R.string.ruta_uno));
                                imgHospi.setImageResource(R.drawable.pasillo_uno);
                            }
                            if (read == 'u') {
                                stepView.go(currentStep, true);
                                stepView.getState()
                                        .selectedCircleColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent))
                                        .commit();
                                txtContenido.setText(getString(R.string.ruta_dos));
                                imgHospi.setImageResource(R.drawable.pasillo_dos);
                            }
                            if (read == 't') {
                                stepView.go(currentStep, true);
                                stepView.getState()
                                        .selectedCircleColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent))
                                        .commit();
                                txtContenido.setText(getString(R.string.ruta_tres));
                                imgHospi.setImageResource(R.drawable.pasillo_tres);
                            }
                            if (read == 'a') {
                                stepView.go(currentStep, true);
                                stepView.getState()
                                        .selectedCircleColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent))
                                        .commit();
                                txtContenido.setText(getString(R.string.ruta_cuatro));
                                imgHospi.setImageResource(R.drawable.pasillo_tres);
                            }

                        }

                        a++;
                    }
                } else {
                    stepView.done(true);
                }

            }
        });
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            // metodo para ir hacia tras en Step
            @Override
            public void onClick(View v) {
                if (currentStep > 0) {
                    currentStep--;
                    txtContenido.setVisibility(View.VISIBLE);
                    imgHospi.setVisibility(View.VISIBLE);
                    int e = currentStep +2;
                    // bucle para que recorra todos los states creados
                    for (int i = currentStep; i <= contador_dos; i++) {
                        if (currentStep == i) {

                            char read = cadena.charAt(e);
                            String c = Integer.toString(e);
                            txtvalor2.setText(c);
                            if (read == 'r') {
                                stepView.go(currentStep, true);
                                stepView.getState()
                                        .selectedCircleColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent))
                                        .commit();
                                txtContenido.setText(getString(R.string.ruta_uno));
                                imgHospi.setImageResource(R.drawable.pasillo_uno);
                            }
                            if (read == 'u') {
                                stepView.go(currentStep, true);
                                stepView.getState()
                                        .selectedCircleColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent))
                                        .commit();
                                txtContenido.setText(getString(R.string.ruta_dos));
                                imgHospi.setImageResource(R.drawable.pasillo_dos);
                            }
                            if (read == 't') {
                                stepView.go(currentStep, true);
                                stepView.getState()
                                        .selectedCircleColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent))
                                        .commit();
                                txtContenido.setText(getString(R.string.ruta_tres));
                                imgHospi.setImageResource(R.drawable.pasillo_tres);
                            }
                            if (read == 'a') {
                                stepView.go(currentStep, true);
                                stepView.getState()
                                        .selectedCircleColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent))
                                        .commit();
                                txtContenido.setText(getString(R.string.ruta_cuatro));
                                imgHospi.setImageResource(R.drawable.pasillo_tres);
                            }

                        }
                        e--;
                    }
                } else {
                    stepView.done(false);
                }
            }
        });

        // Listener para cuando clickas en cada estado o paso
        stepView.setOnStepClickListener(new StepView.OnStepClickListener() {
            @Override
            public void onStepClick(int step) {
                //Toast.makeText(StepViewActivity.this, "Step " + step, Toast.LENGTH_LONG).show();
                txtContenido.setVisibility(View.VISIBLE);
                imgHospi.setVisibility(View.VISIBLE);

                // bucle para que recorra todos los states creados
                int a = 2;
                for (int i = 0; i <= contador_dos; i++) {

                    // Condionales para cada step
                    if (step == i) {
                        char read = cadena.charAt(a);

                        if (read == 'r') {

                            stepView.go(step, true);
                            stepView.getState()
                                    .selectedCircleColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent))
                                    .commit();
                            txtContenido.setText(getString(R.string.ruta_uno));
                            imgHospi.setImageResource(R.drawable.pasillo_uno);
                        }
                        if (read == 'u') {

                            stepView.go(step, true);
                            stepView.getState()
                                    .selectedCircleColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent))
                                    .commit();
                            txtContenido.setText(getString(R.string.ruta_dos));
                            imgHospi.setImageResource(R.drawable.pasillo_dos);
                        }
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
                            txtContenido.setText(getString(R.string.ruta_cuatro));
                            imgHospi.setImageResource(R.drawable.pasillo_tres);
                        }
                    }
                    a++;
                }
            }
        });
    }
}

