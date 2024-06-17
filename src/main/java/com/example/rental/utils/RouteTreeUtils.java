package com.example.rental.utils;

import com.example.rental.entity.Permission;
import com.example.rental.vo.RouteVo;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class RouteTreeUtils {
    /**
     * 根据权限列表和父ID构建路由树。
     *
     * @param permissionList 权限列表，包含所有路由信息。
     * @param pid 父ID，用于筛选当前层级的路由。
     * @return 返回路由树列表，每个路由包含路径、名称等信息以及可能的子路由。
     */
    public static List<RouteVo> buildRouteTree(List<Permission> permissionList, int pid) {
        List<RouteVo> routeVoList = new ArrayList<>();

        // 使用Optional避免空指针异常，初始化为空列表
        //从权限列表中筛选出父ID为pid的权限，并为每个权限构建一个RouteVo实例
        Optional.ofNullable(permissionList).orElse(new ArrayList<>())
                .stream()
                .filter(permission -> permission != null && Objects.equals(permission.getPid(), pid)) // 筛选出父ID为pid的权限
                .forEach(permission -> {
                    RouteVo routeVo = new RouteVo();
                    // 设置路由的基本信息
                    routeVo.setPath(permission.getRoutePath());//设置路由路径
                    routeVo.setName(permission.getRouteName());//设置路由名称

                    // 根据权限的父ID判断是否为根路由
                    if (Objects.equals(permission.getPid(), 0)) {
                        routeVo.setComponent("Layout");
                        routeVo.setAlwaysShow(true);
                    } else {
                        routeVo.setComponent(permission.getRouteUrl());
                        routeVo.setAlwaysShow(false);
                    }

                    // 设置路由的元信息，如标签、图标、权限码等
                    routeVo.setMeta(routeVo.new Meta(permission.getPermissionLabel(),
                            permission.getIcon(),
                            permission.getPermissionCode().split(",")));

                    // 递归构建子路由树
                    List<RouteVo> children = buildRouteTree(permissionList, permission.getId());
                    routeVo.setChildren(children);

                    routeVoList.add(routeVo);
                });
        return routeVoList;

        /*List<RouteVo> routeVoList = new ArrayList<>();
        if (permissionList == null){
            return routeVoList;
        }

        for (Permission permission : permissionList) {
            if (permission != null && Objects.equals(permission.getPid(), pid)) {
                RouteVo routeVo = new RouteVo();
                routeVo.setPath(permission.getRoutePath());
                routeVo.setName(permission.getRouteName());
                if (Objects.equals(permission.getPid(), 0)) {
                    routeVo.setComponent("Layout");
                    routeVo.setAlwaysShow(true);
                } else {
                    routeVo.setComponent(permission.getRouteUrl());
                    routeVo.setAlwaysShow(false);
                }
                routeVo.setMeta(routeVo.new Meta(permission.getPermissionLable(),
                        permission.getIcon(),
                        permission.getPermissionCode().split(",")));

                // 递归构建子路由树
                List<RouteVo> children = buildRouteTree(permissionList, permission.getId());
                routeVo.setChildren(children);

                routeVoList.add(routeVo);

            }
        }

        return routeVoList;*/
    }

    /**
     * 根据权限列表和父权限ID构建权限树。
     * 该方法通过递归方式，将具有相同父权限ID的权限项组织成树结构。
     * @param permissionList 权限列表，包含所有权限项。
     * @param pid 父权限ID，用于查找并组织子权限项。
     * @return 构建完成的权限树列表。
     */
    public static List<Permission> buildPermissionTree(List<Permission> permissionList, int pid) {
        List<Permission> permissionTree = new ArrayList<>();

        // 从权限列表中筛选出父权限ID为pid的权限项
        Optional.ofNullable(permissionList).orElse(new ArrayList<>())
                .stream()
                .filter(permission -> permission != null && Objects.equals(permission.getPid(), pid))
                .forEach(permission -> {
                    Permission permissionTreeNode = new Permission();
                    // 复制筛选出的权限项的属性到新的权限节点
                    BeanUtils.copyProperties(permission, permissionTreeNode);
                    // 递归构建该权限节点的子权限树
                    permissionTreeNode.setChildren(buildPermissionTree(permissionList, permission.getId()));
                    // 将构建好的权限节点添加到权限树列表
                    permissionTree.add(permissionTreeNode);
                });
        return permissionTree;
    }


}
