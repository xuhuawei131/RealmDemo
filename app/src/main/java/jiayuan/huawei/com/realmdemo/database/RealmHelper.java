package jiayuan.huawei.com.realmdemo.database;

import android.os.Environment;

import java.io.File;
import java.io.UnsupportedEncodingException;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import jiayuan.huawei.com.realmdemo.MyApplication;

/**
 * Created by Administrator on 2016/1/12.
 */
public class RealmHelper  {
    private static RealmHelper instance;
    private static final String encodeStr="xuhuawei";
    private static final String databseName="db.reanlm";
    private byte encode[];

    private RealmHelper(){
        try {
            encode= encodeStr.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        File dbDir=new File(Environment.getExternalStorageDirectory()+"/realmDemo");

        if(!dbDir.exists()){
            dbDir.mkdirs();
        }
        RealmConfiguration config=new RealmConfiguration.Builder(dbDir)//数据库
//                .encryptionKey(encode)//加密
                .name(databseName)//数据库名称
                .schemaVersion(1)
                .build();
        Realm.setDefaultConfiguration(config);
//    Realm.removeDefaultConfiguration();
    }

    public static  RealmHelper  getInstance(){
        if(instance==null){
            instance=new RealmHelper();
        }
        return instance;
    }
    public Realm getRealm(){
        Realm realm =Realm.getInstance(MyApplication.context);
        return realm;
    }
}
