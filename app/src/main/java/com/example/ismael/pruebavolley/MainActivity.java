package com.example.ismael.pruebavolley;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private String cadena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //final TextView tv = (TextView)findViewById(R.id.textView);
        ListView lv = (ListView)findViewById(R.id.list);
        final ArrayList<JSONObject> jsonlist = new ArrayList<>();
        ArrayAdapter simpleAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,jsonlist);
        lv.setAdapter(simpleAdapter);
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.200:60000/Los_insaciables/app/MostrarEncuestas.php";



        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    //JSONObject jsonObject = response.getJSONObject(0);
                    //JSONArray jsonArray = response.getJSONArray(1);
                    cadena = "";
                    for(int i = 0; i < response.length();i++){
                        JSONObject job = response.getJSONObject(i);
                        jsonlist.add(job);
                        cadena += job.getString("TITULO") + "\n";
                    }
                    Log.d("cadena",cadena);
                    //tv.setText(cadena);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("Error",e.getMessage());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //tv.setText("Error");
            }
        });

        queue.add(jsonArrayRequest);

    }

    public void LanzarCheckEmail(View view){
        Intent intent = new Intent(this,CheckEmail.class);
        startActivity(intent);
    }

}
