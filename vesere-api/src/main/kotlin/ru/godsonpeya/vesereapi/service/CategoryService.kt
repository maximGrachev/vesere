package ru.godsonpeya.vesereapi.service

import org.springframework.stereotype.Service
import ru.godsonpeya.atmosphere.exceptions.ResourceNotFoundException
import ru.godsonpeya.vesereapi.model.CategoryDto
import ru.godsonpeya.vesereapi.repository.CategoryRepository
import ru.godsonpeya.vesereapi.utils.fromDtoToEntity
import ru.godsonpeya.vesereapi.utils.fromEntityToDto

@Service
class CategoryService(
    private val categoryRepository: CategoryRepository
) {
    val all: List<CategoryDto> = categoryRepository.findAll().map {
        it.fromEntityToDto()
    }

    fun getOne(id: Int): CategoryDto {
        val category = categoryRepository.findById(id).orElseThrow { ResourceNotFoundException("Item", "id", id) }
        return category.fromEntityToDto()
    }

    fun save(category: CategoryDto): CategoryDto {
        return categoryRepository.saveAndFlush(category.fromDtoToEntity()).fromEntityToDto()
    }


    fun update(id: Int, category: CategoryDto): CategoryDto {
        val cat = getOne(id).fromDtoToEntity()
        cat.apply {
            name = category.categoryName
            parameters = category.categoryParameters
        }
        return categoryRepository.saveAndFlush(cat).fromEntityToDto()
    }

    fun delete(id: Int): String {
        return try {
            categoryRepository.deleteById(id)
            "Запись удалена"
        } catch (e: Exception) {
            "Ошибка при удалении "
        }
    }
}
