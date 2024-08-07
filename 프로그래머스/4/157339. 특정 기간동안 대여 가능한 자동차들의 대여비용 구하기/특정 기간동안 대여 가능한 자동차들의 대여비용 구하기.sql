-- 코드를 입력하세요
# '세단', 'SUV' 이면서, 2022-11-1 ~ 2022-11-30 일에 빌릴 수 있는 차 ID
WITH 
ABLE_CAR AS (
    SELECT DISTINCT CAR_ID
    FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
    WHERE CAR_ID NOT IN (
        SELECT CAR_ID
        FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
        WHERE START_DATE <= '2022-11-30' AND END_DATE >= '2022-11-01'
    ) -- 시작일이 11.30 전이면서 종료일이 11.01 이후인 것들을 제외하면 나옴
),

DIS_PLAN AS (
    SELECT * FROM CAR_RENTAL_COMPANY_DISCOUNT_PLAN 
    WHERE DURATION_TYPE = '30일 이상'
)

SELECT B.CAR_ID, A.CAR_TYPE, ROUND((A.DAILY_FEE * (1 - C.DISCOUNT_RATE * 0.01) * 30), 0) AS FEE FROM CAR_RENTAL_COMPANY_CAR A 
    JOIN ABLE_CAR B ON A.CAR_ID = B.CAR_ID
    JOIN DIS_PLAN C ON A.CAR_TYPE = C.CAR_TYPE
    WHERE A.CAR_TYPE IN ('세단', 'SUV')
        AND A.DAILY_FEE * (1 - C.DISCOUNT_RATE*0.01) * 30 > 500000
        AND A.DAILY_FEE * (1 - C.DISCOUNT_RATE*0.01) * 30 < 2000000
    ORDER BY 3 DESC, 2, 1 DESC;
