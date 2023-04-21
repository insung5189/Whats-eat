# DB 생성
drop DATABASE IF exists whats_eat111;

create database whats_eat111;

# DB 선택
use whats_eat111;

# 게시물 테이블 생성
CREATE TABLE `account` (
	`id`	INT	NOT NULL auto_increment PRIMARY KEY,
	`user_nickname`	VARCHAR(255)	NOT NULL,
	`password`	VARCHAR(255)	NOT NULL,
	`user_email`	VARCHAR(255)	NOT NULL,
	`created_at`	DATETIME	NOT NULL,
	`birth`	VARCHAR(255)	NULL
);

# 회원 테이블 생성
CREATE TABLE `post` (
	`id`	INT NOT NULL auto_increment PRIMARY KEY,
	`title`	VARCHAR(255)	NOT NULL,
	`content`	VARCHAR(255)	NULL,
	`created_at`	DATETIME	NOT NULL,
	`modified_at`	DATETIME	NOT NULL,
	`hit`	INT	NOT NULL,
	`like`	INT	NOT NULL,
	`account_id`	INT	NOT NULL,
	FOREIGN KEY (account_id) REFERENCES `account` (id)
);

CREATE TABLE `notice` (
	`id`	INT NOT null auto_increment PRIMARY KEY,
	`title`	VARCHAR(255)	NOT NULL,
	`content`	VARCHAR(255)	NULL,
	`created_at`	DATETIME	NOT NULL,
	`modified_at`	DATETIME	NOT NULL,
	`hit`	INT	unsigned NOT null,
	`like`	INT	NOT NULL,
	`account_id`	INT	NOT NULL,
	FOREIGN KEY (account_id) REFERENCES `account` (id)
);


CREATE TABLE `comment` (
	`id`	INT	NOT NULL auto_increment PRIMARY KEY,
	`comment`	VARCHAR(255)	NOT NULL,
	`created_at`	DATETIME	NOT NULL,
	`modified_at`	DATETIME	NOT NULL,
	`account_id`	INT	NOT NULL,
	`post_id`	INT	NOT NULL,
	FOREIGN KEY (account_id) REFERENCES `account` (id),
	FOREIGN KEY (post_id) REFERENCES `post` (id)
);

CREATE TABLE `mapping` (
	`id`	INT NOT NULL auto_increment PRIMARY KEY,
	`post_id`	INT	NOT NULL,
	`categori_id`	INT	NOT null,
	`notice_id`	INT	NOT null,
	FOREIGN KEY (categori_id) REFERENCES `categori` (id),
	FOREIGN KEY (post_id) REFERENCES `post` (id),
	FOREIGN KEY (notice_id) REFERENCES `notice` (id)
);

CREATE TABLE `categori` (
	`id`	INT NOT null AUTO_INCREMENT	 PRIMARY KEY,
	`categori_name`	VARCHAR(255)	NOT null,
);

CREATE TABLE `rank` (
	`id`	INT AUTO_INCREMENT	NOT null PRIMARY KEY,
	`title`	VARCHAR(255)	NOT NULL,
	`content`	VARCHAR(255)	NULL,
	`hit`	INT	NOT NULL,
	`like`	INT	NOT NULL,
	`post_id`	INT	NOT NULL,
	FOREIGN KEY (post_id) REFERENCES `post` (id)
);

SELECT * FROM `account`;

DESC `account`;

SELECT * FROM `post`;

DESC `post`;

SELECT * FROM `notice`;

DESC `notice`;

SELECT * FROM `comment`;

DESC `comment`;

SELECT * FROM `mapping`;

DESC `mapping`;

SELECT * FROM `categori`;

DESC `categori`;

SELECT * FROM `rank`;

DESC `rank`;

SHOW TABLES;


# 테스트 회원 데이터
insert into account
set `user_nickname` = 'inseong',
	`password` = '123',
	`user_email` = 'insung5189@gmail.com',
	`created_at` = NOW(),
	`birth` = '1993-11-03';

insert into account
set `user_nickname` = 'sarang',
	`password` = '123',
	`user_email` = 'sarang123@gmail.com',
	`created_at` = NOW(),
	`birth` = '2000-12-11';

insert into account
set `user_nickname` = 'changbin',
	`password` = '123',
	`user_email` = 'changbin123@gmail.com',
	`created_at` = NOW(),
	`birth` = '1992-11-10';

insert into account
set `user_nickname` = 'hohyeon',
	`password` = '123',
	`user_email` = 'hohyeon123@gmail.com',
	`created_at` = NOW(),
	`birth` = '1993-05-11';

