package com.example.homedomitic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData.Item;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class Cliente extends AppCompatActivity {
    private TextView tvTitulo,tv1,tv2,tv3,tv4;
    private EditText etNombres,etApellidos,etDocumento,etDireccion,etid;
    private Spinner spDocumento,spPais,spProvincia;
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvTitulo = (TextView) findViewById(R.id.tvTitulo);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        tv4 = (TextView) findViewById(R.id.tv4);

        etNombres = (EditText) findViewById(R.id.etNombres);
        etApellidos = (EditText) findViewById(R.id.etApellidos);
        etDocumento = (EditText) findViewById(R.id.etDocumento);
        etDireccion = (EditText) findViewById(R.id.etDireccion);
        etid = (EditText) findViewById(R.id.etid);

        spDocumento = (Spinner) findViewById(R.id.spDocumento);
        spPais = (Spinner) findViewById(R.id.spPais);
        spProvincia = (Spinner) findViewById(R.id.spProvincia);

        String [] opcionesDocumento = {"Tipo de documento","DNI","Pasaporte"};
        ArrayAdapter<String> adapter1= new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,opcionesDocumento);
        spDocumento.setAdapter(adapter1);

        String [] opcionesPais = {"Seleccione Pais de residencia","Ecuador","Colombia","Venezuela"};
        ArrayAdapter<String> adapter2= new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,opcionesPais);
        spPais.setAdapter(adapter2);

        String [] opcionesProvincia = {"Seleccionar Provincia de residencia",
                "Azuay",
                "Bolivar",
                "Cañar",
                "Carchi",
                "Chimborazo",
                "Cotopaxi",
                "El Oro",
                "Esmeraldas",
                "Galápagos",
                "Guayas",
                "Imbabura",
                "Loja",
                "Los Ríos",
                "Manabí",
                "Morona Santiago",
                "Napo",
                "Orellana",
                "Pastaza",
                "Pinchincha",
                "Santa Elena",
                "Santo Domingo de los Tsáchilas",
                "Sucumbíos",
                "Tungurahua",
                "Zamora Chinchipe"};
        ArrayAdapter<String> adapter3= new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,opcionesProvincia);
        spProvincia.setAdapter(adapter3);
        

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
                guardar("http://192.168.1.6:8080/homedomotic/cliente/guardar.php");
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
                etNombres.setText("");
                etApellidos.setText("");
                spDocumento.setSelection(0);
                etDocumento.setText("");
                spPais.setSelection(0);
                spProvincia.setSelection(0);
                etDireccion.setText("");
            }
        }, new Response.ErrorListener() {

            public void onErrorResponse(VolleyError error){
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            protected Map<String, String> getParams() throws AuthFailureError {
                Map <String, String> parametros=new HashMap<String, String>();
                parametros.put("nombres",etNombres.getText().toString());
                parametros.put("apellidos",etApellidos.getText().toString());
                parametros.put("TipoDocumento",String.valueOf(spDocumento.getSelectedItemPosition()));
                parametros.put("documento",etDocumento.getText().toString());
                parametros.put("pais",String.valueOf(String.valueOf(spPais.getSelectedItemPosition())));
                parametros.put("provincia",String.valueOf(spProvincia.getSelectedItemPosition()));
                parametros.put("direccion",etDireccion.getText().toString());
                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}