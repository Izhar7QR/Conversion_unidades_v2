package com.example.conversordeunidades;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LongitudActivity extends AppCompatActivity {

    private Spinner spinnerDesde;
    private Spinner spinnerHasta;
    private EditText editTextValorDesde;
    private TextView textViewResultado;
    private String[] unidadesLongitud;
    private double valorHasta = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_longitud);

        // Obtener referencias a los elementos de la interfaz
        spinnerDesde = findViewById(R.id.sp_unidad_origen);
        spinnerHasta = findViewById(R.id.sp_unidad_destino);
        editTextValorDesde = findViewById(R.id.et_longitud);
        textViewResultado = findViewById(R.id.tv_resultado);

        // Cargar las unidades de longitud desde el archivo de recursos
        unidadesLongitud = getResources().getStringArray(R.array.unidades_longitud);

        // Configurar los adaptadores de los spinners
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, unidadesLongitud);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDesde.setAdapter(adapter);
        spinnerHasta.setAdapter(adapter);
    }

    public void convertirLongitud(View view) {
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

        // Realizar la conversión de longitud

        switch (unidadDesde) {
            case "Metros":
                switch (unidadHasta) {
                    case "Metros":
                        valorHasta = valorDesde;
                        break;
                    case "Kilómetros":
                        valorHasta = valorDesde / 1000;

                        break;
                    case "Centímetro":
                        valorHasta = valorDesde * 100;
                        break;
                    case "Pulgada":
                        valorHasta = valorDesde * 39.37;
                        break;
                }
                break;
            case "Kilómetros":
                switch (unidadHasta) {
                    case "Metros":
                        valorHasta = valorDesde * 1000;
                        break;
                    case "Kilómetros":
                        valorHasta = valorDesde;
                        break;
                    case "Centímetro":
                        valorHasta = valorDesde * 100000;
                        break;
                    case "Pulgada":
                        valorHasta = valorDesde * 39370.1;
                        break;
                }
                break;
            case "Centímetro":
                switch (unidadHasta) {
                    case "Metros":
                        valorHasta = valorDesde / 100;
                        break;
                    case "Kilómetros":
                        valorHasta = valorDesde / 100000;
                        break;
                    case "Centímetro":
                        valorHasta = valorDesde;
                        break;
                    case "Pulgada":
                        valorHasta = valorDesde / 2.54;
                        break;
                }
                break;
            case "Pulgada":
                switch (unidadHasta) {
                    case "Metros":
                        valorHasta = valorDesde / 39.37;
                        break;
                    case "Kilómetros":
                        valorHasta = valorDesde / 39370.1;
                        break;
                    case "Centímetro":
                        valorHasta = valorDesde * 2.54;
                        break;
                    case "Pulgada":
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

