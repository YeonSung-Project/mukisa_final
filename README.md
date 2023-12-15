# mukisa_final


![image](https://github.com/YeonSung-Project/mukisa_final/assets/85202681/d10c2852-0293-48ef-bf1e-cfd8618d0caa)

(start.spring.io settings img)

<hr>


db port : 3306<br>
url port : 8080<br>
<hr>
file upload 기능 encoding - utf8mb4 설정.<br>
1. application.propertis에 jdbc연결 부분<br>
2. mariaDB 프롬프트로 명령어 입력하여 데이터베이스 인코딩도 서버와 맞춤<br>
   mariadb -uroot -p 입력<br>
   alter database mukisa_tea_db character set = utf8mb4 collate = utf8mb4_unicode_ci;<br>
   +<br>
   heidSQL에서 인코딩이 필요한 저장 테이블 설정하여 테이블에 utf8mb4_bin GUI환경에서 세팅해도 가능.
