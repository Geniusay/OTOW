package io.github.geniusay.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class UpdateFileDTO {

    private String fileId;

    /**
     * 文件内容（按行分割）
     */
    @NotNull(message = "文件内容不能为空")
    private List<String> content;
}