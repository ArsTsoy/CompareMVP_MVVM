package com.example.differencemvpandmoxy.ui.main.mvp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.differencemvpandmoxy.databinding.MainFragmentBinding
import com.example.differencemvpandmoxy.dto.User
import com.example.differencemvpandmoxy.model.MockUserService
import com.example.differencemvpandmoxy.recycler_view.user_list.UserListRVAdapter
import com.example.differencemvpandmoxy.ui.main.mvvm.MainViewModel
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class MainMVPFragment : MvpAppCompatFragment(), MainContract.View {

    //region Vars
    private var privateBinding: MainFragmentBinding? = null

    private val binding: MainFragmentBinding
        get() = requireNotNull(privateBinding)

    private val userAdapter = UserListRVAdapter() {
        presenter.onUserClicked(it)
    }
    //endregion

    //region Presenter init
    @ProvidePresenter
    internal fun providePresenter() = MainPresenter(MockUserService())

    @InjectPresenter
    internal lateinit var presenter: MainPresenter
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
    }

    override fun updateUserList(users: List<User>) {
        userAdapter.updateUsers(users)
    }

    override fun addUsers(users: List<User>) {
        userAdapter.addUsers(users)
    }

    override fun showToast(user: User) {
        Toast.makeText(requireContext(), user.toString(), Toast.LENGTH_SHORT).show()
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
    //endregion


}