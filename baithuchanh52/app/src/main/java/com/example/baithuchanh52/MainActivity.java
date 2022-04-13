package com.example.baithuchanh52;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baithuchanh52.sv.sinhvien;

import java.awt.font.TextAttribute;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    int id;
    EditText txthoten, txtsdt, txtemail;
    Button btnxoatrong, btnluu, btntimkiem, btncapnhat;
    int vt;
    ListView lst_listview;

    public static final int REQUEST_CODE = -1;

    //khai báo arraylist
    ArrayList<sinhvien> arrsinhvien;
    // Khai báo adapter
    adapterSV myadapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Anhxa();
        // KHởi tạo  Arraylist;
        arrsinhvien = new ArrayList<sinhvien>();
        //khởi tạo adapter
        myadapter = new adapterSV(this,R.layout.item_layout,arrsinhvien);
        //set adapter cho list view
        lst_listview.setAdapter(myadapter);
        // đăng kí menu
        registerForContextMenu(lst_listview);

        //sửa note: bắt sự kiện
        lst_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                id = i;
                txthoten.setText(arrsinhvien.get(i).getHoten());
                txtsdt.setText(arrsinhvien.get(i).getSodienthoai());
                txtemail.setText(arrsinhvien.get(i).getEmail());

            }
        });

        lst_listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                vt = i;
                return false;
            }
        });


    }

    private void Anhxa() {

        txthoten = findViewById(R.id.am_txt_hoten);
        txtsdt = findViewById(R.id.am_txt_sdt);
        txtemail = findViewById(R.id.am_txt_email);

        btnxoatrong = findViewById(R.id.am_btn_xoatrong);
        btncapnhat = findViewById(R.id.am_btn_capnhat);
        btnluu = findViewById(R.id.am_btn_luu);
        btntimkiem = findViewById(R.id.am_btn_timkiem);
        lst_listview = findViewById(R.id.am_lst_listview);
        btncapnhat.setOnClickListener(this);
        btnluu.setOnClickListener(this);
        btntimkiem.setOnClickListener(this);
        btnxoatrong.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.am_btn_luu:
                //Thêm
                String hoten = txthoten.getText().toString();
                String sodienthoai = txtsdt.getText().toString();
                String email = txtemail.getText().toString();

                sinhvien sv = new sinhvien(hoten,sodienthoai,email);

                    arrsinhvien.add(sv);

                myadapter.notifyDataSetChanged();

                break;
            case R.id.am_btn_capnhat:
                String tensua = txthoten.getText().toString();
                String sodienthoaisua = txtsdt.getText().toString();
                String emailsua = txtemail.getText().toString();

                //tạo đối tượng
                sinhvien svsua = new sinhvien(tensua,sodienthoaisua,emailsua);
                arrsinhvien.set(id,svsua);
                id = -1;
                myadapter.notifyDataSetChanged();
                //cập nhật

                break;
            case R.id.am_btn_xoatrong:
                //xóa trống
                txthoten.getText().clear();
                txtsdt.getText().clear();
                txtemail.getText().clear();
                break;
            case R.id.am_btn_timkiem:
                //Tìm kiếm
                break;
        }
    }

    // băt hiện menu bắt sự kiện

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.menu_item,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    //sử lí từng chức năng

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        //Xóa phải lấy được cái vị trí
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int index = info.position;
       switch (item.getItemId()){
           case R.id.mi_item_xemthongtin:
               //this.curron = index;

               sinhvien sv = arrsinhvien.get(vt);
               sendata(sv);
               //showDialog();
//               Intent intent = new Intent(MainActivity.this, chuyendoi.class);
//               sinhvien sv = new sinhvien();
//               intent.putExtra("du lieu", sv);
//               startActivity(intent);
               //code xem thông tin
               break;
           case R.id.mi_item_goidienthoai:
               //code gọi điện thoại
               break;
           case R.id.mi_item_xoalienlac:
               arrsinhvien.remove(index);
               myadapter.notifyDataSetChanged();
               //code xóa liên lạc
               break;
           default:
       }

        return super.onContextItemSelected(item);
    }
    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_item,null);

        TextView tv_hoten = view.findViewById(R.id.di_tv_hoten);
        TextView tv_dienthoai = view.findViewById(R.id.di_tv_sodienthoai);
        TextView tv_email = view.findViewById(R.id.di_tv_email);

        // gán lại cho listview
        if(vt >=0 ){
            tv_hoten.setText(arrsinhvien.get(vt).getHoten().toString());
            tv_dienthoai.setText(arrsinhvien.get(vt).getSodienthoai().toString());
            tv_email.setText(arrsinhvien.get(vt).getEmail().toString());
        }

        builder.setView(view);


        builder.setTitle("Xem thông tin");

        builder.show();
    }

//    private void suathongtin () {
//        Intent intent = new Intent(MainActivity.this, chuyendoi.class);
//        startActivityForResult(intent,REQUEST_CODE);
//    }

    public void sendata (sinhvien sv1){
        Intent intent = new Intent(MainActivity.this,chuyendoi.class);

        intent.putExtra("du lieu", sv1);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case 1:
//                String hoten =data.getStringExtra("hoten");
//                String sodienthoai = data.getStringExtra("sodienthoai");
//                String email = data.getStringExtra("email");
//                sinhvien sv1 = new sinhvien(hoten,sodienthoai,email);
                //arrsinhvien.add(sv1);
                //Toast.makeText(MainActivity.this,hoten,Toast.LENGTH_SHORT).show();

                sinhvien sv1 = (sinhvien)data.getSerializableExtra("data");

                arrsinhvien.set(vt,sv1);
                //arrsinhvien.notifyAll();
                myadapter.notifyDataSetChanged();

                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}