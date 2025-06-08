package com.fastcampus.fcboard.controller.dto

data class PostDetailResponse(
    val id: Long,
    val title: String,
    val content: String,
    val createdBy: String,
    val createdAt: String,
    val comments: List<CommentResonse> = emptyList()
)

// fun PostDetailResponseDto.toResonse() = PostDetailResponse(
//    id = id,
//    title = title,
//    content = content,
//    createdBy = createdBy,
//    createdAt = createdAt
// )
