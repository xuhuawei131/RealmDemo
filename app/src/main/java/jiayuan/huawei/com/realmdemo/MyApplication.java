package jiayuan.huawei.com.realmdemo;

import android.app.Application;

/**
 * Created by Administrator on 2016/1/11.
 */
public class MyApplication extends Application {
    public static MyApplication context;
    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
    }
}
