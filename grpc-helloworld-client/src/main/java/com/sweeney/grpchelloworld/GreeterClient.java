package com.sweeney.grpchelloworld;

import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import org.apache.commons.cli.*;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GreeterClient {

    private final GreeterGrpc.GreeterBlockingStub blockingStub;
    private final Logger logger = Logger.getLogger(GreeterClient.class.getName());

    /** Construct client for accessing HelloWorld server using the existing channel. */
    public GreeterClient(Channel channel) {
        blockingStub = GreeterGrpc.newBlockingStub(channel);
    }

    public void greet(String name) {
        logger.info("Requesting greeting for " + name + " ...");
        HelloRequest request = HelloRequest.newBuilder().setName(name).build();
        HelloReply response;
        try {
            response = blockingStub.sayHello(request);
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC call to server failed: {0}", e.getStatus());
            return;
        }
        logger.info("Greeting: " + response.getMessage());
    }

    /**
     * Greet server. If provided, the first element of {@code args} is the name to use in the
     * greeting. The second argument is the target server.
     */
    public static void main(String[] args) throws Exception {
        Options options = new Options();
        options.addOption("n", true, "name");
        options.addOption("t", true, "target server, i.e. localhost:5000");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse( options, args);

        if (!cmd.hasOption('t') || !cmd.hasOption('n')) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp( "greeter", options );
            System.exit(1);
        }

        String name = cmd.getOptionValue('n');
        String target = cmd.getOptionValue('t');

        ManagedChannel channel = ManagedChannelBuilder.forTarget(target)
                // Disable TLS to avoid certs
                .usePlaintext()
                .build();
        try {
            GreeterClient client = new GreeterClient(channel);
            client.greet(name);
        } finally {
            channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
        }

        System.exit(0);
    }
}
