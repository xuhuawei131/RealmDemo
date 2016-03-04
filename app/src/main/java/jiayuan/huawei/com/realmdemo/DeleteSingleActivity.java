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

public class DeleteSingleActivity extends AppCompatActivity implements View.OnClickListener{
    private ListView listView;
    private TestOperateAdapter adapter;
    private List<TestBean> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_delete);

        arrayList=new ArrayList<TestBean>();

        View btn_delete_all=findViewById(R.id.btn_delete_all);
        btn_delete_all.setOnClickListener(this);

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
        if(v.getId()==R.id.btn_delete_all){
            TestManager.getInstance().deleteTestAll();
        }else{
            int position=(Integer)v.getTag();
            TestManager.getInstance().deleteTest(arrayList.get(position).getId());
        }
        refreshListView();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        TestManager.getInstance().closeDB();
    }
}
