package com.example.rental.vo;

import com.example.rental.entity.Permission;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class RolePermissionVo {
    private Object[] checkedList;

    private List<Permission> permissionList;


}
