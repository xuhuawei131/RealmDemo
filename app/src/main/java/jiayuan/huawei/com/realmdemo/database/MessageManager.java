package jiayuan.huawei.com.realmdemo.database;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import jiayuan.huawei.com.realmdemo.mode.MessageBean;

/**
 * Created by Administrator on 2016/1/11.
 */
public class MessageManager {
    /**
     * 清空与某个人的聊天所有数据
     * 删除数据 就是先把数据搜索出来 然后 在清空数据 同步数据库
     * @param gid
     */
    public void deleteChatGroup(long gid){
        Realm realm =RealmHelper.getInstance().getRealm();
        RealmResults<MessageBean> results = realm.where(MessageBean.class)
                .equalTo("gid", gid)
                .notEqualTo("type",-3)
                .findAll();
        String sortCon[]={"",""};
        results.sort("time");//sort("age", RealmResults.SORT_ORDER_DECENDING);

        realm.beginTransaction();
        results.clear();
        realm.commitTransaction();
    }

    /**
     * 是否存在相同的消息
     * @param info
     * @return
     */
    public boolean isExitSameMsg(MessageBean info) {
        if (TextUtils.isEmpty(info.getMsgID())
                && info.getType() != MessageBean.SOURCE_SYSTEM) {
            return true;
        }
        if (!TextUtils.isEmpty(info.getMsgID())) {
            boolean exit = isExitChatInfo(info.getMsgID());
            if (exit) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否存在这条消息
     * @param msgid
     * @return
     */
    public boolean isExitChatInfo(String msgid){
        Realm realm =RealmHelper.getInstance().getRealm();
        RealmResults<MessageBean> results = realm.where(MessageBean.class)
                .equalTo("msgid", msgid)
                .findAll();
        return !(results == null && results.isEmpty());
    }

    /**
     * 获取针对某人的聊天消息列表
     * @param gid
     * @param page
     * @return
     */
    public List<MessageBean> getChatInfos(long gid,int page){
        Realm realm =RealmHelper.getInstance().getRealm();
        RealmResults<MessageBean> results=realm.where(MessageBean.class)
                .equalTo("giud", gid)
                .equalTo("islock",false)
                .findAll();
        String sortItem[]={"",""};
        boolean sortDes[]={true,false};
        results.sort(sortItem,sortDes);
        return results.subList(page*50,50);
    }

    /**
     * 单个插入
     * @param bean
     */
    public void insertChatInfo(MessageBean bean){
        Realm realm =RealmHelper.getInstance().getRealm();
        realm.beginTransaction();
//        MessageBean message = realm.createObject(MessageBean.class);
//        message.copyMessage(bean);
        MessageBean realmUser = realm.copyToRealm(bean);
        realm.commitTransaction();
    }

    /**
     * 批量插入
     * @param arrayList
     */
    public void insertChatInfoList(ArrayList<MessageBean> arrayList){
        Realm realm =RealmHelper.getInstance().getRealm();
        realm.beginTransaction();
        for(MessageBean bean:arrayList){
            MessageBean message = realm.createObject(MessageBean.class);
        }
        realm.commitTransaction();
    }
    public void updateChatInfo(MessageBean bean){
        Realm realm =RealmHelper.getInstance().getRealm();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(bean);
        realm.commitTransaction();
    }

}
