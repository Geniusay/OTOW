package io.github.geniusay.template.model;

import io.github.geniusay.template.ClassTemplate;
import org.apache.velocity.VelocityContext;

import java.util.stream.Collectors;

import static io.github.geniusay.constant.TemplateConstant.ENTITY_ANNOTATION;

/**
 * 数据库实体类模板
 */
public class EntityTemplate extends ClassTemplate {

    // 数据库表名
    protected String tableName;

    private static final String ENTITY_TEMPLATE_PATH = "entity.vm";

    public EntityTemplate(String outputDir, String packagePath, String className, String tableName) {
        super(ENTITY_TEMPLATE_PATH, outputDir, packagePath, getEntityClassName(className));
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
