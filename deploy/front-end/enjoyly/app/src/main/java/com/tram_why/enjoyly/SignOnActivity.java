package com.tram_why.enjoyly;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignOnActivity extends AppCompatActivity {

    EditText etEmail;
    EditText etPassword;
    Button bSignOn;
    TextView tSignUpLink;
    String email;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_on);


        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bSignOn = (Button) findViewById(R.id.bSignUp);
        tSignUpLink = (TextView) findViewById(R.id.tSignUpLink);


        bSignOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = etEmail.getText().toString();
                password = etPassword.getText().toString();
                Request request = new Request();
                request.execute();
            }
        });
        tSignUpLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUpIntent = new Intent(SignOnActivity.this, SignUpActivity.class);
                SignOnActivity.this.startActivity(signUpIntent);
            }
        });

    }
    class Request extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            Services services = new Services();
            services.SignOn(email, password);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }
    }
}
