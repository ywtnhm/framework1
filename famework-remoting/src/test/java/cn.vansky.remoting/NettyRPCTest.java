/*
 * Copyright (C) 2016 hyssop, Inc. All Rights Reserved.
 */

/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

/**
 * $Id: NettyRPCTest.java 1831 2013-05-16 01:39:51Z hyssop $
 */
package cn.vansky.remoting;


import cn.vansky.remoting.nettyway.CommandCustomHeader;
import cn.vansky.remoting.nettyway.InvokeCallback;
import cn.vansky.remoting.nettyway.RPCHook;
import cn.vansky.remoting.nettyway.RemotingClient;
import cn.vansky.remoting.nettyway.RemotingServer;
import cn.vansky.remoting.nettyway.exception.RemotingCommandException;
import cn.vansky.remoting.nettyway.exception.RemotingConnectException;
import cn.vansky.remoting.nettyway.exception.RemotingSendRequestException;
import cn.vansky.remoting.nettyway.exception.RemotingTimeoutException;
import cn.vansky.remoting.nettyway.exception.RemotingTooMuchRequestException;
import cn.vansky.remoting.nettyway.netty.NettyClientConfig;
import cn.vansky.remoting.nettyway.netty.NettyRemotingClient;
import cn.vansky.remoting.nettyway.netty.NettyRemotingServer;
import cn.vansky.remoting.nettyway.netty.NettyRequestProcessor;
import cn.vansky.remoting.nettyway.netty.NettyServerConfig;
import cn.vansky.remoting.nettyway.netty.ResponseFuture;
import cn.vansky.remoting.nettyway.protocol.RemotingCommand;
import io.netty.channel.ChannelHandlerContext;
import org.junit.Test;

import java.util.concurrent.Executors;

import static org.junit.Assert.assertTrue;


/**
 * @author hyssop
 */
public class NettyRPCTest {
    @Test
    public void test_RPC_Sync() throws InterruptedException, RemotingConnectException,
            RemotingSendRequestException, RemotingTimeoutException {
        RemotingServer server = createRemotingServer();
        RemotingClient client = createRemotingClient();

        for (int i = 0; i < 100; i++) {
            TestRequestHeader requestHeader = new TestRequestHeader();
            requestHeader.setCount(i);
            requestHeader.setMessageTitle("HelloMessageTitle");
            RemotingCommand request = RemotingCommand.createRequestCommand(0, requestHeader);
            RemotingCommand response = client.invokeSync("localhost:8888", request, 1000 * 3000);
            System.out.println("invoke result = " + response);
            assertTrue(response != null);
        }

        client.shutdown();
        server.shutdown();
        System.out.println("-----------------------------------------------------------------");
    }

    public static RemotingServer createRemotingServer() throws InterruptedException {
        NettyServerConfig config = new NettyServerConfig();
        RemotingServer remotingServer = new NettyRemotingServer(config);
        remotingServer.registerProcessor(0, new NettyRequestProcessor() {
            private int i = 0;


            public RemotingCommand processRequest(ChannelHandlerContext ctx, RemotingCommand request) {
                System.out.println("processRequest=" + request + " " + (i++));
                request.setRemark("hello, I am respponse " + ctx.channel().remoteAddress());
                return request;
            }

            public boolean rejectRequest() {
                return false;
            }
        }, Executors.newCachedThreadPool());
        remotingServer.start();
        return remotingServer;
    }

    public static RemotingClient createRemotingClient() {
        NettyClientConfig config = new NettyClientConfig();
        RemotingClient client = new NettyRemotingClient(config);
        client.registerRPCHook(new MyRPCHook());
        client.start();
        return client;
    }

    @Test
    public void test_RPC_Oneway() throws InterruptedException, RemotingConnectException,
            RemotingTimeoutException, RemotingTooMuchRequestException, RemotingSendRequestException {
        RemotingServer server = createRemotingServer();
        RemotingClient client = createRemotingClient();


        for (int i = 0; i < 100; i++) {
            RemotingCommand request = RemotingCommand.createRequestCommand(0, null);
            request.setRemark(String.valueOf(i));
            client.invokeOneway("localhost:8888", request, 1000 * 3);
        }

        client.shutdown();
        server.shutdown();
        System.out.println("-----------------------------------------------------------------");
    }


    @Test
    public void test_RPC_Async() throws InterruptedException, RemotingConnectException,
            RemotingTimeoutException, RemotingTooMuchRequestException, RemotingSendRequestException {
        RemotingServer server = createRemotingServer();
        RemotingClient client = createRemotingClient();

        for (int i = 0; i < 100; i++) {
            RemotingCommand request = RemotingCommand.createRequestCommand(0, null);
            request.setRemark(String.valueOf(i));
            client.invokeAsync("localhost:8888", request, 1000 * 3, new InvokeCallback() {

                public void operationComplete(ResponseFuture responseFuture) {
                    System.out.println(responseFuture.getResponseCommand());
                }
            });
        }

        Thread.sleep(1000 * 3);

        client.shutdown();
        server.shutdown();
        System.out.println("-----------------------------------------------------------------");
    }


    @Test
    public void test_server_call_client() throws InterruptedException, RemotingConnectException,
            RemotingSendRequestException, RemotingTimeoutException {
        final RemotingServer server = createRemotingServer();
        final RemotingClient client = createRemotingClient();

        server.registerProcessor(0, new NettyRequestProcessor() {

            public RemotingCommand processRequest(ChannelHandlerContext ctx, RemotingCommand request) {
                try {
                    return server.invokeSync(ctx.channel(), request, 1000 * 10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (RemotingSendRequestException e) {
                    e.printStackTrace();
                } catch (RemotingTimeoutException e) {
                    e.printStackTrace();
                }

                return null;
            }

            public boolean rejectRequest() {
                return false;
            }
        }, Executors.newCachedThreadPool());

        client.registerProcessor(0, new NettyRequestProcessor() {
            public RemotingCommand processRequest(ChannelHandlerContext ctx, RemotingCommand request) {
                System.out.println("client receive server request = " + request);
                request.setRemark("client remark");
                return request;
            }
            public boolean rejectRequest() {
                return false;
            }
        }, Executors.newCachedThreadPool());

        for (int i = 0; i < 3; i++) {
            RemotingCommand request = RemotingCommand.createRequestCommand(0, null);
            RemotingCommand response = client.invokeSync("localhost:8888", request, 1000 * 3);
            System.out.println("invoke result = " + response);
            assertTrue(response != null);
        }

        client.shutdown();
        server.shutdown();
        System.out.println("-----------------------------------------------------------------");
    }

}

class MyRPCHook implements RPCHook {

    public void doBeforeRequest(String remoteAddr, RemotingCommand request) {
        System.out.println("doBeforeRequest remoteAddr:"+remoteAddr+"request:"+request);
    }

    public void doAfterResponse(String remoteAddr, RemotingCommand request, RemotingCommand response) {
        System.out.println("doAfterResponse remoteAddr:"+remoteAddr+"request:"+request+" response"+response);
    }
}
class TestRequestHeader implements CommandCustomHeader {
    private Integer count;

    private String messageTitle;


    public void checkFields() throws RemotingCommandException {
    }


    public Integer getCount() {
        return count;
    }


    public void setCount(Integer count) {
        this.count = count;
    }


    public String getMessageTitle() {
        return messageTitle;
    }


    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }
}


class TestResponseHeader implements CommandCustomHeader {
    private Integer count;

    private String messageTitle;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void checkFields() throws RemotingCommandException {

    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }


}
