package com.rjwl.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rjwl.api.entity.Merchant;
import com.rjwl.api.entity.dto.RechargeMerchantBalanceDto;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author aniu
 * @since 2019-04-23
 */
public interface MerchantMapper extends BaseMapper<Merchant> {
    /**
     * @Author aniu
     * @Description 商户余额充值
     * @Date 13:57 2019/4/28
     * @Param [id, money, version]
     * @return void
     **/
    Integer rechargeMerchantBalance(@Param("id") Integer id, @Param("money")BigDecimal money, @Param("version")Integer version);
}
