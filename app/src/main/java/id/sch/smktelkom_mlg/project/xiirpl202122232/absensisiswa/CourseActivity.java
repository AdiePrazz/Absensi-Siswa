package id.sch.smktelkom_mlg.project.xiirpl202122232.absensisiswa;

/**
 * Created by A on 08/11/2016.
 */
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CourseActivity extends AppCompatActivity implements View.OnClickListener{

    Button blogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.akun);

        Intent in=getIntent();
        Bundle b=in.getExtras();
        String s=b.getString("nis");
        String u=b.getString("username");
        String p=b.getString("password");

        blogout = (Button) findViewById(R.id.btn_logout);

        blogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_logout:

                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setMessage("Apakah anda ingin LogOut?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Ya",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                                startActivity(new Intent(CourseActivity.this, MainActivity.class));
                            }
                        });

                builder1.setNegativeButton(
                        "Tidak",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();

                break;

        }

    }
}
