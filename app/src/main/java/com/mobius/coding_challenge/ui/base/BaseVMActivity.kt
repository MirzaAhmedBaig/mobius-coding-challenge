package com.mobius.coding_challenge.ui.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.mobius.coding_challenge.R
import com.mobius.coding_challenge.data.network.Resource
import java.lang.reflect.ParameterizedType
import java.net.HttpURLConnection
import javax.inject.Inject

abstract class BaseVMActivity<VM : BaseViewModel, B : ViewBinding> : AppCompatActivity() {

    val TAG: String
        get() = getClassName()

    lateinit var viewModel: VM
    lateinit var binding: B

    @Inject
    lateinit var factory: BaseViewModelFactory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, factory).get(getViewModelClass())
        binding = getViewBinding()
        setContentView(binding.root)
    }

    override fun onBackPressed() {
        finish()
        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out)
    }

    override fun finish() {
        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out)
        super.finish()
    }

    override fun finishAffinity() {
        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out)
        super.finishAffinity()
    }

    private fun getViewModelClass(): Class<VM> {
        val type = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]
        return type as Class<VM>
    }

    fun handleError(error: Resource.Failure, onDismiss: () -> Unit = {}) {
        when {
            error.isNetworkError -> Toast.makeText(this, "No internet access", Toast.LENGTH_SHORT)
                .show()
            error.errorCode == HttpURLConnection.HTTP_BAD_GATEWAY -> Toast.makeText(
                this,
                "Server not responding",
                Toast.LENGTH_SHORT
            ).show()
            else -> Toast.makeText(this, "Communication to server failed", Toast.LENGTH_SHORT)
                .show()
        }
    }

    abstract fun getViewBinding(): B
    abstract fun getClassName(): String
}