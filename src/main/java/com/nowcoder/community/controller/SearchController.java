package com.nowcoder.community.controller;

import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.Page;
import com.nowcoder.community.entity.SearchResult;
import com.nowcoder.community.service.ElasticSearchService;
import com.nowcoder.community.service.LikeService;
import com.nowcoder.community.service.UserService;
import com.nowcoder.community.utils.CommunityConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xindong
 * @create 2022-05-23 23:22
 */
@Controller
public class SearchController implements CommunityConstant {

    private static final Logger logger = LoggerFactory.getLogger(SearchController.class);

    @Autowired
    private ElasticSearchService elasticSearchService;

    @Autowired
    private UserService userService;

    @Autowired
    private LikeService likeService;

    //搜索帖子，注意这里没用offset，用的current，所以需要手动计算每页偏移量
    @RequestMapping(path = "/search", method = RequestMethod.GET)
    public String search(String keyword, Page page, Model model) {
        try {
            SearchResult searchResult = elasticSearchService.searchDiscussPost(keyword, page.getOffset(), page.getLimit());
            List<Map<String, Object>> discussPosts = new ArrayList<>();
            List<DiscussPost> list = searchResult.getList();

            if (list != null) {
                for (DiscussPost post : list) {
                    Map<String, Object> map = new HashMap<>();
                    //封装帖子和作者和点赞数目
                    map.put("post", post);
                    map.put("user", userService.findUserById(post.getUserId()));
                    map.put("likeCount", likeService.findEntityLikeCount(ENTITY_POST, post.getId()));
                    discussPosts.add(map);
                }
            }

            model.addAttribute("discussPosts", discussPosts);
            model.addAttribute("keyword", keyword);

            //分页
            page.setPath("/search?keyword=" + keyword);
            page.setRows(searchResult.getTotal() == 0 ? 0 : (int) searchResult.getTotal());

        } catch (IOException e) {
            logger.error("系统出错，没有数据：" + e.getMessage());
        }

        return "site/search";
    }


}
