package com.daily.okio_demo;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
import okio.Sink;
import okio.Source;

public class MainActivity extends AppCompatActivity {

    String fileName = "shenma2000.txt";
    Source mSource;
    BufferedSource mBufferedSource = null;

    boolean isCreate = false;
    Sink mSink;
    BufferedSink mBufferedSink = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button text_write = findViewById(R.id.text_write);
        Button text_read = findViewById(R.id.text_read);
        Button text_decode = findViewById(R.id.text_decode);

        text_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeFile();
            }
        });

        text_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readFile();
            }
        });

        text_decode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                codeOperate();
            }
        });
    }

    /**
     * 编码解码操作
     */
    private void codeOperate() {
        String str = "使用ByteString进行编码操作";
        //base64进行解码
        ByteString.decodeBase64(str);
        //base64编码
        byte[] bytes = str.getBytes();
        String s = ByteString.of(bytes).base64();

    }

    private void readFile() {
        try {
            InputStream open = getAssets().open(fileName);
//            File file = new File(path, fileName);
            mSource = Okio.source(open);
            mBufferedSource = Okio.buffer(mSource);
            String read = mBufferedSource.readString(Charset.forName("UTF-8"));
            Log.d("Okio", read);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (null != mBufferedSource) {
                    mBufferedSource.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void writeFile() {
        String path = Environment.getExternalStorageDirectory().getPath();
        try {
            File file = new File(path, fileName);
            if (!file.exists()) {
                isCreate = file.createNewFile();
            }else {
                isCreate = true;
            }

            if (isCreate) {
                mSink = Okio.sink(file);
                mBufferedSink = Okio.buffer(mSink);
                mBufferedSink.writeInt(90002);
//                mBufferedSink.writeString("aaa12352345234523452233asdfasdasdfas大家可能觉得我举的例子有些太简单了，好吧，我来说一个难的。让byte变量b等于-1。", Charset.forName("GBK"));
                mBufferedSink.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (null != mBufferedSink) {
                    mBufferedSink.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
