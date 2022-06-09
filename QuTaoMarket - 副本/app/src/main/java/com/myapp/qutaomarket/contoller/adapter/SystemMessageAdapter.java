package com.myapp.qutaomarket.contoller.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.myapp.qutaomarket.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//系统消息的适配器
public class SystemMessageAdapter extends BaseAdapter {

    //定义适配器的上下文
    private Context mContext;

    //定义消息列表
    private ArrayList<Map<String,Object>> messages;

    //适配器的构造方法
    public SystemMessageAdapter(Context context, ArrayList<Map<String,Object>> msgs) {
        this.mContext = context;
        this.messages = msgs;
    }

    @Override
    public int getCount() {
        return messages == null ? 0:(messages.size());
    }

    @Override
    public Object getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.adapter_system_message, null);
            holder.tv_sysmessage_time = (TextView) convertView.findViewById(R.id.tv_sysmessage_time);
            holder.tv_sysmessage_title = (TextView) convertView.findViewById(R.id.tv_sysmessage_title);
            holder.iv_sysmessage_state = (ImageView) convertView.findViewById(R.id.iv_sysmessage_state);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        //显示数据
        holder.tv_sysmessage_time.setText(messages.get(position).get("mtime").toString());
        holder.tv_sysmessage_title.setText(messages.get(position).get("mtitle").toString());
        int state = Integer.valueOf(messages.get(position).get("mstate").toString());
        if(state == 1){
            holder.iv_sysmessage_state.setImageResource(R.drawable.message_close);
        }else {
            holder.iv_sysmessage_state.setImageResource(R.drawable.message_open);
        }
        //返回数据
        return convertView;
    }

    private class ViewHolder{
        public TextView tv_sysmessage_time,tv_sysmessage_title;
        public ImageView iv_sysmessage_state;
    }
}
