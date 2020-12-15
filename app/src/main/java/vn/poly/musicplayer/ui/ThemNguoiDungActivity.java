package vn.poly.musicplayer.ui;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import vn.poly.musicplayer.Model.NguoiDung;
import vn.poly.musicplayer.R;
import vn.poly.musicplayer.base.BaseActivity;
import vn.poly.musicplayer.dao.NguoiDungDao;


public class ThemNguoiDungActivity extends BaseActivity {

    private EditText edUserName;
    private EditText edtPassword;
    private EditText edtPassword2;
    private Button btnadduser,btncanceluser;
    NguoiDungDao nguoiDungDao;

    @Override
    public int getLayout()  { return R.layout.activity_them_nguoi_dung; }

    @Override
    public void initData() {

        edUserName = (EditText) findViewById(R.id.edUserName);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        edtPassword2 = (EditText) findViewById(R.id.edtPassword2);
        btnadduser = (Button) findViewById(R.id.btnadduser);

        btnadduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
            }
        });
    }

    public void addUser() {
        nguoiDungDao = new NguoiDungDao(ThemNguoiDungActivity.this);
        NguoiDung user = new NguoiDung(edUserName.getText().toString(), edtPassword.getText().toString());
        try {
            if (validateForm()>0){
                if (nguoiDungDao.insertNguoiDung(user)>0){
                    Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
            //        Intent i = new Intent(ThemNguoiDungActivity.this, NguoidungActivity.class);
             //       startActivity(i);
                }else {
                    Toast.makeText(getApplicationContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        }catch (Exception e){
            Log.e("Error",e.toString());

        }


    }

    public int validateForm() {
        int check = 1;
        if (edUserName.getText().length() == 0 || edtPassword.getText().length() == 0
                || edtPassword2.getText().length() == 0) {
            Toast.makeText(getApplicationContext(), "Bạn phải nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        } else {
            String pass = edtPassword.getText().toString();
            String rePass= edtPassword2.getText().toString();
            if (pass.length()<6){
                edtPassword.setError(getString(R.string.notify_length_pass));
                check = -1;
            }
            if (!pass.equals(rePass)){
               edtPassword2.setError("Mật khẩu phải trùng nhau");
                check =-1;
            }

        }
        return check;
    }
}

