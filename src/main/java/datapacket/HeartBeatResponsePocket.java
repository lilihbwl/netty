package datapacket;

import datapacket.information.Commend;
import org.omg.CORBA.PRIVATE_MEMBER;

public class HeartBeatResponsePocket extends AbstractPacket {
    public static HeartBeatResponsePocket INSTANCE=new HeartBeatResponsePocket();
    private HeartBeatResponsePocket(){

    }

    @Override
    public Byte getCommand() {
        return Commend.HEARTBEAT_RESPONSE;
    }
}
