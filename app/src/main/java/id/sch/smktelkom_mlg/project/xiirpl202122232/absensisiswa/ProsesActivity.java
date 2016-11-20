package id.sch.smktelkom_mlg.project.xiirpl202122232.absensisiswa;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class ProsesActivity extends AppCompatActivity {
    ImageView absen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proses);
        absen = (ImageView) findViewById(R.id.imageView2);
        findViewById(R.id.imageView2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                absen();
            }
        });

    }

    private void absen() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppVar.LOGIN_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.contains(AppVar.LOGIN_USER)) {
                    //hideDialog();
                    int duration = Toast.LENGTH_LONG;
                    Context context = getApplicationContext();
                    //Log.d("Login", "onResponse: " + response);
                    String[] x = response.split("#");
                    String NIS = x[1];
                    String Username = x[2];
                    String Password = x[3];

                    Toast.makeText(context, "Anda telah absen", duration).show();
                    gotouser(NIS, Username, Password);
                } else {
                    int duration = Toast.LENGTH_LONG;
                    Context context = getApplicationContext();
                    //hideDialog();
                    //Displaying an error message on toast
                    Toast.makeText(context, "Gak iso", duration).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        int duration = Toast.LENGTH_LONG;
                        Context context = getApplicationContext();
                        //You can handle error here if you want
                        //hideDialog();
                        Toast.makeText(context, "The server unreachable", duration).show();
                    }

                });
        Volley.newRequestQueue(this).add(stringRequest);
    }

    private void gotouser(String nis, String username, String password) {
        Bundle b = new Bundle();
        b.putString("nis", nis);
        b.putString("username", username);
        b.putString("password", password);
        Intent in = new Intent(getApplicationContext(), CourseActivity.class);
        in.putExtras(b);
        startActivity(in);
        finish();
    }
}