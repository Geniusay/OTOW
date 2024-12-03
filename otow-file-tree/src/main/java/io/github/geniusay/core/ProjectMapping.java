package io.github.geniusay.core;

import java.util.HashMap;
import java.util.Map;

public class ProjectMapping {

    // 项目 ID 和文件系统路径的映射
    private static final Map<String, String> PROJECT_PATH_MAPPING = new HashMap<>();

    static {
        PROJECT_PATH_MAPPING.put("369202865", "D:\\OTOW");
        PROJECT_PATH_MAPPING.put("1883662598", "D:\\cyberNomads-d");
    }

    /**
     * 根据项目 ID 获取对应的文件系统路径
     */
    public static String getProjectPath(String projectId) {
        String path = PROJECT_PATH_MAPPING.get(projectId);
        if (path == null) {
            throw new IllegalArgumentException("无效的项目 ID: " + projectId);
        }
        return path;
    }
}