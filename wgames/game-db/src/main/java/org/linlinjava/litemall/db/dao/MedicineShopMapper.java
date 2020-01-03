package org.linlinjava.litemall.db.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.linlinjava.litemall.db.domain.MedicineShop;
import org.linlinjava.litemall.db.domain.example.MedicineShopExample;

public interface MedicineShopMapper {
    long countByExample(MedicineShopExample example);

    int deleteByExample(MedicineShopExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MedicineShop record);

    int insertSelective(MedicineShop record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table medicine_shop
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    MedicineShop selectOneByExample(MedicineShopExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table medicine_shop
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    MedicineShop selectOneByExampleSelective(@Param("example") MedicineShopExample example, @Param("selective") MedicineShop.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table medicine_shop
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    List<MedicineShop> selectByExampleSelective(@Param("example") MedicineShopExample example, @Param("selective") MedicineShop.Column ... selective);

    List<MedicineShop> selectByExample(MedicineShopExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table medicine_shop
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    MedicineShop selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") MedicineShop.Column ... selective);

    MedicineShop selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table medicine_shop
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    MedicineShop selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    int updateByExampleSelective(@Param("record") MedicineShop record, @Param("example") MedicineShopExample example);

    int updateByExample(@Param("record") MedicineShop record, @Param("example") MedicineShopExample example);

    int updateByPrimaryKeySelective(MedicineShop record);

    int updateByPrimaryKey(MedicineShop record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table medicine_shop
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int logicalDeleteByExample(@Param("example") MedicineShopExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table medicine_shop
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int logicalDeleteByPrimaryKey(Integer id);
}