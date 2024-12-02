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

    // 获取指定文件夹的结构
    @GetMapping("/folder")
    public FolderNode getFolderTree(@RequestParam String path) {
        return fileService.getFolderTree(path);
    }

    // 获取指定文件的信息
    @GetMapping("/file")
    public FileNode getFileInfo(@RequestParam String path) {
        return fileService.getFileInfo(path);
    }
}