package com.example.rental.utils;

import com.example.rental.entity.Dept;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class DeptTreeUtils {

    /**
     * 根据部门列表和父部门ID构建部门树结构。
     *
     * @param deptList 部门列表，包含所有部门信息。
     * @param pid 父部门ID，用于筛选出指定父部门下的子部门。
     * @return 构建好的部门树结构列表。
     */
    public static List<Dept> buildDeptTree(List<Dept> deptList, Integer pid) {
        List<Dept> deptTree = new ArrayList<>();

        // 从deptList中筛选出父部门ID为pid的部门，并递归构建其子部门树
        Optional.ofNullable(deptList).orElse(new ArrayList<>())
                .stream()
                .filter(dept -> dept != null && Objects.equals(dept.getPid(), pid))
                .forEach(dept -> {
                    Dept dept1 = new Dept();
                    // 复制当前部门的属性到新对象dept1中
                    BeanUtils.copyProperties(dept, dept1);
                    // 递归构建dept1的子部门树
                    dept1.setChildren(buildDeptTree(deptList, dept.getId()));
                    deptTree.add(dept1);
                });
        return deptTree;
    }

}
