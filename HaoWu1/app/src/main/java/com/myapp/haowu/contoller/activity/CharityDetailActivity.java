package com.myapp.haowu.contoller.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hyphenate.easeui.EaseConstant;
import com.myapp.haowu.R;
import com.myapp.haowu.model.Model;
import com.myapp.haowu.model.db.User;
import com.myapp.haowu.utils.HttpUtils;
import com.myapp.haowu.utils.RoundTransform;
import com.myapp.haowu.utils.UserUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CharityDetailActivity extends AppCompatActivity {

    //定义变量
    private ImageView iv_cdetail_back,iv_cdetail_cimage;

    private TextView tv_cdetail_title,tv_cdetail_cname,tv_cdetail_cstate,tv_cdetail_cdetail,tv_cdetail_cneed,tv_cdetail_cnickname,
            tv_cdetail_caddress,tv_cdetail_ctime,tv_cdetail_cdeadline,tv_cdetail_cscannum,tv_cdetail_cjoinnum,tv_cdetail_chat,
            tv_cdetail_join;

    private CharityDetailActivity.OnClickListener listener;

    //进度条
    private ProgressDialog pd;

    //定义从其他Activity中传过来的数据
    Map<String, Object> uagdata = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charity_detail);

        //接收数据
        uagdata = (Map<String, Object>) getIntent().getSerializableExtra("data");

        //初始化视图
        initView();
        //初始化数据
        initData();
    }

    //初始化视图
    private void initView() {
        //初始化进度条
        pd = new ProgressDialog(CharityDetailActivity.this);
        pd.setIndeterminate(true);
        pd.setCancelable(false);

        //ImageView
        iv_cdetail_back = (ImageView)findViewById(R.id.iv_cdetail_back);
        iv_cdetail_cimage = (ImageView)findViewById(R.id.iv_cdetail_cimage);
        //TextView
        tv_cdetail_title = (TextView)findViewById(R.id.tv_cdetail_title);
        tv_cdetail_cname = (TextView)findViewById(R.id.tv_cdetail_cname);
        tv_cdetail_cstate = (TextView)findViewById(R.id.tv_cdetail_cstate);
        tv_cdetail_cdetail = (TextView)findViewById(R.id.tv_cdetail_cdetail);
        tv_cdetail_cneed = (TextView)findViewById(R.id.tv_cdetail_cneed);
        tv_cdetail_cnickname = (TextView)findViewById(R.id.tv_cdetail_cnickname);
        tv_cdetail_caddress = (TextView)findViewById(R.id.tv_cdetail_caddress);
        tv_cdetail_ctime = (TextView)findViewById(R.id.tv_cdetail_ctime);
        tv_cdetail_cdeadline = (TextView)findViewById(R.id.tv_cdetail_cdeadline);
        tv_cdetail_cscannum = (TextView)findViewById(R.id.tv_cdetail_cscannum);
        tv_cdetail_cjoinnum = (TextView)findViewById(R.id.tv_cdetail_cjoinnum);
        tv_cdetail_chat = (TextView)findViewById(R.id.tv_cdetail_chat);
        tv_cdetail_join = (TextView)findViewById(R.id.tv_cdetail_join);

        //初始化listener
        listener = new OnClickListener();
        tv_cdetail_chat.setOnClickListener(listener);
        tv_cdetail_join.setOnClickListener(listener);
        iv_cdetail_back.setOnClickListener(listener);
    }

    //初始化数据
    private void initData(){
        //加载公益图片
        RequestOptions options = new RequestOptions();
        options.centerCrop()
                .placeholder(R.drawable.ic_moren_goods)
                .error(R.drawable.ic_moren_goods)
                .fallback(R.drawable.ic_moren_goods)
                .transform(new RoundTransform(this));
        Glide.with(this)
                .applyDefaultRequestOptions(options)
                .load(uagdata.get("cimage").toString())
                .into(iv_cdetail_cimage);
        //设置公益名称
        tv_cdetail_cname.setText(uagdata.get("cname").toString());
        //设置公益状态
        if(Integer.valueOf(uagdata.get("cstate").toString()) == 1){
            tv_cdetail_cstate.setText("进行中");
        }else tv_cdetail_cstate.setText("已失效");
        //设置公益详情
        tv_cdetail_cdetail.setText(uagdata.get("cdetail").toString());
        //设置公益需求
        tv_cdetail_cneed.setText(uagdata.get("cneed").toString());
        //设置发起人昵称
        tv_cdetail_cnickname.setText(uagdata.get("nickname").toString());
        //设置发布地点
        tv_cdetail_caddress.setText(uagdata.get("caddress").toString());
        //设置开始时间
        tv_cdetail_ctime.setText(uagdata.get("ctime").toString());
        //设置截止时间
        tv_cdetail_cdeadline.setText(uagdata.get("cdeadline").toString());
        //设置浏览人数
        tv_cdetail_cscannum.setText(uagdata.get("cscannum").toString());
        //设置参与人数
        tv_cdetail_cjoinnum.setText(uagdata.get("cjoinnum").toString());

        //设置浏览人数
        setScannum();
    }

    //handler处理
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    int joinBack;
                    if(!msg.obj.toString().trim().isEmpty()&&!msg.obj.toString().trim().equals("-1"))
                    {
                        try{
                            JSONObject joinResult = new JSONObject(msg.obj.toString().trim());
                            joinBack = joinResult.getInt("code");
                            if(joinBack == 1){
                                tv_cdetail_cjoinnum.setText(String.valueOf(joinResult.getInt("joinnum")));
                                pd.cancel();
                                Toast.makeText(CharityDetailActivity.this,"加入成功",Toast.LENGTH_SHORT).show();
                            } else if(joinBack == 2){
                                pd.cancel();
                                Toast.makeText(CharityDetailActivity.this,"用户已加入",Toast.LENGTH_SHORT).show();
                            }else {
                                pd.cancel();
                                Toast.makeText(CharityDetailActivity.this,"加入失败",Toast.LENGTH_SHORT).show();
                            }
                        }catch (JSONException e){
                            pd.cancel();
                            e.printStackTrace();
                        }
                    }else {
                        pd.cancel();
                        Toast.makeText(CharityDetailActivity.this,"请检查网络",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 2:
                    int scannumBack;
                    if(!msg.obj.toString().trim().isEmpty()&&!msg.obj.toString().trim().equals("-1"))
                    {
                        try{
                            JSONObject scannumResult = new JSONObject(msg.obj.toString().trim());
                            scannumBack = scannumResult.getInt("code");
                            if(scannumBack == 1){
                                //设置浏览人数
                                tv_cdetail_cscannum.setText(scannumResult.getString("data"));
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                    break;
                case 3:
                    int sendBack;
                    if(!msg.obj.toString().trim().isEmpty()&&!msg.obj.toString().trim().equals("-1"))
                    {
                        try{
                            JSONObject sendResult = new JSONObject(msg.obj.toString().trim());
                            sendBack = sendResult.getInt("code");
                            if(sendBack == 1){
                                pd.cancel();
                                Toast.makeText(CharityDetailActivity.this,"发送成功",Toast.LENGTH_SHORT).show();
                            } else {
                                pd.cancel();
                                Toast.makeText(CharityDetailActivity.this,"发送失败",Toast.LENGTH_SHORT).show();
                            }
                        }catch (JSONException e){
                            pd.cancel();
                            e.printStackTrace();
                        }
                    }else {
                        pd.cancel();
                        Toast.makeText(CharityDetailActivity.this,"请检查网络",Toast.LENGTH_SHORT).show();
                    }
                    break;
                    default:
                        break;
            }
        }
    };

    //按键监听处理
    private class OnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.iv_cdetail_back:
                    finish();
                    break;
                case R.id.tv_cdetail_chat:
                    userChat(uagdata.get("account").toString());
                    break;
                case R.id.tv_cdetail_join:
                    userJoin();
                    break;
            }
        }
    }

    //用户聊天
    private void userChat(String hxid){
        if(UserUtils.getCurrentUser().getHxId().trim().equals(hxid)){
            Toast.makeText(CharityDetailActivity.this, "不能跟自己聊天！", Toast.LENGTH_SHORT).show();
        }else {
            Intent intent = new Intent(CharityDetailActivity.this, ChatActivity.class);
            //传递参数
            intent.putExtra(EaseConstant.EXTRA_USER_ID, hxid);
            //单聊
            intent.putExtra(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_SINGLE);
            startActivity(intent);
        }
    }

    //加入公益
    private void userJoin(){
        //获取时间
        final Date d = new Date();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取当前用户
        User user = UserUtils.getCurrentUser();
        AlertDialog.Builder ad = new AlertDialog.Builder(CharityDetailActivity.this);
        ad.setTitle("提示");
        ad.setMessage("加入后不可退出，确定参加？");
        ad.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(user.getHxId().trim().equals(uagdata.get("hxid").toString())){
                    Toast.makeText(CharityDetailActivity.this, "不能参加自己的公益！", Toast.LENGTH_SHORT).show();
                }else {
                    pd.setMessage("请稍后...");
                    pd.show();
                    Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
                        @Override
                        public void run() {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("requesttop","joincharity");
                            params.put("account",user.getAccount());
                            params.put("cid",uagdata.get("cid").toString());
                            params.put("cuid",uagdata.get("uid").toString());
                            params.put("jtime",sdf.format(d));
                            String strUrlpath = getResources().getString(R.string.burl) + "Charity_Servlet";
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
        });
        ad.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        ad.show();
    }

/*
    private void send(){
        pd.setMessage("请稍后...");
        pd.show();
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("requesttop","send");
                params.put("hxid","xiaohong");
                String strUrlpath = getResources().getString(R.string.burl) + "Charity_Servlet";
                String Result = HttpUtils.submitPostData(strUrlpath, params, "utf-8");
                System.out.println("结果为：" + Result);
                Message msg = new Message();
                msg.what = 3;
                msg.obj = Result;
                handler.sendMessage(msg);
            }
        });
    }
*/

    //设置浏览人数加一
    private void setScannum(){
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("requesttop","setscannum");
                params.put("cid",uagdata.get("cid").toString());
                String strUrlpath = getResources().getString(R.string.burl) + "Charity_Servlet";
                String Result = HttpUtils.submitPostData(strUrlpath, params, "utf-8");
                System.out.println("结果为：" + Result);
                Message msg = new Message();
                msg.what = 2;
                msg.obj = Result;
                handler.sendMessage(msg);
            }
        });
    }
}
