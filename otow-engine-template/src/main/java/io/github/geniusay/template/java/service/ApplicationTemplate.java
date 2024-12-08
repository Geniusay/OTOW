package io.github.geniusay.template.java.service;

import io.github.geniusay.template.java.ClassTemplate;
import io.github.geniusay.template.java.method.ApplicationMethodTemplate;
import io.github.geniusay.template.meta.MetaMethod;
import io.github.geniusay.template.meta.MetaMethodParam;
import org.springframework.boot.SpringApplication;

import java.util.List;

import static io.github.geniusay.common.constant.TemplateConstant.SPRING_BOOT_APPLICATION;

public class ApplicationTemplate extends ClassTemplate {

    public ApplicationTemplate(String packagePath, String className) {
        super(packagePath, className+"Application");
        initMethod();
    }

    public void initMethod(){
        ApplicationMethodTemplate methodTemplate = new ApplicationMethodTemplate(className);
        this.addMethod(methodTemplate.generateMethod());
    }

    @Override
    public void initAnnotations() {
        addAnnotation(SPRING_BOOT_APPLICATION);
    }
}
