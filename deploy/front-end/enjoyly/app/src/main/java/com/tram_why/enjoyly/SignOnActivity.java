package com.tram_why.enjoyly;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Response;

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
        bSignOn = (Button) findViewById(R.id.bSignOn);
        tSignUpLink = (TextView) findViewById(R.id.tSignUpLink);


        bSignOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = etEmail.getText().toString();
                password = etPassword.getText().toString();
                Request request = new Request();
                request.execute();
                Pattern pattern = Pattern.compile("message=.*,");
                try {
                    Matcher matcher = pattern.matcher(request.get());
                    while (matcher.find()){
                        int start = matcher.start()+8;
                        int end = matcher.end()-1;
                        String match = request.get().substring(start, end);
                        Toast.makeText(getApplicationContext(), match, Toast.LENGTH_LONG).show();
                        if (match.equals("OK")){
                            Intent mainIntent = new Intent(SignOnActivity.this, MainActivity.class);
                            SignOnActivity.this.startActivity(mainIntent);
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
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
    class Request extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            Services services = new Services();
            Response response = services.SignOn(email, password);
            System.out.println(response.toString());
            return response.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            return ;
        }
    }
}
