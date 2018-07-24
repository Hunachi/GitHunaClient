package io.github.hunachi.githunaclient.presentation.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import io.github.hunachi.githunaclient.util.ErrorCallback
import com.github.salomonbrys.kodein.android.KodeinSupportFragment

/**
 * Created by hunachi on 2018/01/28.
 */
abstract class BaseFragment: KodeinSupportFragment() {
    
    protected lateinit var activity: BaseActivity
    
    private var viewModel: BaseFragmentViewModel? = null
    
    /*VMとActivityを同期させるなら必須*/
    open fun setViewModel(viewModel: BaseFragmentViewModel) {
        this.viewModel = viewModel
        viewModel.onCreate()
    }
    
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        this.activity = context as BaseActivity
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel?.onCreateView()
        return super.onCreateView(inflater, container, savedInstanceState)
    }
    
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel?.onActivityCreated()
    }
    
    override fun onStart() {
        super.onStart()
        viewModel?.onStart()
    }
    
    override fun onResume() {
        super.onResume()
        viewModel?.onResume()
    }
    
    override fun onPause() {
        super.onPause()
        viewModel?.onPause()
    }
    
    override fun onStop() {
        super.onStop()
        viewModel?.onStop()
    }
    
    override fun onDestroy() {
        super.onDestroy()
        viewModel?.onDestroy()
    }
    
    override fun onDetach() {
        super.onDetach()
        viewModel?.onDetach()
    }
    
    fun errorToast(){
        errorToast("failed to receive the data")
    }
    
    fun errorToast(text: String){
        context?.let { Toast.makeText(it, text, Toast.LENGTH_SHORT).show() }
    }
}
