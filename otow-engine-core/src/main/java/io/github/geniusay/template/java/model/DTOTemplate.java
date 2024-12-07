package io.github.geniusay.template.java.model;

import io.github.geniusay.template.java.ClassTemplate;
import io.github.geniusay.template.java.meta.MetaAnnotation;

import static io.github.geniusay.common.constant.TemplateConstant.ENTITY_ANNOTATION;

public class DTOTemplate extends ClassTemplate {

    public DTOTemplate(String packagePath, String className) {
        super(packagePath, className+"DTO");
    }

    @Override
    public void initAnnotations() {
        addAnnotations(MetaAnnotation.convertByClazz(ENTITY_ANNOTATION));
    }
}
