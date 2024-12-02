package io.github.geniusay.common.params;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Params {

    private final Map<String, Object> params;

    public Params() {
        this.params = new HashMap<>();
    }

    public Params kv(String key, Object value){
        params.put(key, value);
        return this;
    }

    public <T> T get(String key, Class<T> clazz){
        Object data = params.computeIfPresent(key, (k, v) -> v);
        if(!Objects.isNull(data)){
            return clazz.cast(params.get(key));
        }
        return null;
    }
}
