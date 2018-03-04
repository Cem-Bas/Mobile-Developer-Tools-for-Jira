package com.cembas.mobiledevelopertoolsforjira

/**
 * Created by cem on 11/16/17.
 */

data class Response2(
		val self: String, //http://www.example.com/jira/rest/api/2.0/attachments/10000
		val id: String, //10001
		val filename: String, //picture.jpg
		val author: Author2,
		val created: String, //2017-11-16T07:15:34.957+0000
		val size: Int, //23123
		val mimeType: String, //image/jpeg
		val content: String, //http://www.example.com/jira/attachments/10000
		val thumbnail: String //http://www.example.com/jira/secure/thumbnail/10000
)

data class Author2(
		val self: String, //http://www.example.com/jira/rest/api/2/user?username=fred
		val name: String, //fred
		val key: String, //fred
		val accountId: String, //99:27935d01-92a7-4687-8272-a9b8d3b2ae2e
		val emailAddress: String, //fred@example.com
		val avatarUrls: AvatarUrls,
		val displayName: String, //Fred F. User
		val active: Boolean, //true
		val timeZone: String //Australia/Sydney
)

data class AvatarUrls(
		val a48x48: String, //http://www.example.com/jira/secure/useravatar?size=large&ownerId=fred
		val a24x24: String, //http://www.example.com/jira/secure/useravatar?size=small&ownerId=fred
		val a16x16: String, //http://www.example.com/jira/secure/useravatar?size=xsmall&ownerId=fred
		val a32x32: String //http://www.example.com/jira/secure/useravatar?size=medium&ownerId=fred
)