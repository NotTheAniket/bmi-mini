package com.netlify.helloapp.bmi;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private EditText weight, height;
    private Button btnCalculate;
    private ProgressBar progressBar;
    private TextView txtBMI;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        weight = findViewById(R.id.iptWeight);
        height = findViewById(R.id.iptHeight);
        btnCalculate = findViewById(R.id.btnCalculate);
        progressBar = findViewById(R.id.progressBar);
        txtBMI = findViewById(R.id.txtBMI);
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String weightValue = weight.getText().toString();
                String heightValue = height.getText().toString();


                if (weightValue.isEmpty() && heightValue.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Empty Fields", Toast.LENGTH_SHORT).show();
                } else {

                    Log.d("value", String.valueOf(calculateBMI(Double.parseDouble(weightValue), Double.parseDouble(heightValue))));


                    double progressbarValue = calculateBMI(Double.parseDouble(weightValue), Double.parseDouble(heightValue));
                    progressBar.setProgress(0);//initially progress is 0
                    progressBar.setMax(100);//sets the maximum value 100

                    if (progressbarValue < 18.5) {

                        progressBar.setProgress(20);
                        Toast.makeText(MainActivity.this, "Underweight", Toast.LENGTH_SHORT).show();

                    } else if (progressbarValue > 18.5 || progressbarValue < 24.9) {


                        progressBar.setProgress(50);

                        Toast.makeText(MainActivity.this, "Normal Weight", Toast.LENGTH_SHORT).show();


                    } else if (progressbarValue > 25 || progressbarValue < 29.9) {
                        progressBar.setProgress(75);
                        Toast.makeText(MainActivity.this, "Over Weight", Toast.LENGTH_SHORT).show();

                    } else {
                        progressBar.setProgress(100);

                        Toast.makeText(MainActivity.this, "Obesity", Toast.LENGTH_SHORT).show();

                    }


                    txtBMI.setText(String.valueOf(progressbarValue));


                }


            }
        });


    }


    public double calculateBMI(double weight, double height) {
        double sqrHeight = height * height;
        return weight / sqrHeight;
    }

}
