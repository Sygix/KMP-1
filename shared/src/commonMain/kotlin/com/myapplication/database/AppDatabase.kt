package com.myapplication.database

import network.Answer
import network.Question
import network.Quiz

internal class QuizDatabase(databaseDriverFactory: DriverFactory) {
    private val database = Database(databaseDriverFactory.createDriver())
    private val dbQuery = database.quizQueries

    internal fun clearDatabase() {
        dbQuery.transaction {
            dbQuery.clearDatabase()
        }
    }

    private fun getAnswersByQuestionId(questionId: Int): List<Answer> {
        return dbQuery.selectAnswersFromQuestion(questionId.toLong()).executeAsList().map { Answer(it.id.toInt(), it.label)}
    }

    private fun getQuestionsByQuizId(quizId: Int): List<Question> {
        return dbQuery.selectQuestionsFromQuiz(quizId.toLong()).executeAsList().map { questionEntity ->
            val questionId = questionEntity.id.toInt()
            val correctAnswerId = questionEntity.correct_answer_id?.toInt() ?: 0

            val answers = getAnswersByQuestionId(questionId)

            Question(
                id = questionId,
                label = questionEntity.label,
                correctAnswerId = correctAnswerId,
                imageUrl = questionEntity.image_url,
                answers = answers.map { Answer(it.id, it.label) }
            )
        }
    }

    internal fun getQuizes(): List<Quiz> {
        return dbQuery.selectAllQuizes().executeAsList().map { quizEntity ->
            val quizId = quizEntity.id.toInt()
            val questions = getQuestionsByQuizId(quizId)

            Quiz(
                id = quizId,
                createdAt = quizEntity.created_at,
                questions = questions
            )
        }
    }

    internal fun insertQuiz(quiz: Quiz) {
        dbQuery.transaction {
            dbQuery.insertQuiz(quiz.id.toLong())
            quiz.questions.forEach { question ->
                dbQuery.insertQuestion(
                    question.label,
                    question.imageUrl,
                    quiz.id.toLong(),
                )
                val questionId = dbQuery.selectLastQuestionId().executeAsOne()

                question.answers.forEach { answer ->
                    dbQuery.insertAnswer(answer.label, questionId)
                    if(answer.id == question.correctAnswerId) {
                        val answerId = dbQuery.selectLastAnswerId().executeAsOne()
                        dbQuery.updateCorrectAnswer(answerId, questionId)
                    }
                }
            }
        }
    }

}