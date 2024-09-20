# 8~10월까지의 총 대여 횟수가 5회 이상인 값들을 조회
WITH CAR_RENTAL_MONTHS AS (
    SELECT CAR_ID FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
    WHERE START_DATE BETWEEN '2022-08-01' AND '2022-10-31'
    GROUP BY CAR_ID
    HAVING COUNT(*) >= 5
)

-- 코드를 입력하세요
# SELECT MONTH(START_DATE) AS MONTH, CAR_ID ,COUNT(CAR_ID) AS RECORDS
#     FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY 
#     WHERE CAR_ID 

SELECT MONTH(START_DATE) AS MONTH, CAR_ID, COUNT(CAR_ID) AS RECORDS
    FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
    WHERE START_DATE BETWEEN '2022-08-01' AND '2022-10-31'
    AND CAR_ID IN (SELECT CAR_ID FROM CAR_RENTAL_MONTHS)
    GROUP BY MONTH, CAR_ID
    HAVING COUNT(*) > 0
    ORDER BY 1, 2 DESC;
    