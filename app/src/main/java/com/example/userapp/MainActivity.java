
package com.example.userapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView tt1;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        tt1=(TextView)findViewById(R.id.t1);

        CallApi();
    }

    private void CallApi() {

        String ApiUrl="https://jsonplaceholder.typicode.com/users";


        JsonArrayRequest request =new JsonArrayRequest(

                Request.Method.GET,
                ApiUrl,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        StringBuilder result=new StringBuilder();
                        for (int i=0;i<response.length();i++)
                        {
                            try {
                                JSONObject ob=response.getJSONObject(i);
                                String getName=ob.getString("name");
                                String getLastName=ob.getString("username");
                                String getPurpose=ob.getString("phone");
                                String getWhomToMeet=ob.getString("website");

                                result.append("name: ").append(getName).append("\n");
                                result.append("username: ").append(getLastName).append("\n");
                                result.append("phone: ").append(getPurpose).append("\n");
                                result.append(" website: ").append(getWhomToMeet).append("\n");
                                result.append("---------------------------------------------------------------\n");

                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        tt1.setText(result.toString());


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"ERROR OCCURED",Toast.LENGTH_LONG).show();
                    }

                }

        );
        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        queue.add(request);

    }


}