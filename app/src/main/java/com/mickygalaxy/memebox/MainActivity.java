package com.mickygalaxy.memebox;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.target.ViewTarget;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

  //  ImageView imageView;
    //TextView textView;
    String URL=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        //imageView = (ImageView) findViewById(R.id.imageView);
        //textView = (TextView) findViewById(R.id.text);
// ...

// Instantiate the RequestQueue.
      //  RequestQueue queue = Volley.newRequestQueue(this);
        //String url = "https://meme-api.herokuapp.com/gimme";

// Request a string response from the provided URL.

loadMeme();

    }


   private void loadMeme(){
        ProgressBar simpleProgressBar = (ProgressBar) findViewById(R.id.progressBar);
       simpleProgressBar.setVisibility(View.VISIBLE);
       ImageView imageView;
       TextView textView;
         imageView = (ImageView) findViewById(R.id.imageView);
         textView = (TextView) findViewById(R.id.text);
      // final String URl ;
// ...

// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://meme-api.herokuapp.com/gimme";
       queue.start();


// Request a string response from the provided URL.
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url, null, response -> {

            //String URL = " ";


            try {
                URL = response.getString("url");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Glide.with(this).load(URL).listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    simpleProgressBar.setVisibility(View.GONE);
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    simpleProgressBar.setVisibility(View.GONE);
                    return false;
                }
            }).into(imageView);


            // Glide.with(MainActivity.this).load(URL).into(imageView);
            //Glide.with(MainActivity.this).load(url).into(imageView);
            //  Glide.with(this).load(url).into(imageView);
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue.add(stringRequest);

    }


    public void shareMeme(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plane");
        intent.putExtra(Intent.EXTRA_TEXT,"Hey, Checkout this cool meme"+URL);
        startActivity(Intent.createChooser(intent,"Share This Meme Using....."));
    }

    public void nextMeme(View view) {
        loadMeme();
    }
}