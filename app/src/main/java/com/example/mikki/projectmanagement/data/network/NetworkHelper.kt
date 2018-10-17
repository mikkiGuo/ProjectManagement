package com.example.mikki.projectmanagement.data.network

import android.util.Log
import com.example.mikki.projectmanagement.data.IDataManager
import com.example.mikki.projectmanagement.data.model.*
import com.example.mikki.projectmanagement.viewmodel.ViewModelSubTask
import com.example.mikki.projectmanagement.viewmodel.ProjectViewModel
import com.example.mikki.projectmanagement.viewmodel.TeamViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class NetworkHelper:INetworkHelper {

    private val MIKKI_TEAM = "MikkiTeam"

    var disposable: Disposable? = null
    val apiServe by lazy {
        APIService.create()
    }

    override fun updateProject(p: ProjectsItem,
                               viewModel: ProjectViewModel, index:Int) {
        Log.d("mikkiproject", "+++++++++++++++++++++++++++++++++++++++")
        disposable =
                apiServe.updateProject(
                        p.id!!,
                        p.projectname!!,
                        p.projectstatus!!,
                        p.projectdesc!!,
                        p.startdate!!,
                        p.endstart!!
                )
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { result ->viewModel.updateItem(index, p)
                                    Log.d("mikkiproject","Message"
                                            + result.toString()
                                    )
                                },
                                { error -> Log.d("mikkiproject", error.message) }
                        )
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
                                    p.id = result.id.toString()
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
                                    for(item in result.projects!!)
                                    {
                                        item as ProjectsItem
                                        if(!item.projectstatus.equals("2") ){
                                            viewModel.updateList(item)
                                        }
                                    }
                                    Log.d("mikkiproject",result.projects.toString()
                                    )
                                },
                                { error -> Log.d("mikkiproject", error.message) }
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
        //listener.editTask("You did it!!")

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
                                     subTask: ProjectSubTaskItem, userId: Int, position: Int) {
        Log.d("SubTaskAssign", "+++++++++++++++++++++++++++++++++++++++")
        disposable =
                apiServe.assignSubTaskToUser(
                        subTask.taskid!!,
                        subTask.subtaskid!!,
                        subTask.projectid!!,
                        userId.toString())
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

    override fun viewSubTaskListByUser(viewModelSubTask: ViewModelSubTask,
                                       userId: String, taskId: String) {

        disposable =
                apiServe.viewAllSubTaskListByUser(
                        userId,
                        taskId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                {result ->
                                    Log.d("ViewSTDetail Success: ", result.viewsubtasks.toString())
//                                    for(item in result.viewsubtasks!!) {
//                                        viewModelSubTask.upadteSubTaskListByUser(item!!)
//                                    }
                                },
                                {error ->
                                    Log.d("ViewSTDetail Fail: ", error.localizedMessage)
                                })
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


    /**************************************************************************
     * Team Stuff Divider
     **************************************************************************/

    override fun createTeamForProject(projectId: Int,
                                      team_member_userid: Int,
                                      index: Int,
                                      viewModel: TeamViewModel) {
        disposable =
                apiServe.createTeamForProject(
                        projectId!!,
                        team_member_userid!!)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { result ->
                                    Log.d(MIKKI_TEAM, result.toString())
                                    viewModel.removeAddedEmployeeFromView(index)

                                },
                                { error -> Log.d(MIKKI_TEAM, error.message) }
                        )
    }

    override fun getEmployeeList(viewModel: TeamViewModel) {
        disposable = apiServe.getEmployeeList().
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(
                        {
                            result ->
                            Log.d(MIKKI_TEAM, result.employees.toString())
                            for(item in result.employees!!){
                                viewModel.updateList(item!!)
                            }
                        },
                        {
                            error -> Log.d(MIKKI_TEAM, error.message)
                        }
                )
    }


}