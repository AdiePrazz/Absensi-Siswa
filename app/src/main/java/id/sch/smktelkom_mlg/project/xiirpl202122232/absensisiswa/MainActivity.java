package id.sch.smktelkom_mlg.project.xiirpl202122232.absensisiswa;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

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
        String usr = user.getText().toString().trim();
        String pw = password.getText().toString().trim();
        //pDialog.setMessage("Login Process...");
        //showDialog();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppVar.LOGIN_URL, new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                if(response.contains(AppVar.LOGIN_USER)) {
                    //hideDialog();
                    String [] x = response.split("#");
                    String NIS = x[1];
                    String Username = x[2];
                    String Password = x[3];

                    gotouser(NIS, Username, Password);
                }
                else {
                    //hideDialog();
                    //Displaying an error message on toast
                    Toast.makeText(context, "Invalid username or password", Toast.LENGTH_LONG).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                        //hideDialog();
                        Toast.makeText(context, "The server unreachable", Toast.LENGTH_LONG).show();
                    }
                });

        Volley.newRequestQueue(this).add(stringRequest);
    }


    private void gotouser(String nis, String username, String password)
    {
        Bundle b=new Bundle();
        b.putString("nis",nis);
        b.putString("username",username);
        b.putString("password",password);
        Intent in = new Intent(getApplicationContext(), CourseActivity.class);
        in.putExtras(b);
        startActivity(in);
        finish();
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
