package edu.stts.quiz_uas;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class Main4Activity extends AppCompatActivity {
    private AppDatabase db;
    ListView listView;
    ArrayList<Pemain> data = new ArrayList<>();
    ArrayAdapter<Pemain> myAdapter;
    String username="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        listView = findViewById(R.id.listview);

        if(getIntent()!=null) {
            Intent pemanggil = getIntent();
            username=pemanggil.getStringExtra("username");
        }

        db= Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"pemaindb").build();
        myAdapter = new ArrayAdapter<Pemain>(this, android.R.layout.simple_expandable_list_item_1, data);
        showdb();
    }
    public void showdb(){
        new AsyncTask<Void,Void, ArrayList<Pemain>>() {

            @Override
            protected ArrayList<Pemain> doInBackground(Void... voids) {
                ArrayList<Pemain> hasil = new ArrayList<>();
                hasil.addAll(Arrays.asList(db.pemainDAO().selectAllPemains()));
                return hasil;
            }
            @Override
            protected void onPostExecute(ArrayList<Pemain> barangs) {
                for (Pemain b : barangs) {
                    myAdapter.add(b);

                }
                listView.setAdapter(myAdapter);
            }
        }.execute();
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
