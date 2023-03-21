package eu.tutorials.quizapp


import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat

class Quizquestion : AppCompatActivity(), View.OnClickListener {

    private var mcurrentprogress : Int = 1
    private var mQuestionList : ArrayList<Question>? = null
    private var mslectedAnswerPosition : Int = 0
    private var musername : String? = null
    private var mcorrectanswers : Int = 0

    private var progressbar : ProgressBar? = null
    private var tvProgress: TextView?= null
    private var tvQuestion :TextView? = null
    private var ivImage : ImageView? = null

    private var tvOptionOne : TextView? = null
    private var tvOptionTwo : TextView? = null
    private var tvOptionThree : TextView? = null
    private var tvOptionFour : TextView? = null
    private var BtnSubmit :Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quizquestion)

        musername = intent.getStringExtra(constants.USER_NAME)


        progressbar = findViewById(R.id.tv_progress_bar)
        tvProgress = findViewById(R.id.tv_progress)
        tvQuestion = findViewById(R.id.tv_question)
        ivImage = findViewById(R.id.tv_flagimg)
        tvOptionOne = findViewById(R.id.tv_option_one)
        tvOptionTwo = findViewById(R.id.tv_option_two)
        tvOptionThree = findViewById(R.id.tv_option_three)
        tvOptionFour = findViewById(R.id.tv_option_four)
        mQuestionList = constants.getQuestions()
        BtnSubmit = findViewById(R.id.Btn_submit)

        tvOptionOne?.setOnClickListener(this)
        tvOptionTwo?.setOnClickListener(this)
        tvOptionThree?.setOnClickListener(this)
        tvOptionFour?.setOnClickListener(this)
        BtnSubmit?.setOnClickListener(this)

        SetQuestion()

    }

    private fun SetQuestion() {
        defaultOptionView()
        val question: Question = mQuestionList!![mcurrentprogress - 1]
        ivImage?.setImageResource(question.image)
        progressbar?.progress = mcurrentprogress
        tvProgress?.text = "$mcurrentprogress/${progressbar?.max}"
        tvQuestion?.text = question.questions
        tvOptionOne?.text = question.optionOne
        tvOptionTwo?.text = question.optionTwo
        tvOptionThree?.text = question.optionThree
        tvOptionFour?.text = question.optionFour

        if (mcurrentprogress == mQuestionList!!.size){
            BtnSubmit?.text ="FINISH"
        }else{
            BtnSubmit?.text="SUBMIT"}

    }

    private fun defaultOptionView(){
     val options = ArrayList<TextView>()

        tvOptionOne?.let {
            options.add(0,it)
        }
        tvOptionTwo?.let {
            options.add(1,it)
        }
        tvOptionThree?.let {
            options.add(2,it)
        }
        tvOptionFour?.let {
            options.add(3,it)
        }
        for (option in options ){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
               this, R.drawable.default_option_border_bg
            )
        }

    }

    private fun selectedOptionVIew(tv:TextView , selectedoptionview : Int ){
       mslectedAnswerPosition = selectedoptionview
       tv.setTextColor(Color.parseColor("#00FF00"))
        tv.setTypeface(tv.typeface , Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this, R.drawable.selected_option_border)
    }

    override fun onClick(view: View?) {
       when(view?.id){
           R.id.tv_option_one ->{
               tvOptionOne?.let {
                   selectedOptionVIew(it, 1 ) }
           }
           R.id.tv_option_two ->{
               tvOptionTwo?.let {
                   selectedOptionVIew(it, 2 ) }
           }
           R.id.tv_option_three ->{
               tvOptionThree?.let {
                   selectedOptionVIew(it, 3 ) }
           }
           R.id.tv_option_four ->{
               tvOptionFour?.let {
                   selectedOptionVIew(it, 4 ) }
           }

           R.id.Btn_submit->{
              if (mslectedAnswerPosition== 0 ){
                  mcurrentprogress++
                  when{
                      mcurrentprogress<= mQuestionList!!.size->{
                          SetQuestion()
                      }
                      else->{
                          val intent = Intent(this,result_activity::class.java)
                          intent.putExtra(constants.USER_NAME,musername)
                          intent.putExtra(constants.CORRECT_ANSWERS,mcorrectanswers)
                          intent.putExtra(constants.TOTAL_NUMBERS,mQuestionList?.size)
                          startActivity(intent)
                          finish()

                      }
                  }

              }
               else{
                   val question = mQuestionList?.get(mcurrentprogress-1)
                  if(question!!.correctAnswers != mslectedAnswerPosition ){
                      answerView(mslectedAnswerPosition,R.drawable.wrong_answer)
                  }else{
                      mcorrectanswers++
                  }
                  answerView(question.correctAnswers,R.drawable.correct_answer)
                  if (mcurrentprogress == mQuestionList!!.size){
                      BtnSubmit?.text ="FINISH"
                  }else{
                      BtnSubmit?.text="Go to next Question"}
                  mslectedAnswerPosition = 0
              }
           }
       }
    }

    private fun answerView(answer : Int, drawable: Int){

        when(answer){
            1 ->{
                tvOptionOne?.background = ContextCompat.getDrawable(
                    this,drawable
                )
            }
            2 ->{
                tvOptionTwo?.background = ContextCompat.getDrawable(
                    this,drawable
                )
            }
            3 ->{
                tvOptionThree?.background = ContextCompat.getDrawable(
                    this,drawable
                )
            }
            4 ->{
                tvOptionFour?.background = ContextCompat.getDrawable(
                    this,drawable
                )
            }
        }
    }
}