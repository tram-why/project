package com.tram_why.enjoyly;

import com.google.gson.annotations.Expose;

/**
 * Created by Valentin on 25.03.2017.
 */

public class User {
    @Expose
    private String email;
    @Expose
    private String password;
    @Expose
    private String firstName;
    @Expose
    private String lastName;
}
