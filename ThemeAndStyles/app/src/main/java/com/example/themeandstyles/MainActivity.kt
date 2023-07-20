package com.example.themeandstyles

import android.animation.AnimatorInflater.loadAnimator
import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Pair
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.themeandstyles.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var isNightMode =
        AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonValueAnimator.text = getString(R.string.score, 0f)
        binding.buttonChangeTheme.setOnClickListener {
            if (isNightMode) AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            isNightMode = !isNightMode
        }

        binding.buttonValueAnimator.setOnClickListener { view ->
            val button = view as Button
            with(
                loadAnimator(
                    applicationContext,
                    R.animator.score_animator_1_1000
                ) as ValueAnimator
            ) {
                addUpdateListener {
                    button.text = getString(R.string.score, animatedValue as Float)
                }
                start()
            }
            with(loadAnimator(applicationContext, R.animator.color_animator) as ValueAnimator) {
                addUpdateListener {
                    button.setTextColor(animatedValue as Int)
                }
                start()
            }
        }

        binding.buttonObjectAnimator.setOnClickListener { view ->
            with(
                loadAnimator(
                    applicationContext,
                    R.animator.rotation_xy_object_animator_set
                ) as AnimatorSet
            ) {
                setTarget(view)
                start()
            }
        }

        binding.buttonViewPropertyAnimator.setOnClickListener {
            it.animate()
                .scaleX(2f)
                .scaleY(2f)
                .alpha(0f)
                .setDuration(2000L)
                .withEndAction {
                    it.animate()
                        .scaleX(1f)
                        .scaleY(1f)
                        .alpha(1f)
                        .setDuration(2000L)
                        .start()
                }
                .start()
        }

        binding.buttonTransitionActivity.setOnClickListener {
            val intent = Intent(this, TransitionActivity::class.java)
            val activityOptions =
                ActivityOptions.makeSceneTransitionAnimation(this, Pair(it, "transitionButton"))
            startActivity(intent, activityOptions.toBundle())
        }
    }
}