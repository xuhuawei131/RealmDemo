package jiayuan.huawei.com.realmdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import jiayuan.huawei.com.realmdemo.adapter.TestOperateAdapter;
import jiayuan.huawei.com.realmdemo.database.TestManager;
import jiayuan.huawei.com.realmdemo.mode.DBTestBean;
import jiayuan.huawei.com.realmdemo.mode.TestBean;
import jiayuan.huawei.com.realmdemo.utils.Util;

public class AddPatchActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView listView;
    private TestOperateAdapter adapter;
    private List<TestBean> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patch);

        arrayList=new ArrayList<TestBean>();

        View view=findViewById(R.id.btn_add);
        view.setOnClickListener(this);

        listView=(ListView)findViewById(R.id.listview);
        adapter=new TestOperateAdapter(this,arrayList);
        listView.setAdapter(adapter);

        refreshListView();
    }

    private void refreshListView(){
        arrayList.clear();
        List<DBTestBean> dbList= TestManager.getInstance().getTestList();
        arrayList.addAll(Util.treanlateTestBeanList(dbList));
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        long time=System.currentTimeMillis();
        for(int i=0;i<10;i++){
            time++;
            TestBean bean=new TestBean();
            bean.setId((int)time);
            bean.setPrice((float) time);
            bean.setIsVip(time % 2 == 0);
            bean.setName("name" + i);
            arrayList.add(bean);
        }
        TestManager.getInstance().addPatchTestList(arrayList);
        refreshListView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        TestManager.getInstance().closeDB();
    }
}
