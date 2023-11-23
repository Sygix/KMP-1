package network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Answer(
    @SerialName("id")
    val id: Int,
    @SerialName("label")
    val label: String,
)

@Serializable
data class Question(
    @SerialName("id")
    val id:Int,
    @SerialName("label")
    val label:String,
    @SerialName("correct_answer_id")
    val correctAnswerId:Int,
    @SerialName("image_url")
    val imageUrl: String,
    @SerialName("answers")
    val answers:List<Answer>
)

@Serializable
data class Quiz(
    @SerialName("id")
    val id: Int,
    @SerialName("questions")
    var questions: List<Question>
)