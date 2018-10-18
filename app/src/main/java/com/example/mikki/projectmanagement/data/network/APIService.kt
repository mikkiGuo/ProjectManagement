package com.example.mikki.projectmanagement.data.network

import com.example.mikki.projectmanagement.data.model.projectmodel.ProjectList
import com.example.mikki.projectmanagement.data.model.subtaskmodel.SubTaskList
import com.example.mikki.projectmanagement.data.model.SuccessMsg
import com.example.mikki.projectmanagement.data.model.*
import com.example.mikki.projectmanagement.data.model.projectmodel.CreateProjectSuccessMsg
import com.example.mikki.projectmanagement.data.model.projectmodel.ProjectUserTask
import com.example.mikki.projectmanagement.data.model.subtaskmodel.SubTaskByUser
import com.example.mikki.projectmanagement.data.model.subtaskmodel.SubTaskDetailsByUser
import com.example.mikki.projectmanagement.data.model.taskmodel.TaskCreate
import com.example.mikki.projectmanagement.data.model.taskmodel.TaskList
import com.example.mikki.projectmanagement.data.model.taskmodel.TaskMemberList
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    // BASE URL -> http://rjtmobile.com/aamir/pms/android-app/

    /**
     * http://rjtmobile.com/aamir/pms/android-app/
     * pms_login.php?
     * email=aa@aa.com&
     * password=12345678
     */
    @GET("pms_login.php")
    fun login(@Query("email") email:String,
              @Query("password") password:String):Observable<LoginUserInfo>

    // pms_reg.php?first_name=aamir&last_name=husain&email=aa@aa.com&mobile=9876543210&password=12345678&company_size=500&your_role=TL
    @GET("pms_reg.php")
    fun register(@Query("first_name") fname: String,
                 @Query("last_name") lname: String,
                 @Query("email") email: String,
                 @Query("mobile") mobile: String,
                 @Query("password") password: String,
                 @Query("company_size") company_size: String,
                 @Query("your_role") role: String): Observable<SuccessMsg>

    // pms_create_project.php?project_name=ecomm&project_status=1&project_desc=xyz&start_date=2018-04-03&end_date=2018-04-15
    @GET("pms_create_project.php")
    fun getCreateNewProjectStatus(@Query("project_name") projectName: String,
                            @Query("project_status") project_status: String,
                            @Query("project_desc") project_desc: String,
                            @Query("start_date") start_date: String,
                            @Query("end_date") end_date: String):
            Observable<CreateProjectSuccessMsg>

    // pms_projects.php?
    @GET("pms_projects.php")
    fun getProjectList():Observable<ProjectList>

    /// pms_create_task.php?project_id=30&task_name=blah&task_status=1&task_desc=xyz&start_date=2018-04-03&end_date=2018-04-15
    @GET("pms_edit_project.php")
    fun updateProject(@Query("project_id") project_id:String,
                      @Query("project_name") project_name:String,
                      @Query("project_status") project_status:String,
                      @Query("project_desc") project_desc:String,
                      @Query("start_date") start_date:String,
                      @Query("end_end") end_end:String):
            Observable<SuccessMsg>


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

    // pms_assign_task_project.php?task_id=1&project_id=27&team_member_userid=14
    @GET("pms_assign_task_project.php")
    fun assignMemberToTask(@Query("task_id") taskId: String,
                               @Query("project_id") projectId: String,
                               @Query("team_member_userid") userId: String): Observable<SuccessMsg>

    // pms_create_sub_task.php?project_id=27&task_id=1&sub_task_name=category screen image loading&
    // sub_task_status=1&sub_task_desc=xyz&start_date=2018-04-03&end_date=2018-04-15
    @GET("pms_create_sub_task.php")
    fun createNewSubTask(@Query("project_id") projectId: String,
                         @Query("task_id") taskId: String,
                         @Query("sub_task_name") subTaskName: String,
                         @Query("sub_task_status") subTaskStatus: String,
                         @Query("sub_task_desc") subTaskDescription: String,
                         @Query("start_date") subTaskStartDate: String,
                         @Query("end_date") subTaskEndDate: String):
            Observable<SuccessMsg>

    // pms_edit_sub_task_update.php?taskid=1&project_id=27&userid=14&sub_task_status=2&sub_task_name=sub%20task%202&
    // sub_task_desc=testing%20from%20postman%202&start_date=2019-01-01&end_date=2019-01-10&subtaskid=1
    @GET("pms_edit_sub_task_update.php")
    fun editNewSubTask(@Query("taskid") taskId: String,
                       @Query("project_id") projectId: String,
                       @Query("userid") userId: String,
                       @Query("sub_task_status") subTaskStatus: String,
                       @Query("sub_task_name") subTaskName: String,
                       @Query("sub_task_desc") subTaskDescription: String,
                       @Query("start_date") subTaskStartDate: String,
                       @Query("end_date") subTaskEndDate: String,
                       @Query("subtaskid") subTaskId: String):
            Observable<SuccessMsg>

    // pms_project_sub_task_list.php?
    @GET("pms_project_sub_task_list.php")
    fun getSubTaskList():Observable<SubTaskList>

    // pms_assign_sub_task_project.php?task_id=1&subtask_id=1&project_id=27&team_member_userid=14
    @GET("pms_assign_sub_task_project.php")
    fun assignSubTaskToUser(@Query("task_id") taskId: String,
                            @Query("subtask_id") subTaskId: String,
                            @Query("project_id") projectId: String,
                            @Query("team_member_userid") userId: String):
            Observable<SuccessMsg>

    // http://rjtmobile.com/aamir/pms/android-app/pms_view_sub_task_deatil.php?
    // taskid=1&
    // subtask_id=1&
    // project_id=27
    @GET("pms_view_sub_task_deatil.php")
    fun viewSubTaskDetailByUser(@Query("taskid") taskId: String,
                                @Query("subtask_id") subTaskId: String,
                                @Query("project_id") projectId: String):
            Observable<SubTaskDetailsByUser>


    // http://rjtmobile.com/aamir/pms/android-app/pms_team_sub_task.php?
    // taskid=1&
    // subtaskid=1&
    // projectid=27
    @GET("pms_team_sub_task.php")
    fun viewTeamMemberBySubTask(@Query("taskid") taskId: String,
                                @Query("subtaskid") subTaskId: String,
                                @Query("projectid") projectId: String):
            Observable<TaskMemberList>

    // http://rjtmobile.com/aamir/pms/android-app/pms_edit_sub_task_status.php?
    // taskid=1&
    // subtaskid=1&
    // project_id=27&
    // userid=14&
    // subtask_status=2
    @GET("pms_edit_sub_task_status.php")
    fun updateSubTaskStatus(@Query("taskid") taskId: String,
                            @Query("subtask_id") subTaskId: String,
                            @Query("project_id") projectId: String,
                            @Query("userid") userId: String,
                            @Query("subtask_status") subTaskStatusId: String):
            Observable<SuccessMsg>

    // http://rjtmobile.com/aamir/pms/android-app/pms_view_sub_task_priority.php?
    // taskid=1&
    // subtaskid=1&
    // project_id=27&
    // userid=14
    @GET("pms_view_sub_task_priority.php")
    fun viewSubTaskPriority(@Query("taskid") taskId: String,
                            @Query("subtask_id") subTaskId: String,
                            @Query("project_id") projectId: String,
                            @Query("userid") userId: String):
            Observable<SuccessMsg>

    // http://rjtmobile.com/aamir/pms/android-app/pms_view_subtask.php?
    // user_id=15&
    // taskid=1
    @GET("pms_view_subtask.php")
    fun viewAllSubTaskListByUser(@Query("user_id") userId: String,
                                 @Query("taskid") taskId: String):
            Observable<SubTaskByUser>

    /*http://rjtmobile.com/aamir/pms/android-app/
     *pms_create_sub_task.php?
     *project_id=27&
     *task_id=1&
     *sub_task_name=category screen image loading&
     *sub_task_status=1&
     *sub_task_desc=xyz&
     *start_date=2018-04-03&
     *end_date=2018-04-15*/
    @GET("pms_create_sub_task.php")
    fun getCreateNewSubTaskStatus(@Query("project_id") pId:String,
                                  @Query("task_id") taskId:String,
                                  @Query("sub_task_name") subTaskName:String,
                                  @Query("sub_task_status") subTaskStatus:String,
                                  @Query("sub_task_desc") subTaskDesc:String,
                                  @Query("start_date") subTaskSdate:String,
                                  @Query("end_date") subTaskEdate:String):
            Observable<SuccessMsg>


    /* http://rjtmobile.com/aamir/pms/android-app/
     * pms_create_project_team.php?
     * project_id=27&
     * team_member_userid=14
     */
    @GET("pms_create_project_team.php")
    fun createTeamForProject(@Query("project_id") project_id:Int,
                             @Query("team_member_userid") team_member_userid:Int):
            Observable<SuccessMsg>

    /* http://rjtmobile.com/aamir/pms/android-app/
     * pms_employee_list.php?
     */
    @GET("pms_employee_list.php")
    fun getEmployeeList():Observable<EmployeeList>


    /* http://rjtmobile.com/aamir/pms/android-app/
     * pms_project_team.php?
     * project_id=29
     */
    @GET("pms_project_team.php")
    fun getProjectTeamList(@Query("project_id")project_id:Int):
            Observable<ProjectTeam>

    /***
     * http://rjtmobile.com/aamir/pms/android-app/
     * pms_team_member_deatil.php?
     * memberuserid=15
     */
    @GET("pms_team_member_deatil.php")
    fun getMemberDetailForProject(@Query("memberuserid") memberuserid:String):
            Observable<TeamMemberDetail>


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