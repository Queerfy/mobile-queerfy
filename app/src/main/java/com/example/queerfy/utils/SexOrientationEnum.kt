package com.example.queerfy.utils

enum class SexOrientationEnum(val sexualOrientationName: String, val id: Int) {
    SELECT("Selecionar", 0),
    HETEROSEXUAL("Heterossexual", 1),
    GAY("Homossexual", 2),
    BISEXUAL("Bissexual", 3),
    DO_NOT_INFORM("Não informar",4 );

    companion object {
        fun toList(): List<String> = values().map { it.sexualOrientationName }

        fun idFromName(name: String?) = values().find { it.sexualOrientationName == name }?.id

        fun fromId(id: Int?) = values().find { it.id == id }
    }
}