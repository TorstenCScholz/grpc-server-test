package de.doaschdn.grpc.server;

import de.doaschdn.grpc.HelloRequest;
import de.doaschdn.grpc.HelloResponse;
import de.doaschdn.grpc.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;

public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {

  @Override
  public void hello(
          HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
    System.out.println("Request received from client:\n" + request);

    var greeting = new StringBuilder().append("Hello, ")
            .append(request.getFirstName())
            .append(" ")
            .append(request.getLastName())
            .append(" ")
            .append(request.getAge())
            .toString();

    var response = HelloResponse.newBuilder()
            .setGreeting(greeting)
            .build();

    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }
}
