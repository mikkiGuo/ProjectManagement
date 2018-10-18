package com.example.mikki.projectmanagement.data.network

import android.util.Log
import com.example.mikki.projectmanagement.data.IDataManager.*
import com.example.mikki.projectmanagement.data.model.*
import com.example.mikki.projectmanagement.data.IDataManager
import com.example.mikki.projectmanagement.data.model.projectmodel.ProjectSubTaskItem
import com.example.mikki.projectmanagement.data.model.projectmodel.ProjectsItem
import com.example.mikki.projectmanagement.data.model.taskmodel.TaskItem
import com.example.mikki.projectmanagement.data.model.taskmodel.TaskMemberItem
import com.example.mikki.projectmanagement.viewmodel.ProjectViewModel
import com.example.mikki.projectmanagement.viewmodel.ViewModelSubTask
import com.example.mikki.projectmanagement.viewmodel.TaskViewModel
import com.example.mikki.projectmanagement.viewmodel.TeamViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.warn

class NetworkHelper : INetworkHelper {

    private val MIKKI_TEAM = "MikkiTeam"
    private val MIKKI_LOGIN = "MikkiLogin"

    private val ninntag = AnkoLogger("ninntag")

    var disposable: Disposable? = null
    val apiServe by lazy {
        APIService.create()
    }

