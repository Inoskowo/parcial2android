package com.example.unu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.AsyncTask;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;



public class MainActivity extends AppCompatActivity {


    private EditText editTextNombreDeUsuario, editTextContraseña;
    private Button buttonInicioSesion;

    private static final String URL_VERIFICAR_LOGIN = "http://localhost/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNombreDeUsuario = (EditText) findViewById(R.id.EditText_Usuario);
        editTextContraseña = (EditText) findViewById(R.id.EditText_Contraseña);
        buttonInicioSesion = (Button) findViewById(R.id.button_inicio_sesion);


        buttonInicioSesion.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String nombreDeUsuario = editTextNombreDeUsuario.getText().toString();
                String contraseña = editTextContraseña.getText().toString();
                new VerificarLogin().execute(nombreDeUsuario, contraseña);
            }
        });
    }
    private class VerificarLogin extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            // Crear una lista de parámetros para enviar a la página PHP
            List<NameValuePair> parametros = new ArrayList<NameValuePair>();
            parametros.add(new BasicNameValuePair("nombre_de_usuario", params[0]));
            parametros.add(new BasicNameValuePair("contraseña", params[1]));

            // Realizar la conexión HTTP y obtener la respuesta
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = jsonParser.makeHttpRequest(URL_VERIFICAR_LOGIN, "POST", parametros);

            try {
                int success = jsonObject.getInt("success");
                if (success == 1) {
                    // Inicio de sesión correcto
                    return "Inicio de sesión correcto";
                } else {
                    // Error en el inicio de sesión
                    return "Nombre de usuario o contraseña incorrectos";
                }
            } catch (JSONException e) {
                e.printStackTrace();
                return "Error en la conexión";
            }
        }

        @Override
        protected void onPostExecute(String mensaje) {
            // Mostrar el mensaje de respuesta al usuario
            Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();
        }
    }
}