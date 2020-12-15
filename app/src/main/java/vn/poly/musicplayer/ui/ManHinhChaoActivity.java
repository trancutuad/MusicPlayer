package vn.poly.musicplayer.ui;

import android.content.Intent;
import android.os.CountDownTimer;

import vn.poly.musicplayer.R;
import vn.poly.musicplayer.base.BaseActivity;



public class ManHinhChaoActivity extends BaseActivity {

    @Override
    public void initData() {
        CountDownTimer countDownTimer = new CountDownTimer(1500,1500) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                finish();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        }.start();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_manhinhchao;
    }
}
