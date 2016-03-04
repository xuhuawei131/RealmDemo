package jiayuan.huawei.com.realmdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById();
    }
private void findViewById(){
    View btn_add_single=findViewById(R.id.btn_add_single);
    btn_add_single.setOnClickListener(this);

    View btn_add_patch=findViewById(R.id.btn_add_patch);
    btn_add_patch.setOnClickListener(this);

    View btn_delete_patch=findViewById(R.id.btn_delete_patch);
    btn_delete_patch.setOnClickListener(this);

    View btn_single_update=findViewById(R.id.btn_single_update);
    btn_single_update.setOnClickListener(this);
}


    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        switch (v.getId()){
            case R.id.btn_add_single:
                intent.setClass(this,AddSingleActivity.class);
                break;
            case R.id.btn_add_patch:
                intent.setClass(this,AddPatchActivity.class);
                break;
            case R.id.btn_delete_patch:
                intent.setClass(this,DeleteSingleActivity.class);
                break;
            case R.id.btn_single_update:
                intent.setClass(this,UpdateSingleActivity.class);
                break;
        }
        startActivity(intent);
    }
}
