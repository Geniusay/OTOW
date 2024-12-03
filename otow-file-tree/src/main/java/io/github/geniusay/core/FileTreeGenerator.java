package io.github.geniusay.core;

import io.github.geniusay.pojo.FolderNode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileTreeGenerator {

    /**
     * 获取文件夹树（带路径合并）
     *
     * @param directoryPath 文件夹路径
     * @return 文件夹节点
     */
    public static FolderNode getFolderTree(String directoryPath) {
        // 如果缓存中存在，直接返回
        if (FolderTreeCache.contains(directoryPath)) {
            return FolderTreeCache.get(directoryPath);
        }

        // 构建完整的文件树并缓存
        FolderNode folderNode = buildFolderTree(directoryPath, "", "");
        FolderTreeCache.put(directoryPath, folderNode);
        return folderNode;
    }

    /**
     * 构建文件夹树
     *
     * @param directoryPath 文件夹路径
     * @param currentPath   当前路径（用于路径合并）
     * @param rootPath      根路径，用于后续去除前缀
     * @return 文件夹节点
     */
    private static FolderNode buildFolderTree(String directoryPath, String currentPath, String rootPath) {
        File root = new File(directoryPath);
        if (!root.exists() || !root.isDirectory()) {
            throw new IllegalArgumentException("路径不存在或不是文件夹: " + directoryPath);
        }

        // 当前文件夹的路径
        String folderName = currentPath.isEmpty() ? root.getName() : currentPath + "|" + root.getName();
        if (rootPath.isEmpty()) {
            rootPath = folderName; // 记录根路径
        }
        FolderNode folderNode = new FolderNode(folderName);

        File[] children = root.listFiles();
        if (children != null) {
            List<String> folderList = new ArrayList<>();
            for (File child : children) {
                if (child.isDirectory()) {
                    // 递归构建子文件夹树
                    FolderNode subFolder = buildFolderTree(child.getAbsolutePath(), folderName, rootPath);

                    // 如果子文件夹下只有一个子文件夹且没有文件，合并路径
                    if (subFolder.getFolderList().size() == 1 && subFolder.getFileList().isEmpty()) {
                        folderList.add(subFolder.getFolderList().get(0)); // 合并子文件夹路径
                    } else {
                        folderList.add(subFolder.getParentPath()); // 添加子文件夹路径
                    }
                } else {
                    folderNode.getFileList().add(child.getName());
                }
            }

            // 去除根路径前缀
            List<String> adjustedFolderList = new ArrayList<>();
            for (String folder : folderList) {
                adjustedFolderList.add(folder.replaceFirst("^" + rootPath + "\\|", ""));
            }
            folderNode.setFolderList(adjustedFolderList);
        }

        return folderNode;
    }
}