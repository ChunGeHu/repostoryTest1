package com.itheima.dao;

import com.itheima.domain.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author 王磊
 * @Date 2019/8/16/016
 */
public interface PermissionMapper {

    /**
     * 根据角色ID查询权限信息
     * @param roleId
     * @return
     */
    @Select("select * from PERMISSION where ID in (select PERMISSIONID from ROLE_PERMISSION where  ROLEID = #{roleId})")
    public List<Permission> findByRoleId(String roleId);


    /**
     * 查询所有权限信息
     * @return
     */
    @Select("select * from PERMISSION")
    List<Permission> findAll();

    /**
     * 添加权限信息
     * @param permission
     */
    @Insert("insert into PERMISSION values(sys_guid(),#{permissionName},#{url})")
    void add(Permission permission);

    /**
     * 查询所有权限信息 , 包含角色关联权限状态
     * @param roleId
     * @return
     */
    @Select("select p.* ,NVL2(T.ROLEID,1,0) selected from PERMISSION p left join (select * from ROLE_PERMISSION where ROLEID = #{roleId}) t on  p.ID = t.PERMISSIONID")
    List<Permission> findAdditablePermission(String roleId);

    /**
     * 删除角色关联的权限
     * @param roleId
     */
    @Delete("delete from ROLE_PERMISSION where ROLEID = #{roleId}")
    void deletePermissionFromRole(String roleId);

    /**
     * 给角色添加权限
     * @param roleId
     * @param permissionId
     */
    @Insert("insert into ROLE_PERMISSION values(#{permissionId},#{roleId})")
    void addPermisssion2role(@Param("roleId") String roleId,@Param("permissionId") String permissionId);

    /**
     * 查询用户第一层级菜单数据
     * @param username
     * @return
     */
    @Select("select * from PERMISSION where ID in(\n" +
            "  select\n" +
            "      p.PID\n" +
            "  from\n" +
            "      PERMISSION p , ROLE_PERMISSION rp , ROLE r ,USERINFO_ROLE ur , USERINFO u\n" +
            "  where\n" +
            "    p.ID = rp.PERMISSIONID and rp.ROLEID = r.ID and r.ID = ur.ROLEID and ur.USERID = u.ID and u.USERNAME = #{username}\n" +
            ")")
    List<Permission> findMenus(String username);

    /**
     * 根据用户名及父分类ID查询子分类
     * @param username
     * @param id
     * @return
     */
    @Select("select * from PERMISSION where PID = #{id}  and  ID in (\n" +
            "  select\n" +
            "    rp.PERMISSIONID\n" +
            "  from\n" +
            "    ROLE_PERMISSION rp , ROLE r ,USERINFO_ROLE ur , USERINFO u\n" +
            "  where\n" +
            "    rp.ROLEID = r.ID and r.ID = ur.ROLEID and ur.USERID = u.ID and u.USERNAME = #{username}\n" +
            ")")
    List<Permission> findChildrenByUsernameAndPid( @Param("username") String username, @Param("id")  String id);
}
