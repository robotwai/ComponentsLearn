package com.example.jkb.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jkb on 18/3/5.
 */

public class MicropostAdapter extends BaseAdapter {
    private Context context;
    List<Micropost> list = new ArrayList<>();

    public MicropostAdapter(Context context) {
        this.context = context;
    }
    public void setData(List<Micropost> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }
    public void clearData(){
        list.clear();
    }

    public List<Micropost> getData(){
        return list;
    }
    @Override
    public long getItemId(int position) {
        return list.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.ad_micropost, null);

            holder.textView7 = (TextView)convertView.findViewById(R.id.textView7);
            holder.textView8 = (TextView)convertView.findViewById(R.id.textView8);
            holder.textView9 = (TextView)convertView.findViewById(R.id.textView9);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Micropost micropost = list.get(position);

//        Glide.with(context).
//                load(ImageUtils.getInstance().formatUrl(healthInfo.getPath())).dontAnimate().
//                diskCacheStrategy(DiskCacheStrategy.ALL).
//                error(R.mipmap.healthinfo_default).
//                placeholder(R.mipmap.healthinfo_default).
//                centerCrop().into(holder.iv);


        holder.textView7.setText(micropost.getContent());
        holder.textView8.setText(micropost.getId()+"");
        holder.textView9.setText(micropost.getCreated_at());
        return convertView;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return list==null?0:list.size();
    }


    class ViewHolder {
        TextView textView7;
        TextView textView8;
        TextView textView9;
    }
}
