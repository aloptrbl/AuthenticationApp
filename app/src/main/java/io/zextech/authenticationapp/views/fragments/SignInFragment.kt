package io.zextech.authenticationapp.views.fragments

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.datastore.preferences.core.edit
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.fragment.findNavController
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.textfield.TextInputEditText
import io.zextech.authenticationapp.R
import io.zextech.authenticationapp.preferences.UserPreference
import io.zextech.authenticationapp.preferences.dataStore
import io.zextech.authenticationapp.views.activities.PageActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class SignInFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var email: MutableLiveData<String> = MutableLiveData("")
    private var password: MutableLiveData<String> = MutableLiveData("")
    lateinit var loadingAnimation: LottieAnimationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        GlobalScope.launch {
            context?.dataStore?.edit {
                it[UserPreference.NAME] = "Tester"
                it[UserPreference.EMAIL] = "test@gmail.com"
                it[UserPreference.PASSWORD] = "1234"
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userPreferences = UserPreference(view.context)
        loadingAnimation = view.findViewById(R.id.loadingAnimation)
        var emailinput = view.findViewById<TextInputEditText>(R.id.email_input)
        var passwordinput = view.findViewById<TextInputEditText>(R.id.password_input)
        var signup_btn = view.findViewById<TextView>(R.id.signup_btn)
        var signin_btn =
            view.findViewById<com.google.android.material.button.MaterialButton>(R.id.signin_btn)
        signup_btn.setOnClickListener {
            findNavController().navigate(ActionOnlyNavDirections(R.id.action_signin_to_signup))
        }


        lifecycleScope.launch {
            email.value = context?.dataStore?.data?.firstOrNull()?.get(UserPreference.EMAIL) ?: ""
            password.value =
                context?.dataStore?.data?.firstOrNull()?.get(UserPreference.PASSWORD) ?: ""
        }

        signin_btn.setOnClickListener {
            loadingAnimation.visibility = View.VISIBLE
            Handler().postDelayed(Runnable {
                signIn(view, emailinput, passwordinput, loadingAnimation)
            }, 4000)
        }
    }

    fun signIn(
        view: View,
        emailinput: TextInputEditText,
        passwordinput: TextInputEditText,
        loadingAnimation: LottieAnimationView
    ) {
        if (emailinput.text.toString().equals(email.value) == true && passwordinput.text.toString()
                .equals(password.value) == true
        ) {
            Toast.makeText(view.context, "Sign in successful.", Toast.LENGTH_LONG).show()
            GlobalScope.launch {
                context?.dataStore?.edit {
                    val rnds = (0..10).random()
                    it[UserPreference.TOKEN] = "Tester$rnds"
                }
                val activity = PageActivity::class.java
                var intent = Intent(view.context, activity)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        } else {
            Toast.makeText(view.context, "Wrong credential. Please try again.", Toast.LENGTH_LONG)
                .show()
        }
        loadingAnimation.visibility = View.GONE
    }

//    fun getEmail(): Flow<String>? {
//        val email = stringPreferencesKey("email")
//        val user: Flow<String>? = context?.dataStore?.data?.map { preferences ->
//            preferences[email] ?: ""
//        }
//        return user
////        val activity = PageActivity::class.java
////        var intent = Intent(view.context, activity)
////        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
////        startActivity(intent)
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SignInFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}