    override fun login(listener: OnLoginListener, loginInfo: LoginInfo) {
        disposable = apiServe.login(
                loginInfo.email!!,
                loginInfo.password!!
        ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    Log.d(MIKKI_LOGIN, result.msg)
                    listener.getUserInfo(result)
                }, { error ->
                    Log.d(MIKKI_LOGIN, error.message)
                })

    }

    override fun register(listener: OnRegisterListener, register: Register) {
        ninntag.warn { "in networkhelper, registering user..." }
        disposable = apiServe.register(
                register.fname,
                register.lname,
                register.email,
                register.mobile,
                register.pass,
                register.compSize,
                register.role)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            if (result.msg!!.get(0).equals("successfully registered")) {
                                ninntag.warn { "registered user " + register.toString() }
                                listener.isRegistered(true)
                            } else {
                                ninntag.warn { result.toString() }
                                listener.isRegistered(false)
                            }
                        }, { error -> ninntag.warn { error.message } })
    }

    /***************************************
     *          Project Stuff
     ***************************************/

    override fun updateProject(listener: IDataManager.OnProjectListListener,
                               p: ProjectsItem,
                               index: Int) {
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
                                { result ->
                                    listener.finishedUpdateProject(p, index)
                                    Log.d("mikkiproject", "Message"
                                            + result.toString()
                                    )
                                },
                                { error -> Log.d("mikkiproject", error.message) }
                        )
    }

    override fun storeNewProjectToServer(listener: OnCreateProjectListener,
                                         p: ProjectsItem) {
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
                                    listener.finishedOnCreateProject(p)
                                },
                                { error -> Log.d("mikkiproject", error.message) }
                        )
    }

    override fun getProjectList(listener: IDataManager.OnProjectListListener) {
        Log.d("mikkiproject", "+++++++++++++++++++++++++++++++++++++++")
        disposable =
                apiServe.getProjectList()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { result ->
                                    for (item in result.projects!!) {
                                        item as ProjectsItem
                                        //if(!item.projectstatus.equals("2") ){
                                        listener.finishedInitialList(item)
                                        //}
                                    }
                                    Log.d("mikkiproject", (result.projects.size.toString())
                                    )
                                },
                                { error -> Log.d("mikkiproject", error.message) }
                        )
    }

    /***************************************
     *          Task Stuff
     ***************************************/

    override fun createTask(viewModel: TaskViewModel, listener: OnAdminCreateTaskListener, taskItem: TaskItem) {
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

    override fun getAdminTaskList(viewModel: TaskViewModel, listener: OnAdminTaskListListener, projectId: Int) {
        var taskList = ArrayList<TaskItem>()
        disposable = apiServe.getAdminTaskList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            for (item in result.task!!) {
                                if (item.projectid!!.toInt() == projectId)
                                    taskList.add(item)
                            }
                            viewModel.showTaskList(listener, taskList)
                        },
                        { error -> viewModel.showTaskList(listener, null) }
                )
    }

    override fun getUserTaskList(viewModel: TaskViewModel, id: String) {
        disposable = apiServe.getUserTaskList(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            ninntag.warn { "result: " + result.toString() }
                        },
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
        ninntag.warn { "nh: getting team member by task" }
        disposable = apiServe.getTeamListByTask(
                taskItem.taskid!!,
                "99",
                taskItem.projectid!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            ninntag.warn { "nh: got result going to show member list" }
                            viewModel.showTaskMemberList(listener, result.members)
                            ninntag.warn { "nh: showed member list" }
                        },
                        { error ->
                            viewModel.showTaskMemberList(listener, null)
                            ninntag.warn { "error: " + error.message }
                        }
                )
    }

    override fun getMemberDetails(viewModel: TaskViewModel,
                                  listener: OnAddMemberDetailsListener,
                                  memberListListener: OnTaskMemberListener,
                                  memberList: ArrayList<TaskMemberItem>?) {

        ninntag.warn { "nh: getting member details" }
        for ((index, value) in memberList!!.withIndex()) {
            disposable = apiServe.getMemberDetails(value.userid!!)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { result ->
                                ninntag.warn { "nh: got result" }
                                viewModel.addMemberDetailsToList(result, index)
                                ninntag.warn { "nh: added details to vm list" }
                                if (index == memberList.size - 1)
                                    listener.finishedAdding(memberListListener)
                                ninntag.warn { "result: " + result.toString() }
                            },
                            { error -> ninntag.warn { "error: " + error.message } }
                    )
        }
    }

    override fun assignMemberToTask(viewModel: TaskViewModel, listener: OnAssignMemberListener, memberItem: TaskMemberItem) {
        disposable = apiServe.assignMemberToTask(
                memberItem.taskid!!,
                memberItem.projectid!!,
                memberItem.userid!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> ninntag.warn { result.toString() } },
                        { error -> ninntag.warn { error.message } }
                )
    }

    /***************************************
     *          SubTask Stuff
     ***************************************/

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
                                { result ->
                                    Log.d("SubTask Success: ", result.msg?.get(0).toString())
                                    listener.createTask(result.msg?.get(0).toString())
                                },
                                { error ->
                                    Log.d("SubTask Fail: ", error.message)
                                    listener.createTask(error.message.toString())
                                }
                        )
    }

    override fun editSubTask(listener: IDataManager.OnAdminEditSubTaskListener, subTask: ProjectSubTaskItem) {
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
                                { result ->
                                    Log.d("SubTaskEdit Success: ", result.msg?.get(0).toString())
                                    listener.editTask(result.msg?.get(0).toString())
                                },
                                { error ->
                                    Log.d("SubTaskEdit Fail: ", error.message)
                                    listener.editTask(error.message.toString())
                                }
                        )
        //listener.editTask("You did it!!")

    }

    override fun editSubTaskStatus(listner: IDataManager.OnUserEditSubTaskStatusListener, subTask: ProjectSubTaskItem) {
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
                                { result ->
                                    Log.d("UpdateStatus Success: ",
                                            result.msg?.get(0).toString())
                                    listner.editSubTaskStatusByUser(result.msg?.get(0).toString())
                                },
                                { error ->
                                    Log.d("UpdateStatus Fail: ", error.message)
                                    listner.editSubTaskStatusByUser(error.localizedMessage)
                                }
                        )
    }

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
                                { result ->
                                    Log.d("StFragList Success: ",
                                            result.msg?.get(0).toString())
                                    listner.assignSubTask(result.msg?.get(0).toString())
                                },
                                { error ->
                                    Log.d("StFragList Fail: ", error.message)
                                    listner.assignSubTask(error.localizedMessage)
                                }
                        )
    }

    override fun viewSubTaskDetailByUser(listner: IDataManager.OnUserAdminViewSubTaskDetailListener, subTask: ProjectSubTaskItem) {

        disposable =
                apiServe.viewSubTaskDetailByUser(
                        subTask.taskid!!,
                        subTask.subtaskid!!,
                        subTask.projectid!!)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { result ->
                                    Log.d("ViewSTDetail Success: ", result.toString())
                                },
                                { error ->
                                    Log.d("ViewSTDetail Fail: ", error.localizedMessage)
                                })

