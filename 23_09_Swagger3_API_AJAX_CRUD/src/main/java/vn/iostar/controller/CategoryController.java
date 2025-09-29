package vn.iostar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vn.iostar.entity.Category;
import vn.iostar.service.CategoryService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/admin/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String listCategories(ModelMap model,
                                 @RequestParam(name = "name", required = false) String name,
                                 @RequestParam(name = "page", defaultValue = "1") int page,
                                 @RequestParam(name = "size", defaultValue = "5") int size) {
        
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("name"));
        
        Page<Category> resultPage;
        if (StringUtils.hasText(name)) {
            resultPage = categoryService.findByNameContaining(name, pageable);
            model.addAttribute("name", name);
        } else {
            resultPage = categoryService.findAll(pageable);
        }

        int totalPages = resultPage.getTotalPages();
        if (totalPages > 0) {
            int start = Math.max(1, page - 2);
            int end = Math.min(page + 2, totalPages);
            List<Integer> pageNumbers = IntStream.rangeClosed(start, end)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("categoryPage", resultPage);
        return "admin/categories/list";
    }

    @GetMapping("/add")
    public String add(ModelMap model) {
        model.addAttribute("category", new Category());
        return "admin/categories/addOrEdit";
    }

    @PostMapping("/saveOrUpdate")
    public ModelAndView saveOrUpdate(ModelMap model, @ModelAttribute("category") Category category) {
        categoryService.save(category);
        model.addAttribute("message", "Category is saved!");
        return new ModelAndView("redirect:/admin/categories", model);
    }
    
    @GetMapping("/edit/{categoryId}")
    public ModelAndView edit(ModelMap model, @PathVariable("categoryId") Long categoryId) {
        Optional<Category> opt = categoryService.findById(categoryId);
        if (opt.isPresent()) {
            model.addAttribute("category", opt.get());
            return new ModelAndView("admin/categories/addOrEdit", model);
        } else {
            model.addAttribute("message", "Category is not found!");
            return new ModelAndView("redirect:/admin/categories", model);
        }
    }

    @GetMapping("/delete/{categoryId}")
    public ModelAndView delete(ModelMap model, @PathVariable("categoryId") Long categoryId) {
        categoryService.deleteById(categoryId);
        model.addAttribute("message", "Category is deleted!");
        return new ModelAndView("redirect:/admin/categories", model);
    }
}