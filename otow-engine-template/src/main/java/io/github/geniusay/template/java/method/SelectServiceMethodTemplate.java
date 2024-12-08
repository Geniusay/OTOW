package io.github.geniusay.template.java.method;

import io.github.geniusay.template.java.MethodTemplate;
import io.github.geniusay.template.meta.MetaMethod;
import org.apache.velocity.VelocityContext;

//TODO serviceImpl搜索实体方法模板完善
public class SelectServiceMethodTemplate extends MethodTemplate {

    protected SelectServiceMethodTemplate(String templateFilePath) {
        super(templateFilePath);
    }

    @Override
    public VelocityContext getContext() {
        return null;
    }

    @Override
    public MetaMethod generateMethod() {
        return null;
    }
}
