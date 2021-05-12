package com.muz1i.diseasereportandroid.view.activity

import android.app.AlertDialog
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import com.muz1i.diseasereportandroid.R
import com.muz1i.diseasereportandroid.base.BaseActivity
import com.muz1i.diseasereportandroid.databinding.ActivityPublicTaskBinding
import com.muz1i.diseasereportandroid.databinding.ItemPunchEditTextBinding
import com.muz1i.diseasereportandroid.utils.FinishActivityAndToastUtils
import com.muz1i.diseasereportandroid.utils.ToastUtils
import com.muz1i.diseasereportandroid.viewmodel.punch.PunchViewModel

/**
 * @author: Muz1i
 * @date: 2021/5/12
 */
class PublicTaskActivity : BaseActivity<PunchViewModel, ActivityPublicTaskBinding>() {
    private val maxItems = 5
    private val itemList by lazy {
        mutableListOf<String>()
    }

    override fun getContentViewId(): Int {
        return R.layout.activity_public_task
    }

    override fun getVMClass(): Class<PunchViewModel> {
        return PunchViewModel::class.java
    }

    override fun setToolBarTitle(): String {
        return getString(R.string.public_task_text)
    }

    override fun initEvent() {
        val that = this
        binding.run {
            addItemBtn.setOnClickListener {
                if (itemList.size >= maxItems) {
                    ToastUtils.showToast("任务条目已达最多数量")
                } else {
                    AlertDialog.Builder(that).apply {
                        setMessage("名称")
                        setCancelable(false)
                        val editTextView =
                            layoutInflater.inflate(R.layout.dialog_edit_text_item, null)
                        setView(editTextView)
                        setPositiveButton("确认") { _, _ ->
                            val itemName =
                                editTextView.findViewById<EditText>(R.id.add_task_item).text.toString()
                            itemList.add(itemName)
                            addPunchItem(itemName)
                        }
                        setNegativeButton("取消") { _, _ -> }
                        show()
                    }
                }
            }
            completeTaskEditBtn.setOnClickListener {
                if (itemList.isEmpty()) {
                    ToastUtils.showToast("任务条目不能为空，请添加条目")
                } else {
                    viewModel.publicPunchTask(itemList)
                }
            }
        }
    }

    override fun observeData() {
        val that = this
        viewModel.run {
            publicTaskSuccess.observe(that) {
                FinishActivityAndToastUtils.finishActivity(that, it, "发布成功", "发布失败，请稍后重试")
            }
        }
    }

    private fun addPunchItem(itemName: String) {
        binding.editTextContainer.run {
            val itemBinding = DataBindingUtil.inflate<ItemPunchEditTextBinding>(
                layoutInflater,
                R.layout.item_punch_edit_text,
                this,
                true
            )
            itemBinding.itemName.text = itemName
        }
    }
}
