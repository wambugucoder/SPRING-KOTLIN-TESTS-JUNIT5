package com.crud.demo.entity

import javax.persistence.*

@Entity
data class Product(
        @Column(nullable = false)
       var  name:String,

        @Column(nullable = false)
       var price:String,

        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        var id:Long=0
)
{
     constructor():this("","")

}
