package com.example.mikki.projectmanagement.login

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat
import android.support.v4.os.CancellationSignal
import android.widget.ImageView
import android.widget.TextView
import com.example.mikki.projectmanagement.MainActivity
import com.example.mikki.projectmanagement.R

class FingerprintController (
        private val fingerprintManager: FingerprintManagerCompat,
        private val callback: Callback,
        private val title: TextView,
        private val subtitle: TextView,
        private val errorText: TextView,
        private val icon: ImageView) : FingerprintManagerCompat.AuthenticationCallback() {

        /**
         * The signal that gets called if the fingerprint authentication is cancelled.
         */
        private var cancellationSignal: CancellationSignal? = null

        /**
         * Boolean flag for whether or not authentication was cancelled by this controller or something else.
         */
        private var selfCancelled = false

        /**
         * Determines whether or not this device can support fingerprint authentication.
         */
        private val isFingerprintAuthAvailable: Boolean
        get() = fingerprintManager.isHardwareDetected && fingerprintManager.hasEnrolledFingerprints()

        /**
         * Helper variable to get the context from one of the views. The view used is arbitrary.
         */
        private val context: Context
        get() = errorText.context

        /**
         * Runnable that resets the icon and error text as necessary.
         */
        private val resetErrorTextRunnable: Runnable = Runnable {
            errorText.setTextColor(ContextCompat.getColor(context, R.color.hint_color))
            errorText.text = context.getString(R.string.touch_sensor)
            icon.setImageResource(R.drawable.ic_fingerprint_white_24dp)
        }

        init {
            errorText.post(resetErrorTextRunnable)
        }

        /**
         * Begins listening for fingerprint authentication on the device.
         */
        fun startListening(cryptoObject: FingerprintManagerCompat.CryptoObject) {
            if (!isFingerprintAuthAvailable) return

            cancellationSignal = CancellationSignal()
            selfCancelled = false
            fingerprintManager.authenticate(cryptoObject, 0, cancellationSignal, this, null)
        }

        /**
         * Cancels listening for fingerprint authentication. This should be done anytime your activity is killed, so that another app in the system can begin to check for the fingerprint.
         */
        fun stopListening() {
            cancellationSignal?.let {
                selfCancelled = true
                it.cancel()
                cancellationSignal = null
            }
        }

        /**
         * Displays an error to the user if there was a problem with authentication.
         *
         * @param[text] The error message to show.
         */
        private fun showError(text: CharSequence?) {
            icon.setImageResource(R.drawable.ic_error_white_24dp)
            errorText.text = text
            errorText.setTextColor(ContextCompat.getColor(errorText.context, R.color.warning_color))
            errorText.removeCallbacks(resetErrorTextRunnable)
            errorText.postDelayed(resetErrorTextRunnable, ERROR_TIMEOUT_MILLIS)
        }

        override fun onAuthenticationError(errMsgId: Int, errString: CharSequence?) {
            if (!selfCancelled) {
                showError(errString)
                icon.postDelayed({
                    callback.onError()
                }, ERROR_TIMEOUT_MILLIS)
            }
        }

        override fun onAuthenticationSucceeded(result: FingerprintManagerCompat.AuthenticationResult?) {
            errorText.removeCallbacks(resetErrorTextRunnable)
            icon.setImageResource(R.drawable.ic_check_white_24dp)
            errorText.setTextColor(ContextCompat.getColor(errorText.context, R.color.success_color))
            errorText.text = errorText.context.getString(R.string.fingerprint_recognized)
            icon.postDelayed({
                callback.onAuthenticated()
            }, SUCCESS_DELAY_MILLIS)
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }

        override fun onAuthenticationHelp(helpMsgId: Int, helpString: CharSequence?) {
            showError(helpString)
        }

        override fun onAuthenticationFailed() {
            showError(errorText.context.getString(R.string.fingerprint_not_recognized))
        }

        /**
         * Displays a title for the fingerprint authentication flow.
         *
         * @param[title] The text to be displayed.
         */
        fun setTitle(title: CharSequence) {
            this.title.text = title
        }

        /**
         * Displays a subtitle for the fingerprint authentication flow.
         *
         * @param[subtitle] The text to be displayed.
         */
        fun setSubtitle(subtitle: CharSequence) {
            this.subtitle.text = subtitle
        }

        companion object {
            /**
             * The amount of time that we should delay before showing the error message to the user.
             */
            private val ERROR_TIMEOUT_MILLIS = 1600L

            /**
             * The amount of time that we should delay before showing the success message to the user.
             */
            private val SUCCESS_DELAY_MILLIS = 1300L
        }

        /**
         * A callback that allows a class to be updated when fingerprint authentication is complete.
         */
        interface Callback {
            /**
             * Callback method used for a successful fingerprint authentication.
             */
            fun onAuthenticated()

            /**
             * Callback method used if there is any error authenticating the fingerprint.
             */
            fun onError()
        }
}