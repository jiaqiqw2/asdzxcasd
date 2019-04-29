package edu.illinois.cs.cs125.spring2019.lab12;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;


import org.json.JSONException;
import org.json.JSONObject;

/**
 * Main class for our UI design lab.
 */
public final class MainActivity extends AppCompatActivity {


    /** Default logging tag for messages from the main activity. */
    private static final String TAG = "Lab12:Main";

    /** Request queue for our API requests. */
    private static RequestQueue requestQueue;
    /** Refresh button */
    private Button click;

    /**
     * Run when this activity comes to the foreground.
     *
     * @param savedInstanceState unused
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        click = (Button) findViewById(R.id.button);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                String r1 = "";
                String r2 = "";
                String r3 = "";
                String r5 = "";
                String r6 = "";
                String r7 = "";
                String r8 = "";
                GetRate process = new GetRate();
                process.doInBackground(r1, r2, r3, r5, r6, r7, r8);
                TextView t1 = (TextView) findViewById(R.id.textView5);
                t1.setText(r1);
                TextView t2 = (TextView) findViewById(R.id.textView8);
                t1.setText(r2);
                TextView t3 = (TextView) findViewById(R.id.textView11);
                t1.setText(r3);
                TextView t5 = (TextView) findViewById(R.id.textView17);
                t1.setText(r5);
                TextView t6 = (TextView) findViewById(R.id.textView20);
                t1.setText(r6);
                TextView t7 = (TextView) findViewById(R.id.textView23);
                t1.setText(r7);
                TextView t8 = (TextView) findViewById(R.id.textView26);
                t1.setText(r8);
            }
        });
    }

    /**
     * Run when this activity is no longer visible.
     */
    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * Make a call to the IP geolocation API.
     *
     * @param ipAddress IP address to look up
     */
    void startAPICall(final String ipAddress) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "https://ipinfo.io/" + ipAddress + "/json",
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            apiCallDone(response);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(final VolleyError error) {
                            Log.e(TAG, error.toString());
                        }
                    });
            jsonObjectRequest.setShouldCache(false);
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Handle the response from our IP geolocation API.
     *
     * @param response response from our IP geolocation API.
     */
    void apiCallDone(final JSONObject response) {
        try {
            Log.d(TAG, response.toString(2));
            // Example of how to pull a field off the returned JSON object
            Log.i(TAG, response.get("hostname").toString());
        } catch (JSONException ignored) { }
    }
}
