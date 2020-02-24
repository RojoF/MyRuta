package com.example.myruta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
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
        txtContenido = (TextView) findViewById(R.id.txtIndicacion);
        imgHospi = (ImageView) findViewById(R.id.imageView);
        final StepView stepView = (StepView) findViewById(R.id.step_view);
        Intent intent = this.getIntent();
        Bundle extra = intent.getExtras();
        final String valor = extra.getString("message");
        final String valor_dos = extra.getString("respuesta");
        final int contador_dos = Integer.parseInt(valor);
        stepView.getState()
                .steps(new ArrayList<String>() {{
                    for (int i = 1; i <= contador_dos; i++) {
                        add("Paso" + i);
                    }
                }})
                .stepsNumber(contador_dos)
                .commit();

        stepView.setOnStepClickListener(new StepView.OnStepClickListener() {
            @Override
            public void onStepClick(int step) {
                Toast.makeText(StepViewActivity.this, "Step " + step, Toast.LENGTH_LONG).show();


                if (step == 0) {

                    stepView.go(step, true);
                    stepView.getState()
                            .selectedCircleColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary))
                            .commit();
                    txtContenido.setText(getString(R.string.ruta_uno));
                    txtContenido.append(valor);

                    imgHospi.setImageResource(R.drawable.pasillo_uno);
                }

                if (step == 1) {

                    stepView.go(step, true);
                    stepView.getState()
                            .selectedCircleColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary))
                            .commit();
                    txtContenido.setText(getString(R.string.ruta_dos));
                    imgHospi.setImageResource(R.drawable.pasillo_dos);
                }

                if (step == 2) {

                    stepView.go(step, true);
                    stepView.getState()
                            .selectedCircleColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary))
                            .commit();
                    txtContenido.setText(getString(R.string.ruta_tres));
                    imgHospi.setImageResource(R.drawable.pasillo_tres);
                }
                if (step == 2) {

                    stepView.go(step, true);
                    stepView.getState()
                            .selectedCircleColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary))
                            .commit();
                    txtContenido.setText(getString(R.string.ruta_tres));
                    imgHospi.setImageResource(R.drawable.pasillo_tres);
                }
                if (step == 3) {

                    stepView.go(step, true);
                    stepView.getState()
                            .selectedCircleColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary))
                            .commit();
                    txtContenido.setText(getString(R.string.ruta_cuatro));
                    imgHospi.setImageResource(R.drawable.pasillo_cuatro);
                }

            }


        });


    }
}

