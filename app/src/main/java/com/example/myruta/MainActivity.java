
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
    //String donde introducimos los states
    private static final String[] PUNTOS_SALIDA = {
            "radiología",
            "urgencias",
            "traumatologia",
            "farmacia",
            "quirófano",
            "salida",
            "ascensor",
            "cuidados Intensivos",
            "vestuario",
            "esterización",
            "lavabos"
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
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Snackbar.make(view, "Has seleccionado: " + item, Snackbar.LENGTH_LONG).show();
                spin = position;
            }
        });

        spinner.setOnNothingSelectedListener(new MaterialSpinner.OnNothingSelectedListener() {

            @Override
            public void onNothingSelected(MaterialSpinner spinner) {
                Snackbar.make(spinner, "Ninguna selección", Snackbar.LENGTH_LONG).show();
            }
        });
        spinner_dos.setItems(PUNTOS_SALIDA);
        spinner_dos.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Snackbar.make(view, "Has seleccionado: " + item, Snackbar.LENGTH_LONG).show();
                spin_dos = position;
            }
        });
        spinner.setOnNothingSelectedListener(new MaterialSpinner.OnNothingSelectedListener() {

            @Override
            public void onNothingSelected(MaterialSpinner spinner) {
                Snackbar.make(spinner, "Ninguna selección", Snackbar.LENGTH_LONG).show();
            }
        });

    }

    public void onClickRutabtn(View v) {

        if (spin >= 0 && spin_dos >= 0) {

            Grafo_Android g = new Grafo_Android("ruta");
            g.agregarRuta('r', 'u', 5);
            g.agregarRuta('r', 't', 3);
            g.agregarRuta('u', 't', 1);
            g.agregarRuta('u', 'a', 2);
            g.agregarRuta('t', 'a', 7);
            g.agregarRuta('a', 'q', 6);
            g.agregarRuta('a', 'c', 4);
            g.agregarRuta('q', 'c', 2);
            g.agregarRuta('q', 'e', 1);
            g.agregarRuta('e', 'c', 3);
            g.agregarRuta('e', 'v', 2);
            g.agregarRuta('v', 'c', 7);


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
            sc -= 2;

            Intent intent = new Intent(this, StepViewActivity.class);
            intent.putExtra("respuesta", (contador));
            intent.putExtra("message", Integer.toString(sc));
            startActivity(intent);
        } else {
            Toast.makeText(MainActivity.this,
                    "Selecciona origen y destino", Toast.LENGTH_LONG).show();
        }
    }

    // Se coje el primer caracter del string origen y fin introducidos en los textView
    // bucle para pintar cada paso en el textView
            /*for (int i = 1; i < respuesta.length(); i++) {
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

        spinner.setText(getString(R.string.hint_uno));
        spinner_dos.setText(getString(R.string.hint_dos));
        contador = "";
        respuesta = "";
        spin = -1;
        spin_dos = -1;

    }
}
