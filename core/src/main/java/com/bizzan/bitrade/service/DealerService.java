package com.bizzan.bitrade.service;


import com.bizzan.bitrade.constant.PageModel;
import com.bizzan.bitrade.dao.DealerDao;
import com.bizzan.bitrade.dao.DealerMemberRelationDao;
import com.bizzan.bitrade.dao.MemberDao;
import com.bizzan.bitrade.dto.FlowDealerDto;
import com.bizzan.bitrade.entity.Dealer;
import com.bizzan.bitrade.entity.DealerMemberRelation;
import com.bizzan.bitrade.entity.DealerVO;
import com.bizzan.bitrade.entity.Member;
import com.bizzan.bitrade.service.Base.BaseService;
import com.bizzan.bitrade.util.MessageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Slf4j
@Service
public class DealerService extends BaseService<Dealer> {

    @Autowired
    private DealerDao dealerDao;
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private DealerMemberRelationDao dealerMemberRelationDao;

    public List<DealerVO> findDealerVOByCountDesc(Date queryTime, PageModel pageModel,Long memberId) {
        List<DealerVO> list = new ArrayList<>();
        List<Dealer> dealers = dealerDao.queryDealerVO(queryTime, pageModel.getPageable().getPageSize());
        if (dealers == null || dealers.size() <= 0){
            return list;
        }
        dealers.stream().forEach(item->{
            DealerVO dealerVO = new DealerVO();
            dealerVO.setId(item.getId());
            dealerVO.setMemberId(item.getMemberId());
            dealerVO.setFlowCount(item.getFlowCount());
            //根据memberID获取member信息
            Member one = memberDao.findOne(item.getMemberId());
            if (one != null){
                dealerVO.setAvatar(one.getAvatar());
                dealerVO.setUsername(one.getUsername());
            }
            if (memberId != null){
                //查关联表
                DealerMemberRelation byMemberIdAndDealerId = dealerMemberRelationDao.findByMemberIdAndDealerId(memberId, item.getId());
                if (byMemberIdAndDealerId == null){
                    dealerVO.setIfFlowed(false);
                }else{
                    dealerVO.setIfFlowed(true);
                }
            }else{
                dealerVO.setIfFlowed(false);
            }
            list.add(dealerVO);
        });
        return list;
    }

    @Transactional
    public MessageResult flow(FlowDealerDto flowDealerDto) {
        Dealer one = dealerDao.findOne(flowDealerDto.getDealerId());
        if (one == null) {
            return new MessageResult(500, "交易员不存在!");
        }
        //判断跟单用户是否是交易员 如果是则 不允许跟单
        Dealer two = dealerDao.findDealerByMemberId(flowDealerDto.getMemberId());
        if ( two != null){
            return new MessageResult(500, "交易员不允许做跟单操作!");
        }
        if (flowDealerDto.getIfFollowed()){
            //添加关联
            DealerMemberRelation dealerMemberRelation = new DealerMemberRelation();
            dealerMemberRelation.setDealerId(flowDealerDto.getDealerId());
            dealerMemberRelation.setMemberId(flowDealerDto.getMemberId());
            dealerMemberRelationDao.save(dealerMemberRelation);
            //跟单数+1
            one.setFlowCount(one.getFlowCount() + 1);
            dealerDao.save(one);
        }else{
            //取消关联
            dealerMemberRelationDao.deleteDealerMemberRelationByDealerIdAndMemberId(flowDealerDto.getDealerId(),flowDealerDto.getMemberId());
            //跟单数-1
            one.setFlowCount(one.getFlowCount() - 1 >=0 ? one.getFlowCount() - 1 : 0 );
            dealerDao.save(one);
        }
        return MessageResult.success();
    }

    public MessageResult ifFlowed(FlowDealerDto flowDealerDto) {
        Dealer one = dealerDao.findOne(flowDealerDto.getDealerId());
        if (one == null) {
            return new MessageResult(500, "交易员不存在!");
        }
        //查关联表
        DealerMemberRelation byMemberIdAndDealerId = dealerMemberRelationDao.findByMemberIdAndDealerId(flowDealerDto.getMemberId(), flowDealerDto.getDealerId());
        if (byMemberIdAndDealerId == null){
            return MessageResult.success("成功",false);
        }else{
            return MessageResult.success("成功",true);
        }
    }
    public Dealer save(Dealer dealer) {
        return dealerDao.save(dealer);
    }

    public Page<Dealer> findAll(Pageable pageable){
        return dealerDao.findAll(pageable);
    }
}
