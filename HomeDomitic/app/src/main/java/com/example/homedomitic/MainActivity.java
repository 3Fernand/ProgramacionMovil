package com.example.homedomitic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnIngresar, btnEstancia, btnCasa, btnElemento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnIngresar = (Button) findViewById(R.id.btnIngresar);
        btnEstancia = (Button) findViewById(R.id.btnEstancia);
        btnCasa = (Button) findViewById(R.id.btnCasa);
        btnElemento = (Button) findViewById(R.id.btnElemento);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ingresar();
            }
        });
        btnEstancia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ingresarEstancia();
            }
        });

        btnCasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ingresarCasa();
            }
        });
        btnElemento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ingresarElemento();
            }
        });
    }
    public void ingresar() {
        Intent i = new Intent(this, Cliente.class);
        startActivity(i);
    }

    public void ingresarEstancia() {
        Intent i = new Intent(this, Estancia.class);
        startActivity(i);
    }
    public void ingresarCasa() {
        Intent i = new Intent(this, Casa.class);
        startActivity(i);
    }
    public void ingresarElemento() {
        Intent i = new Intent(this, Elementos.class);
        startActivity(i);
    }
}