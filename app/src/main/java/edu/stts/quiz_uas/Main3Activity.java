package edu.stts.quiz_uas;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class Main3Activity extends AppCompatActivity {
    TextView tvlvl,tvlife,tvsoal;
    EditText et_ans;
    Button bt_ans,bt_giv;
    Random rnd=new Random();
    int jawaban=0;
    int life=5;
    int s1,s2;
    int score;
    String level,nama;
    private AppDatabase db;
    ArrayList<Pemain> arrPemain=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        tvlvl=findViewById(R.id.tv_level);
        tvlife=findViewById(R.id.tv_life);
        tvsoal=findViewById(R.id.tv_soal);
        et_ans=findViewById(R.id.et_answer);
        bt_ans=findViewById(R.id.btn_go);
        bt_giv=findViewById(R.id.btn_giveup);
        db= Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"pemaindb").build();

        if(getIntent()!=null){
            Intent pemanggil=getIntent();
            tvlvl.setText("Level :"+ pemanggil.getStringExtra("Level"));
            level=pemanggil.getStringExtra("Level");
            nama=pemanggil.getStringExtra("username");
            if(pemanggil.getStringExtra("Level").equalsIgnoreCase("Easy")){

                s1= rnd.nextInt(10);
                s2= rnd.nextInt(10);
            }
            if(pemanggil.getStringExtra("Level").equalsIgnoreCase("Medium")){

                s1= rnd.nextInt(100);
                s2= rnd.nextInt(100);
            }
            if(pemanggil.getStringExtra("Level").equalsIgnoreCase("Hard")){

                s1= rnd.nextInt(1000);
                s2= rnd.nextInt(1000);
            }

        }
        tvlife.setText("Life : "+life);


        jawaban=s1+s2;
        tvsoal.setText(s1+" + "+s2+" = ?");

        bt_ans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_ans.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(Main3Activity.this, "Field Cannot Be Empty", Toast.LENGTH_SHORT).show();
                }else{
                    if(bt_ans.getText().toString().equalsIgnoreCase("GO")){
                        if(jawaban==Integer.parseInt(et_ans.getText().toString())){
                            Toast.makeText(Main3Activity.this, "You're Right", Toast.LENGTH_SHORT).show();
                            bt_ans.setText("Done");
                        }else{
                            life-=1;
                            if(life>0){
                                Toast.makeText(Main3Activity.this, "You're Wrong", Toast.LENGTH_SHORT).show();
                            }else if(life==0){
                                Toast.makeText(Main3Activity.this, "Game Over!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Main3Activity.this,Main2Activity.class));
                            }
                            tvlife.setText("Life : "+life);
                        }
                    }else{
                        score=life*20;
                        insertDataPemain(new Pemain(level,nama,String.valueOf(score)));
                        Toast.makeText(Main3Activity.this, "You Win "+"\n"+"Your Score:"+score, Toast.LENGTH_SHORT).show();
                        Intent i= new Intent(Main3Activity.this,Main2Activity.class);
                        i.putExtra("username",nama);
                        startActivity(i);
                    }

                }

            }
        });
        bt_giv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(Main3Activity.this,Main2Activity.class);
                startActivity(i);
            }
        });
    }
    private void insertDataPemain(final Pemain p){
        new AsyncTask<Void,Void,Long>(){

            @Override
            protected Long doInBackground(Void... voids) {
                long status = db.pemainDAO().insertPemain(p);
                return status;
            }

            @Override
            protected void onPostExecute(Long status) {
                Toast.makeText(Main3Activity.this, "" +
                        "Berhasil insert dengan status: "+status, Toast.LENGTH_LONG).show();
            }
        }.execute();
    }
}
