2023/03/23 By ChinHua

selectAll()其實有limit 100筆

insert() 可以使用keyholder去catch自動增加的id值，詳細寫法可看5-5

 namedParameterJdbcTemplate.query(sql,map,RowMapper<T>)
 -->RowMapper是用來將資料庫查詢出來的數據，轉換成java object