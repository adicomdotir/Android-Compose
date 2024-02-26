package app.aec.data.mappers

import app.aec.data.network.model.BlogDTO
import app.aec.data.network.model.OwnerDTO
import app.aec.domain.model.Blog
import app.aec.domain.model.Owner

fun List<BlogDTO>.toDomain(): List<Blog> {
    return map {
        Blog(
            id = it.id ?: "",
            image = it.image ?: "",
            likes = it.likes ?: 0,
            owner = it.owner?.toDomain() ?: Owner("", "", "", "", ""),
            publishDate = it.publishDate ?: "",
            tags = it.tags ?: emptyList(),
            text = it.text ?: ""
        )
    }
}

fun OwnerDTO.toDomain(): Owner {
    return Owner(
        firstName ?: "", id ?: "", lastName ?: "", picture ?: "", title ?: ""
    )
}