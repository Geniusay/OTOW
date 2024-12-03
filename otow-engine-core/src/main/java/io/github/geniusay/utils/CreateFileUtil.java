package io.github.geniusay.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zq
 * @Date: 2024/12/2 22:40
 */
public class CreateFileUtil {
    // LRU缓存实现
    private static final int CACHE_SIZE = 100;
    // 缓存过期时间：10分钟
    private static final long CACHE_EXPIRATION_TIME = TimeUnit.MINUTES.toMillis(10);
    // 使用 LinkedHashMap 实现LRU缓存
    private static final Map<String, CacheEntry> createdDirectoriesCache = new LinkedHashMap<String, CacheEntry>(CACHE_SIZE, 0.75f, true){
        @Override
        protected boolean removeEldestEntry(Map.Entry<String, CacheEntry> eldest) {
            return size() > CACHE_SIZE || isExpired(eldest.getValue());
        }
    };

    /**
     * 判断缓存是否过期
     *
     * @param entry 缓存条目
     * @return 是否过期
     */
    private static boolean isExpired(CacheEntry entry) {
        return System.currentTimeMillis() - entry.timestamp > CACHE_EXPIRATION_TIME;
    }

    // 缓存条目，存储路径和时间戳
    private static class CacheEntry {
        String directoryPath;
        long timestamp;

        CacheEntry(String directoryPath) {
            this.directoryPath = directoryPath;
            this.timestamp = System.currentTimeMillis();
        }
    }


    /**
     * 创建目录
     *
     * @param directoryPath 目录路径
     * @throws IOException 如果目录创建失败
     */
    private static void createDirectories(String directoryPath) throws IOException {
        // 路径无效直接抛出异常
        if (directoryPath == null || directoryPath.isEmpty()) {
            throw new IllegalArgumentException("Invalid directory path");
        }
        // 判断路径是否合法
        try {
            Paths.get(directoryPath); // 尝试将路径转换为有效路径，若非法会抛出异常
        } catch (InvalidPathException e) {
            throw new IOException("Invalid path: " + directoryPath, e);
        }

        File dir = new File(directoryPath);

        // 如果目录存在且是文件，抛出异常
        if (dir.exists() && !dir.isDirectory()) {
            throw new IOException("A file exists at the specified path: " + directoryPath);
        }

        // 不存在且路径合法，创建目录
        if (!dir.exists()) {
            // 使用 mkdir只创建子目录
            if (dir.getParentFile().exists()) {
                if (!dir.mkdir()) {
                    throw new IOException("Failed to create directory: " + directoryPath);
                }
            } else {
                // 使用 mkdirs（递归创建所有不存在的目录）
                if (!dir.mkdirs()) {
                    throw new IOException("Failed to create directory structure: " + directoryPath);
                }
            }
        }
    }


    /**
     * 创建文件，检查路径是否存在，不存在则创建
     *
     * @param filePath 文件路径，如：xxx/yyy/pool.class
     * @throws IOException 如果文件创建失败
     */
    public static void createFile(String filePath) throws IOException {
        // 路径无效直接抛出异常
        if (filePath == null || filePath.isEmpty()) {
            throw new IllegalArgumentException("Invalid file path: " + filePath);
        }

        // 判断路径是否合法
        try {
            // 尝试将路径转换为有效路径，若非法会抛出异常
            Paths.get(filePath);
        } catch (InvalidPathException e) {
            throw new IOException("Invalid file path: " + filePath, e);
        }
        // 使用 Path 类处理路径，避免手动处理分隔符
        String directoryPath = Paths.get(filePath).getParent().toString();

        // 使用缓存路径，避免重复检查
        CacheEntry cachedEntry = createdDirectoriesCache.get(directoryPath);

        if (cachedEntry == null || isExpired(cachedEntry)) {
            // 如果缓存中没有路径或缓存已过期，进行路径创建
            createDirectories(directoryPath);
            // 将路径加入缓存
            createdDirectoriesCache.put(directoryPath, new CacheEntry(directoryPath));
        }

        // 创建文件
        File file = new File(filePath);
        if (!file.exists()) {
            if (!file.createNewFile()) {
                throw new IOException("Failed to create the file: " + file.getAbsolutePath());
            }
        }
    }

    public static void main(String[] args) {
        try {
            String localPath = "F:\\桌面2\\demo\\";
            // 测试用例：创建文件
            createFile(localPath + "xxx\\yyy\\pool.class");
            createFile(localPath + "xxx\\yyy\\pool2.class");
            createFile(localPath +  "xxx\\yyy\\ccc\\pool3.class");
            createFile(localPath +  "xxx\\yyy\\ccc\\ocuments\\Files<test>");
            createFile(localPath +  "xxx\\yyy\\ccc\\myfolder|test");
            createFile(localPath +  "xxx\\yyy\\ccc\\Some Folder\\file.txt");
            createFile(localPath +  "xxx\\yyy\\ccc\\...\\Documents\\file.txt");
            createFile(localPath +  "xxx\\yyy\\ccc\\Data\\NUL");
            createFile(localPath +  "xxx\\yyy\\ccc\\Program*Files\\test");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
