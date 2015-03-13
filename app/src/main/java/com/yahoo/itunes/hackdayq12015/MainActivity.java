package com.yahoo.itunes.hackdayq12015;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    ListView listView;
    Button button1;
    Dialog dialog;
    BaseAdapter2 adapter;
    Button button2;
    Button goButton;
    Button sButton;
    Button mButton;
    Button tButton;
    Button bButton;
    Button aButton;

    String query1 = "s";
    String querySet;
    String query2 = "s";
    final Context context = this;
    ArrayList<String> title_array = new ArrayList<String>();
    ArrayList<String> score_array = new ArrayList<String>();
    EditText searchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.list);
        button1 = (Button) findViewById(R.id.butt1);
        button2 = (Button) findViewById(R.id.butt2);
        goButton = (Button) findViewById(R.id.searchIt);
        searchText = (EditText) findViewById(R.id.searchText);

        button1.setText("Artist:Song");
        button2.setText("Artist:Song");


        button2.setOnClickListener(onClickListener);
        button1.setOnClickListener(onClickListener);
        goButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AsyncTaskRunner runner = new AsyncTaskRunner();
                String tempUrl = "http://yst-lab-ss06.ysl.corp.gq1.yahoo.com:8089/search?input=" + query1 + "&output=" + query2 + "&query=" + searchText.getText().toString();

                runner.execute(tempUrl.replaceAll(" ", "%20"));

            }
        });
    }


    public View.OnClickListener onInnerClickListener2 = new View.OnClickListener() {
        @Override

        public void onClick(View songView) {
            String text = null;
            switch (songView.getId()) {
                case R.id.song:
                    query2 = "s";
                    text = sButton.getText().toString();
                    break;
                case R.id.movie:
                    query2 = "m";
                    text = mButton.getText().toString();
                    break;
                case R.id.app:
                    query2 = "a";
                    text = aButton.getText().toString();
                    break;
                case R.id.tvShow:
                    query2 = "t";
                    text = tButton.getText().toString();
                    break;
                case R.id.book:
                    query2 = "b";
                    text = bButton.getText().toString();
                    break;
                default:
                    query2 = "s";
                    text = sButton.getText().toString();
                    break;

            }
            button2.setText(text);
            dialog.dismiss();
        }
    };

    public View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {


            // create a Dialog component
            dialog = new Dialog(context);

            Log.d("the idddddddddddd ", "" + view.getId());
            //tell the Dialog to use the dialog.xml as it's layout description
            dialog.setContentView(R.layout.activity_list_view_android_example);
            sButton = (Button) dialog.findViewById(R.id.song);
            mButton = (Button) dialog.findViewById(R.id.movie);
            bButton = (Button) dialog.findViewById(R.id.book);
            tButton = (Button) dialog.findViewById(R.id.tvShow);
            aButton = (Button) dialog.findViewById(R.id.app);

            if (view.getId() == R.id.butt1) {
                sButton.setOnClickListener(onInnerClickListener);
                tButton.setOnClickListener(onInnerClickListener);
                bButton.setOnClickListener(onInnerClickListener);
                mButton.setOnClickListener(onInnerClickListener);
                aButton.setOnClickListener(onInnerClickListener);


            } else if (view.getId() == R.id.butt2) {
                sButton.setOnClickListener(onInnerClickListener2);
                tButton.setOnClickListener(onInnerClickListener2);
                bButton.setOnClickListener(onInnerClickListener2);
                mButton.setOnClickListener(onInnerClickListener2);
                aButton.setOnClickListener(onInnerClickListener2);

            }
            dialog.setTitle("Select option");


            dialog.show();


        }

    };

    public View.OnClickListener onInnerClickListener = new View.OnClickListener() {
        @Override

        public void onClick(View songView) {
            String text = null;
            switch (songView.getId()) {
                case R.id.song:
                    query1 = "s";
                    text = sButton.getText().toString();
                    break;
                case R.id.movie:
                    query1 = "m";
                    text = mButton.getText().toString();
                    break;
                case R.id.app:
                    query1 = "a";
                    text = aButton.getText().toString();
                    break;
                case R.id.tvShow:
                    query1 = "t";
                    text = tButton.getText().toString();
                    break;
                case R.id.book:
                    query1 = "b";
                    text = bButton.getText().toString();
                    break;
                default:
                    query1 = "s";
                    text = sButton.getText().toString();
                    break;
            }
            button1.setText(text);
            dialog.dismiss();
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        private String resp;

        @Override
        protected String doInBackground(String... params) {
            //  publishProgress("Sleeping..."); // Calls onProgressUpdate()
            InputStream inputStream = null;
            String result = "";
            try {

                // create HttpClient
                HttpClient httpclient = new DefaultHttpClient();


                // make GET request to the given URL
                Log.d("", params[0] + "-----------------" + "sdajsdalskdjalskdj");

                HttpResponse httpResponse = httpclient.execute(new HttpGet(params[0]));
                inputStream = httpResponse.getEntity().getContent();
                // convert inputstream to string
                if (inputStream != null)
                    result = convertInputStreamToString(inputStream);
                else
                    result = "Did not work!";


                // receive response as inputStream

            } catch (Exception e) {
                e.printStackTrace();
                result = e.getMessage();
            }
            return result;
        }

        private String convertInputStreamToString(InputStream inputStream) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            String result = "";
            while ((line = bufferedReader.readLine()) != null)
                result += line;

            inputStream.close();
            return result;

        }


        /*
         * (non-Javadoc)
         *
         * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
         */
        @Override
        protected void onPostExecute(String result) {
            // execution of result of Long time consuming operation
            String response;
            title_array.clear();
            if (result.equals("[]")) {
                response = "[{id: 'No results', cosine: --}]";

            } else {
                response = result.toString();
            }
            try {


                JSONArray new_array = new JSONArray(response);

                for (int i = 0, count = new_array.length(); i < count; i++) {
                    try {
                        JSONObject jsonObject = new_array.getJSONObject(i);
                        title_array.add(jsonObject.getString("id").toString());
                        score_array.add(jsonObject.getString("cosine").toString());


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                adapter = new BaseAdapter2(MainActivity.this, title_array, score_array);
                listView.setAdapter(adapter);

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                // tv.setText("error2");
            }

        }


        /*
         * (non-Javadoc)
         *
         * @see android.os.AsyncTask#onPreExecute()
         */
        @Override
        protected void onPreExecute() {
            // Things to be done before execution of long running operation. For
            // example showing ProgessDialog
        }

        /*
         * (non-Javadoc)
         *
         * @see android.os.AsyncTask#onProgressUpdate(Progress[])
         */
        @Override
        protected void onProgressUpdate(String... text) {
            searchText.setText(text[0]);
            // Things to be done while execution of long running operation is in
            // progress. For example updating ProgessDialog
        }
    }
}
