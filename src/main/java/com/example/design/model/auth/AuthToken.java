package com.example.design.model.auth;

/**
 * Created by lxh on 4/20/16.
 */
public class AuthToken {
    private String accountName;
    private String tokenContent;

    public AuthToken(String accountName, String tokenContent) {
        this.accountName = accountName;
        this.tokenContent = tokenContent;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getTokenContent() {
        return tokenContent;
    }

    public void setTokenContent(String tokenContent) {
        this.tokenContent = tokenContent;
    }
}
