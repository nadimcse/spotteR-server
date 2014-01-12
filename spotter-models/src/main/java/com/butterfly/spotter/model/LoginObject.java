package com.butterfly.spotter.model;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

/**
 * @author : Nadim
 * @since : 12/19/13
 */
public class LoginObject {
    private String gcmKey;
    private String callerId;
    private String password;

    public LoginObject(String gcmKey, String callerId, String password) {
        this.gcmKey = gcmKey;
        this.callerId = callerId;
        this.password = password;
    }

    public String getCallerId() {
        return callerId;
    }

    public void setCallerId(String callerId) {
        this.callerId = callerId;
    }

    public String getGcmKey() {
        return gcmKey;
    }

    public void setGcmKey(String gcmKey) {
        this.gcmKey = gcmKey;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String generateAuthKey(long dateLong) {
        String signature = gcmKey + "|"  + dateLong;
        return Hashing.md5().hashString(signature, Charsets.UTF_8).toString();
    }

}
