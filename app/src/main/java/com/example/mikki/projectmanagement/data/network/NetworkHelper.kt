package com.example.mikki.projectmanagement.data.network

import android.util.Log
import com.example.mikki.projectmanagement.data.IDataManager
import com.example.mikki.projectmanagement.data.model.MembersItem
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
        Log.d("Create SubTask", "+++++++++++++++++++++++++++++++++++++++")
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


    //http://rjtmobile.com/aamir/pms/android-app/pms_edit_sub_task_update.php?
    // taskid=1&
    // project_id=27&
    // userid=14&
    // sub_task_status=2&
    // sub_task_name=sub%20task%202&
    // sub_task_desc=testing%20from%20postman%202&
    // start_date=2019-01-01&
    // end_date=2019-01-10&
    // subtaskid=1
    override fun editSubTask(listener: IDataManager.OnAdminEditSubTaskListener,
                             subTask: ProjectSubTaskItem) {
        Log.d("Edit SubTask", "+++++++++++++++++++++++++++++++++++++++")
        disposable =
                apiServe.editNewSubTask(
                        subTask.taskid!!,
                        subTask.projectid!!,
                        "14",
                        subTask.subtaskstatus!!,
                        subTask.subtaskname!!,
                        subTask.subtaskdesc!!,
                        subTask.startdate!!,
                        subTask.endstart!!,
                        subTask.subtaskid!!)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { result -> Log.d("SubTaskEdit Success: ", result.msg?.get(0).toString())
                                    listener.editTask(result.msg?.get(0).toString())
                                },
                                { error -> Log.d("SubTaskEdit Fail: ", error.message)
                                    listener.editTask(error.message.toString())
                                }
                        )
        listener.editTask("You did it!!")

    }

    // taskid=1&
    // subtaskid=1&
    // project_id=27&
    // userid=14&
    // subtask_status=2
    override fun editSubTaskStatus(listner: IDataManager.OnUserEditSubTaskStatusListener,
                                   subTask: ProjectSubTaskItem) {
        Log.d("editSTStatus", "+++++++++++++++++++++++++++++++++++++++")
        disposable =
                apiServe.updateSubTaskStatus(
                        subTask.taskid!!,
                        subTask.subtaskid!!,
                        subTask.projectid!!,
                        "14",
                        subTask.subtaskstatus!!)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { result -> Log.d("UpdateStatus Success: ",
                                        result.msg?.get(0).toString())
                                    listner.editSubTaskStatusByUser(result.msg?.get(0).toString())
                                },
                                { error -> Log.d("UpdateStatus Fail: ", error.message)
                                    listner.editSubTaskStatusByUser(error.localizedMessage)
                                }
                        )
    }


    //http://rjtmobile.com/aamir/pms/android-app/pms_assign_sub_task_project.php?
    // taskid=1&
    // subtaskid=1&
    // project_id=27&
    // team_member_userid=14
    override fun assignSubTaskToUser(listner: IDataManager.OnAdminAssignSubTaskToUserListener,
                                     subTask: ProjectSubTaskItem) {
        Log.d("SubTaskAssign", "+++++++++++++++++++++++++++++++++++++++")
        disposable =
                apiServe.assignSubTaskToUser(
                        subTask.taskid!!,
                        subTask.subtaskid!!,
                        subTask.projectid!!,
                        "14")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { result -> Log.d("StFragList Success: ",
                                            result.msg?.get(0).toString())
                                    listner.assignSubTask(result.msg?.get(0).toString())
                                },
                                { error -> Log.d("StFragList Fail: ", error.message)
                                    listner.assignSubTask(error.localizedMessage)
                                }
                        )
    }

    //adapter 
    override fun viewSubTaskDetailByUser(listner: IDataManager.OnUserAdminViewSubTaskDetailListener,
                                         subTask: ProjectSubTaskItem) {

        disposable =
                apiServe.viewSubTaskDetailByUser(
                    subTask.taskid!!,
                    subTask.subtaskid!!,
                    subTask.projectid!!)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                {result ->
                                    Log.d("ViewSTDetail Success: ", result.toString())
                                },
                                {error ->
                                    Log.d("ViewSTDetail Fail: ", error.localizedMessage)
                                })

//        @Query("taskid") taskId: String,
//        @Query("subtask_id") subTaskId: String,
//        @Query("project_id") projectId: String)
    }

    override fun viewTeamMemberBySubTask(listener: IDataManager.OnAdminViewTeamMemeberBySubTask,
                                         subTask: ProjectSubTaskItem) {
        disposable =
                apiServe.viewTeamMemberBySubTask(
                        subTask.taskid!!,
                        subTask.subtaskid!!,
                        subTask.projectid!!)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                {result ->
                                    Log.d("ViewSTDetail Success: ", result.members.toString())
                                    var members: ArrayList<MembersItem>?
                                    members = result.members as ArrayList<MembersItem>?
                                    listener.viewTeamMemberBySubTask(members)
                                },
                                {error ->
                                    Log.d("ViewSTDetail Fail: ", error.localizedMessage)
                                })
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

}