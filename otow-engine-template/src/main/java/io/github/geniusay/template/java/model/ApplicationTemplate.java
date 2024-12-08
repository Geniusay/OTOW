package io.github.geniusay.template.java.model;

import io.github.geniusay.template.java.ClassTemplate;
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
        String body = String.format("SpringApplication.run(%s.class, args);", className);
        MetaMethod metaMethod = new MetaMethod("main", List.of(new MetaMethodParam("String[]", "args")), body);
        metaMethod.addImportClazz(SpringApplication.class);
        metaMethod.setIsStatic(true);
        this.addMethod(metaMethod);
    }

    @Override
    public void initAnnotations() {
        addAnnotation(SPRING_BOOT_APPLICATION);
    }
}
