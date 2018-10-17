package com.example.mikki.projectmanagement.data.network

import com.example.mikki.projectmanagement.data.model.*
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    // BASE URL -> http://rjtmobile.com/aamir/pms/android-app/

    // pms_create_project.php?project_name=ecomm&project_status=1&project_desc=xyz&start_date=2018-04-03&end_date=2018-04-15
    @GET("pms_create_project.php")
    fun getCreateNewProjectStatus(@Query("project_name") projectName: String,
                            @Query("project_name") project_status: String,
                            @Query("project_name") project_desc: String,
                            @Query("project_name") start_date: String,
                            @Query("project_name") end_date: String):
            Observable<SuccessMsg>

    // pms_projects.php?
    @GET("pms_projects.php")
    fun getProjectList():Observable<ProjectList>

    /// pms_create_task.php?project_id=30&task_name=blah&task_status=1&task_desc=xyz&start_date=2018-04-03&end_date=2018-04-15
    @GET("pms_create_task.php")
    fun createNewTask(@Query("project_id") id: String,
                      @Query("task_name") name: String,
                      @Query("task_status") status: String?,
                      @Query("task_desc") desc: String?,
                      @Query("start_date") start: String?,
                      @Query("end_date") end: String?): Observable<TaskCreate>

    // pms_project_task_list.php?
    @GET("pms_project_task_list.php?")
    fun getAdminTaskList(): Observable<TaskList>

    // pms_view_task.php?user_id=14
    @GET("pms_view_task.php")
    fun getUserTaskList(@Query("user_id") id: String): Observable<ProjectUserTask>

    // pms_edit_task_update.php?taskid=1&project_id=27&userid=14&task_status=2&
    // task_name=demo&task_desc=testing from postman&start_date=2019-01-01&end_date=2019-01-10
    @GET("pms_edit_task_update.php")
    fun updateTaskDetails(@Query("taskid") taskID: String,
                 @Query("project_id") projectID: String,
                 @Query("userid") userID: String,
                 @Query("task_status") status: String,
                 @Query("task_name") name: String,
                 @Query("task_desc") desc: String,
                 @Query("start_date") start: String,
                 @Query("end_date") end: String): Observable<SuccessMsg>

    // pms_team_task.php?taskid=1&userid=14&projectid=27
    @GET("pms_team_task.php")
    fun getTeamListByTask(@Query("taskid") taskID: String,
                          @Query("userid") userID: String,
                          @Query("projectid") projectID: String): Observable<TaskMemberList>

    // pms_team_member_deatil.php?memberuserid=15
    @GET("pms_team_member_deatil.php")
    fun getMemberDetails(@Query("memberuserid") userID: String): Observable<MemberDetails>

    // pms_create_sub_task.php?project_id=27&task_id=1&sub_task_name=category screen image loading&
    // sub_task_status=1&sub_task_desc=xyz&start_date=2018-04-03&end_date=2018-04-15
    @GET("pms_create_sub_task.php")
    fun getCreateNewSubTaskStatus(@Query("project_id") pId:String,
                                  @Query("task_id") taskId:String,
                                  @Query("sub_task_name") subTaskName:String,
                                  @Query("sub_task_status") subTaskStatus:String,
                                  @Query("sub_task_desc") subTaskDesc:String,
                                  @Query("start_date") subTaskSdate:String,
                                  @Query("end_date") subTaskEdate:String):
            Observable<SuccessMsg>

    companion object {

        val BASEURL:String = "http://rjtmobile.com/aamir/pms/android-app/"

        fun create(): APIService {

            val retrofit = Retrofit.Builder()
                    .baseUrl(BASEURL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            return retrofit.create(APIService::class.java)
        }
    }
}