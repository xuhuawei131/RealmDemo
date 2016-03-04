package jiayuan.huawei.com.realmdemo.mode;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by Administrator on 2016/1/11.
 */
public class MessageBean extends RealmObject {//javabean 继承 RealmObject必须有一项 不能编译不能通过



    @Ignore
    public static final  int  SOURCE_SYSTEM=3;
    @PrimaryKey
    private int id;
    @Required
    private String msgID;//只有对象类型的 需要设置 Required  int long float 不能设置
    @Required
    private String name;

    private long time;

    private int type;


    private boolean isvip;
    @Ignore
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMsgID() {
        return msgID;
    }

    public void setMsgID(String msgID) {
        this.msgID = msgID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isvip() {
        return isvip;
    }

    public void setIsvip(boolean isvip) {
        this.isvip = isvip;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
