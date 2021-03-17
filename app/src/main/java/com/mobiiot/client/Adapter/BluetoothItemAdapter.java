package com.mobiiot.client.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mobiiot.client.R;

import java.lang.reflect.Type;
import java.util.List;

public class BluetoothItemAdapter extends BaseAdapter {
    private List<String> listName,listAddress;
    private Context context;
    public BluetoothItemAdapter(Context context, List<String> listName, List<String> listAddress){
        this.context = context;
        this.listAddress = listAddress;
        this.listName = listName;
    }
    @Override
    public int getCount() {
        return listAddress.size();
    }

    @Override
    public Object getItem(int position) {
        return listAddress.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            //convertView = View.inflate(context,R.layout.item_bluetooth,parent);
            convertView = LayoutInflater.from(context).inflate(R.layout.item_bluetooth,parent,false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvName.setText(listName.get(position));
        viewHolder.tvAddress.setText(listAddress.get(position));
        return convertView;
    }

    class ViewHolder{
        private TextView tvName;
        private TextView tvAddress;
        public ViewHolder(View view){
            Typeface type = Typeface.createFromAsset(context.getAssets(),"CaviarDreams.ttf");
            tvName = view.findViewById(R.id.item_bluetooth_tvName);tvName.setTypeface(type);
            tvAddress = view.findViewById(R.id.item_bluetooth_tvAddress);tvAddress.setTypeface(type);
        }
    }
}
