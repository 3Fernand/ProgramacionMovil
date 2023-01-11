package com.example.homedomitic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Estancia extends AppCompatActivity {
    private TextView tvest, tvUb, etNombre;
    //private EditText etNombre;
    private Spinner spNombres;
    private RadioButton rbtnPrimeraPlanta, rbtnSegundaPlanta,rbtnTerceraPlanta;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estancia);
        tvest = (TextView) findViewById(R.id.tvEst);
        tvUb = (TextView) findViewById(R.id.tvUb);
        etNombre = (TextView) findViewById(R.id.etNombre);

        spNombres = (Spinner) findViewById(R.id.spEstancia);

        rbtnPrimeraPlanta = (RadioButton) findViewById(R.id.rbtnPrimeraPlanta);
        rbtnSegundaPlanta = (RadioButton) findViewById(R.id.rbtnSegundaPlanta);
        rbtnTerceraPlanta = (RadioButton) findViewById(R.id.rbtnTerceraPlanta);

        String[] opciones = {"Selecciones habitación", "Cocina", "Sala", "Baño", "Dormitorio", "Comedor"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, opciones);
        spNombres.setAdapter(adapter);
    }
        //metodo para mostrar y ocultar el menu
        public boolean onCreateOptionsMenu (Menu menu){
            getMenuInflater().inflate(R.menu.herramientas, menu);
            //R.id.item2 = (int) findViewById (R.id.item2,menu);
            return true;
        }

        ///metodo para asignar las funciones correspondientes a las acciones
        public boolean onOptionsItemSelected (MenuItem item){
            int id = item.getItemId();

            switch (item.getItemId()){
                case android.R.id.home:
                    finish();
                    return true;
            }

            if (id == R.id.item1){
                Intent i = new Intent(this,MainActivity.class);
                startActivity(i);
                Toast.makeText(this, "Cerrarando Cesion...", Toast.LENGTH_SHORT).show();

            }else if (id == R.id.item2){
                guardar("http://192.168.1.6:8080/homedomotic/estancia/guardarEstancia.php");
                Toast.makeText(this, "Guardar", Toast.LENGTH_SHORT).show();
            }
        /*else if(id == R.id.item3){
            //Intent i = new Intent(this,MainActivity.class);
            //startActivity(i);
            Toast.makeText(this, "Buscar", Toast.LENGTH_SHORT).show();
        }else if(id == R.id.item4){
            //Intent i = new Intent(this,MainActivity.class);
            //startActivity(i);
            Toast.makeText(this, "Actualizar", Toast.LENGTH_SHORT).show();
        }*/
            return super.onOptionsItemSelected(item);
        }
        public void guardar(String URL){
            StringRequest stringRequest= new StringRequest (Request.Method.POST, URL, new Response.Listener<String>() {
                public void onResponse(String response) {

                    Toast.makeText(getApplicationContext(), "Operacion exitosa", Toast.LENGTH_SHORT).show();
                    spNombres.setSelection(0);
                    rbtnPrimeraPlanta.setChecked(false);
                    rbtnSegundaPlanta.setChecked(false);
                    rbtnTerceraPlanta.setChecked(false);
                }
            }, new Response.ErrorListener() {

                public void onErrorResponse(VolleyError error){
                    Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                }
            }){
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map <String, String> parametros=new HashMap<String, String>();
                    parametros.put("nombre",String.valueOf(spNombres.getSelectedItemPosition()));
                    parametros.put("ubicacion",piso());
                    return parametros;
                }
            };
            requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);


        }
    public String piso(){
        String piso ="";
        if(rbtnPrimeraPlanta.isChecked() == true){
            piso = "Primera Planta";
        }else if(rbtnSegundaPlanta.isChecked() == true){
            piso = "Segunda Planta";
        }else if(rbtnTerceraPlanta.isChecked() == true){
            piso = "Tercera Planta";
        }
        return piso;
    }
}