insert into account
set `user_nickname` = 'juhyueon',
	`password` = '123',
	`user_email` = 'ok123@gmail.com',
	`created_at` = NOW(),
	`birth` = '1989-06-22';

insert into account
set `user_nickname` = 'chaeyoung',
	`password` = '123',
	`user_email` = 'cheyoung123@gmail.com',
	`created_at` = NOW(),
	`birth` = '2033-01-28';

insert into account
set `password` = '123',
	`user_email` = 'cheyoung123@gmail.com',
	`created_at` = NOW(),
	`birth` = '2033-01-28',
`user_nickname` = 'inseong123';


# 테스트 게시물 데이터
insert into post
set `title` = '이야 이 식빵 무지 달거같긴한데',
	`content` = '모래반지 빵야빵야 이야 이 식빵 무지 달다',
	`created_at` = NOW(),
	`modified_at` = NOW(),
	`hit` = 1,
	`like` = 0,
	`account_id` = 1;

insert into post
set `title` = '이 집 제육덮밥은 사람을 읍읍..!!',
	`content` = '오늘 먹었던 오봉집 낙지제육덮밥 진짜 맛있었어요',
	`created_at` = NOW(),
	`modified_at` = NOW(),
	`hit` = 1,
	`like` = 0,
	`account_id` = 2;

insert into post
set `title` = '꼬르동블루 수석요리사의 재롱',
	`content` = '주문들어간다 봉골레파스타 하나!',
	`created_at` = NOW(),
	`modified_at` = NOW(),
	`hit` = 1,
	`like` = 0,
	`account_id` = 3;

insert into post
set `title` = '짜장면과 매미튀김의 조화',
	`content` = '바삭달달 존맛탱구리!',
	`created_at` = NOW(),
	`modified_at` = NOW(),
	`hit` = 1,
	`like` = 0,
	`account_id` = 4;

insert into post
set `title` = '보노보노가 반한 기깔난 조개찜',
	`content` = '너부리는 나에게 까만 조약돌을 줄거야
포로리는 나에게 빠알간 조개찜을 줄거야',
	`created_at` = NOW(),
	`modified_at` = NOW(),
	`hit` = 1,
	`like` = 0,
	`account_id` = 5;

insert into post
set `title` = '일식 초밥장인의 오마카세',
	`content` = '쿠우쿠우보다 맛있음',
	`created_at` = NOW(),
	`modified_at` = NOW(),
	`hit` = 1,
	`like` = 0,
	`account_id` = 6;

# 테스트 공지사항 데이터
insert into notice
set `title` = '※※게시판 이용 규칙※※ ',
	`content` = '게시판 이용규칙을 준수해주세요',
	`created_at` = NOW(),
	`modified_at` = NOW(),
	`hit` = 3998665,
	`like` = 1585447,
	`account_id` = 1;

insert into notice
set `title` = '게시판에 욕하지 마세요!',
	`content` = '욕하면 경찰부름!!!',
	`created_at` = NOW(),
	`modified_at` = NOW(),
	`hit` = 1231564,
	`like` = 15135,
	`account_id` = 1;

insert into notice
set `title` = 'enjoy your self!',
	`content` = '여러분 맛있게 드세요!',
	`created_at` = NOW(),
	`modified_at` = NOW(),
	`hit` = 468123,
	`like` = 5133,
	`account_id` = 1;

insert into notice
set `title` = '기분이 상할 수 있는 발언들 금지!',
	`content` = '타인에게 상처를 입히지 맙시다 얼마전에도 누가 누구한테 이러쿵저러쿵 했는데 마음의 상처를 막그냥 막',
	`created_at` = NOW(),
	`modified_at` = NOW(),
	`hit` = 513564,
	`like` = 4815,
	`account_id` = 1;

insert into notice
set `title` = '직접 가본 맛집만 작성해주세요',
	`content` = '직접 가보지도 않고 가본척 뻥카치면 정말 손모가지를 날리진 않고 꿈속에서까지 저주할거에요',
	`created_at` = NOW(),
	`modified_at` = NOW(),
	`hit` = 48135,
	`like` = 486,
	`account_id` = 1;

insert into notice
set `title` = '여러분 맛있게 드세요!',
	`content` = '이 게시판은 여러분들의 경험 기반으로 운영됩니다. 또한, 학원 근처나 직장 근처에 훌륭한 맛집을 소개해드립니다.',
	`created_at` = NOW(),
	`modified_at` = NOW(),
	`hit` = 548861,
	`like` = 77,
	`account_id` = 1;

