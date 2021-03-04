package io.reflectoring.cache.client;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.NearCacheConfig;
import com.hazelcast.config.SerializerConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import io.reflectoring.cache.rest.Car;
import io.reflectoring.cache.serializer.CarSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CacheClient {

    private static final String CARS = "cars";

    private HazelcastInstance client = HazelcastClient.newHazelcastClient(creatClientConfig());

    public Car put(String key, Car car) {
        log.info("put in the cache - client: {}, key: {}, car: {}", client, key, car);
        IMap<String, Car> map = client.getMap(CARS);
        return map.putIfAbsent(key, car);
    }

    public Car get(String key) {
        IMap<String, Car> map = client.getMap(CARS);
        if (map != null) {
            Car car = map.get(key);
            log.info("get from cache - client: {}, key: {}, car: {}", client, key, car);
            return car;
        }
        return null;
    }

    public Config createConfig() {
        Config config = new Config();
        config.addMapConfig(mapConfig());
        config.getSerializationConfig().addSerializerConfig(serializerConfig());
        return config;
    }

    private MapConfig mapConfig() {
        MapConfig mapConfig = new MapConfig(CARS);
        mapConfig.setTimeToLiveSeconds(360);
        mapConfig.setMaxIdleSeconds(360);
        return mapConfig;
    }

    private ClientConfig creatClientConfig() {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.addNearCacheConfig(createNearCacheConfig());
        clientConfig.getSerializationConfig().addSerializerConfig(serializerConfig());
        return clientConfig;
    }

    private NearCacheConfig createNearCacheConfig() {
        NearCacheConfig nearCacheConfig = new NearCacheConfig();
        nearCacheConfig.setName(CARS);
        nearCacheConfig.setTimeToLiveSeconds(360);
        nearCacheConfig.setMaxIdleSeconds(360);
        return nearCacheConfig;
    }

    private SerializerConfig serializerConfig() {
        return  new SerializerConfig()
                .setImplementation(new CarSerializer())
                .setTypeClass(Car.class);
    }
}
