package com.project.shop.user.service;

import com.project.shop.user.domain.Point;
import com.project.shop.user.domain.User;
import com.project.shop.user.dto.request.PointRequest;
import com.project.shop.user.dto.request.PointUpdateRequest;
import com.project.shop.user.dto.response.PointResponse;
import com.project.shop.user.repository.PointRepository;
import com.project.shop.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PointService {

    private final PointRepository pointRepository;
    private final UserRepository userRepository;

    //포인트 전체 조회
    public PointResponse pointFindAll(long userId){

        var sumPoint = pointRepository.findSumPoint(userId);
        var disappearPoint = pointRepository.findDisappearPoint(userId);

        List<Point> pointList = pointRepository.findAllByUserId(userId);

        var list =  pointList.stream().map( x -> {
            return PointResponse.PointList.builder()
                    .pointId(x.getPointId())
                    .point(x.getPoint())
                    .deadlineDate(x.getDeadlineDate())
                    .state(x.getPointType())
                    .date(x.getInsertDate())
                    .build();
                })
                .toList();

        return PointResponse.builder()
                .sumPoint(sumPoint)
                .disappearPoint(disappearPoint)
                .pointList(list)
                .build();
    }

   
    //포인트 등록
    public void create(PointRequest pointRequest){

        //userId 받아오기
        long userId = 5;
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("NOT_FOUND_USER"));

        pointRepository.save(pointRequest.toEntity(user));

    }

    //포인트 수정
    public void update(Long pointId, PointUpdateRequest pointUpdateRequest){

        Point point = pointRepository.findById(pointId)
                .orElseThrow(() -> new RuntimeException("NOT_FOUND_POINT"));

        Point update = point.editPoint(pointUpdateRequest);
        pointRepository.save(update);

    }

    //포인트 삭제
    public void delete(long pointId){
        pointRepository.deleteById(pointId);
    }
}