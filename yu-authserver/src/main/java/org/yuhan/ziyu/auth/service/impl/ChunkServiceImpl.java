package org.yuhan.ziyu.auth.service.impl;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.yuhan.ziyu.auth.dao.ChunkRepository;
import org.yuhan.ziyu.auth.entity.Chunk;
import org.yuhan.ziyu.auth.service.ChunkService;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author luoliang
 * @date 2018/6/21
 */
@Service
public class ChunkServiceImpl implements ChunkService {
    @Resource
    private ChunkRepository chunkRepository;

    @Override
    public void saveChunk(Chunk chunk) {
        chunkRepository.save(chunk);
    }

    @Override
    public boolean checkChunk(String identifier, Integer chunkNumber) {
        Specification<Chunk> specification = (Specification<Chunk>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("identifier"), identifier));
            predicates.add(criteriaBuilder.equal(root.get("chunkNumber"), chunkNumber));

            return criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
        };

        return chunkRepository.findOne(specification).orElse(null) == null;
    }

}
