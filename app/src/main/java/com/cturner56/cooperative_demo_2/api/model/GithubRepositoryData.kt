package com.cturner56.cooperative_demo_2.api.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Represents a single Github Repository.
 * Structuring of db table based on sample output JSON response.
 *
 * Usage:
 *      Moshi - Utilizes such for a network data model.
 *      Room-DB - Utilizes such as a database entity
 *
 * @property id || Represents the uuid for the repo.
 * @property name || Represents the repo's name.
 * @property fullName || Represents both the author, and repository name conjoined.
 *      Example: "ZG089/Re-Malwack"
 * @property description || A brief overview of the repo.
 * @property starCount || Shows how many users have 'starred' a repo/
 * @property htmlUrl || The url to a given repo.
 *
 * doc-ref (Table is based on example response output):
 * https://docs.github.com/en/rest/repos/repos?apiVersion=2022-11-28#get-a-repository
 */
@Entity(tableName = "github_repos")
@JsonClass(generateAdapter = true)
data class GithubRepository (
    @PrimaryKey
    @Json(name = "id")
    var id: Long = 0,

    @Json(name = "name")
    var name: String = "",

    @Json(name = "full_name")
    var fullName: String = "",

    @Json(name = "description")
    var description: String = "",

    @Json(name = "stargazers_count")
    var starCount: Int = 0,

    @Json(name = "html_url")
    var htmlUrl: String = ""
)

/**
 * Represents a repository's latest release version.
 * Usage:
 *      Moshi - Utilizes such for a network data model.
 *      Room-DB - Utilizes such as a database entity
 *
 *  @property id || Represents the uuid for the repo.
 *  @property repoFullName || A means to link releases back to a repo in the database.
 *  @property tagName || Represents the repo's name.
 *  @property datePublished || Represents the point in which the release was published.
 *  @property releaseNotes || Provides notes for a given repo's release.
 *  @property htmlUrl || The url to a given repo.
 *
 * doc-ref (Table is based on example response output):
 * https://docs.github.com/en/rest/releases/releases?apiVersion=2022-11-28#get-the-latest-release
 */
@Entity(tableName = "github_releases")
@JsonClass(generateAdapter = true)
data class RepositoryReleaseVersion (
    @PrimaryKey
    @Json(name = "id")
    var id: Long = 0,

    var repoFullName: String = "",

    @Json(name = "tag_name")
    var tagName: String = "",

    @Json(name = "published_at")
    var datePublished: String = "",

    @Json(name = "body")
    var releaseNotes: String = "",

    @Json(name = "html_url")
    var htmlUrl: String = ""
)

