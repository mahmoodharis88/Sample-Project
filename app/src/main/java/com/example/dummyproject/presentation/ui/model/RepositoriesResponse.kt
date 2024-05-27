package com.example.dummyproject.presentation.ui.model


import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
@Keep
data class RepositoriesResponse(
    @SerializedName("incomplete_results")
    var incompleteResults: Boolean = false, // false
    @SerializedName("items")
    var items: List<Item> = listOf(),
    @SerializedName("total_count")
    var totalCount: Int = 0 // 325779
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0

    @Keep
    data class Item(
        var expand: Boolean = false,
        @SerializedName("allow_forking")
        var allowForking: Boolean = false, // true
        // true
        @SerializedName("archive_url")
        var archiveUrl: String = "", // https://api.github.com/repos/golang/go/{archive_format}{/ref}
        @SerializedName("archived")
        var archived: Boolean = false, // false
        @SerializedName("assignees_url")
        var assigneesUrl: String = "", // https://api.github.com/repos/golang/go/assignees{/user}
        @SerializedName("blobs_url")
        var blobsUrl: String = "", // https://api.github.com/repos/golang/go/git/blobs{/sha}
        @SerializedName("branches_url")
        var branchesUrl: String = "", // https://api.github.com/repos/golang/go/branches{/branch}
        @SerializedName("clone_url")
        var cloneUrl: String = "", // https://github.com/golang/go.git
        @SerializedName("collaborators_url")
        var collaboratorsUrl: String = "", // https://api.github.com/repos/golang/go/collaborators{/collaborator}
        @SerializedName("comments_url")
        var commentsUrl: String = "", // https://api.github.com/repos/golang/go/comments{/number}
        @SerializedName("commits_url")
        var commitsUrl: String = "", // https://api.github.com/repos/golang/go/commits{/sha}
        @SerializedName("compare_url")
        var compareUrl: String = "", // https://api.github.com/repos/golang/go/compare/{base}...{head}
        @SerializedName("contents_url")
        var contentsUrl: String = "", // https://api.github.com/repos/golang/go/contents/{+path}
        @SerializedName("contributors_url")
        var contributorsUrl: String = "", // https://api.github.com/repos/golang/go/contributors
        @SerializedName("created_at")
        var createdAt: String = "", // 2014-08-19T04:33:40Z
        @SerializedName("default_branch")
        var defaultBranch: String = "", // master
        @SerializedName("deployments_url")
        var deploymentsUrl: String = "", // https://api.github.com/repos/golang/go/deployments
        @SerializedName("description")
        var description: String = "", // The Go programming language
        @SerializedName("disabled")
        var disabled: Boolean = false, // false
        @SerializedName("downloads_url")
        var downloadsUrl: String = "", // https://api.github.com/repos/golang/go/downloads
        @SerializedName("events_url")
        var eventsUrl: String = "", // https://api.github.com/repos/golang/go/events
        @SerializedName("fork")
        var fork: Boolean = false, // false
        @SerializedName("forks")
        var forks: Int = 0, // 14935
        @SerializedName("forks_count")
        var forksCount: Int = 0, // 14935
        @SerializedName("forks_url")
        var forksUrl: String = "", // https://api.github.com/repos/golang/go/forks
        @SerializedName("full_name")
        var fullName: String = "", // golang/go
        @SerializedName("git_commits_url")
        var gitCommitsUrl: String = "", // https://api.github.com/repos/golang/go/git/commits{/sha}
        @SerializedName("git_refs_url")
        var gitRefsUrl: String = "", // https://api.github.com/repos/golang/go/git/refs{/sha}
        @SerializedName("git_tags_url")
        var gitTagsUrl: String = "", // https://api.github.com/repos/golang/go/git/tags{/sha}
        @SerializedName("git_url")
        var gitUrl: String = "", // git://github.com/golang/go.git
        @SerializedName("has_downloads")
        var hasDownloads: Boolean = false, // true
        @SerializedName("has_issues")
        var hasIssues: Boolean = false, // true
        @SerializedName("has_pages")
        var hasPages: Boolean = false, // false
        @SerializedName("has_projects")
        var hasProjects: Boolean = false, // true
        @SerializedName("has_wiki")
        var hasWiki: Boolean = false, // true
        @SerializedName("homepage")
        var homepage: String = "", // https://go.dev
        @SerializedName("hooks_url")
        var hooksUrl: String = "", // https://api.github.com/repos/golang/go/hooks
        @SerializedName("html_url")
        var htmlUrl: String = "", // https://github.com/golang/go
        @SerializedName("id")
        var id: Int = 0, // 23096959
        @SerializedName("is_template")
        var isTemplate: Boolean = false, // false
        @SerializedName("issue_comment_url")
        var issueCommentUrl: String = "", // https://api.github.com/repos/golang/go/issues/comments{/number}
        @SerializedName("issue_events_url")
        var issueEventsUrl: String = "", // https://api.github.com/repos/golang/go/issues/events{/number}
        @SerializedName("issues_url")
        var issuesUrl: String = "", // https://api.github.com/repos/golang/go/issues{/number}
        @SerializedName("keys_url")
        var keysUrl: String = "", // https://api.github.com/repos/golang/go/keys{/key_id}
        @SerializedName("labels_url")
        var labelsUrl: String = "", // https://api.github.com/repos/golang/go/labels{/name}
        @SerializedName("language")
        var language: String = "", // Go
        @SerializedName("languages_url")
        var languagesUrl: String = "", // https://api.github.com/repos/golang/go/languages
        @SerializedName("license")
        var license: License = License(),
        @SerializedName("merges_url")
        var mergesUrl: String = "", // https://api.github.com/repos/golang/go/merges
        @SerializedName("milestones_url")
        var milestonesUrl: String = "", // https://api.github.com/repos/golang/go/milestones{/number}
        @SerializedName("mirror_url")
        var mirrorUrl: String = "", // null
        @SerializedName("name")
        var name: String = "", // go
        @SerializedName("node_id")
        var nodeId: String = "", // MDEwOlJlcG9zaXRvcnkyMzA5Njk1OQ==
        @SerializedName("notifications_url")
        var notificationsUrl: String = "", // https://api.github.com/repos/golang/go/notifications{?since,all,participating}
        @SerializedName("open_issues")
        var openIssues: Int = 0, // 7781
        @SerializedName("open_issues_count")
        var openIssuesCount: Int = 0, // 7781
        @SerializedName("owner")
        var owner: Owner = Owner(),
        @SerializedName("private")
        var `private`: Boolean = false, // false
        @SerializedName("pulls_url")
        var pullsUrl: String = "", // https://api.github.com/repos/golang/go/pulls{/number}
        @SerializedName("pushed_at")
        var pushedAt: String = "", // 2022-06-09T07:28:50Z
        @SerializedName("releases_url")
        var releasesUrl: String = "", // https://api.github.com/repos/golang/go/releases{/id}
        @SerializedName("score")
        var score: Double = 0.0, // 1.0
        @SerializedName("size")
        var size: Int = 0, // 308039
        @SerializedName("ssh_url")
        var sshUrl: String = "", // git@github.com:golang/go.git
        @SerializedName("stargazers_count")
        var stargazersCount: Int = 0, // 100307
        @SerializedName("stargazers_url")
        var stargazersUrl: String = "", // https://api.github.com/repos/golang/go/stargazers
        @SerializedName("statuses_url")
        var statusesUrl: String = "", // https://api.github.com/repos/golang/go/statuses/{sha}
        @SerializedName("subscribers_url")
        var subscribersUrl: String = "", // https://api.github.com/repos/golang/go/subscribers
        @SerializedName("subscription_url")
        var subscriptionUrl: String = "", // https://api.github.com/repos/golang/go/subscription
        @SerializedName("svn_url")
        var svnUrl: String = "", // https://github.com/golang/go
        @SerializedName("tags_url")
        var tagsUrl: String = "", // https://api.github.com/repos/golang/go/tags
        @SerializedName("teams_url")
        var teamsUrl: String = "", // https://api.github.com/repos/golang/go/teams
        @SerializedName("topics")
        var topics: List<String> = listOf(),
        @SerializedName("trees_url")
        var treesUrl: String = "", // https://api.github.com/repos/golang/go/git/trees{/sha}
        @SerializedName("updated_at")
        var updatedAt: String = "", // 2022-06-09T07:30:40Z
        @SerializedName("url")
        var url: String = "", // https://api.github.com/repos/golang/go
        @SerializedName("visibility")
        var visibility: String = "", // public
        @SerializedName("watchers")
        var watchers: Int = 0, // 100307
        @SerializedName("watchers_count")
        var watchersCount: Int = 0 // 100307
    ) {
        @Keep
        data class License(
            @SerializedName("key")
            var key: String = "", // bsd-3-clause
            @SerializedName("name")
            var name: String = "", // BSD 3-Clause "New" or "Revised" License
            @SerializedName("node_id")
            var nodeId: String = "", // MDc6TGljZW5zZTU=
            @SerializedName("spdx_id")
            var spdxId: String = "", // BSD-3-Clause
            @SerializedName("url")
            var url: String = "" // https://api.github.com/licenses/bsd-3-clause
        )

        @Keep
        data class Owner(
            @SerializedName("avatar_url")
            var avatarUrl: String = "", // https://avatars.githubusercontent.com/u/4314092?v=4
            @SerializedName("events_url")
            var eventsUrl: String = "", // https://api.github.com/users/golang/events{/privacy}
            @SerializedName("followers_url")
            var followersUrl: String = "", // https://api.github.com/users/golang/followers
            @SerializedName("following_url")
            var followingUrl: String = "", // https://api.github.com/users/golang/following{/other_user}
            @SerializedName("gists_url")
            var gistsUrl: String = "", // https://api.github.com/users/golang/gists{/gist_id}
            @SerializedName("gravatar_id")
            var gravatarId: String = "",
            @SerializedName("html_url")
            var htmlUrl: String = "", // https://github.com/golang
            @SerializedName("id")
            var id: Int = 0, // 4314092
            @SerializedName("login")
            var login: String = "", // golang
            @SerializedName("node_id")
            var nodeId: String = "", // MDEyOk9yZ2FuaXphdGlvbjQzMTQwOTI=
            @SerializedName("organizations_url")
            var organizationsUrl: String = "", // https://api.github.com/users/golang/orgs
            @SerializedName("received_events_url")
            var receivedEventsUrl: String = "", // https://api.github.com/users/golang/received_events
            @SerializedName("repos_url")
            var reposUrl: String = "", // https://api.github.com/users/golang/repos
            @SerializedName("site_admin")
            var siteAdmin: Boolean = false, // false
            @SerializedName("starred_url")
            var starredUrl: String = "", // https://api.github.com/users/golang/starred{/owner}{/repo}
            @SerializedName("subscriptions_url")
            var subscriptionsUrl: String = "", // https://api.github.com/users/golang/subscriptions
            @SerializedName("type")
            var type: String = "", // Organization
            @SerializedName("url")
            var url: String = "" // https://api.github.com/users/golang
        )
    }
}