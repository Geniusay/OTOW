package io.github.geniusay.service;

import io.github.geniusay.core.FileTreeGenerator;
import io.github.geniusay.core.ProjectMapping;
import io.github.geniusay.pojo.FileNode;
import io.github.geniusay.pojo.FolderNode;
import io.github.geniusay.utils.PathUtils;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class FileService {

    /**
     * 获取指定项目的文件夹结构
     *
     * @param projectIdAndPath 项目 ID 和路径，格式为：项目ID|相对路径
     * @return 文件夹树
     */
    public FolderNode getFolderTree(String projectIdAndPath) {
        // 解析项目 ID 和相对路径
        String[] parts = PathUtils.parseProjectIdAndPath(projectIdAndPath);
        String projectId = parts[0];
        String relativePath = parts[1];

        // 获取项目对应的根路径
        String projectRoot = ProjectMapping.getProjectPath(projectId);

        // 转换为系统路径
        String directoryPath = projectRoot + (relativePath.isEmpty() ? "" : "\\" + PathUtils.toSystemPath(relativePath));

        FolderNode folderNode = FileTreeGenerator.getFolderTree(directoryPath);

        // 设置相对路径为 parentPath，并转换为自定义路径
        folderNode.setParentPath(PathUtils.toCustomPath(relativePath));
        return folderNode;
    }

    /**
     * 获取指定项目的文件信息
     *
     * @param projectIdAndPath 项目 ID 和路径，格式为：项目ID|相对路径
     * @return 文件信息
     */
    public FileNode getFileInfo(String projectIdAndPath) {
        // 解析项目 ID 和相对路径
        String[] parts = PathUtils.parseProjectIdAndPath(projectIdAndPath);
        String projectId = parts[0];
        String relativePath = parts[1];

        // 获取项目对应的根路径
        String projectRoot = ProjectMapping.getProjectPath(projectId);

        // 转换为系统路径
        String filePath = projectRoot + File.separator + PathUtils.toSystemPath(relativePath);

        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            throw new IllegalArgumentException("文件不存在: " + filePath);
        }

        return new FileNode(
                file.getName(),
                getFileExtension(file.getName()),
                file.length(),
                io.github.geniusay.core.FileContentReader.readFileContent(file, file.length())
        );
    }

    /**
     * 获取文件扩展名
     *
     * @param fileName 文件名称
     * @return 文件扩展名
     */
    private String getFileExtension(String fileName) {
        int lastIndex = fileName.lastIndexOf(".");
        return (lastIndex > 0) ? fileName.substring(lastIndex + 1) : "unknown";
    }
}