package io.github.geniusay.velocity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.github.geniusay.engine.VelocityCodeEngine;
import io.github.geniusay.template.java.meta.MetaAnnotation;
import io.github.geniusay.template.java.meta.MetaMethod;
import io.github.geniusay.template.java.meta.MetaMethodParam;
import io.github.geniusay.template.java.meta.MetalField;
import io.github.geniusay.template.java.model.*;
import org.junit.Test;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static io.github.geniusay.common.constant.TemplateConstant.REQUEST_BODY;

public class VelocityGenerateTest {

    private static EntityTemplate getEntityTemplate() {
        EntityTemplate userEntity = new EntityTemplate("io.github.geniusay.velocity.generate", "User", "user");

        MetaAnnotation fillAnnotation = new MetaAnnotation(TableField.class, "fill", "FieldFill.INSERT_UPDATE", FieldFill.class);
        MetaAnnotation tableId = new MetaAnnotation(TableId.class);

        userEntity.addModelFields(List.of(
                new MetalField("id", String.class, tableId),
                new MetalField("uid", String.class),
                new MetalField("uid", String.class),
                new MetalField("avatar", String.class),
                new MetalField("createTime", Date.class, fillAnnotation),
                new MetalField("updateTime", Date.class, fillAnnotation)
        ));
        return userEntity;
    }

    private static ServiceTemplate getServiceTemplate() {
        ServiceTemplate userService = new ServiceTemplate("io.github.geniusay.velocity.generate.service", "User");
        EntityTemplate entityTemplate = getEntityTemplate();
        String className = entityTemplate.getClassName();
        userService.addMethod("insertUserInfo",List.of(
            new MetaMethodParam(className, userService.getAllPackagePath(), className.toLowerCase())
        ));
        return userService;
    }

    private static MetaMethod getPostMethod(){
        ServiceTemplate serviceTemplate = getServiceTemplate();
        ReqTemplate reqUser = new ReqTemplate("io.github.geniusay.velocity.generate.pojo.req", "User");
        MetaMethodParam metaMethodParam = new MetaMethodParam(reqUser.getClassName(),reqUser.getAllPackagePath(), reqUser.getClassName().toLowerCase());
        metaMethodParam.addAnnotations(List.of(REQUEST_BODY));

        String body = String.format("%s.%s(%s);", serviceTemplate.getClassName().toLowerCase(), "insertUserInfo", metaMethodParam.getName());
        return new MetaMethod("insertUserInfo", List.of(metaMethodParam), body);
    }

    @Test
    public void generateEntity(){
        VelocityCodeEngine velocityCodeEngine = new VelocityCodeEngine();
        EntityTemplate userEntity = getEntityTemplate();

        String generate = velocityCodeEngine.generate(userEntity);

        System.out.println(generate);
    }


    @Test
    public void generateController(){
        VelocityCodeEngine velocityCodeEngine = new VelocityCodeEngine();
        ControllerTemplate userController = new ControllerTemplate("io.github.geniusay.velocity.generate", "User", "/user");
        ServiceTemplate userService = getServiceTemplate();
        userController.addService(userService);
        userController.addPostMethod(getPostMethod(),"/add/user");
        String generateController = velocityCodeEngine.generate(userController);
        String generateService = velocityCodeEngine.generate(userService);

        System.out.println(generateController);
        System.out.println(generateService);
    }


    @Test
    public void generateMapper(){
        VelocityCodeEngine velocityCodeEngine = new VelocityCodeEngine();
        MapperTemplate mapperTemplate = new MapperTemplate("io.github.geniusay.velocity.generate.mapper","User", getEntityTemplate());

        String generate = velocityCodeEngine.generate(mapperTemplate);
        System.out.println(generate);
    }

    @Test
    public void generateServiceImpl(){
        VelocityCodeEngine velocityCodeEngine = new VelocityCodeEngine();
        ServiceImplTemplate serviceImplTemplate = new ServiceImplTemplate("io.github.geniusay.velocity.generate.service.impl","User", getServiceTemplate());

        String generate = velocityCodeEngine.generate(serviceImplTemplate);
        System.out.println(generate);
    }

    @Test
    public void generateApplication(){
        VelocityCodeEngine velocityCodeEngine = new VelocityCodeEngine();
        ApplicationTemplate applicationTemplate = new ApplicationTemplate("io.github.geniusay.velocity.generate","OTOW");

        String generate = velocityCodeEngine.generate(applicationTemplate);
        System.out.println(generate);
    }
}
