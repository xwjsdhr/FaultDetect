package info.faultdetect.com.faultdetect.bean;

public class Req_Regist {
    private String name;
    private String pwd;
    private String nickName;
    private String account;
    private String tel;
    private String email;
    private String organizationId;

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public Req_Regist(String name, String nickName, String account, String pwd, String tel, String email, String organizationId) {
        this.name = name;
        this.nickName = nickName;
        this.account = account;
        this.pwd = pwd;
        this.tel = tel;
        this.email = email;
        this.organizationId = organizationId;
    }

    public Req_Regist(){}
}
