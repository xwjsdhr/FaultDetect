package info.faultdetect.com.faultdetect.bean;

import info.faultdetect.com.faultdetect.MyApplication;

/**
 * Created by nicai on 2016/7/20.
 * email：930324291@qq.com
 */
public class Req_UpdatePwd extends Req_BaseBean{
    private String userid = MyApplication.getApplication().getUserId();
    private String tel;
    private String jym;
    private String newPw;

    public Req_UpdatePwd(String tel, String newPw, String jym) {
        this.tel = tel;
        this.jym = jym;
        this.newPw = newPw;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getJym() {
        return jym;
    }

    public void setJym(String jym) {
        this.jym = jym;
    }

    public String getNewPw() {
        return newPw;
    }

    public void setNewPw(String newPw) {
        this.newPw = newPw;
    }
}
