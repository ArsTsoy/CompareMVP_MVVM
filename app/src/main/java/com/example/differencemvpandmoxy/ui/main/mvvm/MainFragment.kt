package com.example.differencemvpandmoxy.ui.main.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.differencemvpandmoxy.databinding.MainFragmentBinding
import com.example.differencemvpandmoxy.dto.User
import com.example.differencemvpandmoxy.model.MockUserService
import com.example.differencemvpandmoxy.recycler_view.user_list.UserListRVAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    //region Vars
    private var privateBinding: MainFragmentBinding? = null

    private val binding: MainFragmentBinding
        get() = requireNotNull(privateBinding)

    private val vm: MainViewModel by viewModels() {
        MainViewModelFactory(MockUserService())
    }

    private val userAdapter = UserListRVAdapter() {
        vm.onUserClicked(it)
    }
    //endregion

    //region Overridden
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        privateBinding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareUI()
    }

    override fun onDestroyView() {
        privateBinding = null
        super.onDestroyView()
    }
    //endregion

    //region UI
    private fun prepareUI() {
        setupRV()
        setupObservers()
//        observeFlows()
    }
    //endregion

    //region Support
    private fun setupRV() = with(binding) {
        rvUsers.adapter = userAdapter
        rvUsers.layoutManager = LinearLayoutManager(requireContext())
        rvUsers.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun setupObservers() = with(vm) {
        observerChosenUser.observe(this@MainFragment.viewLifecycleOwner) {
            Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
        }
        (observerChosenUser as MutableLiveData<List<User>>).postValue(listOf())
        (observerUserList2 as MutableStateFlow<List<User>>).value = listOf()

        observerUserList.observe(this@MainFragment.viewLifecycleOwner) {
            if (it is MainViewModel.RVUpdater.UpdateAll) {

                userAdapter.updateUsers(it.users)
            } else {
                userAdapter.addUsers(it.users)
            }
        }
    }

    private fun observeFlows() {

        this.lifecycleScope


        this.viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            vm.observerUserList2.collectLatest{
                userAdapter.updateUsers(it)
            }
        }
    }
    //endregion

}