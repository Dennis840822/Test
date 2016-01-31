package com.example.dennis.test;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addBtn = (Button)findViewById(R.id.btn_add);
        Button readBtn = (Button)findViewById(R.id.btn_read);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDate();
               // show();測試監聽是否有成功
            }
        });

        readBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readDate();
            }
        });
    }

    public void show() {
        Toast.makeText(this,"被點擊了", Toast.LENGTH_LONG).show();
    }

    public void readDate() {
        EditText readEditText = (EditText)findViewById(R.id.et_read);
       //SharedPreferences familyTable = getSharedPreferences("family",0);
        String name = readEditText.getText().toString();

        if(TextUtils.isEmpty(name)) {
            Toast.makeText(this,"你沒有輸入文字喔~~", Toast.LENGTH_SHORT).show();
        }
        else{
            SharedPreferences familyTable = getSharedPreferences("family",0);
            String result = familyTable.getString(name, null);

            TextView resultTextView = (TextView)findViewById(R.id.tv_result);
            if(result == null)
            {
                Toast.makeText(this,"通訊錄找不到這筆資料",Toast.LENGTH_SHORT).show();
            }
            else
            {
                resultTextView.setText(name+"的電話號碼是 = "+ result);
            }
        }
    }

    public void saveDate() {
        EditText nameEditText = (EditText)findViewById(R.id.et_name);
        EditText phoneEditText = (EditText)findViewById(R.id.et_phone);

        String name = nameEditText.getText().toString();//取得字串
        String phone = phoneEditText.getText().toString();

        SharedPreferences familyTable = getSharedPreferences("fmaily",0);//建立表格
        familyTable.edit()//取得字串放入表格
                .putString(name, phone)
                .apply();
    }
}
