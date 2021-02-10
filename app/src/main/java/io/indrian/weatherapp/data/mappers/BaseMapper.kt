package io.indrian.weatherapp.data.mappers

interface BaseMapper<E, M> {
    fun toModel(entity: E): M
}