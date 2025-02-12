package com.marvel.avengers.avengers_api.resource.avenger

import com.marvel.avengers.avengers_api.domain.avenger.Avenger
import com.marvel.avengers.avengers_api.domain.avenger.AvengerRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.util.*

@Component
class AvengerRepositoryImpl(
    private val repository: AvengerEntityRepository
) : AvengerRepository {
    override fun getDetails(id: Long): Avenger? = repository.findByIdOrNull(id)?.toAvenger()

    override fun getAvengers(): List<Avenger> =
        repository.findAll().map { it.toAvenger() }

    override fun create(avenger: Avenger): Avenger =
        repository.save(AvengerEntity.from(avenger)).toAvenger()

    override fun delete(id: Long) = repository.deleteById(id)

    override fun update(avenger: Avenger): Avenger =
        repository.save(AvengerEntity.from(avenger)).toAvenger()
}