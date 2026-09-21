package org.example.controller

import jakarta.validation.Valid
import org.example.dto.location.request.LocationUpsertRequest
import org.example.dto.location.response.LocationResponse
import org.example.entity.Location
import org.example.service.LocationService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/location")
class LocationController(
    private val locationService: LocationService
) {

    @GetMapping
    fun getPage(
        @PageableDefault(page = 0, size = 10, sort = ["id"])
        pageable: Pageable,
        @RequestParam(required = false) nameFilter: String?
    ): Page<LocationResponse> =
        locationService.getPage(nameFilter, pageable)

    @GetMapping("/{id}")
    fun getById(
        @PathVariable id: Int,
    ): LocationResponse =
        locationService.getById(id)

    @PostMapping
    fun create(
        @Valid @RequestBody request: LocationUpsertRequest
    ): ResponseEntity<LocationResponse> =
        ResponseEntity.ok(locationService.create(request))

    @PostMapping("/{id}")
    fun update(
        @PathVariable id: Int,
        @Valid @RequestBody request: LocationUpsertRequest
    ): ResponseEntity<LocationResponse> =
        ResponseEntity.ok(locationService.update(id, request))

    @DeleteMapping("/{id}")
    fun delete(
        @PathVariable id: Int,
    ): ResponseEntity<Void> {
        locationService.delete(id)

        return ResponseEntity.noContent().build()
    }

}