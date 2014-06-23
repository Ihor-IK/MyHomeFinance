package com.home.homefinance.activity.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.home.HomeFinance.R;


public class MyActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void onShow(View v){
        Intent intent;
        switch (v.getId()){
            case R.id.buttonBalance:
                intent = new Intent(this,MyBalance_of_money.class);
                startActivity(intent);
                break;
            case R.id.buttonCosts:
                intent = new Intent(this,MyCosts.class);
                startActivity(intent);
                break;
            case R.id.buttonEarnings:
                intent = new Intent(this,MyEarnings.class);
                startActivity(intent);
                break;
        }

    }

}
