package jiayuan.huawei.com.realmdemo.utils;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmResults;
import jiayuan.huawei.com.realmdemo.mode.DBTestBean;
import jiayuan.huawei.com.realmdemo.mode.TestBean;

/**
 * Created by Administrator on 2016/1/13.
 */
public class Util {

    /**
     * 数据库的模型与应用模型转换
     * @param bean
     * @return
     */
    public static TestBean treanlateTestBean(DBTestBean bean){
        TestBean cbean=new TestBean();
        cbean.setId(bean.getId());
        cbean.setName(bean.getName());
        cbean.setPrice(bean.getPrice());
        cbean.setIsVip(bean.isVip());
        return cbean;
    }

    public static DBTestBean treanlateDBTestBean(TestBean bean){
        DBTestBean cbean=new DBTestBean();
        cbean.setId(bean.getId());
        cbean.setName(bean.getName());
        cbean.setPrice(bean.getPrice());
        cbean.setIsVip(bean.isVip());
        return cbean;
    }
    /**
     * 批量转换
     * @param dbList
     * @return
     */
    public static List<TestBean> treanlateTestBeanList(List<DBTestBean> dbList){
        List<TestBean> arrayList=new ArrayList<TestBean>();
        for(DBTestBean bean:dbList){
            arrayList.add(treanlateTestBean(bean));
        }
        return arrayList;
    }
}
