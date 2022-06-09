package com.myapp.qutaomarket.contoller.adapter;

import android.content.Context;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.hyphenate.easeui.EaseConstant;
import com.myapp.qutaomarket.QTApplication;
import com.myapp.qutaomarket.R;
import com.myapp.qutaomarket.contoller.activity.ChatActivity;
import com.myapp.qutaomarket.contoller.activity.GoodsdetailActivity;
import com.myapp.qutaomarket.model.Model;
import com.myapp.qutaomarket.utils.HttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MycollectAdapter extends BaseAdapter {
    // 创建ImageLoader对象
    private Context context;
    private List<Map<String, Object>> list;
    private int myPosition;
    public MycollectAdapter(Context context, List<Map<String, Object>> list, ListView listView){
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
        final MycollectAdapter.ViewHolder holder;
        myPosition = position;
        if(convertView ==null)

        {
            convertView = LayoutInflater.from(context).inflate(R.layout.mycollect_adapter, parent, false);
            holder = new MycollectAdapter.ViewHolder();

            holder.iv_coladapter_gimage = (ImageView) convertView.findViewById(R.id.iv_coladapter_gimage);
            holder.tv_coladapter_gname = (TextView) convertView.findViewById(R.id.tv_coladapter_gname);
            holder.tv_coladapter_gdetail = (TextView) convertView.findViewById(R.id.tv_coladapter_gdetail);
            holder.tv_coladapter_gstate = (TextView) convertView.findViewById(R.id.tv_coladapter_gstate);
            holder.tv_coladapter_uncollect = (TextView) convertView.findViewById(R.id.tv_coladapter_uncollect);
            holder.tv_coladapter_gprice = (TextView) convertView.findViewById(R.id.tv_coladapter_gprice);
            holder.tv_coladapter_gscannum = (TextView) convertView.findViewById(R.id.tv_coladapter_gscannum);
            holder.tv_coladapter_symbol = (TextView) convertView.findViewById(R.id.tv_coladapter_symbol);
            holder.ll_coladapter_chat = (LinearLayout)convertView.findViewById(R.id.ll_coladapter_chat);

            convertView.setTag(holder);
        }else{
            holder = (MycollectAdapter.ViewHolder) convertView.getTag();
        }
        Glide.with(convertView.getContext())
                .load(list.get(position).get("gimage").toString())
                .placeholder(R.drawable.ic_moren_goods)
                .error(R.drawable.ic_moren_goods)
                .into(holder.iv_coladapter_gimage);
        holder.tv_coladapter_gname.setText(list.get(position).get("gname").toString());
        holder.tv_coladapter_gdetail.setText(list.get(position).get("gdetail").toString());
        if(Integer.valueOf(list.get(position).get("gstate").toString()) == 1){
            holder.tv_coladapter_gstate.setText("在售");
        }else {
            holder.tv_coladapter_gstate.setText("已售出");
        }
        if(Double.valueOf(list.get(position).get("gprice").toString()) == 0){
            holder.tv_coladapter_symbol.setVisibility(View.GONE);
            holder.tv_coladapter_gprice.setText("免费送");
        }else {
            holder.tv_coladapter_gprice.setText(list.get(position).get("gprice").toString());
        }
        holder.tv_coladapter_gscannum.setText(list.get(position).get("gscannum").toString());
        holder.ll_coladapter_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChatActivity.class);
                //传递参数
                intent.putExtra(EaseConstant.EXTRA_USER_ID, list.get(position).get("hxid").toString());
                //单聊
                intent.putExtra(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_SINGLE);
                context.startActivity(intent);
            }
        });
        holder.tv_coladapter_uncollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
                    @Override
                    public void run() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("requesttop", "uncollect");
                        params.put("colid", list.get(position).get("colid").toString());
                        String strUrlpath = context.getResources().getString(R.string.burl) + "Goodsdetail_Servlet";
                        String Result = HttpUtils.submitPostData(strUrlpath, params, "utf-8");
                        System.out.println("结果为：" + Result);
                        Message message = new Message();
                        message.what = 1;
                        message.obj = Result;
                        handler.sendMessage(message);
                    }
                });
            }
        });
        return convertView;
    }
    static class ViewHolder{
        TextView tv_coladapter_symbol,tv_coladapter_gname,tv_coladapter_gdetail,tv_coladapter_gstate,tv_coladapter_gprice,tv_coladapter_gscannum,tv_coladapter_uncollect;
        ImageView iv_coladapter_gimage;
        LinearLayout ll_coladapter_chat;
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    int unCollectBack;
                    if(!msg.obj.toString().trim().isEmpty()&&!msg.obj.toString().trim().equals("-1"))
                    {
                        try{
                            JSONObject unCollectResult = new JSONObject(msg.obj.toString().trim());
                            unCollectBack = unCollectResult.getInt("code");
                            if(unCollectBack == 1){
                                Toast.makeText(context,"取消成功",Toast.LENGTH_SHORT).show();
                                list.remove(myPosition);
                                notifyDataSetChanged();
                            } else {
                                Toast.makeText(context,"取消失败",Toast.LENGTH_SHORT).show();
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }else {
                        Toast.makeText(context,"请检查网络",Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };
}
