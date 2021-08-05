package com.example.tutorial05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText Firstname,Lastname,Username,Password;
    Switch branch;
    Spinner city;
    CheckBox agree;
    Button Register;
    RadioGroup rdb_group;
    RadioButton rdb_select;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setTitle("Registration Form");

        Firstname = findViewById(R.id.Fname);
        Lastname  = findViewById(R.id.Lname);
        Username  = findViewById(R.id.email);
        Password  = findViewById(R.id.password);
        branch       = findViewById(R.id.branch);
        rdb_group    = findViewById(R.id.rdb_group);
        city         = findViewById(R.id.city);
        agree        = findViewById(R.id.agree);
        Register  = findViewById(R.id.Register);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String ValFirstname,ValLastname,ValUsername,ValPassword,Valbranch,ValGender,Valcity,Valagree;

                ValFirstname = Firstname.getText().toString();
                ValLastname  = Lastname.getText().toString();
                ValUsername  = Username.getText().toString();
                ValPassword  = Password.getText().toString();
                Valbranch    = branch.getText().toString();
                int id       = rdb_group.getCheckedRadioButtonId();
                rdb_select   = findViewById(id);
                ValGender    = rdb_select.getText().toString();
                Valcity      = city.getSelectedItem().toString();


                if (TextUtils.isEmpty(ValFirstname)) {
                    Firstname.setError("Please Enter FirstName");
                    return;
                }

                if (TextUtils.isEmpty(ValLastname)) {
                    Lastname.setError("Please Enter LastName");
                    return;
                }

                if (TextUtils.isEmpty(ValUsername)) {
                    Username.setError("Please Enter Email Address");
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(ValUsername).matches()){
                    Toast.makeText(MainActivity.this,"Email address format is not valid", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(ValPassword)){
                    Password.setError("Password is Required.");
                    return;
                }

                if (ValPassword.length() <6){
                    Password.setError("Password Must be >= 6 Characters");
                    return;
                }

                if(Valcity.equals("Select City"))
                {
                    Toast.makeText(MainActivity.this, "Please Select City", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(branch.isChecked()) {
                    Valbranch = "IT";
                }else{
                    Valbranch = "CE";
                }

                if(agree.isChecked()) {
                    Valagree = "Active";
                }else{
                    Valagree = "Inactive";
                }

                Intent intent=new Intent(MainActivity.this,OutputActivity.class);
                intent.putExtra("first_name",ValFirstname);
                intent.putExtra("last_name",ValLastname);
                intent.putExtra("val_username",ValUsername);
                intent.putExtra("val_password",ValPassword);
                intent.putExtra("val_branch",Valbranch);
                intent.putExtra("val_gender",ValGender);
                intent.putExtra("val_city",Valcity);
                intent.putExtra("val_agree",Valagree);
                startActivity(intent);
            }
        });
    }
}