package jiayuan.huawei.com.realmdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import jiayuan.huawei.com.realmdemo.adapter.TestAdapter;
import jiayuan.huawei.com.realmdemo.adapter.TestOperateAdapter;
import jiayuan.huawei.com.realmdemo.database.TestManager;
import jiayuan.huawei.com.realmdemo.mode.DBTestBean;
import jiayuan.huawei.com.realmdemo.mode.TestBean;
import jiayuan.huawei.com.realmdemo.utils.Util;

/**
 * 更新数据库  批量更新以及单个更新
 */
public class UpdateSingleActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView listView;
    private TestOperateAdapter adapter;
    private List<TestBean> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_single);

        arrayList=new ArrayList<TestBean>();

        View btn_update=findViewById(R.id.btn_update);
        btn_update.setOnClickListener(this);

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
        if(v.getId()==R.id.btn_update){
            TestManager.getInstance().updateTestList();
        }else{
            int position=(Integer)v.getTag();
            TestBean bean=arrayList.get(position);
            TestManager.getInstance().updateTestSingle(bean);
        }
        refreshListView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        TestManager.getInstance().closeDB();
    }
}
