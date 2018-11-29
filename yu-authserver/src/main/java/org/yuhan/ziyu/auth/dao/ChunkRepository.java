package org.yuhan.ziyu.auth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.yuhan.ziyu.auth.entity.Chunk;

/**
 * @author luoliang
 * @date 2018/6/21
 */
public interface ChunkRepository extends JpaRepository<Chunk, Long>, JpaSpecificationExecutor<Chunk> {
}
