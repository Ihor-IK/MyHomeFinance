package com.home.homefinance.activity.activity;


import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import com.home.homefinance.R;
import com.home.homefinance.activity.adapter.CostsCategoryAdapter;
import com.home.homefinance.activity.myListCosts.CostsCategory;

import java.util.ArrayList;
import java.util.List;

public class MyCosts  extends Activity {

    private ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.costs_list_layout);

        listView = (ListView) findViewById(R.id.listView);

        CostsCategoryAdapter adapter = new CostsCategoryAdapter(this,initData() );

        listView.setAdapter(adapter);
    }

    private List<CostsCategory> initData(){
        List<CostsCategory> list=new ArrayList<CostsCategory>();
        list.add(new CostsCategory(1,"Products",1000));
        list.add(new CostsCategory(2,"Clothing",1000));
        list.add(new CostsCategory(3,"Cars",1000));
        list.add(new CostsCategory(4,"Children",1000));
        list.add(new CostsCategory(5,"Parents",1000));
        list.add(new CostsCategory(6,"Medicine",1000));
        list.add(new CostsCategory(7,"Contingencies",1000));
        list.add(new CostsCategory(8,"Recreation",1000));
        list.add(new CostsCategory(9,"Gifts",1000));
        return list;


    }
}
