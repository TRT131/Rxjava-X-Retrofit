package com.example.trt.rxjavademo;

/**
 * Created by Trt on 2019/3/3.
 */

public class JsonBean {
    String cmd;
    int fund_key;
    String Sex;

    @Override
    public String toString() {
        return "JsonBean{" +
                "cmd='" + cmd + '\'' +
                ", fundKey=" + fund_key +
                ", sex='" + Sex + '\'' +
                '}';
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public int getFundKey() {
        return fund_key;
    }

    public void setFundKey(int fundKey) {
        this.fund_key = fundKey;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        this.Sex = sex;
    }
}
