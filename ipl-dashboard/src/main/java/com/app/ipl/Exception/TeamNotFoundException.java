package com.app.ipl.Exception;

/**
 * @Author saurabh vaish
 * @Date 05-07-2022
 */

public class TeamNotFoundException extends RuntimeException {

    public TeamNotFoundException(String ex){
        super(ex);
    }
}
