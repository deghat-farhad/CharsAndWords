package com.farhad.deghat.charsandwords

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
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
    private lateinit var txtViwTenthChar: TextView
    private lateinit var btnFire: Button
    private lateinit var pgrsBarTenthChar: ProgressBar

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
        txtViwTenthChar = findViewById(R.id.txtViwTenthChar)
        btnFire = findViewById(R.id.btnFire)
        btnFire.setOnClickListener { viewModel.btnFireClicked() }
        pgrsBarTenthChar = findViewById(R.id.pgrsBarTenthChar)
        pgrsBarTenthChar.visibility = View.GONE
    }

    private fun setObservers() {
        viewModel.tenthCharacter.observe(
            this,
            Observer { tenthChar -> txtViwTenthChar.text = tenthChar.toString() })
        viewModel.showTenthCharProgress.observe(this, Observer { showProgress ->
            if (showProgress)
                pgrsBarTenthChar.visibility = View.VISIBLE
            else
                pgrsBarTenthChar.visibility = View.GONE
        })
    }
}
