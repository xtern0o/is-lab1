package org.example.service

import org.example.repository.LocationRepository
import org.example.repository.MovieRepository
import org.example.repository.PersonRepository
import org.springframework.stereotype.Service

@Service
class MovieService(
    private val movieRepository: MovieRepository,
    private val locationRepository: LocationRepository,
    private val personRepository: PersonRepository
) {

}