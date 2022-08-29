package com.jre.hireout.database.entities.users

import javax.persistence.*

@Entity
@Table(name = "table_roles")
data class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    val id: Long? = null,

    @Column(name = "role_roleName", nullable = false)
    var name: String = "",
)