package com.example.sai_bharath_kumar_bandi_project1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity<override> extends AppCompatActivity {
    private Button firstButton;
    private Button secondButton;
    private String userEnteredPhoneNumber = null;
    private TextView phone_number;
    Boolean Validity;
    Boolean secondbuttonvalidity = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstButton = (Button) findViewById(R.id.first_btn);
        firstButton.setOnClickListener(firstButtonListener);
        phone_number = (TextView) findViewById(R.id.phone_number);

        secondButton = (Button) findViewById(R.id.Second_btn);
        secondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(secondbuttonvalidity == true) {
                    if (Validity == true) {
                        Intent intent_second = new Intent();
                        intent_second.setAction(Intent.ACTION_DIAL);
                        intent_second.setData(Uri.parse("tel:" + userEnteredPhoneNumber));
                        startActivity(intent_second);
                    } else {
                        Toast t = Toast.makeText(MainActivity.this, "The number you have entered [ "+userEnteredPhoneNumber+" ] is invalid. Please Enter a valid phone number", Toast.LENGTH_SHORT);
                        t.show();
                        //Toast.makeText(MainActivity.this, "The number you have entered: [ "+userEnteredPhoneNumber+" ] is invalid. Please Enter a valid phone number", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast t1 = Toast.makeText(MainActivity.this, "Please enter the phone number before clicking the DIAL button", Toast.LENGTH_SHORT);
                    t1.show();
                }
            }
        });
    }

    public View.OnClickListener firstButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            openSecondActivity();


        }
    };

    public void openSecondActivity(){
        secondbuttonvalidity = true;
        Intent intent = new Intent(MainActivity.this,SecondActivityPhoneDial.class);
        try {
            startActivityForResult(intent,1);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    protected void onActivityResult(int i,int result_code, Intent intent_received){
        super.onActivityResult(i,result_code,intent_received);
        if(intent_received != null) {

            userEnteredPhoneNumber = intent_received.getStringExtra("phone-number");
            phone_number.setText("The phone number entered is: " + userEnteredPhoneNumber);
            if (result_code == 0) {
                Validity = false;
            }else{
                Validity = true;
            }

        }
        else{
            userEnteredPhoneNumber = "";
            phone_number.setText("The phone number entered is:");
            Validity = false;
        }
    }

}