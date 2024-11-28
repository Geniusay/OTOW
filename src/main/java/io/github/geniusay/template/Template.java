package io.github.geniusay.template;

import org.apache.velocity.VelocityContext;

/**
 * 模板类
 */
public abstract class Template {

    // 模板文件路径
    protected final String templateFilePath;

    // 输出文件位置
    protected final String outputDir;

    public Template(String templateFilePath, String outputDir) {
        this.templateFilePath = templateFilePath;
        this.outputDir = outputDir;
    }

    public String getTemplateFilePath() {
        return templateFilePath;
    }

    public String getOutputDir() {
        return outputDir;
    }

    /**
     * 对于模板生成引擎的容器操作
     * @return
     */
    public abstract VelocityContext getContext();
}
