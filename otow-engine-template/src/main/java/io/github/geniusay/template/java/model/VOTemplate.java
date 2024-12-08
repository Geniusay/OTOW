package io.github.geniusay.template.java.model;

import io.github.geniusay.template.java.ClassTemplate;
import io.github.geniusay.template.meta.MetaAnnotation;

import static io.github.geniusay.common.constant.TemplateConstant.ENTITY_ANNOTATION;

public class VOTemplate extends ClassTemplate {

    public VOTemplate(String packagePath, String className) {
        super(packagePath, className+"VO");
    }

    @Override
    public void initAnnotations() {
        addAnnotations(MetaAnnotation.convertByClazz(ENTITY_ANNOTATION));
    }
}
