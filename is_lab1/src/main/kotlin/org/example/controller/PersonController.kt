package org.example.controller

import jakarta.validation.Valid
import org.example.dto.person.request.PersonUpsertRequest
import org.example.dto.person.response.PersonResponse
import org.example.repository.PersonRepository
import org.example.service.PersonService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/persons")
class PersonController(
    private val personService: PersonService,
) {
    @GetMapping
    fun getPage(
        @RequestParam(required = false) name: String?,
        @PageableDefault(size = 20, sort = ["id"])
        pageable: Pageable,
    ): Page<PersonResponse> =
        personService.getPage(name, pageable)

    @GetMapping("/{id}")
    fun getById(
        @PathVariable id: Int,
    ): PersonResponse =
        personService.getById(id)

    @PostMapping
    fun create(
        @Valid @RequestBody request: PersonUpsertRequest,
    ): ResponseEntity<PersonResponse> =
        ResponseEntity
            .status(HttpStatus.CREATED)
            .body(personService.create(request))

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Int,
        @Valid @RequestBody request: PersonUpsertRequest,
    ): PersonResponse =
        personService.update(id, request)

    @DeleteMapping("/{id}")
    fun delete(
        @PathVariable id: Int,
    ): ResponseEntity<Void> {
        personService.delete(id)

        return ResponseEntity.noContent().build()
    }
}
