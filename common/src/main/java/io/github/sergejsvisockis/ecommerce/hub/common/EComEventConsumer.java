package io.github.sergejsvisockis.ecommerce.hub.common;

public interface EComEventConsumer<R, T> {

    T consumer(R queues);

}
