package com.sweeney.grpchelloworld.service;

import com.sweeney.grpchelloworld.GreeterGrpc;
import com.sweeney.grpchelloworld.HelloReply;
import com.sweeney.grpchelloworld.HelloRequest;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
public class GreeterService extends GreeterGrpc.GreeterImplBase {

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        responseObserver.onNext(HelloReply.newBuilder().setMessage("Hello " + request.getName()).build());
        responseObserver.onCompleted();
    }
}
