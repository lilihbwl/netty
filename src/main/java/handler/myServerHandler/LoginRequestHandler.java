package handler.myServerHandler;

import Util.LoginUtil;
import Util.SessionUtil;
import datapacket.LoginRequestPacket;
import datapacket.LoginResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import session.Session;

import java.util.UUID;

public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx,LoginRequestPacket loginRequestPacket) throws Exception {
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        // 登录校验
        if (valid(loginRequestPacket)) {
            loginResponsePacket.setSuccess(true);
            LoginUtil.markAsLogin(ctx.channel());
            String userId = UUID.randomUUID().toString();
            loginResponsePacket.setUserId(userId);
            //将sessionId和信道绑定起来，并且在channel中存储session信息,在之后可以得到信道的发送端消息
            SessionUtil.bindSession(new Session(userId, loginRequestPacket.getUserName()), ctx.channel());
            System.out.println("用户"+loginRequestPacket.getUserName()+"上线了->"+userId);
        } else {
            loginResponsePacket.setReason("账号密码校验失败");
            loginResponsePacket.setSuccess(false);
        }

        ctx.channel().writeAndFlush(loginResponsePacket);
    }
    private boolean valid(LoginRequestPacket loginRequestPacket) {
        if(loginRequestPacket.getPassword().equals("123654"))
            return true;
        return false;
    }
    public void channelInactive(ChannelHandlerContext ctx) {
        SessionUtil.unBindSession(ctx.channel());
    }
}
