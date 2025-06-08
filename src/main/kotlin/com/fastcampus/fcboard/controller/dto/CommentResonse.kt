package com.fastcampus.fcboard.controller.dto

data class CommentResonse(
    val id: Long,
    val content: String,
    val createdBy: String,
    val createdAt: String
)
