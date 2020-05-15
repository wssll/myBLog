package com.sll.blog.service;

import com.sll.blog.po.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagsService {

    Tag saveTag(Tag tag);

    Tag getTag(Long id);

    Tag updateTag(Long id,Tag tag);

    void deleteTag(Long id);

    Page<Tag> listTag(Pageable pageable);

    List<Tag> listTag();

    List<Tag> listTag(String ids);

    List<Tag> listTagTop(Integer size);

    Tag getTagByName(String name);
}
