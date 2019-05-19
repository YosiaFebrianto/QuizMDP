package edu.stts.quiz_uas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etusername,etpass;
    Button btn;
    //RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etusername=findViewById(R.id.etUsername);
        etpass=findViewById(R.id.etPass);
        btn=findViewById(R.id.btn_login);
        //queue=Volley.newRequestQueue(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Cek Login
                Intent i= new Intent(MainActivity.this,Main2Activity.class);
                i.putExtra("username",etusername.getText().toString());
                startActivity(i);
            }
        });
    }
}
