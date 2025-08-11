package com.tenco.jobpotal.alarm;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alarms")
@RequiredArgsConstructor
@Slf4j
public class AlarmRestController {

    private final AlarmService alarmService;

    /**
     * 사용자별 알람 목록 조회
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AlarmResponse.ListDTO>> getAlarmsByUserId(@PathVariable Long userId) {
        log.info("알람 목록 조회 API 호출 - 사용자ID: {}", userId);
        
        List<AlarmResponse.ListDTO> alarms = alarmService.getAlarmsByUserId(userId);
        
        log.info("API 응답 데이터 개수: {}", alarms.size());
        if (!alarms.isEmpty()) {
            log.info("첫 번째 응답 데이터: {}", alarms.get(0));
        }
        
        return ResponseEntity.ok(alarms);
    }

    /**
     * 알람 읽음 처리
     */
    @PutMapping("/read")
    public ResponseEntity<?> updateReadStatus(@RequestBody AlarmRequest.UpdateReadStatusDTO dto) {
        alarmService.updateReadStatus(dto);
        return ResponseEntity.ok().build();
    }

    /**
     * 읽지 않은 알람 개수 조회
     */
    @GetMapping("/unread-count/{userId}")
    public ResponseEntity<?> getUnreadCount(@PathVariable Long userId) {
        Long count = alarmService.getUnreadCount(userId);
        return ResponseEntity.ok(count);
    }
}
