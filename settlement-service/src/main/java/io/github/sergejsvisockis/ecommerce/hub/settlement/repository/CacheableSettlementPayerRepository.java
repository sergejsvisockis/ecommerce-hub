package io.github.sergejsvisockis.ecommerce.hub.settlement.repository;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import io.github.sergejsvisockis.ecommerce.hub.settlement.entity.SettlementPayerDetails;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class CacheableSettlementPayerRepository {

    private final Cache<CacheKey, SettlementPayerDetails> cache;
    private final RedisTemplate<CacheKey, SettlementPayerDetails> redisTemplate;
    private final SettlementPayerRepository settlementPayerRepository;

    public CacheableSettlementPayerRepository(RedisTemplate<CacheKey, SettlementPayerDetails> redisTemplate,
                                              SettlementPayerRepository settlementPayerRepository) {
        this.redisTemplate = redisTemplate;
        this.settlementPayerRepository = settlementPayerRepository;
        this.cache = Caffeine.newBuilder()
                .expireAfterWrite(1, TimeUnit.HOURS)
                .initialCapacity(10)
                .maximumSize(20)
                .build();
    }

    public SettlementPayerDetails findBySettlementPayerDetails(String email, String phone) {
        CacheKey key = new CacheKey(email, phone);

        SettlementPayerDetails payerDetails = cache.getIfPresent(key);

        if (payerDetails != null) {
            return payerDetails;
        }

        SettlementPayerDetails secondLevelPayerDetails = redisTemplate.opsForValue().get(key);
        if (secondLevelPayerDetails != null) {
            cache.put(key, secondLevelPayerDetails);
            return secondLevelPayerDetails;
        }

        SettlementPayerDetails repoDetails = settlementPayerRepository.findBySettlementPayerDetails(email, phone);
        if (repoDetails != null) {
            cache.put(key, repoDetails);
            redisTemplate.opsForValue().set(key, repoDetails);
        }

        return repoDetails;
    }

    public SettlementPayerDetails save(SettlementPayerDetails payerDetails) {
        SettlementPayerDetails savedDetails = settlementPayerRepository.save(payerDetails);

        CacheKey key = new CacheKey(savedDetails.getPayerEmail(), savedDetails.getPayerPhone());
        cache.put(key, savedDetails);
        redisTemplate.opsForValue().set(key, savedDetails);

        return savedDetails;
    }

}
