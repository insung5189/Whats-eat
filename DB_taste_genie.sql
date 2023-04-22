DROP DATABASE IF EXISTS taste_genie;

create database taste_genie;

use taste_genie;

show tables;


############################account############################

CREATE TABLE `account` (
	`id`			INT	NOT NULL auto_increment PRIMARY KEY,
	`user_id`		CHAR(255)	NOT NULL,
	`password`		CHAR(255)	NOT NULL,
	`user_name` 	CHAR(200) 	NOT NULL,
	`user_email`	CHAR(255)	NOT NULL,
	`created_at`	DATETIME	NOT NULL,
	`modified_at`	DATETIME	NOT NULL,
	`birthday`		CHAR(50)	NULL
);

############################account_data############################

#테이블 구조
DESC `account`;

#튜플 데이터 삽입
insert into account 
set `user_id` = 'inseong',
	`password` = '123',
	`user_name` = '황인성',
	`user_email` = 'insung5189@gmail.com',
	`created_at` = NOW(),
	`modified_at` = NOW(),
	`birthday` = '1993-11-03';

insert into account 
set `user_id` = 'sarang',
	`password` = '123',
	`user_name` = '김사랑',
	`user_email` = 'sarang123@gmail.com',
	`created_at` = NOW(),
	`modified_at` = NOW(),
	`birthday` = '2000-12-11';

insert into account 
set `user_id` = 'changbin',
	`password` = '123',
	`user_name` = '문창빈',
	`user_email` = 'changbin123@gmail.com',
	`created_at` = NOW(),
	`modified_at` = NOW(),
	`birthday` = '1992-11-10';

insert into account 
set `user_id` = 'hohyeon',
	`password` = '123',
	`user_name` = '김호현',
	`user_email` = 'hohyeon123@gmail.com',
	`created_at` = NOW(),
	`modified_at` = NOW(),
	`birthday` = '1993-05-11';

insert into account 
set `user_id` = 'juhyueon',
	`password` = '123',
	`user_name` = '송주현',
	`user_email` = 'ok123@gmail.com',
	`created_at` = NOW(),
	`modified_at` = NOW(),
	`birthday` = '1989-06-22';

insert into account 
set `user_id` = 'chaeyoung',
	`password` = '123',
	`user_name` = '송채영',
	`user_email` = 'cheyoung123@gmail.com',
	`created_at` = NOW(),
	`modified_at` = NOW(),
	`birthday` = '2033-01-28';

#############################################################



############################post############################

CREATE TABLE `post` (
	`id`			INT 		NOT NULL auto_increment PRIMARY KEY,
	`title`			CHAR(255)	NOT NULL,
	`content`		TEXT		NOT NULL,
	`created_at`	DATETIME	NOT NULL,
	`modified_at`	DATETIME	NOT NULL,
	`hit`			INT			NOT NULL,
	`like`			INT			NOT NULL,
	`account_id`	INT			NOT NULL,
	FOREIGN KEY (account_id) REFERENCES `account` (id)
);

############################post_data############################

#테이블 구조
DESC `post`;

#튜플 데이터 삽입
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

#############################################################



############################notice############################

CREATE TABLE `notice` (
	`id`			INT 		NOT NULL auto_increment PRIMARY KEY,
	`title`			CHAR(255) 	NOT NULL,
	`content`		TEXT		NOT NULL,
	`created_at`	DATETIME	NOT NULL,
	`modified_at`	DATETIME	NOT NULL,
	`hit`			INT			NOT NULL,
	`like`			INT			NOT NULL,
	`account_id`	INT			NOT NULL,
	FOREIGN KEY (account_id) REFERENCES `account` (id)
);

############################notice_data############################

#테이블 구조
DESC `notice`;

#튜플 데이터 삽입
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

#############################################################



############################comment###########################

CREATE TABLE `comment` (
	`id`			INT			NOT NULL auto_increment PRIMARY KEY,
	`comment`		TEXT		NOT NULL,
	`created_at`	DATETIME	NOT NULL,
	`modified_at`	DATETIME	NOT NULL,
	`account_id`	INT			NOT NULL,
	`post_id`		INT			NOT NULL,
	FOREIGN KEY (account_id) REFERENCES `account` (id),
	FOREIGN KEY (post_id) REFERENCES `post` (id)
);

############################comment_data###########################

#테이블 구조
DESC `comment`;

#튜플 데이터 삽입
insert into comment
set `comment` = '게시판 이용규칙이 너무 깐깐한것 같아요 ㅠㅠ',
	`created_at` = NOW(),
	`modified_at` = NOW(),
	`account_id` = 1,
	`post_id` = 1;

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

#############################################################



############################categori###########################

CREATE TABLE `categori` (
	`id`			INT 		NOT null AUTO_INCREMENT	 PRIMARY KEY,
	`categori_name`	VARCHAR(255)NOT null
);

############################categori_data###########################

#테이블 구조
DESC `categori`;

#튜플 데이터 삽입
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
set categori_name  = '공지(관리자용)';

#튜플 정보 삭제시에 사용
DELETE FROM categori WHERE id = 8;

#튜플 정보 수정시에 사용
UPDATE categori
SET categori_name = '한식' WHERE id = 5;

UPDATE categori
SET id = 6 WHERE id = 13;

#############################################################



############################rank###########################

CREATE TABLE `rank` (
	`id`			INT			NOT null AUTO_INCREMENT	 PRIMARY KEY,
	`title`			VARCHAR(255)NOT NULL,
	`content`		TEXT		NOT NULL,
	`hit`			INT		NOT NULL,
	`like`			INT		NOT NULL,
	`post_id`		INT		NOT NULL,
	FOREIGN KEY (post_id) REFERENCES `post` (id)
);

############################rank_data###########################

#테이블 구조
DESC `rank`;

#튜플 데이터 삽입



#############################################################



############################mapping###########################

CREATE TABLE `mapping` (
	`id`			INT 		NOT NULL auto_increment PRIMARY KEY,
	`categori_id`	INT			NOT null,
	`post_id`		INT			NULL,
	`notice_id`		INT			null,
	FOREIGN KEY (categori_id) REFERENCES `categori` (id),
	FOREIGN KEY (post_id) REFERENCES `post` (id),
	FOREIGN KEY (notice_id) REFERENCES `notice` (id)
);

############################mapping_data###########################

#테이블 구조
DESC `mapping`;

#튜플 데이터 삽입
insert into mapping 
set `post_id` = 1,
	`categori_id`  = 3;

insert into mapping 
set `post_id` = 2,
	`categori_id` = 1,
	`notice_id` = NULL; 

#튜플 정보 수정시에 사용
UPDATE mapping
SET id = 1 WHERE id = 2;

#테이블 속성 수정시에 사용
ALTER TABLE `mapping` MODIFY `post_id` INT NULL;
ALTER TABLE `mapping` MODIFY `notice_id` INT NULL;

#############################################################

############################선택###########################

SELECT * FROM `account`;

SELECT * FROM `post`;

SELECT * FROM `notice`;

SELECT * FROM `comment`;

SELECT * FROM `mapping`;

SELECT * FROM `categori`;

SELECT * FROM `rank`;

SHOW TABLES;

###################################################################