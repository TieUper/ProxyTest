package com.daily.proxytest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.daily.proxytest.annotation.InjectView;
import com.daily.proxytest.annotation.onClick;
import com.daily.proxytest.utils.ViewUtils;

public class AnnotationActivity extends AppCompatActivity {

    @InjectView(R.id.bind_view_btn)
    Button mBindView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annotaion);

        ViewUtils.injectView(this);
        ViewUtils.injectEvent(this);
    }

    @onClick(R.id.bind_view_btn)
    public void invokeClick(View view) {
        switch (view.getId()) {
            case R.id.bind_view_btn:
                Toast.makeText(AnnotationActivity.this,"点击了绑定视图的按钮",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
