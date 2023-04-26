package com.example.unu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ActivitySuma extends AppCompatActivity {

    private EditText numberoEditText;
    private TextView resultadoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suma);

        numberoEditText = findViewById(R.id.EditTextValor1Fibonnaci);
        resultadoTextView = findViewById(R.id.TextViewResultadoFibonacci);

        Button calcularButton = findViewById(R.id.button_operar);
        calcularButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int numbero = Integer.parseInt(numberoEditText.getText().toString());
                int resultado = fibonacci(numbero);
                resultadoTextView.setText(String.valueOf(resultado));
            }
        });
    }


    private int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        int fibonacci = 1;
        int prevFib = 1;
        for (int i = 2; i < n; i++) {
            int temp = fibonacci;
            fibonacci += prevFib;
            prevFib = temp;
        }
        return fibonacci;
    }
}


