메타코딩 Blog 만들기 프로젝트

- Database
  - 도커 이미지 다운로드
    ```
    docker pull mariadb
    ```

  - 도커 실행
    ```
    docker run --name mariadb -d -p 3306:3306 --restart=always -e MYSQL_ROOT_PASSWORD=5762 -v D:\Program_source\docker\mariadb\data:/var/lib/mysql  mariadb --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci
    ```