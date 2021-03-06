package com.farhad.deghat.charsandwords

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.farhad.deghat.charsandwords.di.viewModel.DaggerViewModelComponent
import com.farhad.deghat.charsandwords.di.viewModel.ViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: MainActivityViewModel

    //UI components
    private lateinit var btnFire: Button

    private lateinit var txtViwTenthChar: TextView
    private lateinit var pgrsBarTenthChar: ProgressBar

    private lateinit var txtViwEvery10Char: TextView
    private lateinit var pgrsBarEvery10thChar: ProgressBar

    private lateinit var txtViwWordCounter: TextView
    private lateinit var pgrsBarWordCounter: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerViewModelComponent.create().injectActivity(this)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(MainActivityViewModel::class.java)

        setUiComponents()
        setObservers()
    }

    private fun setUiComponents() {
        btnFire = findViewById(R.id.btnFire)
        btnFire.setOnClickListener { viewModel.btnFireClicked() }

        txtViwTenthChar = findViewById(R.id.txtViwTenthChar)

        pgrsBarTenthChar = findViewById(R.id.pgrsBarTenthChar)
        pgrsBarTenthChar.visibility = View.GONE

        txtViwEvery10Char = findViewById(R.id.txtViwEvery10thChar)
        txtViwEvery10Char.movementMethod = ScrollingMovementMethod()

        pgrsBarEvery10thChar = findViewById(R.id.pgrsBarEvery10thChar)
        pgrsBarEvery10thChar.visibility = View.GONE

        txtViwWordCounter = findViewById(R.id.txtViwWordCounter)
        txtViwWordCounter.movementMethod = ScrollingMovementMethod()

        pgrsBarWordCounter = findViewById(R.id.pgrsBarWordCounter)
        pgrsBarWordCounter.visibility = View.GONE
    }

    private fun setObservers() {
        viewModel.tenthCharacter.observe(
            this,
            Observer { tenthChar ->
                val text = "${getString(R.string.The_10th_char)}'$tenthChar'"
                txtViwTenthChar.text = text
            })

        viewModel.showTenthCharProgress.observe(this, Observer { showProgress ->
            pgrsBarTenthChar.visibility =
                if (showProgress)
                    View.VISIBLE
                else
                    View.GONE
        })

        viewModel.every10thCharactersSequence.observe(
            this,
            Observer { every10CharSequence -> txtViwEvery10Char.text = every10CharSequence })

        viewModel.showEvery10thCharProgress.observe(this, Observer { showProgress ->
            pgrsBarEvery10thChar.visibility =
                if (showProgress)
                    View.VISIBLE
                else
                    View.GONE
        })

        viewModel.wordCounterOutput.observe(
            this,
            Observer { wordCounterOutput -> txtViwWordCounter.text = wordCounterOutput })

        viewModel.showWordCounterProgress.observe(this, Observer { showProgress ->
            pgrsBarWordCounter.visibility =
                if (showProgress)
                    View.VISIBLE
                else
                    View.GONE
        })

        viewModel.errorText.observe(this, Observer { errorMsg -> showError(errorMsg) })
    }

    private fun showError(errorMsg: String){
        Toast.makeText(this, "${getString(R.string.errPrefix)}$errorMsg", Toast.LENGTH_SHORT).show()
    }
}
