package io.github.geniusay.core;

public class FileTypeUtils {

    // 判断文件是否为可展示类型
    public static boolean isDisplayableFile(String fileExtension) {
        return SupportedLanguages.isDisplayableFile(fileExtension);
    }

    // 获取文件扩展名
    public static String getFileExtension(String fileName) {
        int lastIndex = fileName.lastIndexOf(".");
        return (lastIndex > 0) ? fileName.substring(lastIndex + 1) : "unknown";
    }
}