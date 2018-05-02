package com.daily.proxytest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.daily.proxytest.annotation.InjectView;
import com.daily.proxytest.utils.ViewUtils;

public class AnnotaionActivity extends AppCompatActivity {

    @InjectView(R.id.bind_view_btn)
    Button mBindView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annotaion);

        ViewUtils.inJectView(this);
    }
}
