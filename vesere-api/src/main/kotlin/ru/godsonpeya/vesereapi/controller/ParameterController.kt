package ru.godsonpeya.vesereapi.controller

import org.springframework.web.bind.annotation.*
import ru.godsonpeya.vesereapi.model.ParameterDto
import ru.godsonpeya.vesereapi.service.ParameterService

@RestController
@RequestMapping("/api/parameters")
class ParameterController(private val parameterService: ParameterService) {

    @GetMapping("")
    fun getAll(): List<ParameterDto> = parameterService.all

    @GetMapping("/{id}")
    fun getOneItem(@PathVariable id: Int): ParameterDto = parameterService.getOne(id)

    @PostMapping("")
    fun saveItem(@RequestBody param: ParameterDto): ParameterDto = parameterService.save(param)

    @PutMapping("/{id}")
    fun updateItem(@PathVariable id: Int, @RequestBody param: ParameterDto): ParameterDto =
        parameterService.update(id, param)

    @DeleteMapping("/{id}")
    fun deleteItem(@PathVariable id: Int) = parameterService.delete(id)
}

