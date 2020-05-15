package com.sll.blog.web;

import com.sll.blog.NotFoundException;
import com.sll.blog.service.BlogService;
import com.sll.blog.service.TagsService;
import com.sll.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class indexController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagsService tagsService;

    @GetMapping("/")
    public String index(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        model.addAttribute("page",blogService.listBlog(pageable));
        model.addAttribute("types", typeService.listTypeTop(6));
        model.addAttribute("tags", tagsService.listTagTop(10));
        model.addAttribute("recommendBlogs", blogService.listRecommentBlogTop(8));
        return "index";
    }

    @PostMapping("/search")
    public String search(@PageableDefault(size = 8, sort = {"updateTime"},direction = Direction.DESC) Pageable pageable,String query,Model model){
        model.addAttribute("page",blogService.listBlog("%"+query+"%",pageable));
        model.addAttribute("query",query);
        return "search";
    }

    @GetMapping("/blog")
    public String blog(){
        return "blog";
    }

    @GetMapping("/blog/{id}")
    public String blogcontent(@PathVariable Long id,Model model){
        model.addAttribute("blog",blogService.getAndConvert(id));
        return "blog";
    }

    @GetMapping("/footer/newblog")
    public String newblogs(Model model) {
        model.addAttribute("newblogs", blogService.listRecommentBlogTop(3));
        return "_fragments :: newblogList";
    }
}
