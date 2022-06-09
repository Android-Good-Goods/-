package com.myapp.haowu.contoller.fragment;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.myapp.haowu.R;
import com.myapp.haowu.contoller.adapter.SystemMessageAdapter;
import com.myapp.haowu.model.Model;
import com.myapp.haowu.model.db.User;
import com.myapp.haowu.utils.HttpUtils;
import com.myapp.haowu.utils.UserUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SystemMessageFragment extends Fragment {

    private ListView ll_message_list;
    private SwipeRefreshLayout sl_message_refresh;
    private SystemMessageAdapter adapter;

    //进度条
    private ProgressDialog pd;

    //消息列表
    private ArrayList<Map<String,Object>> mData= new ArrayList<Map<String,Object>>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_systemmessage, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //初始化视图
        initView();
        //获取系统消息
        getMessage();
    }

    //handler处理
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
                                    //liatview显示
                                    Map<String,Object> item = new HashMap<String,Object>();
                                    item.put("mid",data.getJSONObject(i).getInt("mid"));
                                    item.put("receiveid",data.getJSONObject(i).getInt("receiveid"));
                                    item.put("mtitle",data.getJSONObject(i).getString("mtitle"));
                                    item.put("mcontent",data.getJSONObject(i).getString("mcontent"));
                                    item.put("mtime",data.getJSONObject(i).getString("mtime"));
                                    item.put("mstate",data.getJSONObject(i).getInt("mstate"));
                                    mData.add(item);
                                }
                                adapter = new SystemMessageAdapter(getActivity(),mData);
                                ll_message_list.setAdapter(adapter);
                                ll_message_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                                        AlertDialog alert = new AlertDialog.Builder(getActivity()).setTitle(mData.get(position).get("mtitle").toString())
                                                .setMessage(mData.get(position).get("mcontent").toString())
                                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {//设置确定按钮
                                                    @Override//处理确定按钮点击事件
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        dialog.cancel();//对话框关闭
                                                        if("1".equals(mData.get(position).get("mstate").toString())){
                                                            //如果是未读消息，则将消息状态修改为已读
                                                            changeState(mData.get(position).get("mid").toString(), "2");
                                                        }
                                                    }
                                                })
                                                .setNegativeButton("删除消息", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        //将消息id修改成3即删除消息
                                                        changeState(mData.get(position).get("mid").toString(), "3");
                                                    }
                                                }).create();
                                        alert.show();
                                    }
                                });
                                adapter.notifyDataSetChanged();
                                if(sl_message_refresh.isRefreshing()){
                                    sl_message_refresh.setRefreshing(false);
                                }
                            }else {
                                pd.cancel();
                                if(sl_message_refresh.isRefreshing()){
                                    sl_message_refresh.setRefreshing(false);
                                }
                                Toast.makeText(getActivity(),"还没有消息哦！",Toast.LENGTH_SHORT).show();
                            }
                        }catch (JSONException e){
                            pd.cancel();
                            if(sl_message_refresh.isRefreshing()){
                                sl_message_refresh.setRefreshing(false);
                            }
                            e.printStackTrace();
                        }
                    }else {
                        pd.cancel();
                        if(sl_message_refresh.isRefreshing()){
                            sl_message_refresh.setRefreshing(false);
                        }
                        Toast.makeText(getActivity(),"请检查网络",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 2:
                    if(!msg.obj.toString().trim().isEmpty()&&!msg.obj.toString().trim().equals("-1")) {
                        try{
                            final JSONObject json = new JSONObject(msg.obj.toString().trim());
                            int back = json.getInt("code");
                            if(back == 1){
                                getMessage();
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }else{
                        Toast.makeText(getActivity(), "请检查网络链接!", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };

    //初始化视图
    private void initView() {
        //初始化进度条
        pd = new ProgressDialog(getActivity());
        pd.setMessage("数据加载中...");
        pd.setIndeterminate(true);
        pd.setCancelable(false);

        //初始化控件
        ll_message_list = (ListView) getView().findViewById(R.id.ll_message_list);
        sl_message_refresh = (SwipeRefreshLayout)getView().findViewById(R.id.sl_message_refresh);

        //下拉刷新
        sl_message_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //重新从服务器获取消息
                getMessage();
            }
        });
    }

    //获取系统消息
    private void getMessage(){
        pd.show();
        mData.clear();
        User user = UserUtils.getCurrentUser();
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("requesttop","getmessage");
                params.put("account",user.getAccount());
                String strUrlpath = getResources().getString(R.string.burl) + "Message_Servlet";
                String Result = HttpUtils.submitPostData(strUrlpath, params, "utf-8");
                System.out.println("结果为：" + Result);
                Message message = new Message();
                message.what = 1;
                message.obj = Result;
                handler.sendMessage(message);
            }
        });
    }

    //改变消息状态
    public void changeState(final String mid,final String state)
    {
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("requesttop","changemessage");
                params.put("mid",mid);
                params.put("state",state);
                String strUrlpath = getResources().getString(R.string.burl) + "Message_Servlet";
                String Result = HttpUtils.submitPostData(strUrlpath, params, "utf-8");
                System.out.println("结果为：" + Result);
                Message message = new Message();
                message.what = 2;
                message.obj = Result;
                handler.sendMessage(message);
            }
        });
    }
}
