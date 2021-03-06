package org.linlinjava.litemall.db.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.linlinjava.litemall.db.domain.Maps;
import org.linlinjava.litemall.db.domain.example.MapsExample;

public interface MapsMapper {
    long countByExample(MapsExample example);

    int deleteByExample(MapsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Maps record);

    int insertSelective(Maps record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table maps
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    Maps selectOneByExample(MapsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table maps
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    Maps selectOneByExampleSelective(@Param("example") MapsExample example, @Param("selective") Maps.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table maps
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    List<Maps> selectByExampleSelective(@Param("example") MapsExample example, @Param("selective") Maps.Column ... selective);

    List<Maps> selectByExample(MapsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table maps
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    Maps selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") Maps.Column ... selective);

    Maps selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table maps
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    Maps selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    int updateByExampleSelective(@Param("record") Maps record, @Param("example") MapsExample example);

    int updateByExample(@Param("record") Maps record, @Param("example") MapsExample example);

    int updateByPrimaryKeySelective(Maps record);

    int updateByPrimaryKey(Maps record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table maps
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int logicalDeleteByExample(@Param("example") MapsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table maps
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int logicalDeleteByPrimaryKey(Integer id);
}