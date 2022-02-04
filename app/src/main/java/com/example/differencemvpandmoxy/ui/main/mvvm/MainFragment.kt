package com.example.differencemvpandmoxy.ui.main.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.differencemvpandmoxy.databinding.MainFragmentBinding
import com.example.differencemvpandmoxy.model.MockUserService
import com.example.differencemvpandmoxy.recycler_view.user_list.UserListRVAdapter

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

        observerUserList.observe(this@MainFragment.viewLifecycleOwner) {
            userAdapter.updateUsers(it)
        }
    }

    //endregion

}