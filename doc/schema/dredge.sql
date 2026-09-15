-- dredge 航道水深测量与疏浚养护管理 -- schema (jia-017)
-- 列名与基线实体契约（@TableName/@TableField）逐列对齐，改列必须同步实体。
-- 库：jia_017

CREATE TABLE IF NOT EXISTS t_dg_export (
  id bigint NOT NULL COMMENT '主键',
  export_no varchar(64) DEFAULT NULL COMMENT '导出单号',
  reach_id bigint DEFAULT NULL COMMENT '河段ID',
  reach_code varchar(32) DEFAULT NULL COMMENT '河段编号(冗余，以档案为准)',
  begin_date datetime DEFAULT NULL COMMENT '起始日期',
  end_date datetime DEFAULT NULL COMMENT '结束日期',
  row_count int DEFAULT NULL COMMENT '导出行数',
  expire_date datetime DEFAULT NULL COMMENT '文件到期时间',
  valid_days int DEFAULT NULL COMMENT '文件有效剩余天数',
  export_status int DEFAULT NULL COMMENT '导出状态 0已生成 1已下载',
  export_by varchar(64) DEFAULT NULL COMMENT '导出人',
  del_flag int DEFAULT '0' COMMENT '删除标记 0正常 1删除',
  create_by varchar(64) DEFAULT NULL COMMENT '创建者',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_by varchar(64) DEFAULT NULL COMMENT '更新者',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  remark varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='测量台账导出记录';

CREATE TABLE IF NOT EXISTS t_dg_import (
  id bigint NOT NULL COMMENT '主键',
  batch_no varchar(64) DEFAULT NULL COMMENT '批次号',
  reach_id bigint DEFAULT NULL COMMENT '河段ID',
  reach_code varchar(32) DEFAULT NULL COMMENT '河段编号(冗余，以档案为准)',
  total_rows int DEFAULT NULL COMMENT '总行数',
  success_rows int DEFAULT NULL COMMENT '成功行数',
  fail_rows int DEFAULT NULL COMMENT '失败行数',
  fail_rate decimal(6,2) DEFAULT NULL COMMENT '失败率(%)',
  batch_status int DEFAULT NULL COMMENT '批次状态 0导入中 1已完成 2已作废',
  import_by varchar(64) DEFAULT NULL COMMENT '导入人',
  del_flag int DEFAULT '0' COMMENT '删除标记 0正常 1删除',
  create_by varchar(64) DEFAULT NULL COMMENT '创建者',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_by varchar(64) DEFAULT NULL COMMENT '更新者',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  remark varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='测量数据导入批次';

CREATE TABLE IF NOT EXISTS t_dg_material (
  id bigint NOT NULL COMMENT '主键',
  material_no varchar(64) DEFAULT NULL COMMENT '台账单号',
  warn_id bigint DEFAULT NULL COMMENT '关联预警单ID',
  warn_no varchar(64) DEFAULT NULL COMMENT '预警单号(冗余，以预警单为准)',
  material_name varchar(64) DEFAULT NULL COMMENT '物料名称',
  spec varchar(64) DEFAULT NULL COMMENT '规格型号',
  in_qty int DEFAULT NULL COMMENT '入库数量',
  use_qty int DEFAULT NULL COMMENT '消耗数量',
  remain_qty int DEFAULT NULL COMMENT '结存数量',
  keeper varchar(64) DEFAULT NULL COMMENT '保管员',
  material_status int DEFAULT NULL COMMENT '台账状态 0在用 1已用尽',
  del_flag int DEFAULT '0' COMMENT '删除标记 0正常 1删除',
  create_by varchar(64) DEFAULT NULL COMMENT '创建者',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_by varchar(64) DEFAULT NULL COMMENT '更新者',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  remark varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='疏浚物料台账';

CREATE TABLE IF NOT EXISTS t_dg_plan (
  id bigint NOT NULL COMMENT '主键',
  dredge_no varchar(64) DEFAULT NULL COMMENT '计划单号',
  reach_id bigint DEFAULT NULL COMMENT '河段ID',
  reach_code varchar(32) DEFAULT NULL COMMENT '河段编号(冗余，以档案为准)',
  plan_month varchar(16) DEFAULT NULL COMMENT '计划月份',
  plan_cube int DEFAULT NULL COMMENT '计划方量(m³)',
  done_cube int DEFAULT NULL COMMENT '完成方量(m³)',
  done_rate decimal(6,2) DEFAULT NULL COMMENT '完成率(%)',
  plan_by varchar(64) DEFAULT NULL COMMENT '编制人',
  plan_status int DEFAULT NULL COMMENT '计划状态 0待确认 1已归档',
  del_flag int DEFAULT '0' COMMENT '删除标记 0正常 1删除',
  create_by varchar(64) DEFAULT NULL COMMENT '创建者',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_by varchar(64) DEFAULT NULL COMMENT '更新者',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  remark varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='疏浚计划单';

CREATE TABLE IF NOT EXISTS t_dg_reach (
  id bigint NOT NULL COMMENT '主键',
  reach_code varchar(32) DEFAULT NULL COMMENT '河段编号',
  reach_name varchar(64) DEFAULT NULL COMMENT '河段名称',
  mileage varchar(64) DEFAULT NULL COMMENT '起止里程',
  length_km decimal(8,2) DEFAULT NULL COMMENT '河段长度(km)',
  grade varchar(32) DEFAULT NULL COMMENT '航道等级',
  open_date datetime DEFAULT NULL COMMENT '通航日期',
  status int DEFAULT NULL COMMENT '档案状态 0维护中 1已封闭',
  del_flag int DEFAULT '0' COMMENT '删除标记 0正常 1删除',
  create_by varchar(64) DEFAULT NULL COMMENT '创建者',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_by varchar(64) DEFAULT NULL COMMENT '更新者',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  remark varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='河段档案';

CREATE TABLE IF NOT EXISTS t_dg_rule (
  id bigint NOT NULL COMMENT '主键',
  rule_code varchar(32) DEFAULT NULL COMMENT '规则编号',
  rule_name varchar(64) DEFAULT NULL COMMENT '规则名称',
  dev1_max decimal(6,2) DEFAULT NULL COMMENT '良好档浅点率上限(%)',
  dev2_max decimal(6,2) DEFAULT NULL COMMENT '一般档浅点率上限(%)',
  dev3_max decimal(6,2) DEFAULT NULL COMMENT '紧张档浅点率上限(%)',
  status int DEFAULT NULL COMMENT '规则状态 0启用 1停用',
  del_flag int DEFAULT '0' COMMENT '删除标记 0正常 1删除',
  create_by varchar(64) DEFAULT NULL COMMENT '创建者',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_by varchar(64) DEFAULT NULL COMMENT '更新者',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  remark varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='浅情判定规则';

CREATE TABLE IF NOT EXISTS t_dg_scan_log (
  id bigint NOT NULL COMMENT '主键',
  scan_no varchar(64) DEFAULT NULL COMMENT '登记流水号',
  reach_id bigint DEFAULT NULL COMMENT '河段ID',
  reach_code varchar(32) DEFAULT NULL COMMENT '河段编号(冗余，以档案为准)',
  reach_name varchar(64) DEFAULT NULL COMMENT '河段名称(冗余，以档案为准)',
  survey_ref_no varchar(64) DEFAULT NULL COMMENT '关联测量单号',
  scan_type int DEFAULT NULL COMMENT '登记类型 1现场查询 2现场登记',
  scan_time datetime DEFAULT NULL COMMENT '登记时间',
  valid_date datetime DEFAULT NULL COMMENT '登记有效期至',
  valid_days int DEFAULT NULL COMMENT '有效期剩余天数',
  scan_status int DEFAULT NULL COMMENT '登记状态 0暂存 1已提交 2已归档',
  scan_by varchar(64) DEFAULT NULL COMMENT '登记人',
  device varchar(64) DEFAULT NULL COMMENT '登记设备',
  del_flag int DEFAULT '0' COMMENT '删除标记 0正常 1删除',
  create_by varchar(64) DEFAULT NULL COMMENT '创建者',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_by varchar(64) DEFAULT NULL COMMENT '更新者',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  remark varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='移动端现场测深登记';

CREATE TABLE IF NOT EXISTS t_dg_silt (
  id bigint NOT NULL COMMENT '主键',
  silt_no varchar(64) DEFAULT NULL COMMENT '监测单号',
  reach_id bigint DEFAULT NULL COMMENT '河段ID',
  reach_code varchar(32) DEFAULT NULL COMMENT '河段编号(冗余，以档案为准)',
  silt_depth decimal(6,2) DEFAULT NULL COMMENT '回淤厚度(m)',
  interval_days int DEFAULT NULL COMMENT '复测间隔(天)',
  monitor_date datetime DEFAULT NULL COMMENT '监测日期',
  next_date datetime DEFAULT NULL COMMENT '下次复测日期',
  remain_days int DEFAULT NULL COMMENT '距下次复测剩余天数',
  monitor_by varchar(64) DEFAULT NULL COMMENT '监测人',
  silt_status int DEFAULT NULL COMMENT '处置进度 0待复核 1已复核 2已归档',
  del_flag int DEFAULT '0' COMMENT '删除标记 0正常 1删除',
  create_by varchar(64) DEFAULT NULL COMMENT '创建者',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_by varchar(64) DEFAULT NULL COMMENT '更新者',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  remark varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='回淤监测记录';

CREATE TABLE IF NOT EXISTS t_dg_stat (
  id bigint NOT NULL COMMENT '主键',
  stat_no varchar(64) DEFAULT NULL COMMENT '统计单号',
  reach_id bigint DEFAULT NULL COMMENT '河段ID',
  reach_code varchar(32) DEFAULT NULL COMMENT '河段编号(冗余，以档案为准)',
  stat_month varchar(16) DEFAULT NULL COMMENT '统计月份',
  should_count int DEFAULT NULL COMMENT '应测断面数',
  done_count int DEFAULT NULL COMMENT '已测断面数',
  qual_rate decimal(6,2) DEFAULT NULL COMMENT '合格率(%)',
  stat_status int DEFAULT NULL COMMENT '统计状态 0待确认 1已归档',
  stat_by varchar(64) DEFAULT NULL COMMENT '统计人',
  del_flag int DEFAULT '0' COMMENT '删除标记 0正常 1删除',
  create_by varchar(64) DEFAULT NULL COMMENT '创建者',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_by varchar(64) DEFAULT NULL COMMENT '更新者',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  remark varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='疏浚质量合格率统计';

CREATE TABLE IF NOT EXISTS t_dg_survey (
  id bigint NOT NULL COMMENT '主键',
  survey_no varchar(64) DEFAULT NULL COMMENT '测量单号',
  reach_id bigint DEFAULT NULL COMMENT '河段ID',
  reach_code varchar(32) DEFAULT NULL COMMENT '河段编号(冗余，以档案为准)',
  rule_id bigint DEFAULT NULL COMMENT '判定规则ID',
  rule_code varchar(32) DEFAULT NULL COMMENT '规则编号(冗余，以档案为准)',
  shoal_rate decimal(6,2) DEFAULT NULL COMMENT '浅点率(%)',
  shoal_level int DEFAULT NULL COMMENT '浅情等级 1良好 2一般 3紧张 4告急',
  survey_date datetime DEFAULT NULL COMMENT '测量日期',
  survey_by varchar(64) DEFAULT NULL COMMENT '测量人',
  survey_status int DEFAULT NULL COMMENT '复核状态 0待复核 1已复核',
  del_flag int DEFAULT '0' COMMENT '删除标记 0正常 1删除',
  create_by varchar(64) DEFAULT NULL COMMENT '创建者',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_by varchar(64) DEFAULT NULL COMMENT '更新者',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  remark varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='断面测深记录';

CREATE TABLE IF NOT EXISTS t_dg_warn (
  id bigint NOT NULL COMMENT '主键',
  warn_no varchar(64) DEFAULT NULL COMMENT '预警单号',
  reach_id bigint DEFAULT NULL COMMENT '河段ID',
  reach_code varchar(32) DEFAULT NULL COMMENT '河段编号(冗余，以档案为准)',
  warn_level int DEFAULT NULL COMMENT '预警级别 1蓝色 2黄色 3橙色 4红色',
  raise_date datetime DEFAULT NULL COMMENT '预警日期',
  due_date datetime DEFAULT NULL COMMENT '处置时限',
  remain_days int DEFAULT NULL COMMENT '剩余处置天数',
  warn_by varchar(64) DEFAULT NULL COMMENT '登记人',
  warn_status int DEFAULT NULL COMMENT '预警状态 0待处置 1已处置 2已关闭',
  del_flag int DEFAULT '0' COMMENT '删除标记 0正常 1删除',
  create_by varchar(64) DEFAULT NULL COMMENT '创建者',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_by varchar(64) DEFAULT NULL COMMENT '更新者',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  remark varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='浅情预警单';

CREATE TABLE IF NOT EXISTS t_dg_work (
  id bigint NOT NULL COMMENT '主键',
  work_no varchar(64) DEFAULT NULL COMMENT '作业单号',
  reach_id bigint DEFAULT NULL COMMENT '河段ID',
  reach_code varchar(32) DEFAULT NULL COMMENT '河段编号(冗余，以档案为准)',
  vessel varchar(64) DEFAULT NULL COMMENT '施工船舶',
  start_date datetime DEFAULT NULL COMMENT '开工日期',
  finish_due datetime DEFAULT NULL COMMENT '完工时限',
  remain_days int DEFAULT NULL COMMENT '距完工时限剩余天数',
  foreman varchar(64) DEFAULT NULL COMMENT '施工负责人',
  work_status Integer DEFAULT NULL COMMENT '作业进度 0待进场 1施工中 2已完工',
  del_flag int DEFAULT '0' COMMENT '删除标记 0正常 1删除',
  create_by varchar(64) DEFAULT NULL COMMENT '创建者',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_by varchar(64) DEFAULT NULL COMMENT '更新者',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  remark varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='疏浚作业单';

-- 初始档案数据（验收测试依赖 id=1 启用 / id=2 停用）
INSERT INTO t_dg_reach (id, reach_code, reach_name, mileage, length_km, grade, open_date, status, del_flag, create_by, create_time)
VALUES (1, 'HD-0001', '青湾区下段', 'K20+000~K28+500', 8.50, 'III级', DATE_SUB(NOW(), INTERVAL 2500 DAY), 0, 0, 'sys', NOW()),
       (2, 'HD-0002', '老港池停用段', 'K05+000~K07+200', 2.20, 'V级', DATE_SUB(NOW(), INTERVAL 4100 DAY), 1, 0, 'sys', NOW());

INSERT INTO t_dg_rule (id, rule_code, rule_name, dev1_max, dev2_max, dev3_max, status, del_flag, create_by, create_time)
VALUES (1, 'DR-01', '浅情判定规则', 5.00, 12.00, 25.00, 0, 0, 'sys', NOW()),
       (2, 'DR-02', '停用版判定规则', 5.00, 12.00, 25.00, 1, 0, 'sys', NOW());

INSERT INTO t_dg_warn (id, warn_no, reach_id, reach_code, warn_level, raise_date, due_date, remain_days, warn_by, warn_status, del_flag, create_by, create_time)
VALUES (1, 'DW-0001', 1, 'HD-0001', 3, DATE_SUB(NOW(), INTERVAL 3 DAY), DATE_ADD(NOW(), INTERVAL 12 DAY), 12, '吴测量', 0, 0, 'sys', NOW()),
       (2, 'DW-0002', 1, 'HD-0001', 1, DATE_SUB(NOW(), INTERVAL 40 DAY), DATE_SUB(NOW(), INTERVAL 5 DAY), 0, '郑测量', 2, 0, 'sys', NOW());
