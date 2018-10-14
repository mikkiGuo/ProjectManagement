package com.example.mikki.projectmanagement.data.network

import android.util.Log
import com.example.mikki.projectmanagement.data.model.ProjectSubTaskItem
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
                                { result -> Log.d("MyTag PROJECT", result.toString())
                                },
                                { error -> Log.d("MyTag PROJECT", error.message) }
                        )
    }

    override fun getProjectList() {
        Log.d("MyTag", "+++++++++++++++++++++++++++++++++++++++")
        disposable =
                apiServe.getProjectList()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { result -> Log.d("MyTag", result.toString())
                                },
                                { error -> Log.d("MyTag", error.message) }
                        )
    }

    override fun storeNewSubTaskToServer(subTask: ProjectSubTaskItem) {
        Log.d("Create NewSubTask", "++++++++++create NewSubTask+++++++++++")
        Log.d("NetWork: Subtask data: ", subTask.toString())
        disposable =
                apiServe.getCreateNewSubTask(
                        subTask.projectid!!,
                        subTask.taskid!!,
                        subTask.subtaskname!!,
                        subTask.subtaskstatus!!,
                        subTask.subtaskdesc!!,
                        subTask.startdate!!,
                        subTask.endstart!!)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                {result -> Log.d("Success: createSubTask", result.toString())},
                                {error -> Log.d("Fail: createSubTask", error.localizedMessage)}
                        )
    }

    override fun getSubTaskList() {
        Log.d("SubTask List", "++++++++++++SubTaskList+++++++++++++++")
        disposable = apiServe.getSubTaskList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {result -> Log.d("Success: SubTaskList ", result.projectSubTask!![result.projectSubTask.lastIndex].toString())},
                        { error -> Log.d("Fail: SubTaskList ", error.message)}
                )
    }

    /*override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }*/

}