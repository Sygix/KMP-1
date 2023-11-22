package network.data

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class Question(
    val id:Int,
                    val label:String,
                    @SerialName("correct_answer_id") val correctAnswerId:Int,
                    @SerialName("image_url") val imageUrl: String,
                    val answers:List<Answer>
)