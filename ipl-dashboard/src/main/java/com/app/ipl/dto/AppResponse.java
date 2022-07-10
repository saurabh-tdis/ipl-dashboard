package com.app.ipl.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * @Author saurabh vaish
 * @Date 08-07-2022
 */

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AppResponse<T> implements Serializable {

    private HttpStatus status;

    private String message;

    private T payload;


}
