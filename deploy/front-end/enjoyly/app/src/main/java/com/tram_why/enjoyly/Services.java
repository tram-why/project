package com.tram_why.enjoyly;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Valentin on 25.03.2017.
 */

public class Services {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    Request service = retrofit.create(Request.class);
    public void SignOn(String email, String password){
        Call<User> call = service.signOn(email, password);
        try {
            call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }

    }
    public void SignUp(String firstName, String lastName, String email, String password){
        Call<User> call = service.signUp(firstName, lastName, email, password);
        try {
            call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }
}
