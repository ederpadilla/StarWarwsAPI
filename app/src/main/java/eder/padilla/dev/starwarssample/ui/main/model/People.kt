package eder.padilla.dev.starwarssample.ui.main.model

data class People (

    var name: String? = null,

    var height: String? = null,

    var mass: String? = null,

    var hair_color: String? = null,

    var skin_color: String? = null,

    var eye_color: String? = null,

    var birth_year: String? = null,

    var gender: String? = null,

    var homeworld: String? = null,

    var films: List<String>? = null,

    var species: List<String>? = null,

    var vehicles: List<String>? = null,

    var starships: List<String>? = null,

    var created: String? = null,

    var edited: String? = null,

    var url: String? = null

)