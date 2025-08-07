INSERT INTO job_post
(comp_id, deadline, inst_date, posted_at, content, employment_type, inst_id, require_career_years, title)
VALUES
(1, DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 30 DAY), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Java 백엔드 개발', '정규직', 'HR001', '3', '백엔드 개발자 모집'),
(2, DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 45 DAY), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'React 프론트엔드 개발', '계약직', 'HR002', '2', '프론트엔드 개발자'),
(3, DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 60 DAY), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'AWS 클라우드 인프라 운영', '정규직', 'HR003', '4', 'DevOps 엔지니어'),
(4, DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 35 DAY), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '머신러닝 모델 개발', '정규직', 'HR004', '1', 'AI 엔지니어'),
(5, DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 50 DAY), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Kotlin 앱 개발', '계약직', 'HR005', '2', '안드로이드 앱 개발자'),
(9, DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 40 DAY), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '데이터 분석 및 시각화', '계약직', 'HR009', '2', '데이터 분석가');
