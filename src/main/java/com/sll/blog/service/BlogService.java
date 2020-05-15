package com.sll.blog.service;

import com.sll.blog.po.Blog;
import com.sll.blog.vo.BlogQuery;
import org.hibernate.jpa.criteria.expression.function.AggregationFunction.COUNT;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface BlogService {

    Blog getBlog(Long id);

    Blog getAndConvert(Long id);

    Blog saveBlog(Blog blog);

    Blog updateBlog(Long id,Blog blog);

    Page<Blog> listBlog(Pageable pageable,BlogQuery blog);

    Page<Blog> listBlog(Pageable pageable);

    Page<Blog> listBlog(Long tagId,Pageable pageable);

    Page<Blog> listBlog(String query,Pageable pageable);

    List<Blog> listRecommentBlogTop(Integer size);

    Map<String,List<Blog>> archivesBlog();

    Long countBlog();

    void deleteBlot(Long id);

}
