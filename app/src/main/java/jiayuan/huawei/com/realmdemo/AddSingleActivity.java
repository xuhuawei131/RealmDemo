package jiayuan.huawei.com.realmdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import junit.framework.Test;

import java.util.ArrayList;
import java.util.List;

import jiayuan.huawei.com.realmdemo.adapter.TestAdapter;
import jiayuan.huawei.com.realmdemo.database.TestManager;
import jiayuan.huawei.com.realmdemo.mode.DBTestBean;
import jiayuan.huawei.com.realmdemo.mode.TestBean;
import jiayuan.huawei.com.realmdemo.utils.Util;

/**
 * 单个增加
 */
public class AddSingleActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView listView;
    private TestAdapter adapter;
    private List<TestBean> arrayList;
    private int index=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_single);

        arrayList=new ArrayList<TestBean>();

        View btn_add=findViewById(R.id.btn_add);
        btn_add.setOnClickListener(this);

        listView=(ListView)findViewById(R.id.listview);
        adapter=new TestAdapter(this,arrayList);
        listView.setAdapter(adapter);

        refreshListView();
    }

    @Override
    public void onClick(View v) {
        long time=System.currentTimeMillis();
        TestBean bean=new TestBean();
        bean.setName("name" + index++);
        bean.setId((int)time);
        bean.setIsVip(time % 2 == 0);
        bean.setPrice((float) (time / 10000));
        DBTestBean dbTestBean=Util.treanlateDBTestBean(bean);
        TestManager.getInstance().addSingle(dbTestBean);
        refreshListView();
    }

    private void refreshListView(){
        arrayList.clear();
        List<DBTestBean> dbList= TestManager.getInstance().getTestList();
        arrayList.addAll(Util.treanlateTestBeanList(dbList));
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        TestManager.getInstance().closeDB();
    }
}
