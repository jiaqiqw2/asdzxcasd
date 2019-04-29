package edu.illinois.cs.cs125.spring2019.lab12;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/** Default logging tag for messages from the main activity. */
public class GetRate {
    /** Default logging tag for messages from the main activity. */
    private String data = "";
    /** Default logging tag for messages from the main activity.
     *
     * @param r1 unused
     * @param r2 unused
     * @param r3 unused
     * @param r5 unused
     * @param r6 unused
     * @param r7 unused
     * @param r8 unused
     */
    public void doInBackground(String r1, String r2, String r3,
                              String r5, String r6, String r7, String r8) {
        try {
            URL url = new URL("http://data.fixer.io/api/latest?access_key=4d9839a578aed279092066bcb8addd72");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream aa = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(aa));
            String line = "";
            while (line != null) {
                line = bufferedReader.readLine();
                data = data + line;
            }
            JsonParser parser = new JsonParser();
            JsonObject result = parser.parse(data).getAsJsonObject();
            r1 = result.get("rates").getAsJsonObject().get("AUD").getAsString();
            r2 = result.get("rates").getAsJsonObject().get("CAD").getAsString();
            r3 = result.get("rates").getAsJsonObject().get("CNY").getAsString();
            r5 = result.get("rates").getAsJsonObject().get("GBP").getAsString();
            r6 = result.get("rates").getAsJsonObject().get("HKD").getAsString();
            r7 = result.get("rates").getAsJsonObject().get("JPY").getAsString();
            r8 = result.get("rates").getAsJsonObject().get("USD").getAsString();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
