package com.marvel.avengers.avengers_api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories(basePackages = ["com.marvel.avengers.avengers_api.resource.avenger"])
class AvengersApiApplication

fun main(args: Array<String>) {
    runApplication<AvengersApiApplication>(*args)
}
