package com.bekup.ooi.carigadget;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ParseException;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class DetailPhone extends AppCompatActivity {
    String strJudul,strType,strUkuran,strResolusi,strOS,strTypeScreen;
    TextView judul;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_phone);
        String slug= getIntent().getStringExtra("slug");
        System.out.println("Slugnya" + slug);
        new DetailPhone.JSONAsyncTask().execute("http://ibacor.com/api/gsm-arena?view=product&slug=" + slug);

    }
    class JSONAsyncTask extends AsyncTask<String, Void, Boolean> {


        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(DetailPhone.this);
            dialog.setMessage("Sedang Mengambil Data...");
            dialog.show();
            dialog.setCancelable(false);
        }

        @Override
        protected Boolean doInBackground(String... urls) {
            try {

                //------------------>>
                HttpGet httppost = new HttpGet(urls[0]);
                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse response = httpclient.execute(httppost);

                // StatusLine stat = response.getStatusLine();
                int status = response.getStatusLine().getStatusCode();

                if (status == 200) {
                    HttpEntity entity = response.getEntity();
                    String data = EntityUtils.toString(entity);
                    JSONObject jsono = new JSONObject(data);
                    JSONObject deskripsi = jsono.getJSONObject("data");
                    JSONObject body = deskripsi.getJSONObject("body");
                    JSONObject display = deskripsi.getJSONObject("display");
                    JSONObject platform = deskripsi.getJSONObject("platform");
                    strJudul = jsono.getString("title");
                    strType=display.getString("type");
                    strUkuran=display.getString("size");
                    strResolusi=display.getString("resolution");
                    strTypeScreen=display.getString("multitouch");
                    strOS=platform.getString("os");

                    return true;
                }

                //------------------>>

            } catch (ParseException e1) {
                e1.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return false;
        }

        protected void onPostExecute(Boolean result) {
            ((TextView) findViewById(R.id.djudul)).setText(strJudul);
            ((TextView) findViewById(R.id.dukuran)).setText("UKURAN : " +strUkuran);
            ((TextView) findViewById(R.id.dtype)).setText("TIPE : " + strType);
            ((TextView) findViewById(R.id.dresolusi)).setText("RESOLUSI : " +strResolusi);
            ((TextView) findViewById(R.id.dos)).setText("OS : " + strOS);
            ((TextView) findViewById(R.id.dscreen)).setText("TOUCH SCREEN : " +strTypeScreen);
            dialog.cancel();
            if(result == false)
                Toast.makeText(DetailPhone.this, "Unable to fetch data from server", Toast.LENGTH_LONG).show();

        }
    }


}
