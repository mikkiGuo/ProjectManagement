package com.example.mikki.projectmanagement.data.network

import com.example.mikki.projectmanagement.data.model.*
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    //http://rjtmobile.com/aamir/pms/android-app/pms_create_project.php?
    //project_name=ecomm&project_status=1&project_desc=xyz
    // &start_date=2018-04-03&end_date=2018-04-15

    @GET("pms_create_project.php")
    fun getCreateNewProjectStatus(@Query("project_name") projectName: String,
                            @Query("project_name") project_status: String,
                            @Query("project_name") project_desc: String,
                            @Query("project_name") start_date: String,
                            @Query("project_name") end_date: String):
            Observable<SuccessMsg>

    //http://rjtmobile.com/aamir/pms/android-app/
    //pms_projects.php?
    @GET("pms_projects.php")
    fun getProjectList():Observable<ProjectList>

    /* http://rjtmobile.com/aamir/pms/android-app/
     * pms_create_task.php?
     * project_id=30&
     * task_name=blah&
     * task_status=1&
     * task_desc=xyz&
     * start_date=2018-04-03&
     * end_date=2018-04-15
     */
    @GET("pms_create_task.php")
    fun createNewTask(@Query("project_id") id: String,
                      @Query("task_name") name: String,
                      @Query("task_status") status: String?,
                      @Query("task_desc") desc: String?,
                      @Query("start_date") start: String?,
                      @Query("end_date") end: String?): Observable<SuccessMsg>

    /* http://rjtmobile.com/aamir/pms/android-app/
     * pms_project_task_list.php?
     */
    @GET("pms_project_task_list.php?")
    fun getAdminTaskList(): Observable<ProjectAdminTask>

    /* http://rjtmobile.com/aamir/pms/android-app/
     * pms_view_task.php?
     * user_id=14
     */
    @GET("pms_view_task.php")
    fun getUserTaskList(@Query("user_id") id: String): Observable<ProjectUserTask>

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