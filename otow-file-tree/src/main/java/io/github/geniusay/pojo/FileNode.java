package io.github.geniusay.pojo;

import lombok.Data;

import java.util.List;

@Data
public class FileNode {

    private String name; // 文件名称
    private String fileType; // 文件类型（扩展名）
    private long size; // 文件大小（字节）
    private List<String> rawLines; // 文件内容（按行分割）

    public FileNode(String name, String fileType, long size, List<String> rawLines) {
        this.name = name;
        this.fileType = fileType;
        this.size = size;
        this.rawLines = rawLines;
    }
}