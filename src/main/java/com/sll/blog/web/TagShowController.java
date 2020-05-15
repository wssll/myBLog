package com.sll.blog.web;

import com.sll.blog.po.Blog;
import com.sll.blog.po.Tag;
import com.sll.blog.service.BlogService;
import com.sll.blog.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TagShowController {

    @Autowired
    private TagsService tagsService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/tags/{id}")
    public String tags(@PageableDefault(size = 8,sort = {"updateTime"},direction = Direction.DESC)Pageable pageable, @PathVariable Long id, Model model){
        List<Tag> tags = tagsService.listTagTop(10000);
        model.addAttribute("tags",tags);
        if (id == -1 ){
            id = tags.get(0).getId();
        }
        Blog blog = new Blog();
        model.addAttribute("page",blogService.listBlog(id,pageable));
        model.addAttribute("activeTagId",id);
        return "tags";
    }

}
