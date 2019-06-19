package com.meterstoinches.asynctaskdemo2;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView t1;
    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1=findViewById(R.id.t1);
        t1.setMovementMethod(new ScrollingMovementMethod());
        pb=findViewById(R.id.pb);
        pb.setVisibility(View.INVISIBLE);
    }
    public class MyTask extends AsyncTask<String,String,String>{

        @Override
        protected void onPreExecute() {
            update("starting task");
            pb.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            for (int i= 0 ; i<strings.length ; i++){
                publishProgress("working with "+ strings[i]);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "task complete";
        }

        @Override
        protected void onProgressUpdate(String... values) {
            update(values[0]);
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            update(s);
            pb.setVisibility(View.INVISIBLE);
            super.onPostExecute(s);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.action){
            MyTask task = new MyTask();
            task.execute("p1","p2","p3","p4","p5","p6");
        }
        return super.onOptionsItemSelected(item);
    }

    public void update(String message){
        t1.append(message+"\n");
    }
}
