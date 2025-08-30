package io.github.sergejsvisockis.ecommerce.hub.settlement.consumer;

import io.github.sergejsvisockis.ecommerce.hub.common.order.dto.DebitCard;
import io.github.sergejsvisockis.ecommerce.hub.common.order.dto.Payer;
import io.github.sergejsvisockis.ecommerce.hub.settlement.entity.SettlementDebitCardDetails;
import io.github.sergejsvisockis.ecommerce.hub.settlement.entity.SettlementPayerDetails;
import org.springframework.stereotype.Component;

@Component
public class SettlementPayerMapper {

    public SettlementPayerDetails mapPayerDetails(Payer payer) {
        DebitCard debitCardDetails = payer.getDebitCardDetails();
        return new SettlementPayerDetails.Builder()
                .withPayerFirstName(payer.getFirstName())
                .withPayerLastName(payer.getLastName())
                .withPayerAddress(payer.getAddress())
                .withPayerEmail(payer.getEmail())
                .withPayerPhone(payer.getPhone())
                .withDebitCardDetails(new SettlementDebitCardDetails.Builder()
                        .withCardHolderName(debitCardDetails.getCardHolderName())
                        .withCardNumber(debitCardDetails.getCardNumber())
                        .withCardExpiry(debitCardDetails.getExpiryDate())
                        .build())
                .build();
    }

}
