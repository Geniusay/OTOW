package io.github.geniusay.template.java;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.github.geniusay.template.VelocityOTOWTemplate;
import org.apache.velocity.VelocityContext;

import java.util.Map;

public class ApplicationConfigTemplate extends VelocityOTOWTemplate {

    private JSONObject config;
    public ApplicationConfigTemplate(JSONObject config) {
        super("application.yml.vm");
        this.config = config;
    }

    @Override
    public VelocityContext getContext() {
        VelocityContext context = new VelocityContext();
        context.put("config", JSON.parseObject(config.toJSONString(), Map.class));
        return context;
    }
}
