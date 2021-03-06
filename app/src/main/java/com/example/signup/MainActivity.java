package com.example.signup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.signup.RoomDataBase.Register;
import com.example.signup.RoomDataBase.RegisterRoomDataBase;
import com.example.signup.RoomDataBase.RegisterViewModel;

import java.util.List;

/* SignUp Activity */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RegisterViewModel registerViewModel;
    private Register register;
    private List<Register> registers;
    private volatile RegisterRoomDataBase registerRoomDataBase;


    private EditText name, address, email, mobile, password, location; // Create Edit Text


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Initialize the Edit Text */

        name = findViewById(R.id.ed);
        address = findViewById(R.id.ed1);
        email = findViewById(R.id.ed2);
        mobile=findViewById(R.id.ed3);
        password=findViewById(R.id.ed4);
        location = findViewById(R.id.ed5);

        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        registerViewModel.getAllUser().observe(this, new Observer<List<Register>>() {
            @Override
            public void onChanged(List<Register> register) {
                registers = register;
            }
        });


        findViewById(R.id.button).setOnClickListener(this); //Click listener for the button

    }

    @Override
    public void onClick(View view) {
        String s_name = name.getText().toString();
        String s_address = address.getText().toString();
        String s_email = email.getText().toString();
        String s_mobile = mobile.getText().toString();
        String s_password = password.getText().toString();
        String s_location = location.getText().toString();
        Boolean userExist = Boolean.FALSE;
        Register newRegister = new Register();
        newRegister.setName(s_name);
        newRegister.setAddress(s_address);
        newRegister.setEmail(s_email);
        newRegister.setMobile(s_mobile);
        newRegister.setPassword(s_password);
        newRegister.setLocation(s_location);

        if(!registers.isEmpty())
            for(Register register : registers)
            {
                if(register.getEmail().equals(s_email)){
                    userExist = Boolean.TRUE;
                    break;
                }
            }

        /* Validation for all the EditText*/

        if(s_name.isEmpty()){
            name.setError("Name is Required");
            name.requestFocus();
            return;
        }
        if(s_address.isEmpty()){
            address.setError("Address is Required");
            address.requestFocus();
            return;
        }
        if(s_email.isEmpty()){
            email.setError("Email Id is Required");
            email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(s_email).matches()){
            email.setError("Enter a Valid E-mail Address");
            email.requestFocus();
            return;
        }
        if(s_mobile.isEmpty()){
            mobile.setError("Mobile Number is Required");
            mobile.requestFocus();
            return;
        }
        if(s_password.isEmpty()){
            password.setError("Password is Required");
            password.requestFocus();
            return;
        }
        if(s_password.length()<8){
            password.setError("Good Password must be atleast 8 Characters");
            password.requestFocus();
            return;
        }
        if(s_location.isEmpty()){
            location.setError("Location is Required");
            location.requestFocus();
            return;
        }

        else if(userExist)
        {

            Toast.makeText(getApplicationContext(),"User Already Exists",Toast.LENGTH_SHORT).show();
            email.setError("This Email Already Exists");
            email.setText("");
        }
        else {
            registerViewModel.insert(newRegister);
            email.setText("");
            address.setText("");
            name.setText("");
            password.setText("");
            mobile.setText("");
            location.setText("");
            Toast.makeText(getApplicationContext(),"Registered Successfully",Toast.LENGTH_SHORT).show();
        }

        }
}