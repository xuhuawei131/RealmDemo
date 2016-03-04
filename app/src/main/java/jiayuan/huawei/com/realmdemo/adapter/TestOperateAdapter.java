package jiayuan.huawei.com.realmdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import jiayuan.huawei.com.realmdemo.DeleteSingleActivity;
import jiayuan.huawei.com.realmdemo.R;
import jiayuan.huawei.com.realmdemo.mode.TestBean;

/**
 * Created by Administrator on 2016/1/13.
 */
public class TestOperateAdapter extends BaseAdapter {
    private List<TestBean> arrayList;
    private LayoutInflater inflater;
    private View.OnClickListener  listener;
    public TestOperateAdapter(Context context, List<TestBean> arrayList){
        inflater=  LayoutInflater.from(context);
        this.listener=(View.OnClickListener)context;
        this.arrayList=arrayList;
    }
    @Override
    public int getCount() {
        return arrayList==null?0:arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            convertView=inflater.inflate(R.layout.adapter_test_operate,null);
        }
        TextView text_id=(TextView)convertView.findViewById(R.id.text_id);
        TextView text_name=(TextView)convertView.findViewById(R.id.text_name);
        TextView text_price=(TextView)convertView.findViewById(R.id.text_price);
        TextView text_vip=(TextView)convertView.findViewById(R.id.text_vip);
        TextView text_opereate=(TextView)convertView.findViewById(R.id.text_operate);

        TestBean bean=arrayList.get(position);
        text_id.setText(String.valueOf(bean.getId()));
        text_name.setText(bean.getName());
        text_price.setText(String.valueOf(bean.getPrice()));
        text_vip.setText(String.valueOf(bean.isVip()));
        text_opereate.setOnClickListener(listener);
        text_opereate.setClickable(true);
        text_opereate.setTag(position);

        return convertView;
    }
}
