package vn.poly.musicplayer.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.List;

import vn.poly.musicplayer.Adapter.NguoiDungAdapter;
import vn.poly.musicplayer.Model.NguoiDung;
import vn.poly.musicplayer.R;
import vn.poly.musicplayer.base.BaseActivity;
import vn.poly.musicplayer.dao.NguoiDungDao;


public class NguoidungActivity extends BaseActivity {
    public static List<NguoiDung> dsNguoiDung = new ArrayList<>();
    ListView lvnguoidung;
    NguoiDungAdapter adapter=null;
    NguoiDungDao nguoiDungDao;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onResume() {
        super.onResume();
        dsNguoiDung.clear();
        dsNguoiDung = nguoiDungDao.getAllNguoiDung();
        adapter.changeDataset(nguoiDungDao.getAllNguoiDung());
    }


    @Override
    public void initData() {
        setTitle("Nguời Dùng");

        lvnguoidung = findViewById(R.id.customlvnguoidung);
        nguoiDungDao = new NguoiDungDao(NguoidungActivity.this);
        dsNguoiDung = nguoiDungDao.getAllNguoiDung();
        floatingActionButton = findViewById(R.id.fab);

        adapter = new NguoiDungAdapter(dsNguoiDung, this);

        lvnguoidung.setAdapter(adapter);
        lvnguoidung.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(NguoidungActivity.this, ChiTietNguoiDungActivity.class);
                Bundle b = new Bundle() ;
                b.putString("USERNAME",dsNguoiDung.get(position).getUserName());
                b.putString("PASSWORD",dsNguoiDung.get(position).getPassword());
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(NguoidungActivity.this, ThemNguoiDungActivity.class);
                startActivity(a);
            }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.activity_nguoidung;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.optionnguoidung, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.adduser:
                Intent a = new Intent(NguoidungActivity.this, ThemNguoiDungActivity.class);
                startActivity(a);
                break;
            case R.id.dangxuat:
                SharedPreferences preferences = getSharedPreferences("USER_FILE",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();
                Intent c = new Intent(NguoidungActivity.this, LoginActivity.class);
                startActivity(c);
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
