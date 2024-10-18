-- 코드를 입력하세요
# SELECT A.PRODUCT_ID, A.PRODUCT_NAME, SUM(A.PRICE) AS TOTAL_SALES FROM FOOD_PRODUCT A
#     JOIN FOOD_ORDER B ON A.PRODUCT_ID = B.PRODUCT_ID
#     WHERE YEAR(B.PRODUCE_DATE) = '2022' AND MONTH(B.PRODUCE_DATE) = '05'
#     GROUP BY 1
#     ORDER BY 3 DESC, 1

SELECT B.PRODUCT_ID, A.PRODUCT_NAME, (SUM(B.AMOUNT) * A.PRICE) AS TOTAL_SALES FROM FOOD_ORDER B
    JOIN FOOD_PRODUCT A ON A.PRODUCT_ID = B.PRODUCT_ID
    WHERE YEAR(PRODUCE_DATE) = '2022' AND MONTH(PRODUCE_DATE) = '05'
    GROUP BY 1
    ORDER BY 3 DESC, 1