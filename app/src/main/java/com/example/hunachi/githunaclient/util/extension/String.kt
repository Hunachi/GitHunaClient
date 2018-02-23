package com.example.hunachi.githunaclient.util.extension

/**
 * Created by hunachi on 2018/02/08.
 */

fun String.convertToLowerText(): String {
    var index = 0
    var string = ""
    this.forEachIndexed { i: Int, c: Char ->
        if (c.isUpperCase()) {
            if (i != 0)
                string += this[index].toLowerCase() + this.substring(index + 1, i) + " "
            index = i
        }
    }
    /*remove "Event"*/
    // s += this[index].toLowerCase() + this.substring(index + 1, this.length)
    return string
}

//todo if you have time, add detail info for these actions.
fun String.convertToActionText(actions: String?) =
        when (this) {
            "CommitCommentEvent"                         -> "committed"
            "CreateEvent"                                -> "created"
            "DeleteEvent"                                -> "deleted"
            "DeploymentEvent"                            -> "deployed"
            "ForkEvent"                                  -> "forked"
            "ForkApplyEvent"                             -> "applied fork"
            "GistEvent"                                  -> "created gits"
            "GollumEvent"                                -> "action on Wiki page" //todo "action on" -> "pages.action"
            "InstallationRepositoriesEvent"              -> actions
            "IssueCommentEvent", "IssuesEvent"           -> "$actions issue"
            "LabelEvent"                                 -> "$actions label"
            "MemberEvent"                                -> "added $actions for"
            "ProjectCardEvent"                           -> "$actions card"
            "ProjectColumnEvent"                         -> "$actions column"
            "ProjectEvent"                               -> "$actions project"
            "PublicEvent"                                -> "made public"
            "PullRequestEvent", "PullRequestReviewEvent" -> "$actions pull request"
            "PullRequestReviewCommentEvent"              -> "$actions pull request comment"
        //"PushEvent"                                   -> { }
            "ReleaseEvent"                               -> "published release"
        //"RepositoryEvent"                            -> { }
        //"StatusEvent"                                -> { }
        //"TeamEvent"                                  -> { }
        //"TeamAddEvent"                               -> { }
            "WatchEvent"                                 -> "staring"
            else                                         -> this.convertToLowerText()
        }

fun String.sepatateOwnerRepo() =
        Pair(substring(0, indexOfFirst { it == '/' }), substring(indexOfFirst { it == '/' } + 1, length))
