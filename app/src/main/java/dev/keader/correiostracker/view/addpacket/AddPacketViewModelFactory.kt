package dev.keader.correiostracker.view.addpacket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.keader.correiostracker.database.dao.ItemDatabaseDAO

class AddPacketViewModelFactory(
        private val dataSource: ItemDatabaseDAO) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddPacketViewModel::class.java)) {
            return AddPacketViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}