package io.github.geniusay.core;

import io.github.geniusay.pojo.FolderNode;

import java.util.concurrent.ConcurrentHashMap;

public class FolderTreeCache {

    // 文件夹树缓存
    private static final ConcurrentHashMap<String, FolderNode> folderTreeCache = new ConcurrentHashMap<>();

    // 获取缓存中的文件夹树
    public static FolderNode get(String directoryPath) {
        return folderTreeCache.get(directoryPath);
    }

    // 添加文件夹树到缓存
    public static void put(String directoryPath, FolderNode folderNode) {
        folderTreeCache.put(directoryPath, folderNode);
    }

    // 检查是否存在缓存
    public static boolean contains(String directoryPath) {
        return folderTreeCache.containsKey(directoryPath);
    }

    // 清空缓存（可用于手动刷新）
    public static void clear() {
        folderTreeCache.clear();
    }
}