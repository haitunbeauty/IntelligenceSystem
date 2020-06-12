package com.manage.intelligence.bean.login;

public class LoginParam {


    /**
     * securitycode : 09E4247EFB290F801B848F85630BCF6E
     * data : {"ACCOUNT":"APPUSER01","PASSWORD":"123456"}
     */

    private String securitycode;
    private DataBean data;

    public String getSecuritycode() {
        return securitycode;
    }

    public void setSecuritycode(String securitycode) {
        this.securitycode = securitycode;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * ACCOUNT : APPUSER01
         * PASSWORD : 123456
         */

        private String ACCOUNT;
        private String PASSWORD;

        public String getACCOUNT() {
            return ACCOUNT;
        }

        public void setACCOUNT(String ACCOUNT) {
            this.ACCOUNT = ACCOUNT;
        }

        public String getPASSWORD() {
            return PASSWORD;
        }

        public void setPASSWORD(String PASSWORD) {
            this.PASSWORD = PASSWORD;
        }
    }
}
