package org.yuhan.ziyu.auth.service.impl;

import org.springframework.stereotype.Service;
import org.yuhan.ziyu.auth.dao.FileInfoRepository;
import org.yuhan.ziyu.auth.entity.FileInfo;
import org.yuhan.ziyu.auth.service.FileInfoService;

import javax.annotation.Resource;

/**
 * @author luoliang
 * @date 2018/6/20
 */
@Service
public class FileInfoServiceImpl implements FileInfoService {
    @Resource
    private FileInfoRepository fileInfoRepository;

    @Override
    public FileInfo addFileInfo(FileInfo fileInfo) {
        return fileInfoRepository.save(fileInfo);
    }
}
