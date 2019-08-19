package com.itheima.dao;

import com.itheima.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author 王磊
 * @Date 2019/8/15/015
 */
public interface RoleMapper {

    /**
     * 根据用户ID查询用户所拥有的角色信息
     * @param userId
     * @return
     */
    @Select("select * from ROLE where ID in(select ROLEID from USERINFO_ROLE where  USERID = #{userId})")
    @Results({
            @Result(id = true,property = "id",column = "ID"),
            @Result(property = "roleName",column = "ROLENAME"),
            @Result(property = "roleDesc",column = "ROLEDESC"),
            @Result(property = "permissions",column = "ID",many = @Many(select = "com.itheima.dao.PermissionMapper.findByRoleId")),
    })
    public List<Role> findByUserId(String userId);


    /**
     * 查询所有角色信息
     * @return
     */
    @Select("select * from ROLE")
    List<Role> findAll();

    /**
     * 添加角色到数据库
     * @param role
     */
    @Insert("insert into ROLE values(sys_guid(),#{roleName},#{roleDesc})")
    void add(Role role);

    /**
     * 查询用户未关联的角色信息
     * @param userId
     * @return
     */
    //@Select("select r.* from ROLE r left join (select * from USERINFO_ROLE where USERID = #{userId}) t on r.ID = t.ROLEID  where t.ROLEID is  null")
    @Select("select r.* , NVL2(T.ROLEID,1,0) selected from ROLE r left join ( select * from USERINFO_ROLE where  USERID = #{userId}  ) t on  r.ID = t.ROLEID")
    List<Role> findAdditableRoles(String userId);

    /**
     *向UserInfo_role表中插入数据(用户关联角色)
     * 如果数据访问层接口中需要传递多个参数 : mybatis获取参数值的方案有二种
     * 第一种 :  param1 param2   , params固定的,后面的1 ,2 ,3 代表参数的位置
     * 第二种 : 使用 @Param("aa") 注解,为参数娶一个名称 , 参数名称是什么,在SQL语句中就应该使用取的参数名称取值
     * @param userId
     * @param roleId
     *
     * Map : {aa:参数值,bb:参数值}
     */
    @Insert("insert into USERINFO_ROLE values(#{userId},#{roleId})")
    void addRole2User(@Param("userId") String userId, @Param("roleId") String roleId);

    /**
     * 删除用户关联的角色信息
     * @param userId
     */
    @Delete("delete from USERINFO_ROLE where userid = #{userId}")
    void deleteRolesFromUser(String userId);
}
