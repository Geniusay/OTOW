package io.github.geniusay.template.java.model;

import io.github.geniusay.template.java.ClassTemplate;
import io.github.geniusay.template.java.meta.MetaAnnotation;

import static io.github.geniusay.common.constant.TemplateConstant.REQ_ANNOTATION;

public class ReqTemplate extends ClassTemplate {

    public ReqTemplate(String packagePath, String className) {
        super(packagePath, className+"Req");
    }

    @Override
    public void initAnnotations(){
        addAnnotations(MetaAnnotation.convertByClazz(REQ_ANNOTATION));
    }
}
