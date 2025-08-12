-- 데이터베이스 초기화 스크립트
-- 외래 키 제약조건을 만족시키기 위해 부모 테이블부터 순서대로 데이터를 추가합니다.

-- 1. 기업 회원 데이터 (comp_user) - 부모 테이블
-- comp_info에서 comp_user_id 1~10을 사용하므로 미리 생성합니다.
INSERT INTO comp_user (comp_user_id, comp_user_login_id, comp_user_password, comp_user_name, comp_user_email, comp_reg_number, comp_user_nickname) VALUES
(1, 'openai', '1234', '김승민', 'ceo@openai.kr', '111-11-11111', '오픈AI'),
(2, 'naver', '1234', '이재석', 'ceo@naver.com', '222-22-22222', '네이버'),
(3, 'kakao', '1234', '홍길동', 'ceo@kakao.com', '333-33-33333', '카카오'),
(4, 'samsung', '1234', '김현석', 'ceo@samsung.com', '444-44-44444', '삼성전자'),
(5, 'ubisoft', '1234', '이브 기예모', 'ceo@ubisoft.com', '555-55-55555', '유비소프트'),
(6, 'vue-devs', '1234', '에반 유', 'ceo@vuejs.org', '666-66-66666', 'VueJS'),
(7, 'google-flutter', '1234', '순다르 피차이', 'ceo@google.com', '777-77-77777', '구글'),
(8, 'amazon-aws', '1234', '앤디 재시', 'ceo@amazon.com', '888-88-88888', '아마존'),
(9, 'jetbrains', '1234', '막심 샤피로프', 'ceo@jetbrains.com', '999-99-99999', '젯브레인즈'),
(10, 'apple', '1234', '팀 쿡', 'ceo@apple.com', '101-01-01010', '애플');

-- 2. 기업 정보 데이터 (comp_info) - 부모 테이블
-- job_post에서 comp_id 1~10을 사용하므로 미리 생성합니다.
INSERT INTO comp_info (comp_id, company_name, company_ceo_name, company_desc, comp_user_id, inst_date, inst_id) VALUES
(1, 'OpenAI Korea', '김승민', '인공지능 연구 및 개발 전문 기업입니다.', 1, NOW(), 'admin'),
(2, 'Naver Corporation', '이재석', '대한민국 대표 인터넷 포털 서비스 제공 회사', 2, NOW(), 'admin'),
(3, 'Kakao Corp', '홍길동', '모바일 메신저 및 플랫폼 서비스 기업', 3, NOW(), 'admin'),
(4, 'Samsung Electronics', '김현석', '글로벌 전자제품 및 반도체 제조 기업', 4, NOW(), 'admin'),
(5, 'Ubisoft', '이브 기예모', 'AAA 게임 개발사', 5, NOW(), 'admin'),
(6, 'Vue.js Org', '에반 유', 'Vue.js 프레임워크 개발 및 유지보수', 6, NOW(), 'admin'),
(7, 'Google Flutter Team', '순다르 피차이', '크로스플랫폼 UI 툴킷', 7, NOW(), 'admin'),
(8, 'Amazon Web Services', '앤디 재시', '클라우드 컴퓨팅 서비스', 8, NOW(), 'admin'),
(9, 'JetBrains', '막심 샤피로프', '개발자 도구 전문 기업', 9, NOW(), 'admin'),
(10, 'Apple', '팀 쿡', '혁신적인 소비자 가전 및 소프트웨어', 10, NOW(), 'admin');

-- 3. 스킬 목록 데이터 (skill_list) - 부모 테이블
-- job_post에서 skill_id 1~10을 사용하므로 미리 생성합니다.
INSERT INTO skill_list (skill_id, skill_grp_id, inst_date, inst_id, skill_name) VALUES
(1, 1, NOW(), 'admin', 'Java Spring Boot'),
(2, 1, NOW(), 'admin', 'React.js'),
(3, 1, NOW(), 'admin', 'Node.js'),
(4, 1, NOW(), 'admin', 'Python Django'),
(5, 1, NOW(), 'admin', 'C# .NET'),
(6, 1, NOW(), 'admin', 'Vue.js'),
(7, 1, NOW(), 'admin', 'Flutter'),
(8, 2, NOW(), 'admin', 'AWS Cloud'),
(9, 1, NOW(), 'admin', 'Kotlin Android'),
(10, 1, NOW(), 'admin', 'Swift iOS');

