package com.daily.rxjava2;

/**
 * Created by Administrator on 2018/6/5.
 */

public class ResponseEntity {


    /**
     * error_code : 10001
     * reason : 错误的请求KEY
     */

    private int error_code;
    private String reason;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "ResponseEntity{" +
                "error_code=" + error_code +
                ", reason='" + reason + '\'' +
                '}';
    }
}
