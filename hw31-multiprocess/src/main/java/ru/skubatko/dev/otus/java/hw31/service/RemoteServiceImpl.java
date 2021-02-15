package ru.skubatko.dev.otus.java.hw31.service;


import static ru.skubatko.dev.otus.java.hw31.util.Utils.sleep;

import ru.skubatko.dev.otus.java.hw31.generated.InMessage;
import ru.skubatko.dev.otus.java.hw31.generated.OutMessage;
import ru.skubatko.dev.otus.java.hw31.generated.RemoteServiceGrpc;

import io.grpc.stub.StreamObserver;

import java.util.stream.IntStream;

public class RemoteServiceImpl extends RemoteServiceGrpc.RemoteServiceImplBase {

    @Override
    public void generate(InMessage request, StreamObserver<OutMessage> responseObserver) {
        IntStream.rangeClosed(request.getFirstValue() + 1, request.getLastValue())
                .forEach(generatedValue -> send(responseObserver, generatedValue));
        responseObserver.onCompleted();
    }

    private void send(StreamObserver<OutMessage> responseObserver, int generatedValue) {
        responseObserver.onNext(OutMessage.newBuilder().setGeneratedValue(generatedValue).build());
        sleep(2);
    }
}
