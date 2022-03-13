package dev.keader.correiostracker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.keader.correiostracker.model.Event
import dev.keader.correiostracker.model.PreferencesManager
import dev.keader.correiostracker.view.capture.CodeDetectionActions
import javax.inject.Inject

@HiltViewModel
class UIViewModel @Inject constructor(
    private val preferences: PreferencesManager
) : ViewModel(), CodeDetectionActions {

    val autoMove = preferences.autoMoveFlow.asLiveData()
    val darkTheme = preferences.darkThemeFlow.asLiveData()
    val frequency = preferences.frequencyFlow.asLiveData()

    private val _bottomNavVisibility = MutableLiveData(true)
    val bottomNavVisibility: LiveData<Boolean> = _bottomNavVisibility

    private val _qrCodeResult = MutableLiveData<Event<String>>()
    val qrCodeResult: LiveData<Event<String>> = _qrCodeResult

    private val _onQrCodeDetected = MutableLiveData<Event<Unit>>()
    val onQrCodeDetected: LiveData<Event<Unit>> = _onQrCodeDetected

    fun setBottomNavVisibility(visible: Boolean) {
        _bottomNavVisibility.value = visible
    }

    override fun onCodeDetected(code: String, source: Int) {
        _qrCodeResult.postValue(Event(code))
        _onQrCodeDetected.postValue(Event(Unit))
    }
}
