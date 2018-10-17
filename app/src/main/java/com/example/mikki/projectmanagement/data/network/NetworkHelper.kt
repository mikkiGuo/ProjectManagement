package com.example.mikki.projectmanagement.data.network

import android.util.Log
import com.example.mikki.projectmanagement.data.IDataManager
import com.example.mikki.projectmanagement.data.model.ProjectAdminTaskItem
import com.example.mikki.projectmanagement.data.model.ProjectSubTaskItem
import com.example.mikki.projectmanagement.data.model.ProjectsItem
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
                                            viewModel.updateList(item!!)
                                        }
                                    }
                                    Log.d("mikkiproject",result.projects.toString()
                                    )
                                },
                                { error -> Log.d("mikkiproject", error.message) }
                        )
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
                                    viewModel.printMsg(result.toString())

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
                            result -> Log.d(MIKKI_TEAM, result.employees.toString())
                        },
                        {
                            error -> Log.d(MIKKI_TEAM, error.message)
                        }
                )
    }


}