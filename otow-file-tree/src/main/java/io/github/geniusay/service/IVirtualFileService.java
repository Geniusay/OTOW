package io.github.geniusay.service;

import io.github.geniusay.entity.dto.AddFileDTO;
import io.github.geniusay.entity.dto.AddFolderDTO;
import io.github.geniusay.entity.dto.UpdateFileDTO;
import io.github.geniusay.entity.vo.NodeVO;

import java.util.List;

public interface IVirtualFileService {

    /**
     * 初始化虚拟文件树
     *
     * @param directoryPath 文件夹路径
     * @param treeId        虚拟树ID（可选）
     * @return 虚拟树ID
     */
    String initializeVirtualTree(String directoryPath, String treeId);

    /**
     * 获取某个父节点下的子节点列表
     *
     * @param treeId   虚拟树ID
     * @param parentId 父节点ID（可以为空表示根）
     * @return 子节点列表
     */
    List<NodeVO> getChildrenNodes(String treeId, String parentId);

    /**
     * 获取文件内容
     *
     * @param treeId 虚拟树ID
     * @param fileId 文件ID
     * @return 文件内容
     */
    List<String> getFileContent(String treeId, String fileId);

    /**
     * 添加文件夹
     *
     * @param treeId  虚拟树ID
     * @param request 添加文件夹的请求
     * @return 新文件夹的ID
     */
    String addFolder(String treeId, AddFolderDTO request);

    /**
     * 添加文件
     *
     * @param treeId  虚拟树ID
     * @param request 添加文件的请求
     * @return 新文件的ID
     */
    String addFile(String treeId, AddFileDTO request);

    /**
     * 修改文件内容（覆盖）
     *
     * @param treeId  虚拟树ID
     * @param request 修改文件内容的请求
     */
    void updateFileContent(String treeId, UpdateFileDTO request);

    /**
     * 删除节点（文件或文件夹）
     *
     * @param treeId 虚拟树ID
     * @param nodeId 节点ID
     */
    void deleteNode(String treeId, String nodeId);

    /**
     * 重命名节点（文件或文件夹）
     *
     * @param treeId  虚拟树ID
     * @param nodeId  节点ID
     * @param newName 新名称
     */
    void renameNode(String treeId, String nodeId, String newName);

    /**
     * 导出虚拟文件树到指定目录
     *
     * @param treeId     虚拟树ID
     * @param exportPath 导出路径
     */
    void exportVirtualTree(String treeId, String exportPath);
}