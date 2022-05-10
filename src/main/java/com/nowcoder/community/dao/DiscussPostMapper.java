package com.nowcoder.community.dao;

import com.nowcoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xindong
 * @create 2022-05-09 17:05
 */
@Mapper
@Repository
public interface DiscussPostMapper {

    //当userId有效时，显示当前用户的评论，当无效时分页显示所有评论
    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);

    //如果只有一个参数，并且在<if>里使用，则必须加别名(@Param)。
    //返回每个用户的评论条数
    int selectDiscussPostRows(@Param("userId") int userId);
}
