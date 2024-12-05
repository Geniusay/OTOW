package io.github.geniusay.core;

import io.github.geniusay.entity.po.VirtualFileTree;

import java.util.concurrent.ConcurrentHashMap;

public class VirtualFileTreeCache {

    private static final ConcurrentHashMap<String, VirtualFileTree> virtualFileTrees = new ConcurrentHashMap<>();

    // 获取虚拟文件树
    public static VirtualFileTree get(String treeId) {
        return virtualFileTrees.get(treeId);
    }

    // 添加虚拟文件树
    public static void put(String treeId, VirtualFileTree tree) {
        virtualFileTrees.put(treeId, tree);
    }

    // 判断是否存在虚拟文件树
    public static boolean contains(String treeId) {
        return virtualFileTrees.containsKey(treeId);
    }

    // 删除虚拟文件树
    public static void remove(String treeId) {
        virtualFileTrees.remove(treeId);
    }

    // 清空所有虚拟文件树
    public static void clear() {
        virtualFileTrees.clear();
    }
}