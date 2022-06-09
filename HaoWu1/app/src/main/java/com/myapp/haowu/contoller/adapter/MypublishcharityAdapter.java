package com.myapp.haowu.contoller.adapter;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.myapp.haowu.R;
import com.myapp.haowu.contoller.activity.PublishcharityActivity;
import com.myapp.haowu.model.Model;
import com.myapp.haowu.utils.HttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MypublishcharityAdapter extends BaseAdapter {
    // 创建ImageLoader对象
    private Context context;
    private List<Map<String, Object>> list;
    private int myPosition;
    //进度条
    private ProgressDialog pd;
    public MypublishcharityAdapter(Context context, List<Map<String, Object>> list, ListView listView){
        this.context = context;
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final MypublishcharityAdapter.ViewHolder holder;
        //初始化进度条
        pd = new ProgressDialog(context);
        pd.setMessage("请稍等...");
        pd.setIndeterminate(true);
        pd.setCancelable(false);

        if(convertView ==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_mypublish_charity, parent, false);
            holder = new MypublishcharityAdapter.ViewHolder();

            holder.iv_mycharity_state = (ImageView) convertView.findViewById(R.id.iv_mycharity_state);
            holder.tv_mycharity_cname = (TextView) convertView.findViewById(R.id.tv_mycharity_cname);
            holder.tv_mycharity_time = (TextView) convertView.findViewById(R.id.tv_mycharity_time);
            holder.tv_mycharity_edit = (TextView) convertView.findViewById(R.id.tv_mycharity_edit);
            holder.tv_mycharity_delete = (TextView) convertView.findViewById(R.id.tv_mycharity_delete);
            holder.ll_mycharity_hui = (LinearLayout) convertView.findViewById(R.id.ll_mycharity_hui);
            holder.rl_mycharity_symble = (RelativeLayout) convertView.findViewById(R.id.rl_mycharity_symble);
            convertView.setTag(holder);
        }else{
            holder = (MypublishcharityAdapter.ViewHolder) convertView.getTag();
        }
        if("mypart".equals(list.get(position).get("style").toString())){
            holder.ll_mycharity_hui.setVisibility(View.GONE);
            holder.rl_mycharity_symble.setVisibility(View.GONE);
        }
        if (Integer.valueOf(list.get(position).get("cstate").toString()) == 1){
            Glide.with(convertView.getContext())
                    .load(R.drawable.youxiao)
                    .placeholder(R.drawable.youxiao)
                    .error(R.drawable.youxiao)
                    .into(holder.iv_mycharity_state);
        }else if (Integer.valueOf(list.get(position).get("cstate").toString()) == 2){
            Glide.with(convertView.getContext())
                    .load(R.drawable.shixiao)
                    .placeholder(R.drawable.shixiao)
                    .error(R.drawable.shixiao)
                    .into(holder.iv_mycharity_state);
        }else ;

        holder.tv_mycharity_cname.setText(list.get(position).get("cname").toString());
        holder.tv_mycharity_time.setText(list.get(position).get("ctime").toString());
        holder.tv_mycharity_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PublishcharityActivity.class);
                //传递参数
                intent.putExtra("title", "编辑公益");
                intent.putExtra("type", "editcharity");
                intent.putExtra("data", (Serializable)list.get(position));
                context.startActivity(intent);
            }
        });
        holder.tv_mycharity_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(context).setTitle("删除公益")
                        .setMessage("确定删除该公益吗？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                pd.show();
                                myPosition = position;
                                Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        Map<String, String> params = new HashMap<String, String>();
                                        params.put("requesttop", "deletecharity");
                                        params.put("cid", list.get(position).get("cid").toString());
                                        String strUrlpath = context.getResources().getString(R.string.burl) + "Charity_Servlet";
                                        String Result = HttpUtils.submitPostData(strUrlpath, params, "utf-8");
                                        System.out.println("结果为：" + Result);
                                        Message message = new Message();
                                        message.what = 1;
                                        message.obj = Result;
                                        handler.sendMessage(message);
                                    }
                                });
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }).create();
                alertDialog.show();
            }
        });
        return convertView;
    }
    static class ViewHolder{
        TextView tv_mycharity_cname,tv_mycharity_time,tv_mycharity_edit,tv_mycharity_delete;
        ImageView iv_mycharity_state;
        LinearLayout ll_mycharity_hui;
        RelativeLayout rl_mycharity_symble;
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    int deleteBack;
                    if(!msg.obj.toString().trim().isEmpty()&&!msg.obj.toString().trim().equals("-1"))
                    {
                        try{
                            JSONObject deleteResult = new JSONObject(msg.obj.toString().trim());
                            deleteBack = deleteResult.getInt("code");
                            if(deleteBack == 1){
                                pd.cancel();
                                Toast.makeText(context,"已删除",Toast.LENGTH_SHORT).show();
                                list.remove(myPosition);
                                notifyDataSetChanged();
                            } else {
                                pd.cancel();
                                Toast.makeText(context,"删除失败",Toast.LENGTH_SHORT).show();
                            }
                        }catch (JSONException e){
                            pd.cancel();
                            e.printStackTrace();
                        }
                    }else {
                        pd.cancel();
                        Toast.makeText(context,"请检查网络",Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };
}
