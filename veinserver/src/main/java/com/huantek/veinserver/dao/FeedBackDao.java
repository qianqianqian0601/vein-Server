package com.huantek.veinserver.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface FeedBackDao {

    void feedbackMessageAndIMG(@Param("ideaType") Integer ideaType, @Param("ideaInfo") String ideaInfo, @Param("e_mail") String e_mail, @Param("img") String urls);

    void feedbackMessage(@Param("ideaType") Integer ideaType, @Param("ideaInfo") String ideaInfo, @Param("e_mail") String e_mail);
}
