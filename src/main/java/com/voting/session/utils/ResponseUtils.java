package com.voting.session.utils;

import com.voting.session.view.RestResponseView;
import org.springframework.http.HttpStatus;

public class ResponseUtils {

    public static RestResponseView createRestResponseView(Object response, HttpStatus httpStatus) {

        return new RestResponseView(response, httpStatus);

    }


}
