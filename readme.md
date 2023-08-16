Java 프로그래밍 study 프로젝트 저장소
=================================

## Gradle 다운로드시 인증서 문제  조치 방법

### Root 인증서 생성
1. 인증서 관리 실행
2. 신뢰할수 있는 루트 인증 기관 - 인증서
3. somansa root ca 인증서 - 내보내기
4. base64로 인코딩된 x.509(cer)
5. 내보낼 경로 선택후 실행


### Java에 인증서 등록
JDK or JRE BIN 폴더의 하위 cacerts 경로를 찾은 후 실행

```
keytool.exe -importcert -keystore "cacerts 파일 경로" -storepass changeit -trustcacerts -alias "my_company_cert" -file "인증서파일경로"
```

ex)
```
# keytool.exe -importcert -keystore "C:\Users\Administrator\.jdks\openjdk-16.0.1\lib\security\cacerts" -storepass changeit -trustcacerts -alias "my_company_cert" -file "C:\Temp\root_cert\somansa.cer"
```


### Gradle 빌드 재실행