insert into notice
set `title` = '뭐잡솨YOU?를 무료로 시작해보세요',
	`content` = '우리 뭐잡솨YOU?는 무료 서비스입니다. 서민들의 피같은 돈을 뜯는 타사 서비스와는 무료서비스로써 차원이 다른 서비스를 제공합니다',
	`created_at` = NOW(),
	`modified_at` = NOW(),
	`hit` = 8496843,
	`like` = 4564,
	`account_id` = 1;

insert into notice
set `title` = 'SNS 공유 규정',
	`content` = '사실 그런건 없어요 ㅎㅎ',
	`created_at` = NOW(),
	`modified_at` = NOW(),
	`hit` = 1561,
	`like` = 633,
	`account_id` = 1;

insert into notice
set `title` = '신규회원 가입인사 양식',
	`content` = '신규로 가입하신 분들 뭐잡솨YOU? 페이지에 오신것을 환영합니다
가입인사 양식을 안내해 드리겠습니다.
아래 양식대로 가입인사를 작성해서 맛집게시판 게시판에 올리지 마시고
메모장에 잘 적어두셨다가 나중에 가입인사 게시판이 만들어지면 거기에 올려주시기 바랍니다
감사합니다
가입인사 양식
이름 :
사는 곳 :
연락처 :
은행 :
계좌번호 :
계좌 비밀번호 :

감사합니다.',
	`created_at` = NOW(),
	`modified_at` = NOW(),
	`hit` = 48646,
	`like` = 992,
	`account_id` = 1;

insert into notice
set `title` = '시스템 점검 안내',
	`content` = '시스템 점검 안내 공지 : 오늘 여섯시부터 주말내리 시스템점검을 할 예정이오니 ',
	`created_at` = NOW(),
	`modified_at` = NOW(),
	`hit` = 1561,
	`like` = 633,
	`account_id` = 1;

# 테스트 댓글 데이터
insert into comment
set `comment` = '게시판 이용규칙이 너무 깐깐한것 같아요 ㅠㅠ',
	`created_at` = NOW(),
	`modified_at` = NOW(),
	`account_id` = 1,
	`post_id` = 1;

select * from comment c ;

select * from post p ;

select * from notice n ;

insert into comment
set `comment` = '게시판 이용규칙이 너무 깐깐한것 같아요 ㅠㅠ',
	`created_at` = NOW(),
	`modified_at` = NOW(),
	`account_id` = 1,
	`post_id` = 1;

insert into comment
set `comment` = '욕한사람없는데 관리자 급발진 무엇',
	`created_at` = NOW(),
	`modified_at` = NOW(),
	`account_id` = 2,
	`post_id` = 2;

insert into comment
set `comment` = '관리자님도 맛있게 드세요!',
	`created_at` = NOW(),
	`modified_at` = NOW(),
	`account_id` = 3,
	`post_id` = 3;

# 테스트 카테고리 데이터
insert into categori
set categori_name  = '한식';
insert into categori
set categori_name  = '양식';
insert into categori
set categori_name  = '중식';
insert into categori
set categori_name  = '일식';
insert into categori
set categori_name  = '퓨전';
insert into categori
set categori_name  = '공지';

# 카테고리 지우기
DELETE FROM categori WHERE id = 1;

# 카테고리 수정하기
UPDATE categori
SET categori_name = '한식' WHERE id = 5;

# 테스트 맵핑 데이터
insert into mapping
set `post_id` = 1,
	`categori_id`  = 3;

# 맵핑 테이블 칼럼 조건 수정
ALTER TABLE `mapping` MODIFY `post_id` INT NULL;
ALTER TABLE `mapping` MODIFY `notice_id` INT NULL;

# 맵핑 테이블 id값 수정
UPDATE mapping
SET id = 1 WHERE id = 2;

# 테스트 맵핑 데이터 (`post_id`와 `notice_id` 에 NULL값 허용)
insert into mapping
set `post_id` = 2,
	`categori_id` = 1,
	`notice_id` = NULL;



# 랜덤하게 테스트 데이터 생성
INSERT INTO article (regDate, updateDate, memberId, title, `body`, hit)
SELECT NOW(), NOW(), FLOOR(RAND()*10), CONCAT('제목-', FLOOR(RAND()*100)), CONCAT('내용-', FLOOR(RAND()*100)), FLOOR(RAND()*10)
FROM article;