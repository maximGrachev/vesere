package ru.godsonpeya.vesereapi.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "parameter")
class Parameter(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,

    @Column(name = "category_id")
    var categoryId: Int? = null,

    @Column(name = "name")
    var name: String? = null,

    @Column(name = "value")
    var value: String? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(insertable = false, updatable = false)
    var category: Category?=null

)
