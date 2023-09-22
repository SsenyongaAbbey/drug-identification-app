package com.example.pharma_det;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.pharma_det.ml.Model;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;

public class takepic extends AppCompatActivity {
    Button selectionBtn, predictionBtn, capturingBtn;
    TextView Result;
    Bitmap bitmap;
    ImageView imageView;
    int imageSize=224;
   TextView dosage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_takepic);

        //permission to access the camera
        getPermission();

        selectionBtn = findViewById(R.id.selectBtn);
        capturingBtn = findViewById(R.id.captureBtn);
        predictionBtn = findViewById(R.id.predictBtn);
        Result=findViewById(R.id.result);
        imageView = findViewById(R.id.imageView);
        dosage = findViewById(R.id.dose);

        predictionBtn.setOnClickListener(new View.OnClickListener() {
           @Override
          public void onClick(View v) {
//               try {
//                   Model model = Model.newInstance(getApplicationContext());
//
//                   // Creates inputs for reference.
//                   TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.UINT8);
//                   ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * imageSize * imageSize * 3);
//                   byteBuffer.order(ByteOrder.nativeOrder());
//
//                   int[] intValues = new int[imageSize * imageSize];
//                   bitmap.getPixels(intValues, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
//                   int pixel = 0;
//                   for(int i = 0; i<imageSize; i++){
//
//                       for (int j = 0; j<imageSize;j++){
//                           int val = intValues[pixel++];  //RGB
//                           byteBuffer.putFloat(((val >> 16) & 0xFF) * (1.f / 255));
//                           byteBuffer.putFloat(((val >> 8) & 0xFF) * (1.f / 255));
//                           byteBuffer.putFloat((val & 0xFF) * (1.f / 255));
//                       }
//                   }
//                   inputFeature0.loadBuffer(byteBuffer);
//
//                   // Runs model inference and gets result.
//                   Model.Outputs outputs = model.process(inputFeature0);
//                   TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();
//
//                   float[] confidence = outputFeature0.getFloatArray();
//                   //finding the index of the class with the biggest confidence
//                   int maxPos = 0;
//                   float maxConfidence = 0;
//                   for (int i=0; i<confidence.length; i++){
//                       if(confidence[i]>maxConfidence) {
//                           maxConfidence = confidence[i];
//                           maxPos = i;
//                       }
//                   }
//                   String[] classes = {"Prozac", "Prilosec"};
//                   Result.setText(classes[maxPos]);
//
//                   // Releases model resources if no longer used.
//                   model.close();
//               } catch (IOException e) {
//                   // TODO Handle the exception
//               }


////
//                try {
//                    Model model = Model.newInstance(getApplicationContext());
//
//                    // Creates inputs for reference.
//                    TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.UINT8);
//
//                    bitmap = Bitmap.createScaledBitmap(bitmap, 224, 224, true);
//                    inputFeature0.loadBuffer(TensorImage.fromBitmap(bitmap).getBuffer());
//
//                    // Runs model inference and gets result.
//                    Model.Outputs outputs = model.process(inputFeature0);
//                    TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();
//
//                    String[] classes = {"Prozac", "Prilosec"};
//               Result.setText(classes[getMax(outputFeature0.getFloatArray())]+" ");
//                    // Releases model resources if no longer used.
//                    model.close();
//                } catch (IOException e) {
//                    // TODO Handle the exception
//         }

               try {
                   Model model = Model.newInstance(getApplicationContext());

                   // Creates inputs for reference.
                   TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.UINT8);

                   //inputFeature0.loadBuffer(byteBuffer);
                   bitmap = Bitmap.createScaledBitmap(bitmap, 224, 224, true);
                    inputFeature0.loadBuffer(TensorImage.fromBitmap(bitmap).getBuffer());

                   // Runs model inference and gets result.
                   Model.Outputs outputs = model.process(inputFeature0);
                   TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

                   String[] classes = {"Prozac", "Prilosec", "Mesotrim", "Ampicillin", "Unknown"};
               Result.setText(classes[getMax(outputFeature0.getFloatArray())]+" ");

                   // Releases model resources if no longer used.
                   model.close();
               } catch (IOException e) {
                   // TODO Handle the exception
               }
          }
        });

        selectionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 10);
            }
        });

        capturingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 12);
            }
        });
    }

    void getPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(takepic.this, new String[]{Manifest.permission.CAMERA}, 11);
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 11) {
            if (grantResults.length > 0) {
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    this.getPermission();
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode==10){
            if (data!=null){
                Uri uri = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                  imageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        else if (requestCode == 12){
            bitmap = (Bitmap) data.getExtras().get("data");
           imageView.setImageBitmap(bitmap);
        }

     super.onActivityResult(requestCode, resultCode, data);
    }

    int getMax(float[] arr){
        int max=0;
        for (int i=0;i<arr.length;i++){
            if(arr[i] > arr[max]) max=i;
        }
        return max;
    }
}
