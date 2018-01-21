package xyz.htooaungnaing.news.events;

import xyz.htooaungnaing.news.data.vo.LoginUserVO;

/**
 * Created by htoo on 1/21/2018.
 */

public class SuccessLoginEvent {

    private LoginUserVO loginUser;

    public SuccessLoginEvent(LoginUserVO loginUser) {
        this.loginUser = loginUser;
    }

    public LoginUserVO getLoginUser() {
        return loginUser;
    }
}
