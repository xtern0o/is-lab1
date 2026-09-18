package org.example.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.PrePersist
import jakarta.persistence.Table
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import org.example.entity.enums.MovieGenre
import org.example.entity.enums.MpaaRating
import org.jetbrains.annotations.NotNull
import java.util.Date

@Entity
@Table(name = "movies")
class Movie(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    @field:Column(nullable = false, updatable = false)
    var id: Int? = null,

    @field:NotBlank
    @field:Column(nullable = false)
    var name: String = "",

    @field:ManyToOne(optional = false)
    @field:NotNull
    var coordinates: Coordinates? = null,

    @field:Column(nullable = false, updatable = false)
    var creationDate: Date? = null,

    @field:Positive
    @field:Column(nullable = false)
    var oscarsCount: Int = 1,

    @field:NotNull
    @field:Positive
    @field:Column(nullable = false)
    var budget: Int? = null,

    @field:Positive
    @field:Column(nullable = false)
    var totalBoxOffice: Int = 1,

    @field:Enumerated(EnumType.STRING)
    var mpaaRating: MpaaRating? = null,

    @field:ManyToOne(optional = false)
    @field:NotNull
    var director: Person? = null,

    @field:ManyToOne
    var screenwriter: Person? = null,

    @field:ManyToOne
    var operator: Person? = null,

    @field:Positive
    var length: Long? = null,

    @field:Positive
    @field:Column(nullable = false)
    var goldenPalmCount: Long = 1,

    @field:Enumerated(EnumType.STRING)
    var genre: MovieGenre? = null,
) {
    @PrePersist
    fun assignCreationDate() {
        if (creationDate == null) {
            creationDate = Date()
        }
    }
}
