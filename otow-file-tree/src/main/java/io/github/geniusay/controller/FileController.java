package io.github.geniusay.controller;

import io.github.geniusay.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/tree")
@RequiredArgsConstructor
@RestController
public class FileController {

    private final FileService fileService;

    /**
     * 获取指定项目的文件或文件夹信息
     *
     * @param path 项目 ID 和路径，格式为：项目ID|相对路径
     * @return 文件信息或文件夹树
     */
    @GetMapping("/{*path}")
    public Object getFileOrFolderInfo(@PathVariable("path") String path) {
        // 判断路径是否是文件（以 .xxx 结尾）
        if (isFile(path)) {
            return fileService.getFileInfo(path);
        } else {
            return fileService.getFolderTree(path);
        }
    }

    /**
     */
    private boolean isFile(String path) {
        return path.matches(".*\\.[a-zA-Z0-9]+$");
    }
}