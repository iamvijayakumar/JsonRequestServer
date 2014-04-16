package com.example.jsons;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONObject;

import android.support.v4.app.Fragment;
import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy); 
        HttpClient client = new DefaultHttpClient();
        HttpConnectionParams.setConnectionTimeout(client.getParams(), 10000); //Timeout Limit
        HttpResponse response;
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();
        JSONObject json1 = new JSONObject();
        try {
           
            json.put("pub_id", "ebaf89bf0f06486d8e3c711be2c4b27a");
            json.put("width", "320");
            json.put("height", "480");
            json.put("osPlatform", "Android");
            Log.e("Retrun", "jso ::  " +json.toString());
            HttpPost post = new HttpPost("http://www.mediawhite.com/mobileads/mob_adserv.php?");
            post.setHeader("Content-type", "application/json");
            StringEntity se = new StringEntity( json.toString());  
           se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            post.setEntity(se);
            response = client.execute(post);
            /*Checking response */
            if(response!=null){
                InputStream in = response.getEntity().getContent(); //Get the data in the entity
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line = null;
                StringBuilder sb=new StringBuilder();
                while((line = reader.readLine()) != null){
                    sb.append(line);
                }
                Log.e("Retrun", "response ::  " +sb.toString());
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
  
}
