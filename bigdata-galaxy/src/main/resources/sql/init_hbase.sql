-- 创建订单
CREATE TABLE IF NOT EXISTS tbl_order (
	id BIGINT not null primary key,
	order_code char(20),
	total_amount decimal(10,2),
	create_time date,
	user_id bigint
);

-- 插入数据
upsert into tbl_order values(1, 'A001', 10.5, '2019-3-19 23:35:00', 1);
upsert into tbl_order values(2, 'A002', 60.0, '2019-3-19 23:36:00', 2);
upsert into tbl_order values(3, 'B001', 66.6, '2019-3-20 01:01:00', 3);
upsert into tbl_order values(4, 'C001', 66.4, '2019-3-20 02:01:00', 3);