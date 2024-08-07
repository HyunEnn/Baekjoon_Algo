-- 코드를 입력하세요
SELECT MONTH(START_DATE) AS MONTH, CAR_ID, COUNT(CAR_ID) AS RECORDS FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY 
    WHERE CAR_ID IN (SELECT CAR_ID FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY 
                    WHERE MONTH(START_DATE) IN ('08', '09', '10')
                    GROUP BY CAR_ID
                    HAVING COUNT(CAR_ID) >= 5) -- 여기서 CAR_ID를 뽑고 날짜 조건을 한번 더 걸어줘야함.
                    and START_DATE >= '2022-08-01' and START_DATE <= '2022-10-31'
    GROUP BY MONTH, CAR_ID
    ORDER BY 1, 2 DESC;
    
# SELECT * FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY WHERE CAR_ID = 15;
    
    
-- BETWEEN (YEAR(START_DATE) = '2022' AND MONTH(START_DATE) = '08') AND (YEAR(END_DATE) = '2022' AND MONTH(END_DATE) = '10')