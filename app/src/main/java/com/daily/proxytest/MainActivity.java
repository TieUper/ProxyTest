package com.daily.proxytest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.daily.proxytest.bean.Animal;
import com.daily.proxytest.inter.FLy;
import com.daily.proxytest.inter.Run;
import com.daily.proxytest.reflect.ProxyHandler;

import java.lang.reflect.Proxy;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Animal animal = new Animal();
        ClassLoader classLoader = animal.getClass().getClassLoader();

        Class<?>[] interfaces = animal.getClass().getInterfaces();

        ProxyHandler proxyHandler = new ProxyHandler(animal);

//        MyClass.generateClassFile(animal.getClass(),"AnimalProxy");

        Object newProxyInstance = Proxy.newProxyInstance(classLoader, interfaces, proxyHandler);

        FLy fly = (FLy) newProxyInstance;
        fly.fly();

        Run run = (Run) newProxyInstance;
        run.run();
    }

    public void annotation(View view) {
        startActivity(new Intent(this, AnnotaionActivity.class));
    }

}
