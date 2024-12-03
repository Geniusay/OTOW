package io.github.geniusay.service;

import io.github.geniusay.core.FileContentReader;
import io.github.geniusay.core.FileTreeGenerator;
import io.github.geniusay.core.ProjectMapping;
import io.github.geniusay.core.SupportedLanguages;
import io.github.geniusay.pojo.FileNode;
import io.github.geniusay.pojo.FolderNode;
import io.github.geniusay.utils.PathUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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

        String fileName = file.getName();
        String fileType = getFileExtension(fileName);
        long fileSize = file.length();
        List<String> rawLines = new ArrayList<>();

        // 判断文件是否为可展示类型
        if (!SupportedLanguages.isDisplayableFile(fileType)) {
            rawLines.add("文件类型不支持展示，请下载后查看。");
        } else if (FileContentReader.isFileTooLarge(fileSize)) {
            rawLines.add("文件内容过大或不可展示，请下载后查看。");
        } else {
            // 可展示类型且文件大小在可预览范围内，读取文件内容
            rawLines = FileContentReader.readFileContent(file, fileSize);
        }

        return new FileNode(fileName, fileType, fileSize, rawLines);
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