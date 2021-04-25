package com.muz1i.diseasereportandroid.view.activity

import android.content.Intent
import android.text.InputFilter
import android.view.View
import com.muz1i.diseasereportandroid.R
import com.muz1i.diseasereportandroid.base.BaseActivity
import com.muz1i.diseasereportandroid.databinding.ActivityLoginBinding
import com.muz1i.diseasereportandroid.utils.LoadState
import com.muz1i.diseasereportandroid.utils.ToastUtils
import com.muz1i.diseasereportandroid.viewmodel.LoginViewModel

/**
 * @author: Muz1i
 * @date: 2021/4/23
 */
class LoginActivity : BaseActivity<LoginViewModel, ActivityLoginBinding>() {

    override fun getContentViewId(): Int {
        return R.layout.activity_login
    }

    override fun getVMClass(): Class<LoginViewModel> {
        return LoginViewModel::class.java
    }

    override fun initView() {
        binding.viewModel = viewModel
    }

    override fun observeData() {
        viewModel.apply {
            val that = this@LoginActivity
            loadState.observe(that, {
                binding.loadingPic.visibility =
                    if (it == LoadState.LOADING) View.VISIBLE else View.GONE
            })
            loginResult.observe(that, { loginResult ->
                ToastUtils.showToast(loginResult.message)
                if (loginResult.success) {
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                }
            })
            permission.observe(that, { permission ->
                when (permission) {
                    R.id.user_permission -> loginIdFilter(12)
                    R.id.doctor_permission -> loginIdFilter(10)
                    R.id.admin_permission -> loginIdFilter(10)
                }
            })
        }
    }

    override fun initEvent() {
        binding.run {
            loginBtn.setOnClickListener {
                if (viewModel!!.id.value == null || viewModel!!.password.value == null) {
                    ToastUtils.showToast(getString(R.string.id_password_illegal_text))
                } else {
                    this@LoginActivity.viewModel.login()
                }
            }
        }
    }

    private fun loginIdFilter(length: Int) {
        binding.loginId.run {
            if (this.text.length > length) {
                val substring = this.text.substring(0, length - 1)
                this.setText(substring)
            }
        }
        binding.loginId.filters = Array<InputFilter>(1) {
            InputFilter.LengthFilter(length)
        }
    }
}