-- 4. 채용 공고 데이터 (job_post) - 자식 테이블
-- 부모 테이블(comp_info, skill_list)에 데이터가 생성된 후 추가합니다.
INSERT INTO job_post (comp_id, skill_id, title, content, require_career_years, employment_type, inst_id, inst_date, posted_at, deadline) VALUES
(1, 1, '백엔드 개발자', 'Spring Boot 기반 API 개발', '3', '정규직', 'HR001', NOW(), NOW(), '2025-12-31 23:59:59'),
(2, 2, '프론트엔드 개발자', 'React.js UI 구현', '2', '정규직', 'HR002', NOW(), NOW(), '2025-11-30 23:59:59'),
(3, 3, '풀스택 개발자', 'Node.js + Vue.js 풀스택 개발', '4', '계약직', 'HR003', NOW(), NOW(), '2025-10-31 23:59:59'),
(4, 4, '데이터 엔지니어', 'Django 기반 데이터 파이프라인 구축', '5', '정규직', 'HR004', NOW(), NOW(), '2025-12-15 23:59:59'),
(5, 5, '게임 클라이언트 개발자', 'C# 기반 게임 개발', '2', '정규직', 'HR005', NOW(), NOW(), '2025-09-30 23:59:59'),
(6, 6, '웹 프론트엔드 개발자', 'Vue.js SPA 개발', '1', '인턴', 'HR006', NOW(), NOW(), '2025-08-31 23:59:59'),
(7, 7, '모바일 앱 개발자', 'Flutter 크로스플랫폼 앱 개발', '3', '정규직', 'HR007', NOW(), NOW(), '2025-12-20 23:59:59'),
(8, 8, '클라우드 엔지니어', 'AWS 기반 인프라 구축', '4', '정규직', 'HR008', NOW(), NOW(), '2025-11-15 23:59:59'),
(9, 9, '안드로이드 개발자', 'Kotlin 기반 앱 개발', '2', '계약직', 'HR009', NOW(), NOW(), '2025-10-15 23:59:59'),
(10, 10, 'iOS 개발자', 'Swift 기반 iOS 앱 개발', '3', '정규직', 'HR010', NOW(), NOW(), '2025-09-15 23:59:59');

-- 5. FAQ 데이터 (faq_info)
INSERT INTO faq_info (title, content, inst_id, inst_date) VALUES
('지원 자격이 어떻게 되나요?', '지원 자격은 포지션마다 다르며, 일반적으로 경력 2년 이상을 요구합니다.', 'admin', NOW()),
('이력서 양식이 정해져 있나요?', '별도 양식은 없으며 자유 양식의 PDF 또는 Word 문서를 제출하시면 됩니다.', 'hr_manager', NOW()),
('면접은 어떤 방식으로 진행되나요?', '서류 통과 후 1차 비대면, 2차 대면 면접이 진행됩니다.', 'recruiter1', NOW()),
('근무지는 어디인가요?', '근무지는 서울 본사이며, 일부 직무는 재택도 가능합니다.', 'admin', NOW()),
('복지 혜택은 무엇이 있나요?', '점심 식대, 자율 출퇴근제, 헬스장 이용권 등이 제공됩니다.', 'welfare_team', NOW()),
('서류 전형 결과는 언제 알 수 있나요?', '서류 접수 마감 후 5일 이내에 개별 연락을 드립니다.', 'hr_support', NOW()),
('합격 후 입사 일정은 어떻게 되나요?', '합격자 발표 후, 개인 일정 조율을 통해 입사일을 정합니다.', 'hr_manager', NOW());