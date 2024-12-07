package io.github.geniusay.template.java.model;

import io.github.geniusay.template.java.ClassTemplate;
import io.github.geniusay.template.java.meta.MetaAnnotation;
import io.github.geniusay.template.java.meta.MetaMethod;
import io.github.geniusay.template.java.meta.MetaMethodParam;
import io.github.geniusay.template.java.meta.MetalField;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static io.github.geniusay.common.constant.TemplateConstant.CONTROLLER_ANNOTATION;
import static io.github.geniusay.common.constant.TemplateConstant.REQUEST_BODY;

public class ControllerTemplate extends ClassTemplate {

    private String urlPath;

    private String commonResult;

    private Set<ServiceTemplate> services = new HashSet<>();

    private List<MetaMethod> apiMethod = new ArrayList<>();

    public ControllerTemplate(String packagePath, String className, String urlPath) {
        super(packagePath, getControllerClassName(className));
        this.urlPath = urlPath;
        addAnnotation(new MetaAnnotation(RequestMapping.class, urlPath));
    }

    public static String getControllerClassName(String className){
        return className+"Controller";
    }

    public String getUrlPath() {
        return urlPath;
    }

    public void setCommonResult(String commonResult,Class<?> clazz){
        this.commonResult = commonResult;
        addImportClazz(clazz);
    }
    public void addService(ServiceTemplate serviceTemplate){
        this.services.add(serviceTemplate);
        String serviceClass = serviceTemplate.getClassName();
        String servicePackage = serviceTemplate.getAllPackagePath();
        String serviceName = serviceClass.toLowerCase();
        MetalField service = new MetalField(serviceName, serviceClass);
        service.addAnnotations(List.of(new MetaAnnotation(Resource.class)));
        this.addModelField(service);
        this.addImportPath(servicePackage);
    }

    public void addPostMethod(MetaMethod method, String path){
        method.addAnnotations(List.of(new MetaAnnotation(PostMapping.class, path)));
        this.addMethod(method);
    }

    public void addMethod(MetaMethod method){
        this.apiMethod.add(method);
        super.addMethod(method);
    }
    @Override
    public void initAnnotations(){
        addAnnotations(MetaAnnotation.convertByClazz(CONTROLLER_ANNOTATION));
    }
}
