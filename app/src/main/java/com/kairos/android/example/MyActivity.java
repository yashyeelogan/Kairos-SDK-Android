package com.kairos.android.example;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kairos.*;
import org.json.JSONException;


import java.io.UnsupportedEncodingException;

public class MyActivity extends Activity {

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // listener
        KairosListener listener = new KairosListener() {

            Button camButton = (Button) findViewById(R.id.camButton);

            @Override
            public void onSuccess(String response) {
                startActivity(new Intent(getApplicationContext(), CameraActivity.class));
                camButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, 100);
                    }
                });
                //Log.d("KAIROS DEMO", response);
            }

            @Override
            public void onFail(String response) {
                Log.d("KAIROS DEMO", response);
            }
        };


        /* * * instantiate a new kairos instance * * */
        Kairos myKairos = new Kairos();

        /* * * set authentication * * */
        String app_id = "24a7b953";
        String api_key = "75704dbd16a8c25b5e4c3638f9b57399";
        myKairos.setAuthentication(this, app_id, api_key);




        try {


            /* * * * * * * * * * * * * * * * * * * * */
            /* * *  Kairos Method Call Examples * * */
            /* * * * * * * * * * * * * * * * * * * */
            /* * * * * * * * * * * * * * * * * * **/
            /* * * * * * * * * * * * * * * * * * */
            /* * * * * * * * * * * * * * * * * **/
            /* * * * * * * * * * * * * * * * * */
            /* * * * * * * * * * * * * * * * **/
            /* * * * * * * * * * * * * * * * */


            //  List galleries
            myKairos.listGalleries(listener);


            /* * * * * * * * DETECT EXAMPLES * * * * * * *
            // Bare-essentials Example:
            // This example uses only an image url, setting optional params to null */
            String image = "http://media.kairos.com/liz.jpg";
            myKairos.detect(image, null, null, listener);
            // Fine-grained Example:
            /*/ This example uses a bitmap image and also optional parameters
            Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.liz);
            String selector = "FULL";
            String minHeadScale = "0.25";
            myKairos.detect(image, selector, minHeadScale, listener);
            */



            /* * * * * * * * ENROLL EXAMPLES * * * * * * *
            // Bare-essentials Example:
            // This example uses only an image url, setting optional params to null
            String image = "http://media.kairos.com/liz.jpg";
            String subjectId = "Elizabeth";
            String galleryId = "friends";
            myKairos.enroll(image, subjectId, galleryId, null, null, null, listener);
            // Fine-grained Example:
            // This example uses a bitmap image and also optional parameters
            Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.liz);
            String subjectId = "Elizabeth";
            String galleryId = "friends";
            String selector = "FULL";
            String multipleFaces = "false";
            String minHeadScale = "0.25";
            myKairos.enroll(image,
                    subjectId,
                    galleryId,
                    selector,
                    multipleFaces,
                    minHeadScale,
                    listener);
                    */


            /* * * * * * * RECOGNIZE EXAMPLES * * * * * * *
            // Bare-essentials Example:
            // This example uses only an image url, setting optional params to null
            String image = "http://media.kairos.com/liz.jpg";
            String galleryId = "friends";
            myKairos.recognize(image, galleryId, null, null, null, null, listener);
            // Fine-grained Example:
            // This example uses a bitmap image and also optional parameters
            Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.liz);
            String galleryId = "friends";
            String selector = "FULL";
            String threshold = "0.75";
            String minHeadScale = "0.25";
            String maxNumResults = "25";
            myKairos.recognize(image,
                    galleryId,
                    selector,
                    threshold,
                    minHeadScale,
                    maxNumResults,
                    listener);
                    */


            /* * * * GALLERY-MANAGEMENT EXAMPLES * * * *
            //  List galleries
            myKairos.listGalleries(listener);
            //  List subjects in gallery
            myKairos.listSubjectsForGallery("your_gallery_name", listener);
            // Delete subject from gallery
            myKairos.deleteSubject("your_subject_id", "your_gallery_name", listener);
            // Delete an entire gallery
            myKairos.deleteGallery("your_gallery_name", listener);
            */



        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }
}