package datapacket;

public class LoginRequestPacket extends AbstractPacket {
    private String userId;

    private String username;

    private String password;

    @Override
    public Byte getCommand() {
        return 1;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
