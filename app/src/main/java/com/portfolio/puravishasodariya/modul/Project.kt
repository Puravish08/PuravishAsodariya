package com.portfolio.puravishasodariya.models

data class Project(
    val title: String,
    val description: String,
    val imageResId: Int,
    val technologies: List<String>,
    val githubLink: String
)

data class Project2(
    val title: String,
    val description: String,
    val imageResId: Int,
    val technologies: List<String>,
)

data class Experience(
    val jobTitle: String,
    val companyName: String,
    val duration: String,
    val description: String,
    val companyLogoResId: Int,
    val technologies: List<String>
)

data class Certification(
    val name: String,
    val issuingOrganization: String,
    val issueDate: String,
    val logoResId: Int,
)
//data class Certification(
//    val name: String,
//    val issuingOrganization: String,
//    val issueDate: String,
//    val logoResId: Int,
//    val certificateLink: String
//)