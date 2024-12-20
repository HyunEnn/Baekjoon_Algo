-- 코드를 작성해주세요

WITH MAXFISH AS (
    SELECT FISH_TYPE, MAX(LENGTH) AS LENGTH FROM FISH_INFO
    GROUP BY 1
)

SELECT A.ID, B.FISH_NAME, C.LENGTH AS LENGTH FROM FISH_INFO A 
    JOIN FISH_NAME_INFO B ON A.FISH_TYPE = B.FISH_TYPE
    JOIN MAXFISH C ON A.FISH_TYPE = C.FISH_TYPE 
                AND A.LENGTH = C.LENGTH;