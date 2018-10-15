package com.example.mikki.projectmanagement.data.network

import android.util.Log
import com.example.mikki.projectmanagement.data.model.ProjectSubTaskItem
import com.example.mikki.projectmanagement.data.model.ProjectsItem
import com.example.mikki.projectmanagement.project.ProjectViewModel
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
                                { result -> Log.d("MyTag", result.toString())
                                },
                                { error -> Log.d("MyTag", error.message) }
                        )
    }

    override fun getProjectList(viewModel: ProjectViewModel) {
        Log.d("MyTag", "+++++++++++++++++++++++++++++++++++++++")
        disposable =
                apiServe.getProjectList()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { result ->
                                    for(item in result.projects!!){
                                        viewModel.updateList(item!!)
                                    }
                                    Log.d("MyTag",result.projects.toString()
                                    )
                                },
                                { error -> Log.d("MyTag", error.message) }
                        )
    }

    override fun storeNewSubTaskToServer(subTask: ProjectSubTaskItem) {
        Log.d("MyTag", "+++++++++++++++++++++++++++++++++++++++")
        disposable =
                apiServe.getCreateNewSubTaskStatus(
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
                                { result -> Log.d("MyTag", result.toString())
                                },
                                { error -> Log.d("MyTag", error.message) }
                        )
    }
/*override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }*/

}