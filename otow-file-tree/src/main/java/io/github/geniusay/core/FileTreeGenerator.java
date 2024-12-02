package io.github.geniusay.core;

import io.github.geniusay.pojo.FolderNode;

import java.io.File;

public class FileTreeGenerator {

    // 获取文件夹树（带缓存）
    public static FolderNode getFolderTree(String directoryPath) {
        // 如果缓存中存在，直接返回
        if (FolderTreeCache.contains(directoryPath)) {
            return FolderTreeCache.get(directoryPath);
        }

        // 构建完整的文件树并缓存
        FolderNode folderNode = buildFolderTree(directoryPath);
        FolderTreeCache.put(directoryPath, folderNode);
        return folderNode;
    }

    // 构建文件夹树
    private static FolderNode buildFolderTree(String directoryPath) {
        File root = new File(directoryPath);
        if (!root.exists() || !root.isDirectory()) {
            throw new IllegalArgumentException("路径不存在或不是文件夹: " + directoryPath);
        }

        FolderNode folderNode = new FolderNode(root.getAbsolutePath());
        File[] children = root.listFiles();

        if (children != null) {
            for (File child : children) {
                if (child.isDirectory()) {
                    folderNode.getFolderList().add(child.getName());
                } else {
                    folderNode.getFileList().add(child.getName());
                }
            }
        }

        return folderNode;
    }
}