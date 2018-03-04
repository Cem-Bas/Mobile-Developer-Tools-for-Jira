package com.cembas.mobiledevelopertoolsforjira

/**
 * Created by cem on 11/16/17.
 */

data class Response(
		val expand: String, //names,schema
		val startAt: Int, //0
		val maxResults: Int,
		val total: Int, //1
		val issues: List<Issue>,
		val warningMessages: List<String>
)

data class Issue(
		val expand: String,
		val id: String, //10001
		val self: String, //http://www.example.com/jira/rest/api/2/issue/10001
		val key: String //HSP-1
)