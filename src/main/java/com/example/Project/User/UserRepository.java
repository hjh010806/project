package com.example.Project.User;


import com.querydsl.core.BooleanBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
<<<<<<< HEAD
=======

import static com.example.Project.User.QSiteUser.siteUser;
>>>>>>> aa24f00274921681faced4aa1b6aeccb532ff0a9

public interface UserRepository extends JpaRepository<SiteUser, Long> {
//    SiteUser findByEmail(String email);

    Optional<SiteUser> findByEmail(String email);

    SiteUser findByName(String name);
    Optional<SiteUser> findById(Long id);
@Query("select u " +
        "from SiteUser u " +
        "where " +
        "  number = :number " +
        "or " +
        "  nickName = :nickName " +
        "or " +
        "  email = :email ")
    List<SiteUser> checklist(String number, String nickName, String email);
<<<<<<< HEAD
=======

//    List<SiteUser> checklist(String number, String nickName, String email) {
//        BooleanBuilder builder = new BooleanBuilder();
//        if (number != null) {
//            builder.or(siteUser.number.eq(number));
//        }
//        if (nickName != null) {
//            builder.or(siteUser.nickName.eq(nickName));
//        }
//        if (email != null) {
//            builder.or(siteUser.email.eq(email));
//        }
//        return queryFactory.selectFrom(siteUser)
//                .where(builder)
//                .fetch();
//    }
>>>>>>> aa24f00274921681faced4aa1b6aeccb532ff0a9
}
