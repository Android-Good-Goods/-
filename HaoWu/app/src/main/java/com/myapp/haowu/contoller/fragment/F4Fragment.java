package com.myapp.haowu.contoller.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.myapp.haowu.R;
import com.myapp.haowu.model.Model;
import com.myapp.haowu.model.db.User;
import com.myapp.haowu.utils.HttpUtils;
import com.myapp.haowu.utils.UserUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息页面
 */
public class F4Fragment extends Fragment {

    //定义个人消息和系统消息的Fragment
    private PersonMessageFragment Personmessage;
    private SystemMessageFragment Systemmessage;
    private RelativeLayout rl_f4_personmessage,rl_f4_systemmessage;
    public ImageView iv_f4_persondot;
    public static ImageView iv_f4_systemdot;
    private TextView tv_f4_personmessage,tv_f4_systemmessage;
    private OnClickListener listener;

    //定义颜色值
    private int Black = 0xFF000000;
    private int Red =0xFFFF0000;

    public F4Fragment() {
        // Required empty public constructor
    }

    //创建view
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_f4, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化视图
        initView();
        //初始化状态
        initState();
        //获取未读消息
        getUnRead();
    }

    //切换fragment
    private void switchFragment(Fragment fragment) {
        FragmentManager fManager = getActivity().getSupportFragmentManager();
        fManager.beginTransaction().replace(R.id.fragment_f4_message, fragment).commit();
    }

    //handler处理
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.what){
                case 1:
                    if(!msg.obj.toString().trim().isEmpty()&&!msg.obj.toString().trim().equals("-1")) {
                        try{
                            final JSONObject json = new JSONObject(msg.obj.toString().trim());
                            int back = json.getInt("code");
                            if(back == 1){
                                iv_f4_systemdot.setVisibility(View.VISIBLE);
                            }else iv_f4_systemdot.setVisibility(View.GONE);
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }else Toast.makeText(getActivity(), "请检查网络连接", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    //初始化视图
    private void initView() {
        iv_f4_persondot = (ImageView) getActivity().findViewById(R.id.iv_f4_persondot);
        iv_f4_systemdot = (ImageView) getActivity().findViewById(R.id.iv_f4_systemdot);
        rl_f4_personmessage = (RelativeLayout) getActivity().findViewById(R.id.rl_f4_personmessage);
        rl_f4_systemmessage = (RelativeLayout) getActivity().findViewById(R.id.rl_f4_systemmessage);
        tv_f4_personmessage = (TextView)getActivity().findViewById(R.id.tv_f4_personmessage);
        tv_f4_systemmessage = (TextView)getActivity().findViewById(R.id.tv_f4_systemmessage);

        listener = new OnClickListener();
        rl_f4_personmessage.setOnClickListener(listener);
        rl_f4_systemmessage.setOnClickListener(listener);

        Personmessage = new PersonMessageFragment();
        Systemmessage = new SystemMessageFragment();

        //初始化视第一个界面应该是PersonMessageFragment
        clearChioce();
        tv_f4_personmessage.setTextColor(Red);
        switchFragment(Personmessage);
    }

    private class OnClickListener implements View.OnClickListener {
        Fragment fragment = null;
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.rl_f4_personmessage:
                    clearChioce();
                    tv_f4_personmessage.setTextColor(Red);
                    //切换fragment
                    switchFragment(Personmessage);
                    break;
                case R.id.rl_f4_systemmessage:
                    clearChioce();
                    tv_f4_systemmessage.setTextColor(Red);
                    //切换fragment
                    switchFragment(Systemmessage);
                    break;
            }

        }
    }

    //定义一个设置初始状态的方法
    private void initState()
    {
        tv_f4_personmessage.setTextColor(Red);
        tv_f4_systemmessage.setTextColor(Black);
    }

    //建立一个清空选中状态的方法
    public void clearChioce()
    {
        tv_f4_personmessage.setTextColor(Black);
        tv_f4_systemmessage.setTextColor(Black);
    }

    private void getUnRead(){
        User user = UserUtils.getCurrentUser();
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("requesttop","getunread");
                params.put("account",user.getAccount());
                String strUrlpath = getResources().getString(R.string.burl) + "Message_Servlet";
                String Result = HttpUtils.submitPostData(strUrlpath, params, "utf-8");
                System.out.println("结果为：" + Result);
                Message msg = new Message();
                msg.what = 1;
                msg.obj = Result;
                handler.sendMessage(msg);
            }
        });
    }
}
