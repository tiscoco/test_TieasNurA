package com.test_tieasnura;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.test_tieasnura.adapter.product_adapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RequestQueue queue;
    StringRequest stringRequest;
    product_adapter adapter;
    RecyclerView recyclerView;

    private List<Mproducts>mproductsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv_product);

        adapter = new product_adapter(mproductsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        products();
    }

    void products() {

        String url = "https://fakestoreapi.com/products";
        stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    String name, rate, price, desc, image;

//                        // Display the first 500 characters of the response string.
                    try {

                        JSONArray itto = new JSONArray(response);
                        for (int x =0; x<itto.length(); x++){
                            JSONObject A = itto.getJSONObject(x);

                            JSONObject rating = A.getJSONObject("rating");

                            name = A.getString("title");
                            price = A.getString("price");
                            desc = A.getString("description");
                            image = A.getString("image");
                            rate = rating.getString("rate");
//                            Log.e("diluc mana",image+name+price+desc+rate);

                            Mproducts Mpro = new Mproducts(name,price,desc,rate,image);
                            mproductsList.add(Mpro);

                        }
                        adapter.notifyDataSetChanged();
                        ((TextView)findViewById(R.id.blalba)).setText("awawkfawjfe");
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }, error -> {
    //                textView.setText("That didn't work!");
                    if (!(error instanceof NetworkError | error instanceof TimeoutError)) {
                        NetworkResponse networkResponse = error.networkResponse;
                        Toast.makeText(getApplicationContext(), "Terjadi Kesalahan pada server, siahkan coba beberapa menit lagi. ", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(getApplicationContext(), "Jaringan Internet anda tidak stabil, siahkan coba beberapa menit lagi.", Toast.LENGTH_LONG).show();
                    }
                });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(20000,
                0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(this).add(stringRequest);

    }
}