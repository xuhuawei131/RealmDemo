package jiayuan.huawei.com.realmdemo.database;

import java.util.Iterator;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import jiayuan.huawei.com.realmdemo.mode.DBTestBean;
import jiayuan.huawei.com.realmdemo.mode.TestBean;

/**
 * Created by Administrator on 2016/1/12.
 */
public class TestManager {
    private static TestManager instance;
    private TestManager(){

    }
    public  static TestManager getInstance(){
            if(instance==null){
                instance=new TestManager();
            }
        return instance;
    }
    public void addSingle(DBTestBean bean){
        Realm realm =RealmHelper.getInstance().getRealm();
        realm.beginTransaction();
//        MessageBean message = realm.createObject(MessageBean.class);
//        message.copyMessage(bean);
        DBTestBean realmUser = realm.copyToRealm(bean);
        realm.commitTransaction();
    }

    public List<DBTestBean> getTestList(long gid,int page){
        Realm realm =RealmHelper.getInstance().getRealm();
        RealmResults<DBTestBean> results=realm.where(DBTestBean.class)
                .equalTo("giud", gid)
                .equalTo("islock",false)
                .findAll();
        String sortItem[]={"",""};
        boolean sortDes[]={true,false};
        results.sort(sortItem, sortDes);
        return results.subList(page*50,50);
    }

    public void deleteTest(int id){
        Realm realm =RealmHelper.getInstance().getRealm();
        RealmResults<DBTestBean> results=realm.where(DBTestBean.class)
                .equalTo("id", id)
                .findAll();
        realm.beginTransaction();
        results.remove(0);
        realm.commitTransaction();
    }
    public void deleteTestAll(){
        Realm realm =RealmHelper.getInstance().getRealm();
        RealmResults<DBTestBean> results=realm.where(DBTestBean.class)
                .findAll();
        realm.beginTransaction();
        results.clear();
        realm.commitTransaction();
    }

    public List<DBTestBean> getTestList(){
        Realm realm =RealmHelper.getInstance().getRealm();
        RealmResults<DBTestBean> results=realm.where(DBTestBean.class)
                .findAll();
        results.sort("name", true);
        return results;
    }

    public void updateTestSingle(TestBean bean){
        Realm realm =RealmHelper.getInstance().getRealm();
        realm.beginTransaction();
        DBTestBean dbTestBean=new DBTestBean();
        dbTestBean.setId(bean.getId());
        dbTestBean.setName(bean.getName());
        dbTestBean.setPrice(bean.getPrice());
        dbTestBean.setIsVip(!bean.isVip());
        realm.copyToRealmOrUpdate(dbTestBean);
        realm.commitTransaction();
    }
    public void updateTestList(){
        Realm realm =RealmHelper.getInstance().getRealm();
        RealmResults<DBTestBean> results=realm.where(DBTestBean.class)
                .findAll();
        boolean isVip=System.currentTimeMillis() % 2 == 0;
        realm.beginTransaction();
        for(int i=0;i<results.size();i++){
            DBTestBean dbTestBean=results.get(i);
            dbTestBean.setIsVip(isVip);
        }
        realm.commitTransaction();
    }
}
