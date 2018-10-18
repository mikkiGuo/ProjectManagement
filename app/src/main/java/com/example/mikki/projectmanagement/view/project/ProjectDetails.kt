package com.example.mikki.projectmanagement.view.project

import android.app.DatePickerDialog
import android.app.Fragment
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.mikki.projectmanagement.BuildConfig
import com.example.mikki.projectmanagement.R
import com.example.mikki.projectmanagement.data.IDataManager
import com.example.mikki.projectmanagement.data.model.projectmodel.ProjectsItem
import com.example.mikki.projectmanagement.utils.validator.CustomNameValidator
import com.example.mikki.projectmanagement.utils.validator.NonEmptyValidator
import com.example.mikki.projectmanagement.view.task.TaskListFragment
import com.example.mikki.projectmanagement.view.team.TeamForProjectFragment
import com.example.mikki.projectmanagement.viewmodel.ProjectViewModel
import com.github.phajduk.rxvalidator.RxValidator
import kotlinx.android.synthetic.main.frag_project_details.view.*
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.storage.UploadTask
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_register.*
import rx.android.schedulers.AndroidSchedulers
import java.util.*


class ProjectDetails:Fragment(), IDataManager.OnProjectListListener {
    private val MIKKI_RXVALIDATOR = "MikkiValidator"
    private val viewModel = ProjectViewModel()
    lateinit var bundleFrom:Bundle
    val bundleTo:Bundle = Bundle()
    lateinit var projectItem: ProjectsItem
    lateinit var mStorage : StorageReference
    lateinit var uri : Uri
    private val dateFormat = "yyyy-MM-dd"
    private val sdf = SimpleDateFormat(dateFormat, Locale.US)

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val view = inflater!!.inflate(R.layout.frag_project_details,
                container, false)

        if (BuildConfig.FLAVOR.equals("manager")) {
            view.btn_update_project.visibility = View.INVISIBLE

        } else if (BuildConfig.FLAVOR.equals("developer")) {
            view.btn_edit_project.visibility = View.GONE
            view.btn_update_project.visibility = View.VISIBLE

        }

        mStorage = FirebaseStorage.getInstance().getReference();

        bundleFrom = arguments
        projectItem = bundleFrom.getParcelable<ProjectsItem>("data")

        bundleTo.putParcelable("data", projectItem)

        createInputRxValidator(view)
        setValueToUI(view)
        setEnableFalse(view)

        onViewClickedHandler(view)

        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == AppCompatActivity.RESULT_OK) {
            if(requestCode == 1){
                uri = data!!.data
                upload ()
            }

        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun onViewClickedHandler(view:View){
        view.btn_edit_project.setOnClickListener{
            setEnableTrue(view)
        }

        view.tv_add_teammates.setOnClickListener {
            var fragment = TeamForProjectFragment()
            fragment.arguments = bundleTo

            fragmentManager.beginTransaction()
                    .replace(R.id.mainActivity, fragment)
                    .addToBackStack(null).commit()
        }

        view.btn_update_project.setOnClickListener{

            var index = bundleFrom.get("index")
            var updatedProject = setUpdatedProject(view)

            viewModel.updateProject(this, updatedProject, index as Int)

        }

        view.btn_view_tasks.setOnClickListener {
            var fragment = TaskListFragment()

            bundleTo.putInt("projectId", projectItem.id!!.toInt())
            fragment.arguments = bundleTo

            fragmentManager.beginTransaction().add(R.id.mainActivity, fragment).addToBackStack(null).commit()
        }

        view.tv_add_file.setOnClickListener {
            val intent = Intent()
            intent.setType ("image/*")
            intent.setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(Intent.createChooser(intent,
                    "Select Picture"), 1)
        }

        view.tv_startdate_cnp.setOnClickListener {
            Log.d("mikkidate","datepicked+++++++++++++++")
            Toast.makeText(context,"clicked",Toast.LENGTH_LONG).show()
            val dpd = DatePickerDialog(context,
                    DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                val selectedDate = GregorianCalendar(year, monthOfYear, dayOfMonth).time
                it.tv_startdate_cnp.setText(sdf.format(selectedDate))
            }, 2018, 10, 18)
            dpd.show()
        }


        view.tv_enddate_cnp.setOnClickListener {
            val dpd = DatePickerDialog(context, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                val selectedDate = GregorianCalendar(year, monthOfYear, dayOfMonth).time
                it.tv_enddate_cnp.text = (sdf.format(selectedDate))

            }, 2018, 10, 18)
            dpd.show()
        }
    }

    private fun upload(){

        var mReference = mStorage.child(uri.lastPathSegment)
        try {
            mReference.putFile(uri).addOnSuccessListener {
                taskSnapshot: UploadTask.TaskSnapshot? ->
                var url = mReference.downloadUrl!!.toString()
                Log.d("mikkiUrl", url)
                Toast.makeText(context,
                        "Successfully Uploaded : $url",
                        Toast.LENGTH_LONG).show()
            }
        }catch (e: Exception) {
            Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show()
        }
    }

    private fun setUpdatedProject(view: View):ProjectsItem {

        var updatedProject = ProjectsItem()
        updatedProject.id = projectItem.id
        updatedProject.projectname = view.tv_title_cnp.text.toString()
        updatedProject.projectdesc = view.tv_despt_cnp.text.toString()

        updatedProject.startdate = view.tv_startdate_cnp.text.toString()
        updatedProject.endstart = view.tv_enddate_cnp.text.toString()

        var status = view.spinner_project_status.selectedItemPosition

        updatedProject.projectstatus = status.toString()

        return updatedProject
    }

    private fun setEnableFalse(view:View){
        view.tv_title_cnp.isEnabled = false
        view.tv_despt_cnp.isEnabled = false
        view.tv_enddate_cnp.isEnabled = false
        view.tv_startdate_cnp.isEnabled = false
    }

    private fun setEnableTrue(view:View){
        view.tv_title_cnp.isEnabled = true
        view.tv_despt_cnp.isEnabled = true
        view.btn_edit_project.visibility = View.GONE
        view.btn_update_project.visibility = View.VISIBLE
        view.tv_enddate_cnp.isEnabled = true
        view.tv_startdate_cnp.isEnabled = true
    }

    private fun setValueToUI(view:View){
        view.tv_title_cnp.setText(projectItem.projectname)
        view.tv_despt_cnp.setText(projectItem.projectdesc)
        view.tv_startdate_cnp.text = projectItem.startdate
        view.tv_enddate_cnp.text = projectItem.endstart
        var statusPos = projectItem.projectstatus!!.toInt()
        view.spinner_project_status.setSelection(statusPos)
    }


    override fun finishedInitialList(p: ProjectsItem) {
        //do nothing in this fragment
    }

    override fun finishedUpdateProject(p: ProjectsItem, index: Int) {
        Toast.makeText(context,
                "Successfully updated project",
                Toast.LENGTH_SHORT).show()
        var fragment = ProjectListFragment()
        fragmentManager.beginTransaction()
                .replace(R.id.mainActivity, fragment)
                .addToBackStack(null).commit()

    }

    private fun createInputRxValidator(view:View){
        RxValidator.createFor(view.tv_title_cnp)
                .nonEmpty()
                .with(NonEmptyValidator())
                .minLength(2, "Min length is 2")
                .onValueChanged()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    result.item.error = if (result.isProper) null else result.message
                    Log.i(MIKKI_RXVALIDATOR, "Validation result " + result.toString())
                }, { throwable -> Log.e(MIKKI_RXVALIDATOR, "Validation error", throwable) })


    }
}