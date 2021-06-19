package com.schaefer.login.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.schaefer.login.R
import com.schaefer.login.databinding.FragmentLoginBinding
import com.schaefer.navigation.ContainerSingleActivity
import com.schaefer.navigation.home.HomeNavigation
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

private const val ARG_SHOULD_LOGOUT = "should_logout"

class LoginFragment : Fragment() {

    private val homeNavigation: HomeNavigation by inject()
    private val containerSingleActivity: ContainerSingleActivity by inject()
    private val viewModel: LoginViewModel by viewModel()

    private lateinit var binding: FragmentLoginBinding

    private val auth: FirebaseAuth by lazy {
        Firebase.auth
    }

    private val gso: GoogleSignInOptions by lazy {
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.client_id))
            .requestEmail()
            .build()
    }

    private val googleSignInClient: GoogleSignInClient by lazy {
        GoogleSignIn.getClient(requireActivity(), gso)
    }

    private val openSignInActivity =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            GoogleSignIn.getSignedInAccountFromIntent(result.data).apply {
                try {
                    // Google Sign In was successful, authenticate with Firebase
                    val account = this.getResult(ApiException::class.java)
                    account?.idToken?.let { firebaseAuthWithGoogle(it) }
                } catch (e: ApiException) {
                    Timber.e(e)
                    // Google Sign In failed, update UI appropriately
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onStart() {
        super.onStart()
        arguments?.let {
            if (it.getBoolean(ARG_SHOULD_LOGOUT)) {
                auth.signOut().also {
                    viewModel.updateUser(auth.currentUser)
                }
            } else {
                viewModel.updateUser(auth.currentUser)
            }
        } ?: viewModel.updateUser(auth.currentUser)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogin.setOnClickListener {
            signIn()
        }
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.action.observe(viewLifecycleOwner) {
            when (it) {
                LoginAction.NavigateToHome -> navigateToHome()
                is LoginAction.ShowErrorMessage -> TODO()
            }
        }
    }

    private fun navigateToHome() {
        parentFragmentManager.beginTransaction()
            .replace(
                containerSingleActivity.containerId,
                homeNavigation.getFragment(1)
            )
            .commit()
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener { task ->
            viewModel.handleTask(task.isSuccessful, task.exception)
        }
    }

    private fun signIn() {
        openSignInActivity.launch(googleSignInClient.signInIntent)
    }

    companion object {

        fun newInstance(shouldLogout: Boolean? = false) =
            LoginFragment().apply {
                arguments = bundleOf(ARG_SHOULD_LOGOUT to shouldLogout)
            }
    }
}