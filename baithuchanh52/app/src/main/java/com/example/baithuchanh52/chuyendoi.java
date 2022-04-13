package com.example.baithuchanh52;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.baithuchanh52.sv.sinhvien;

import java.io.Serializable;

public class chuyendoi extends AppCompatActivity {
EditText cd_hoten,cd_sodienthoai, cd_email;
Button btncapnhat, btnthoat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chuyendoi);
        Anhxa();

        laydulieu();

        btncapnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hoten = cd_hoten.getText().toString();
                String sodienthoai = cd_sodienthoai.getText().toString();
                String email = cd_email.getText().toString();

//                    Intent intent = new Intent();
//                intent.putExtra("hoten", hoten);
//                intent.putExtra("sodienthoai",sodienthoai);
//                intent.putExtra("email",email);
                sinhvien sv = new sinhvien(hoten,sodienthoai,email);
                Intent intent = new Intent();
                intent.putExtra("data",sv);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btnthoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //finish();
                laydulieu();
            }
        });
    }

    private void Anhxa() {
        cd_hoten = findViewById(R.id.ac_txt_hoten);
        cd_sodienthoai = findViewById(R.id.ac_txt_dienthoai);
        cd_email = findViewById(R.id.ac_txt_email);

        btncapnhat = findViewById(R.id.ac_btn_luu);
        btnthoat = findViewById(R.id.ac_btn_thoat);
    }

    public void laydulieu() {
        Intent intent = getIntent();
        sinhvien sv1 = (sinhvien)intent.getSerializableExtra("du lieu");
        //Toast.makeText(chuyendoi.this, sv1.getHoten(),Toast.LENGTH_SHORT).show();
        cd_hoten.setText(sv1.getHoten());
        cd_sodienthoai.setText(sv1.getSodienthoai());
        cd_email.setText(sv1.getEmail());
    }
}