package com.myapp.qutaomarket.contoller.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.myapp.qutaomarket.R;
import com.myapp.qutaomarket.contoller.activity.CharityDetailActivity;
import com.myapp.qutaomarket.contoller.activity.GoodsdetailActivity;
import com.myapp.qutaomarket.contoller.adapter.MypublishcharityAdapter;
import com.myapp.qutaomarket.contoller.adapter.MypublistAdapter;
import com.myapp.qutaomarket.model.Model;
import com.myapp.qutaomarket.model.db.User;
import com.myapp.qutaomarket.utils.HttpUtils;
import com.myapp.qutaomarket.utils.UserUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyPublishCharityFragment extends Fragment {

    private ListView lv_charitylist_list;

    //进度条
    private ProgressDialog pd;

    //发布列表
    private ArrayList<Map<String,Object>> mData= new ArrayList<Map<String,Object>>();

    //构造方法
    public MyPublishCharityFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_charity_mypub, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        lv_charitylist_list = (ListView)getActivity().findViewById(R.id.lv_charitylist_list);

        //初始化进度条
        pd = new ProgressDialog(getActivity());
        pd.setMessage("数据加载中...");
        pd.setIndeterminate(true);
        pd.setCancelable(false);

        //获取数据
        getCharityData();
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    int getDataBack;
                    if(!msg.obj.toString().trim().isEmpty()&&!msg.obj.toString().trim().equals("-1"))
                    {
                        pd.cancel();
                        try{
                            JSONArray data = new JSONArray();
                            JSONObject getDataResult = new JSONObject(msg.obj.toString().trim());
                            getDataBack = getDataResult.getInt("code");
                            if(getDataBack == 1){
                                data = getDataResult.getJSONArray("data");
                                for(int i=0;i<data.length();i++){
                                    String goodsimageurl = URLEncoder.encode(data.getJSONObject(i).getJSONObject("charitydata").getString("cimage"), "utf-8");
                                    String userimageurl = URLEncoder.encode(data.getJSONObject(i).getJSONObject("userdata").getString("headphoto"), "utf-8");
                                    //liatview显示
                                    Map<String,Object> item = new HashMap<String,Object>();
                                    item.put("uid",data.getJSONObject(i).getJSONObject("userdata").getInt("uid"));
                                    item.put("account",data.getJSONObject(i).getJSONObject("userdata").getString("account"));
                                    item.put("nickname",data.getJSONObject(i).getJSONObject("userdata").getString("nickname"));
                                    item.put("reputation",data.getJSONObject(i).getJSONObject("userdata").getString("reputation"));
                                    item.put("tel",data.getJSONObject(i).getJSONObject("userdata").getString("tel"));
                                    item.put("hxid",data.getJSONObject(i).getJSONObject("userdata").getString("hxid"));
                                    item.put("cid",data.getJSONObject(i).getJSONObject("charitydata").getInt("cid"));
                                    item.put("cname",data.getJSONObject(i).getJSONObject("charitydata").getString("cname"));
                                    item.put("cdetail",data.getJSONObject(i).getJSONObject("charitydata").getString("cdetail"));
                                    item.put("cneed",data.getJSONObject(i).getJSONObject("charitydata").getString("cneed"));
                                    item.put("cnumber",data.getJSONObject(i).getJSONObject("charitydata").getString("cnumber"));
                                    item.put("ctime",data.getJSONObject(i).getJSONObject("charitydata").getString("ctime"));
                                    item.put("cdeadline",data.getJSONObject(i).getJSONObject("charitydata").getString("cdeadline"));
                                    item.put("ctype",data.getJSONObject(i).getJSONObject("charitydata").getString("ctype"));
                                    item.put("caddress",data.getJSONObject(i).getJSONObject("charitydata").getString("caddress"));
                                    item.put("cscannum",data.getJSONObject(i).getJSONObject("charitydata").getString("cscannum"));
                                    item.put("cjoinnum",data.getJSONObject(i).getJSONObject("charitydata").getString("cjoinnum"));
                                    item.put("cstate",data.getJSONObject(i).getJSONObject("charitydata").getString("cstate"));
                                    item.put("style","mypublish");
                                    item.put("cimage",getResources().getString(R.string.burl)+"Image_Servlet?" + goodsimageurl);
                                    item.put("headphoto",getResources().getString(R.string.burl)+"Image_Servlet?" + userimageurl);
                                    mData.add(item);
                                }
                                MypublishcharityAdapter adapter = new MypublishcharityAdapter(getActivity(),mData, lv_charitylist_list);
                                lv_charitylist_list.setAdapter(adapter);
                                lv_charitylist_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int position,
                                                            long id) {
                                        Intent intent = new Intent(view.getContext(), CharityDetailActivity.class);
                                        intent.putExtra("data",(Serializable)mData.get(position));
                                        view.getContext().startActivity(intent);
                                    }
                                });
                            }else {
                                pd.cancel();
                                Toast.makeText(getActivity(),"获取数据失败",Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(getActivity(),"请检查网络",Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };

    //获取公益活动列表的数据
    private void getCharityData(){
        mData.clear();
        User user = UserUtils.getCurrentUser();
        pd.show();
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("requesttop","getmypubcharity");
                params.put("account",user.getAccount());
                String strUrlpath = getResources().getString(R.string.burl) + "Charity_Servlet";
                String Result = HttpUtils.submitPostData(strUrlpath, params, "utf-8");
                System.out.println("结果为：" + Result);
                Message message = new Message();
                message.what = 1;
                message.obj = Result;
                handler.sendMessage(message);
            }
        });
    }
}
