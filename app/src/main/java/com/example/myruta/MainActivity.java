package com.example.myruta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    ImageView imageMap;
    Button btnOk,btnRestart;
    EditText txtOrigen, txtFin;
    TextView txtResult;
    boolean isImageFitToScreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageMap = (ImageView) findViewById(R.id.imgView);
        btnOk = (Button) findViewById(R.id.btnBuscar);
        txtOrigen = (EditText) findViewById(R.id.nOrigen);
        txtFin = (EditText) findViewById(R.id.nFinal);
        txtResult = (TextView) findViewById(R.id.txtResultado);
        btnRestart = (Button) findViewById(R.id.btnReset);
        imageMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Resources res = getResources();

        TabHost tabs=(TabHost)findViewById(android.R.id.tabhost);
        tabs.setup();

        TabHost.TabSpec spec=tabs.newTabSpec("mitab1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("RUTA",
                getDrawable(android.R.drawable.ic_btn_speak_now));
        tabs.addTab(spec);
        spec=tabs.newTabSpec("mitab2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("MAPA",
                getDrawable(android.R.drawable.ic_dialog_map));
        tabs.addTab(spec);

        tabs.setCurrentTab(0);

        tabs.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            public void onTabChanged(String tabId) {
                Log.i("AndroidTabsDemo", "Pulsar tag " + tabId);
            }
        });
    }
    public void onClickbtn(View v) {


        Grafo_Android g = new Grafo_Android("ruta");
        g.agregarRuta('r', 'u', 5);
        g.agregarRuta('r', 't', 3);
        g.agregarRuta('u', 'r', 5);
        g.agregarRuta('u', 't', 1);
        g.agregarRuta('u', 'a', 2);
        g.agregarRuta('t', 'r', 3);
        g.agregarRuta('t', 'u', 1);
        g.agregarRuta('t', 'a', 7);
        g.agregarRuta('a', 'u', 2);
        g.agregarRuta('a', 't', 7);

        char origen = txtOrigen.getText().charAt(0);
        char fin = txtFin.getText().charAt(0);

        String respuesta = g.encontrarRutaMinimaDijkstra(origen,fin);
        for (int i=1; i<respuesta.length(); i++){
            char read = respuesta.charAt(i);
            if (read == 'r'){
                txtResult.append("Radiologia-> ");
            }
            if (read == 'u'){
                txtResult.append("Urgencias-> ");
            }
            if (read == 't'){
                txtResult.append("Traumatología-> ");
            }
            if (read == 'a'){
                txtResult.append("Ascensor-> ");
            }
        }

        String contador= "";
        StringTokenizer st = new StringTokenizer(respuesta);
        while (st.hasMoreElements()) {
            contador += st.nextElement();
        }
        int sc = contador.length();
        sc-= 2;
        String contadorB = Integer.toString(sc);
        txtResult.append(contadorB);



    }
    public void onClickbtnReset(View v) {

        txtOrigen.setText("");
        txtFin.setText("");
        txtResult.setText("");


    }

}
