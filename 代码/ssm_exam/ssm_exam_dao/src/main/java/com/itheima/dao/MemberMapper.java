package com.itheima.dao;

import com.itheima.domain.Member;
import org.apache.ibatis.annotations.Select;

/**
 * @Author 王磊
 * @Date 2019/8/15/015
 */
public interface MemberMapper {

    /**
     * 根据会员ID查询会员信息
     * @param id
     * @return
     */
    @Select("select *from MEMBER where  ID = #{id}")
    public Member findById(String id);
}
