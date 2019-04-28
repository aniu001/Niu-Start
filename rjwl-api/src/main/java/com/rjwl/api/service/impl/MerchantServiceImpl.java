package com.rjwl.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rjwl.api.common.annotation.OptLogAnnotation;
import com.rjwl.api.entity.Merchant;
import com.rjwl.api.entity.MerchantBill;
import com.rjwl.api.entity.dto.RechargeMerchantBalanceDto;
import com.rjwl.api.mapper.MerchantBillMapper;
import com.rjwl.api.mapper.MerchantMapper;
import com.rjwl.api.service.MerchantService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author aniu
 * @since 2019-04-23
 */
@Service
public class MerchantServiceImpl extends ServiceImpl<MerchantMapper, Merchant> implements MerchantService {

    @Resource
    private MerchantMapper merchantMapper;
    @Resource
    private MerchantBillMapper merchantBillMapper;

    @Override
    @OptLogAnnotation("商户保存")
    public void saveMerchant(Merchant merchant) {
        merchantMapper.insert(merchant);
    }

    @Override
    @OptLogAnnotation("商户删除")
    public synchronized void removeMerchant(Integer id) {
        merchantMapper.deleteById(id);
    }

    @Override
    @OptLogAnnotation("商户修改")
    public synchronized void modifyMerchant(Merchant merchant) {
        merchantMapper.updateById(merchant);
    }

    @Override
    public Merchant queryMerchant(Integer id) {
        return merchantMapper.selectById(id);
    }

    @Override
    public List<Merchant> queryMerchants() {
        return merchantMapper.selectList(null);
    }

    @Override
    @OptLogAnnotation("商户余额充值")
    @Transactional(rollbackFor = Exception.class)
    public void rechargeMerchantBalance(RechargeMerchantBalanceDto rechargeMerchantBalanceDto) {
        Merchant merchant = merchantMapper.selectById(rechargeMerchantBalanceDto.getId());
        merchantMapper.rechargeMerchantBalance(merchant.getId(),rechargeMerchantBalanceDto.getMoney(),merchant.getVersion());
        //充值成功生成流水
        MerchantBill merchantBill = new MerchantBill();
        merchantBill.setMerchantTag(merchant.getMerchantTag());
        merchantBill.setMoney(rechargeMerchantBalanceDto.getMoney());
        merchantBillMapper.insert(merchantBill);
    }
}
