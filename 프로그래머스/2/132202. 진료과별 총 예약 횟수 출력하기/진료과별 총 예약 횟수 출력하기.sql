-- 코드를 입력하세요
SELECT MCDP_CD AS '진료과 코드', COUNT(*) AS '5월예약건수' FROM APPOINTMENT
    WHERE YEAR(APNT_YMD) = '2022' AND MONTH(APNT_YMD) = '05'
    GROUP BY 1
    ORDER BY 2, 1;
    
    