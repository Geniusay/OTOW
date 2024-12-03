package io.github.geniusay.controller;

import io.github.geniusay.pojo.FileNode;
import io.github.geniusay.pojo.FolderNode;
import io.github.geniusay.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/tree")
@RequiredArgsConstructor
@RestController
public class FileController {

    private final FileService fileService;

    /**
     * 获取指定项目的文件夹结构
     *
     * @param pIdAndPath 项目 ID 和路径，格式为：项目ID|相对路径
     * @return 文件夹树
     */
    @GetMapping("/folder")
    public FolderNode getFolderTree(@RequestParam String pIdAndPath) {
        return fileService.getFolderTree(pIdAndPath);
    }

    /**
     * 获取指定项目的文件信息
     *
     * @param pIdAndPath 项目 ID 和路径，格式为：项目ID|相对路径
     * @return 文件信息
     */
    @GetMapping("/file")
    public FileNode getFileInfo(@RequestParam String pIdAndPath) {
        return fileService.getFileInfo(pIdAndPath);
    }
}