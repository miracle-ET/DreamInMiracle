package org.linlinjava.litemall.db.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.linlinjava.litemall.db.domain.Charge;
import org.linlinjava.litemall.db.domain.example.ChargeExample;

public interface ChargeMapper {
    long countByExample(ChargeExample example);

    int deleteByExample(ChargeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Charge record);

    int insertSelective(Charge record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table charge
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    Charge selectOneByExample(ChargeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table charge
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    Charge selectOneByExampleSelective(@Param("example") ChargeExample example, @Param("selective") Charge.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table charge
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    List<Charge> selectByExampleSelective(@Param("example") ChargeExample example, @Param("selective") Charge.Column ... selective);

    List<Charge> selectByExample(ChargeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table charge
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    Charge selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") Charge.Column ... selective);

    Charge selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table charge
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    Charge selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    int updateByExampleSelective(@Param("record") Charge record, @Param("example") ChargeExample example);

    int updateByExample(@Param("record") Charge record, @Param("example") ChargeExample example);

    int updateByPrimaryKeySelective(Charge record);

    int updateByPrimaryKey(Charge record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table charge
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int logicalDeleteByExample(@Param("example") ChargeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table charge
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int logicalDeleteByPrimaryKey(Integer id);
}