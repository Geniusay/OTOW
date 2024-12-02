package io.github.geniusay.service;

import io.github.geniusay.core.FileContentReader;
import io.github.geniusay.core.FileTreeGenerator;
import io.github.geniusay.core.FileTypeUtils;
import io.github.geniusay.pojo.FileNode;
import io.github.geniusay.pojo.FolderNode;
import io.github.geniusay.core.SupportedLanguages;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {

    // 获取文件夹树（带缓存）
    public FolderNode getFolderTree(String directoryPath) {
        return FileTreeGenerator.getFolderTree(directoryPath);
    }

    // 获取文件信息
    public FileNode getFileInfo(String filePath) {
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            throw new IllegalArgumentException("文件不存在: " + filePath);
        }

        String fileName = file.getName();
        String fileType = FileTypeUtils.getFileExtension(fileName);
        long fileSize = file.length();
        List<String> rawLines = new ArrayList<>();

        // 判断文件是否为 Dockerfile（特殊文件）
        if (SupportedLanguages.isDockerfile(fileName)) {
            rawLines = FileContentReader.readFileContent(file, fileSize);
        } else if (FileContentReader.isFileTooLarge(fileSize)) {
            rawLines.add("文件内容过大或不可展示，请下载后查看");
        } else if (SupportedLanguages.isDisplayableFile(fileType)) {
            rawLines = FileContentReader.readFileContent(file, fileSize);
        } else {
            rawLines.add("文件类型不支持展示，请下载后查看");
        }

        return new FileNode(fileName, fileType, fileSize, rawLines);
    }
}