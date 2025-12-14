package io.github.sergejsvisockis.ecommerce.hub.store.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SettlementJob {


    @Scheduled(cron = "0 */5 * * * *")
    public void sendSettlementData() {

        // TODO: here has to be a settlement job.
        //  1. retrieve latest order information
        //  2. transform into the SettlementAggregate
        //  3. Send it

    }

}
