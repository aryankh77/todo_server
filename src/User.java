import java.util.Objects;

class User {
    private String username;
    private String password;
    private String name;
    private String imail;
    private String familyName;
    private boolean isLogin;


    public boolean getIsLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImail() {
        return imail;
    }

    public void setImail(String imail) {
        this.imail = imail;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public boolean isLogin() {
        return isLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(imail, user.imail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, imail);
    }
}