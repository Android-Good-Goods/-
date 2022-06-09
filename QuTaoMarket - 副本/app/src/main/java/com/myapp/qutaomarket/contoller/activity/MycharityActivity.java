package com.myapp.qutaomarket.contoller.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.myapp.qutaomarket.R;
import com.myapp.qutaomarket.contoller.fragment.MyPartCharityFragment;
import com.myapp.qutaomarket.contoller.fragment.MyPublishCharityFragment;

public class MycharityActivity extends AppCompatActivity {

    //定义变量
    private MyPublishCharityFragment MyPublishCharityFragment;
    private MyPartCharityFragment MyPartCharityFragment;

    private ImageView iv_mycharity_back;

    private RelativeLayout rl_charity_mypub, rl_charity_mypart;

    private MycharityActivity.onClickListener listener;

    private TextView tv_charity_mypub, tv_charity_mypart;

    //定义颜色值
    private int Black = 0xFF000000;
    private int Red =0xFFFF0000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycharity);

        //初始化变量
        initView();
        //初始化状态
        initState();
    }

    private void initView() {
        //初始化变量
        iv_mycharity_back = (ImageView) findViewById(R.id.iv_mycharity_back);
        rl_charity_mypub = (RelativeLayout)findViewById(R.id.rl_charity_mypub);
        rl_charity_mypart = (RelativeLayout)findViewById(R.id.rl_charity_mypart);
        tv_charity_mypub = (TextView)findViewById(R.id.tv_charity_mypub);
        tv_charity_mypart = (TextView)findViewById(R.id.tv_charity_mypart);
        //返回按钮的监听
        listener = new onClickListener();
        iv_mycharity_back.setOnClickListener(listener);
        rl_charity_mypub.setOnClickListener(listener);
        rl_charity_mypart.setOnClickListener(listener);

        //初始化fragment
        MyPublishCharityFragment = new MyPublishCharityFragment();
        MyPartCharityFragment = new MyPartCharityFragment();

        //清空选中
        clearChoice();
        //设置fragment
        switchFragment(MyPublishCharityFragment);
    }

    //切换fragment
    private void switchFragment(Fragment fragment) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        supportFragmentManager.beginTransaction().replace(R.id.fragment_charity_list, fragment).commit();
    }

    //按键监听
    private class onClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.iv_mycharity_back:
                    finish();
                    break;
                case R.id.rl_charity_mypub:
                    clearChoice();
                    tv_charity_mypub.setTextColor(Red);
                    //实现切换fragment
                    switchFragment(MyPublishCharityFragment);
                    break;
                case R.id.rl_charity_mypart:
                    clearChoice();
                    tv_charity_mypart.setTextColor(Red);
                    //实现切换fragment
                    switchFragment(MyPartCharityFragment);
                    break;
            }
        }
    }

    //初始化状态
    private void initState(){
        tv_charity_mypub.setTextColor(Red);
        tv_charity_mypart.setTextColor(Black);
    }

    //清空选中
    private void clearChoice(){
        tv_charity_mypub.setTextColor(Black);
        tv_charity_mypart.setTextColor(Black);
    }
}
