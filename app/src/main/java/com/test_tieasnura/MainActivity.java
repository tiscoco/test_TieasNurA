package com.test_tieasnura;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.test_tieasnura.model.Mproducts;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RequestQueue queue;
    StringRequest stringRequest;
    TextView textView;
    Mproducts Mpro;
    private List<Mproducts>mproductsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        products();
    }

    void products() {
        // Instantiate the RequestQueue.


// Instantiate the RequestQueue.
        String url = "https://fakestoreapi.com/products";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String name, rate, price, desc, image;
                        Mproducts mproducts;
//                        // Display the first 500 characters of the response string.
                        try {

                            JSONArray itto = new JSONArray(response);

                            for (int x =0; x<itto.length(); x++){
                                JSONObject A = itto.getJSONObject(x);
                                name = A.getString("");
                                rate = A.getString("");
                                price = A.getString("");
                                desc = A.getString("");

                                mproducts = new Mproducts("","","","","");
                                mproductsList.add(mproducts);

                            }

                        } catch (Exception e){

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                textView.setText("That didn't work!");
                if (!(error instanceof NetworkError | error instanceof TimeoutError)) {
                    NetworkResponse networkResponse = error.networkResponse;
                    Toast.makeText(getApplicationContext(), "Terjadi Kesalahan pada server, siahkan coba beberapa menit lagi. ", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(getApplicationContext(), "Jaringan Internet anda tidak stabil, siahkan coba beberapa menit lagi.", Toast.LENGTH_LONG).show();
                }
            }
        });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(20000,
                0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(this).add(stringRequest);

    }
}