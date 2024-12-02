package io.github.geniusay.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: zq
 * @Description: TODO
 * @Date: 2024/12/2 22:40
 */
public class CreateFileUtil {
    /*
    // 缓存已创建的路径，避免重复检查
    private static final Map<String, String> createdDirectories = new HashMap<>();*/

    // LRU缓存实现
    private static final int CACHE_SIZE = 100;
    // 使用 LinkedHashMap 实现LRU缓存
    private static final Map<String, String> createdDirectoriesCache = new LinkedHashMap<String, String>(CACHE_SIZE, 0.75f, true){
        @Override
        protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
            return size() > CACHE_SIZE;
        }
    };

    /**
     * 创建目录
     *
     * @param directoryPath 目录路径
     * @throws IOException 如果目录创建失败
     */
    private static void createDirectories(String directoryPath) throws IOException {
        File file = new File(directoryPath);
        // 不存在，创建文件
        if(!file.exists()){
            // 创建成功返回提示
            if (file.mkdirs()){
                System.out.println("文件创建成功： " + file.getAbsolutePath());
            }else { // 创建失败返回提示
                throw new IOException("文件创建失败： " + file.getAbsolutePath());
            }
        }else { // 存在，直接返回
            System.out.println("文件已存在： "+ file.getAbsolutePath());
        }
    }

    /**
     * 创建文件，检查路径是否存在，不存在则创建
     *
     * @param filePath 文件路径，如：xxx/yyy/pool.class
     * @throws IOException 如果文件创建失败
     */
    public static void createFile(String filePath) throws IOException{
        // 获取当前系统的文件分隔符
        String separator = File.separator;

        // 使用 Path 类处理路径，避免手动处理分隔符
        String directoryPath = Paths.get(filePath).getParent().toString();

/*        // 判断操作系统类型，修改路径分隔符（window 与 Linux 不同）
        if (System.getProperty("os.name").toLowerCase().contains("win")){
            filePath = filePath.replace("/", separator);
        }else {
            filePath = filePath.replace("\\",separator);
        }*/
/*
        // 分离文件夹路径与文件名
        String[] pathParts = filePath.split(separator);
        // 获取文件名
        String fileName = pathParts[pathParts.length - 1];
        // 文件路径:/xxx/yyy
        String directoryPath = filePath.substring(0,filePath.lastIndexOf(separator));
*/

        // 使用缓存路径，避免重复检查
        String cachedPath = createdDirectoriesCache.get(directoryPath);
        if (cachedPath == null){
            // 如果缓存中没有路径，进行路径创建
            createDirectories(directoryPath);
            // 将路径加入缓存
            createdDirectoriesCache.put(directoryPath,directoryPath);
        }else {
            // 已经创建过该路径，无需再次检查
            System.out.println("Using cached path: " + cachedPath);
        }

        // 创建文件
        File file = new File(filePath);
        if (!file.exists()) {
            if (file.createNewFile()) {
                System.out.println("文件创建 :  " + file.getAbsolutePath());
            } else {
                throw new IOException("文件创建失败 :  " + file.getAbsolutePath());
            }
        } else {
            System.out.println("文件已经存在:  " + file.getAbsolutePath());
        }
    }

    public static void main(String[] args) {
        try {
            String localPath = "F:\\桌面2\\demo\\";
            // 测试用例：创建文件
            createFile(localPath + "xxx\\yyy\\pool.class");
            createFile(localPath + "xxx\\yyy\\pool2.class");
            createFile(localPath +  "xxx\\yyy\\ccc\\pool3.class");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
