package com.udacity.gradle.builditbigger;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.InstrumentationTestCase;
import android.text.TextUtils;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;



/**
 * Created by fatoumeh on 18/06/2018.
 */

@RunWith(AndroidJUnit4.class)
public class TestJokeFetcher  extends InstrumentationTestCase {


    @Test
    public final void testFetchJokeViaEndPoint() {
        String joke="";
        FetchJokeViaEndPoint fetchJokeViaEndPoint=new FetchJokeViaEndPoint(InstrumentationRegistry.getContext()){
            @Override
            protected void onPostExecute(String result) {
                //process crashes if i dont include this override
            }
        };
        fetchJokeViaEndPoint.execute();
        try {
            joke=fetchJokeViaEndPoint.get(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        boolean isJokeEmpty=true;
        if (!TextUtils.isEmpty(joke)) {
            isJokeEmpty=false;
        }
        assertFalse(isJokeEmpty);
    }

}
