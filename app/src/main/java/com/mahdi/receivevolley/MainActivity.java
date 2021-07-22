package com.mahdi.receivevolley;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mahdi.receivevolley.recyclerview.adapters.CustomAdabter;
import com.mahdi.receivevolley.recyclerview.model.Model;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    private static String Url = "http://10.0.3.2/my/uploadimage/filefetchVolley.php";
    private List<Model> list1;
    private RecyclerView recyclerView1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView1 = findViewById(R.id.recyclerView1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        list1 = new ArrayList<>();



        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray jsonArray = object.getJSONArray("date");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject ob = jsonArray.getJSONObject(i);
                        Model model = new Model(ob.getString("name"), ob.getString("image"));
                        list1.add(model);
                    }

                    recyclerView1.setAdapter(new CustomAdabter(MainActivity.this, list1));

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("m1", error.toString());
            }
        });
        queue.add(stringRequest);

    }


}