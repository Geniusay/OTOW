package io.github.geniusay.utils;

import io.github.geniusay.pojo.FileNode;
import io.github.geniusay.pojo.FolderNode;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileUtils {

    private static final long MAX_PREVIEW_SIZE = 1024 * 1024; // 最大预览大小：1MB
    private static final int PARTIAL_CONTENT_SIZE = 16 * 1024; // 部分展示大小：16KB

    // 文件树缓存，key 为顶层文件夹路径，value 为完整的文件树
    private static final Map<String, FolderNode> folderTreeCache = new HashMap<>();

    // 获取文件夹树（带缓存）
    public static FolderNode getFolderTree(String directoryPath) {
        // 如果缓存中存在，直接返回
        if (folderTreeCache.containsKey(directoryPath)) {
            return folderTreeCache.get(directoryPath);
        }

        // 构建完整的文件树并缓存
        FolderNode folderNode = buildFolderTree(directoryPath);
        folderTreeCache.put(directoryPath, folderNode);
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

    // 获取文件信息
    public static FileNode getFileInfo(String filePath) {
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            throw new IllegalArgumentException("文件不存在: " + filePath);
        }

        String fileType = getFileExtension(file);
        long fileSize = file.length();
        List<String> rawLines;

        // 判断文件是否可展示
        if (fileSize > MAX_PREVIEW_SIZE) {
            rawLines = new ArrayList<>();
            rawLines.add("文件内容过大或不可展示，请下载后查看");
        } else {
            rawLines = readFileContent(file, fileSize);
        }

        return new FileNode(file.getName(), fileType, fileSize, rawLines);
    }

    // 读取文件内容（按行分割）
    private static List<String> readFileContent(File file, long fileSize) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            long bytesRead = 0;

            while ((line = reader.readLine()) != null) {
                lines.add(line);
                bytesRead += line.getBytes().length;

                // 如果文件内容过大，只读取部分内容
                if (bytesRead > PARTIAL_CONTENT_SIZE) {
                    lines.add("...（内容过长，仅展示部分）");
                    break;
                }
            }
        } catch (IOException e) {
            lines.clear();
            lines.add("读取文件内容失败");
        }
        return lines;
    }

    // 获取文件扩展名
    private static String getFileExtension(File file) {
        String name = file.getName();
        int lastIndex = name.lastIndexOf(".");
        return (lastIndex > 0) ? name.substring(lastIndex + 1) : "unknown";
    }
}