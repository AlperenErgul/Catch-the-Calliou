package com.alperenergul.catchthecalliou;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    TextView textView , textView2;
    int score;
    ImageView imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);

        imageView1=findViewById(R.id.imageView1);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);
        imageView4=findViewById(R.id.imageView4);
        imageView5=findViewById(R.id.imageView5);
        imageView6=findViewById(R.id.imageView6);
        imageView7=findViewById(R.id.imageView7);
        imageView8=findViewById(R.id.imageView8);
        imageView9=findViewById(R.id.imageView9);

        imageArray = new ImageView[]{imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9};

        hideImages();

        score=0;

       new CountDownTimer(10000,100){

           @Override
           public void onTick(long millisUntilFinished) {
             textView.setText("Time : " + millisUntilFinished/1000);
           }

           @Override
           public void onFinish() {
               textView.setText("Time is Finish");
               handler.removeCallbacks(runnable);

               for (ImageView image1 : imageArray){
                   image1.setVisibility(View.INVISIBLE);
               }

               AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
               alert.setTitle("Restart");
               alert.setMessage("Are you sure restart game?");
               alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {

                       Intent intent = getIntent();
                       finish();
                       startActivity(intent);
                   }
               });

               alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       Toast.makeText(MainActivity.this,"Game Over",Toast.LENGTH_LONG).show();
                   }
               });
                alert.show();
           }
       }.start();

    }

    public void incraseScore(View view){

      score=score+5;
      textView2.setText("Score : " + score);

    }

   public void hideImages(){

        handler = new Handler();

        runnable= new Runnable() {
            @Override
            public void run() {
                for (ImageView image1 : imageArray){
                    image1.setVisibility(View.INVISIBLE);
                }

                Random random = new Random();
                int i = random.nextInt(9);
                imageArray[i] .setVisibility(View.VISIBLE);
                handler.postDelayed(this,350);



            }
        };
        handler.post(runnable);



   }

}