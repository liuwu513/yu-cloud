package org.yuhan.ziyu.auth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yuhan.ziyu.auth.entity.FileInfo;

/**
 * @author luoliang
 * @date 2018/6/20
 */
public interface FileInfoRepository extends JpaRepository<FileInfo, Long> {
}
