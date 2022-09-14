package de.doaschdn.grpc.client;

import de.doaschdn.grpc.HelloRequest;
import de.doaschdn.grpc.HelloServiceGrpc;
import io.grpc.ManagedChannelBuilder;

public class Client {
  public static void main(String[] args) {
    if (args.length != 2) {
      System.out.println("Invalid number of arguments. Require exactly firstname and lastname.");
      System.exit(-1);
      return;
    }

    var channel = ManagedChannelBuilder.forAddress("localhost", 8080)
            .usePlaintext()
            .build();

    var stub
            = HelloServiceGrpc.newBlockingStub(channel);

    var helloResponse = stub.hello(HelloRequest.newBuilder()
            .setFirstName(args[0])
            .setLastName(args[1])
            .build());

    System.out.println("Response received from server:\n" + helloResponse);

    channel.shutdown();
  }
}
