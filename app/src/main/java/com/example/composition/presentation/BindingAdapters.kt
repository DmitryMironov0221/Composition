package com.example.composition.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.view.View.OnClickListener
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.composition.R
import com.example.composition.domain.entity.GameResult


interface OnOptionClickListener{
    fun onOptionClick(option: Int)
}

@SuppressLint("StringFormatMatches")
@BindingAdapter("requiredAnswers")
fun bindingRequiredAnswers(textView: TextView, count: Int){
    textView.text = String.format(
        textView.context.getString(R.string.required_score),
        count
    )
}
@SuppressLint("StringFormatMatches")
@BindingAdapter("requiredPercentage")
fun bindingRequiredPercentage(textView: TextView, percentage: Int){
    textView.text = String.format(
        textView.context.getString(R.string.required_percentage),
        percentage
    )
}

@SuppressLint("StringFormatMatches")
@BindingAdapter("scoreAnswers")
fun bindingScoreAnswers(textView: TextView, countAnswers: Int){
    textView.text = String.format(
        textView.context.getString(R.string.score_answers),
        countAnswers
    )
}

@SuppressLint("StringFormatMatches")
@BindingAdapter("scorePercentage")
fun bindingScorePercentage(textView: TextView, gameResult: GameResult){
    textView.text = String.format(
        textView.context.getString(R.string.score_percentage),
        getPercentOfRightAnswers(gameResult)
    )
}
private fun getPercentOfRightAnswers(gameResult: GameResult) = with(gameResult) {
    if (countOfQuestions == 0) {
        0
    } else{
        ((countOfRightAnswers/ countOfQuestions.toDouble()) * 100).toInt()
    }
}

@BindingAdapter("resultEmoji")
fun bindingResultEmoji(imageView: ImageView, winner : Boolean){
    imageView.setImageResource(getSmileResId(winner))

}
private fun getSmileResId(winner: Boolean) : Int{
    return if (winner){
        R.drawable.smile
    } else {
        R.drawable.sad
    }
}

@BindingAdapter("enoughCount")
fun bindingEnoughCount(textView: TextView, enough: Boolean) {
    textView.setTextColor(getColorByState(textView.context, enough))
}


@BindingAdapter("enoughPercent")
fun bindingEnoughPercent(progressBar: ProgressBar, enough: Boolean) {
    val color = getColorByState(progressBar.context, enough)
    progressBar.progressTintList = ColorStateList.valueOf(color)
}

private fun getColorByState(context: Context, goodState: Boolean): Int {
    val colorResId = if (goodState) {
        android.R.color.holo_green_light
    } else {
        android.R.color.holo_red_light
    }
    return ContextCompat.getColor(context, colorResId)
}

@BindingAdapter("numberAsText")
fun bindingNumberAsText(textView: TextView, number: Int){
    textView.text = number.toString()
}

@BindingAdapter("onClickListener")
fun bindingOnClickListener(textView: TextView, clickListener: OnOptionClickListener){
    textView.setOnClickListener {
        clickListener.onOptionClick(textView.text.toString().toInt())
    }
}