//        @Query("taskid") taskId: String,
//        @Query("subtask_id") subTaskId: String,
//        @Query("project_id") projectId: String)
    }

    override fun viewSubTaskListByUser(viewModelSubTask: ViewModelSubTask, userId: String, taskId: String) {

        disposable =
                apiServe.viewAllSubTaskListByUser(
                        userId,
                        taskId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { result ->
                                    Log.d("ViewSTDetail Success: ", result.viewsubtasks.toString())
//                                    for(item in result.viewsubtasks!!) {
//                                        viewModelSubTask.upadteSubTaskListByUser(item!!)
//                                    }
                                },
                                { error ->
                                    Log.d("ViewSTDetail Fail: ", error.localizedMessage)
                                })
    }

    override fun viewTeamMemberBySubTask(listener: IDataManager.OnAdminViewTeamMemeberBySubTask, subTask: ProjectSubTaskItem) {
        disposable =
                apiServe.viewTeamMemberBySubTask(
                        subTask.taskid!!,
                        subTask.subtaskid!!,
                        subTask.projectid!!)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { result ->
                                    Log.d("ViewSTDetail Success: ", result.members.toString())
                                    var members: ArrayList<MembersItem>?
                                    members = result.members as ArrayList<MembersItem>?
                                    listener.viewTeamMemberBySubTask(members)
                                },
                                { error ->
                                    Log.d("ViewSTDetail Fail: ", error.localizedMessage)
                                })
    }

    override fun getSubTasksList(subTaskViewModel: ViewModelSubTask, taskId: String) {
        Log.d("SubTaskList", "+++++++++++++++++++++++++++++++++++++++")
        disposable =
                apiServe.getSubTaskList()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { result ->
                                    for (item in result.projectSubTask!!) {
                                        if (item?.taskid.equals(taskId)) {
                                            subTaskViewModel.upadteSubTaskList(item!!)
                                        }
                                    }
                                    Log.d("StFragList Success: ",
                                            result.projectSubTask.get(result.projectSubTask.size - 1).toString())
                                },
                                { error -> Log.d("StFragList Fail: ", error.message) }
                        )
    }

    override fun getTeamMemberBySubTask(viewModelSubTask: ViewModelSubTask,
                                        listener: OnTaskMemberListener, subTaskItem: ProjectSubTaskItem) {
        Log.d("nh getTeamMembSubTask", subTaskItem.subtaskid + " " + subTaskItem.taskid + " " + subTaskItem.projectid)
        disposable = apiServe.viewTeamMemberBySubTask(
                subTaskItem.taskid!!,
                subTaskItem.subtaskid!!,
                subTaskItem.projectid!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            viewModelSubTask.showTaskMemberList(listener, result.members!!)
                            Log.d("nh getTeamMembSubTask", "successrittttoooo " + result.members.toString())
                        },
                        { error ->
                            viewModelSubTask.showTaskMemberList(listener, null)
                            Log.d("nh getTeamMembSubTask", "got an error")
                            ninntag.warn { "error: " + error.message }
                        }
                )
    }


    override fun getMemberDetailsSubTask(viewModelSubTask: ViewModelSubTask,
                                         addlistener: OnAddMemberDetailsListener,
                                         memberListListener: OnTaskMemberListener,
                                         memberList: ArrayList<TaskMemberItem>?) {
        Log.d("nh getMemberDetails", memberList.toString())
        for ((index, value) in memberList!!.withIndex()) {
            disposable = apiServe.getMemberDetails(value.userid!!)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { result ->
                                viewModelSubTask.addMemberDetailsToList(result, index)
                                Log.d("nh getMemberDetails", result.toString())
                                if (index == memberList.size - 1)
                                    addlistener.finishedAdding(memberListListener)
                                ninntag.warn { "result: " + result.toString() }
                            },
                            { error -> ninntag.warn { "error: " + error.message } }
                    )
        }

    }

    /**************************************************************************
     * Team Stuff Divider
     **************************************************************************/

    override fun createTeamForProject(listener: IDataManager.OnCreateTeamForProject,
                                      projectId: Int,
                                      team_member_userid: Int,
                                      index: Int) {
        disposable =
                apiServe.createTeamForProject(
                        projectId!!,
                        team_member_userid!!)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { result ->
                                    Log.d(MIKKI_TEAM, result.toString())
                                    listener.finishedAddedMemberToProject(index)

                                },
                                { error -> Log.d(MIKKI_TEAM, error.message) }
                        )
    }


    override fun getEmployeeList(listener: IDataManager.OnCreateTeamForProject) {
        disposable = apiServe.getEmployeeList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                { result ->
                    Log.d(MIKKI_TEAM, result.employees.toString())
                    for (item in result.employees!!) {
                        listener.finishedInitialEmployeeList(item!!)
                    }
                },
                { error ->
                    Log.d(MIKKI_TEAM, error.message)
                }
        )
    }

    override fun getProjectTeamList(listener: OnDisplayProjectTeam, projectId: Int) {
        disposable = apiServe.getProjectTeamList(
                projectId!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            Log.d(MIKKI_TEAM, "getProjectTeamList: " + result.toString())
                            for (item in result.projectteam!!) {
                                listener.finishedGetProjectTeamList(item!!)
                            }
                        },
                        {
                            error("result invalid")
                        }
                )

    }

    override fun getMemberDetailForProjectTeam(listener: OnDisplayProjectTeam,
                                               memberId: String) {
        disposable = apiServe.getMemberDetailForProject(memberId!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    Log.d(MIKKI_TEAM, result.toString())
                    listener.convertToEmployeeListFormat(result)
                }, { error ->
                    Log.d(MIKKI_TEAM, error.message)
                })
    }

}