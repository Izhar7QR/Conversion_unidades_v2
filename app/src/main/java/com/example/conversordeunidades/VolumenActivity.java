package com.example.conversordeunidades;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class VolumenActivity extends AppCompatActivity {

    private Spinner spinnerDesde;
    private Spinner spinnerHasta;
    private EditText editTextValorDesde;
    private TextView textViewResultado;

    private String[] unidadesVolumen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volumen);

        // Obtener referencias a los elementos de la interfaz
        spinnerDesde = findViewById(R.id.sp_unidad_origen);
        spinnerHasta = findViewById(R.id.sp_unidad_destino);
        editTextValorDesde = findViewById(R.id.et_volumen);
        textViewResultado = findViewById(R.id.tv_resultado);

        // Cargar las unidades de volumen desde el archivo de recursos
        unidadesVolumen = getResources().getStringArray(R.array.unidades_volumen);

        // Configurar los adaptadores de los spinners
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, unidadesVolumen);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDesde.setAdapter(adapter);
        spinnerHasta.setAdapter(adapter);
    }

    public void convertirVolumen(View view) {
        // Obtener los valores de los elementos de la interfaz
        String unidadDesde = spinnerDesde.getSelectedItem().toString();
        String unidadHasta = spinnerHasta.getSelectedItem().toString();
        String valorDesdeString = editTextValorDesde.getText().toString();

        // Validar que el valor ingresado sea un número
        if (valorDesdeString.isEmpty()) {
            Toast.makeText(this, "R.string.error_valor_vacio", Toast.LENGTH_SHORT).show();
            return;
        }
        double valorDesde = Double.parseDouble(valorDesdeString);

        // Realizar la conversión de volumen
        double valorHasta = 0;
        switch (unidadDesde) {
            case "Litros":
                switch (unidadHasta) {
                    case "Litros":
                        valorHasta = valorDesde;
                        break;
                    case "Mililitros":
                        valorHasta = valorDesde * 1000;
                        break;
                    case "Galones":
                        valorHasta = valorDesde / 3.78541;
                        break;
                    case "Onzas líquidas":
                        valorHasta = valorDesde * 33.814;
                        break;
                }
                break;
            case "Mililitros":
                switch (unidadHasta) {
                    case "Litros":
                        valorHasta = valorDesde / 1000;
                        break;
                    case "Mililitros":
                        valorHasta = valorDesde;
                        break;
                    case "Galones":
                        valorHasta = valorDesde / 3785.41;
                        break;
                    case "Onzas líquidas":
                        valorHasta = valorDesde * 0.033814;
                        break;
                }
                break;
            case "Galones":
                switch (unidadHasta) {
                    case "Litros":
                        valorHasta = valorDesde * 3.78541;
                        break;
                    case "Mililitros":
                        valorHasta = valorDesde * 3785.41;
                        break;
                    case "Galones":
                        valorHasta = valorDesde;
                        break;
                    case "Onzas líquidas":
                        valorHasta = valorDesde * 128;
                        break;
                }
                break;
            case "Onzas líquidas":
                switch (unidadHasta) {
                    case "Litros":
                        valorHasta = valorDesde * 0.0295735;
                        break;
                    case "Mililitros":
                        valorHasta = valorDesde * 29.5735;
                        break;
                    case "Galones":
                        valorHasta = valorDesde / 128;
                        break;
                    case "Onzas líquidas":
                        valorHasta = valorDesde;
                        break;
                }
                break;
        }
        // Mostrar el resultado en la interfaz
        String resultadoString = String.format("%.2f", valorHasta);
        textViewResultado.setText(resultadoString + " " + unidadHasta);
    }
}

