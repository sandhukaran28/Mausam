package com.example.mausam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mausam.R;
import com.example.mausam.utilities.NetworkUtils;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
   private EditText editText;
   private TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=(EditText)findViewById(R.id.query_text);
        result=(TextView)findViewById(R.id.response);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int selected=item.getItemId();
        if(selected==R.id.search){
            weatherRequest();
            //NetworkUtils.volley(this);
        }
        return super.onOptionsItemSelected(item);
    }
 private void weatherRequest(){
        result.setText("processing.......");

     OkHttpClient client =new OkHttpClient();
     Request request = new Request.Builder()
             .url("https://weatherbit-v1-mashape.p.rapidapi.com/current?lon=38.5&lat=-78.5")
             .get()
             .addHeader("x-rapidapi-key", "7e3cb23de8msh8bfba0cdc33da4fp11396ejsn84f981b6e3df")
             .addHeader("x-rapidapi-host", "weatherbit-v1-mashape.p.rapidapi.com")
             .build();

     client.newCall(request).enqueue(new Callback() {
         @Override
         public void onFailure(@NotNull Call call, @NotNull IOException e) {
             e.printStackTrace();
             result.setText("Error..........");
         }

         @Override
         public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
           if(response.isSuccessful()){
              String myResponse=response.body().string();
              MainActivity.this.runOnUiThread(new Runnable() {
                  @Override
                  public void run() {
                      result.setText(myResponse);
                  }
              });
             }
         }
     });
 }
}
