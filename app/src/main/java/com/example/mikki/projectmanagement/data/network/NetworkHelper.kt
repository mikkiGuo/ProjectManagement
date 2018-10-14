package com.example.mikki.projectmanagement.data.network

import android.util.Log
import com.example.mikki.projectmanagement.data.model.ProjectTaskItem
import com.example.mikki.projectmanagement.data.model.ProjectsItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class NetworkHelper:INetworkHelper {

    var disposable: Disposable? = null
    val apiServe by lazy {
        APIService.create()
    }

    override fun storeNewProjectToServer(p:ProjectsItem) {
        Log.d("MyTag", "+++++++++++++++++++++++++++++++++++++++")
        disposable =
                apiServe.getCreateNewProjectStatus(
                        p.projectname!!,
                        p.projectstatus!!,
                        p.projectdesc!!,
                        p.startdate!!,
                        p.endstart!!)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { result -> Log.d("MyTag", result.toString()) },
                                { error -> Log.d("MyTag", error.message) }
                        )
    }

    override fun getProjectList() {
        Log.d("MyTag", "+++++++++++++++++++++++++++++++++++++++")
        disposable =
                apiServe.getProjectList()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { result -> Log.d("MyTag", result.toString()) },
                                { error -> Log.d("MyTag", error.message) }
                        )
    }

    /*override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }*/

    override fun createTask(taskItem: ProjectTaskItem) {
        disposable = apiServe.createNewTask(
                taskItem.projectid!!,
                taskItem.taskname!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> Log.d("ninntag", result.toString()) },
                        { error -> Log.d("ninntag", error.message) }
                )
    }

    override fun getTaskList() {
        disposable = apiServe.getTaskList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                        { result -> Log.d("ninntag", result.toString()) },
                        { error -> Log.d("ninntag", error.message) }
                )
    }

}