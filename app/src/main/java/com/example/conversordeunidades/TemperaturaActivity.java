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

public class TemperaturaActivity extends AppCompatActivity {

    private Spinner spinnerDesde;
    private Spinner spinnerHasta;
    private EditText editTextValorDesde;
    private TextView textViewResultado;

    private String[] unidadesTemperatura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperatura);

        // Obtener referencias a los elementos de la interfaz
        spinnerDesde = findViewById(R.id.sp_unidad_origen);
        spinnerHasta = findViewById(R.id.sp_unidad_destino);
        editTextValorDesde = findViewById(R.id.et_temperatura);
        textViewResultado = findViewById(R.id.tv_resultado);

        // Cargar las unidades de temperatura desde el archivo de recursos
        unidadesTemperatura = getResources().getStringArray(R.array.unidades_temperatura);

        // Configurar los adaptadores de los spinners
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, unidadesTemperatura);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDesde.setAdapter(adapter);
        spinnerHasta.setAdapter(adapter);
    }

    public void convertirTemperatura(View view) {
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

        // Realizar la conversión de temperatura
        double valorHasta = 0;
        switch (unidadDesde) {
            case "Celsius":
                switch (unidadHasta) {
                    case "Celsius":
                        valorHasta = valorDesde;
                        break;
                    case "Fahrenheit":
                        valorHasta = (valorDesde * 1.8) + 32;
                        break;
                    case "Kelvin":
                        valorHasta = valorDesde + 273.15;
                        break;
                }
                break;
            case "Fahrenheit":
                switch (unidadHasta) {
                    case "Celsius":
                        valorHasta = (valorDesde - 32) / 1.8;
                        break;
                    case "Fahrenheit":
                        valorHasta = valorDesde;
                        break;
                    case "Kelvin":
                        valorHasta = (valorDesde + 459.67) / 1.8;
                        break;
                }
                break;
            case "Kelvin":
                switch (unidadHasta) {
                    case "Celsius":
                        valorHasta = valorDesde - 273.15;
                        break;
                    case "Fahrenheit":
                        valorHasta = (valorDesde * 1.8) - 459.67;
                        break;
                    case "Kelvin":
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
