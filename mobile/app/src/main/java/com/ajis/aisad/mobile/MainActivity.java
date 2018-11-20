package com.ajis.aisad.mobile;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
    /*
    created by llf
    2018/11/19
    */

public class MainActivity extends AppCompatActivity {
    Button start=null; //按钮
    Button finish=null; //按钮
    Button show=null; //按钮
    Button  q1=null; //按钮
    Button  q2=null; //按钮
    Button  q3=null; //按钮
    Button  q4=null; //按钮
    Button  q5=null; //按钮
    Button  q6=null; //按钮
    Button  q7=null; //按钮
    Button  q8=null; //按钮
    TextView textView=null;
    TextView textView1=null;
    EditText editText=null;
    int count=0;


    boolean mStopFlag=false;//线程变量
    Thread checkthread=null;//线程
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCollector.addActivity(this);
        initialization();//实例化布局

        checkthread=new checkThread();//向上转型，new checkThread()为对象，引用给 checkthread
        //动态的请求权限
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 0x11);
        start.setOnClickListener(new View.OnClickListener() {//按钮点击监听
            @Override
            public void onClick(View v) {
                checkthread.start();//开始线程
            }
        });
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            ActivityCollector.finishAll();
            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                switch (count){
                    case 1:
                        showShort(MainActivity.this,"不许偷看~");
                        break;
                    case 2:
                        showShort(MainActivity.this,"还看？");
                        break;
                    case 3:
                        showShort(MainActivity.this,"再看，再看就把你吃掉！");
                        break;
                    case 4:
                        showShort(MainActivity.this,"哈哈哈嗝");
                        break;
                    case 5:
                        showShort(MainActivity.this,"好吧，你赢了。");
                        editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());//密码可见
                        break;
                    default:
                        break;

                }
            }
        });
        listener();
    }

    private void listener() {
        q1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShort(MainActivity.this,"蒹葭苍苍，白露为霜。");
            }
        });
        q2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShort(MainActivity.this,"所谓伊人，在水一方。");
            }
        });
        q3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShort(MainActivity.this,"亲亲子衿，悠悠我心。");
            }
        });
        q4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShort(MainActivity.this,"纵我不往，子宁不嗣音？");
            }
        });
        q5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShort(MainActivity.this,"死生契阔，与子成说。");
            }
        });
        q6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShort(MainActivity.this,"执子之手，与子偕老。");
            }
        });
        q7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShort(MainActivity.this,"桃之夭夭，灼灼其华。");
            }
        });
        q8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShort(MainActivity.this,"之子于归，宜室宜家。");
            }
        });
    }

    private void initialization() {
        try {
            start = findViewById(R.id.call);
            finish = findViewById(R.id.done);
            q1=findViewById(R.id.q1);
            q2=findViewById(R.id.q2);
            q3=findViewById(R.id.q3);
            q4=findViewById(R.id.q4);
            q5=findViewById(R.id.q5);
            q6=findViewById(R.id.q6);
            q7=findViewById(R.id.q7);
            q8=findViewById(R.id.q8);
            textView=findViewById(R.id.end);
            textView1=findViewById(R.id.end1);
            editText=findViewById(R.id.weixin);
            show=findViewById(R.id.show);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void startCall() {
        Intent intent = new Intent(Intent.ACTION_CALL);//打电话
        Uri data = Uri.parse("tel:" + "10086");
        intent.setData(data);
        startActivity(intent);
    }

    @Override
    //动态申请系统权限：打电话
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0x11) {
            // If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.e("CMCC", "权限被允许");
            } else {
                Log.e("CMCC", "权限被拒绝");
            }
        }
    }

    @Override
    protected void onDestroy() {
        try{
            super.onDestroy();
            ((checkThread)checkthread).stopFlag();

            this.finish();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public class checkThread extends Thread{
        public void stopFlag(){
            mStopFlag=true;
        }
        public void run() {

                try {
                    startCall();
                    textView.setText("emmm，当你看到这个消息时，恭喜你有了BUG。再给你的狗子打电话就是闪退~。咳咳，这个问题还没想到解决方案233。\n" +
                            "如果你有好的构思，欢迎骚扰~\n" +
                            "WeChat:");
                } catch (Exception e) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    e.printStackTrace();
                }
            }
        }
    public static void showShort(Context context, CharSequence message) {
        Toast mToast = Toast.makeText(context, null, Toast.LENGTH_SHORT);
        mToast.setText(message);
        mToast.show();
    }

    }


