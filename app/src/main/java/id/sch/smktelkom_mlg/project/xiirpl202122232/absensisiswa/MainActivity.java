package id.sch.smktelkom_mlg.project.xiirpl202122232.absensisiswa;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity
{
    EditText user;
    EditText password;
    Context context;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //pDialog = new ProgressDialog(context);
        user = (EditText) findViewById(R.id.editTextUser);
        password = (EditText) findViewById(R.id.editTextPass);
        findViewById(R.id.buttonLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //coding tombol
                login();
            }
        });
    }

    private void login()
    {
        final String usr = user.getText().toString().trim();
        final String pw = password.getText().toString().trim();
        //pDialog.setMessage("Login Process...");
        //showDialog();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppVar.LOGIN_URL, new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                if(response.contains(AppVar.LOGIN_USER)) {
                    //hideDialog();
                    Log.d("Login", "onResponse: " + response);
                    String [] x = response.split("#");
                    String NIS = x[1];
                    String Username = x[2];
                    String Password = x[3];

                    gotouser(NIS, Username, Password);
                }
                else {
                    int duration = Toast.LENGTH_LONG;
                    Context context = getApplicationContext();
                    //hideDialog();
                    //Displaying an error message on toast
                    Toast.makeText(context, "Invalid username or password", duration).show();
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

                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Adding parameters to request
                params.put(AppVar.KEY_USERNAME, usr);
                params.put(AppVar.KEY_PASSWORD, pw);

                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        Volley.newRequestQueue(this).add(stringRequest);
    }


    private void gotouser(String nis, String username, String password)
    {

    }

    /*private void showDialog()
        {
            if (!pDialog.isShowing())
                pDialog.show();
        }

        private void hideDialog()
        {
            if (pDialog.isShowing())
                pDialog.dismiss();
        }*/
    }
