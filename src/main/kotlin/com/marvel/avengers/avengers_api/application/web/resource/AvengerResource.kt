package com.marvel.avengers.avengers_api.application.web.resource

import com.marvel.avengers.avengers_api.application.web.resource.request.AvengerRequest
import com.marvel.avengers.avengers_api.application.web.resource.response.AvengerResponse
import com.marvel.avengers.avengers_api.domain.avenger.AvengerRepository
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

private const val API_PATH: String = "/v1/api/avenger"

@RestController
@RequestMapping(API_PATH)
class AvengerResource(
    @Autowired private val repository: AvengerRepository
) {

    @GetMapping
    fun getAvengers(): ResponseEntity<List<AvengerResponse>> =
        repository.getAvengers().map { AvengerResponse.from(it) }.let { ResponseEntity.ok().body(it) }

    @GetMapping("{id}/detail")
    fun getAvengerDetails(@PathVariable("id") id: Long): ResponseEntity<AvengerResponse> =
        repository.getDetails(id)?.let { ResponseEntity.ok().body(AvengerResponse.from(it)) }
            ?: ResponseEntity.notFound().build()


    @PostMapping
    fun createAvenger(@Valid @RequestBody request: AvengerRequest): ResponseEntity<AvengerResponse> =
        request.toAvenger().run {
            repository.create(this)
        }.let {
            ResponseEntity.created(URI("$API_PATH/${it.id}")).body(AvengerResponse.from(it))
        }

    @PutMapping("{id}")
    fun updateAvenger(@Valid @RequestBody request: AvengerRequest, @PathVariable("id") id: Long) =
        repository.getDetails(id)?.let {
            AvengerRequest.to(it.id, request).apply {
                repository.update(this)
            }.let { avenger ->
                ResponseEntity.accepted().body(AvengerResponse.from(avenger))
            }
        } ?: ResponseEntity.notFound().build<Unit>()

    @DeleteMapping("{id}")
    fun deleteAvenger(@PathVariable("id") id: Long) =
        repository.delete(id).let {
            ResponseEntity.accepted().build<Unit>()
        }

}