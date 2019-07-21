package handler.myServerHandler;

import datapacket.HeartBeatRequestPacket;
import datapacket.HeartBeatResponsePocket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class HeartBeatRequestHandler extends SimpleChannelInboundHandler<HeartBeatRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HeartBeatRequestPacket heartBeatRequestPacket) throws Exception {
        ctx.channel().writeAndFlush(HeartBeatResponsePocket.INSTANCE);
    }
}
