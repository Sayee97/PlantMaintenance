package com.example.plantmaintenance;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import com.example.plantmaintenance.Helper.GraphicOverlay;
import com.example.plantmaintenance.Helper.TextGraphic;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionCloudTextRecognizerOptions;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;
import com.wonderkiln.camerakit.CameraKitError;
import com.wonderkiln.camerakit.CameraKitEvent;
import com.wonderkiln.camerakit.CameraKitEventListener;
import com.wonderkiln.camerakit.CameraKitImage;
import com.wonderkiln.camerakit.CameraKitVideo;
import com.wonderkiln.camerakit.CameraView;

import dmax.dialog.SpotsDialog;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class
Detect extends AppCompatActivity {
CameraView cameraView;
GraphicOverlay graphicOverlay;


Button b;
android.app.AlertDialog waitingDialog;
    @Override
    protected void onResume() {
        super.onResume();
        cameraView.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        cameraView.stop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detect);
        cameraView=(CameraView)findViewById(R.id.cameraView);
        graphicOverlay=(GraphicOverlay)findViewById(R.id.graphicOverlay);
        waitingDialog=new SpotsDialog.Builder().setCancelable(false).setMessage("Pls wait").setContext(this).build();
        b=(Button)findViewById(R.id.recognize);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraView.start();
                cameraView.captureImage();
                graphicOverlay.clear();
            }
        });
        cameraView.addCameraKitListener(new CameraKitEventListener() {
            @Override
            public void onEvent(CameraKitEvent cameraKitEvent) {

            }

            @Override
            public void onError(CameraKitError cameraKitError) {

            }

            @Override
            public void onImage(CameraKitImage cameraKitImage) {
waitingDialog.show();
                Bitmap btmap=cameraKitImage.getBitmap();
                btmap=Bitmap.createScaledBitmap(btmap,cameraView.getHeight(),cameraView.getHeight(),false);
                cameraView.stop();
                processImage(btmap);
            }

            @Override
            public void onVideo(CameraKitVideo cameraKitVideo) {

            }
        });
    }

    private void processImage(Bitmap btmap) {

       /* FirebaseVisionImage image=FirebaseVisionImage.fromBitmap(btmap);
      // FirebaseVisionCloudTextRecognizerOptions options=new FirebaseVisionCloudTextRecognizerOptions.Builder().setLanguageHints(Arrays.asList("en")).build();
        FirebaseVisionTextRecognizer detector = FirebaseVision.getInstance()
                .getOnDeviceTextRecognizer();
        //FirebaseVisionTextRecognizer textRecognizer=FirebaseVision.getInstance().getCloudTextRecognizer();


        //Task<FirebaseVisionText> result =
                detector.processImage(image)
                        .addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
                            @Override
                            public void onSuccess(FirebaseVisionText firebaseVisionText) {
                                // Task completed successfully
                                // ...
                                drawTextResult(firebaseVisionText);
                                Log.v("Inside Detection","Sayee");
                            }
                        })
                        .addOnFailureListener(
                                new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        // Task failed with an exception
                                        // ...
                                        Toast.makeText(getApplicationContext(),"fail",Toast.LENGTH_LONG).show();
                                    }
                                });




       //// cloud paid h bro!!!!
      *//* // FirebaseVisionCloudTextRecognizerOptions textRecognizer=FirebaseVision.getInstance().getCloudTextRecognizer(options);
        textRecognizer.processImage(image).addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
            @Override
            public void onSuccess(FirebaseVisionText firebaseVisionText) {
                drawTextResult(firebaseVisionText);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });*//*
      ///////No use*/
//////////////////////////////////////main
        FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(btmap);
        FirebaseVisionTextRecognizer detector = FirebaseVision.getInstance().getOnDeviceTextRecognizer();
        detector.processImage(image)
                .addOnSuccessListener(
                        new OnSuccessListener<FirebaseVisionText>() {
                            @Override
                            public void onSuccess(FirebaseVisionText texts) {
                                drawTextResult(texts);
                            }
                        })
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Task failed with an exception
                                e.printStackTrace();
                            }
                        });
    }



    private void drawTextResult(FirebaseVisionText texts) {
        waitingDialog.dismiss();
        Log.v("sayee",texts.getText().toString());

        List<FirebaseVisionText.TextBlock> blocks = texts.getTextBlocks();
        if (blocks.size() == 0) {
            Log.d("TAG", "No text found");
            return;
        }
       // graphicOverlay.clear();
        for (int i = 0; i < blocks.size(); i++) {
            List<FirebaseVisionText.Line> lines = blocks.get(i).getLines();
            for (int j = 0; j < lines.size(); j++) {
                List<FirebaseVisionText.Element> elements = lines.get(j).getElements();
                for (int k = 0; k < elements.size(); k++) {
                    GraphicOverlay.Graphic textGraphic = new TextGraphic(graphicOverlay, elements.get(k));
                   // Log.v("lines_sayee",);
                    graphicOverlay.add(textGraphic);


                }
            }
        }
        String s1=texts.getText().toString().trim();


        Intent intent = new Intent(getBaseContext(), BoilerDetails.class);
        intent.putExtra("boilerID", s1);
        startActivity(intent);
    }
}
