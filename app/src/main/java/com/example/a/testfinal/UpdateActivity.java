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
import android.widget.TextView;
import android.widget.Toast;

import com.example.a.testfinal.dao.MemberDAO;
import com.example.a.testfinal.model.Member;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class UpdateActivity extends AppCompatActivity {
    EditText edt_update_name;
    EditText edt_update_date;
    EditText edt_update_place;
    Spinner spn_update_roles;
    Spinner spn_update_dep;
    RadioGroup rdo_update_gender;
    RadioButton btn_update_rdo_gender;
    TextView txt_change_password;
    EditText edt_update_image;
    ImageView img_view_update;
    RadioButton rdo_update_male;
    RadioButton rdo_update_female;
    Button btnDate;
    DatePickerDialog datePickerDialog;
    String date;
    private String depID;
    private Button btnUpdate;
    private MemberDAO memberDAO;
    String Roles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        initModel();
        initView();
        initEvent();


    }

    private void initView() {
        Member member = (Member) getIntent().getSerializableExtra("member_data");
        edt_update_name.setText(member.getUsername().toString());
        edt_update_date.setText(member.getDatefbirth().toString());
        edt_update_place.setText(member.getPlaceofbirth().toString());
        int id_image = getResources().getIdentifier(getPackageName() + ":drawable/" + member.getImage().toString(),
                null, null);
        img_view_update.setImageResource(id_image);
        edt_update_image.setText(member.getImage());
        edt_update_image.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    int id_image = getResources().getIdentifier(getPackageName() + ":drawable/" + edt_update_image.getText().toString(),
                            null, null);
                    img_view_update.setImageResource(id_image);

                } catch (Exception e) {
                    img_view_update.setImageResource(R.drawable.ic_launcher_background);
                    e.printStackTrace();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        List<String> list_dep = new ArrayList<>();
        list_dep.add("Design");
        list_dep.add("Database");
        ArrayAdapter<String> adapter_dep = new ArrayAdapter(UpdateActivity.this, android.R.layout.simple_spinner_item, list_dep);
        adapter_dep.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spn_update_dep.setAdapter(adapter_dep);

        spn_update_dep.setSelection((member.getDepID() == 1 ? 0 : 1));

        List<String> list_roles = new ArrayList<>();
        list_roles.add("Member");
        list_roles.add("Admin");
        ArrayAdapter<String> adapter_roles = new ArrayAdapter(UpdateActivity.this, android.R.layout.simple_spinner_item, list_roles);
        adapter_roles.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spn_update_roles.setAdapter(adapter_roles);
        spn_update_roles.setSelection((member.getAcctype() == 1 ? 0 : 1));
        rdo_update_male.setChecked(member.isGender() == true ? true : false); // nếu là nam database tra ve true (radio button checked =true)
        rdo_update_female.setChecked(member.isGender() == false ? true : false);//nếu là nu database tra ve false (radio button checked =true)
        Toast.makeText(this, String.valueOf(member.isGender()), Toast.LENGTH_SHORT).show();


    }

    private void initEvent() {
        btnDate.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                datePickerDialog = new DatePickerDialog(UpdateActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        calendar.set(i, i1, i2);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        date = simpleDateFormat.format(calendar.getTime());
                        edt_update_date.setText(date);

                    }
                }, 1998, 12, 15);
                datePickerDialog.show();
            }
        });
        edt_update_image.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    int id_image = getResources().getIdentifier(getPackageName() + ":drawable/" + edt_update_image.getText().toString(),
                            null, null);
                    img_view_update.setImageResource(id_image);
                } catch (Exception e) {
                    img_view_update.setImageResource(R.drawable.ic_launcher_background);
                    e.printStackTrace();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        spn_update_dep.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                depID = spn_update_dep.getSelectedItem().toString();
                Toast.makeText(UpdateActivity.this, spn_update_dep.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spn_update_roles.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Roles = spn_update_roles.getSelectedItem().toString();
                Toast.makeText(UpdateActivity.this, spn_update_roles.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                memberDAO = new MemberDAO(UpdateActivity.this);
                int selectedId = rdo_update_gender.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                btn_update_rdo_gender = (RadioButton) findViewById(selectedId);

                //  Toast.makeText(RegisterActivity.this, btn_rdo_gender.getText().toString() + edt_username.getText().toString(), Toast.LENGTH_SHORT).show();
                String id= edt_update_name.getText().toString(); //dùng name de lam id
                String name = edt_update_name.getText().toString();

                String gender = btn_update_rdo_gender.getText().toString();
                String date = edt_update_date.getText().toString();
                String place = edt_update_place.getText().toString();
                String depID_spn = depID;
                String image = edt_update_image.getText().toString();
                String roles = Roles;
                Toast.makeText(UpdateActivity.this, gender, Toast.LENGTH_SHORT).show();
                memberDAO.update(UpdateActivity.this,id,name,gender.equals("Male")  ? "1" : "0", date, place, (depID_spn == "Design" ? "1" : "2"), image, (roles == "Member" ? "1" : "2"));
                overridePendingTransition(0, 0);
                // memberAdapter.notifyDataSetChanged();
            }


        });
    }


    private void initModel() {
        edt_update_name = (EditText) findViewById(R.id.edt_update__username);
        edt_update_date = (EditText) findViewById(R.id.edt_update__date);
        edt_update_place = (EditText) findViewById(R.id.edt_update__place);
        edt_update_image = (EditText) findViewById(R.id.edt_update__image);
        img_view_update = (ImageView) findViewById(R.id.img_update__avatar);
        spn_update_roles = (Spinner) findViewById(R.id.spn_update_roles);
        spn_update_dep = (Spinner) findViewById(R.id.spn_update_dep);
        rdo_update_gender = (RadioGroup) findViewById(R.id.rdo_update_gender);
        rdo_update_male = (RadioButton) findViewById(R.id.radioButton_update_male);
        rdo_update_female = (RadioButton) findViewById(R.id.radioButton_update_female);
        txt_change_password = (TextView) findViewById(R.id.txt_update_change_password);
        btnDate=(Button) findViewById(R.id.btn_update_choose_date);
        btnUpdate=(Button) findViewById(R.id.btn_update_update);

    }
}
