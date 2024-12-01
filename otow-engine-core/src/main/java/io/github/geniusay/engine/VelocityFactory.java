package io.github.geniusay.engine;

import org.apache.velocity.app.VelocityEngine;

import java.util.Properties;

/**
 * velocity工厂
 */
public class VelocityFactory {

    private static Properties props;

    static{
        props = new Properties();
        props.setProperty("file.resource.loader.path", "src/main/resources/template"); // 设置模板路径
        props.setProperty("input.encoding", "UTF-8"); // 设置输入文件的编码
        props.setProperty("output.encoding", "UTF-8"); // 设置输出文件的编码
    }

    public static VelocityEngine getVelocity(){
        VelocityEngine velocityEngine = new VelocityEngine(props);
        velocityEngine.init();
        return velocityEngine;
    }
}
