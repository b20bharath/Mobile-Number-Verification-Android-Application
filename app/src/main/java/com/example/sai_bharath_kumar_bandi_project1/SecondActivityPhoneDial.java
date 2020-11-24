package com.example.sai_bharath_kumar_bandi_project1;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Pattern;

public class SecondActivityPhoneDial extends AppCompatActivity {
    private EditText phone_number;
    private static final String regex_phn = "^\\+?[1]? ?\\(?[0-9]{3}\\)? ?-?[0-9]{3} ?-?[0-9]{4}";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_phone_dial);
        phone_number = (EditText) findViewById(R.id.phnnum);
        phone_number.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if((i == EditorInfo.IME_ACTION_DONE) || (keyEvent!=null && keyEvent.getKeyCode() == keyEvent.KEYCODE_ENTER)){
                    String p = phone_number.getText().toString();
                    Intent intent2 = getIntent();
                    Boolean flag = true;
                    if (p != null && !TextUtils.isEmpty(p)){
                        p = p.trim();
                    }
                    else {
                        flag = false;
                    }
                    intent2.putExtra("phone-number",p);
                    if(flag == true && p.matches(regex_phn)){
                        setResult(RESULT_OK,intent2);
                        finish();
                    }
                    else{
                        setResult(RESULT_CANCELED,intent2);
                        finish();
                    }

                }
                return false;
            }
        });
    }
}