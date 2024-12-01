package io.github.geniusay.engine;

import org.apache.velocity.app.VelocityEngine;

import java.util.Objects;
import java.util.Properties;

/**
 * velocity工厂
 */
public class VelocityFactory {

    private static Properties props;

    static{
        props = new Properties();
        String resourcePath = Objects.requireNonNull(VelocityFactory.class.getClassLoader().getResource("template")).getPath();
        props.setProperty("file.resource.loader.path", resourcePath); // 设置模板路径
        props.setProperty("input.encoding", "UTF-8"); // 设置输入文件的编码
        props.setProperty("output.encoding", "UTF-8"); // 设置输出文件的编码
    }

    public static VelocityEngine getVelocity(){
        VelocityEngine velocityEngine = new VelocityEngine(props);
        velocityEngine.init();
        return velocityEngine;
    }
}
