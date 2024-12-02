package io.github.geniusay.service;

import io.github.geniusay.pojo.FileNode;
import io.github.geniusay.pojo.FolderNode;
import io.github.geniusay.utils.FileUtils;
import org.springframework.stereotype.Service;

@Service
public class FileService {

    // 获取文件夹树（带缓存）
    public FolderNode getFolderTree(String directoryPath) {
        return FileUtils.getFolderTree(directoryPath);
    }

    // 获取文件信息
    public FileNode getFileInfo(String filePath) {
        return FileUtils.getFileInfo(filePath);
    }
}