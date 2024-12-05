package io.github.geniusay.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AddFolderDTO {
    
    /**
     * 父节点ID（可为空表示根节点）
     */
    private String parentId;

    /**
     * 文件夹名称
     */
    @NotBlank(message = "文件夹名称不能为空")
    private String folderName;
}