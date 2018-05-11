package com.example.jkb.myapplication;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.jkb.myapplication.utils.DensityUtils;
import com.example.jkb.myapplication.utils.GlideCircleTransform;
import com.example.jkb.myapplication.utils.TimeUtils;

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

            holder.tv_name = (TextView)convertView.findViewById(R.id.tv_name);
            holder.tv_time = (TextView)convertView.findViewById(R.id.tv_time);
            holder.tv_content = (TextView)convertView.findViewById(R.id.tv_content);
            holder.iv_icon = (ImageView) convertView.findViewById(R.id.iv_icon);
            holder.iv_picture = (ImageView)convertView.findViewById(R.id.iv_picture);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Micropost micropost = list.get(position);
        if (micropost.getPicture()!=null){
            holder.iv_picture.setVisibility(View.VISIBLE);
            Glide.with(context).
                    load(DemoService.url+micropost.getPicture()).dontAnimate().
                    override(DensityUtils.dip2px(context,200),DensityUtils.dip2px(context,200)).
                    diskCacheStrategy(DiskCacheStrategy.ALL).
                    into(holder.iv_picture);
        }else {
            holder.iv_picture.setVisibility(View.GONE);
        }

        Glide.with(context).
                load(DemoService.url+micropost.getIcon()).dontAnimate().
                diskCacheStrategy(DiskCacheStrategy.ALL).
                placeholder(ContextCompat.getDrawable(context, R.mipmap.ic_launcher)).
                error(ContextCompat.getDrawable(context, R.mipmap.ic_launcher)).
                transform(new GlideCircleTransform(context)).
                into(holder.iv_icon);

        holder.tv_content.setText(micropost.getContent());
        holder.tv_name.setText(micropost.getUser_name());
        holder.tv_time.setText(TimeUtils.CalculateTime(micropost.getCreated_at()));
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
        TextView tv_name;
        TextView tv_time;
        TextView tv_content;
        ImageView iv_icon;
        ImageView iv_picture;
    }
}
