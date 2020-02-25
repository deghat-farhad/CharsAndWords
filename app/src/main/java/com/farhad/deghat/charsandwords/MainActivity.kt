package com.farhad.deghat.charsandwords

import android.os.Bundle
import android.widget.Button
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
    private lateinit var txtViwTenthChat: TextView
    private lateinit var btnFire: Button

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
        txtViwTenthChat = findViewById(R.id.txtViwTenthChar)
        btnFire = findViewById(R.id.btnFire)
        btnFire.setOnClickListener { viewModel.btnFireClicked() }
    }

    private fun setObservers() {
        viewModel.tenthCharacter.observe(this, Observer { txtViwTenthChat.text = it.toString() })
    }
}
