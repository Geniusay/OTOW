package io.github.geniusay.template;

/**
 * 模板类
 */
public abstract class OTOWTemplate {

    // 输出文件位置
    protected final String outputDir;

    public OTOWTemplate(String outputDir) {
        this.outputDir = outputDir;
    }

    public String getOutputDir() {
        return outputDir;
    }

}
