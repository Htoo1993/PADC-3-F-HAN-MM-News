package xyz.htooaungnaing.news.network.responses;

import com.google.gson.annotations.SerializedName;

import xyz.htooaungnaing.news.data.vo.LoginUserVO;

/**
 * Created by htoo on 1/21/2018.
 */

public class RegisterResponse {

    private int code;
    private String message;
    @SerializedName("login_user")
    private LoginUserVO registerUser;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public LoginUserVO getRegisterUser() {
        return registerUser;
    }
}
