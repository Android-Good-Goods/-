package com.myapp.qutaomarket.contoller.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.myapp.qutaomarket.R;
import com.myapp.qutaomarket.contoller.adapter.CharitylistAdapter;
import com.myapp.qutaomarket.model.Model;
import com.myapp.qutaomarket.utils.HttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CharityListActivity extends AppCompatActivity {

    //定义变量
    private ImageView iv_charitylist_back;

    private ListView lv_charitylist_list;

    private CharityListActivity.onClickListener listener;

    //进度条
    private ProgressDialog pd;

    //商品列表
    private ArrayList<Map<String,Object>> mData= new ArrayList<Map<String,Object>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charity_list);

        //初始化变量
        initView();
        //获取数据
        getCharityData();
    }

    private void initView() {
        //初始化进度条
        pd = new ProgressDialog(CharityListActivity.this);
        pd.setMessage("数据加载中...");
        pd.setIndeterminate(true);
        pd.setCancelable(false);

        //初始化变量
        lv_charitylist_list = (ListView)findViewById(R.id.lv_charitylist_list);
        iv_charitylist_back = (ImageView) findViewById(R.id.iv_charitylist_back);
        //返回按钮的监听
        listener = new onClickListener();
        iv_charitylist_back.setOnClickListener(listener);
    }

    //handler处理
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    int getCharityBack;
                    if(!msg.obj.toString().trim().isEmpty()&&!msg.obj.toString().trim().equals("-1"))
                    {
                        pd.cancel();
                        try{
                            JSONArray charityData = new JSONArray();
                            JSONObject getCharityDataResult = new JSONObject(msg.obj.toString().trim());
                            getCharityBack = getCharityDataResult.getInt("code");
                            if(getCharityBack == 1){
                                //获取返回的公益列表数据
                                charityData = getCharityDataResult.getJSONArray("data");
                                for(int i=0;i<charityData.length();i++){
                                    String goodsimageurl = URLEncoder.encode(charityData.getJSONObject(i).getJSONObject("charitydata").getString("cimage"), "utf-8");
                                    String userimageurl = URLEncoder.encode(charityData.getJSONObject(i).getJSONObject("userdata").getString("headphoto"), "utf-8");
                                    //将数据添加到列表中
                                    Map<String,Object> item = new HashMap<String,Object>();
                                    item.put("uid",charityData.getJSONObject(i).getJSONObject("userdata").getInt("uid"));
                                    item.put("account",charityData.getJSONObject(i).getJSONObject("userdata").getString("account"));
                                    item.put("nickname",charityData.getJSONObject(i).getJSONObject("userdata").getString("nickname"));
                                    item.put("reputation",charityData.getJSONObject(i).getJSONObject("userdata").getString("reputation"));
                                    item.put("tel",charityData.getJSONObject(i).getJSONObject("userdata").getString("tel"));
                                    item.put("hxid",charityData.getJSONObject(i).getJSONObject("userdata").getString("hxid"));
                                    item.put("cid",charityData.getJSONObject(i).getJSONObject("charitydata").getInt("cid"));
                                    item.put("cname",charityData.getJSONObject(i).getJSONObject("charitydata").getString("cname"));
                                    item.put("cdetail",charityData.getJSONObject(i).getJSONObject("charitydata").getString("cdetail"));
                                    item.put("cneed",charityData.getJSONObject(i).getJSONObject("charitydata").getString("cneed"));
                                    item.put("cnumber",charityData.getJSONObject(i).getJSONObject("charitydata").getString("cnumber"));
                                    item.put("ctime",charityData.getJSONObject(i).getJSONObject("charitydata").getString("ctime"));
                                    item.put("cdeadline",charityData.getJSONObject(i).getJSONObject("charitydata").getString("cdeadline"));
                                    item.put("ctype",charityData.getJSONObject(i).getJSONObject("charitydata").getString("ctype"));
                                    item.put("caddress",charityData.getJSONObject(i).getJSONObject("charitydata").getString("caddress"));
                                    item.put("cscannum",charityData.getJSONObject(i).getJSONObject("charitydata").getString("cscannum"));
                                    item.put("cjoinnum",charityData.getJSONObject(i).getJSONObject("charitydata").getString("cjoinnum"));
                                    item.put("cstate",charityData.getJSONObject(i).getJSONObject("charitydata").getString("cstate"));
                                    item.put("cimage",getResources().getString(R.string.burl)+"Image_Servlet?" + goodsimageurl);
                                    item.put("headphoto",getResources().getString(R.string.burl)+"Image_Servlet?" + userimageurl);
                                    mData.add(item);
                                }
                                //设置适配器
                                CharitylistAdapter adapter = new CharitylistAdapter(CharityListActivity.this,mData, lv_charitylist_list);
                                lv_charitylist_list.setAdapter(adapter);
                                //设置公益列表的点击事件
                                lv_charitylist_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                                        Intent intent = new Intent(view.getContext(), CharityDetailActivity.class);
                                        intent.putExtra("data",(Serializable)mData.get(position));
                                        view.getContext().startActivity(intent);
                                    }
                                });
                            }
                        }catch (JSONException e){
                            pd.cancel();
                            e.printStackTrace();
                        } catch (UnsupportedEncodingException e) {
                            pd.cancel();
                            e.printStackTrace();
                        }
                    }else {
                        pd.cancel();
                        Toast.makeText(CharityListActivity.this,"请检查网络",Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };

    //返回按键的点击事件
    private class onClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.iv_charitylist_back:
                    finish();
                    break;
            }
        }
    }

    //获取公益活动列表的数据
    private void getCharityData(){
        pd.show();
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            HttpUtils httpUtils = new HttpUtils();
            @Override
            public void run() {
                mData.clear();
                Map<String, String> params = new HashMap<String, String>();
                params.put("requesttop","getcharity");
                params.put("cstate","1");
                String strUrlpath = getResources().getString(R.string.burl) + "Charity_Servlet";
                String Result = httpUtils.AsubmitPostData(strUrlpath, params, "utf-8");
                System.out.println("获取的结果为：" + Result);
                Message message = new Message();
                message.what = 1;
                message.obj = Result;
                handler.sendMessage(message);
            }
        });
    }
}
