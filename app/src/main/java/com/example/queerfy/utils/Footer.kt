package com.example.queerfy.utils

import android.content.Context
import android.content.Intent

import android.net.Uri
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.example.queerfy.R

class Footer @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : LinearLayout(context, attrs) {

    init {
        LayoutInflater.from(context)
            .inflate(R.layout.footer, this, true)

        val imgTwitter = findViewById<ImageButton>(R.id.imgTwitter)
        val imgFacebook = findViewById<ImageButton>(R.id.imgFacebook)
        val imgInstagram = findViewById<ImageButton>(R.id.imgInstagram)
        val imgLinkedin = findViewById<ImageButton>(R.id.imgLinkedin)

        imgTwitter.setOnClickListener {
            openUrl(TWITTER_URL)
        }

        imgFacebook.setOnClickListener {
            openUrl(FACEBOOK_URL)
        }

        imgInstagram.setOnClickListener {
            openUrl(INSTAGRAM_URL)
        }

        imgLinkedin.setOnClickListener {
            openUrl(LINKEDIN_URL)
        }
    }

    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        ContextCompat.startActivity(context, intent, null)
    }

    companion object {
        const val TWITTER_URL = "http://www.twitter.com.br"
        const val FACEBOOK_URL = "http://www.facebook.com.br"
        const val INSTAGRAM_URL = "http://www.instagram.com.br"
        const val LINKEDIN_URL = "http://www.linkedin.com.br"
    }
}

