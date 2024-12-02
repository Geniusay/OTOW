package io.github.geniusay.template.java.model;

import io.github.geniusay.template.java.ClassTemplate;
import org.apache.velocity.VelocityContext;

import java.util.stream.Collectors;

import static io.github.geniusay.common.constant.TemplateConstant.ENTITY_ANNOTATION;

/**
 * 数据库实体类模板
 */
public class EntityTemplate extends ClassTemplate {

    // 数据库表名
    protected String tableName;

    public EntityTemplate(String outputDir, String packagePath, String className, String tableName) {
        super(outputDir, packagePath, getEntityClassName(className));
        this.tableName = tableName;
        initImports();
        initAnnotations();

    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public VelocityContext getContext() {
        VelocityContext context = super.getContext();
        context.put("tableName", tableName);
        return context;
    }

    public static String getEntityClassName(String className){
        return className+"Entity";
    }

    private void initImports(){
        addImportClazz(ENTITY_ANNOTATION);
        addImportPath("com.baomidou.mybatisplus.annotation.*");
    }

    private void initAnnotations(){
        addAnnotations(
                ENTITY_ANNOTATION.stream().map(Class::getSimpleName).collect(Collectors.toList())
        );
        addAnnotation(String.format("TableName(\"%s\")", tableName));
    }
}
