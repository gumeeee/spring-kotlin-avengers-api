package com.marvel.avengers.avengers_api.resource.avenger

import com.marvel.avengers.avengers_api.domain.avenger.Avenger
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "avenger")
data class AvengerEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    @Column(nullable = false)
    val nick: String,

    @Column(nullable = false)
    val person: String,

    @Column(nullable = false)
    val description: String?,

    @Column(nullable = false)
    val history: String?
) {
    fun toAvenger() = Avenger(id, nick, person, description, history)

    companion object {
        fun from(avenger: Avenger) =
            AvengerEntity(
                id = avenger.id,
                nick = avenger.nick,
                person = avenger.person,
                description = avenger.description,
                history = avenger.history,
            )
    }
}
