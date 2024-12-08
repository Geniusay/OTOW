package io.github.geniusay.template.java;

import io.github.geniusay.engine.VelocityCodeEngine;
import io.github.geniusay.template.VelocityOTOWTemplate;
import io.github.geniusay.template.meta.MetaMethod;

public abstract class MethodTemplate extends VelocityOTOWTemplate {

    protected MethodTemplate(String templateFilePath) {
        super(templateFilePath);
    }

    public abstract MetaMethod generateMethod();

    public String generateMethodBody(){
        return VelocityCodeEngine.getCodeEngine().generate(this);
    }
}
