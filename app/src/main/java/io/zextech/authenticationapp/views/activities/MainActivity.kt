package io.zextech.authenticationapp.views.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import io.zextech.authenticationapp.R
import io.zextech.authenticationapp.preferences.UserPreference

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userPreferences = UserPreference(this)

        userPreferences.authtoken.asLiveData().observe(this, {
            val activity = if (it == null) AuthActivity::class.java else PageActivity::class.java
            var intent = Intent(this, activity)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        })

    }
}