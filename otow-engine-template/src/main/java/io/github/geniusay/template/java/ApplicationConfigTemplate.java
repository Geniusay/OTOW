package io.github.geniusay.template.java;

import io.github.geniusay.template.VelocityOTOWTemplate;
import org.apache.velocity.VelocityContext;

//TODO application配置文件完善
public class ApplicationConfigTemplate extends VelocityOTOWTemplate {

    protected ApplicationConfigTemplate() {
        super("application.yml.vm");
    }

    @Override
    public VelocityContext getContext() {
        return null;
    }
}
