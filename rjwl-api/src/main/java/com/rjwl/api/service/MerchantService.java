package com.rjwl.api.service;

import com.rjwl.api.entity.Merchant;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rjwl.api.entity.dto.RechargeMerchantBalanceDto;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author aniu
 * @since 2019-04-23
 */
public interface MerchantService extends IService<Merchant> {
    /**
     * 新增商户
     * @param merchant
     */
    void saveMerchant(Merchant merchant);

    /**
     * 删除商户
     * @param id
     */
    void removeMerchant(Integer id);

    /**
     * 更新商户
     * @param merchant
     */
    void modifyMerchant(Merchant merchant);

    /**
     * 查询单个商户
     * @param id
     * @return
     */
    Merchant queryMerchant(Integer id);

    /**
     * 查询所有商户
     * @return
     */
    List<Merchant> queryMerchants();

    /**
     * @Author aniu
     * @Description 商户充值
     * @Date 11:31 2019/4/28
     * @Param [rechargeMerchantBalanceDto]
     * @return void
     **/
    void rechargeMerchantBalance(RechargeMerchantBalanceDto rechargeMerchantBalanceDto);
}
