-- 코드를 입력하세요
SELECT A.FLAVOR FROM FIRST_HALF AS A 
    JOIN ICECREAM_INFO AS B ON A.FLAVOR = B.FLAVOR
    WHERE A.TOTAL_ORDER > 3000 AND B.INGREDIENT_TYPE = 'fruit_based'
    ORDER BY A.TOTAL_ORDER DESC;