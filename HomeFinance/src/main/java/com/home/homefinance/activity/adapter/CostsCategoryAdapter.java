package com.home.homefinance.activity.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.home.homefinance.R;
import com.home.homefinance.activity.myListCosts.CostsCategory;

import java.util.List;

public class CostsCategoryAdapter extends BaseAdapter {

    private List <CostsCategory> list;
    private LayoutInflater layoutInflater;

    public CostsCategoryAdapter(Context context, List<CostsCategory> list) {
        this.list = list;
        layoutInflater  = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

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

        View view = convertView;
        if(view==null){
            view = layoutInflater.inflate(R.layout.costs_layout, parent, false);
        }

        CostsCategory costsCategory = getCostsCategory(position);
        TextView textViewCosts = (TextView)view.findViewById(R.id.textViewCosts);
        textViewCosts.setText(costsCategory.getName());

        return view;
    }

    private CostsCategory getCostsCategory(int position){
        return (CostsCategory)getItem(position);
    }
}
