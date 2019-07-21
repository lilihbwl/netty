package datapacket;

import datapacket.information.Commend;

public class MessageRequestPacket extends AbstractPacket {
    private String message;
    private String ToUserId;
    public MessageRequestPacket(){}

    public MessageRequestPacket(String toUserId, String message) {
        ToUserId=toUserId;
        this.message=message;
    }

    public String getToUserId() {
        return ToUserId;
    }

    public void setToUserId(String toUserId) {
        ToUserId = toUserId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return Commend.MESSAGE_REQUEST;
    }
}
