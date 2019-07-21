package datapacket.information;

public interface Commend {
    Byte LOGIN_REQUEST = 1;

    Byte LOGIN_RESPONSE = 2;

    Byte MESSAGE_REQUEST = 3;

    Byte MESSAGE_RESPONSE = 4;
    Byte HEARTBEAT_REQUEST = 10;
    Byte HEARTBEAT_RESPONSE = 9;
}
