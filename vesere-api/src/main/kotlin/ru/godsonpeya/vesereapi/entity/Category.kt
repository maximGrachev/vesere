package ru.godsonpeya.vesereapi.entity

import javax.persistence.*

@Entity
@Table(name = "category")
data class Category(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,

    @Column(name = "name")
    var name: String? = null,

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "category", cascade = [CascadeType.ALL])
    var parameters: MutableList<Parameter?>? = null

)
