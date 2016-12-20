package com.sage.common.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

@Component
@Qualifier("springProtocolInitializer")
public class StringProtocolInitializer extends ChannelInitializer<SocketChannel> {

	@Autowired
	StringDecoder stringDecoder;
	
	@Autowired 
	StringEncoder stringEncoder;
	
	@Autowired
	ServerHandler serverHandler;
	
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		pipeline.addLast("decoder", stringDecoder);
		pipeline.addLast("handler", serverHandler);
		pipeline.addLast("encoder", stringEncoder);
		
	}
	
	public StringDecoder getStringDecoder() {
		return stringDecoder;
	}
	
	public void setStringDecoder(StringDecoder stringDecoder) {
		this.stringDecoder = stringDecoder;
	}
	
	public StringEncoder getStringEncoder() {
		return stringEncoder;
	}
	
	public ServerHandler getServerHandler() {
		return serverHandler;
	}
	
	public void setServerHandler(ServerHandler serverHandler) {
		this.serverHandler = serverHandler;
	}

}
