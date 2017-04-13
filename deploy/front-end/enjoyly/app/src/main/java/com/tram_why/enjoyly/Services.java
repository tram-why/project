package com.tram_why.enjoyly;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Valentin on 25.03.2017.
 */

public class Services {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://enjoyly.pythonanywhere.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    Request service = retrofit.create(Request.class);
    public Response SignOn(String email, String password){
        Call<User> call = service.signOn(email, password);
        try {
            Response response = call.execute();
           // call.execute();
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }

        return null;
    }
    public Response SignUp(String firstName, String lastName, String email, String password){
        Call<User> call = service.signUp(firstName, lastName, email, password);
        try {
            Response response = call.execute();
            // call.execute();
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return null;
    }
}
