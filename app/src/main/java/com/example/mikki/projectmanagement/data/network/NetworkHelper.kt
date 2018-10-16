package com.example.mikki.projectmanagement.data.network

import android.util.Log
import com.example.mikki.projectmanagement.data.IDataManager
import com.example.mikki.projectmanagement.data.model.ProjectAdminTaskItem
import com.example.mikki.projectmanagement.data.model.ProjectSubTaskItem
import com.example.mikki.projectmanagement.data.model.ProjectsItem
import com.example.mikki.projectmanagement.viewmodel.ProjectViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class NetworkHelper:INetworkHelper {

    var disposable: Disposable? = null
    val apiServe by lazy {
        APIService.create()
    }

    override fun storeNewProjectToServer(p:ProjectsItem, viewModel: ProjectViewModel) {
        Log.d("mikkiproject", "+++++++++++++++++++++++++++++++++++++++")
        Log.d("mikkiproject", p.projectname)
        Log.d("mikkiproject", p.projectstatus)
        Log.d("mikkiproject", p.projectdesc)
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
                                { result ->
                                    Log.d("mikkiproject", result.toString())
                                    viewModel.updateList(p)
                                     },
                                { error -> Log.d("mikkiproject", error.message) }
                        )
    }


    override fun getProjectList(viewModel: ProjectViewModel) {
        Log.d("mikkiproject", "+++++++++++++++++++++++++++++++++++++++")
        disposable =
                apiServe.getProjectList()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { result ->
                                    for(item in result.projects!!){
                                        viewModel.updateList(item!!)
                                    }
                                    Log.d("mikkiproject",result.projects.toString()
                                    )
                                },
                                { error -> Log.d("mikkiproject", error.message) }
                        )
    }

    override fun updateProject(pId: String, p: ProjectsItem, viewModel: ProjectViewModel) {
        Log.d("mikkiproject", "+++++++++++++++++++++++++++++++++++++++")
        disposable =
                apiServe.updateProject(
                        pId,
                        p.projectname!!,
                        p.projectstatus!!,
                        p.projectdesc!!,
                        p.startdate!!,
                        p.endstart!!
                )
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { result ->
                                    Log.d("mikkiproject","Message"
                                            + result.toString()
                                    )
                                },
                                { error -> Log.d("mikkiproject", error.message) }
                        )
    }


    override fun createTask(listener: IDataManager.OnAdminCreateTaskListener, adminTaskItem: ProjectAdminTaskItem) {
        disposable = apiServe.createNewTask(
                adminTaskItem.projectid!!,
                adminTaskItem.taskname!!,
                adminTaskItem.taskstatus,
                adminTaskItem.taskdesc,
                adminTaskItem.startdate,
                adminTaskItem.endstart)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> Log.d("ninntag", result.toString())
                            listener.createTask()},
                        { error -> Log.d("ninntag", error.message) }
                )
    }

    override fun getAdminTaskList(listener: IDataManager.OnAdminTaskListListener) {
        var adminTaskList: ArrayList<ProjectAdminTaskItem>?

        disposable = apiServe.getAdminTaskList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                        { result -> Log.d("ninntag", result.toString())
                            adminTaskList = result.projectAdminTask
                            Log.d("ninntag", adminTaskList.toString())
                            listener.getAdminTaskList(adminTaskList)},
                        { error -> Log.d("ninntag", error.message)
                            listener.getAdminTaskList(null)}
                )
    }

    override fun getUserTaskList(id: String) {
        disposable = apiServe.getUserTaskList(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                        { result -> Log.d("ninntag", result.toString()) },
                        { error -> Log.d("ninntag", error.message) }
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
                                { result -> Log.d("MyTag", result.toString()) },
                                { error -> Log.d("MyTag", error.message) }
                        )
    }

    /*
    //should add this to activity class
    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }*/





}