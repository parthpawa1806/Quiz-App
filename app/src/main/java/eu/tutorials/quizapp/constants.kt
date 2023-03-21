package eu.tutorials.quizapp

object constants {
    const val USER_NAME : String = "user_name"
    const val TOTAL_NUMBERS : String = "total_questions"
    const val CORRECT_ANSWERS : String = "correct_answers"

fun getQuestions():ArrayList<Question>{
val questionsList = ArrayList<Question>()
val que1 = Question(
    1,"What country does this belog to flag is this",R.drawable.firstflag,
    "Australia","India","Canada","America",3)
    questionsList.add(que1)

    val que2 = Question(
        2,"What country does this belog to flag is this",R.drawable.secondflag,
        "Kenya","Argentina","Canada","Austria",4)
    questionsList.add(que2)

    val que3 = Question(
        3,"What country does this belog to flag is this",R.drawable.thirdflag,
        "Afganistan","Argentina","Australia","Kenya",4)
    questionsList.add(que3)

    val que4 = Question(
        4,"What country does this belog to flag is this",R.drawable.forthflag,
        "Australia","Argentina","Canada","Pakistan",1)
    questionsList.add(que4)

    val que5 = Question(
        5,"What country does this belog to flag is this",R.drawable.fifthflag,
        "South Africa","UAE","China","Austria",5)
    questionsList.add(que5)

    return questionsList
}
}


