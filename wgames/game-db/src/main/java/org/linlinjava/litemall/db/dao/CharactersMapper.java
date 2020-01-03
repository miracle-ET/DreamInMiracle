package org.linlinjava.litemall.db.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.linlinjava.litemall.db.domain.Characters;
import org.linlinjava.litemall.db.domain.example.CharactersExample;

public interface CharactersMapper {
    long countByExample(CharactersExample example);

    int deleteByExample(CharactersExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Characters record);

    int insertSelective(Characters record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table characters
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    Characters selectOneByExample(CharactersExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table characters
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    Characters selectOneByExampleSelective(@Param("example") CharactersExample example, @Param("selective") Characters.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table characters
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    Characters selectOneByExampleWithBLOBs(CharactersExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table characters
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    List<Characters> selectByExampleSelective(@Param("example") CharactersExample example, @Param("selective") Characters.Column ... selective);

    List<Characters> selectByExampleWithBLOBs(CharactersExample example);

    List<Characters> selectByExample(CharactersExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table characters
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    Characters selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") Characters.Column ... selective);

    Characters selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table characters
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    Characters selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    int updateByExampleSelective(@Param("record") Characters record, @Param("example") CharactersExample example);

    int updateByExampleWithBLOBs(@Param("record") Characters record, @Param("example") CharactersExample example);

    int updateByExample(@Param("record") Characters record, @Param("example") CharactersExample example);

    int updateByPrimaryKeySelective(Characters record);

    int updateByPrimaryKeyWithBLOBs(Characters record);

    int updateByPrimaryKey(Characters record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table characters
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int logicalDeleteByExample(@Param("example") CharactersExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table characters
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int logicalDeleteByPrimaryKey(Integer id);
}