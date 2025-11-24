package com.cturner56.cooperative_demo_2.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cturner56.cooperative_demo_2.api.Api
import com.cturner56.cooperative_demo_2.api.model.GithubRepository
import com.cturner56.cooperative_demo_2.api.model.RepositoryReleaseVersion
import kotlinx.coroutines.launch
import java.io.IOException

/**
 * Purpose - A ViewModel which exposes relevant repo information, and
 * release details for a public Github repository.
 */
class GithubViewModel : ViewModel() {

    // Holds the state for fetched repository details.
    private val _repositoryState = mutableStateOf<GithubRepository?>(null)
    val repositoryState: State<GithubRepository?> = _repositoryState

    // Holds the state for fetched release details.
    private val _releaseState = mutableStateOf<RepositoryReleaseVersion?>(null)
    val releaseState: State<RepositoryReleaseVersion?> = _releaseState

    // Holds the state for any error messages that could occur during retrieval.
    private val _errorState = mutableStateOf<String?>(null)
    val errorState: State<String?> = _errorState

    /**
     * Fetches latest repository information, and it's corresponding release data.
     *
     * The function launches a coroutine in the [viewModelScope].
     * It makes async network calls to retrieve data, and updates the respective states
     * [repositoryState] && [releaseState]. In the event of an error, it'll update [errorState]
     */
    fun fetchRepoData(owner: String, repo: String) {
        viewModelScope.launch {
            try {
                val repository = Api.retrofitService.getRepository(owner, repo)
                val release = Api.retrofitService.getLatestRelease(owner, repo)

                _repositoryState.value = repository
                _releaseState.value = release
                _errorState.value = null // Preventing previous errors from displaying

                Log.i("CIT - GithubViewModel", "Data successfully fetched or $owner/$repo")

            } catch (e: IOException) {
                // Handles network error output.
                _errorState.value = "Network error has occurred, please connect to the internet"
                Log.e("CIT - GithubViewModel", "Network error has occurred, ${e.message}")
            } catch (e: Exception) {
                _errorState.value = "Sorry, we've hit a wall. Another error has occurred ${e.message}"
                Log.e("CIT - GithubViewModel", "M'lord, we doth have an error which is most unexpected ${e.message}")
            }
        }
    }
}