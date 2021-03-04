package io.reflectoring.cache;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.SerializerConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import io.reflectoring.cache.rest.Car;
import io.reflectoring.cache.serializer.CarSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CacheClient {

    public static final String CARS = "cars";
    private final HazelcastInstance client = Hazelcast.newHazelcastInstance(createConfig());

    public Car put(String number, Car car){
        IMap<String, Car> map = client.getMap(CARS);
        Car value = map.putIfAbsent(number, car);
        log.info("hazelcastInstance - client: {}, number: {}, car: {}, value: {}",
                client, number, car, value);
        return value;
    }

    public Car get(String key){
        IMap<String, Car> map = client.getMap(CARS);
        Car value = map.get(key);
        log.info("hazelcastInstance - client: {}, key: {},  value: {}", client, key, value);
        return value;
    }

    public Config createConfig() {
        Config config = new Config();
        config.addMapConfig(mapConfig());
        config.getSerializationConfig().addSerializerConfig(serializerConfig());
        return config;
    }

    private SerializerConfig serializerConfig() {
        return  new SerializerConfig()
                .setImplementation(new CarSerializer())
                .setTypeClass(Car.class);
    }

    private MapConfig mapConfig() {
        MapConfig mapConfig = new MapConfig(CARS);
        mapConfig.setTimeToLiveSeconds(360);
        mapConfig.setMaxIdleSeconds(360);
        return mapConfig;
    }
}
