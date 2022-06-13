package com.ebig.crosso.ui;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ebig.crosso.R;
import com.ebig.crosso.manager.type.AopDbInfo;
import com.ebig.crosso.utils.CroStrUtils;

import java.util.List;

public class InfoAdaper extends BaseAdapter {
    private Context context;
    private List<AopDbInfo> infoList;

    public InfoAdaper(Context context, List<AopDbInfo> infoList) {
        this.context = context;
        this.infoList = infoList;
    }

    @Override
    public int getCount() {
        return infoList.size();
    }

    @Override
    public Object getItem(int position) {
        return infoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return infoList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        final AopDbInfo info=infoList.get(position);
        if (holder == null) {
            convertView = View.inflate(context, R.layout.item_aopinfo, null);
            holder = new ViewHolder();
            holder.tv_type=(TextView)convertView.findViewById(R.id.tv_type);
            holder.tv_time=(TextView)convertView.findViewById(R.id.tv_time);
            holder.tv_detail=(TextView)convertView.findViewById(R.id.tv_detail);
            holder.tv_click=(LinearLayout)convertView.findViewById(R.id.tv_click);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_type.setText(CroStrUtils.getType(info.getEvent()));
        holder.tv_time.setText(info.getTimeStamp());
        holder.tv_detail.setText(CroStrUtils.getShortInfo(info));
        holder.tv_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QrDialog qrDialog=new QrDialog(context,info);
                qrDialog.show();
            }
        });
        return convertView;
    }

    static class ViewHolder {
        TextView tv_type;
        TextView tv_time;
        TextView tv_detail;
        LinearLayout tv_click;
    }
}
