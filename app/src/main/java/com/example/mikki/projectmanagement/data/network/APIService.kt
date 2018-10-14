package com.example.mikki.projectmanagement.data.network

import com.example.mikki.projectmanagement.data.model.ProjectList
import com.example.mikki.projectmanagement.data.model.SubTaskList
import com.example.mikki.projectmanagement.data.model.SuccessMsg
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
                            @Query("project_status") project_status: String,
                            @Query("project_desc") project_desc: String,
                            @Query("start_date") start_date: String,
                            @Query("end_date") end_date: String):
            Observable<SuccessMsg>

    //http://rjtmobile.com/aamir/pms/android-app/pms_create_sub_task.php?
    // project_id=27&task_id=1&sub_task_name=category screen image loading&sub_task_status=1&
    // sub_task_desc=xyz&start_date=2018-04-03&end_date=2018-04-15
    @GET("pms_create_sub_task.php")
    fun getCreateNewSubTask(@Query("project_id") projectId: String,
                            @Query("task_id") taskId: String,
                            @Query("sub_task_name") subTaskName: String,
                            @Query("sub_task_status") subTaskStatus: String,
                            @Query("sub_task_desc") subTaskDescription: String,
                            @Query("start_date") subTaskStartDate: String,
                            @Query("end_date") subTaskEndDate: String):
            Observable<SuccessMsg>

    //http://rjtmobile.com/aamir/pms/android-app/pms_project_sub_task_list.php?
    @GET("pms_project_sub_task_list.php")
    fun getSubTaskList():Observable<SubTaskList>

    //http://rjtmobile.com/aamir/pms/android-app/
    //pms_projects.php?
    @GET("pms_projects.php")
    fun getProjectList():Observable<ProjectList>

    //http://rjtmobile.com/aamir/pms/android-app/
    // pms_create_sub_task.php?
    // project_id=27&
    // task_id=1&
    // sub_task_name=category screen image loading&
    // sub_task_status=1&
    // sub_task_desc=xyz&
    // start_date=2018-04-03&
    // end_date=2018-04-15

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