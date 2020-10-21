package com.clp3z.xapotestapp.repo

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.clp3z.properlytestapp.base.architecture.interfaces.BinderMethods
import com.clp3z.xapotestapp.R
import com.clp3z.xapotestapp.base.database.LocalDatabase
import com.clp3z.xapotestapp.base.factory.ViewModelFactoryBuilder
import com.clp3z.xapotestapp.base.generic.GenericModel
import com.clp3z.xapotestapp.base.generic.ViewModelBinder
import com.clp3z.xapotestapp.databinding.FragmentRepositoryBinding
import com.squareup.picasso.Picasso

/**
 * Created by Clelia López on 10/19/20
 */
class RepoBinder(
    private val fragment: RepoFragment,
    private val binding: FragmentRepositoryBinding
):
    ViewModelBinder<FragmentRepositoryBinding, RepoViewModel>(fragment, binding),
    BinderMethods {

    private var application: Application = fragment.requireActivity().application
    private var arguments: RepoFragmentArgs


    init {
        localDatabase = LocalDatabase.getInstance(application).localDatabaseDao
        arguments = RepoFragmentArgs.fromBundle(fragment.requireArguments())
    }

    override fun createModel(): GenericModel<*> =
        RepoModel(localDatabase, arguments.id)


    override fun createViewModelFactory() =
        ViewModelFactoryBuilder(application, model).build()


    override fun createViewModel() =
        ViewModelProvider(fragment, viewModelFactory).get(RepoViewModel::class.java)


    override fun onBindViewModel() {
        super.onBindViewModel()
        binding.viewModel = viewModel as RepoViewModel
    }

    // TODO: User Adapter Binders to simplify this
    override fun onBindObservers() {
        (viewModel as RepoViewModel).repository.observe(fragment, { repository ->
            binding.forksTextView.text = repository.forks.toString()
            binding.watchersTextView.text = repository.watchers.toString()
            binding.issuesTextView.text = repository.issues.toString()

            Picasso.get()
                .load(repository.owner_avatar)
                .placeholder(R.drawable.placeholder)
                .into(binding.avatarImageView)
        })
    }
}