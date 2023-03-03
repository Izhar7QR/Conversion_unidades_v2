package com.example.conversordeunidades;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PesoActivity extends AppCompatActivity {

    private Spinner spinnerDesde;
    private Spinner spinnerHasta;
    private EditText editTextValorDesde;
    private TextView textViewResultado;

    private String[] unidadesPeso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peso);

        // Obtener referencias a los elementos de la interfaz
        spinnerDesde = findViewById(R.id.sp_unidad_origen);
        spinnerHasta = findViewById(R.id.sp_unidad_destino);
        editTextValorDesde = findViewById(R.id.et_peso);
        textViewResultado = findViewById(R.id.tv_resultado);

        // Cargar las unidades de peso desde el archivo de recursos
        unidadesPeso = getResources().getStringArray(R.array.unidades_peso);

        // Configurar los adaptadores de los spinners
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, unidadesPeso);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDesde.setAdapter(adapter);
        spinnerHasta.setAdapter(adapter);
    }

    public void convertirPeso(View view) {
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

        // Realizar la conversión de peso
        double valorHasta = 0;
        switch (unidadDesde) {
            case "Kilogramos":
                switch (unidadHasta) {
                    case "Kilogramos":
                        valorHasta = valorDesde;
                        break;
                    case "Gramos":
                        valorHasta = valorDesde * 1000;
                        break;
                    case "Libras":
                        valorHasta = valorDesde * 2.20462;
                        break;
                    case "Onzas":
                        valorHasta = valorDesde * 35.274;
                        break;
                }
                break;
            case "Gramos":
                switch (unidadHasta) {
                    case "Kilogramos":
                        valorHasta = valorDesde / 1000;
                        break;
                    case "Gramos":
                        valorHasta = valorDesde;
                        break;
                    case "Libras":
                        valorHasta = valorDesde / 453.592;
                        break;
                    case "Onzas":
                        valorHasta = valorDesde / 28.35;
                        break;
                }
                break;
            case "Libras":
                switch (unidadHasta) {
                    case "Kilogramos":
                        valorHasta = valorDesde / 2.20462;
                        break;
                    case "Gramos":
                        valorHasta = valorDesde * 453.592;
                        break;
                    case "Libras":
                        valorHasta = valorDesde;
                        break;
                    case "Onzas":
                        valorHasta = valorDesde * 16;
                        break;
                }
                break;
            case "Onzas":
                switch (unidadHasta) {
                    case "Kilogramos":
                        valorHasta = valorDesde / 35.274;
                        break;
                    case "Gramos":
                        valorHasta = valorDesde * 28.35;
                        break;
                    case "Libras":
                        valorHasta = valorDesde / 16;
                        break;
                    case "Onzas":
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

