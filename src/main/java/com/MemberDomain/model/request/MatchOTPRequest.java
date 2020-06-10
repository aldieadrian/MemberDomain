package com.MemberDomain.model.request;

public class MatchOTPRequest {
    private String otp;

    public MatchOTPRequest() {
    }

    public MatchOTPRequest(String otp) {
        super();
        this.otp = otp;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("OTPr = ").append(otp).append(" - ");

        return sb.toString();
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getOtp() {
        return otp;
    }
}