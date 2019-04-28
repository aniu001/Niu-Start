package com.rjwl.api.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rjwl.api.common.json.JsonResp;
import com.rjwl.api.entity.Merchant;
import com.rjwl.api.entity.dto.RechargeMerchantBalanceDto;
import com.rjwl.api.service.MerchantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author aniu
 * @since 2019-04-23
 */
@RestController
@RequestMapping("/api/merchant")
@Api(value = "merchant", description = "商户相关服务")
public class MerchantController {
    @Resource
    private MerchantService merchantService;

    /**
     * 添加商户
     *
     * @return
     */
    @PostMapping(value = "/addMerchant")
    @ApiOperation(value = "添加商户", notes = "merchant全字段除id,gmt,upt,version")
    @RequiresPermissions(value = "merchant:operation")
    public JsonResp<?> saveMerchant(@ApiParam(name = "merchant", value = "传入json格式", required = true) @RequestBody Merchant merchant) {
        merchantService.saveMerchant(merchant);
        return JsonResp.success(merchant);
    }

    /**
     * 删除商户
     *
     * @return
     */
    @DeleteMapping(value = "/deleteMerchant/{id}")
    @ApiOperation(value = "删除商户", notes = "字段id必须")
    @RequiresPermissions(value = "merchant:operation")
    public JsonResp<?> deleteMerchant(@ApiParam(name = "id", value = "传入id", required = true) @PathVariable("id") Integer id) {
        merchantService.removeMerchant(id);
        return JsonResp.success(id);
    }

    /**
     * 修改商户
     *
     * @return
     */
    @PutMapping(value = "/updateMerchant")
    @ApiOperation(value = "修改商户", notes = "字段id必须")
    @RequiresPermissions(value = "merchant:operation")
    public JsonResp<?> updateMerchant(@ApiParam(name = "merchant", value = "传入json格式", required = true) @RequestBody Merchant merchant) {
        merchantService.modifyMerchant(merchant);
        return JsonResp.success(merchant);
    }

    /**
     * 查询单个商户
     *
     * @return
     */
    @GetMapping(value = "/selectMerchant")
    @ApiOperation(value = "查询单个商户", notes = "字段id必须")
    @RequiresPermissions(value = "merchant:operation")
    public JsonResp<?> selectMerchant(@ApiParam(name = "id", value = "传入id", required = true) @RequestParam("id") Integer id) {
        Merchant merchant = merchantService.queryMerchant(id);
        return JsonResp.success(merchant);
    }

    /**
     * 查询分页商户
     *
     * @return
     */
    @GetMapping(value = "/selectPageMerchant")
    @ApiOperation(value = "查询分页商户", notes = "字段pageNum,pageSize必须")
    @RequiresPermissions(value = "merchant:operation")
    public JsonResp<?> selectPageMerchant(@ApiParam(name = "pageNum", value = "传入pageNum", required = true) @RequestParam("pageNum") Integer pageNum,
                                          @ApiParam(name = "pageSize", value = "传入pageSize", required = true) @RequestParam("pageSize") Integer pageSize
    ) {
        PageHelper.startPage(pageNum,pageSize);
        List<Merchant> merchants = merchantService.queryMerchants();
        PageInfo<Merchant> pageInfo = new PageInfo<>(merchants);
        return JsonResp.success(pageInfo);
    }

    /**
     * 商户余额充值（充值、扣减为充值负数）
     *
     * @return
     */
    @PutMapping(value = "/rechargeMerchantBalance")
    @ApiOperation(value = "商户余额充值（充值、扣减为充值负数）", notes = "字段id必须、充值金额")
    @RequiresPermissions(value = "merchant:operation")
    public JsonResp<?> rechargeMerchantBalance(@ApiParam(name = "rechargeMerchantBalanceDto", value = "传入json格式", required = true) @RequestBody RechargeMerchantBalanceDto rechargeMerchantBalanceDto) {
        merchantService.rechargeMerchantBalance(rechargeMerchantBalanceDto);
        return JsonResp.success(rechargeMerchantBalanceDto);
    }
}

