package com.wer.dao.visa;

import com.wer.dao.BaseMapper;
import com.wer.entity.visa.VisaArticle;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VisaArticleMapper extends BaseMapper<VisaArticle>{

    VisaArticle queryVisaArticleByCountryName(String countryName);
}