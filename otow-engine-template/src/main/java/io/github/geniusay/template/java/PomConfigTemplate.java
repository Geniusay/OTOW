package io.github.geniusay.template.java;

import io.github.geniusay.template.VelocityOTOWTemplate;
import org.apache.velocity.VelocityContext;

//TODO pom配置文件模板
public class PomConfigTemplate extends VelocityOTOWTemplate {

    protected PomConfigTemplate() {
        super("pom.xml.vm");
    }

    @Override
    public VelocityContext getContext() {
        return null;
    }
}
