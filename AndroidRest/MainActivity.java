package com.example.jbt.restconsumer;

import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public void onClick(View view) {
//        switch (view.getId())
//        {
//            case R.id.btn1:
//
//                break;
//
//        }
//        if (view.getId() == R.id.btn1)
//        {
//
//        }
        Log.e("1", " =====================================");
        RequestParams rp = new RequestParams();
        rp.add("username", "aaa"); rp.add("password", "aaa@123");

        HttpUtils.get("https://jsonplaceholder.typicode.com/posts", rp, new JsonHttpResponseHandler() {
            //   HttpUtils.get("https://randomuser.me/api", rp, new JsonHttpResponseHandler() {

            public void onSuccess(int statusCode, PreferenceActivity.Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                Log.d("asd", "---------------- this is response : " + response);
                try {
                    JSONObject serverResp = new JSONObject(response.toString());
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
                // Pull out the first event on the public timeline
                Log.d("asd", "---------------- this is response : " + timeline);
                try {

                    for (int i = 0; i < timeline.length(); i++)
                    {
                        JSONObject serverResp = new JSONObject(timeline.getJSONObject(i).toString());
                        Log.d(String.valueOf(i), serverResp.toString());
                    }

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        });
    }
}
