package org.example.controller

import jakarta.validation.Valid
import org.example.dto.movie.request.AwardOscarsByLengthRequest
import org.example.dto.movie.response.MovieResponse
import org.example.dto.movie.request.MovieUpsertRequest
import org.example.dto.movie.response.AwardOscarsResponse
import org.example.service.MovieService
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
@RequestMapping("/api/movies")
class MovieController(
    private val movieService: MovieService,
) {
    @GetMapping
    fun getPage(
        @RequestParam(required = false) name: String?,
        @PageableDefault(size = 20, sort = ["id"])
        pageable: Pageable,
    ): Page<MovieResponse> =
        movieService.getPage(name, pageable)

    @GetMapping("/{id}")
    fun getById(
        @PathVariable id: Int,
    ): MovieResponse =
        movieService.getById(id)

    @PostMapping
    fun create(
        @Valid @RequestBody request: MovieUpsertRequest,
    ): ResponseEntity<MovieResponse> =
        ResponseEntity
            .status(HttpStatus.CREATED)
            .body(movieService.create(request))

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Int,
        @Valid @RequestBody request: MovieUpsertRequest,
    ): MovieResponse =
        movieService.update(id, request)

    @DeleteMapping("/{id}")
    fun delete(
        @PathVariable id: Int,
    ): ResponseEntity<Void> {
        movieService.delete(id)

        return ResponseEntity.noContent().build()
    }

    @GetMapping("/search/{name}")
    fun getByContainsName(
        @PathVariable name: String
    ): List<MovieResponse> =
        movieService.findByName(name)

    @GetMapping("/golden-palm-count-less-than/{goldenPalmCount}")
    fun getByGoldenPalmCountLessThan(
        @RequestParam goldenPalmCount: Long,
    ): List<MovieResponse> =
        movieService.findByGoldenPalmCountLessThan(goldenPalmCount)

    @GetMapping("/without-oscars")
    fun getWithoutOscars(): List<MovieResponse> =
        movieService.findWithoutOscars()

    @PostMapping("/actions/award-oscars")
    fun awardOscars(
        @RequestBody request: AwardOscarsByLengthRequest,
    ): AwardOscarsResponse =
        movieService.awardOscars(request)

}
