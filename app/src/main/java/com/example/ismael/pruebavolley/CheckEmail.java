package com.example.ismael.pruebavolley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class CheckEmail extends AppCompatActivity {
    private EditText etEmail;
    private TextView tvResultado;
    private String email,url;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_email);

        etEmail = (EditText)findViewById(R.id.etEmail);
        tvResultado = (TextView)findViewById(R.id.tvResultado);
        url = "http://192.168.1.200//Los_insaciables/vista/comprobaremail.php";
        queue =Volley.newRequestQueue(this);
    }

    public void Comprobar(View view){
        email = etEmail.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                tvResultado.setText(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tvResultado.setText("UPS, algo ha ido mal");
            }
        }){
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("email",email);
                return params;
            }
        };


        queue.add(stringRequest);

    }
}
