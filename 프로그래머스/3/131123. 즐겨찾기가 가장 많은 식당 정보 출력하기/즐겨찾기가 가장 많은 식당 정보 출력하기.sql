-- 코드를 입력하세요
SELECT A.FOOD_TYPE, A.REST_ID, A.REST_NAME, A.FAVORITES FROM REST_INFO A
    JOIN (SELECT FOOD_TYPE, MAX(FAVORITES) AS MAX_FAVORITES FROM REST_INFO 
          GROUP BY FOOD_TYPE) B
    ON A.FOOD_TYPE = B.FOOD_TYPE AND A.FAVORITES = B.MAX_FAVORITES
    ORDER BY 1 DESC;
    
# SELECT FOOD_TYPE, MAX(FAVORITES) FROM REST_INFO GROUP BY FOOD_TYPE;