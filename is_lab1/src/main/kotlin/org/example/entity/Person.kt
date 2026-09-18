package org.example.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import org.example.entity.enums.Color
import org.example.entity.enums.Country

@Entity
@Table(name = "persons")
open class Person(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    @field:Column(nullable = false, updatable = false)
    var id: Int? = null,

    @field:NotBlank
    @field:Column(nullable = false)
    var name: String = "",

    @field:Enumerated(EnumType.STRING)
    var eyeColor: Color? = null,

    @field:NotNull
    @field:Enumerated(EnumType.STRING)
    @field:Column(nullable = false)
    var hairColor: Color? = null,

    @field:ManyToOne
    var location: Location? = null,

    @field:NotNull
    @field:Positive
    @field:Column(nullable = false)
    var height: Int? = null,

    @field:Enumerated(EnumType.STRING)
    var nationality: Country? = null,
)
