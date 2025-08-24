package io.github.sergejsvisockis.ecommerce.hub.common;

public interface EComEventProducer<R, T> {

    T sendMessage(R event);
}
