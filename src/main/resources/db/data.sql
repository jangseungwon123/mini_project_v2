INSERT INTO job_post
(comp_id, deadline, inst_date, posted_at, content, employment_type, inst_id, require_career_years, title)
VALUES
(1, DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 30 DAY), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Java 백엔드 개발', '정규직', 'HR001', '3', '백엔드 개발자 모집'),
(2, DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 45 DAY), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'React 프론트엔드 개발', '계약직', 'HR002', '2', '프론트엔드 개발자'),
(3, DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 60 DAY), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'AWS 클라우드 인프라 운영', '정규직', 'HR003', '4', 'DevOps 엔지니어'),
(4, DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 35 DAY), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '머신러닝 모델 개발', '정규직', 'HR004', '1', 'AI 엔지니어'),
(5, DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 50 DAY), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Kotlin 앱 개발', '계약직', 'HR005', '2', '안드로이드 앱 개발자'),
(9, DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 40 DAY), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '데이터 분석 및 시각화', '계약직', 'HR009', '2', '데이터 분석가');


INSERT INTO faq_info (title, content, inst_id, inst_date)
VALUES
('지원 자격이 어떻게 되나요?', '지원 자격은 포지션마다 다르며, 일반적으로 경력 2년 이상을 요구합니다.', 'admin', NOW()),
('이력서 양식이 정해져 있나요?', '별도 양식은 없으며 자유 양식의 PDF 또는 Word 문서를 제출하시면 됩니다.', 'hr_manager', NOW()),
('면접은 어떤 방식으로 진행되나요?', '서류 통과 후 1차 비대면, 2차 대면 면접이 진행됩니다.', 'recruiter1', NOW()),
('근무지는 어디인가요?', '근무지는 서울 본사이며, 일부 직무는 재택도 가능합니다.', 'admin', NOW()),
('복지 혜택은 무엇이 있나요?', '점심 식대, 자율 출퇴근제, 헬스장 이용권 등이 제공됩니다.', 'welfare_team', NOW()),
('서류 전형 결과는 언제 알 수 있나요?', '서류 접수 마감 후 5일 이내에 개별 연락을 드립니다.', 'hr_support', NOW()),
('합격 후 입사 일정은 어떻게 되나요?', '합격자 발표 후, 개인 일정 조율을 통해 입사일을 정합니다.', 'hr_manager', NOW());

