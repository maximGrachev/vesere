package ru.godsonpeya.vesereapi.controller

import org.springframework.web.bind.annotation.*
import ru.godsonpeya.vesereapi.model.CategoryDto
import ru.godsonpeya.vesereapi.service.CategoryService

@RestController
@RequestMapping("/api/categories")
class CategoryController(private val categoryService: CategoryService) {
    @GetMapping("")
    fun getAll(): List<CategoryDto> = categoryService.all

    @GetMapping("/{id}")
    fun getOneItem(@PathVariable id: Int): CategoryDto = categoryService.getOne(id)

    @PostMapping("")
    fun saveItem(@RequestBody cat: CategoryDto): CategoryDto = categoryService.save(cat)

    @PutMapping("/{id}")
    fun updateItem(@PathVariable id: Int, @RequestBody cat: CategoryDto): CategoryDto =
        categoryService.update(id, cat)

    @DeleteMapping("/{id}")
    fun deleteItem(@PathVariable id: Int) = categoryService.delete(id)
}
