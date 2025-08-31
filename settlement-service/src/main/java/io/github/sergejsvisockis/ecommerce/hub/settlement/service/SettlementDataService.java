package io.github.sergejsvisockis.ecommerce.hub.settlement.service;

import io.github.sergejsvisockis.ecommerce.hub.common.order.dto.OrderRequest;
import io.github.sergejsvisockis.ecommerce.hub.common.order.dto.Payer;
import io.github.sergejsvisockis.ecommerce.hub.settlement.consumer.SettlementDataMapper;
import io.github.sergejsvisockis.ecommerce.hub.settlement.consumer.SettlementPayerMapper;
import io.github.sergejsvisockis.ecommerce.hub.settlement.entity.SettlementData;
import io.github.sergejsvisockis.ecommerce.hub.settlement.entity.SettlementPayerDetails;
import io.github.sergejsvisockis.ecommerce.hub.settlement.repository.CacheableSettlementPayerRepository;
import io.github.sergejsvisockis.ecommerce.hub.settlement.repository.SettlementDataRepository;
import org.springframework.stereotype.Service;

@Service
public class SettlementDataService {

    private final SettlementDataRepository settlementDataRepository;
    private final CacheableSettlementPayerRepository settlementPayerRepository;
    private final SettlementDataMapper settlementDataMapper;
    private final SettlementPayerMapper settlementPayerMapper;

    public SettlementDataService(SettlementDataRepository settlementDataRepository,
                                 CacheableSettlementPayerRepository settlementPayerRepository,
                                 SettlementDataMapper settlementDataMapper,
                                 SettlementPayerMapper settlementPayerMapper) {
        this.settlementDataRepository = settlementDataRepository;
        this.settlementPayerRepository = settlementPayerRepository;
        this.settlementDataMapper = settlementDataMapper;
        this.settlementPayerMapper = settlementPayerMapper;
    }

    public void saveSettlementData(OrderRequest request) {
        Payer payer = request.getPayer();

        SettlementData settlementData = settlementDataMapper.mapToSettlementData(request);

        SettlementPayerDetails settlementPayer =
                settlementPayerRepository.findBySettlementPayerDetails(payer.getEmail(), payer.getPhone());

        if (settlementPayer != null) {

            settlementData.setPyerId(settlementPayer.getId());

        } else {

            SettlementPayerDetails payerDetails = settlementPayerMapper.mapPayerDetails(payer);
            SettlementPayerDetails savedPayer = settlementPayerRepository.save(payerDetails);

            settlementData.setPyerId(savedPayer.getId());
        }

        settlementDataRepository.save(settlementData);
    }

}
