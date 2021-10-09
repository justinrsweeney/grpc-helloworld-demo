package com.sweeney.grpchelloworld.service;

import com.sweeney.grpchelloworld.GreeterGrpc;
import com.sweeney.grpchelloworld.HelloReply;
import com.sweeney.grpchelloworld.HelloRequest;
import com.sweeney.grpchelloworld.InvalidRequestException;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

import java.util.logging.Level;
import java.util.logging.Logger;

@GRpcService
public class GreeterService extends GreeterGrpc.GreeterImplBase {

    private static final Logger logger = Logger.getLogger(GreeterService.class.getName());

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        if (request.getName().isEmpty()) {
            logger.log(Level.SEVERE, "No name was provided for request");
            responseObserver.onError(new InvalidRequestException("A name must be provided for the greeting"));
        }

        logger.log(Level.INFO, "Greeting: " + request.getName());
        responseObserver.onNext(HelloReply.newBuilder().setMessage("Hello " + request.getName()).build());
        responseObserver.onCompleted();
    }
}
