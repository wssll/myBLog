package com.sll.blog.dao;

import com.sll.blog.po.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag,Long> {
    Tag getByName(String name);

    @Query("select t from Tag t")
    List<Tag> findTop(Pageable pageable);

}
