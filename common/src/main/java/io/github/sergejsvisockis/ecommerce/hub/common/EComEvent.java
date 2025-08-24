package io.github.sergejsvisockis.ecommerce.hub.common;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalDateTime;

@JsonDeserialize(builder = EComEvent.Builder.class)
public final class EComEvent {

    private final EventType eventType;
    private final String eventKey;
    private final LocalDateTime eventDate;
    private final byte[] data;

    private EComEvent(Builder builder) {
        this.eventType = builder.eventType;
        this.eventKey = builder.eventKey;
        this.eventDate = builder.eventDate;
        this.data = builder.data;
    }

    public EventType getEventType() {
        return eventType;
    }

    public String getEventKey() {
        return eventKey;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public byte[] getData() {
        return data;
    }

    public static class Builder {
        private EventType eventType;
        private String eventKey;
        private LocalDateTime eventDate;
        private byte[] data;

        public Builder withEventType(EventType eventType) {
            this.eventType = eventType;
            return this;
        }

        public Builder withEventKey(String eventKey) {
            this.eventKey = eventKey;
            return this;
        }

        public Builder withEventDate(LocalDateTime eventDate) {
            this.eventDate = eventDate;
            return this;
        }

        public Builder withData(byte[] data) {
            this.data = data;
            return this;
        }

        public EComEvent build() {
            return new EComEvent(this);
        }
    }
}
