package com.arqivame.user.infrastructure.messaging.producer;

@FunctionalInterface
public interface MessageProducer<T> {

    void produce(T payload);

}
