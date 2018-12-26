package com.example.a.testfinal;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.a.testfinal.dao.MemberDAO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    Button btnAdd;
    Button btnDate;
    EditText edt_username, edt_pass, edt_date, edt_place, edt_image;
    Spinner spn_dep;
    RadioGroup rdo_gender;
    RadioButton btn_rdo_gender;
    MemberDAO memberDAO;
    String depID="";
    String date="";
    DatePickerDialog datePickerDialog;
    ImageView img_avatar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initModel();
        initView();
        initEvent();
    }

    private void initView() {
        memberDAO = new MemberDAO(RegisterActivity.this);
        List<String> list = new ArrayList<>();
        list.add("Design");
        list.add("Database");
        ArrayAdapter<String> adapter = new ArrayAdapter(RegisterActivity.this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spn_dep.setAdapter(adapter);


    }
    private void initEvent() {
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar=Calendar.getInstance();
                datePickerDialog=new DatePickerDialog(RegisterActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        calendar.set(i,i1,i2);
                        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
                        date= simpleDateFormat.format(calendar.getTime());
                        edt_date.setText(date);

                    }
                },1998,12,15);
                datePickerDialog.show();
            }
        });
        edt_image.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    int id_image = getResources().getIdentifier(getPackageName()+":drawable/" +edt_image.getText().toString() ,
                            null, null);
                    img_avatar.setImageResource(id_image);
                }catch (Exception e){
                    img_avatar.setImageResource(R.drawable.ic_launcher_background);
                    e.printStackTrace();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        spn_dep.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                depID=spn_dep.getSelectedItem().toString();
                Toast.makeText(RegisterActivity.this,spn_dep.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int selectedId = rdo_gender.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                btn_rdo_gender = (RadioButton) findViewById(selectedId);

              //  Toast.makeText(RegisterActivity.this, btn_rdo_gender.getText().toString() + edt_username.getText().toString(), Toast.LENGTH_SHORT).show();

                String name = edt_username.getText().toString();
                String pass = edt_pass.getText().toString();
                String gender = btn_rdo_gender.getText().toString().trim();
                String date = edt_date.getText().toString();
                String place = edt_place.getText().toString();
                String depID_spn = depID;
                String image = edt_image.getText().toString();
                String roles = "1";
                memberDAO.insert(RegisterActivity.this,name,pass,gender.equals("Male")  ? "1" : "0",date,place,(depID_spn=="Design"?"1":"2"),image,roles);
                overridePendingTransition(0, 0);

            }


        });
    }

    private void initModel() {

        btnAdd = (Button) findViewById(R.id.btn_register);
        btnDate = (Button) findViewById(R.id.btn_choose_date);
        edt_username = (EditText) findViewById(R.id.edt_username);
        edt_pass = (EditText) findViewById(R.id.edt_pass);
        rdo_gender = (RadioGroup) findViewById(R.id.rdo_gender);
        edt_date = (EditText) findViewById(R.id.edt_date);
        edt_place = (EditText) findViewById(R.id.edt_place);
        edt_image = (EditText) findViewById(R.id.edt_image);
        spn_dep = (Spinner) findViewById(R.id.spn_dep);
        img_avatar=(ImageView) findViewById(R.id.img_avatar);


    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}

