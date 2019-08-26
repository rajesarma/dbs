package com.dbs.customerservice.common.error;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 * GlobalErrorHandler
 *
 * Component class for Global Error
 *
 * @author lakshmirajeswararao.p
 * */

@RestController
public class GlobalErrorHandler implements ErrorController {

    /**
     * This method handles http errors in the project returns a Response Entity of the message
     *
     * @param request HttpServletRequest
     * @return ResponseEntity
     */

    @RequestMapping("/error")
    public ResponseEntity<String> handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.valueOf(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return new ResponseEntity<>("BAD REQUEST", HttpStatus.BAD_REQUEST);
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return new ResponseEntity<>("INTERNAL SERVER ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                return new ResponseEntity<>("FORBIDDEN", HttpStatus.FORBIDDEN);
            }
        }
        return new ResponseEntity<>("INTERNAL SERVER ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public String getErrorPath()
    {
        return "/error";
    }
}