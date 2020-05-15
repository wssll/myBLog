package com.sll.blog.web.admin;

import com.sll.blog.po.Tag;
import com.sll.blog.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sun.rmi.runtime.Log;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagsService tagsService;

    @GetMapping("/tags")
    public String tags(@PageableDefault(size = 3,sort = {"id"},direction = Direction.DESC) Pageable pageable, Model model){
        Page<Tag> tags = tagsService.listTag(pageable);
        model.addAttribute("page",tags);
        return "admin/tags";
    }

    @GetMapping("/tags/input")
    public String input(Model model){
        Tag tag = new Tag();
        model.addAttribute("tag",tag);
        return "admin/tags-input";
    }

    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Long id,Model model){
        model.addAttribute("tag",tagsService.getTag(id));
        return "admin/tags-input";
    }

    @PostMapping("/tags")
    public String post(@Valid Tag tag, BindingResult result, RedirectAttributes redirectAttributes){
        Tag tag1 = tagsService.getTagByName(tag.getName());
        if (tag1 != null){
            result.rejectValue("name","nameError","不能重复添加标签");
        }
        if(result.hasErrors()){
            return "admin/tags-input";
        }
        Tag t = tagsService.saveTag(tag);
        if (t==null){
            redirectAttributes.addFlashAttribute("message","添加失败");
        }else {
            redirectAttributes.addFlashAttribute("message","添加成功");
        }
        return "redirect:/admin/tags";
    }

    @PostMapping("/tags/{id}")
    public String editPost(@Valid Tag tag,BindingResult result,@PathVariable Long id,
                           RedirectAttributes redirectAttributes){
        Tag tag1 = tagsService.getTagByName(tag.getName());
        if (tag1 != null){
            result.rejectValue("name","nameError","不能添加重复标签");
        }
        if (result.hasErrors()){
            return "admin/tags-input";
        }
        Tag t =tagsService.updateTag(id,tag);
        if (t == null){
            redirectAttributes.addFlashAttribute("message","更新失败");
        }else {
            redirectAttributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/tags";
    }

    @GetMapping("tags/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        tagsService.deleteTag(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/tags";
    }

}
