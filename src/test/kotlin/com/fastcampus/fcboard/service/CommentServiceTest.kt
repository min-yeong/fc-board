package com.fastcampus.fcboard.service

import com.fastcampus.fcboard.controller.dto.CommentCreateRequestDto
import com.fastcampus.fcboard.domain.Post
import com.fastcampus.fcboard.exception.PostNotFoundException
import com.fastcampus.fcboard.repository.CommentRepository
import com.fastcampus.fcboard.repository.PostRepository
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.comparables.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull

@SpringBootTest
class CommentServiceTest (
    private val commentService : CommentService,
    private val commentRepository: CommentRepository,
    private val postRepository: PostRepository,
) : BehaviorSpec ({
    given("댓글 생성 시") {
        val post = postRepository.save(Post(
            title = "title",
            content = "content",
            createdBy = "harris"
        ))
        When("인풋이 정상적으로 들어오면") {
            val commentId = commentService.createComment(post.id, CommentCreateRequestDto(
                    content = "댓글 내용",
                    createdBy = "댓글 생성자",
                )
            )
            then("정상 생성됨을 확인한다.") {
                commentId shouldBeGreaterThan 0L
                val comment = commentRepository.findByIdOrNull(commentId)
                comment shouldNotBe null
                comment?.content shouldBe "댓글 내용"
                comment?.createdBy shouldBe "댓글 생성자"
            }
        }
        When("게시글이 존재하지 않으면") {
            then("게시글이 존재하지 않음 예외가 발생한다.") {
                shouldThrow<PostNotFoundException> {
                    commentService.createComment(9999L, CommentCreateRequestDto(
                        content = "댓글 내용",
                        createdBy = "댓글 생성자"
                    ))
                }
            }
        }
    }

    given("댓글 수정 시") {
        When("인풋이 정상적으로 들어오면") {
        }
        When()
    }
})