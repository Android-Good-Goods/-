package com.myapp.qutaomarket.contoller.activity;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import com.myapp.qutaomarket.R;
import com.myapp.qutaomarket.contoller.fragment.ChatFragment;

//聊天的Activity
public class ChatActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        initData();
    }

    private void initData() {
        //创建一个会话的fragment
        ChatFragment chatFragment = new ChatFragment();
        chatFragment.setArguments(getIntent().getExtras());

        //替换fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_chat, chatFragment).commit();
    }
}
