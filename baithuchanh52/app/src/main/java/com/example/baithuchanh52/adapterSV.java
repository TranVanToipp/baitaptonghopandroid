package com.example.baithuchanh52;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.baithuchanh52.sv.sinhvien;

import java.util.List;

public class adapterSV extends BaseAdapter {
    Activity activity;
    int layout;
    List<sinhvien> datalist;

    public adapterSV(Activity activity,int layout, List<sinhvien> datalist) {
        this.activity = activity;
        this.datalist = datalist;
        this.layout = layout;
    }


    @Override
    public int getCount() {
        return datalist.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = activity.getLayoutInflater();
        // gọi view để chứa thông tin mỗi dòng
        view = inflater.inflate(layout,null);

        // Ánh xạ các view
        TextView hoten = (TextView) view.findViewById(R.id.il_tv_hoten);
        TextView sodienthoai = (TextView) view.findViewById(R.id.il_tv_sdt);
        TextView  email = (TextView) view.findViewById(R.id.il_tv_email);

        //gán giá trị
        sinhvien sv = datalist.get(i);

        hoten.setText(sv.getHoten());
        sodienthoai.setText(sv.getSodienthoai());
        email.setText(sv.getEmail());

        return view;
    }
}
