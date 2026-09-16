-- dredge 断面测深登记 / 浅情判定规则 菜单与权限（jia-017）
-- 依赖表：t_sys_permission / t_sys_permission_role（管理员角色 488243256161730560）
-- 权限标识与后端 @RequiresPermissions 逐一对应：dredge:dgSurvey:* / dredge:dgRule:*

-- 目录：航道疏浚管理
INSERT INTO `t_sys_permission` VALUES
 (970000000000000001, '航道疏浚管理', NULL, '', 0, 0, '', 0, 'layui-icon layui-icon-app', 4, 0, 'admin', NOW(), NULL, NULL, NULL);

-- 菜单：断面测深记录
INSERT INTO `t_sys_permission` VALUES
 (970000000000000101, '断面测深记录', '断面测深记录展示', '/DgSurveyController/view', 0, 970000000000000001, 'dredge:dgSurvey:view', 1, 'layui-icon layui-icon-survey', 1, 0, 'admin', NOW(), NULL, NULL, NULL),
 (970000000000000102, '断面测深记录集合', '断面测深记录集合', '/DgSurveyController/list', 0, 970000000000000101, 'dredge:dgSurvey:list', 2, '', NULL, 0, 'admin', NOW(), NULL, NULL, NULL),
 (970000000000000103, '断面测深记录添加', '断面测深记录添加', '/DgSurveyController/add', 0, 970000000000000101, 'dredge:dgSurvey:add', 2, 'layui-icon layui-icon-add-1', NULL, 0, 'admin', NOW(), NULL, NULL, NULL),
 (970000000000000104, '断面测深记录修改', '断面测深记录修改', '/DgSurveyController/edit', 0, 970000000000000101, 'dredge:dgSurvey:edit', 2, 'layui-icon layui-icon-edit', NULL, 0, 'admin', NOW(), NULL, NULL, NULL),
 (970000000000000105, '断面测深记录删除', '断面测深记录删除', '/DgSurveyController/remove', 0, 970000000000000101, 'dredge:dgSurvey:remove', 2, 'layui-icon layui-icon-delete', NULL, 0, 'admin', NOW(), NULL, NULL, NULL),
 (970000000000000106, '断面测深记录复核', '断面测深记录复核', '/DgSurveyController/review', 0, 970000000000000101, 'dredge:dgSurvey:review', 2, 'layui-icon layui-icon-ok', NULL, 0, 'admin', NOW(), NULL, NULL, NULL);

-- 菜单：浅情判定规则
INSERT INTO `t_sys_permission` VALUES
 (970000000000000201, '浅情判定规则', '浅情判定规则展示', '/DgRuleController/view', 0, 970000000000000001, 'dredge:dgRule:view', 1, 'layui-icon layui-icon-set', 2, 0, 'admin', NOW(), NULL, NULL, NULL),
 (970000000000000202, '浅情判定规则集合', '浅情判定规则集合', '/DgRuleController/list', 0, 970000000000000201, 'dredge:dgRule:list', 2, '', NULL, 0, 'admin', NOW(), NULL, NULL, NULL),
 (970000000000000203, '浅情判定规则添加', '浅情判定规则添加', '/DgRuleController/add', 0, 970000000000000201, 'dredge:dgRule:add', 2, 'layui-icon layui-icon-add-1', NULL, 0, 'admin', NOW(), NULL, NULL, NULL),
 (970000000000000204, '浅情判定规则修改', '浅情判定规则修改', '/DgRuleController/edit', 0, 970000000000000201, 'dredge:dgRule:edit', 2, 'layui-icon layui-icon-edit', NULL, 0, 'admin', NOW(), NULL, NULL, NULL),
 (970000000000000205, '浅情判定规则删除', '浅情判定规则删除', '/DgRuleController/remove', 0, 970000000000000201, 'dredge:dgRule:remove', 2, 'layui-icon layui-icon-delete', NULL, 0, 'admin', NOW(), NULL, NULL, NULL);

-- 菜单：疏浚计划单
INSERT INTO `t_sys_permission` VALUES
 (970000000000000401, '疏浚计划单', '疏浚计划单展示', '/DgPlanController/view', 0, 970000000000000001, 'dredge:dgPlan:view', 1, 'layui-icon layui-icon-form', 3, 0, 'admin', NOW(), NULL, NULL, NULL),
 (970000000000000402, '疏浚计划单集合', '疏浚计划单集合', '/DgPlanController/list', 0, 970000000000000401, 'dredge:dgPlan:list', 2, '', NULL, 0, 'admin', NOW(), NULL, NULL, NULL),
 (970000000000000403, '疏浚计划单添加', '疏浚计划单添加', '/DgPlanController/add', 0, 970000000000000401, 'dredge:dgPlan:add', 2, 'layui-icon layui-icon-add-1', NULL, 0, 'admin', NOW(), NULL, NULL, NULL),
 (970000000000000404, '疏浚计划单修改', '疏浚计划单修改', '/DgPlanController/edit', 0, 970000000000000401, 'dredge:dgPlan:edit', 2, 'layui-icon layui-icon-edit', NULL, 0, 'admin', NOW(), NULL, NULL, NULL),
 (970000000000000405, '疏浚计划单删除', '疏浚计划单删除', '/DgPlanController/remove', 0, 970000000000000401, 'dredge:dgPlan:remove', 2, 'layui-icon layui-icon-delete', NULL, 0, 'admin', NOW(), NULL, NULL, NULL),
 (970000000000000406, '疏浚计划单归档', '疏浚计划单归档', '/DgPlanController/archive', 0, 970000000000000401, 'dredge:dgPlan:archive', 2, 'layui-icon layui-icon-ok', NULL, 0, 'admin', NOW(), NULL, NULL, NULL);

-- 授权给管理员角色
INSERT INTO `t_sys_permission_role` (id, role_id, permission_id) VALUES
 (970000000000000301, 488243256161730560, 970000000000000001),
 (970000000000000302, 488243256161730560, 970000000000000101),
 (970000000000000303, 488243256161730560, 970000000000000102),
 (970000000000000304, 488243256161730560, 970000000000000103),
 (970000000000000305, 488243256161730560, 970000000000000104),
 (970000000000000306, 488243256161730560, 970000000000000105),
 (970000000000000307, 488243256161730560, 970000000000000106),
 (970000000000000308, 488243256161730560, 970000000000000201),
 (970000000000000309, 488243256161730560, 970000000000000202),
 (970000000000000310, 488243256161730560, 970000000000000203),
 (970000000000000311, 488243256161730560, 970000000000000204),
 (970000000000000312, 488243256161730560, 970000000000000205),
 (970000000000000313, 488243256161730560, 970000000000000401),
 (970000000000000314, 488243256161730560, 970000000000000402),
 (970000000000000315, 488243256161730560, 970000000000000403),
 (970000000000000316, 488243256161730560, 970000000000000404),
 (970000000000000317, 488243256161730560, 970000000000000405),
 (970000000000000318, 488243256161730560, 970000000000000406);
