package vn.poly.musicplayer.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import vn.poly.musicplayer.Activity.MainActivity;
import vn.poly.musicplayer.DB.DatabaseHelper;
import vn.poly.musicplayer.Model.NguoiDung;
import vn.poly.musicplayer.R;
import vn.poly.musicplayer.base.BaseActivity;
import vn.poly.musicplayer.dao.NguoiDungDao;


public class LoginActivity extends BaseActivity {

    private EditText edtUsername;
    private EditText edtPassword;
    private Button btnLogin;

    NguoiDungDao nguoiDungDao;
    private DatabaseHelper dbHelper;
    private Button btndangki;
    private TextView btnquenmatkhau;
    private int MY_PERMISSION_REQUEST = 1;


    @Override
    public void initData() {
        setTitle("ĐĂNG NHẬP");

        edtUsername = (EditText) findViewById(R.id.edtUsername);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btndangki = (Button) findViewById(R.id.btndangki);
        btnquenmatkhau = (TextView) findViewById(R.id.btnquenmatkhau);
        nguoiDungDao = new NguoiDungDao(getApplicationContext());

        NguoiDung user2;
        user2 = nguoiDungDao.getUser("admin");
        if (user2 == null) {
            NguoiDung user3 = new NguoiDung("admin", "123456");
            nguoiDungDao.insertNguoiDung(user3);
        }
        checkLogin();

        edtUsername.setText("admin");
        edtPassword.setText("123456");

        btndangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ThemNguoiDungActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

//    private void grantedPermission() {
//        if (ContextCompat.checkSelfPermission(LoginActivity.this,
//                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//            Toast.makeText(LoginActivity.this,"read external storage",Toast.LENGTH_SHORT).show();
//            } else {
//            onRequest();
//            }
//        }
//
//
//        private void onRequest(){
//        if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE)){
//            new AlertDialog.Builder(this)
//                    .setTitle("Cấp quyền truy cập")
//                    .setMessage("clickkkkkkkkkkkkkkkkkkkk")
//                    .setPositiveButton("oke", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            ActivityCompat.requestPermissions(LoginActivity.this,new String[]{Manifest.permission.READ_CONTACTS},MY_PERMISSION_REQUEST );
//                        }
//                    }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    dialogInterface.dismiss();
//                }
//            }) .create().show();
//        }else {
//            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS},MY_PERMISSION_REQUEST );
//        }
//        }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            Toast.makeText(this, "Permission fall!", Toast.LENGTH_SHORT).show();
//        }else {
//            Toast.makeText(this, "Permission error!", Toast.LENGTH_SHORT).show();
//        }
//    }

    public void checkLogin() {
            btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName = edtUsername.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                if (password.length() < 6 || userName.isEmpty() || password.isEmpty()) {

                    if (userName.isEmpty())
                        edtUsername.setError(getString(R.string.notify_empty_user));

                    if (password.isEmpty())
                        edtPassword.setError(getString(R.string.notify_empty_pass));


                } else {
                    NguoiDung user = nguoiDungDao.getUser(userName);
                    if (user != null && user.getUserName() != null) {
                        if (password.matches(user.getPassword())) {
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();
                            Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        } else {

                            Toast.makeText(LoginActivity.this, "Tài khoản hoặc mật khẩu chưa chính xác", Toast.LENGTH_SHORT).show();

                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "Bạn chưa có tài khoản", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }


}
