package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.displayjoke.DisplayJokeActivity;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

/**
 * Created by fatoumeh on 18/06/2018.
 */

public class FetchJokeViaEndPoint extends AsyncTask<Void, Void, String> {
    private MyApi myApiService = null;
    private static String LOG_TAG=MainActivity.class.getSimpleName();
    private Context context;

    public FetchJokeViaEndPoint(Context context) {
        this.context=context;
    }
    @Override
    protected String doInBackground(Void...voids) {
        if(myApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            myApiService = builder.build();
        }

        try {
            return myApiService.getJoke().execute().getMyJoke();
        } catch (IOException e) {
            Log.d(LOG_TAG, context.getString(R.string.error_nojoke) + e.toString());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if (result==null) {
            Toast.makeText(context, context.getString(R.string.error_nojoke), Toast.LENGTH_LONG).show();
        } else {
            Intent displayJoke=new Intent(context, DisplayJokeActivity.class);
            displayJoke.putExtra(context.getString(R.string.joke), result);
            context.startActivity(displayJoke);
        }
    }
}