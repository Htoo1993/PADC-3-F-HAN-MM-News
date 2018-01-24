package xyz.htooaungnaing.news.events;

import xyz.htooaungnaing.news.data.vo.LoginUserVO;

/**
 * Created by htoo on 1/21/2018.
 */

public class RegisterEvent {
    private LoginUserVO registerUser;

    public RegisterEvent(LoginUserVO registerUser) {
        this.registerUser = registerUser;
    }

    public LoginUserVO getRegisterUser() {
        return registerUser;
    }
}
