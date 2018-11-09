package com.imooc.exception;

/**
 * @author Gatsby.Luo
 * @date 2018-10-24
 */

public class UserNotExistException extends RuntimeException {

    private String id;

    public UserNotExistException(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
