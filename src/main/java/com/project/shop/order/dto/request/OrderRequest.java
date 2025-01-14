package com.project.shop.order.dto.request;

import com.project.shop.item.domain.Item;
import com.project.shop.item.domain.Option;
import com.project.shop.order.domain.*;
import com.project.shop.member.domain.Point;
import com.project.shop.member.domain.PointType;
import com.project.shop.member.domain.Member;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record OrderRequest(

//{
//              "orderTotalPrice" : "주문상품 총 가격",
//              "deliverFee" : "배송비",
//              "receiverName" : "받는사람이름",
//              "zipcode" :"우편번호",
//              "address" : "주소",
//              "addressDetail" : "상세주소",
//              "receiverPhoneNum" : "받는사람 전화번호",
//              "addrMsg" : "배송메시지"
//
//              "usedPoint" : "사용포인트",
//              "payCompany" : "카드사",
//              "cardNum" : "카드일련번호",
//              "payPrice" : "결제금액"
//
//                "item" : [
//                    {
//                      "itemId" : "상품id",
//                      "itemCount" : "주문상품 수량",
//                      "itemPrice" : "상품 가격"
//                      "itemSize" : "주문상품 사이즈",
//                      "itemColor" : "주문상품 색상",
//                    }
//               ]
//}

        @NotNull int orderTotalPrice,
        @NotNull int deliverFee,
        @NotBlank String receiverName,
        @NotBlank String zipcode,
        @NotBlank String address,
        @NotBlank String addressDetail,
        @NotBlank String receiverPhoneNum,
        @NotBlank String addrMsg,

        @NotNull int usedPoint,
        @NotBlank String payCompany,
        @NotBlank String cardNum,
        @NotNull int payPrice,

        @NotNull List<OrderItemRequest> orderItemRequestList

        ) {

        public Order orderToEntity(Member member, String orderNum, OrderType orderType){
            return Order.builder()
                    .member(member)
                    .orderNum(orderNum)
                    .deliverFee(this.deliverFee())
                    .point(this.usedPoint())
                    .price(this.orderTotalPrice())
                    .orderType(orderType)
                    .receiverName(this.receiverName())
                    .zipcode(this.zipcode())
                    .address(this.address())
                    .addrDetail(this.addressDetail())
                    .phoneNum(this.receiverPhoneNum())
                    .msg(this.addrMsg())
                    .insertDate(LocalDateTime.now())
                    .updateDate(LocalDateTime.now())
                    .build();
        }

        public Pay payToEntity(Order order){
            return Pay.builder()
                    .order(order)
                    .payCompany(this.payCompany())
                    .cardNum(this.cardNum())
                    .payPrice(this.payPrice())
                    .insertDate(LocalDateTime.now())
                    .updateDate(LocalDateTime.now())
                    .build();
        }

    public Point pointToEntity(Member member){
        return Point.builder()
                .member(member)
                .point(this.usedPoint())
                .deadlineDate(LocalDate.now())
                .pointType(PointType.사용)
                .insertDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
    }




    public record OrderItemRequest(
//            {
//              "itemId" : "상품id",
//              "itemCount" : "주문상품 수량",
//              "itemPrice" : "상품 가격"
//              "itemSize" : "주문상품 사이즈",
//              "itemColor" : "주문상품 색상",
//              }

            @NotNull long itemId,
            @NotNull int itemCount,
            @NotNull int itemPrice,
            @NotBlank String itemSize,
            @NotBlank String itemColor

    ) {

        public OrderItem toEntity(Item item, Order order, Option option){
            return OrderItem.builder()
                    .item(item)
                    .order(order)
                    .totalQuantity(this.itemCount())
                    .itemPrice(this.itemPrice())
                    .totalPrice(this.itemCount() * this.itemPrice())
                    .itemOptionId(option.getOptionId())
                    .orderItemType(OrderItemType.완료)
                    .build();
        }

    }


}
