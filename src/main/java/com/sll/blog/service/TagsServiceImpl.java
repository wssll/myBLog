package com.sll.blog.service;

import com.sll.blog.NotFoundException;
import com.sll.blog.dao.BlogRepository;
import com.sll.blog.dao.TagRepository;
import com.sll.blog.po.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder.In;
import java.util.ArrayList;
import java.util.List;

@Service
public class TagsServiceImpl implements TagsService{

    @Autowired
    private TagRepository tagRepository;

    @Transactional
    @Override
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Transactional
    @Override
    public Tag getTag(Long id) {
        return tagRepository.findOne(id);
    }

    @Transactional
    @Override
    public Tag updateTag(Long id, Tag tag) {
        Tag t = tagRepository.findOne(id);
        if(t == null){
            throw new NotFoundException("没有该条记录");
        }
        BeanUtils.copyProperties(tag,t);
        return tagRepository.save(t);
    }

    @Transactional
    @Override
    public void deleteTag(Long id) {
        tagRepository.delete(id);
    }

    @Transactional
    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    @Override
    public List<Tag> listTag() {
        return tagRepository.findAll();
    }

    @Override
    public List<Tag> listTag(String ids) {
        return tagRepository.findAll(convertToList(ids));
    }

    @Override
    public List<Tag> listTagTop(Integer size) {
        Sort sort = new Sort(Direction.DESC,"blogs.size");
        Pageable pageable = new PageRequest(0,size,sort);
        return tagRepository.findTop(pageable);
    }

    private List<Long> convertToList(String ids){
        List<Long> list = new ArrayList<>();
        if(!"".equals(ids)&&ids!=null){
            String[] idarr = ids.split(",");
            for(String id:idarr){
                list.add(Long.valueOf(id));
            }
        }
        return list;
    }

    @Override
    public Tag getTagByName(String name) {
        return tagRepository.getByName(name);
    }
}
