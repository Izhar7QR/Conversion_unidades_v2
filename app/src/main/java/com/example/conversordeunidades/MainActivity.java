package com.example.conversordeunidades;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Vincular los botones con sus respectivas actividades
        Button btnLongitud = findViewById(R.id.btn_longitud);
        btnLongitud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LongitudActivity.class);
                startActivity(intent);
            }
        });

        Button btnPeso = findViewById(R.id.btn_peso);
        btnPeso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PesoActivity.class);
                startActivity(intent);
            }
        });

        Button btnVolumen = findViewById(R.id.btn_volumen);
        btnVolumen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, VolumenActivity.class);
                startActivity(intent);
            }
        });

        Button btnTemperatura = findViewById(R.id.btn_temperatura);
        btnTemperatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TemperaturaActivity.class);
                startActivity(intent);
            }
        });
    }
}
