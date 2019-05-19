package edu.stts.quiz_uas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    Button btnplay;
    RadioGroup rg;
    RadioButton rb;
    TextView nama;
    String username="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btnplay=findViewById(R.id.btn_play);
        rg=findViewById(R.id.radioGroup);
        nama=findViewById(R.id.tv_username);
        if(getIntent()!=null) {
            Intent pemanggil = getIntent();
            username=pemanggil.getStringExtra("username");
            nama.setText(username);

        }

        btnplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = rg.getCheckedRadioButtonId();
                rb = (RadioButton) findViewById(selectedId);
                Intent i= new Intent(Main2Activity.this,Main3Activity.class);
                Toast.makeText(Main2Activity.this, rb.getText(), Toast.LENGTH_SHORT).show();
                i.putExtra("username",username);
                i.putExtra("Level",rb.getText());
                startActivity(i);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.option,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i=new Intent();
        if(item.getItemId()==R.id.menu_level){
            i=new Intent(this,Main2Activity.class);
        }
        if(item.getItemId()==R.id.menu_score){
            i=new Intent(this,Main4Activity.class);
        }
        if(item.getItemId()==R.id.menu_log){
           i=new Intent(this,MainActivity.class);
        }
        i.putExtra("username",username);
        startActivity(i);
        return true;
    }
}
