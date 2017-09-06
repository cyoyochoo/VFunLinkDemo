package com.ffcs.yundu;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.hsae.vfun.plink.link.VFunLink;

/**
 * Created by zhuyouyong on 2017/9/6.
 */

public class App extends Application {
    private int count = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {
                if (count == 0) {// 通知当前处于前台
                    VFunLink.notifyForeground();
                }
                count ++;
            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {
                count --;
                if (count == 0) {// 通知当前处于后台
                    VFunLink.notifyBackground();
                }
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }

    /**
     * app是否处于前台
     * @return
     */
    public boolean isForeground() {
        return count > 0;
    }
}
