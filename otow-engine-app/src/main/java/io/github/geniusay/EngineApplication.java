package io.github.geniusay;


import io.github.geniusay.engine.VelocityFactory;
import io.github.geniusay.template.model.EntityTemplate;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

@SpringBootApplication
public class EngineApplication {

//    public static void main( String[] args ) {
//
//        SpringApplication.run(EngineApplication.class, args);
//    }

    public static void main(String[] args) {
        VelocityEngine velocity = VelocityFactory.getVelocity();
        EntityTemplate template = new EntityTemplate("E:\\Project\\OTOW\\otow-engine\\otow-engine-template\\src\\main\\resources\\generate", "org.example.entity", "Test", "test");
        Template velocityTemplate = velocity.getTemplate(template.getTemplateFilePath());
        VelocityContext context = template.getContext();
        StringWriter writer = new StringWriter();
        velocityTemplate.merge(context, writer);

        Path filePath = Path.of(template.getOutputDir()); // 指定文件路径
        try {
            // 写入文件，如果文件存在则覆盖
            Files.write(filePath, writer.toString().getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("File written successfully: " + filePath.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
