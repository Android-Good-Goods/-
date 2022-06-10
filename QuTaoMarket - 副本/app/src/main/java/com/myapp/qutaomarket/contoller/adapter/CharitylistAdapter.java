package com.myapp.qutaomarket.contoller.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.myapp.qutaomarket.R;
import java.util.List;
import java.util.Map;

public class CharitylistAdapter extends BaseAdapter {
    // 创建ImageLoader对象
    private Context context;
    private List<Map<String, Object>> list;
    private int myPosition;
    //进度条
    private ProgressDialog pd;
    public CharitylistAdapter(Context context, List<Map<String, Object>> list, ListView listView){
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
        final CharitylistAdapter.ViewHolder holder;
        myPosition = position;
        //初始化进度条
        pd = new ProgressDialog(context);
        pd.setMessage("请稍等...");
        pd.setIndeterminate(true);
        pd.setCancelable(false);

        if(convertView ==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_charitylist_item, parent, false);
            holder = new CharitylistAdapter.ViewHolder();

            holder.iv_charitylist_cimage = (ImageView) convertView.findViewById(R.id.iv_charitylist_cimage);
            holder.tv_charitylist_cname = (TextView) convertView.findViewById(R.id.tv_charitylist_cname);
            holder.tv_charity_cstate = (TextView) convertView.findViewById(R.id.tv_charity_cstate);
            holder.tv_charitylist_ccontent = (TextView) convertView.findViewById(R.id.tv_charitylist_ccontent);
            holder.tv_charitylist_cjoinnum = (TextView) convertView.findViewById(R.id.tv_charitylist_cjoinnum);
            holder.tv_charitylist_cscannum = (TextView) convertView.findViewById(R.id.tv_charitylist_cscannum);
            convertView.setTag(holder);
        }else{
            holder = (CharitylistAdapter.ViewHolder) convertView.getTag();
        }
        if (Integer.valueOf(list.get(position).get("cstate").toString()) == 1){
            holder.tv_charity_cstate.setText("进行中");
        }else if (Integer.valueOf(list.get(position).get("cstate").toString()) == 2){
            holder.tv_charity_cstate.setText("已失效");
        }else ;
        Glide.with(convertView.getContext())
                .load(list.get(position).get("cimage").toString())
                .placeholder(R.drawable.ic_moren_goods)
                .error(R.drawable.ic_moren_goods)
                .into(holder.iv_charitylist_cimage);
        holder.tv_charitylist_cname.setText(list.get(position).get("cname").toString());
        holder.tv_charitylist_ccontent.setText(list.get(position).get("cdetail").toString());
        holder.tv_charitylist_cjoinnum.setText(list.get(position).get("cjoinnum").toString());
        holder.tv_charitylist_cscannum.setText(list.get(position).get("cscannum").toString());
        return convertView;
    }
    static class ViewHolder{
        TextView tv_charitylist_cname,tv_charity_cstate,tv_charitylist_ccontent,tv_charitylist_cscannum,tv_charitylist_cjoinnum;
        ImageView iv_charitylist_cimage;
    }
}
