package io.github.geniusay.template;

import org.apache.velocity.VelocityContext;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 类文件生成模板
 */
public abstract class ClassTemplate extends Template{

    // 类需要导入的类
    protected final Set<String> imports = new HashSet<>();

    // 类的成员变量
    protected final Set<ModelField> modelFields = new HashSet<>();

    // 类上所加的注解
    protected final Set<String> annotations = new HashSet<>();

    // 类所在的包文件夹
    protected String packagePath;

    protected final String className;

    public ClassTemplate(String templateFilePath, String outputDir, String packagePath, String className) {
        super(templateFilePath, outputDir);
        this.packagePath = packagePath;
        this.className = className;
    }

    public Set<String> getImports() {
        return imports;
    }

    public void addImportClazz(List<Class<?>> importClazz) {
        this.imports.addAll(importClazz.stream().map(Class::getName).collect(Collectors.toSet()));
    }

    public void addImportClazz(Class<?> clazz) {
        this.imports.add(clazz.getName());
    }

    public void addImportPath(String path) {
        this.imports.add(path);
    }

    public void addAnnotation(String annotationName){
        this.annotations.add(getAnnotationToken(annotationName));
    }

    public void addAnnotations(List<String> annotations){
        this.annotations.addAll(annotations.stream().map(ClassTemplate::getAnnotationToken).collect(Collectors.toSet()));
    }

    public void addImportPaths(List<String> importPath) {
        this.imports.addAll(importPath);
    }

    public String getPackagePath() {
        return packagePath;
    }

    public void setPackagePath(String packagePath) {
        this.packagePath = packagePath;
    }

    public String getClassName() {
        return className;
    }

    public Set<ModelField> getModelFields() {
        return modelFields;
    }

    public void addModelField(ModelField modelField){
        this.modelFields.add(modelField);
    }

    public void addModelFields(List<ModelField> modelFields){
        this.modelFields.addAll(modelFields);
    }

    @Override
    public VelocityContext getContext() {
        VelocityContext context = new VelocityContext();
        context.put("package", packagePath);
        context.put("imports", imports);
        context.put("className", className);
        context.put("fields", modelFields);
        context.put("annotations", annotations);
        return context;
    }

    public static String getAnnotationToken(String annotation){
        return "@"+annotation;
    }

    @Override
    public String getOutputDir() {
        return Path.of(this.outputDir, className+".java").toString();
    }
}
