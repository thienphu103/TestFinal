package com.example.a.testfinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.a.testfinal.adapter.MemberAdapter;
import com.example.a.testfinal.dao.MemberDAO;
import com.example.a.testfinal.model.Member;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
ListView listView;
Button btnAdd;
MemberDAO memberDAO;
ArrayList<Member> arrayList;
MemberAdapter memberAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initModel();
        initView();
        initEvent();
    }



    private void initView() {
        memberDAO =new MemberDAO(MainActivity.this);
        arrayList=new ArrayList<>();
        arrayList=memberDAO.viewAll();
        memberAdapter= new MemberAdapter(MainActivity.this,arrayList);
        listView.setAdapter(memberAdapter);
    }
    private void initEvent() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {

           startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
           overridePendingTransition(0, 0);

       };
   });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Member member=memberDAO.viewByID(arrayList.get(i).getUsername().toString());
                Intent intent=new Intent(MainActivity.this,UpdateActivity.class);
                intent.putExtra("member_data",member);

                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                memberDAO.delete(MainActivity.this,arrayList.get(i).getUsername());

                Intent intent=new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                return false;
            }
        });
    }
    private void initModel() {
        listView=(ListView) findViewById(R.id.list_view);
        btnAdd=(Button) findViewById(R.id.add);

    }
    private  void updateUI(){

    }
}
