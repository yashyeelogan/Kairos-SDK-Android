package com.kairos.android.example;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.kairos.*;
import org.json.JSONException;


import java.io.UnsupportedEncodingException;

public class MyActivity extends Activity {

    private Intent intent;
    private Kairos myKairos = new Kairos();

    KairosListener listener = new KairosListener() {

        @Override
        public void onSuccess(String response) {
            Log.d("KAIROS DEMO", response);
        }

        @Override
        public void onFail(String response) {
            Log.d("KAIROS DEMO", response);
        }
    };

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button camButton = (Button) findViewById(R.id.camButton);
        camButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 100);
            }
        });

        /* * * set authentication * * */
        String app_id = "24a7b953";
        String api_key = "75704dbd16a8c25b5e4c3638f9b57399";
        myKairos.setAuthentication(this, app_id, api_key);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

        Bundle extras = intent.getExtras();
        Bitmap image = Bitmap.createBitmap((Bitmap) extras.get("data"));
        String subjectId = "Yash-yee";
        String galleryId = "friends";
        try {
            //myKairos.deleteSubject("Yash-yee", "friends", listener);
            //myKairos.enroll(image, subjectId, galleryId, null, null, null, listener);
            myKairos.recognize(image, galleryId, null, null, null, null, listener);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}