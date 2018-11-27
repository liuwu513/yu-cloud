package org.yuhan.ziyu.auth.dao.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.yuhan.ziyu.auth.entity.Department;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 部门 mybatis持久层
 * Created by liuwu on 2018/9/19 0019.
 */
public interface DepartmentMapper extends BaseMapper<Department> {

    /**
     * 跟进父级部门ID获取下级部门
     *
     * @param ownPartId 所属部门ID
     * @param parentId  父级部门ID
     * @param dataScope 数据作用域
     * @return
     */
    List<Department> findDeptByParentId(@Param("ownPartId") String ownPartId, @Param("parentId")
            String parentId, @Param("dataScope") Integer dataScope);
}
