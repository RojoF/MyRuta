
package com.example.myruta;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    //String donde introducimos todos los states (paradas)
    private static final String[] PUNTOS_SALIDA = {
            "radiología",
            "urgencias",
            "traumatologia",
            "farmacia",
            "quirófano",
            "ascensor",
            "cuidados Intensivos",
            "vestuario",
            "esterización"
    };

    MaterialSpinner spinner, spinner_dos;
    String contador = "";
    String respuesta = "";
    int spin = -1;
    int spin_dos = -1;
    int sc = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://github.com/RojoF/MyRuta")));
                } catch (ActivityNotFoundException ignored) {
                }
            }
        });

        spinner = findViewById(R.id.spinner);
        spinner_dos = findViewById(R.id.spinner_dos);
        spinner.setItems(PUNTOS_SALIDA);

        //Listeners para añadir en una notificación inferior el item seleccionado/No seleccionado->
        //->en los spinners
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Snackbar.make(view, getString(R.string.seleccion_spin) + item, Snackbar.LENGTH_LONG).show();
                spin = position;
            }
        });

        spinner.setOnNothingSelectedListener(new MaterialSpinner.OnNothingSelectedListener() {

            @Override
            public void onNothingSelected(MaterialSpinner spinner) {
                Snackbar.make(spinner, getString(R.string.seleccion_spin_dos), Snackbar.LENGTH_LONG).show();
            }
        });
        spinner_dos.setItems(PUNTOS_SALIDA);
        spinner_dos.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Snackbar.make(view, getString(R.string.seleccion_spin) + item, Snackbar.LENGTH_LONG).show();
                spin_dos = position;
            }
        });
        spinner.setOnNothingSelectedListener(new MaterialSpinner.OnNothingSelectedListener() {

            @Override
            public void onNothingSelected(MaterialSpinner spinner) {
                Snackbar.make(spinner, getString(R.string.seleccion_spin_dos), Snackbar.LENGTH_LONG).show();
            }
        });

    }

    public void onClickRutabtn(View v) {

        // Condicional para que continue solo si hay datos seleccionados en los spinners
        if (spin >= 0 && spin_dos >= 0) {

            //Se instancia la clase Grafo_android para llamar al metodo agregarRuta
            // ** Importante ** Modificar secuencia si se quieres introducir nuevas paradas
            Grafo_Android g = new Grafo_Android(getString(R.string.secuencia));
            g.agregarRuta('r', 'u', 5);
            g.agregarRuta('r', 't', 3);
            g.agregarRuta('u', 't', 1);
            g.agregarRuta('u', 'a', 2);
            g.agregarRuta('u', 'f', 4);
            g.agregarRuta('a', 't', 7);
            g.agregarRuta('a', 'f', 8);
            g.agregarRuta('a', 'q', 8);
            g.agregarRuta('a', 'c', 4);
            g.agregarRuta('q', 'e', 1);
            g.agregarRuta('q', 'c', 2);
            g.agregarRuta('c', 'e', 2);
            g.agregarRuta('e', 'v', 2);
            g.agregarRuta('v', 'c', 2);

            // Se coje el primer caracter de la secuencia en cada spinner
            char origen = spinner.getText().charAt(0);
            char fin = spinner_dos.getText().charAt(0);

            // Se analiza la ruta mas corta entro nodo y nodo exponencialmente
            respuesta = g.encontrarRutaMinimaDijkstra(origen, fin);

            // quitar espacios entre caracteres
            StringTokenizer st = new StringTokenizer(respuesta);
            while (st.hasMoreElements()) {
                contador += st.nextElement();
            }

            //medimos la longitud de la ruta por las paradas
            sc = contador.length();

            //metodo intent para pasar valor variables entre activitys y para pasar a ->
            // -> la siguiente activity
            Intent intent = new Intent(this, StepViewActivity.class);
            intent.putExtra("respuesta", (contador));
            intent.putExtra("message", Integer.toString(sc));
            startActivity(intent);
        } else {
            Toast.makeText(MainActivity.this, getString(R.string.notification), Toast.LENGTH_LONG).show();
        }
    }
            /* Se coje el primer caracter del string origen y fin introducidos en los textView
            bucle para pintar cada paso en el textView
            for (int i = 1; i < respuesta.length(); i++) {
            char read = respuesta.charAt(i);
            if (read == 'r') {
                txtResult.append("Radiologia-> ");

            }
            if (read == 'u') {
                txtResult.append("Urgencias-> ");

            }
            if (read == 't') {
                txtResult.append("Traumatología-> ");

            }
            if (read == 'a') {
                txtResult.append("Ascensor-> ");

            }
        }*/

    public void onClickMapa(View v) {

        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public void onClickbtnReset(View v) {

        if (spinner.getText() != getString(R.string.hint_uno) ||
                spinner_dos.getText() != getString(R.string.hint_dos)) {
            spinner.setText(getString(R.string.hint_uno));
            spinner_dos.setText(getString(R.string.hint_dos));
            contador = "";
            respuesta = "";
            spin = -1;
            spin_dos = -1;
        } else {
            Toast.makeText(MainActivity.this, getString(R.string.reset), Toast.LENGTH_LONG).show();
        }
    }
}
