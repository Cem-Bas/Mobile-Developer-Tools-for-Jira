package com.cembas.mobiledevelopertoolsforjira

/**
 * Created by cem on 11/16/17.
 */


data class Response3(
		val self: String, //http://www.example.com/jira/rest/api/2/issue/10010/comment/10000
		val id: String, //10000
		val author: Author,
		val body: String, //Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eget venenatis elit. Duis eu justo eget augue iaculis fermentum. Sed semper quam laoreet nisi egestas at posuere augue semper.
		val updateAuthor: UpdateAuthor,
		val created: String, //2017-11-16T07:15:32.654+0000
		val updated: String, //2017-11-16T07:15:32.654+0000
		val visibility: Visibility
)

data class UpdateAuthor(
		val self: String, //http://www.example.com/jira/rest/api/2/user?username=fred
		val name: String, //fred
		val displayName: String, //Fred F. User
		val active: Boolean //false
)

data class Author(
		val self: String, //http://www.example.com/jira/rest/api/2/user?username=fred
		val name: String, //fred
		val displayName: String, //Fred F. User
		val active: Boolean //false
)

data class Visibility(
		val type: String, //role
		val value: String //Administrators
)