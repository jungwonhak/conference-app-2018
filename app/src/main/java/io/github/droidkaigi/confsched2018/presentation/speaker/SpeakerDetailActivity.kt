package io.github.droidkaigi.confsched2018.presentation.speaker

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.github.droidkaigi.confsched2018.R
import io.github.droidkaigi.confsched2018.databinding.ActivitySpeakerDetailBinding
import io.github.droidkaigi.confsched2018.presentation.NavigationController
import javax.inject.Inject

class SpeakerDetailActivity : AppCompatActivity(), HasSupportFragmentInjector {
    @Inject lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    @Inject lateinit var navigationController: NavigationController

    private val binding: ActivitySpeakerDetailBinding by lazy {
        DataBindingUtil
                .setContentView<ActivitySpeakerDetailBinding>(
                        this,
                        R.layout.activity_speaker_detail
                )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        navigationController.navigateToSpeakerDetail(intent.getStringExtra(EXTRA_SPEAKER_ID))
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector

    companion object {
        const val EXTRA_SPEAKER_ID = "EXTRA_SPEAKER_ID"
        fun start(context: Context, speakerId: String) {
            context.startActivity(Intent(context, SpeakerDetailActivity::class.java).apply {
                putExtra(EXTRA_SPEAKER_ID, speakerId)
            })
        }
    }
}
