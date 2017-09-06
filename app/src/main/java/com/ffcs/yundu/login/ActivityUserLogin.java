package com.ffcs.yundu.login;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ffcs.yundu.App;
import com.ffcs.yundu.R;
import com.hsae.vfun.plink.link.VFunLink;
import com.hsae.vfun.plink.link.listeners.LinkListener;

public class ActivityUserLogin extends Activity implements LinkListener {
    private TextView LinkTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        LinkTxt = (TextView) findViewById(R.id.link_txt);
        LinkTxt.post(new Runnable() {
            @Override
            public void run() {
                // 连接车机，调用一次即可，后台会根据连接状态自动进行连接
                VFunLink.link(ActivityUserLogin.this, ActivityUserLogin.this);
            }
        });
    }

    @Override
    public void onBackPressed() {
        // 要退出app时需要调用该方法通知车机
        VFunLink.notifyExit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 销毁对象释放资源
        VFunLink.destroy();
    }

    public void onClick(View view) {
        new AlertDialog.Builder(this).setMessage("点击按钮测试看看").show();
    }

    // LinkListener接口方法，用于接收连接状态通知
    // 注意该方法运行在子线程中
    @Override
    public void notifyLinkState(final boolean isConnected) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (isConnected) {
                    // 如果连接成功，通知一下车机端当前app前后台状态
                    if (((App) getApplication()).isForeground()) {
                        VFunLink.notifyForeground();
                    } else {
                        VFunLink.notifyBackground();
                    }

                    //更新下UI状态
                    LinkTxt.setText("连接成功");
                    LinkTxt.setTextColor(Color.GREEN);

                } else {
                    //更新下UI状态
                    LinkTxt.setText("未连接");
                    LinkTxt.setTextColor(Color.RED);
                }
            }
        });
    }

    // LinkListener接口方法，用于接收连接状态通知
    // 注意该方法运行在子线程中
    @Override
    public void notifyExit() {
        // 接收到退出通知，可以退出app了
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ActivityUserLogin.this.finish();
            }
        });
    }
}
