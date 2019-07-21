package datapacket;

import datapacket.information.Commend;

public class HeartBeatRequestPacket extends AbstractPacket{
    public static HeartBeatRequestPacket INSTANCE=new HeartBeatRequestPacket();

    private HeartBeatRequestPacket(){}

    @Override
    public Byte getCommand() {
        return Commend.HEARTBEAT_REQUEST;
    }
}
