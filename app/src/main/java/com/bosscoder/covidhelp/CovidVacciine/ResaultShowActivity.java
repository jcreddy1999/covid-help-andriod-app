package com.bosscoder.covidhelp.CovidVacciine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bosscoder.covidhelp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ResaultShowActivity extends AppCompatActivity {

    public static List<CountryModel> countryModelsList = new ArrayList<>();
    ListView listView;
    String date, pin;
    CountryModel countryModel;
    MyCustomAdapter myCustomAdapter;
    private RecyclerView recyclerView;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resault_show);

        Bundle bundle = getIntent().getExtras();
        date = bundle.getString("DATE");
        pin = bundle.getString("PIN");

        progressDialog = new ProgressDialog(ResaultShowActivity.this);
        progressDialog.setContentView(R.layout.loading);
        progressDialog.setTitle("please wait...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Tips: Please check your internet or wi-fi connection");

        listView = findViewById(R.id.recycler_view);

        fetchData();

    }

    private void fetchData() {


        String url  = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByPin?pincode="+pin+"&date="+date;

        progressDialog.show();


        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {




                        try {


                            JSONObject js = new JSONObject(response);
                            String stringy = js.getString("sessions");


                            JSONArray jsonArray = new JSONArray(stringy);

                            for(int i=0;i<jsonArray.length();i++){

                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                String name = jsonObject.getString("name");
                                String address = jsonObject.getString("address");
                                String state_name = jsonObject.getString("state_name");
                                String district_name = jsonObject.getString("district_name");
                                String pincode = jsonObject.getString("pincode");
                                String available_capacity_dose1 = jsonObject.getString("available_capacity_dose1");
                                String available_capacity_dose2 = jsonObject.getString("available_capacity_dose2");
                                String available_capacity = jsonObject.getString("available_capacity");
                                String fee = jsonObject.getString("fee");
                                String min_age_limit = jsonObject.getString("min_age_limit");
                                String vaccine = jsonObject.getString("vaccine");



                                countryModel = new CountryModel(name,address,state_name,district_name,pincode,available_capacity_dose1,available_capacity_dose2,available_capacity,fee,min_age_limit,vaccine);
                                countryModelsList.add(countryModel);


                            }

                            myCustomAdapter = new MyCustomAdapter(ResaultShowActivity.this,countryModelsList);
                            listView.setAdapter(myCustomAdapter);
                            progressDialog.dismiss();

                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(ResaultShowActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        countryModelsList.clear();
        listView.setAdapter(null);
        finish();
    }

}