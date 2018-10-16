package com.example.mikki.projectmanagement.data.network

import android.util.Log
import com.example.mikki.projectmanagement.data.IDataManager
import com.example.mikki.projectmanagement.data.IDataManager
import com.example.mikki.projectmanagement.data.model.ProjectAdminTaskItem
import com.example.mikki.projectmanagement.data.model.ProjectSubTaskItem
import com.example.mikki.projectmanagement.data.model.ProjectsItem
import com.example.mikki.projectmanagement.viewmodel.ViewModelSubTask
import com.example.mikki.projectmanagement.viewmodel.ProjectViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class NetworkHelper:INetworkHelper {


    var disposable: Disposable? = null
    val apiServe by lazy {
        APIService.create()
    }

    override fun storeNewProjectToServer(p:ProjectsItem) {
        Log.d("Store Project", "+++++++++++++++++++++++++++++++++++++++")
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

    override fun getProjectList() {
        Log.d("ProjectList", "+++++++++++++++++++++++++++++++++++++++")
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

    override fun createNewSubTask(listener: IDataManager.OnAdminCreateSubTaskListener, subTask: ProjectSubTaskItem) {
        Log.d("Store SubTask", "+++++++++++++++++++++++++++++++++++++++")
        disposable =
                apiServe.createNewSubTask(
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
                                { result -> Log.d("SubTask Success: ", result.msg?.get(0).toString())
                                    listener.createTask(result.msg?.get(0).toString())
                                },
                                { error -> Log.d("SubTask Fail: ", error.message)
                                    listener.createTask(error.message.toString())
                                }
                        )
    }

    override fun editSubTask(listener: IDataManager.OnAdminEditSubTaskListener,
                             subTask: ProjectSubTaskItem) {
        listener.editTask("You did it!!")

    }

    override fun getSubTasksList(subTaskViewModel: ViewModelSubTask) {
        Log.d("SubTaskList", "+++++++++++++++++++++++++++++++++++++++")
        disposable =
                apiServe.getSubTaskList()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { result ->
                                    for(item in result.projectSubTask!!) {
                                        subTaskViewModel.upadteSubTaskList(item!!)
                                    }
                                    Log.d("StFragList Success: ",
                                        result.projectSubTask?.get(result.projectSubTask.size-1).toString())
                                },
                                { error -> Log.d("StFragList Fail: ", error.message) }
                                { result -> Log.d("MyTag", result.toString()) },
                                { error -> Log.d("MyTag", error.message) }
                        )
    }
/*override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }*/

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

}