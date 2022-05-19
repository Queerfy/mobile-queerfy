package com.example.queerfy.utils

enum class GenderIdentityEnum(val genderIdentityName: String, val id: Int) {
    SELECT("Selecionar", 0),
    MAN("Homem", 1),
    WOMAM("Mulher", 2),
    NON_BINARY("Não-binário", 3),
    DO_NOT_INFORM("Não informar", 4);

    companion object {
        fun toList(): List<String> = values().map { it.genderIdentityName }

        fun idFromName(name: String?) = values().find { it.genderIdentityName == name }?.id

        fun fromId(id: Int?) = values().find { it.id == id }
    }
}