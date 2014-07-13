package com.home.homefinance.activity.activity;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import com.home.HomeFinance.R;
import com.home.homefinance.activity.bd.Name;
import com.home.homefinance.activity.bd.SqlAdapter;


public class MyEarnings  extends ListActivity {

    SqlAdapter adapter;

    EditText nameEdit;
    ListView listView;
    TextView currentName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earnings_layout);
        setUpView();
    }

    public void addName(View view) {
        String name = nameEdit.getText().toString().trim();
        if (!"".equals(name)) {
            Name newName = new Name(name);
            adapter.addItem(newName);
        }
        finishInput();
    }

    private void setUpView() {
       listView = getListView();
        currentName = (TextView) findViewById(R.id.name_view1);
        nameEdit = (EditText) findViewById(R.id.name_edit);

        adapter = new SqlAdapter(this);
       // setListAdapter(adapter);
    }

    // ����� ������� ���� ����� � ������ �������� ����������
    private void finishInput() {
        nameEdit.getText().clear();
        InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(nameEdit.getWindowToken(), 0);
    }

    public void removeEntry(View view) {
        String name = currentName.getText().toString();
        Name nameToRemove = new Name(name);
        adapter.removeItem(nameToRemove);
        currentName.setText("��� ������� ������?");
    }

    @Override
    protected void onListItemClick(ListView list, View v, int position, long id) {
        super.onListItemClick(list, v, position, id);
        Name selectedName = adapter.getItem(position);
        currentName.setText(selectedName.getName());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        adapter.onDestroy();
    }
}