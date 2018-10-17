package com.example.mikki.projectmanagement.data.network

import android.util.Log
import com.example.mikki.projectmanagement.data.IDataManager.*
import com.example.mikki.projectmanagement.data.model.*
import com.example.mikki.projectmanagement.viewmodel.ProjectViewModel
import com.example.mikki.projectmanagement.viewmodel.TaskViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.warn

class NetworkHelper:INetworkHelper {

    private val ninntag = AnkoLogger("ninntag")

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
                                { result -> Log.d("MyTag", result.toString()) },
                                { error -> Log.d("MyTag", error.message) }
                        )
    }

    override fun createTask(viewModel: TaskViewModel,
                            listener: OnAdminCreateTaskListener,
                            taskItem: TaskItem) {
        disposable = apiServe.createNewTask(
                taskItem.projectid!!,
                taskItem.taskname!!,
                taskItem.taskstatus,
                taskItem.taskdesc,
                taskItem.startdate,
                taskItem.endstart)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            if (result.msg!![0].equals("project not found")) {
                                viewModel.isTaskCreated(listener, false)
                            } else {
                                viewModel.isTaskCreated(listener, true)
                            }
                        },
                        { error -> ninntag.warn { "error: " + error.message } }
                )
    }

    override fun getAdminTaskList(viewModel: TaskViewModel, listener: OnAdminTaskListListener) {
        disposable = apiServe.getAdminTaskList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                        { result ->
                            viewModel.showTaskList(listener, result.task)
                        },
                        { error -> viewModel.showTaskList(listener, null) }
                )
    }

    override fun getUserTaskList(viewModel: TaskViewModel, id: String) {
        disposable = apiServe.getUserTaskList(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                        { result ->
                            ninntag.warn { "result: " + result.toString() } },
                        { error -> ninntag.warn { "error: " + error.message } }
                )
    }

    override fun updateTaskDetails(viewModel: TaskViewModel, listener: OnAdminTaskUpdatedListener, taskItem: TaskItem) {
        disposable = apiServe.updateTaskDetails(
                taskItem.taskid!!,
                taskItem.projectid!!,
                taskItem.userid!!,
                taskItem.taskstatus!!,
                taskItem.taskname!!,
                taskItem.taskdesc!!,
                taskItem.startdate!!,
                taskItem.endstart!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            if (result.msg!![0].equals("status updated"))
                                viewModel.isTaskUpdated(listener, true)
                            else
                                viewModel.isTaskUpdated(listener, false)
                        },
                        { error -> ninntag.warn { "error: " + error.message } }
                )
    }

    override fun getTeamMemberByTask(viewModel: TaskViewModel, listener: OnTaskMemberListener, taskItem: TaskItem) {
        disposable = apiServe.getTeamListByTask(
                taskItem.taskid!!,
                "99",
                taskItem.projectid!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            viewModel.showTaskMemberList(listener, result.members)
                        },
                        { error ->
                            viewModel.showTaskMemberList(listener,null)
                            ninntag.warn { "error: " + error.message }
                        }
                )
    }

    override fun getMemberDetails(viewModel: TaskViewModel,
                                  addlistener: OnAddMemberDetailsListener,
                                  memberListListener: OnTaskMemberListener,
                                  memberList: ArrayList<TaskMemberItem>?) {

        for ((index, value) in memberList!!.withIndex()) {
            disposable = apiServe.getMemberDetails(value.userid!!)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { result ->
                                viewModel.addMemberDetailsToList(result, index)
                                if (index == memberList.size-1)
                                    addlistener.finishedAdding(memberListListener)
                                ninntag.warn { "result: " + result.toString() }
                            },
                            { error -> ninntag.warn { "error: " + error.message } }
                    )
        }
    }
}