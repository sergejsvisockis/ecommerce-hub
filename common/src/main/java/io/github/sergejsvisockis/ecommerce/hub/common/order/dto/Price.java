package io.github.sergejsvisockis.ecommerce.hub.common.order.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers.BigDecimalDeserializer;

import java.math.BigDecimal;

public record Price(
        @JsonDeserialize(using = BigDecimalDeserializer.class) BigDecimal amount,
        Currency currency
) {

}
