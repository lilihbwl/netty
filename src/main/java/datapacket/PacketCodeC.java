package datapacket;

import datapacket.information.Commend;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.util.HashMap;

public class PacketCodeC {
    public static final int MAGIC_NUMBER = 0x12345678;
    private static final HashMap<Byte,Class> map= new HashMap();
    private static final HashMap<Byte,Serializer> serMap= new HashMap();
    public static PacketCodeC INSTANCE=new PacketCodeC();
   private PacketCodeC(){
        map.put((byte)1,LoginRequestPacket.class);
        map.put((byte)2,LoginResponsePacket.class);
       map.put((byte)3,MessageRequestPacket.class);
       map.put((byte)4,MessageResponsePacket.class);
       map.put(Commend.HEARTBEAT_REQUEST,HeartBeatRequestPacket.class);
       map.put(Commend.HEARTBEAT_RESPONSE,HeartBeatResponsePocket.class);
        serMap.put((byte)1,Serializer.DEFAULT);
    }

    public ByteBuf encode(ByteBuf byteBuf,AbstractPacket packet) {
        // 1. 创建 ByteBuf 对象

        // 2. 序列化 Java 对象
        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        // 3. 实际编码过程
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        //System.out.println("此次编码为"+packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

        return byteBuf;
    }
    public AbstractPacket decode(ByteBuf byteBuf) {
        // 跳过 magic number
        byteBuf.skipBytes(4);

        // 跳过版本号
        byteBuf.skipBytes(1);

        // 序列化算法标识
        byte serializeAlgorithm = byteBuf.readByte();

        // 指令
        byte command = byteBuf.readByte();

        // 数据包长度
        int length = byteBuf.readInt();

        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        Class<? extends AbstractPacket> requestType = getRequestType(command);
        Serializer serializer = getSerializer(serializeAlgorithm);

        if (requestType != null && serializer != null) {
            return serializer.deserialize(requestType, bytes);
        }

        return null;
    }

    private Serializer getSerializer(byte serializeAlgorithm) {
        return serMap.getOrDefault(serializeAlgorithm,null);
    }

    private Class<? extends AbstractPacket> getRequestType(byte command) {
        return map.getOrDefault(command,null);
    }

   /* public static void main(String[] args) {
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUsername("xinghe");
        loginRequestPacket.setPassword("123654");
        PacketCodeC codeC = new PacketCodeC();
        ByteBuf a=codeC.encode(loginRequestPacket);
        LoginRequestPacket b= (LoginRequestPacket) codeC.decode(a);
        System.out.println(b.getPassword());
    }*/
}
