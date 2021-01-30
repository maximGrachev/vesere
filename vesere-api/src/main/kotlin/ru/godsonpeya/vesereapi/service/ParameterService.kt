package ru.godsonpeya.vesereapi.service

import org.springframework.stereotype.Service
import ru.godsonpeya.atmosphere.exceptions.ResourceNotFoundException
import ru.godsonpeya.vesereapi.model.ParameterDto
import ru.godsonpeya.vesereapi.repository.CategoryRepository
import ru.godsonpeya.vesereapi.repository.ParameterRepository
import ru.godsonpeya.vesereapi.utils.fromDtoToEntity
import ru.godsonpeya.vesereapi.utils.fromEntityToDto

@Service
class ParameterService (
    private val parameterRepository: ParameterRepository
    ) {
    val all: List<ParameterDto> = parameterRepository.findAll().map {
        it.fromEntityToDto()
    }

    fun getOne(id: Int): ParameterDto {
        val parameter = parameterRepository.findById(id).orElseThrow { ResourceNotFoundException("Item", "id", id) }
        return parameter.fromEntityToDto()
    }

    fun save(parameter: ParameterDto): ParameterDto {
        return parameterRepository.saveAndFlush(parameter.fromDtoToEntity()).fromEntityToDto()
    }

    fun update(id: Int, parameter: ParameterDto): ParameterDto {
        val param = getOne(id).fromDtoToEntity()
        param.apply {
            categoryId = parameter.parameterCategoryId
            name = parameter.parameterName
            value = parameter.parameterValue
        }
        return parameterRepository.saveAndFlush(param).fromEntityToDto()
    }

    fun delete(id: Int): String {
        return try {
            parameterRepository.deleteById(id)
            "Запись удалена"
        } catch (e: Exception) {
            "Ошибка при удалении "
        }
    }
}
