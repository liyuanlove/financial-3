package com.imooc.seller.repositories;

import com.imooc.entity.VerificationOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * 对账相关
 * Created by songyouyu on 2018/6/17
 */
public interface VerifyRepository extends JpaRepository<VerificationOrder, String>,
        JpaSpecificationExecutor<VerificationOrder> {

    /* 生成某段时间内[start, end)的chanId的对账数据列表 */
    @Query(value = "select concat_ws('|', order_id, outer_order_id, chan_id, chan_user_id, product_id, order_type, amount, date_format(create_at, '%Y-%m-%d %H:%i:%s'))\n" +
            "from order_t where order_status = 'success' and chan_id  = ?1 and \n" +
            "create_at >= ?2 and create_at <= ?3", nativeQuery = true)
    List<String> queryVerificationOrders(String chanId, Date start, Date end);

    @Query(value = "SELECT t.`order_id` FROM order_t t LEFT JOIN verification_order v ON t.`chan_id` = ?1 AND t.`outer_order_id` = v.`order_id` WHERE v.`order_id` IS NULL AND t.create_at >= ?2 AND t.create_at < ?3",nativeQuery = true)
    List<String> queryExcessOrders(String chanId, Date start,Date end);

    @Query(value = "SELECT v.`order_id` FROM verification_order v LEFT JOIN order_t t ON t.`chan_id` = ?1 AND v.`outer_order_id` = t.`order_id` WHERE t.`order_id` IS NULL AND v.create_at >= ?2 AND v.create_at < ?3",nativeQuery = true)
    List<String> queryMissOrders(String chanId, Date start,Date end);

    @Query(value = "SELECT t.order_id FROM order_t t JOIN verification_order v ON t.`chan_id` = ?1 AND t.`outer_order_id` = v.`order_id` WHERE CONCAT_WS('|',t.chan_id,t.chan_user_id,t.product_id,t.order_type,t.amount,DATE_FORMAT( t.create_at,'%Y-%m-%d %H:%i:%s')) != CONCAT_WS('|',v.chan_id,v.chan_user_id,v.product_id,v.order_type,v.amount,DATE_FORMAT( v.create_at,'%Y-%m-%d %H:%i:%s')) AND t.create_at >= ?2 AND t.create_at < ?3",nativeQuery = true)
    List<String> queryDifferentOrders(String chanId, Date start,Date end);

}
