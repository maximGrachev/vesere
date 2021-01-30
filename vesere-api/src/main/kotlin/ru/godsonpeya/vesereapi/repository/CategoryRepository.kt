package ru.godsonpeya.vesereapi.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.godsonpeya.vesereapi.entity.Category

interface CategoryRepository :JpaRepository<Category,Int>
