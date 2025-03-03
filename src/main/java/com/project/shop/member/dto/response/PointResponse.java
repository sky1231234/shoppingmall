package com.project.shop.member.dto.response;

import com.project.shop.member.domain.PointType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PointResponse {

//    {
//        "sumPoint" : "사용가능한 포인트",
//        "disappearPoint" : "소멸 예정 포인트",
//        "point" : [
//              {
//                  "pointId" : 1,
//                  "point" : 100,
//                  "deadlineDate" : "유효기간",
//                  "state" : "포인트상태",
//                  "date" : "사용/적립일"
//              }
//          ]
//    }

    private int sumPoint; //총 포인트
    private int disappearPoint; //소멸 예정 포인트
    private List<PointList> pointList;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class PointList{
        private long pointId; //포인트id
        private int point; //포인트
        private LocalDate deadlineDate; //유효기간
        private PointType state; //포인트 상태
        private LocalDateTime date; //적립/사용일
    }

}