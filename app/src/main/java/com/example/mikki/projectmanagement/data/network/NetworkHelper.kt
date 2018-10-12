package com.example.mikki.projectmanagement.data.network

import com.example.mikki.projectmanagement.data.model.Project
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class NetworkHelper:INetworkHelper {

    var disposable: Disposable? = null
    val apiServe by lazy {
        APIService.create()
    }

    override fun storeNewProjectToServer(p:Project) {

        disposable =
                apiServe.getCreateNewProjectStatus(
                        p.pname,
                        p.pstatus,
                        p.pdescription,
                        p.start_date,
                        p.end_date)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { result -> showResult() },
                                { error -> showError(error.message) }
                        )
    }

    private fun showError(message: String?) {

    }

    private fun showResult() {
        
    }

    /*override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }*/

}