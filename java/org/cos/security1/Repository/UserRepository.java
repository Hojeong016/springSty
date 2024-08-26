package org.cos.security1.Repository;

import org.cos.security1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
//CRUD 함수를 JPArepository가 들고 있음
//@Repository 어노테이션이 없어도 JpaRepository를 상속 받고 있기 때문에 ioc 가 된다
public interface UserRepository extends JpaRepository<User,Integer> {

    User findByUsername(String username); // Jpa query method

    //findBy 규칙 -> Username 문법
   // select * from user where username = ?
    //jpa name 함수

}
