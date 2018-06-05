package com.daily.proxytest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.annotation.DIActivity;
import com.annotation.DIView;
import com.annotation.GetMsg;
import com.daily.proxytest.bean.Animal;
import com.daily.proxytest.inter.FLy;
import com.daily.proxytest.reflect.AnimalProxyHandler;

import java.lang.reflect.Proxy;

@DIActivity
public class MainActivity extends AppCompatActivity {

    @DIView(R.id.button_annotation)
    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Animal animal = new Animal();
        ClassLoader classLoader = animal.getClass().getClassLoader();

        Class<?>[] interfaces = animal.getClass().getInterfaces();

        AnimalProxyHandler proxyHandler = new AnimalProxyHandler(animal);

//        MyClass.generateClassFile(animal.getClass(),"AnimalProxy");

        Object newProxyInstance = Proxy.newProxyInstance(classLoader, interfaces, proxyHandler);

        FLy fly = (FLy) newProxyInstance;
        fly.fly();

//        Run run = (Run) newProxyInstance;
//        run.run();

        DIMainActivity.bindView(this);
        mButton.setText("设置了");
    }

    @GetMsg(id=123)
    public void annotation(View view) {
        startActivity(new Intent(this, AnnotationActivity.class));
    }


}
