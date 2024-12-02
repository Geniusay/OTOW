package io.github.geniusay.pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FolderNode {

    private String parentPath; // 父文件夹路径
    private List<String> folderList; // 文件夹名称列表
    private List<String> fileList; // 文件名称列表

    public FolderNode(String parentPath) {
        this.parentPath = parentPath;
        this.folderList = new ArrayList<>();
        this.fileList = new ArrayList<>();
    }
}